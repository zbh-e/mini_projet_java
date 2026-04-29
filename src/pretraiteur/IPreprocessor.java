package pretraiteur;

import modeles.*;

public interface IPreprocessor {

    Nom process(Nom nom);

    String getNom();
}
