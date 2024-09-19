package eprocument.event.user;

import eprocument.common.user.AuthDto;
import eprocument.core.domain.UserEntity;
import ifmis.framework.persistence.transformer.AbstractEntityTransformer;
import org.springframework.stereotype.Service;

@Service
public class AuthTransformer extends AbstractEntityTransformer<AuthDto, UserEntity> {

        @Override
        public UserEntity transform(final AuthDto source) {
                return super.transform(source);
        }

        @Override
        public AuthDto transform(final UserEntity source) {
                return super.transform(source);
        }
}
