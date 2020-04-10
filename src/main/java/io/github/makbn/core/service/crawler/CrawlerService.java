package io.github.makbn.core.service.crawler;

import io.github.makbn.api.post.PostType;
import io.github.makbn.api.post.Posts;
import io.github.makbn.core.ApplicationSettings;
import io.github.makbn.core.utils.DateUtils;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Service
@Named("crawler-service")
public class CrawlerService extends TimerTask {

    /*supported providers*/
    private static ConcurrentHashMap<PostType, Crawler> crawlersMap = new ConcurrentHashMap<>();

    static {
        /*additional providers can be added by implementing from Crawler interface*/
        crawlersMap.put(PostType.CNN, new CNNCrawler());
        crawlersMap.put(PostType.TWITTER, new TwitterCrawler());
    }

    private Timer fetchPostsTimer;
    private Posts posts;

    public CrawlerService() {
        this.fetchPostsTimer = new Timer();
        this.fetchPostsTimer.scheduleAtFixedRate(this,
                ApplicationSettings.getFetchTimerDelay(),
                ApplicationSettings.getFetchTimerInterval());
    }

    /**
     * if posts are available by previous fetch job execution return from cache
     *
     * @param from       since date
     * @param to         untile date
     * @param limitation max number of returned results
     * @param filter     choosing result between providers by {{@link PostType}}
     * @return retrieve news and posts from supported providers
     */
    public Posts getPosts(Date from, Date to, Integer limitation, PostType filter) {
        if (posts != null && posts.hasRequestedPosts(filter, limitation)) {
            return posts.applyFilter(filter, limitation);
        } else {
            posts = fetchPosts(from, to, limitation, filter);
        }
        return posts;
    }

    private Posts fetchPosts(Date from, Date to, Integer limitation, PostType filter) {
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

    @Override
    public void run() {
        posts = fetchPosts(DateUtils.getPreviousYear(), DateUtils.getCurrentDate(),
                ApplicationSettings.getDefaultLimitationNumber(), PostType.ALL);
    }
}
