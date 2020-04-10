package io.github.makbn.core.service.crawler;

import io.github.makbn.api.post.PostType;
import io.github.makbn.api.post.Posts;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Service
@Named("crawler-service")
public class CrawlerService {

    private static HashMap<PostType, Crawler> crawlersMap = new HashMap<>();

    static {
        crawlersMap.put(PostType.CNN, new CNNCrawler());
        crawlersMap.put(PostType.TWITTER, new TwitterCrawler());
    }

    public Posts getPosts(Date from, Date to, Integer limitation, PostType filter) {
        Posts posts = Posts.builder().build();

        if (Objects.isNull(filter) || filter == PostType.ALL) {

            crawlersMap.values()
                    .forEach(provider -> posts.getPosts()
                            .put(provider.getPostType(), provider.getPosts(from, to, limitation)));
        } else {
            Crawler crawler = crawlersMap.get(filter);
            posts.getPosts().put(filter, crawler.getPosts(from, to, limitation));
        }

        return posts;
    }
}
