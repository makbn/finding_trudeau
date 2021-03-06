package io.github.makbn.api.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
public interface Post {
    @JsonProperty
    String getId();

    @JsonProperty
    String getTitle();

    @JsonProperty
    String getContent();

    @JsonProperty
    String getLink();

    @JsonProperty
    Date getPublishDate();

    @JsonIgnore
    PostType getPostType();
}
