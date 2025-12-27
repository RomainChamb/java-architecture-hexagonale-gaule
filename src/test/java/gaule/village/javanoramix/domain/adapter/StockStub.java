package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.StockPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;

import java.util.List;
import java.util.Optional;

public class StockStub implements StockPort {
    private final List<Ingrédient> contenu;

    public StockStub(List<Ingrédient> contenu) {
        this.contenu = contenu;
    }

    @Override
    public boolean vérifierDisponibilité(Ingrédient ingrédient) {
        return this.contenu.contains(ingrédient);
    }

    @Override
    public void ajouterIngrédient(Ingrédient ingrédient) {
        this.contenu.add(ingrédient);
    }

    @Override
    public Optional<Ingrédient> récupérerIngrédient(Ingrédient ingrédient) {
        if (this.contenu.remove(ingrédient)) {
            return Optional.of(ingrédient);
        };
        return Optional.empty();
    }
}
