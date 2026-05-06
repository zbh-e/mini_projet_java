import org.apache.commons.text.similarity.LevenshteinDistance;

public class ComparateurLevenshtein implements ComparateurChaine {

    private final LevenshteinDistance levenshtein = new LevenshteinDistance(100); // seuil large

    public double comparer(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 1.0;

        int maxLength = Math.max(s1.length(), s2.length());
        if (maxLength == 0)
            return 0.0;
        Integer rawDistance = levenshtein.apply(s1, s2);
        if (rawDistance == -1)
            return 1.0;
        return (double) rawDistance / maxLength;
    }

    public TypeMesure getType() {
        return TypeMesure.DISTANCE;
    }
}