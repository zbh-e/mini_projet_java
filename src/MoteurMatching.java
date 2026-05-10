import java.util.ArrayList;
import java.util.List;

public class MoteurMatching {
    private List<IPretraiteur> pretraiteurs;
    private GenerateurDesCandidats generateurDesCandidats;
    private Selectionneur selectionneur;
    private ComparateurNom comparateurNom;

    public MoteurMatching(List<IPretraiteur> pretraiteurs, GenerateurDesCandidats generateurDesCandidats,
            Selectionneur selectionneur, ComparateurNom comparateurNom) {
        this.pretraiteurs = pretraiteurs;
        this.generateurDesCandidats = generateurDesCandidats;
        this.selectionneur = selectionneur;
        this.comparateurNom = comparateurNom;

    }

    public List<Triplet> rechercher(List<Nom> noms, String nomRechercher) {
        for (Nom n : noms) {
            for (IPretraiteur pretraiteur : pretraiteurs) {
                n = pretraiteur.traiter(n);
            }
        }

        Nom nomARechercher = new Nom("recherche", nomRechercher);
        for (IPretraiteur pretraiteur : pretraiteurs) {
            nomARechercher = pretraiteur.traiter(nomARechercher);
        }

        List<Candidats> Candidats = generateurDesCandidats.genererCandidats(List.of(nomARechercher), noms);

        List<Triplet> resultats = new ArrayList<>();

        for (Candidats candidat : Candidats) {
            double score = comparateurNom.comparer(candidat.getnom1(), candidat.getnom2());
            resultats.add(new Triplet(candidat.getnom1(), candidat.getnom2(), score));

        }
        return selectionneur.selectionner(resultats);
    }

}
