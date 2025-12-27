package gaule.village.javanoramix.domain.druide;

import gaule.village.javanoramix.domain.recette.Plat;
import gaule.village.javanoramix.domain.recette.Recette;

public interface Druide {
    Plat préparer(Recette recetteÀPréparer);
}
