import java.util.ArrayList;
import java.util.List;

public class QueueToDepartment {
    int maxTanksInQueue;
    List<Tank> tanksList;
    boolean isBusy=false;


    public QueueToDepartment(int maxTanksInQueue) {
        this.maxTanksInQueue = maxTanksInQueue;
        this.tanksList = new ArrayList<Tank>();
    }

    public boolean GiveTankToQueue(Tank tank){
        if(maxTanksInQueue>tanksList.size()){
            synchronized (this){
                tanksList.add(tank);
            }
            try {
                this.notifyAll();
            } catch (Exception e) {
                ;
            }
            //System.out.println(this.toString());
            return true;
        }
        else return false;
    }

    public Tank TakeTankFromQueue(){
        if(tanksList.size()>0){
            Tank tank;
            synchronized (this){
                tank = tanksList.remove(0);
            }
            try {
                this.notifyAll();
            } catch (Exception e) {
                ;
            }
            return tank;

        }
        else return null;
    }

    public boolean IsEmpty(){
        return this.tanksList.size()==0;
    }

    public boolean IsFull(){
        return this.tanksList.size()==this.maxTanksInQueue;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public String Status(){
        return "QueueToDepartment={"+
                " maxTanksInQueue=" + maxTanksInQueue +
                ", tanksList.size()=" + tanksList.size()+"}";

    }

    @Override
    public String toString() {
        return "QueueToDepartment{" +
                "maxTanksInQueue=" + maxTanksInQueue +
                ", tanksList=" + tanksList +
                '}';
    }

}
