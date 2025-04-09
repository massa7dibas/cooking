package cook_Project;

public class Substitution {
    private Ingredient original;
    private Ingredient substitute;

    public Substitution(Ingredient original, Ingredient substitute) {
        this.original = original;
        this.substitute = substitute;
    }

    public Ingredient getOriginal() {
        return original;
    }

    public Ingredient getSubstitute() {
        return substitute;
    }
}
