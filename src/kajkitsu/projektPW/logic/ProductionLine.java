package kajkitsu.projektPW.logic;

import kajkitsu.projektPW.Game;

public class ProductionLine extends Thread {
    protected int ID=0;
    protected int actualLevel;
    protected int maxLevel;
    protected long levelCost;
    protected Department department;
    protected long levelEfficiency;
    protected long actualResources;
    protected boolean isBusy;
    protected boolean isUpgrading;
    protected Tank tankInDepartment;
    protected int tanksServiced=0;
    protected Game game;
    protected QueueToDepartment queueToThisDepartment;
    protected QueueToDepartment queueToNextDepartment;
    protected double progress;

    public ProductionLine(Department department, Game game, int maxLevel) {
        this.department = department;
        this.actualResources = 0;
        this.isBusy = false;
        this.game = game;
        this.tankInDepartment = null;
        this.maxLevel = maxLevel;

        this.isUpgrading = false;
        this.actualLevel = 1;
        this.progress = 0.0;
        this.levelEfficiency = (long) Math.pow(1.8, actualLevel) * 10L;
        this.levelCost = (long) Math.pow(2, actualLevel) * 1000L;
    }


    public void upgrade() {
        if (actualLevel < maxLevel) {
            actualLevel++;
            //System.out.println("|||||||||||||||||||||||||||UpgradeLine||||||||||||||||||||||||||||||||||");
            levelEfficiency = (long) Math.pow(1.8, actualLevel) * 10L;
            levelCost = (long) Math.pow(2, actualLevel) * 1000L;
        }
        this.setUpgrading(false);
    }




    protected void CollectingResources(Tank tank) {
        //System.out.println(this.toString());
        actualResources=0;
        int requiredResources = tank.getRequiredResourcesFromDepartment(department.getIDOfDepartment());
        while (actualResources < requiredResources) {
//            System.out.println("REQ RES: "+requiredResources);
            try {
                Thread.sleep(1000 / game.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualResources += levelEfficiency;
            progress = (double) actualResources / (double) requiredResources;
//            System.out.println("actualResources:"+actualResources);
//            System.out.println("Progress: "+(double)actualResources/(double)requiredResources);

        }
        progress = 0.0;
    }

    protected void SendTankToNextQueue(Tank tank){
        synchronized (queueToNextDepartment){
            while (queueToNextDepartment.isBusy() || queueToNextDepartment.IsFull()) {
                try {
                    queueToNextDepartment.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queueToNextDepartment.setBusy(true);
            while (!queueToNextDepartment.GiveTankToQueue(tank)) ;
            queueToNextDepartment.setBusy(false);

            queueToNextDepartment.notifyAll();
        }
        this.tanksServiced++;

    }

    protected Tank TakeNextTank(){
        Tank tank = null;
        synchronized (queueToThisDepartment){
            while (queueToThisDepartment.isBusy() || queueToThisDepartment.IsEmpty()) {
                try {
                    queueToThisDepartment.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queueToThisDepartment.setBusy(true);
            tank=queueToThisDepartment.TakeTankFromQueue();
            queueToThisDepartment.setBusy(false);

            queueToThisDepartment.notifyAll();
        }

        return tank;
    }

    protected void Init(){
        queueToThisDepartment = department.getQueueToThisDepartment();
        queueToNextDepartment = department.getQueueToNextDepartment();
        //System.out.println(this.toString());
    }

    @Override
    public void run() {
        this.Init();
        do {
            tankInDepartment=null;
            synchronized (this){
                while(this.isBusy()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //System.out.println("isUpgrading" + isUpgrading());
                if (this.isUpgrading()) {
                    upgrade();
                }
                synchronized (queueToThisDepartment) {
                    while (queueToThisDepartment.IsEmpty() && !this.isUpgrading()) {
                        try {
                            queueToThisDepartment.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.isUpgrading()) {
                            upgrade();
                        }
                    }
                }

                this.setBusy(true);
                if (!queueToThisDepartment.IsEmpty()) {
                    tankInDepartment = this.TakeNextTank();
                    if (tankInDepartment != null) {
                        this.CollectingResources(tankInDepartment);
                        this.SendTankToNextQueue(tankInDepartment);
                    }
                }
                this.setBusy(false);
                this.notifyAll();
            }

        } while (true);
    }

    public long getLevelCost() {
        return levelCost;
    }


    public void setID(int ID) {
        this.ID=ID;
    }

    public int getID() {
        return ID;
    }

    public boolean isUpgrading() {
        return isUpgrading;
    }

    public void setUpgrading(boolean upgrading) {
        isUpgrading = upgrading;
        if (isUpgrading) {
            synchronized (queueToThisDepartment) {
                queueToThisDepartment.notifyAll();
            }
        }
    }

    public boolean isBusy() {
        return isBusy;
    }

    public synchronized void setBusy(boolean busy) {
        isBusy = busy;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public Department getDepartmentNumber() {
        return department;
    }


    public long getLevelEfficiency() {
        return levelEfficiency;
    }

    public long getActualResources() {
        return actualResources;
    }

    public double getProgress() {
        return progress;
    }

    public Game getGame() {
        return game;
    }


    public Tank getTankInDepartment() {
        return tankInDepartment;
    }

    public int getTanksServiced() {
        return tanksServiced;
    }

    public String Status(){
        return "ProductionLine: ID "+ String.valueOf(department.getIDOfDepartment())+"  "+ department.getName() + String.valueOf(ID)+"={"+
                ", aLevel=" + actualLevel +
                ", efficiency=" + levelEfficiency +
                ", levelCost=" + levelCost +
                ", lineCost=" + department.getNewLineCost() +
                ", iBusy=" + isBusy +
                ", tank=" + tankInDepartment +
                ", tServiced=" + tanksServiced +
                ",numberOfProductionLines= " + department.getNumberOfProductionLines() +
                ", " + queueToThisDepartment.Status() +
                "}";

    }


    @Override
    public String toString() {
        return "ProductionLine{" +
                "ID=" + ID +
                ", actualLevel=" + actualLevel +
                ", levelEfficiency=" + levelEfficiency +
                ", actualResources=" + actualResources +
                ", isBusy=" + isBusy +
                ", game=" + game +
                ", queueToThisDepartment=" + queueToThisDepartment +
                ", queueToNextDepartment=" + queueToNextDepartment +
                ", tankInDepartment=" + tankInDepartment +
                ", tanksServiced=" + tanksServiced +
                '}';
    }
}