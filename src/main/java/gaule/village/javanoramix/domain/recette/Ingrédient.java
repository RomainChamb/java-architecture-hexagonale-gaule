package gaule.village.javanoramix.domain.recette;

public record Ingrédient(
        String nom,
        int quantité) {
    public static Ingrédient INGRÉDIENT_VIDE = new Ingrédient("", 0);
}
