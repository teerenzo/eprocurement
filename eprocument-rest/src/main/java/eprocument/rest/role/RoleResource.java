package eprocument.rest.role;

import eprocument.common.role.CreateRoleEvent;
import eprocument.common.role.RoleDto;
import eprocument.core.domain.RoleEntity;
import eprocument.event.role.RoleEventProcessor;
import ifmis.framework.core.dto.MessageDto;
import ifmis.framework.core.response.Response;
import ifmis.framework.rest.resource.AbstractControllerResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
public class RoleResource extends AbstractControllerResource {

    private final RoleEventProcessor roleEventProcessor;

    // create event
    @PostMapping("/createRole")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Response<MessageDto> createRole(@RequestBody RoleDto dto) {
        CreateRoleEvent event = new CreateRoleEvent(this, dto);
        return roleEventProcessor.createRole(event);
    }

    // role by user id
//    @GetMapping("/roleByUserId/{userId}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public Response<RoleEntity> getRoleByUserId(@PathVariable String userId) {
//        return roleEventProcessor.getRoleByUserId(userId);
//    }

}
