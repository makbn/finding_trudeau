package io.github.makbn.core.service.wordcloud;


import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import io.github.makbn.api.post.Post;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Named("wordcloud-service")
public class WordCloudService {

    @Inject
    private WordProcessor wordProcessor;

    public File generateTWC(Set<Post> tweets) throws IOException {
        String text = wordProcessor.generateText(tweets);
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(250);
        frequencyAnalyzer.setMinWordLength(3);
        frequencyAnalyzer.setStopWords(WordProcessor.loadStopWords());
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(Collections.singletonList(text));

        final Dimension dimension = new Dimension(600, 600);
        final com.kennycason.kumo.WordCloud wordCloud = new com.kennycason.kumo.WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(5);

        wordCloud.setBackground(new CircleBackground(300));

        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.decode("#4978BC"), Color.decode("#002E5E"), Color.decode("#001D38"), 15, 20));
        wordCloud.setBackgroundColor(Color.decode("#FFCC00"));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 55));
        wordCloud.build(wordFrequencies);

        File generatedImage = File.createTempFile("jt-wordcloud", ".png");
        wordCloud.writeToFile(generatedImage.getAbsolutePath());

        return generatedImage;
    }

}
