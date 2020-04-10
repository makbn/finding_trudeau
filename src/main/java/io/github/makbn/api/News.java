package io.github.makbn.api;

import java.util.Date;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public class News implements Post {

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public Date getPublishDate() {
        return null;
    }

    @Override
    public PostType getPostType() {
        return PostType.CNN;
    }
}
