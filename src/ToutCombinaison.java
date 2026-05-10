import java.util.ArrayList;
import java.util.List;

public class ToutCombinaison implements GenerateurDesCandidats {
    public List<Candidats> genererCandidats(List<Nom> l1, List<Nom> l2) {

        List<Candidats> c = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l2.size(); j++) {
                c.add(new Candidats(l1.get(i), l2.get(j)));

            }

        }
        return c;

    }
}
