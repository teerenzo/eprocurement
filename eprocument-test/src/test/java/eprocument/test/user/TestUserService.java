package eprocument.test.user;

import eprocument.common.user.CreateUserEvent;
import eprocument.common.user.UserDto;
import eprocument.event.user.UserEventProcessor;
import eprocument.test.application.AbstractEProcurementTest;
import ifmis.framework.core.dto.MessageDto;
import ifmis.framework.core.message.IUserMessage;
import ifmis.framework.core.response.Response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestUserService extends AbstractEProcurementTest {


    @Autowired
    private UserEventProcessor userEventProcessor;


    @Test
    void testCreateUser(){
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("test@gmail");
        // userDto.setPassword("0788888888");
        userDto.setNationalId("123456789");

//        Assertions(userDto).isNotNull();

//        CreateUserEvent event = new CreateUserEvent(this, userDto);
//        userEventProcessor.createUser(event).subscribe(response -> {
//            Assertions.assertThat(response.getMessageNumber()).isEqualTo(IUserMessage.INFORMATION_SAVED);
//        });
//        Response<MessageDto> response = userEventProcessor.createUser(event);

//        Assertions.assertThat(response.getMessageNumber()).isEqualTo(IUserMessage.INFORMATION_SAVED);
    }

}
