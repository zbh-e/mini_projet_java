import java.util.ArrayList;
import java.util.List;


public final class PhoneticNormalizationPreprocessor extends Preprocessor {

    
    public record Rule(String pattern, String replacement) {
        public Rule {
            if (pattern == null || pattern.isBlank())
                throw new IllegalArgumentException("pattern must not be blank");
            if (replacement == null)
                throw new IllegalArgumentException("replacement must not be null");
        }
    }

    private final List<Rule> rules;

    public PhoneticNormalizationPreprocessor(List<Rule> rules) {
        super("normalisation-phonetique");
        this.rules = List.copyOf(rules);
    }

    
    public PhoneticNormalizationPreprocessor() {
        this(buildDefaultRules());
    }

    

    @Override
    protected Nom doProcess(Nom nom) {
        String value = nom.getNomNormalise();

        for (Rule rule : rules) {
            value = value.replace(rule.pattern(), rule.replacement());
        }

        value = value.replaceAll("\\s+", " ").trim();
        nom.setNomNormalise(value);
        return nom;
    }

    public List<Rule> getRules() { return rules; }

    

    private static List<Rule> buildDefaultRules() {
        List<Rule> rules = new ArrayList<>();

        // Digrammes consonantiques arabes courants
        rules.add(new Rule("kh", "k"));
        rules.add(new Rule("dh", "d"));
        rules.add(new Rule("th", "t"));
        rules.add(new Rule("gh", "g"));
        rules.add(new Rule("dj", "j"));
        rules.add(new Rule("ch", "c"));

        // Variantes vocaliques français ↔ latin
        rules.add(new Rule("ou", "u"));
        rules.add(new Rule("ei", "i"));
        rules.add(new Rule("aï", "ai"));
        rules.add(new Rule("oï", "oi"));
        rules.add(new Rule("oe", "o"));

        // Lettres doublées → lettre simple
        for (char c : "bcdfghjklmnprstvwxyz".toCharArray()) {
            rules.add(new Rule(String.valueOf(c) + c, String.valueOf(c)));
        }

        return rules;
    }
}
