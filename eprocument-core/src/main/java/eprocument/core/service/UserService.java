package eprocument.core.service;

import eprocument.common.user.AuthDto;
import eprocument.core.domain.Token;
import eprocument.core.domain.UserEntity;
import eprocument.core.repository.TokenRepository;
import eprocument.core.repository.UserRepository;
import eprocument.core.service.mail.EmailService;
import eprocument.core.service.security.JwtService;
import ifmis.framework.common.audit.Audit;
import ifmis.framework.core.response.Response;
import ifmis.framework.persistence.service.AbstractModifyService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

@Audit
@RequiredArgsConstructor
@Service
public class UserService extends AbstractModifyService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncode;
    private final EmailService emailService;

    public Response<UserEntity> createUser(final UserEntity entity) throws MessagingException {

        var user = UserEntity.builder()
                .enabled(false)
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .nationalId(entity.getNationalId())
                .password(passwordEncode.encode(entity.getPassword()))
                .roles(entity.getRoles())
                .build();

   var saveUser= userRepository.create(user);

        sendValidationEmail(user);

        return new Response<>(saveUser);
    }

    private void sendValidationEmail(UserEntity user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        var confirmationUrl = "http://localhost:8080/api/v1/users/activate?token=" + newToken;
        var emailTemplateName = "activate_account";
        var subject = "Activate your account";
        var username = user.getFirstName() + " " + user.getLastName();

        emailService.sendEmailToRabbit(user.getEmail(), username, emailTemplateName, confirmationUrl, newToken, subject);

    }

    private String  generateAndSaveActivationToken(UserEntity user) {
        var activationCode = generateActivationCode(6);

        var token = Token.builder()
                .token(activationCode)
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        tokenRepository.create(token);

        return activationCode;


    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            stringBuilder.append(characters.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }

    public AuthenticationResponse loginUser(final AuthDto entity) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        entity.getEmail(),
                        entity.getPassword()));

        var claims = new HashMap<String, Object>();
        var user = ((UserEntity) auth.getPrincipal());
        System.out.println("User: " + user);
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());


        var jwtToken = jwtService.generateToken(claims, user);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public Response<UserEntity> activateUser(final String token) throws MessagingException {
        var tokenEntity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (tokenEntity.getValidatedAt() != null) {

            throw new RuntimeException("Token already used");
        }

        if (tokenEntity.getExpiresAt().isBefore(LocalDateTime.now())) {
            var user = tokenEntity.getUser();
            System.out.println("User: " + user);
            sendValidationEmail(user);
            throw new RuntimeException("Token expired");
        }

        tokenEntity.setValidatedAt(LocalDateTime.now());
        tokenRepository.update(tokenEntity);

        var user = tokenEntity.getUser();
        user.setEnabled(true);
        userRepository.update(user);

        return new Response<>(user);
    }

}
