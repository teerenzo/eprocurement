package eprocument.common.user.auth;

import eprocument.common.user.AuthDto;

public class AuthEvent extends AbstractAuthEvent {

    public AuthEvent(Object source, AuthDto dto) {
        super(source, dto);
    }
}
