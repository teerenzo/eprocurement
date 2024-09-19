package eprocument.common.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
public class CreateRoleEvent extends AbstractRoleEvent {

    public CreateRoleEvent(Object source, RoleDto dto) {
        super(source, dto);
    }
}
