package test_Package;

import cook_Project.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import java.util.*;

public class BillingSystemSteps {
    private Order order;
    private Invoice invoice;
    private boolean emailSent;
    private DailyFinancialReport report;
    private String topProduct;

    @Given("a customer named {string} placed an order with the following items:")
    public void a_customer_named_placed_an_order_with_the_following_items(String name, DataTable dataTable) {
        List<FoodItem> items = new ArrayList<>();
        double total = 0;
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String itemName = row.get("Item");
            int qty = Integer.parseInt(row.get("Quantity"));
            double price = Double.parseDouble(row.get("Unit Price"));
            for (int i = 0; i < qty; i++) {
                items.add(new FoodItem(UUID.randomUUID().toString(), itemName, Collections.emptyList(), price));
            }
            total += qty * price;
        }
        order = new Order(UUID.randomUUID().toString(), new Date(), items, total, "Completed");
    }

    @When("the order is completed")
    public void the_order_is_completed() {
        invoice = InvoiceGenerator.generateInvoice(order);
    }

    @Then("an invoice should be generated with:")
    public void an_invoice_should_be_generated_with(DataTable dataTable) {
        List<InvoiceItem> invoiceItems = invoice.getItems();
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        assertEquals(expected.size(), invoiceItems.size());
        for (int i = 0; i < expected.size(); i++) {
            Map<String, String> row = expected.get(i);
            InvoiceItem ii = invoiceItems.get(i);
            assertEquals(row.get("Item"), ii.getName());
            assertEquals(Integer.parseInt(row.get("Quantity")), ii.getQuantity());
            assertEquals(Double.parseDouble(row.get("Unit Price")), ii.getUnitPrice(), 0.001);
            assertEquals(Double.parseDouble(row.get("Total")), ii.getTotal(), 0.001);
        }
    }

    @Then("the invoice total should be {int}")
    public void the_invoice_total_should_be(Integer expectedTotal) {
        assertEquals(expectedTotal.doubleValue(), invoice.getTotal(), 0.001);
    }

    @Then("the invoice status should be {string}")
    public void the_invoice_status_should_be(String expectedStatus) {
        assertEquals(expectedStatus, invoice.getStatus());
    }

    @Given("an invoice has been generated for customer {string}")
    public void an_invoice_has_been_generated_for_customer(String customer) {
        invoice = new Invoice(UUID.randomUUID().toString(), Collections.emptyList());
    }

    @When("the system sends the invoice via email")
    public void the_system_sends_the_invoice_via_email() {
        EmailService.sendInvoice(invoice, "ahmad@example.com");
        emailSent = true;
    }

    @Then("the customer should receive the invoice at {string}")
    public void the_customer_should_receive_the_invoice_at(String email) {
        assertTrue(emailSent);
    }

    @Given("the following orders were completed today:")
    public void the_following_orders_were_completed_today(DataTable dataTable) {
        List<Order> orders = new ArrayList<>();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            double total = Double.parseDouble(row.get("Total"));
            orders.add(new Order(UUID.randomUUID().toString(), new Date(), Collections.emptyList(), total, "Completed"));
        }
        report = FinancialReportService.generateDailyReport(orders);
    }

    @When("the system generates the daily report")
    public void the_system_generates_the_daily_report() {

    }

    @Then("the report should show:")
    public void the_report_should_show(DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        assertEquals(Double.parseDouble(row.get("Total Revenue")), report.getTotalRevenue(), 0.001);
        assertEquals(Integer.parseInt(row.get("Number of Orders")), report.getNumberOfOrders());
        assertEquals(Double.parseDouble(row.get("Average Order Value")), report.getAverageOrderValue(), 0.001);
    }

    @Given("the following product sales data:")
    public void the_following_product_sales_data(DataTable dataTable) {
        Map<String, Integer> salesData = new HashMap<>();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            salesData.put(row.get("Product"), Integer.parseInt(row.get("Quantity Sold")));
        }
        topProduct = SalesAnalyzer.topPerformingProduct(salesData);
    }

    @When("the system analyzes sales performance")
    public void the_system_analyzes_sales_performance() {

    }

    @Then("the top-performing product should be {string}")
    public void the_top_performing_product_should_be(String expected) {
        assertEquals(expected, topProduct);
    }
}
