package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.AppareilDeCuissonPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.domain.recette.Température;
import gaule.village.javanoramix.infrastructure.Thermomix;

public class ThermomixAdapter implements AppareilDeCuissonPort {
    private Thermomix thermomix;

    @Override
    public void préchauffer(Température température) {

    }

    @Override
    public Température vérifierTempérature() {
        return null;
    }

    @Override
    public void cuire(Ingrédient ingrédientÀCuire, String nomIngrédientObtenu) {

    }

    @Override
    public Ingrédient prélever() {
        return null;
    }
}
