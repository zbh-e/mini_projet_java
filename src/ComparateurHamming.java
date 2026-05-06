import org.apache.commons.text.similarity.HammingDistance;

public class ComparateurHamming implements ComparateurChaine {
    private final HammingDistance hamming = new HammingDistance();

    public double comparer(String s1, String s2) {
        if (s1.length() != s2.length())
            return 1.0;
        int dist = hamming.apply(s1, s2);
        return (double) dist / s1.length();
    }

    public TypeMesure getType() {
        return TypeMesure.DISTANCE;
    }
}