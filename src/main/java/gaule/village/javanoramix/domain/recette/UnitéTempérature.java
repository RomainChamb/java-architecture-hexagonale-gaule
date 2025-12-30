package gaule.village.javanoramix.domain.recette;

// utile ?
public enum UnitéTempérature {
    CELCIUS("°C"),
    FAHRENHEIT("°F");

    public final String label;

    private UnitéTempérature(String label) {
        this.label = label;
    }
}
