package eprocument.core.service;

import eprocument.core.domain.RoleEntity;
import eprocument.core.repository.RoleRepository;
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
public class RoleSearchService extends AbstractQueryService {

    private final RoleRepository roleRepository;
    private final JpaFilterSpecification<RoleEntity> searchFilterSpecification;

    public Response<Page<RoleEntity>> search(final FilterCriteria filterCriteria) {
        Specification<RoleEntity> specification = searchFilterSpecification.filterSpecificationBuilder(filterCriteria);
        Pageable pageable = PageRequest.of(filterCriteria.getPageNum(), filterCriteria.getPageSize());

        final Page<RoleEntity> page = roleRepository.findAll(specification, pageable);

        return new Response<>(page, page.getContent().isEmpty()? IUserMessage.INFORMATION_NOT_FOUND: IUserMessage.INFORMATION_FOUND);
    }



}
