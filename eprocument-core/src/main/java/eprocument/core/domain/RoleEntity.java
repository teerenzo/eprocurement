package eprocument.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ifmis.framework.common.audit.Audit;
import ifmis.framework.common.search.SearchTags;
import ifmis.framework.persistence.domain.AbstractUpdatableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Getter
@Setter
@FieldNameConstants
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Audit
@Entity
@Table(name = "roles")
public class RoleEntity extends AbstractUpdatableEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserEntity> users;

    @Override
    public SearchTags getSearchTags() {
        return new SearchTags(this)
                .addTag(Fields.name, name)
                .addTag(Fields.description, description);
    }
}
