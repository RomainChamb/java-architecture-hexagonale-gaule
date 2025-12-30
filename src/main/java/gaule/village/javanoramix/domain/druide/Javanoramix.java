package gaule.village.javanoramix.domain.druide;

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
            stock.ajouterIngrédient(dernierIngrédientObtenu);
            System.out.printf("Réservation de %d * %s\n", dernierIngrédientObtenu.quantité(), dernierIngrédientObtenu.nom());
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
        System.out.printf("Mélanger %d * '%s' avec %d * '%s'\n", étape.base().quantité(), étape.base().nom(), avec.quantité(), avec.nom());
        if (!stock.vérifierDisponibilité(étape.base()) || !stock.vérifierDisponibilité(avec)) {
            throw new IngrédientManquantException();
        }
        return new Ingrédient(étape.nomIngrédientObtenu(), étape.base().quantité() + avec.quantité());
    }

    private Ingrédient bouillir(Étape étape) {
        System.out.printf("Mise à bouillir de %d * %s\n", étape.base().quantité(), étape.base().nom());
        cuiseur.cuire(étape.base(), étape.nomIngrédientObtenu());
        return cuiseur.prélever();
    }


    private Plat servir(Ingrédient ingrédient) {
        System.out.printf("Le plat %d * %s est prêt !\n", ingrédient.quantité(), ingrédient.nom());
        return new Plat(ingrédient.nom(), ingrédient.quantité());
    }
}
