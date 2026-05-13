import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LivreurCSV implements Livreur {

    private String cheminFichier;

    public LivreurCSV(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    @Override
    public void livrer(List<Triplet> resultats) {

        try {
            FileWriter fw = new FileWriter(cheminFichier);

            // en-tête
            fw.write("nom1,nom2,score\n");

            for (Triplet t : resultats) {
                String nom1  = t.getNom1().getNomOriginal();
                String nom2  = t.getNom2().getNomOriginal();
                double score = t.getScore();

                fw.write(nom1 + "," + nom2 + "," + String.format("%.2f", score) + "\n");
            }

            fw.close();
            System.out.println("Sauvegardé dans : " + cheminFichier);

        } catch (IOException e) {
            System.out.println("Erreur fichier : " + e.getMessage());
        }
    }

    public String getCheminFichier() {
        return cheminFichier;
    }
}