import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LecteurCSV {

    private final String cheminFichier;
    private final char separateur;

    public LecteurCSV(String cheminFichier, char separateur) {
        this.cheminFichier = cheminFichier;
        this.separateur = separateur;
    }

    public LecteurCSV(String cheminFichier) {
        this(cheminFichier, ',');
    }

    // CSV format: nom (colonne 0 ou 1 selon le fichier)
    public List<Nom> lire() throws IOException {
        List<Nom> noms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            int id = 0;

            // Essayer de sauter l'entête si elle existe
            String firstLine = br.readLine();
            if (firstLine != null
                    && (firstLine.toLowerCase().contains("nom") || firstLine.toLowerCase().contains("name"))) {
                // C'est probablement l'entête, on la saute
            } else if (firstLine != null) {
                // Sinon, traiter la première ligne
                processLine(firstLine, noms, String.valueOf(id++), separateur);
            }

            while ((ligne = br.readLine()) != null) {
                ligne = ligne.trim();
                if (ligne.isEmpty())
                    continue;
                processLine(ligne, noms, String.valueOf(id++), separateur);
            }
        }
        return noms;
    }

    private static void processLine(String ligne, List<Nom> noms, String idStr, char separateur) {
        String[] colonnes = ligne.split(String.valueOf(separateur));
        if (colonnes.length < 1)
            return;

        // Prendre la dernière colonne ou la première colonne selon le format
        String nom = colonnes[colonnes.length > 1 ? colonnes.length - 1 : 0].trim();

        if (!nom.isEmpty()) {
            noms.add(new Nom(idStr, nom));
        }
    }

    /**
     * Détecte si c'est un fichier ou un répertoire et lit les données
     * - Si c'est un fichier .csv : lit ce fichier
     * - Si c'est un répertoire : lit tous les fichiers .csv du répertoire
     */
    public static List<Nom> lireAutodetect(String chemin) throws IOException {
        Path path = Paths.get(chemin);

        // Si c'est un fichier CSV
        if (Files.isRegularFile(path) && chemin.endsWith(".csv")) {
            LecteurCSV lecteur = new LecteurCSV(chemin);
            return lecteur.lire();
        }
        // Si c'est un répertoire
        else if (Files.isDirectory(path)) {
            return lireRepertoireCSV(chemin);
        }
        // Sinon, erreur
        else {
            throw new IOException("Le chemin n'est pas un fichier CSV ou un répertoire valide : " + chemin);
        }
    }

    /**
     * Lit tous les fichiers CSV d'un dossier
     */
    public static List<Nom> lireRepertoireCSV(String cheminRepertoire) throws IOException {
        List<Nom> tousLesNoms = new ArrayList<>();
        Path repertoire = Paths.get(cheminRepertoire);

        List<Path> fichiersCsv = Files.list(repertoire)
                .filter(path -> path.toString().endsWith(".csv"))
                .collect(Collectors.toList());

        int idGlobal = 0;
        for (Path fichier : fichiersCsv) {
            try {
                LecteurCSV lecteur = new LecteurCSV(fichier.toString());
                List<Nom> nomsLus = lecteur.lire();
                // Renuméroter les ids
                for (Nom nom : nomsLus) {
                    tousLesNoms.add(new Nom(String.valueOf(idGlobal++), nom.getNomOriginal()));
                }
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture de " + fichier + " : " + e.getMessage());
            }
        }

        return tousLesNoms;
    }
}
