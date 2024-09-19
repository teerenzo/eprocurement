package eprocument.rest.user;

import eprocument.common.user.AuthDto;
import eprocument.common.user.CreateUserEvent;
import eprocument.common.user.TokenDto;
import eprocument.common.user.UserDto;
import eprocument.common.user.auth.AuthEvent;
import eprocument.core.service.AuthenticationResponse;
import eprocument.event.user.UserQueryProcessor;
import eprocument.event.user.UserEventProcessor;
import ifmis.framework.core.dto.MessageDto;
import ifmis.framework.core.response.Response;
import ifmis.framework.core.search.FilterCriteria;
import ifmis.framework.rest.resource.AbstractControllerResource;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserResource  extends AbstractControllerResource {

    private final UserEventProcessor userEventProcessor;
    private final UserQueryProcessor personQueryProcessor;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
   public Response<MessageDto> createUser(@RequestBody UserDto dto) throws MessagingException {
        CreateUserEvent event = new CreateUserEvent(this, dto);
        System.out.println("User created"+event.toString());

          return userEventProcessor.createUser(event);
   }

   // verify user
    @PostMapping("/activate")
    @ResponseStatus(code = HttpStatus.OK)
    public Response<MessageDto> verifyUser(@RequestBody TokenDto token) throws MessagingException {
        // request print
        System.out.println("User verified"+token);
        return userEventProcessor.verifyUser(token.getToken());
    }

   // login user
    @PostMapping("/auth")
    @ResponseStatus(code = HttpStatus.OK)
    public Response<AuthenticationResponse> loginUser(@RequestBody AuthDto dto) {
        AuthEvent event = new AuthEvent(this, dto);
        return userEventProcessor.loginUser(event);
    }

    //


   // search users
    @PostMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public Response<Page<UserDto>> search(@RequestBody FilterCriteria filterCriteria) {
        return personQueryProcessor.search(filterCriteria);
    }

}
