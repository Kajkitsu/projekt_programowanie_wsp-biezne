package kajkitsu.projektPW.logic;

import kajkitsu.projektPW.Game;

public class ProductionLine extends Thread {
    private int ID = 0;
    private int actualLevel;
    private int maxLevel;
    private long levelCost;
    protected Department department;
    private long levelEfficiency;
    private long actualResources;
    private boolean isBusy;
    private boolean isUpgrading;
    protected Tank tankInProductionLine;
    protected int countTanksServiced = 0;
    protected Game game;
    protected QueueToDepartment queueToThisDepartment;
    protected QueueToDepartment queueToNextDepartment;
    private double progressProduction;

    public ProductionLine(Department department, Game game, int maxLevel) {
        this.department = department;
        this.actualResources = 0;
        this.isBusy = false;
        this.game = game;
        this.tankInProductionLine = null;
        this.maxLevel = maxLevel;

        this.isUpgrading = false;
        this.actualLevel = 1;
        this.progressProduction = 0.0;
        this.levelEfficiency = (long) Math.pow(1.8, actualLevel) * 10L;
        this.levelCost = (long) Math.pow(2, actualLevel) * 1000L;
    }


    private void upgrade() {
        if (actualLevel < maxLevel) {
            actualLevel++;
            levelEfficiency = (long) Math.pow(1.8, actualLevel) * 10L;
            levelCost = (long) Math.pow(2, actualLevel) * 1000L;
        }
        this.setUpgrading(false);
    }


    private void collectingResources(Tank tank) {
        actualResources=0;
        int requiredResources = tank.getRequiredResourcesFromDepartment(department.getIDOfDepartment());
        while (actualResources < requiredResources) {
            try {
                Thread.sleep(1000 / game.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualResources += levelEfficiency;
            progressProduction = (double) actualResources / (double) requiredResources;

        }
        progressProduction = 0.0;
    }

    protected void sendTankToNextQueue(Tank tank) {
        synchronized (queueToNextDepartment){
            while (queueToNextDepartment.isBusy() || queueToNextDepartment.isFull()) {
                try {
                    queueToNextDepartment.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queueToNextDepartment.setBusy(true);
            while (!queueToNextDepartment.giveTankToQueue(tank)) ;
            queueToNextDepartment.setBusy(false);

            queueToNextDepartment.notifyAll();
        }
        this.countTanksServiced++;

    }

    private Tank takeNextTank() {
        Tank tank = null;
        synchronized (queueToThisDepartment){
            while (queueToThisDepartment.isBusy() || queueToThisDepartment.isEmpty()) {
                try {
                    queueToThisDepartment.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queueToThisDepartment.setBusy(true);
            tank = queueToThisDepartment.takeTankFromQueue();
            queueToThisDepartment.setBusy(false);

            queueToThisDepartment.notifyAll();
        }

        return tank;
    }

    protected void initQueue() {
        queueToThisDepartment = department.getQueueToThisDepartment();
        queueToNextDepartment = department.getQueueToNextDepartment();
    }

    @Override
    public void run() {
        this.initQueue();
        do {
            tankInProductionLine = null;
            synchronized (this){
                while(this.isBusy()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (this.isUpgrading()) {
                    upgrade();
                }
                synchronized (queueToThisDepartment) {
                    while (queueToThisDepartment.isEmpty() && !this.isUpgrading()) {
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
                if (!queueToThisDepartment.isEmpty()) {
                    tankInProductionLine = this.takeNextTank();
                    if (tankInProductionLine != null) {
                        this.collectingResources(tankInProductionLine);
                        this.sendTankToNextQueue(tankInProductionLine);
                    }
                }
                this.setBusy(false);
                this.notifyAll();
            }

        } while (true);
    }

    public void setUpgrading(boolean upgrading) {
        isUpgrading = upgrading;
        if (isUpgrading) {
            synchronized (queueToThisDepartment) {
                queueToThisDepartment.notifyAll();
            }
        }
    }

    public synchronized void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isUpgrading() {
        return isUpgrading;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public long getLevelCost() {
        return levelCost;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public long getLevelEfficiency() {
        return levelEfficiency;
    }

    public double getProgressProduction() {
        return progressProduction;
    }

    public Game getGame() {
        return game;
    }

    public String getStatus() {
        String tank = (tankInProductionLine != null) ? (tankInProductionLine.getName()) : (null);
        return "department=\"" + String.valueOf(department.getIDOfDepartment()) + " " + department.getName() + "\"" +
                " productionLineID=" + String.valueOf(ID) +
                " ={" +
                ", aLevel=" + actualLevel +
                ", mLevel=" + maxLevel +
                ", lCost=" + levelCost +
                ", lEfficiency=" + levelEfficiency +
                ", aResources=" + actualResources +
                ", isBusy=" + isBusy +
                ", isUpgrading=" + isUpgrading +
                ", tank=" + tank +
                ", tServiced=" + countTanksServiced +
                ", queue=" + queueToThisDepartment.getStatus() +
                ", progressProduction=" + progressProduction +
                '}';
    }

    @Override
    public String toString() {
        return "ProductionLine{" +
                "ID=" + ID +
                ", actualLevel=" + actualLevel +
                ", maxLevel=" + maxLevel +
                ", levelCost=" + levelCost +
                ", department=" + department +
                ", levelEfficiency=" + levelEfficiency +
                ", actualResources=" + actualResources +
                ", isBusy=" + isBusy +
                ", isUpgrading=" + isUpgrading +
                ", tankInProductionLine=" + tankInProductionLine +
                ", countTanksServiced=" + countTanksServiced +
                ", game=" + game +
                ", queueToThisDepartment=" + queueToThisDepartment +
                ", queueToNextDepartment=" + queueToNextDepartment +
                ", progressProduction=" + progressProduction +
                '}';
    }
}
