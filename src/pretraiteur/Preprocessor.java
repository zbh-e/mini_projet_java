package pretraiteur;

import modeles.*;

import java.util.Objects;
import java.util.logging.Logger;

public abstract class Preprocessor implements IPreprocessor {

    protected final Logger logger = Logger.getLogger(getClass().getName());

    private final String name;

    protected Preprocessor(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Preprocessor name must not be blank");
        }
        this.name = name;
    }

    @Override
    public final Name process(Name name) {
        Objects.requireNonNull(name, "name must not be null");
        logger.fine(() -> "[" + getName() + "] processing: " + name.getOriginal());

        Name result = doProcess(name);

        if (result == null) {
            throw new IllegalStateException(
                    "[" + getName() + "] doProcess() returned null for: " + name.getOriginal());
        }
        return result;
    }

    @Override
    public final String getName() {
        return name;
    }

    protected abstract Name doProcess(Name name);

    protected String trimNormalized(Name name) {
        return name.getNormalized().trim();
    }
}
