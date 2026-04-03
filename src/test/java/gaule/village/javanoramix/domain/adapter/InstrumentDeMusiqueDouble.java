package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.barde.instrument.InstrumentDeMusique;

public class InstrumentDeMusiqueDouble implements InstrumentDeMusique {
    public String message;

    @Override
    public void jouer(String message) {
        this.message = message;
    }
}
