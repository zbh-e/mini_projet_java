import java.text.Normalizer;
import java.util.Objects;
import java.util.List;

public final class NormalizationPretraiteur extends Pretraiteur {

    public NormalizationPretraiteur() {
        super("normalisation");
    }

    @Override
    protected Nom faireTraitement(Nom nom) {
        String value = nom.getNomNormaliser();

        // 1. Minuscules
        value = value.toLowerCase(java.util.Locale.ROOT);
        // 2. Suppression des accents (décomposition NFD + suppression des marques)
        value = Normalizer.normalize(value, Normalizer.Form.NFD);
        value = value.replaceAll("\\p{InCombiningDiacriticalMarks}", "");
        // 3. Normalisation des espaces
        value = value.replaceAll("\\s+", " ").trim();

        nom.setNomNormaliser(value);
        nom.setNomTraiter(List.of(value));
        return nom;
    }

}