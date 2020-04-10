package io.github.makbn.resources;

import com.codahale.metrics.annotation.Timed;
import io.github.makbn.api.Post;
import io.github.makbn.api.PostType;
import io.github.makbn.api.Response;
import io.github.makbn.core.ApplicationSettings;
import io.github.makbn.core.exception.InvalidRequestException;
import io.github.makbn.core.service.crawler.CrawlerService;
import io.github.makbn.core.utils.DateUtils;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostsResource {
    private CrawlerService crawlerService;

    @Inject
    public PostsResource(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    private static void checkLimitation(Integer limitation) {
        if (limitation < 1 || limitation > 100)
            throw InvalidRequestException.builder()
                    .message("limitation number is not valid!")
                    .build();
    }

    private static Date mapDate(String date) {
        try {
            return DateUtils.convert(date);
        } catch (ParseException e) {
            throw InvalidRequestException.builder()
                    .message("from date format is invalid!")
                    .build();
        }
    }

    /**
     * @param type filter by type, valid values are
     * @return all posts from both providers-twitter and CNN
     */
    @GET
    @Timed
    public Response<Set<Post>> getAllPosts(@QueryParam("type") Optional<String> type,
                                           @QueryParam("from") Optional<String> from,
                                           @QueryParam("to") Optional<String> to,
                                           @QueryParam("limitation") Optional<Integer> limitation) {

        limitation.ifPresent(PostsResource::checkLimitation);
        type.ifPresent(PostType::checkTypeName);

        Optional<Date> fromDate = from.map(PostsResource::mapDate);
        Optional<Date> toDate = from.map(PostsResource::mapDate);

        crawlerService.getPosts(fromDate.orElse(DateUtils.getPreviousYear()),
                toDate.orElse(DateUtils.getCurrentDate()),
                limitation.orElse(ApplicationSettings.getDefaultLimitationNumber()),
                PostType.valueOf(type.get()));
        return null;
    }
}
