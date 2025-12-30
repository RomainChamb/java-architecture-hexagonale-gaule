package gaule.village.javanoramix.domain.druide;

import gaule.village.javanoramix.domain.druide.cuiseur.AppareilDeCuissonPort;
import gaule.village.javanoramix.domain.druide.stock.IngrédientManquantException;
import gaule.village.javanoramix.domain.druide.stock.StockPort;
import gaule.village.javanoramix.domain.recette.*;

public class Javanoramix implements Druide {

    private final StockPort stock;
    private final AppareilDeCuissonPort cuiseur;

    public Javanoramix(StockPort stock, AppareilDeCuissonPort cuiseur) {
        this.stock = stock;
        this.cuiseur = cuiseur;
    }

    @Override
    public Plat préparer(Recette recetteÀPréparer) {
        System.out.printf("Préparation de la recette : %s\n", recetteÀPréparer.nom());

        vérificationDisponibilitéIngrédients(recetteÀPréparer);

        préchauffage(recetteÀPréparer);

        Ingrédient dernierIngrédientObtenu = Ingrédient.INGRÉDIENT_VIDE;
        for (Étape étape : recetteÀPréparer.déroulé()) {
            System.out.printf("Étape : %s\n", étape.nom());
            if (étape.action() == Action.MELANGER) {
                dernierIngrédientObtenu = mélanger(étape);
            }
            if (étape.action() == Action.BOUILLIR) {
                dernierIngrédientObtenu = bouillir(étape);
            }
            stock.stockerIngrédient(dernierIngrédientObtenu);
            System.out.printf("Réservation de %s\n", dernierIngrédientObtenu.toString());
        }

        return servir(dernierIngrédientObtenu);
    }

    private void vérificationDisponibilitéIngrédients(Recette recetteÀPréparer) {
        System.out.println("Vérification de la disponibilité des ingrédients");
        boolean ingrédientsDisponibles = recetteÀPréparer.ingrédients()
                .stream()
                .allMatch(stock::vérifierDisponibilité);

        if (!ingrédientsDisponibles) throw new IngrédientManquantException();
    }

    private void préchauffage(Recette recetteÀPréparer) {
        if (recetteÀPréparer.préchauffage().isPresent()) {
            var températureSouhaitée = recetteÀPréparer.préchauffage().get();
            cuiseur.préchauffer(températureSouhaitée);
            while (cuiseur.vérifierTempérature().degré() < températureSouhaitée.degré()) {
                try {
                    System.out.printf("Préchauffage du cuiseur en cours : %d %s -> %d %s\n", cuiseur.vérifierTempérature().degré(), cuiseur.vérifierTempérature().unité().label, températureSouhaitée.degré(), cuiseur.vérifierTempérature().unité().label);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Ingrédient mélanger(Étape étape) {
        Ingrédient avec = étape.avec().orElseThrow();
        System.out.printf("Mélanger %s avec %s\n", étape.base(), avec);
        if (!stock.vérifierDisponibilité(étape.base()) || !stock.vérifierDisponibilité(avec)) {
            throw new IngrédientManquantException();
        }
        var ingrédientDeBase = this.stock.récupérerIngrédient(étape.base()).orElseThrow();
        var ingrédientAvec = this.stock.récupérerIngrédient(avec).orElseThrow();
        return new Ingrédient(étape.nomIngrédientObtenu(), ingrédientDeBase.quantité() + ingrédientAvec.quantité());
    }

    private Ingrédient bouillir(Étape étape) {
        System.out.printf("Mise à bouillir de %s\n", étape.base());
        var ingrédientDeBase = this.stock.récupérerIngrédient(étape.base()).orElseThrow();
        cuiseur.cuire(ingrédientDeBase, étape.nomIngrédientObtenu());
        return cuiseur.prélever();
    }

    private Plat servir(Ingrédient ingrédient) {
        var ingrédientÀServir = this.stock.récupérerIngrédient(ingrédient).orElseThrow();
        var plat = new Plat(ingrédientÀServir.nom(), ingrédientÀServir.quantité());
        System.out.printf("Le plat %s est prêt !\n", plat);
        return plat;
    }
}
