package eprocument.core.repository;

import eprocument.core.domain.UserEntity;
import ifmis.framework.persistence.repository.JpaBaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaBaseRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {

    Optional<UserEntity> findByEmail(String email);

        UserEntity findByNationalId(String nationalId);

        boolean existsByEmail(String email);

        boolean existsByNationalId(String nationalId);

}
