package eprocument.event.user;

import eprocument.common.user.CreateUserEvent;
import eprocument.common.user.auth.AuthEvent;
import eprocument.core.domain.UserEntity;
import eprocument.core.service.AuthenticationResponse;
import eprocument.core.service.UserService;
import ifmis.framework.common.audit.Audit;
import ifmis.framework.core.dto.MessageDto;
import ifmis.framework.core.message.IUserMessage;
import ifmis.framework.core.response.Response;
import ifmis.framework.persistence.service.AbstractEventProcessor;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Audit
@Service
public class UserEventProcessor extends AbstractEventProcessor {


    private final UserTransformer userTransformer;

    private final AuthTransformer authTransformer;

    private final UserService userService;

    public Response<MessageDto> createUser(final CreateUserEvent event) throws MessagingException {
        UserEntity entity = userTransformer.transform(event.getDto());
         userService.createUser(entity);
        return new Response<>(IUserMessage.INFORMATION_SAVED);}

    // login user
    public Response<AuthenticationResponse> loginUser(final AuthEvent event) {
        UserEntity entity = authTransformer.transform(event.getDto());
      AuthenticationResponse authenticationResponse= userService.loginUser(event.getDto());
        return new Response<>(authenticationResponse);
    }

    public Response<MessageDto> verifyUser(String token) throws MessagingException {
        userService.activateUser(token);
        return new Response<>(IUserMessage.INFORMATION_SAVED);
    }



}
