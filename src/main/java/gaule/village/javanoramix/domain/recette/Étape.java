package gaule.village.javanoramix.domain.recette;

import java.util.Optional;

public record Étape(
        String nom,
        Ingrédient base,
        Optional<Ingrédient> avec,
        Action action,
        String nomIngrédientObtenu) {}
