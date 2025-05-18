package cook_Project;

public class Admin extends User {
    public Admin(String id, String name, String email, String password, String phoneNumber) {
        super(id, name, email, password, phoneNumber, "Admin");
    }
}

