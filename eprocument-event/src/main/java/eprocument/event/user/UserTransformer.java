package eprocument.event.user;

import eprocument.common.user.UserDto;
import eprocument.core.domain.UserEntity;
import ifmis.framework.persistence.transformer.AbstractEntityTransformer;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer extends AbstractEntityTransformer<UserDto, UserEntity> {

        @Override
        public UserEntity transform(final UserDto source) {
                return super.transform(source);
        }

        @Override
        public UserDto transform(final UserEntity source) {
                return super.transform(source);
        }

}
