package eprocument.common.user;

import ifmis.framework.common.dto.AbstractEntityDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto extends AbstractEntityDto {
    private String email;
    private String password;
}
