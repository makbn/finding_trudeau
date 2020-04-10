package io.github.makbn.api.post;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Builder
@Data
public class Posts {
    @Builder.Default
    private HashMap<PostType, Set<Post>> posts = new HashMap<>();

    public boolean hasRequestedPosts(PostType filter, int limitation) {
        if (filter == PostType.ALL) {
            return posts.values().stream().anyMatch(Objects::nonNull)
                    && (posts.values().stream().mapToInt(Set::size).sum() >= limitation);
        } else {
            return posts.containsKey(filter) && posts.get(filter).size() >= limitation;
        }
    }

    /**
     * apply filter for cached data
     *
     * @param filter     filter on provider
     * @param limitation max number of posts
     * @return list of posts by providers filtered by <code>limitation</code> and
     * <code>filter</code>
     */
    public Posts applyFilter(PostType filter, Integer limitation) {
        Posts requestedPost = Posts.builder().build();
        HashSet<Post> posts = new HashSet<>();

        for (Post post : this.posts.get(filter)) {
            if (posts.size() < limitation)
                posts.add(post);
            else
                break;
        }
        requestedPost.getPosts().put(filter, posts);
        return requestedPost;
    }
}
