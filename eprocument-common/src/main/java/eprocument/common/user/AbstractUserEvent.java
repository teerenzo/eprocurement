package eprocument.common.user;

import ifmis.framework.common.trace.AbstractRequestEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public abstract class AbstractUserEvent extends AbstractRequestEvent {

    private final UserDto dto;

    protected AbstractUserEvent(final Object source, final UserDto dto) {
        super(source);
        this.dto = dto;
    }

}
