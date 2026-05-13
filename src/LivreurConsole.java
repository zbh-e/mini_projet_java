import java.util.List;
 
public class LivreurConsole implements Livreur {
 
    @Override
    public void livrer(List<Triplet> resultats) {
 
        if (resultats == null || resultats.isEmpty()) {
            System.out.println("Aucun résultat trouvé.");
            return;
        }
 
        System.out.println("=== Résultats (" + resultats.size() + ") ===");
 
        for (int i = 0; i < resultats.size(); i++) {
            Triplet t = resultats.get(i);
            System.out.println((i + 1) + ". " + t.getNom1().getNomOriginal()
                    + "  ↔  " + t.getNom2().getNomOriginal()
                    + "  →  score : " + String.format("%.2f", t.getScore()));
        }
    }
}