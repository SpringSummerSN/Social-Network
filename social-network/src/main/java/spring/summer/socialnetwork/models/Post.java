package spring.summer.socialnetwork.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "post"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    private List<User> likes;

}
