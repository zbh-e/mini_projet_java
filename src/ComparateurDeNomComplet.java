public class ComparateurDeNomComplet implements ComparateurNom {
    private ComparateurChaine comparateurChaine;

    public ComparateurDeNomComplet(ComparateurChaine comparateurChaine) {
        this.comparateurChaine = comparateurChaine;
    }

    public double comparer(Nom nom1, Nom nom2) {
        if (nom1 == null || nom2 == null) {
            return 0.0;
        }

        String s1 = nom1.getNomOriginal();
        String s2 = nom2.getNomOriginal();

        double score = comparateurChaine.comparer(s1, s2);

        if (comparateurChaine.getType() == TypeMesure.DISTANCE) {
            score = 1.0 - score;
        }

        return score;
    }

}
