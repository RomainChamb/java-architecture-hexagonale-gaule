package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.StockPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.infrastructure.GardeManger;

import java.util.Optional;

public class GardeMangerAdapter implements StockPort {
    private GardeManger gardeManger;

    @Override
    public boolean vérifierDisponibilité(Ingrédient ingrédient) {
        return false;
    }

    @Override
    public void ajouterIngrédient(Ingrédient ingrédient) {

    }

    @Override
    public Optional<Ingrédient> récupérerIngrédient(Ingrédient ingrédient) {
        return null;
    }
}
