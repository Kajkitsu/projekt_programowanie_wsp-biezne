package kajkitsu.projektPW.logic;

import kajkitsu.projektPW.MySQL;

public class Tank {
    private int[] requiredResourcesFromDepartment;
    private int reward;
    private String name;
    private int level;
    private int serialNumber;

    public Tank(int level, int sn, String name) {
        this.name = name;
        this.level = level;
        this.serialNumber = sn;
        this.reward = (level + 1) * 100;
        this.requiredResourcesFromDepartment = new int[]{(level + 1) * 100, (level + 1) * 100, (level + 1) * 100, (level + 1) * 100, (level + 1) * 100, (level + 1) * 100, (level + 1) * 100};
        requiredResourcesFromDepartment[(level + 1) % 7] *= 3;
        requiredResourcesFromDepartment[((level + 1) + 3) % 7] *= 2;
        requiredResourcesFromDepartment[((level + 1) + 6) % 7] *= 4;


    }

//    public Tank(int[] requiredResourcesFromDepartment, String name, int reward) {
//        this.requiredResourcesFromDepartment = requiredResourcesFromDepartment;
//        this.name = name;
//        this.reward = reward;
//    }

    public int sell(int id) {
        MySQL.insertTankToMySQL(id, this.getName(), this.getSerialNumber(), this.getLevel(), this.getSumOfRequiredResourcesFromDepartments());
        return reward;
    }

    public int getRequiredResourcesFromDepartment(int dep) {
        return requiredResourcesFromDepartment[dep];
    }

    public String getNameAndSerial() {
        return name + " SN:" + serialNumber;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getSumOfRequiredResourcesFromDepartments() {
        int sum = 0;
        for (int i = 0; i < 7; i++)
            sum += requiredResourcesFromDepartment[i];
        return sum;
    }
}
