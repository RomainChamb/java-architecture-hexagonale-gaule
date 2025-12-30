package gaule.village.javanoramix.domain.recette;

import java.util.List;
import java.util.Optional;

public record Recette(
        String nom,
        List<Ingrédient> ingrédients,
        Optional<Température> préchauffage,
        List<Étape> déroulé) {

    public static Recette RECETTE_DE_LA_POTION_MAGIQUE = new Recette(
            "Recette de la potion magique",
            List.of(
                    new Ingrédient("trèfle à 4 feuilles", 8),
                    new Ingrédient("fraise", 12),
                    new Ingrédient("once de lait de sanglier", 4),
                    new Ingrédient("pincée de curcuma", 10),
                    new Ingrédient("feuille de gui coupée à la serpe d'or", 1)
            ),
            Optional.of(new Température(90, UnitéTempérature.CELCIUS)),
            List.of(
                    new Étape(
                            "Mélanger les trèfles et les fraises",
                            new Ingrédient("trèfle à 4 feuilles", 8),
                            Optional.of(new Ingrédient("fraise", 12)),
                            Action.MELANGER,
                            "fraifle"
                    ),
                    new Étape(
                            "Préparer le lait doré",
                            new Ingrédient("once de lait de sanglier", 4),
                            Optional.of(new Ingrédient("pincée de curcuma", 10)),
                            Action.MELANGER,
                            "lait doré"
                    ),
                    new Étape(
                            "Faire bouillir les fraifles",
                            new Ingrédient("fraifle", 20),
                            Optional.empty(),
                            Action.BOUILLIR,
                            "fraifle bouillie"
                    ),
                    new Étape(
                            "Ajouter le gui",
                            new Ingrédient("fraifle bouillie", 20),
                            Optional.of(new Ingrédient("feuille de gui coupée à la serpe d'or", 1)),
                            Action.MELANGER,
                            "fraifle au gui"
                    ),
                    new Étape(
                            "Verser le lait doré",
                            new Ingrédient("fraifle au gui", 21),
                            Optional.of(new Ingrédient("lait doré", 14)),
                            Action.MELANGER,
                            "précurseur de potion magique"
                    ),
                    new Étape(
                            "Faire bouillir le précurseur",
                            new Ingrédient("précurseur de potion magique", 35),
                            Optional.empty(),
                            Action.BOUILLIR,
                            "potion magique"
                    )
            )
    );
}
