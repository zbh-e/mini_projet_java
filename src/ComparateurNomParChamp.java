import java.util.List;

public class ComparateurNomParChamp implements ComparateurNom {

    private final ComparateurChaine comparateurChaine;

    public ComparateurNomParChamp(ComparateurChaine comparateurChaine) {
        this.comparateurChaine = comparateurChaine;
    }

    public double comparer(Nom a, Nom b) {
        List<String> champsA = a.getNomTraite();
        List<String> champsB = b.getNomTraite();
        if (champsA == null || champsA.isEmpty() || champsB == null || champsB.isEmpty())
            return 0.0;

        double total = 0.0;
        for (String champA : champsA) {
            double best = 0.0;
            for (String champB : champsB) {
                double score = comparateurChaine.comparer(champA, champB);
                if (comparateurChaine.getType() == TypeMesure.DISTANCE) {
                    score = 1.0 - score; // Convertir distance en similarité
                }
                if (score > best)
                    best = score;
            }
            total += best;
        }
        return total / champsA.size();
    }
}
