package spring.summer.socialnetwork.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.summer.socialnetwork.dto.PostDTO;
import spring.summer.socialnetwork.models.Post;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.repositories.PostRepository;
import spring.summer.socialnetwork.repositories.UserRepository;

import java.util.List;

@Service
public class PostService {
    public static final String POST_NOT_FOUND = "Post with id %s was not found.";
    public static final String USER_NOT_FOUND = "User with id %s was not found.";
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException(String.format(POST_NOT_FOUND, postId)));
    }

    public List<Post> getPostsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND, userId)));
        return postRepository.findPostByCreator(user);
    }

    @Transactional
    public Post addPost(PostDTO postDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND, userId)));
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .description(postDTO.getDescription())
                .creator(user)
                .build();
        return postRepository.save(post);
    }

    @Transactional
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public Post updatePostById(Long postId, PostDTO postDTO) {
        return postRepository.findById(postId).map(post -> {
            post.setDescription(postDTO.getDescription());
            post.setTitle(postDTO.getTitle());
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException(String.format(POST_NOT_FOUND, postId)));
    }

    public Post addLike(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND, userId)));
        return postRepository.findById(postId).map(post -> {
            post.addLike(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException(String.format(POST_NOT_FOUND, postId)));
    }

    public Post deleteLike(Long postId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND, userId)));
        return postRepository.findById(postId).map(post -> {
            post.deleteLike(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException(String.format(POST_NOT_FOUND, postId)));
    }
}
