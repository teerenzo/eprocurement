package eprocument.core.domain;

import ifmis.framework.common.audit.Audit;
import ifmis.framework.common.search.SearchTags;
import ifmis.framework.persistence.domain.AbstractUpdatableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@FieldNameConstants
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Audit
@Entity
public class UserRoleEntity extends AbstractUpdatableEntity {

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Override
    public SearchTags getSearchTags() {
        return null;
    }
}
