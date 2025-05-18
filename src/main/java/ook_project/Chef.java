package ook_project;

public class Chef extends User {

    private final int experienceLevel;
    private final int currentLoad;
    public Chef(String id, String name, String email, String password, String phoneNumber, int experienceLevel, int currentLoad) {
        super(id, name, email, password, phoneNumber, "Chef");
        this .currentLoad=currentLoad;
        this.experienceLevel=experienceLevel;
    }

    public Chef(String chefId, String name, int experienceLevel, int currentLoad) {
        super(chefId,name,"","","","Chef");
        this.currentLoad=currentLoad;
        this.experienceLevel=experienceLevel;
    }

    public int getExperienceLevel(){
        return experienceLevel;
    }
    public int getincrementLoad(){
        return currentLoad;
    }
}
