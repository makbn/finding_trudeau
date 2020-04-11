package io.github.makbn.core.service.wordcloud;

import io.github.makbn.FindingTrudeauApplication;
import io.github.makbn.api.post.Post;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Named("wordcloud-wordprocessor")
public class WordProcessor {

    static Collection<String> loadStopWords() throws FileNotFoundException {
        HashSet<String> stopwords = new HashSet<>();
        String swDir = FindingTrudeauApplication.class.getClassLoader().getResource("stopwords").getFile();
        File dir = new File(swDir);
        BufferedReader reader;
        for (File swf : dir.listFiles()) {
            reader = new BufferedReader(new FileReader(swf));
            stopwords.addAll(reader.lines()
                    .map(s -> s.trim())
                    .collect(Collectors.toList()));
        }

        return stopwords;
    }

    public String generateText(Set<Post> tweets) {
        StringBuilder result = new StringBuilder();
        for (Post tweet : tweets) {
            if (!tweet.getContent().startsWith("https://twitter.com"))
                result.append(" ").append(tweet.getContent().toLowerCase().trim());
        }

        return result.toString();
    }

}
