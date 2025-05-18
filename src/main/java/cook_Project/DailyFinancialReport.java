package cook_Project;

public class DailyFinancialReport {
    private final double totalRevenue;
    private final int numberOfOrders;
    private final double averageOrderValue;

    public DailyFinancialReport(double totalRevenue, int numberOfOrders) {
        this.totalRevenue = totalRevenue;
        this.numberOfOrders = numberOfOrders;
        this.averageOrderValue = numberOfOrders > 0 ? totalRevenue / numberOfOrders : 0;
    }

    public double getTotalRevenue() { return totalRevenue; }
    public int getNumberOfOrders() { return numberOfOrders; }
    public double getAverageOrderValue() { return averageOrderValue; }
}
