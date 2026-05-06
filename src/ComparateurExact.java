public class ComparateurExact implements ComparateurNom {

    public double comparer(Nom nom1, Nom nom2) {
        return nom1.getNomOriginal().equals(nom2.getNomOriginal()) ? 1.0 : 0.0;
    }

}
