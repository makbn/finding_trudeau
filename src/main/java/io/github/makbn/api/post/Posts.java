package io.github.makbn.api.post;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Set;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Builder
@Data
public class Posts {
    @Builder.Default
    private HashMap<PostType, Set<Post>> posts = new HashMap<>();
}
