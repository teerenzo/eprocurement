package eprocument.event.role;

import eprocument.common.role.RoleDto;
import eprocument.core.domain.RoleEntity;
import ifmis.framework.persistence.transformer.AbstractEntityTransformer;
import org.springframework.stereotype.Service;

@Service
public class RoleTransformer extends AbstractEntityTransformer<RoleDto, RoleEntity> {

    @Override
    public RoleEntity transform(final RoleDto source) {
        return super.transform(source);
    }

    @Override
    public RoleDto transform(final RoleEntity source) {
        return super.transform(source);
    }
}
