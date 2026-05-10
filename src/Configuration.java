import java.util.List ;
import java.util.ArrayList;

public class Configuration {
    private List<IPretraiteur> pretraiteurs;
    private ComparateurNom comparateur;
    private GenerateurDesCandidats generateur;
    private Selectionneur selectionneur;

    public Configuration(List<IPretraiteur> pretraiteur , ComparateurNom comparateur , GenerateurDesCandidats generateur,Selectionneur selectionneur ){
        this.pretraiteurs=pretraiteur;
        this.comparateur=comparateur;
        this.generateur=generateur;
        this.selectionneur=selectionneur;

    }
    public Configuration(){
        List<IPretraiteur> p=new ArrayList<>();
        
        IPretraiteur p2=new SpecialCharPretraiteur();
        IPretraiteur p3=new TokenizationPretraiteur();
        IPretraiteur p4=new PhoneticNormalizationPretraiteur();
        IPretraiteur p5=new AbbreviationPretraiteur();
        NormalizationPretraiteur p1=new NormalizationPretraiteur();
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        this.pretraiteurs=p;
        ComparateurChaine c1=new ComparateurJaccard();
        ComparateurNom c=new ComparateurDeNomComplet(c1);
        this.comparateur=c;
        Selectionneur s=new SelectionneurParNombre(5);
        this.selectionneur=s;
        GenerateurDesCandidats g=new GenerateurLongeurDeNom(4);
        this.generateur=g;
        

            
        
    }


    public static GenerateurDesCandidats choisirGenerateur(String x) {
        switch (x) {
            case "Longueur de Nom": return new GenerateurLongeurDeNom(5);
            case "Tous":     return new ToutCombinaison();
            default: throw new IllegalArgumentException("Generateur inconnu ");
        }
    }

    public static ComparateurNom choisirComparateur(String x) {
        switch (x) {
            case "JaroWinkler":  return new ComparateurDeNomComplet(new ComparateurLevenshtein());
            case "Levenshtein":  return new ComparateurDeNomComplet(new ComparateurJaroWinkler());
            case "Jaccard":        return new ComparateurDeNomComplet(new ComparateurJaccard());
            default: throw new IllegalArgumentException("Comparateur inconnu");
        }
    }

    public static Selectionneur choisirSelectionneur(String x, int y) {
        switch (x) {
            case "best N":return new SelectionneurParNombre(y);
            case "Top Score":return new SelectionneurParScore(y);
            case "Tous":return new SelectionneurTous();
            default: throw new IllegalArgumentException("Selectionneur inconnu");
        }
    }

    public void setGenerateur(GenerateurDesCandidats g){this.generateur=g;}
    public void setSelectionneur(Selectionneur s){this.selectionneur=s;}
    public void setComparateur(ComparateurNom c){this.comparateur=c;}
    public void setPretraiteur(List<IPretraiteur> p ){this.pretraiteurs=p;}

    
    
}
