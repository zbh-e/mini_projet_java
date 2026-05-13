import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Livreur {
    public void creerFichierVide(String fichier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fichier))) {
            writer.println("nom1,nom2,score");
            System.out.println("Fichier vide créé : " + fichier);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public void livrer(List<Triplet> triplets, String fichier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fichier))) {
            // En-tête CSV
            writer.println("nom1,nom2,score");

            // Lignes de données
            for (Triplet t : triplets) {
                writer.printf("%s,%s,%.4f%n",
                    t.getNom1().getNomOriginal(),
                    t.getNom2().getNomOriginal(),
                    t.getScore()
                );
            }

            System.out.println("Fichier sauvegardé : " + fichier);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }



    public void add(List<Triplet> triplets, String fichier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fichier,true))) {
            // En-tête CSV
            writer.println("nom1,nom2,score");

            // Lignes de données
            for (Triplet t : triplets) {
                writer.printf("%s,%s,%.4f%n",
                    t.getNom1().getNomOriginal(),
                    t.getNom2().getNomOriginal(),
                    t.getScore()
                );
            }

            System.out.println("Fichier sauvegardé : " + fichier);

        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }
}