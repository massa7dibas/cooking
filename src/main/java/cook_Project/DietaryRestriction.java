package cook_Project;

import java.util.List;
import java.util.stream.Collectors;

public enum DietaryRestriction {
    VEGAN("Vegan"),
    GLUTEN_FREE("Gluten-Free"),
    DAIRY_FREE("Dairy-Free"),
    NUT_FREE("Nut-Free");

    private final String tag;

    DietaryRestriction(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public static List<String> tagsOf(List<DietaryRestriction> restrictions) {
        return restrictions.stream().map(DietaryRestriction::getTag).collect(Collectors.toList());
    }
}
