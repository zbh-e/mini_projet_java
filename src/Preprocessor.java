import java.util.Objects;
import java.util.logging.Logger;


public abstract class Preprocessor implements IPreprocessor {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    private final String name;

    protected Preprocessor(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom du préprocesseur ne doit pas être vide");
        }
        this.name = name;
    }

    @Override
    public final Nom process(Nom nom) {
        Objects.requireNonNull(nom, "nom must not be null");
        logger.fine(() -> "[" + getName() + "] traitement de : " + nom.getNomOriginal());

        Nom result = doProcess(nom);

        if (result == null) {
            throw new IllegalStateException(
                "[" + getName() + "] doProcess() a retourné null pour : " + nom.getNomOriginal());
        }
        return result;
    }

    @Override
    public final String getName() {
        return name;
    }

    
    
    protected abstract Nom doProcess(Nom nom);

    
    protected String getNormaliseTrim(Nom nom) {
        return nom.getNomNormalise().trim();
    }
}