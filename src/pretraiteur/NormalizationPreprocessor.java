import java.text.Normalizer;
import java.util.Locale;
import java.util.Objects;

public final class NormalizationPreprocessor extends Preprocessor {

    private final Locale  locale;
    private final boolean removeAccents;

    public NormalizationPreprocessor(Locale locale, boolean removeAccents) {
        super("normalization");
        this.locale        = Objects.requireNonNull(locale, "locale must not be null");
        this.removeAccents = removeAccents;
    }

    public NormalizationPreprocessor() {
        this(Locale.ROOT, true);
    }

    @Override
    protected Name doProcess(Name name) {
        String value = name.getNormalized();

        value = value.toLowerCase(locale);
        
        if (removeAccents) {
            value = Normalizer.normalize(value, Normalizer.Form.NFD);
            value = value.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
        }
        
        value = value.replaceAll("\\s+", " ").trim();

        return name.withNormalized(value);
    }

    public Locale  getLocale()        { return locale; }
    public boolean isRemoveAccents()  { return removeAccents; }
}
