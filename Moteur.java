import java.util.ArrayList;
import java.util.List;

public class Moteur {
    public List<Triplet> moteur(List<Candidats> candidats) {

        // au place de pretraiteur ...........................

        for (int i = 0; i < candidats.size(); i++) {
            List<String> nomP = new ArrayList<>();
            nomP.add(candidats.get(i).getnom1().getNomOriginal());
            candidats.get(i).getnom1().setNomTraite(nomP);
            List<String> nomj = new ArrayList<>();
            nomj.add(candidats.get(i).getnom2().getNomOriginal());
            candidats.get(i).getnom2().setNomTraite(nomj);

        }

        // au place de comparateur ..................

        List<Triplet> resultat = new ArrayList<>();
        for (int i = 0; i < candidats.size(); i++) {
            if (candidats.get(i).getnom1().getNomOriginal() == candidats.get(i).getnom2().getNomOriginal()) {
                resultat.add(new Triplet(candidats.get(i).getnom1(), candidats.get(i).getnom2(), 1));

            } else {
                resultat.add(new Triplet(candidats.get(i).getnom1(), candidats.get(i).getnom2(), 0));

            }
        }

        // au place de selectionneur ..............

        SelectionneurParScore selectionneur = new SelectionneurParScore(100);

        List<Triplet> r1 = new ArrayList<>();

        r1 = selectionneur.selectionner(resultat);

        return r1;

    }

}
