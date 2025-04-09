package cook_Project;

public class MealPreference {
    private boolean vegetarian;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean vegan;
    private boolean kosher;

    public MealPreference() {
    }

    public MealPreference(boolean vegetarian, boolean glutenFree, boolean dairyFree, boolean vegan, boolean kosher) {
        this.vegetarian = vegetarian;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.vegan = vegan;
        this.kosher = kosher;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isKosher() {
        return kosher;
    }

    public void setKosher(boolean kosher) {
        this.kosher = kosher;
    }
}
