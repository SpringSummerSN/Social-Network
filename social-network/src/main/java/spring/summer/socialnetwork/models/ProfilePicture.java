package spring.summer.socialnetwork.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProfilePicture {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @Column(unique = true)
    private String path;

}
