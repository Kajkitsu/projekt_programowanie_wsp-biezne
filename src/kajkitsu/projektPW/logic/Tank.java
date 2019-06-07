package kajkitsu.projektPW.logic;

public class Tank {
    private int[] requiredResourcesFromDepartment;
    private int reward;
    private String name;

    public Tank(int[] requiredResourcesFromDepartment, String name, int reward) {
        this.requiredResourcesFromDepartment = requiredResourcesFromDepartment;
        this.name = name;
        this.reward = reward;
    }

    public int sell() {
        return reward;
    }

    public int getRequiredResourcesFromDepartment(int dep) {
        return requiredResourcesFromDepartment[dep];
    }

    public String getName() {
        return name;
    }
}
