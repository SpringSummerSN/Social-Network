package spring.summer.socialnetwork.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.summer.socialnetwork.exceptions.TooWeakPassword;
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
    @Email(message = "Email is not valid")
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
    @ValidPassword(message = "too weak password")
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imageData_id", referencedColumnName = "id")
    private ImageData image;


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

    public void addToFriends(User user){
        friends.add(user);
    }


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

