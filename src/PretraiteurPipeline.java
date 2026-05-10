import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class PretraiteurPipeline implements IPretraiteur {

    private final String       name;
    private final List<IPretraiteur> steps;

    public PretraiteurPipeline(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Le nom du pipeline ne doit pas être vide");
        this.name  = name;
        this.steps = new ArrayList<>();
    }

    public PretraiteurPipeline() {
        this("pipeline");
    }

    

    
    public PretraiteurPipeline add(IPretraiteur Pretraiteur) {
        steps.add(Objects.requireNonNull(Pretraiteur, "Pretraiteur must not be null"));
        return this;
    }

    

    @Override
    public Nom traiter(Nom nom) {
        Objects.requireNonNull(nom, "nom must not be null");
        for (IPretraiteur step : steps) {
            nom = step.traiter(nom);
        }
        return nom;
    }

    @Override
    public String getName() { return name; }

    public List<IPretraiteur> getSteps() { return List.copyOf(steps); }

    
    public static PretraiteurPipeline standard() {
        return new PretraiteurPipeline("pipeline-standard")
                .add(new NormalizationPretraiteur())
                .add(new SpecialCharPretraiteur())
                .add(new AbbreviationPretraiteur())
                .add(new PhoneticNormalizationPretraiteur())
                .add(TokenizationPretraiteur.withCommonStopWords());
    }
}