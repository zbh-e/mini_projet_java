import java.util.ArrayList;
import java.util.List;

public class GenerateurCommun implements GenerateurDesCandidats {
    int nombreCaracterMin;

    public GenerateurCommun(int nombreCaracterMin) {
        this.nombreCaracterMin = nombreCaracterMin;

    }

    public int checkCommun(String nom1, String nom2) {
        int score = 0;

        nom1 = nom1.replace(" ", "").toLowerCase();
        nom2 = nom2.replace(" ", "").toLowerCase();

        boolean[] used = new boolean[nom2.length()];

        for (int i = 0; i < nom1.length(); i++) {
            char c = nom1.charAt(i);
            for (int j = 0; j < nom2.length(); j++) {
                if (!used[j] && nom2.charAt(j) == c) {
                    score++;
                    used[j] = true;
                    break;
                }
            }
        }

        return score;

    }

    public List<Candidats> genererCandidats(List<Nom> l1, List<Nom> l2) {
        List<Candidats> c = new ArrayList<>();
        for (Nom n1 : l1) {
            for (Nom n2 : l2) {
                int n = checkCommun(n1.getNomOriginal(), n2.getNomOriginal());
                if (n > 4) {
                    c.add(new Candidats(n1, n2));
                }

            }
        }

        return c;
    }

}
