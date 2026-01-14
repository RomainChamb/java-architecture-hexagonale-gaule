package gaule.village.javanoramix.apix;

import gaule.village.javanoramix.domain.druide.Druide;
import gaule.village.javanoramix.domain.recette.Recette;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PotionMagiqueRestController {
    private final Druide druide;

    public PotionMagiqueRestController(Druide druide) {
        this.druide = druide;
    }

    @PostMapping("/potion-magique")
    void préparerPotionMagique() {
        this.druide.préparer(Recette.RECETTE_DE_LA_POTION_MAGIQUE);
    }
}
