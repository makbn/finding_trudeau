package io.github.makbn.core.service.crawler;

import io.github.makbn.api.post.News;
import io.github.makbn.api.post.PostType;
import io.github.makbn.core.model.cnn.CNNObject;
import io.github.makbn.core.service.mapper.ObjectMapper;
import io.github.makbn.core.utils.DateUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Retrieving data from {{@link PostType#CNN}} provider
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Slf4j
public class CNNCrawler implements Crawler<News> {

    @Override
    public Set<News> getPosts(Date from, Date to, Integer limitation) {
        Set<News> news = crawlForKeywords(new String[]{"Trudeau"}, from, to, limitation);
        return news;
    }

    /**
     * @param keywords   searching criteria, an array of string for querying in the articles
     * @param from       since date
     * @param to         until date
     * @param limitation max number of returned news
     * @return list of news that are retrieved from CNN search service
     */
    private Set<News> crawlForKeywords(String[] keywords, Date from, Date to, Integer limitation) {

        int size = 10;
        int page = 1;
        int offset = 0;

        Set<News> news = new HashSet<>();
        while (news.size() <= limitation) {
            for (String kw : keywords) {
                HttpResponse<CNNObject> response = Unirest.get(PostType.CNN.getHost())
                        .queryString("size", size)
                        .queryString("q", kw)
                        .queryString("from", offset)
                        .queryString("page", page)
                        .asObject(CNNObject.class);

                if (response.isSuccess()) {
                    response.getBody().getResult().stream()
                            .filter(article -> validateArticle(article, keywords, from, to))
                            .forEach(article -> {
                                if (news.size() <= limitation)
                                    news.add(ObjectMapper.map(article));
                            });
                }
            }

            page++;
            offset += size;
        }

        return news;
    }

    /**
     * check if the article is valid for returning as a result
     * @param article from cnn search service
     * @param keywords searching criteria
     * @param from since date
     * @param to until date
     * @return is a valid choice or not
     */
    private boolean validateArticle(CNNObject.Result article, String[] keywords, Date from, Date to) {
        boolean findKeyword = false;
        for (String kw : keywords) {
            if (article.getBody().contains(kw)) {
                findKeyword = true;
                break;
            }
        }

        try {
            Date publishDate = DateUtils.convertCNN(article.getLastPublishDate());
            if (publishDate != null && (publishDate.before(from) || publishDate.after(to)))
                return false;
        } catch (ParseException e) {
            log.error(getClass().getSimpleName(), e);
        }

        return findKeyword;
    }

    @Override
    public PostType getPostType() {
        return PostType.CNN;
    }
}
