package eprocument.core.domain;

import ifmis.framework.common.search.SearchTags;
import ifmis.framework.persistence.domain.AbstractUpdatableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends AbstractUpdatableEntity {

    private String token;

    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private UserEntity user;

    @Override
    public SearchTags getSearchTags() {
        return null;
    }
}