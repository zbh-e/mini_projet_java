import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class PreprocessorPipeline implements IPreprocessor {

    private final String       name;
    private final List<IPreprocessor> steps;

    public PreprocessorPipeline(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Le nom du pipeline ne doit pas être vide");
        this.name  = name;
        this.steps = new ArrayList<>();
    }

    public PreprocessorPipeline() {
        this("pipeline");
    }

    

    
    public PreprocessorPipeline add(IPreprocessor preprocessor) {
        steps.add(Objects.requireNonNull(preprocessor, "preprocessor must not be null"));
        return this;
    }

    

    @Override
    public Nom process(Nom nom) {
        Objects.requireNonNull(nom, "nom must not be null");
        for (IPreprocessor step : steps) {
            nom = step.process(nom);
        }
        return nom;
    }

    @Override
    public String getName() { return name; }

    public List<IPreprocessor> getSteps() { return List.copyOf(steps); }

    
    public static PreprocessorPipeline standard() {
        return new PreprocessorPipeline("pipeline-standard")
                .add(new NormalizationPreprocessor())
                .add(new SpecialCharPreprocessor())
                .add(new AbbreviationExpanderPreprocessor())
                .add(new PhoneticNormalizationPreprocessor())
                .add(TokenizationPreprocessor.withCommonStopWords());
    }
}
