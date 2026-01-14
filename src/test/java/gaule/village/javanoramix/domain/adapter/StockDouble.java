package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.stock.StockPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.infrastructure.GardeManger;

import java.util.List;

public class StockDouble extends GardeManger implements StockPort {
    public StockDouble(List<Ingrédient> contenu) {
        super(contenu);
    }

    public List<Ingrédient> getContenu() {
        return this.contenu;
    }
}
