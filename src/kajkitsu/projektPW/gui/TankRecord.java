package kajkitsu.projektPW.gui;

public class TankRecord {
    int id;
    String name;
    int serialNumber;
    int level;
    String date;
    int resources;


    public TankRecord(int id, String name, int serialNumber, int level, String date, int resources) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.level = level;
        this.date = date;
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getLevel() {
        return level;
    }

    public String getDate() {
        return date;
    }

    public int getResources() {
        return resources;
    }

    @Override
    public String toString() {
        return "TankRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber=" + serialNumber +
                ", level=" + level +
                ", date='" + date + '\'' +
                ", resources=" + resources +
                '}';
    }
}
