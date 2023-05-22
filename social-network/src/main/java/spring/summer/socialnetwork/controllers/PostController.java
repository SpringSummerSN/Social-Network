package spring.summer.socialnetwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.summer.socialnetwork.dto.PostDTO;
import spring.summer.socialnetwork.models.Post;
import spring.summer.socialnetwork.models.User;
import spring.summer.socialnetwork.services.PostService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getAllUserPosts(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> addPost(@RequestBody PostDTO postDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        Post addedPost = postService.addPost(postDTO, userId);
        return ResponseEntity.created(getAddedPostUri(addedPost)).body(addedPost);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable Long postId) {
        postService.deletePostById(postId);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        Post updated = postService.updatePostById(postId, postDTO);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<Post> likePost(@PathVariable Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        Post post = postService.addLike(postId, userId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}/unlike")
    public ResponseEntity<Post> unlikePost(@PathVariable Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        Post post = postService.deleteLike(postId, userId);
        return ResponseEntity.ok(post);
    }

    private static URI getAddedPostUri(Post post) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/" + post.getId())
                .build()
                .toUri();
    }
}
