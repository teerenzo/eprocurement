package eprocument.core.service;

import eprocument.core.domain.UserEntity;
import eprocument.core.repository.UserRepository;
import ifmis.framework.core.message.IUserMessage;
import ifmis.framework.core.response.Response;
import ifmis.framework.core.search.FilterCriteria;
import ifmis.framework.persistence.search.JpaFilterSpecification;
import ifmis.framework.persistence.service.AbstractQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSearchService extends AbstractQueryService {

    private final UserRepository userRepository;
    private final JpaFilterSpecification<UserEntity> searchFilterSpecification;

    public Response<Page<UserEntity>> search(final FilterCriteria filterCriteria) {
        Specification<UserEntity> specification = searchFilterSpecification.filterSpecificationBuilder(filterCriteria);
        Pageable pageable = PageRequest.of(filterCriteria.getPageNum(), filterCriteria.getPageSize());

        final Page<UserEntity> page = userRepository.findAll(specification, pageable);
        
        return new Response<>(page, page.getContent().isEmpty()? IUserMessage.INFORMATION_NOT_FOUND: IUserMessage.INFORMATION_FOUND);

    }



}
