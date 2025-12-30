package gaule.village.javanoramix.domain.adapter;

import gaule.village.javanoramix.domain.druide.cuiseur.AppareilDeCuissonPort;
import gaule.village.javanoramix.domain.druide.cuiseur.CuiseurNonPréchaufféException;
import gaule.village.javanoramix.domain.recette.Ingrédient;
import gaule.village.javanoramix.domain.recette.Température;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CuiseurDouble implements AppareilDeCuissonPort {
    private Ingrédient ingrédientEnCuisson = Ingrédient.INGRÉDIENT_VIDE;
    private Température températureActuelle = Température.ZERO_CELCIUS;
    private Température températureSouhaitée = Température.ZERO_CELCIUS;
    private final ExecutorService executeur = Executors.newSingleThreadExecutor();

    @Override
    public void préchauffer(Température températureSouhaitée) {
        this.températureSouhaitée = températureSouhaitée;
        executeur.submit(() -> {
            while (this.températureActuelle.degré() < températureSouhaitée.degré()) {
                try {
                    Thread.sleep(300);
                    setTempératureActuelle(new Température(this.températureActuelle.degré() + 10, températureActuelle.unité()));
                } catch (InterruptedException _) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    @Override
    public synchronized Température vérifierTempérature() {
        return températureActuelle;
    }

    public synchronized void setTempératureActuelle(Température nouvelleTempérature) {
        this.températureActuelle = nouvelleTempérature;
    }

    @Override
    public void cuire(Ingrédient ingrédientÀCuire, String nomIngrédientObtenu) {
        if (this.vérifierTempérature().degré() != this.températureSouhaitée.degré()) {
            throw new CuiseurNonPréchaufféException();
        }
        this.ingrédientEnCuisson = new Ingrédient(nomIngrédientObtenu, ingrédientÀCuire.quantité());
    }

    @Override
    public Ingrédient prélever() {
        return this.ingrédientEnCuisson;
    }
}
