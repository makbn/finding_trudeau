package io.github.makbn.core.service.crawler;

import io.github.makbn.api.News;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class CNNCrawler implements Crawler<News> {

    @Override
    public Set<News> getPosts(Date from, Date to, Integer limitation) {
        return new HashSet<>();
    }
}
