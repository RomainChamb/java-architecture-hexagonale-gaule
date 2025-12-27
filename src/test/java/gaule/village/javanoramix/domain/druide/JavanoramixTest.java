package gaule.village.javanoramix.domain.druide;

import gaule.village.javanoramix.domain.adapter.CuiseurStub;
import gaule.village.javanoramix.domain.adapter.StockStub;
import gaule.village.javanoramix.domain.recette.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class JavanoramixTest {

    public static final Recette RECETTE_DES_FRAIFLES = new Recette(
            List.of(
                    new Ingrédient("trèfle à 4 feuilles", 8),
                    new Ingrédient("fraise", 12)
            ),
            List.of(
                    new Étape(
                            "Mélanger les trèfles et les fraises",
                            new Ingrédient("trèfle à 4 feuilles", 8),
                            Optional.of(new Ingrédient("fraise", 12)),
                            Action.MELANGER,
                            "fraifle"
                    )
            )
    );

    public static final Recette RECETTE_DES_FRAIFLES_AU_GUI = new Recette(
            List.of(
                    new Ingrédient("trèfle à 4 feuilles", 8),
                    new Ingrédient("fraise", 12),
                    new Ingrédient("feuille de gui coupée à la serpe d'or", 1)
            ),
            List.of(
                    new Étape(
                            "Mélanger les trèfles et les fraises",
                            new Ingrédient("trèfle à 4 feuilles", 8),
                            Optional.of(new Ingrédient("fraise", 12)),
                            Action.MELANGER,
                            "fraifle"
                    ),
                    new Étape(
                            "Mélanger les fraifles et le gui",
                            new Ingrédient("fraifle", 20),
                            Optional.of(new Ingrédient("feuille de gui coupée à la serpe d'or", 1)),
                            Action.MELANGER,
                            "fraifle au gui"
                    )
            )
    );



    @Test
    void préparerDesFraifles() {
        // Arrange
        StockPort stock = new StockStub(
            List.of(
                new Ingrédient("fraise", 12),
                new Ingrédient("trèfle à 4 feuilles", 8)
            )
        );
        AppareilDeCuissonPort cuiseur = new CuiseurStub();
        Druide javanoramix = new Javanoramix(stock, cuiseur);

        Plat platAttendu = new Plat("fraifle", 20);

        // Act
        Plat platObtenu = javanoramix.préparer(RECETTE_DES_FRAIFLES);

        // Assert
        assertEquals(platAttendu, platObtenu);
    }

    @Test
    void préparerDesFraiflesIngrédientAbsent() {
        // Arrange
        StockPort stock = new StockStub(
                List.of(new Ingrédient("fraise", 12))
        );
        AppareilDeCuissonPort cuiseur = new CuiseurStub();
        Druide javanoramix = new Javanoramix(stock, cuiseur);

        // Act + Assert
        IngrédientManquantException exceptionObtenue = assertThrows(IngrédientManquantException.class, () -> javanoramix.préparer(RECETTE_DES_FRAIFLES));

    }

    @Test
    void préparerDesFraiflesIngrédientManquant() {
        // Arrange
        StockPort stock = new StockStub(
            List.of(
                new Ingrédient("fraise", 12 - 1),
                new Ingrédient("trèfle à 4 feuilles", 8)
            )
        );
        AppareilDeCuissonPort cuiseur = new CuiseurStub();
        Druide javanoramix = new Javanoramix(stock, cuiseur);

        // Act + Assert
        IngrédientManquantException exceptionObtenue = assertThrows(IngrédientManquantException.class, () -> javanoramix.préparer(RECETTE_DES_FRAIFLES));

    }

    @Test
    void préparerDesFraiflesAuGui() {
        // Arrange
        StockPort stock = new StockStub(
                List.of(
                        new Ingrédient("fraise", 12),
                        new Ingrédient("trèfle à 4 feuilles", 8),
                        new Ingrédient("feuille de gui coupée à la serpe d'or", 1)
                )
        );
        AppareilDeCuissonPort cuiseur = new CuiseurStub();
        Druide javanoramix = new Javanoramix(stock, cuiseur);

        Plat platAttendu = new Plat("fraifle au gui", 21);

        // Act
        Plat platObtenu = javanoramix.préparer(RECETTE_DES_FRAIFLES_AU_GUI);

        // Assert
        assertEquals(platAttendu, platObtenu);
    }

}
