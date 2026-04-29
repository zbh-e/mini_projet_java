package comparateur;

import modeles.*;

public class ComparateurDistance implements Comparateur {

    @Override
    public double comparer(Nom nom1, Nom nom2) {

        String s1 = nom1.getNomTraite();
        String s2 = nom2.getNomTraite();

        int dist = levenshtein(s1, s2);

        return 1.0 - ((double) dist / Math.max(s1.length(), s2.length()));
    }

    private int levenshtein(String a, String b) {

        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {

                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost);
            }
        }

        return dp[a.length()][b.length()];
    }
}