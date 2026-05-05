public class TestComparateur {

    public static void main(String[] args) {

        System.out.println("njareb fi comparateur 3la deux nom");

        Nom n1 = new Nom("karim dami", "karim dami", "dvz");
        Nom n2 = new Nom("mohamed amin rchid", "rchid mohamed amin", "gbzeze");
        Nom n3 = new Nom("zied ben hamouda ", "Zeid ben", "zevrz");
        Nom n4 = new Nom(" karim damdam ", "Karim Damdam", "rebz");
        Nom n5 = new Nom(" karim damdam ", "Karim Damdam", "bzml");

        Comparateur exact = new ComparateurExact();
        Comparateur distance = new ComparateurDistance();
        Comparateur similitude = new ComparateurSimilitude();

        double seuil = 0.8;

        System.out.println("Test louwel :  ");
        tester(n3, n2, exact, distance, similitude, seuil);

        System.out.println("\n Test theni : ");
        tester(n4, n3, exact, distance, similitude, seuil);

        System.out.println("\nTest theleth : ");
        tester(n2, n5, exact, distance, similitude, seuil);
    }

    private static void tester(Nom a, Nom b, Comparateur exact, Comparateur distance, Comparateur similitude,
            double seuil) {

        double scoreExact = exact.comparer(a, b);
        double scoreDistance = distance.comparer(a, b);
        double scoreSimilitude = similitude.comparer(a, b);

        System.out.println("Nom 1: " + a.getNomOriginal());
        System.out.println("Nom 2: " + b.getNomOriginal());

        System.out.println("Exact       = " + scoreExact + (scoreExact >= seuil ? " houwa " : " mouch houwa "));

        System.out.println("Distance    = " + scoreDistance + (scoreDistance >= seuil ? " houwa " : " mouch houwa"));

        System.out
                .println("Similitude  = " + scoreSimilitude + (scoreSimilitude >= seuil ? " houwa " : " mouch houwa"));
    }
}