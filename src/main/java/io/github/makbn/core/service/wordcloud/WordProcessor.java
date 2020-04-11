package io.github.makbn.core.service.wordcloud;

import io.github.makbn.api.post.Post;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@Named("wordcloud-wordprocessor")
public class WordProcessor {

    private static List<String> stopwords;

    static {
        stopwords = Arrays.asList("i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his",
                "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which",
                "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
                "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with",
                "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out",
                "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both",
                "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t",
                "can", "will", "just", "don", "should");
    }

    static Collection<String> loadStopWords() {
        return stopwords;
    }

    String generateText(Set<Post> tweets) {
        StringBuilder result = new StringBuilder();
        for (Post tweet : tweets) {
            if (!tweet.getContent().startsWith("https://twitter.com"))
                result.append(" ").append(tweet.getContent().toLowerCase().trim());
        }

        return result.toString();
    }

}
