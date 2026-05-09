import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Nom> l1 = new ArrayList<>();
        List<Nom> l2 = new ArrayList<>();
        l1.add(new Nom("Alice", "5"));
        l1.add(new Nom("Bobooo", "2"));
        l2.add(new Nom("Charlie", "2"));
        l2.add(new Nom("rororooooooooo", "6"));
        GenerateurLongeurDeNom t = new GenerateurLongeurDeNom(1);
        List<Candidats> c;
        List<Triplet> resultat = new ArrayList<>();
        resultat.add(new Triplet(new Nom("marjjj", "5"), new Nom("allawi", "5"), 62));
        resultat.add(new Triplet(new Nom("maaaj", "5"), new Nom("allawi", "5"), 655));
        resultat.add(new Triplet(new Nom("maaajj", "5"), new Nom("allawi", "5"), 663));
        resultat.add(new Triplet(new Nom("marjjj", "5"), new Nom("allawi", "5"), 661));

        SelectionneurParNombre s = new SelectionneurParNombre(2);
        List<Triplet> resultatSelectionner;
        resultatSelectionner = s.selectionner(resultat);

        c = t.genererCandidats(l1, l2);
        for (int i = 0; i < resultatSelectionner.size(); i++) {
            System.out.println(resultatSelectionner.get(i).getNom1().getNomOriginal() + "    ,,,    "
                    + resultatSelectionner.get(i).getNom2().getNomOriginal() + "    ,,,   "
                    + resultatSelectionner.get(i).getScore());
        }

    }
}