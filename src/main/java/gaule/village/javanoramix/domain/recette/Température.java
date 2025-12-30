package gaule.village.javanoramix.domain.recette;

public record Température(
        int degré,
        UnitéTempérature unité) {

    public static final Température ZERO_CELCIUS = new Température(0, UnitéTempérature.CELCIUS);
}
