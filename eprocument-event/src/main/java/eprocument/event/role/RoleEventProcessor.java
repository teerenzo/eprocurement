package eprocument.event.role;


import eprocument.common.role.CreateRoleEvent;
import eprocument.core.domain.RoleEntity;
import eprocument.core.service.RoleService;
import ifmis.framework.common.audit.Audit;
import ifmis.framework.core.dto.MessageDto;
import ifmis.framework.core.message.IUserMessage;
import ifmis.framework.core.response.Response;
import ifmis.framework.persistence.service.AbstractEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Audit
@Service
public class RoleEventProcessor extends AbstractEventProcessor {

    private final RoleTransformer roleTransformer;

    private final RoleService roleService;

    public Response<MessageDto> createRole(final CreateRoleEvent event) {
        RoleEntity entity = roleTransformer.transform(event.getDto());
        roleService.createRole(entity);
        return new Response<>(IUserMessage.INFORMATION_SAVED);
    }

//    public Response<RoleEntity> getRoleByUserId(final String userId) {
//        RoleEntity entity = roleService.getRoleByUserId(userId).getResult();
//        return new Response<>(entity, IUserMessage.INFORMATION_FOUND);
//    }
}
