package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.barde.Barde;
import gaule.village.javanoramix.domain.shared.Niveau;

public class BardeDouble implements Barde {
    public String message;
    public Niveau niveau;

    @Override
    public void chanter(String message, Niveau niveau) {
        this.message = message;
        this.niveau = niveau;
    }
}
