import java.util.ArrayList;
import java.util.List;

public class TestMoteurMatching {
    public static void main(String[] args) {

        // Create preprocessors
        List<IPretraiteur> pretraiteurs = new ArrayList<>();
        pretraiteurs.add(new PretraiteurPipeline()); // or your own preprocessor

        // Other components
        GenerateurDesCandidats generateur = new GenerateurLongeurDeNom(3);
        Selectionneur selectionneur = new SelectionneurParNombre(5);
        ComparateurNom comparateur = new ComparateurExact();

        // Create engine
        MoteurMatching moteur = new MoteurMatching(
                pretraiteurs,
                generateur,
                selectionneur,
                comparateur);

        // Test data
        List<Nom> noms = new ArrayList<>();
        noms.add(new Nom("1", "Karim Dami"));
        noms.add(new Nom("2", "Osman Dembele"));
        noms.add(new Nom("3", "karim dami"));

        // Search
        List<Triplet> resultats = moteur.rechercher(noms, "Karim Dami");

        // Display results
        for (Triplet t : resultats) {
            System.out.println(
                    t.getNom1().getNomOriginal()
                            + " <-> "
                            + t.getNom2().getNomOriginal()
                            + " : "
                            + t.getScore());
        }
    }
}