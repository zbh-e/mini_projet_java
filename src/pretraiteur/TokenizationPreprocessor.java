package pretraiteur;

import modeles.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class TokenizationPreprocessor extends Preprocessor {

    private static final Pattern DEFAULT_DELIMITER = Pattern.compile("[\\s\\-\\.'']+");

    private final Pattern delimiter;
    private final Set<String> stopWords;

    public TokenizationPreprocessor(Pattern delimiter, Set<String> stopWords) {
        super("tokenization");
        this.delimiter = delimiter != null ? delimiter : DEFAULT_DELIMITER;
        this.stopWords = stopWords != null
                ? Collections.unmodifiableSet(
                        stopWords.stream()
                                .map(String::toLowerCase)
                                .collect(Collectors.toSet()))
                : Set.of();
    }

    public TokenizationPreprocessor() {
        this(DEFAULT_DELIMITER, Set.of());
    }

    public static TokenizationPreprocessor withCommonStopWords() {
        Set<String> sw = new HashSet<>(
                Arrays.asList("ben", "bel", "el", "al", "de", "du", "le", "la",
                        "les", "dit", "dit", "dit", "ou", "et"));
        return new TokenizationPreprocessor(DEFAULT_DELIMITER, sw);
    }

    @Override
    protected Name doProcess(Name name) {
        String normalized = name.getNormalized();

        List<String> tokens = Arrays.stream(delimiter.split(normalized))
                .map(String::trim)
                .filter(t -> !t.isEmpty())
                .filter(t -> !stopWords.contains(t.toLowerCase()))
                .collect(Collectors.toUnmodifiableList());

        return name.withTokens(tokens);
    }

    public Pattern getDelimiter() {
        return delimiter;
    }

    public Set<String> getStopWords() {
        return stopWords;
    }
}
