import java.util.regex.Pattern;

public final class SpecialCharPreprocessor extends Preprocessor {

    /** Caractères qui seront remplacés par un espace. */
    private static final Pattern REPLACE_BY_SPACE = Pattern.compile("[\\-_/'\"&@#()\\[\\]{}|\\\\]");

    /** Caractères supprimés sans remplacement (ponctuation finale, etc.). */
    private static final Pattern REMOVE_SILENTLY = Pattern.compile("[.,;:!?*%$€£¥]");

    /**
     * Tout caractère autre qu'une lettre ou un chiffre ou un espace (filet final).
     */
    private static final Pattern NON_ALPHANUM = Pattern.compile("[^\\p{L}\\p{N}\\s]");

    private final boolean strictMode;

    /**
     * @param strictMode si {@code true}, tout caractère non alphanumérique
     *                   est supprimé (mode strict) ; sinon seuls les
     *                   caractères explicitement listés sont traités.
     */
    public SpecialCharPreprocessor(boolean strictMode) {
        super("caracteres-speciaux");
        this.strictMode = strictMode;
    }

    public SpecialCharPreprocessor() {
        this(false);
    }

    @Override
    protected Nom doProcess(Nom nom) {
        String value = nom.getNomNormalise();

        if (strictMode) {
            // Remplacer tout ce qui n'est pas lettre / chiffre / espace
            value = NON_ALPHANUM.matcher(value).replaceAll(" ");
        } else {
            // Remplacements ciblés
            value = REPLACE_BY_SPACE.matcher(value).replaceAll(" ");
            value = REMOVE_SILENTLY.matcher(value).replaceAll("");
        }

        // Normalisation finale des espaces
        value = value.replaceAll("\\s+", " ").trim();

        nom.setNomNormalise(value);
        return nom;
    }

    public boolean isStrictMode() {
        return strictMode;
    }
}
