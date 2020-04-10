package io.github.makbn.core.service.crawler;

import io.github.makbn.api.post.PostType;
import io.github.makbn.api.post.Tweet;
import io.github.makbn.core.ApplicationSettings;
import io.github.makbn.core.utils.DateUtils;
import me.jhenrique.manager.TweetManager;
import me.jhenrique.manager.TwitterCriteria;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class TwitterCrawler implements Crawler<Tweet> {

    @Override
    public Set<Tweet> getPosts(Date from, Date to, Integer limitation) {

        TwitterCriteria criteria = TwitterCriteria.create()
                .setUsername(ApplicationSettings.getDefaultUsername())
                .setMaxTweets(limitation == null
                        ? ApplicationSettings.getDefaultLimitationNumber() : limitation);

        if (Objects.nonNull(from))
            criteria.setSince(DateUtils.convert(from));
        if (Objects.nonNull(to))
            criteria.setUntil(DateUtils.convert(to));


        return TweetManager.getTweets(criteria).stream()
                .map(t -> Tweet.builder()
                        .content(t.getText())
                        .id(t.getId())
                        .link(t.getPermalink())
                        .publishDate(t.getDate())
                        .build()).collect(Collectors.toSet());
    }

    @Override
    public PostType getPostType() {
        return PostType.TWITTER;
    }
}
