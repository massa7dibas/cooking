package ook_project;
public class User {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected String role;

    public User(String id, String name, String email, String password, String phoneNumber, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }



    public boolean signIn(String enteredEmail, String enteredPassword) {
        if (this.email.equals(enteredEmail) && this.password.equals(enteredPassword)) {
            System.out.println(role + " " + name + " has signed in.");
            return true;
        } else {
            System.out.println("Incorrect email or password.");
            return false;
        }
    }

    public void editProfile(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println(role + " profile updated.");
    }
public String getId(){
        return id;
}
    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

}
