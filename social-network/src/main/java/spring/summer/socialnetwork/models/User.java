package spring.summer.socialnetwork.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.summer.socialnetwork.validator.ValidPassword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends RepresentationModel<User> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Valid
    @Email(message = "Email is not valid", regexp =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b" +
                    "\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)" +
                    "\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
                    "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 150)
    private String name;
    @NotEmpty(message = "Surname cannot be empty")
    @Size(min = 2, max = 150)
    private String surname;

    @Column(nullable = false, name = "password")
    @ValidPassword
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;

    @Column(name = "roles", columnDefinition = "integer default 0")
    private Role roles;


    //////????
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "invitations_users",
            joinColumns = @JoinColumn(name = "user_1_id"),
            inverseJoinColumns = @JoinColumn(name = "user_2_id")
    )
    private List<User> invitations;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "friends_users",
            joinColumns = @JoinColumn(name = "user_1_id"),
            inverseJoinColumns = @JoinColumn(name = "user_2_id")
    )
    private List<User> friends;


    public void add_invitation(User user) {
        invitations.add(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(roles);
            }
        });

        return list;


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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +

                '}';
    }


}

