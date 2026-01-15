package gaule.village.javanoramix.adapters.druide;

import gaule.village.javanoramix.domain.druide.cuiseur.AppareilDeCuissonPort;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.domain.recette.Température;
import gaule.village.javanoramix.infrastructure.Thermomix;
import org.springframework.stereotype.Service;

@Service
public class ThermomixAdapter implements AppareilDeCuissonPort {
    private final Thermomix thermomix;

    public ThermomixAdapter(Thermomix thermomix) {
        this.thermomix = thermomix;
    }

    @Override
    public void préchauffer(Température température) {
        this.thermomix.préchauffer(température);
    }

    @Override
    public Température vérifierTempérature() {
        return this.thermomix.vérifierTempérature();
    }

    @Override
    public void cuire(Ingrédient ingrédientÀCuire, String nomIngrédientObtenu) {
        this.thermomix.cuire(ingrédientÀCuire, nomIngrédientObtenu);
    }

    @Override
    public Ingrédient prélever() {
        return this.thermomix.prélever();
    }
}
