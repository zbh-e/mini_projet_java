import java.util.ArrayList;
import java.util.List;
public class SelectionneurParNombre implements Selectionneur {
    private int nombreMax ;
    public SelectionneurParNombre ( int x){
        this.nombreMax=x;
    }
    public List<Triplet> selectionner(List<Triplet> l){
        int k=l.size();
        List<Triplet> resultat =new ArrayList<>();
        l.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        for (int i =0;i<nombreMax;i++){
            if (k>0){
                resultat.add(l.get(i));
                k--;

            }
            
        }


        
        
        



        return resultat;
    }
    
}
