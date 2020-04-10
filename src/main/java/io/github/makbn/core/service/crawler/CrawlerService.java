package io.github.makbn.core.service.crawler;

import io.github.makbn.api.Post;
import io.github.makbn.api.PostType;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;
import java.util.*;

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

    public Set<Post> getPosts(Date from, Date to, Integer limitation, PostType filter) {

        if (Objects.nonNull(filter)) {
            Crawler crawler = crawlersMap.get(filter);
            return crawler.getPosts(from, to, limitation);
        } else {
            Set<Post> posts = new HashSet<>();
            crawlersMap.values()
                    .stream()
                    .forEach(provider -> posts.addAll(provider.getPosts(from, to, limitation)));

            return posts;
        }
    }
}
