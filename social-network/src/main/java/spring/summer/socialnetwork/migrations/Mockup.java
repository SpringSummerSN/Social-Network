package spring.summer.socialnetwork.migrations;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;
import spring.summer.socialnetwork.models.Group;
import spring.summer.socialnetwork.models.Post;
import spring.summer.socialnetwork.models.Role;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.GroupRepository;
import spring.summer.socialnetwork.repositories.PostRepository;
import spring.summer.socialnetwork.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Mockup {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final PostRepository postRepository;
    private List<User> userList = new ArrayList<>();

    public void mockData() {
        mockUsers();
        mockGroups();
        mockPosts();
    }

    private void mockUsers() {
        while (userList.size() < 10) {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String password = faker.internet().password(8, 16, true, true, true) + "zZ";
            String email = String.format("%s%s@gmail.com", firstName.toLowerCase(), lastName.toLowerCase());

            if (!userRepository.existsByEmail(email)) {
                User newUser = User.builder()
                        .email(email)
                        .password(password)
                        .name(firstName)
                        .surname(lastName)
                        .roles(Role.USER)
                        .build();
                userRepository.save(newUser);
                userList.add(newUser);
            }
        }
    }

    private void mockGroups() {
        if (userList.size() > 0) {
            Group newGroup1 = Group.builder()
                    .name("group 1")
                    .description("group 1")
                    .group_participant(userList.subList(0, userList.size() / 2))
                    .build();
            Group newGroup2 = Group.builder()
                    .name("group 2")
                    .description("group 2")
                    .group_participant(userList.subList(userList.size() / 2, userList.size() - 2))
                    .build();

            groupRepository.save(newGroup2);
            groupRepository.save(newGroup1);
        }
        Group newGroup3 = Group.builder()
                .name("group 3")
                .description("group 3")
                .build();
        groupRepository.save(newGroup3);
    }

    private void mockPosts() {
        for (User user : userList) {
            Faker faker = new Faker();
            Random random = new Random();
            Post post = Post.builder()
                    .creator(user)
                    .title("post " + user.getName())
                    .description(faker.lorem().sentence(10, 20))
                    .likes(new HashSet<>(userList.subList(0, random.nextInt(userList.size()))))
                    .build();
            postRepository.save(post);
        }
    }
}
