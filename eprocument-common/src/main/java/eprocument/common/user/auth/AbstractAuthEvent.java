package eprocument.common.user.auth;

import eprocument.common.user.AuthDto;
import ifmis.framework.common.trace.AbstractRequestEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AbstractAuthEvent extends AbstractRequestEvent {

    private final AuthDto dto;

    protected AbstractAuthEvent(final Object source, final AuthDto dto) {
        super(source);
        this.dto = dto;
    }

}
