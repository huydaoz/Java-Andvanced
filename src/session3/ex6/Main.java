package session3.ex6;
import java.util.*;
import java.util.stream.Collectors;

class Post {
    private List<String> tags;
    public Post(List<String> tags) {
        this.tags = tags;
    }
    public List<String> getTags() {
        return tags;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Post> posts = List.of(
                new Post(List.of("java", "backend")),
                new Post(List.of("python", "data"))
        );
        List<String> allTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .collect(Collectors.toList());

        System.out.println(allTags);
    }
}