package eprocument.core.service;

import eprocument.core.domain.RoleEntity;
import eprocument.core.repository.RoleRepository;
import ifmis.framework.common.audit.Audit;
import ifmis.framework.core.response.Response;
import ifmis.framework.persistence.service.AbstractModifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Audit
@RequiredArgsConstructor
@Service
public class RoleService extends AbstractModifyService {

     private final RoleRepository roleRepository;

     public Response<RoleEntity> createRole(final RoleEntity entity) {
         return new Response<>(roleRepository.create(entity));
     }

//        public Response<RoleEntity> getRoleByUserId(final String userId) {
//            return new Response<>(roleRepository.findByUserId(userId));
//        }
}
