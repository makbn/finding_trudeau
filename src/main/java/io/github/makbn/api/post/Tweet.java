package io.github.makbn.api.post;

import lombok.Builder;
import lombok.Setter;

import java.util.Date;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Setter
@Builder
public class Tweet implements Post {
    private String id;
    private String content;
    private String link;
    private Date publishDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        if (content == null || content.isEmpty())
            return "";
        return content.substring(0, Math.min(content.length(), 10));
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public Date getPublishDate() {
        return null;
    }

    @Override
    public PostType getPostType() {
        return PostType.TWITTER;
    }
}
