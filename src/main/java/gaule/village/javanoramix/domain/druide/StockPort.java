package gaule.village.javanoramix.domain.druide;

import gaule.village.javanoramix.domain.recette.Ingrédient;

import java.util.Optional;

public interface StockPort {
    boolean vérifierDisponibilité(Ingrédient ingrédient);
    void ajouterIngrédient(Ingrédient ingrédient);
    Optional<Ingrédient> récupérerIngrédient(Ingrédient ingrédient);
}
