public class ComparateurHamming implements ComparateurChaine {

    public double comparer(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return 1.0;
        }

        int distance = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                distance++;
            }
        }

        return (double) distance / s1.length();
    }

    public TypeMesure getType() {
        return TypeMesure.DISTANCE;
    }
}