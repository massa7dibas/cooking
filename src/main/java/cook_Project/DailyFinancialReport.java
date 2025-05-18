package cook_Project;

public class DailyFinancialReport {
    private double totalRevenue;
    private int numberOfOrders;
    private double averageOrderValue;

    public DailyFinancialReport(double totalRevenue, int numberOfOrders) {
        this.totalRevenue = totalRevenue;
        this.numberOfOrders = numberOfOrders;
        this.averageOrderValue = numberOfOrders > 0 ? totalRevenue / numberOfOrders : 0;
    }

    public double getTotalRevenue() { return totalRevenue; }
    public int getNumberOfOrders() { return numberOfOrders; }
    public double getAverageOrderValue() { return averageOrderValue; }
}
