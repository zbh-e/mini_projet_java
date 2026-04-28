public class ComparateurExact implements Comparateur {

    @Override
    public double comparer(Nom nom1, Nom nom2) {
        return nom1.getNomTraite().equals(nom2.getNomTraite()) ? 1.0 : 0.0;
    }

}
