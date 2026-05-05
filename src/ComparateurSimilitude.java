public class ComparateurSimilitude implements Comparateur {

    @Override
    public double comparer(Nom nom1, Nom nom2) {

        String s1 = nom1.getNomTraite();
        String s2 = nom2.getNomTraite();

        int m = 0;
        int matchDistance = Math.max(s1.length(), s2.length()) / 2 - 1;

        boolean[] s1Matches = new boolean[s1.length()];
        boolean[] s2Matches = new boolean[s2.length()];

        for (int i = 0; i < s1.length(); i++) {
            int start = Math.max(0, i - matchDistance);
            int end = Math.min(i + matchDistance + 1, s2.length());

            for (int j = start; j < end; j++) {
                if (s2Matches[j])
                    continue;
                if (s1.charAt(i) != s2.charAt(j))
                    continue;

                s1Matches[i] = true;
                s2Matches[j] = true;
                m++;
                break;
            }
        }

        if (m == 0)
            return 0;

        double t = 0;
        int k = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (!s1Matches[i])
                continue;

            while (!s2Matches[k])
                k++;

            if (s1.charAt(i) != s2.charAt(k))
                t++;

            k++;
        }

        t /= 2.0;

        double jaro = ((m / (double) s1.length())
                + (m / (double) s2.length())
                + ((m - t) / m)) / 3.0;

        int prefix = 0;
        for (int i = 0; i < Math.min(4, Math.min(s1.length(), s2.length())); i++) {
            if (s1.charAt(i) == s2.charAt(i))
                prefix++;
            else
                break;
        }

        return jaro + (prefix * 0.1 * (1 - jaro));
    }
}
