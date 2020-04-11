package io.github.makbn.core;

import io.github.makbn.core.service.crawler.CrawlerService;
import io.github.makbn.core.service.wordcloud.WordCloudService;
import io.github.makbn.core.service.wordcloud.WordProcessor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/
@Slf4j
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        log.debug("ApplicationBinder configuration started!");
        bind(CrawlerService.class).to(CrawlerService.class).in(Singleton.class);
        bind(WordCloudService.class).to(WordCloudService.class).in(Singleton.class);
        bind(WordProcessor.class).to(WordProcessor.class).in(Singleton.class);
    }
}
