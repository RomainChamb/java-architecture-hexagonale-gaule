package gaule.village.javanoramix.domain.druide.stock;

import gaule.village.javanoramix.domain.recette.Ingrédient;

import java.util.Optional;

public interface Stock {
    boolean vérifierDisponibilité(Ingrédient ingrédient);
    void stockerIngrédient(Ingrédient ingrédient);
    Optional<Ingrédient> récupérerIngrédient(Ingrédient ingrédient);
}
