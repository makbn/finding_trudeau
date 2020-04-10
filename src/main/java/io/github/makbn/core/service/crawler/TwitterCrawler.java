package io.github.makbn.core.service.crawler;

import io.github.makbn.api.Tweet;

import java.util.Date;
import java.util.Set;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class TwitterCrawler implements Crawler<Tweet> {

    @Override
    public Set<Tweet> getPosts(Date from, Date to, Integer limitation) {
        return null;
    }
}
