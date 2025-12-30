package gaule.village.javanoramix.domain.druide.cuiseur;

import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.domain.recette.Température;

public interface AppareilDeCuissonPort {
    void préchauffer(Température température);
    Température vérifierTempérature();
    void cuire(Ingrédient ingrédientÀCuire, String nomIngrédientObtenu);
    Ingrédient prélever();
}
