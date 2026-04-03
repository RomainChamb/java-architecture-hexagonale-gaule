package gaule.village.javanoramix.infrastructure;

import gaule.village.javanoramix.domain.barde.instrument.InstrumentDeMusique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Harpe implements InstrumentDeMusique {

    private static final Logger logger = LoggerFactory.getLogger(Harpe.class.getName());

    @Override
    public void jouer(String message) {
        logger.info(message);
    }
}
