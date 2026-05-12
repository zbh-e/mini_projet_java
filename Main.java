
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Codes couleur ANSI
    static final String R = "\033[0m"; // Reset
    static final String B = "\033[1m"; // Bold
    static final String CY = "\033[36m"; // Cyan
    static final String GR = "\033[32m"; // Green
    static final String YL = "\033[33m"; // Yellow
    static final String MG = "\033[35m"; // Magenta
    static final String RD = "\033[31m"; // Red
    static final String WH = "\033[37m"; // White

    static final Scanner sc = new Scanner(System.in);
    static List<Nom> database = null;
    static String cheminRepertoire = "data";

    public static void main(String[] args) {
        while (true) {
            afficherMenuPrincipal();
            int choix = lireEntier(0);

            if (choix == 1)
                chargerCSV();
            else if (choix == 2)
                lanceRecherche();
            else if (choix == 3)
                afficherListe();
            else if (choix == 4)
                lanceRechercheParLot();
            else if (choix == 5) {
                afficherAuRevoir();
                return;
            } else
                afficherErreur("Choix invalide.");
        }
    }

    static void afficherMenuPrincipal() {
        System.out.println();
        System.out.println(CY + B + "  ╔════════════════════════════════════════╗");
        System.out.println("  ║   MOTEUR DE MATCHING DE NOMS            ║");
        System.out.println("  ╚════════════════════════════════════════╝" + R);

        if (database == null) {
            System.out.println(RD + "  Aucune liste chargée" + R);
        } else {
            System.out.println(GR + "  Répertoire : \"" + cheminRepertoire + "\"  (" + database.size() + " noms)" + R);
        }

        System.out.println();
        System.out.println(MG + "  1" + R + ". Charger les listes CSV");
        System.out.println(MG + "  2" + R + ". Lancer une recherche");
        System.out.println(MG + "  3" + R + ". Afficher la liste chargée");
        System.out.println(MG + "  4" + R + ". Recherche par lot (CSV)");
        System.out.println(MG + "  5" + R + ". Quitter");
        System.out.print(GR + "\n  Votre choix : " + R);
    }

    static void chargerCSV() {
        System.out.print(GR + "\n  Chemin du répertoire [" + cheminRepertoire + "] : " + R);
        String saisie = lireLigne();
        if (!saisie.isEmpty())
            cheminRepertoire = saisie;

        try {
            System.out.println(YL + "  Chargement en cours..." + R);
            database = LecteurCSV.lireAutodetect(cheminRepertoire);
            System.out.println(GR + "  ✓ OK : " + database.size() + " noms chargés." + R);
        } catch (Exception e) {
            afficherErreur("Erreur : " + e.getMessage());
            database = null;
        }
    }

    static void lanceRecherche() {
        if (database == null || database.isEmpty()) {
            afficherErreur("Chargez d'abord un fichier CSV.");
            return;
        }

        System.out.print(GR + "\n  Nom à rechercher : " + R);
        String nomStr = lireLigne();
        if (nomStr.isEmpty())
            return;

        // Choix du comparateur
        System.out.println("\n" + CY + "  === COMPARATEUR ===" + R);
        System.out.println(MG + "  1" + R + ". Exact (après normalisation)");
        System.out.println(MG + "  2" + R + ". Jaro-Winkler");
        System.out.println(MG + "  3" + R + ". Levenshtein");
        System.out.println(MG + "  4" + R + ". Hamming");
        System.out.println(MG + "  5" + R + ". Jaccard");
        System.out.println(MG + "  6" + R + ". Par champs (Jaro-Winkler)");
        System.out.print(GR + "  Choix [1] : " + R);
        int choixComp = lireEntier(1);

        ComparateurNom comparateur = choisirComparateur(choixComp);

        // Choix du générateur de candidats
        System.out.println("\n" + CY + "  === GÉNÉRATEUR DE CANDIDATS ===" + R);
        System.out.println(MG + "  1" + R + ". Tous les combinaisons ");
        System.out.println(MG + "  2" + R + ". Longueur (différence max 10)");
        
        System.out.println(MG + "  3" + R + ". Par le nombre des caracteres en commun");
        System.out.print(GR + "  Choix [1] : " + R);
        int choixGen = lireEntier(1);

        GenerateurDesCandidats generateur = choisirGenerateur(choixGen);

        // Choix du sélectionneur
        System.out.println("\n" + CY + "  === SÉLECTIONNEUR ===" + R);
        System.out.println(MG + "  1" + R + ". Top N résultats");
        System.out.println(MG + "  2" + R + ". résultats supeieur à un seuil");
        System.out.println(MG + "  3" + R + ". Tous les résultats");
        System.out.print(GR + "  Choix [1] : " + R);
        int choixSel = lireEntier(1);

        Selectionneur selectionneur=choisirSelectionneur(choixSel);
        
        // Configuration des prétraiteurs
        System.out.println("\n" + CY + "  === PRÉTRAITEURS ===" + R);
        System.out.println(YL + "  Normalization" + R);
        System.out.println(YL + "  Abbreviation" + R);
        System.out.println(YL + "  Phonetique" + R);
        System.out.println(YL + "  Tokenization" + R);

        List<IPretraiteur> pretraiteurs = new ArrayList<>();
        pretraiteurs.add(new NormalizationPretraiteur());
        pretraiteurs.add(new AbbreviationPretraiteur());
        pretraiteurs.add(new PhoneticNormalizationPretraiteur());
        pretraiteurs.add(new TokenizationPretraiteur());

        // Lancer la recherche
        System.out.println("\n" + CY + B + "  Recherche en cours...\n" + R);

        MoteurMatching moteur = new MoteurMatching(
                pretraiteurs,
                generateur,
                selectionneur,
                comparateur);

        try {
            List<Triplet> resultats = moteur.rechercher(database, nomStr);

            if (resultats.isEmpty()) {
                System.out.println(YL + "  Aucun résultat trouvé." + R);
            } else {
                System.out.println(GR + "  === RÉSULTATS (" + resultats.size() + ") ===" + R);
                for (int i = 0; i < resultats.size(); i++) {
                    Triplet t = resultats.get(i);
                    System.out.printf(
                            "  " + MG + "%2d" + R + ". %-40s => score: " + GR + "%.4f" + R + "\n",
                            i + 1,
                            t.getNom2().getNomOriginal(),
                            t.getScore());
                }
            }
        } catch (Exception e) {
            afficherErreur("Erreur lors de la recherche : " + e.getMessage());
        }
    }

    static void afficherListe() {
        if (database == null || database.isEmpty()) {
            afficherErreur("Aucune liste chargée.");
            return;
        }

        System.out.println("\n" + CY + B + "  === LISTE CHARGÉE (" + database.size() + " noms) ===" + R);
        int limite = Math.min(20, database.size());

        for (int i = 0; i < limite; i++) {
            Nom n = database.get(i);
            System.out.printf(
                    "  " + MG + "%4d" + R + "  %-45s\n",
                    i + 1,
                    n.getNomOriginal());
        }

        if (database.size() > 20) {
            System.out.println(YL + "  ... et " + (database.size() - 20) + " autres." + R);
        }

        System.out.print("\n  Appuyez sur Entrée pour continuer...");
        lireLigne();
    }

    static void lanceRechercheParLot() {
        if (database == null || database.isEmpty()) {
            afficherErreur("Chargez d'abord un fichier CSV.");
            return;
        }

        System.out.print(GR + "\n  Chemin du fichier CSV contenant les noms à rechercher : " + R);
        String cheminNoms = lireLigne();
        if (cheminNoms.isEmpty())
            return;

        List<Nom> nomsARechercher = null;
        try {
            nomsARechercher = LecteurCSV.lireAutodetect(cheminNoms);
            System.out.println(GR + "  ✓ " + nomsARechercher.size() + " noms à rechercher chargés." + R);
        } catch (Exception e) {
            afficherErreur("Erreur lors du chargement des noms : " + e.getMessage());
            return;
        }

        // Configuration identique à la recherche simple
        System.out.println("\n" + CY + "  === CONFIGURATION DE LA RECHERCHE ===" + R);

        // Comparateur
        System.out.println(MG + "  1" + R + ". Exact (après normalisation)");
        System.out.println(MG + "  2" + R + ". Jaro-Winkler");
        System.out.println(MG + "  3" + R + ". Levenshtein");
        System.out.println(MG + "  4" + R + ". Hamming");
        System.out.println(MG + "  5" + R + ". Jaccard");
        System.out.println(MG + "  6" + R + ". Par champs (Jaro-Winkler)");
        System.out.print(GR + "  Choix [1] : " + R);
        int choixComp = lireEntier(1);

        ComparateurNom comparateur = choisirComparateur(choixComp);

        // Générateur
        System.out.println("\n" + MG + "  1" + R + ". TOus COmbinaison");
        System.out.println(MG + "  2" + R + ". Longueur de nom)");
        System.out.println(MG + "  3" + R + ". par caractere en commun");
        System.out.print(GR + "  Choix [1] : " + R);
        int choixGen = lireEntier(1);

        GenerateurDesCandidats generateur = choisirGenerateur(choixGen);

        // Sélectionneur
        System.out.println("\n" + MG + "  1" + R + ". Top N résultats");
        System.out.println(MG + "  2" + R + ". Tous les résultats");
        System.out.print(GR + "  Choix [1] : " + R);
        int choixSel = lireEntier(1);

        Selectionneur selectionneur;
        
        switch (choixSel) {
            case 1:
                System.out.print(GR + "  Nombre de résultats par nom [5] : " + R);
                int n = (int) lireDouble(5);
                selectionneur = new SelectionneurParNombre(n);
            case 2:
                System.out.print(GR + "  Nombre de résultats par nom [5] : " + R);
                Double npeka = lireDouble(0.68);
                selectionneur = new SelectionneurParScore(npeka);
            
            case 3:
            default:
                selectionneur = new SelectionneurTous();
        }

        // Prétraiteurs
        List<IPretraiteur> pretraiteurs = List.of(
                new NormalizationPretraiteur(),
                new AbbreviationPretraiteur(),
                new PhoneticNormalizationPretraiteur(),
                new TokenizationPretraiteur());

        // Lancer les recherches par lot
        System.out.println("\n" + CY + B + "  Recherche par lot en cours...\n" + R);

        MoteurMatching moteur = new MoteurMatching(pretraiteurs, generateur, selectionneur, comparateur);

        int totalResultats = 0;
        for (int i = 0; i < nomsARechercher.size(); i++) {
            Nom nomRecherche = nomsARechercher.get(i);
            System.out.println(CY + "Recherche " + (i + 1) + "/" + nomsARechercher.size() + " : "
                    + nomRecherche.getNomOriginal() + R);

            try {
                List<Triplet> resultats = moteur.rechercher(database, nomRecherche.getNomOriginal());

                if (resultats.isEmpty()) {
                    System.out.println(YL + "  Aucun résultat trouvé." + R);
                } else {
                    totalResultats += resultats.size();
                    System.out.println(GR + "  === RÉSULTATS (" + resultats.size() + ") ===" + R);
                    for (int j = 0; j < resultats.size(); j++) {
                        Triplet t = resultats.get(j);
                        System.out.printf(
                                "    " + MG + "%2d" + R + ". %-40s => score: " + GR + "%.4f" + R + "\n",
                                j + 1,
                                t.getNom2().getNomOriginal(),
                                t.getScore());
                    }
                }
                System.out.println();
            } catch (Exception e) {
                afficherErreur(
                        "Erreur lors de la recherche de '" + nomRecherche.getNomOriginal() + "' : " + e.getMessage());
            }
        }

        System.out.println(CY + B + "=== RÉSUMÉ ===" + R);
        System.out.println("Noms recherchés : " + nomsARechercher.size());
        System.out.println("Total résultats : " + totalResultats);
    }

    static ComparateurNom choisirComparateur(int choix) {
        switch (choix) {
            case 2:
                return new ComparateurDeNomComplet(new ComparateurJaroWinkler());
            case 3:
                return new ComparateurDeNomComplet(new ComparateurLevenshtein());
            case 4:
                return new ComparateurDeNomComplet(new ComparateurHamming());
            case 5:
                return new ComparateurDeNomComplet(new ComparateurJaccard());
            case 6:
                return new ComparateurNomParChamp(new ComparateurJaroWinkler());
            case 1:
            default:
                return new ComparateurExact();
        }
    }

    static GenerateurDesCandidats choisirGenerateur(int choix) {
        switch (choix) {
            case 3:
                System.out.println("\n donner le nombre des caracteres en commun minimal ");
                int nombreCarac=lireEntier(5);
                return new GenerateurCommun(nombreCarac);
            case 2:
                System.out.println("\n donner la difference de longueur de nom maximal ");
                int LongueurMin=lireEntier(5);

                return new GenerateurLongeurDeNom(LongueurMin);
            
            case 1:
            default:
                return new ToutCombinaison();
        }
    }
    static Selectionneur choisirSelectionneur(int choix) {
        switch (choix) {
            case 1 :
                System.out.println("\n donner le nombre des noms à afficher ");
                int nombreNom=lireEntier(5);
                return new SelectionneurParNombre(nombreNom);
            case 2:
                System.out.println("\n donner le score minimal ");
                double LongueurMin=lireDouble(0.7);

                return new SelectionneurParScore(LongueurMin);
            
            case 3:
            default:
                return new SelectionneurTous();
        }
    }
    static String lireLigne() {
        try {
            if (sc.hasNextLine()) {
                return sc.nextLine().trim();
            }
        } catch (Exception e) {
            // Ignorer
        }
        return "";
    }

    static int lireEntier(int defaut) {
        String ligne = lireLigne();
        if (ligne.isEmpty())
            return defaut;
        try {
            return Integer.parseInt(ligne);
        } catch (NumberFormatException e) {
            return defaut;
        }
    }

    static double lireDouble(double defaut) {
        String ligne = lireLigne();
        if (ligne.isEmpty())
            return defaut;
        try {
            return Double.parseDouble(ligne);
        } catch (NumberFormatException e) {
            return defaut;
        }
    }

    static void afficherErreur(String message) {
        System.out.println(RD + "  ✗ " + message + R);
    }

    static void afficherAuRevoir() {
        System.out.println(CY + "\n  ══════════════════════════════════════════");
        System.out.println("  Au revoir !");
        System.out.println("  ══════════════════════════════════════════" + R + "\n");
    }
}
