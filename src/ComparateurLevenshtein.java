public class ComparateurLevenshtein implements ComparateurChaine {

    @Override
    public double comparer(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 1.0;
        }

        int maxLength = Math.max(s1.length(), s2.length());

        if (maxLength == 0) {
            return 0.0;
        }

        int distance = calculerDistance(s1, s2);

        return (double) distance / maxLength;
    }

    private int calculerDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost;

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cost = 0;
                } else {
                    cost = 1;
                }

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost);
            }
        }

        return dp[s1.length()][s2.length()];
    }

    @Override
    public TypeMesure getType() {
        return TypeMesure.DISTANCE;
    }
}