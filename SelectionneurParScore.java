import java.util.ArrayList;
import java.util.List;
public class SelectionneurParScore implements Selectionneur {
    private double score;
    public SelectionneurParScore(double x){
        this.score=x;

    }
    public List<Triplet> selectionner (List<Triplet> t){
        List<Triplet> output=new ArrayList<>();
        for (Triplet triplet : t) {
            if (triplet.getScore() >= score) {
                output.add(triplet);
            }
        }
        return output;



    }
    
}
