public class TestComparaterur2 {
    public static void main(String[] args) {
        Nom n1 = new Nom("karim dami", "1");
        Nom n2 = new Nom("karrm Dami", "2");
        Nom n3 = new Nom("osman dembele", "3");

        ComparateurChaine leveshtein = new ComparateurLevenshtein();

        ComparateurDeNomComplet comparateur = new ComparateurDeNomComplet(leveshtein);

        double score1 = comparateur.comparer(n1, n2);
        double score2 = comparateur.comparer(n1, n3);

        System.out.println(n1.getNomOriginal() + " vs " + n2.getNomOriginal() + " : " + score1);

        System.out.println(n1.getNomOriginal() + " vs " + n3.getNomOriginal() + " : " + score2);

    }
}
