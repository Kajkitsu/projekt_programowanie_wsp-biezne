package kajkitsu.projektPW.logic;

import java.util.ArrayList;
import java.util.List;

public class QueueToDepartment {
    private int maxTanksInQueue;
    private List<Tank> tanksList;
    private boolean isBusy = false;
    private boolean isEmpty = true;
    private boolean isFull = false;


    public QueueToDepartment(int maxTanksInQueue) {
        this.maxTanksInQueue = maxTanksInQueue;
        this.tanksList = new ArrayList<Tank>();
    }

    public boolean giveTankToQueue(Tank tank) {
        if(maxTanksInQueue>tanksList.size()){
            synchronized (this){
                tanksList.add(tank);
                isEmpty = false;
                if (maxTanksInQueue == tanksList.size()) isFull = true;

                this.notifyAll();
            }
            return true;
        } else return false;
    }

    public Tank takeTankFromQueue() {
        if(tanksList.size()>0){
            Tank tank = null;
            synchronized (this){
                tank = tanksList.remove(0);
                isFull = false;
                if (tanksList.size() == 0) isEmpty = true;
                this.notifyAll();
            }
            return tank;
        } else return null;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public int getQueueSize() {
        return tanksList.size();
    }

    public String getStatus() {
        return " Queue={" +
                "tanksList.size= " + tanksList.size() +
                ", isEmpty=" + isEmpty +
                ", isFull=" + isFull +
                ", maxTanksInQueue= " + maxTanksInQueue +
                ", isBusy= " + isBusy +
                "}";

    }

    @Override
    public String toString() {
        return "QueueToDepartment{" +
                "maxTanksInQueue=" + maxTanksInQueue +
                ", tanksList=" + tanksList +
                ", isBusy=" + isBusy +
                ", isEmpty=" + isEmpty +
                ", isFull=" + isFull +
                '}';
    }
}
