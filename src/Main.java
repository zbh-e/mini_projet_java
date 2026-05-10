public class Main {
    public static void main(String[] args) {
        PretraiteurPipeline pipeline = PretraiteurPipeline.standard();
        Nom nom = new Nom("1", "M. Éléonore Dupont   ");
        System.out.println("Avant traitement : '" + nom.getNomOriginal() + "'");
        Nom nomTraite = pipeline.traiter(nom);
        System.out.println("Après traitement : '" + nomTraite.getNomNormaliser() + "'");
        NormalizationPretraiteur normalizer = new NormalizationPretraiteur();
        Nom nom2 = new Nom("2", "zied -bén-hammouDA");
        Nom nom2Traite = normalizer.traiter(nom2);
        Nom nom22Traite = normalizer.faireTraitement(nom2);
        System.out.println("Avant traitement : '" + nom2.getNomOriginal() + "'");
        System.out.println("Après traitement : '" + nom2Traite.getNomNormaliser() + "'");
        System.out.println("Après traitement (faireTraitement) : '" + nom22Traite.getNomNormaliser() + "'");
    }
}