package eprocument.common.user;

import eprocument.common.role.RoleDto;
import ifmis.framework.common.dto.AbstractEntityDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class UserDto extends AbstractEntityDto {

    private String firstName;
    private String lastName;
    private String email;
    private String nationalId;
    private String password;
    private List<RoleDto> roles;

}
