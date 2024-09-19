package eprocument.common.role;

import ifmis.framework.common.dto.AbstractEntityDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class RoleDto extends AbstractEntityDto {

        private String name;
        private String description;
}
