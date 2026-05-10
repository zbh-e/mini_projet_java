import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("=== Test global du programme ===\n");

        List<Nom> noms = new ArrayList<>();
        noms.add(new Nom("1", "Karim Dami"));
        noms.add(new Nom("2", "Osman Dembele"));
        noms.add(new Nom("3", "karim dami"));
        noms.add(new Nom("4", "K. Dami"));
        noms.add(new Nom("5", "Karim Dahmi"));

        List<IPretraiteur> pretraiteurs = List.of(
                new NormalizationPretraiteur(),
                new AbbreviationPretraiteur(),
                new PhoneticNormalizationPretraiteur(),
                new TokenizationPretraiteur());

        System.out.println("-- Prétraitement --");
        Nom sample = new Nom("sample", "Karim Dami");
        System.out.println("Original : " + sample.getNomOriginal());
        for (IPretraiteur pretraiteur : pretraiteurs) {
            sample = pretraiteur.traiter(sample);
            System.out.println("Après " + pretraiteur.getName() + " => normalisé='"
                    + sample.getNomNormaliser() + "', traité=" + sample.getNomTraite());
        }

        System.out.println("\n-- Comparateurs de chaînes --");
        testComparateurChaine("Jaro-Winkler", new ComparateurJaroWinkler(), "Karim Dami", "karim dami");
        testComparateurChaine("Levenshtein", new ComparateurLevenshtein(), "Karim Dami", "karim dami");
        testComparateurChaine("Hamming", new ComparateurHamming(), "Karim Dami", "karim dami");
        testComparateurChaine("Jaccard", new ComparateurJaccard(), "Karim Dami", "karim dami");

        System.out.println("\n-- Comparateur de nom complet --");
        ComparateurNom comparateurNomComplet = new ComparateurDeNomComplet(new ComparateurJaroWinkler());
        afficherScoreNom(comparateurNomComplet, new Nom("A", "Karim Dami"), new Nom("B", "karim dami"));

        System.out.println("\n-- Test MoteurMatching --");
        MoteurMatching moteur = new MoteurMatching(
                pretraiteurs,
                new GenerateurLongeurDeNom(3),
                new SelectionneurParNombre(5),
                new ComparateurExact());

        List<Triplet> resultats = moteur.rechercher(noms, "karim dami");
        for (Triplet triplet : resultats) {
            System.out.println(triplet.getNom1().getNomOriginal()
                    + " <-> " + triplet.getNom2().getNomOriginal()
                    + " => score=" + triplet.getScore());
        }

        System.out.println("\n-- Tous les résultats (SelectionneurTous) --");
        MoteurMatching moteurTous = new MoteurMatching(
                pretraiteurs,
                new GenerateurLongeurDeNom(3),
                new SelectionneurTous(),
                new ComparateurExact());
        List<Triplet> tous = moteurTous.rechercher(noms, "karim dami");
        System.out.println("Nombre de résultats = " + tous.size());

        System.out.println("\n-- Lecture CSV du répertoire data --");
        testLectureCSV();
    }

    private static void testComparateurChaine(String name, ComparateurChaine comparateur, String s1, String s2) {
        double score = comparateur.comparer(s1, s2);
        if (comparateur.getType() == TypeMesure.DISTANCE) {
            System.out.println(name + " (distance) entre '" + s1 + "' et '" + s2 + "' = " + score);
        } else {
            System.out.println(name + " (similarité) entre '" + s1 + "' et '" + s2 + "' = " + score);
        }
    }

    private static void afficherScoreNom(ComparateurNom comparateur, Nom a, Nom b) {
        double score = comparateur.comparer(a, b);
        System.out.println(
                "ComparateurNom entre '" + a.getNomOriginal() + "' et '" + b.getNomOriginal() + "' = " + score);
    }

    private static void testLectureCSV() {
        try {
            System.out.println("Lecture des fichiers CSV du répertoire 'data'...");
            List<Nom> nomsLus = LecteurCSV.lireRepertoireCSV("data");
            System.out.println("Nombre total de noms lus : " + nomsLus.size());

            if (!nomsLus.isEmpty()) {
                System.out.println("Premiers 10 noms :");
                for (int i = 0; i < Math.min(10, nomsLus.size()); i++) {
                    System.out.println("  [" + nomsLus.get(i).getId() + "] " + nomsLus.get(i).getNomOriginal());
                }

                // Test du moteur de matching sur les noms lus
                System.out.println("\n-- Test MoteurMatching avec données CSV --");
                List<IPretraiteur> pretraiteurs = List.of(
                        new NormalizationPretraiteur(),
                        new TokenizationPretraiteur());

                MoteurMatching moteur = new MoteurMatching(
                        pretraiteurs,
                        new GenerateurLongeurDeNom(5),
                        new SelectionneurParNombre(3),
                        new ComparateurExact());

                if (nomsLus.size() > 0) {
                    String nomRecherche = nomsLus.get(0).getNomOriginal();
                    List<Triplet> resultats = moteur.rechercher(nomsLus, nomRecherche);
                    System.out.println("Recherche de '" + nomRecherche + "' : " + resultats.size() + " résultats");
                    for (Triplet t : resultats) {
                        System.out.println("  -> " + t.getNom2().getNomOriginal() + " (score=" + t.getScore() + ")");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des fichiers CSV : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
