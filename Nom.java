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




}
