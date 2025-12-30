package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.stock.StockPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StockDouble implements StockPort {
    private final List<Ingrédient> contenu;

    public StockDouble(List<Ingrédient> contenu) {
        this.contenu = contenu;
    }

    @Override
    public boolean vérifierDisponibilité(Ingrédient ingrédientRecherché) {
        return this.contenu.stream()
                .filter(i -> Objects.equals(i.nom(), ingrédientRecherché.nom()))
                .findFirst()
                .filter(ingrédient -> ingrédient.quantité() >= ingrédientRecherché.quantité())
                .isPresent();
    }

    @Override
    public void stockerIngrédient(Ingrédient ingrédient) {
        this.contenu.add(ingrédient);
    }

    @Override
    public Optional<Ingrédient> récupérerIngrédient(Ingrédient ingrédientRecherché) {
        if (!this.vérifierDisponibilité(ingrédientRecherché)) {
            return Optional.empty();
        }

        for (int i = 0; i < this.contenu.size(); i++) {
            var ingrédientCourant = this.contenu.get(i);
            if (Objects.equals(ingrédientCourant.nom(), ingrédientRecherché.nom())) {
                this.contenu.remove(i);
                var nouvelleQuantité = ingrédientCourant.quantité() - ingrédientRecherché.quantité();
                if (nouvelleQuantité > 0) {
                    this.contenu.add(new Ingrédient(ingrédientCourant.nom(), nouvelleQuantité));
                }
                return Optional.of(ingrédientRecherché);
            }
        }

        return Optional.empty();
    }

    public List<Ingrédient> getContenu() {
        return contenu;
    }
}
