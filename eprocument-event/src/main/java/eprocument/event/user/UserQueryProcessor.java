package eprocument.event.user;

import eprocument.common.user.UserDto;
import eprocument.core.domain.UserEntity;
import eprocument.core.service.UserSearchService;
import ifmis.framework.core.response.Response;
import ifmis.framework.core.search.FilterCriteria;
import ifmis.framework.persistence.service.AbstractQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryProcessor extends AbstractQueryService {

 private final UserSearchService userSearchService;
 private final UserTransformer userTransformer;

    public Response<Page<UserDto>> search(final FilterCriteria filterCriteria) {
        Response<Page<UserEntity>> search = userSearchService.search(filterCriteria);
        return userTransformer.transformPage(search);
    }

}
