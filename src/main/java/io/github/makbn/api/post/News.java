package io.github.makbn.api.post;

import lombok.Builder;
import lombok.Setter;

import java.util.Date;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Builder
@Setter
public class News implements Post {

    private String id;
    private String title;
    private String content;
    private String link;
    private String thumbnail;
    private Date publishDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
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
        return publishDate;
    }

    @Override
    public PostType getPostType() {
        return PostType.CNN;
    }
}
