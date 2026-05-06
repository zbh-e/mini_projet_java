import java.util.ArrayList;
import java.util.List;
public class GenerateurLongeurDeNom implements GenerateurDesCandidats {
    private int DifferenceMax;
    public GenerateurLongeurDeNom(int x){
        DifferenceMax=x;
    }
    public List<Candidats> genererCandidats(List<Nom> l1,List<Nom> l2){
        
        List<Candidats> c =new ArrayList<>();
        for (int i=0;i< l1.size();i++){
            for (int j=0;j<l2.size();j++){
                int v=Math.abs(l1.get(i).getNomOriginal().length()-l2.get(j).getNomOriginal().length());
                if (v<=DifferenceMax){
                    c.add(new Candidats(l1.get(i), l2.get(j)));
                    

                }

            }
        }
        return c;

    }

    
    
}