package ook_project;

import java.util.*;

public class SalesAnalyzer {
    public static String topPerformingProduct(Map<String, Integer> salesData) {
        return salesData.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
