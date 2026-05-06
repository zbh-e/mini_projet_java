import java.text.Normalizer;
import java.util.Locale;
import java.util.Objects;


public final class NormalizationPreprocessor extends Preprocessor {

    private final Locale  locale;
    private final boolean removeAccents;

    public NormalizationPreprocessor(Locale locale, boolean removeAccents) {
        super("normalisation");
        this.locale        = Objects.requireNonNull(locale, "locale must not be null");
        this.removeAccents = removeAccents;
    }

   
    public NormalizationPreprocessor() {
        this(Locale.ROOT, true);
    }

    @Override
    protected Nom doProcess(Nom nom) {
        String value = nom.getNomNormalise();

        // 1. Minuscules
        value = value.toLowerCase(locale);

        // 2. Suppression des accents (décomposition NFD + suppression des marques)
        if (removeAccents) {
            value = Normalizer.normalize(value, Normalizer.Form.NFD);
            value = value.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
        }

        // 3. Normalisation des espaces
        value = value.replaceAll("\\s+", " ").trim();

        nom.setNomNormalise(value);
        return nom;
    }

    public Locale  getLocale()       { return locale; }
    public boolean isRemoveAccents() { return removeAccents; }
}