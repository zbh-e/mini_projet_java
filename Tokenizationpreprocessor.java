import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public final class TokenizationPreprocessor extends Preprocessor {

    private static final Pattern DEFAULT_DELIMITER =
            Pattern.compile("[\\s\\-\\.'']+");

    private final Pattern     delimiter;
    private final Set<String> stopWords;

    public TokenizationPreprocessor(Pattern delimiter, Set<String> stopWords) {
        super("tokenisation");
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
                              "les", "dit", "ou", "et"));
        return new TokenizationPreprocessor(DEFAULT_DELIMITER, sw);
    }

    @Override
    protected Nom doProcess(Nom nom) {
        String normalise = nom.getNomNormalise();

        List<String> tokens = Arrays.stream(delimiter.split(normalise))
                .map(String::trim)
                .filter(t -> !t.isEmpty())
                .filter(t -> !stopWords.contains(t.toLowerCase()))
                .collect(Collectors.toUnmodifiableList());

        nom.setNomTraite(tokens);
        return nom;
    }

    public Pattern     getDelimiter() { return delimiter; }
    public Set<String> getStopWords() { return stopWords; }
}