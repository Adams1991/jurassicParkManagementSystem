package models;

public enum RoleType {
    GAMEKEEPER("Looks after the dinosaurs"),
    SECURITYGUARD("Keeps the dinosaurs in check");

    private String job;

    RoleType(String job){
        this.job = job;
    }

    public String job() {
        return job;
    }
}
