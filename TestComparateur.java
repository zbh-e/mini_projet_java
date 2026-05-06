public class TestComparateur {
    public static void main(String[] args) {

        String s1 = "Karim";
        String s2 = "Kariem";

        // Hamming
        ComparateurHamming hamming = new ComparateurHamming();
        System.out.println("ComparateurHamming :");
        System.out.println("Resultat: " + hamming.comparer(s1, s2));
        System.out.println("Type: " + hamming.getType());

        // Jaro Winkler
        ComparateurJaroWinkler jw = new ComparateurJaroWinkler();
        System.out.println("\nComparateurJaroWinkler :");
        System.out.println("Resultat: " + jw.comparer(s1, s2));
        System.out.println("Type: " + jw.getType());

        // Jaccard
        ComparateurJaccard jaccard = new ComparateurJaccard();
        System.out.println("\nComparateurJaccard :");
        System.out.println("Resultat: " + jaccard.comparer(s1, s2));
        System.out.println("Type: " + jaccard.getType());

        // Levenshtein
        ComparateurLevenshtein levenshtein = new ComparateurLevenshtein();
        System.out.println("\nComparateurLevenshtein :");
        System.out.println("Resultat: " + levenshtein.comparer(s1, s2));
        System.out.println("Type: " + levenshtein.getType());
    }
}
