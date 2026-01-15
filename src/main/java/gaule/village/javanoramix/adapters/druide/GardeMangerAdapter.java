package gaule.village.javanoramix.adapters.druide;

import gaule.village.javanoramix.domain.druide.stock.StockPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.infrastructure.GardeManger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GardeMangerAdapter implements StockPort {
    private final GardeManger gardeManger;

    public GardeMangerAdapter(GardeManger gardeManger) {
        this.gardeManger = gardeManger;
    }

    @Override
    public boolean vérifierDisponibilité(Ingrédient ingrédient) {
        return gardeManger.vérifierDisponibilité(ingrédient);
    }

    @Override
    public void stockerIngrédient(Ingrédient ingrédient) {
        gardeManger.stockerIngrédient(ingrédient);
    }

    @Override
    public Optional<Ingrédient> récupérerIngrédient(Ingrédient ingrédient) {
        return gardeManger.récupérerIngrédient(ingrédient);
    }
}
