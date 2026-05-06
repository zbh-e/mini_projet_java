import org.apache.commons.text.similarity.JaccardSimilarity;

public class ComparateurJaccard implements ComparateurChaine {
    private final JaccardSimilarity jaccard = new JaccardSimilarity();

    public double comparer(String s1, String s2) {
        return jaccard.apply(s1, s2);
    }

    @Override
    public TypeMesure getType() {
        return TypeMesure.SIMILARITE;
    }
}
