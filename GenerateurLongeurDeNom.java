public class GenerateurLongeurDeNom implements GenerateurDesCandidats {
    private int DifferenceMax;
    public GenerateurLongeurDeNom(int x){
        DifferenceMax=x;
    }
    public Candidats [] genererCandidats(Nom[] l1,Nom[] l2){
        int k=0;
        Candidats [] c =new Candidats[l1.length*l2.length];
        for (int i=0;i< l1.length;i++){
            for (int j=0;j<l2.length;j++){
                int v=Math.abs(l1[i].getNomOriginal().length()-l2[j].getNomOriginal().length());
                if (v<=DifferenceMax){
                    c[k] = new Candidats(l1[i], l2[j]);
                    k++;

                }

            }
        }
        return c;

    }

    
    
}