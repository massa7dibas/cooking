package cook_Project;

public class Chef extends User {
    private String specialty;

    public Chef(String id, String name, String email, String password, String phoneNumber, String specialty) {
        super(id, name, email, password, phoneNumber, "Chef");
        this.specialty = specialty;
    }
}
