
package cook_Project;

import java.util.*;

public class FinancialReportService {
    public static DailyFinancialReport generateDailyReport(List<Order> orders) {
        double total = 0;
        for (Order o : orders) {
            total += o.getTotalPrice();
        }
        return new DailyFinancialReport(total, orders.size());
    }
}