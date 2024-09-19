package eprocument.common.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
public class CreateUserEvent extends  AbstractUserEvent {

    public CreateUserEvent(Object source, UserDto dto) {
        super(source, dto);
    }
}
