package gaule.village.javanoramix.domain.druide;

import gaule.village.javanoramix.domain.recette.*;

public class Javanoramix implements Druide {

    private StockPort stock;
    private AppareilDeCuissonPort cuiseur;

    public Javanoramix(StockPort stock, AppareilDeCuissonPort cuiseur) {
        this.stock = stock;
        this.cuiseur = cuiseur;
    }

    @Override
    public Plat préparer(Recette recetteÀPréparer) {
        boolean ingrédientsDisponibles = recetteÀPréparer.ingrédients()
                .stream()
                .allMatch(stock::vérifierDisponibilité);

        if (!ingrédientsDisponibles) throw new IngrédientManquantException();

        Ingrédient dernierIngrédientObtenu = Ingrédient.INGRÉDIENT_VIDE;
        for (Étape étape : recetteÀPréparer.déroulé()) {
            if (étape.action() == Action.MELANGER) {
                Ingrédient avec = étape.avec().orElseThrow();
                dernierIngrédientObtenu = new Ingrédient(étape.nomIngrédientObtenu(), étape.base().quantité() + avec.quantité());
            }
        }

        return new Plat(dernierIngrédientObtenu.nom(), dernierIngrédientObtenu.quantité());
    }
}
