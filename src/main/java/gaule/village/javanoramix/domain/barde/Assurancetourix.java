package gaule.village.javanoramix.domain.barde;

import gaule.village.javanoramix.domain.barde.instrument.InstrumentDeMusique;
import gaule.village.javanoramix.domain.shared.Niveau;

public class Assurancetourix implements Barde {

    private final InstrumentDeMusique instrument;

    public Assurancetourix(InstrumentDeMusique instrument) {
        this.instrument = instrument;
    }

    @Override
    public void chanter(String message, Niveau niveau) {
        instrument.jouer(formattedMessage(message, niveau));
    }

    private String formattedMessage(String message, Niveau niveau) {
        if(Niveau.IMPORTANT.equals(niveau)) {
            String importantPrefix = "⚠ ATTENTION, BRAVES GAULOIS : ";
            return importantPrefix + message + ", " + importantPrefix + message;
        }

        if (Niveau.URGENT.equals(niveau)) {
            return message.toUpperCase() + ", " + message.toUpperCase() + ", " + message.toUpperCase();
        }
        return message;
    }
}
