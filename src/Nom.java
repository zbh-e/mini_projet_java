<<<<<<< HEAD
public class Nom{
    private String nomOriginal ;
    private String [] nomTraite;
    private int id ;
    public String getNomOriginal (){
        return nomOriginal;
    }
    public String [] getNomTraite (){
        return nomTraite;
    }
    public int getId (){
        return id;
    }

    public Nom (String s,int id){
        nomOriginal=s;
        this.id=id;


    }




=======
import java.util.List;
import java.util.Objects;


public class Nom {

    private final String id;
    private final String nomOriginal;

    
    private String nomNormalise;

    private List<String> nomTraite;

    
    public Nom(String id, String nomOriginal) {
        this.id           = Objects.requireNonNull(id,          "id must not be null");
        this.nomOriginal  = Objects.requireNonNull(nomOriginal, "nomOriginal must not be null");
        this.nomNormalise = nomOriginal; 
    }

    
    public Nom(String nomOriginal) {
        this("", nomOriginal);
    }

    

    public String getId() {
        return id;
    }

    public String getNomOriginal() {
        return nomOriginal;
    }

    public String getNomNormalise() {
        return nomNormalise;
    }

    public void setNomNormalise(String nomNormalise) {
        this.nomNormalise = Objects.requireNonNull(nomNormalise, "nomNormalise must not be null");
    }

    public List<String> getNomTraite() {
        return nomTraite;
    }

    public void setNomTraite(List<String> nomTraite) {
        this.nomTraite = Objects.requireNonNull(nomTraite, "nomTraite must not be null");
    }

   

    @Override
    public String toString() {
        return "Nom{id='" + id + "', original='" + nomOriginal
             + "', normalise='" + nomNormalise
             + "', traite=" + nomTraite + "}";
    }
>>>>>>> main
}