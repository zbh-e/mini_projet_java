import java.util.Objects;

public abstract class Pretraiteur implements IPretraiteur {

    private final String name;

    protected Pretraiteur(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom du prétraiteur ne doit pas être vide");
        }
        this.name = name;
    }

    @Override
    public final Nom traiter(Nom nom) {
        Objects.requireNonNull(nom, "nom must not be null");
        System.out.println("[" + getName() + "] traitement de : " + nom.getNomOriginal());

        Nom result = faireTraitement(nom);

        if (result == null) {
            throw new IllegalStateException(
                "[" + getName() + "] faireTraitement() a retourné null pour : " + nom.getNomOriginal());
        }
        return result;
    }

    @Override
    public final String getName() {
        return name;
    }

    protected abstract Nom faireTraitement(Nom nom);

    protected String getNormaliseTrim(Nom nom) {
        return nom.getNomNormaliser().trim();
    }
}