package eprocument.core.domain;

import ifmis.framework.common.audit.Audit;
import ifmis.framework.common.search.SearchTags;
import ifmis.framework.persistence.domain.AbstractUpdatableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@FieldNameConstants
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@Audit
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "national_id" }) })
@Builder
@AllArgsConstructor
public class UserEntity extends AbstractUpdatableEntity implements UserDetails, Principal {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "national_id", nullable = false)
    private String nationalId;

    private boolean accountLocked;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    @Override
    public SearchTags getSearchTags() {
        return new SearchTags(
                this
        ).addTag(Fields.firstName, firstName)
                .addTag(Fields.lastName, lastName)
                .addTag(Fields.email, email)
                .addTag(Fields.nationalId, nationalId);

    }

    @Override
    public String getName() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;
    }
}
