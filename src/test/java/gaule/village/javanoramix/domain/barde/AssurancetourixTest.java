package gaule.village.javanoramix.domain.barde;

import gaule.village.javanoramix.domain.adapter.InstrumentDeMusiqueDouble;
import gaule.village.javanoramix.domain.shared.Niveau;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssurancetourixTest {

    @Test
    void chanterUnMessageNormal() {
        // Arrange
        String message = "Message normal !";
        Niveau niveau = Niveau.NORMAL;

        InstrumentDeMusiqueDouble instrument = new InstrumentDeMusiqueDouble();
        Barde barde = new Assurancetourix(instrument);

        // Act
        barde.chanter(message, niveau);

        // Assert
        assertEquals(instrument.message, message);
    }

    @Test
    void chanterUnMessageImportant() {
        // Arrange
        String message = "Message important !";
        Niveau niveau = Niveau.IMPORTANT;
        String expectedMessage = "⚠ ATTENTION, BRAVES GAULOIS : %s, ⚠ ATTENTION, BRAVES GAULOIS : %s".formatted(message, message);

        InstrumentDeMusiqueDouble instrument = new InstrumentDeMusiqueDouble();
        Barde barde = new Assurancetourix(instrument);

        // Act
        barde.chanter(message, niveau);

        // Assert
        assertEquals(instrument.message, expectedMessage);
    }

    @Test
    void chanterUnMessageUrgent() {
        // Arrange
        String message = "Message urgent !";
        Niveau niveau = Niveau.URGENT;
        String expectedMessage = "%s, %s, %s".formatted(message.toUpperCase(), message.toUpperCase(), message.toUpperCase());

        InstrumentDeMusiqueDouble instrument = new InstrumentDeMusiqueDouble();
        Barde barde = new Assurancetourix(instrument);

        // Act
        barde.chanter(message, niveau);

        // Assert
        assertEquals(instrument.message, expectedMessage);
    }
}