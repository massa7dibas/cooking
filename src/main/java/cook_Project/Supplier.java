package cook_Project;

public class Supplier extends User {
    private String company;

    public Supplier(String id, String name, String email, String password, String phoneNumber, String company) {
        super(id, name, email, password, phoneNumber, "Supplier");
        this.company = company;
    }
}

