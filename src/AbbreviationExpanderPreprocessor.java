import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;


public final class AbbreviationExpanderPreprocessor extends Preprocessor {

    /** Dictionnaire d'abréviations → formes développées. */
    private final Map<String, String> dictionary;

    public AbbreviationExpanderPreprocessor(Map<String, String> dictionary) {
        super("expansion-abreviations");
        Objects.requireNonNull(dictionary, "dictionary must not be null");
        this.dictionary = Collections.unmodifiableMap(new HashMap<>(dictionary));
    }

    /** Constructeur avec dictionnaire par défaut (prénoms, titres, voies). */
    public AbbreviationExpanderPreprocessor() {
        this(buildDefaultDictionary());
    }

    @Override
    protected Nom doProcess(Nom nom) {
        String value = nom.getNomNormalise();

        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            // On remplace le mot entier uniquement (limites de mot \b)
            String regex = "(?i)\\b" + Pattern.quote(entry.getKey()) + "\\.?\\b";
            value = value.replaceAll(regex, entry.getValue());
        }

        // Nettoyage des espaces consécutifs éventuels
        value = value.replaceAll("\\s+", " ").trim();
        nom.setNomNormalise(value);
        return nom;
    }

    public Map<String, String> getDictionary() { return dictionary; }

    

    private static Map<String, String> buildDefaultDictionary() {
        Map<String, String> map = new HashMap<>();

        // Titres de civilité
        map.put("mr",   "monsieur");
        map.put("mme",  "madame");
        map.put("mle",  "mademoiselle");
        map.put("dr",   "docteur");
        map.put("pr",   "professeur");
        map.put("me",   "maitre");

        // Prénoms / prénoms composés courants
        map.put("st",   "saint");
        map.put("ste",  "sainte");

        // Termes géographiques (utiles si le nom contient un lieu)
        map.put("ave",  "avenue");
        map.put("bd",   "boulevard");
        map.put("rte",  "route");

        return map;
    }
}
