package kajkitsu.projektPW;

import java.util.ArrayList;
import java.util.List;

public class QueueToDepartment {
    int maxTanksInQueue;
    List<Tank> tanksList;
    boolean isBusy=false;
    boolean isEmpty = true;
    boolean isFull = false;


    public QueueToDepartment(int maxTanksInQueue) {
        this.maxTanksInQueue = maxTanksInQueue;
        this.tanksList = new ArrayList<Tank>();
    }

    public boolean GiveTankToQueue(Tank tank){
        if(maxTanksInQueue>tanksList.size()){
            synchronized (this){
                tanksList.add(tank);
                if (isEmpty) isEmpty = false;
                if (maxTanksInQueue == tanksList.size()) isFull = true;

                this.notifyAll();
            }
            return true;
        } else return false;
    }

    public Tank TakeTankFromQueue(){
        if(tanksList.size()>0){
            Tank tank;
            synchronized (this){
                tank = tanksList.remove(0);
                if (isFull) isFull = false;
                if (tanksList.size() == 0) isEmpty = true;
                this.notifyAll();
            }
            return tank;
        } else return null;
    }


//    public synchronized boolean GiveTankToQueue(Tank tank){
//        if(maxTanksInQueue>tanksList.size()){
//            tanksList.add(tank);
//
//            return true;
//        }
//        else return false;
//    }
//
//    public synchronized Tank TakeTankFromQueue(){
//        if(tanksList.size()>0){
//            Tank tank = tanksList.remove(0);
//            return tank;
//        }
//        else return null;
//    }

    public boolean IsEmpty(){
        return isEmpty;
    }

    public boolean IsFull(){
        return isFull;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public int getQueueSize() {
        return tanksList.size();
    }

    public String Status(){
        return " Queue={" +
                "maxTanksInQueue= " + maxTanksInQueue +
                "isBusy= " + isBusy +
                ", tanksList.size= " + tanksList.size() +
                "}";

    }

    @Override
    public String toString() {
        return "QueueToDepartment{" +
                "maxTanksInQueue=" + maxTanksInQueue +
                ", tanksList=" + tanksList +
                '}';
    }

}
