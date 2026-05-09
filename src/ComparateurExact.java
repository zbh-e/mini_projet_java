import java.util.List;

public class ComparateurExact implements ComparateurNom {

    public double comparer(Nom nom1, Nom nom2) {
        List<String> mots1 = nom1.getNomTraite();
        List<String> mots2 = nom2.getNomTraite();

        if (mots1 != null || mots2 != null) {
            return mots1.equals(mots2) ? 1.0 : 0.0;

        } else {
            String s1 = nom1.getNomOriginal();
            String s2 = nom2.getNomOriginal();

            return s1 != null && s1.equalsIgnoreCase(s2) ? 1.0 : 0.0;
        }
    }

}
