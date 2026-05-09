import java.util.HashSet;
import java.util.Set;

public class ComparateurJaccard implements ComparateurChaine {

    @Override
    public double comparer(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : s1.toCharArray()) {
            set1.add(c);
        }

        for (char c : s2.toCharArray()) {
            set2.add(c);
        }

        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);

        if (union.size() == 0) {
            return 1.0;
        }

        return (double) intersection.size() / union.size();
    }

    @Override
    public TypeMesure getType() {
        return TypeMesure.SIMILARITE;
    }
}