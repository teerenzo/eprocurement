package eprocument.core.repository;

import eprocument.core.domain.Token;
import ifmis.framework.persistence.repository.JpaBaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaBaseRepository<Token, UUID> {
     Optional<Token> findByToken(String token);

}
