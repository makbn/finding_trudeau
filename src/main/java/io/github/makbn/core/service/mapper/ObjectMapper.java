package io.github.makbn.core.service.mapper;

import io.github.makbn.api.post.News;
import io.github.makbn.core.ApplicationSettings;
import io.github.makbn.core.model.cnn.CNNObject;
import io.github.makbn.core.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

/**
 * Pojo and Objects Mapping helper
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
@Slf4j
public class ObjectMapper {

    /**
     * convert returned object from CNN search service to {{@link News}}
     *
     * @param result object from CNN search service
     * @return instance of cnn news in form of  {{@link News}}
     */
    public static News map(CNNObject.Result result) {
        try {
            return News.builder()
                    .content(result.getBody().substring(0,
                            Math.min(ApplicationSettings.getBodyMaxLength(), result.getBody().length())) + "...")
                    .link(result.getUrl())
                    .thumbnail(result.getThumbnail())
                    .title(result.getHeadline())
                    .publishDate(DateUtils.convertCNN(result.getLastPublishDate()))
                    .id(result.get_id())
                    .build();
        } catch (ParseException e) {
            log.error(ObjectMapper.class.getSimpleName(), e);
        }

        return null;
    }

}
