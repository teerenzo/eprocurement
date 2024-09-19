package eprocument.core.repository;

import eprocument.core.domain.RoleEntity;
import ifmis.framework.persistence.repository.JpaBaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RoleRepository extends JpaBaseRepository<RoleEntity, UUID>, JpaSpecificationExecutor<RoleEntity> {

//        RoleEntity findByUserId(String userId);
}
