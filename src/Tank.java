public class Tank {
    private int requiredResourcesFromDepartment[];
    private int reward;
    private String name;

    public Tank(int[] requiredResourcesFromDepartment, String name, int reward) {
        this.requiredResourcesFromDepartment = requiredResourcesFromDepartment;
        this.name = name;
        this.reward = reward;
    }

    public int Sell(){
        return reward;
    }


    public int getRequiredResourcesFromDepartment(Department department) {
        return requiredResourcesFromDepartment[department.getIDOfDepartment()];
    }

    public String getName() {
        return name;
    }

}
