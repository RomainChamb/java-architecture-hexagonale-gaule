package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.cuiseur.AppareilDeCuissonPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.infrastructure.Thermomix;

public class CuiseurDouble extends Thermomix implements AppareilDeCuissonPort{
    public Ingrédient getIngrédientEnCuisson() {
        return this.ingrédientEnCuisson;
    }
}
