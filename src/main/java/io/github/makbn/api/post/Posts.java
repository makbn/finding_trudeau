package io.github.makbn.api.post;

import lombok.Builder;
import lombok.Data;

import java.util.*;

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
                    && (posts.values().stream().allMatch(s -> s.size() >= limitation));
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

        if (filter == PostType.ALL) {
            for (Map.Entry<PostType, Set<Post>> entry : this.posts.entrySet()) {
                HashSet<Post> posts = populate(limitation, entry.getValue());
                requestedPost.getPosts().put(entry.getKey(), posts);
            }
        } else {
            Set<Post> cachedPosts = this.posts.get(filter);
            HashSet<Post> posts = populate(limitation, cachedPosts);
            requestedPost.getPosts().put(filter, posts);
        }
        return requestedPost;
    }

    private HashSet<Post> populate(Integer limitation, Set<Post> cachedPosts) {
        HashSet<Post> posts = new HashSet<>();
        for (Post post : cachedPosts) {
            if (posts.size() < limitation)
                posts.add(post);
            else
                break;
        }

        return posts;
    }
}
