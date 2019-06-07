package kajkitsu.projektPW;

import kajkitsu.projektPW.logic.*;

import java.util.Arrays;

public class Game {
    private int speed;
    private Money money;
    private int countTanksSold;
    private int levelGame;
    private QueueToDepartment[] queueToDepartments;
    private Department[] departments;

    public Game(int speed, int money, QueueToDepartment[] queueToDepartments) {
        this.speed = speed;
        this.money = new Money(money);
        this.countTanksSold = 0;
        this.levelGame = 0;
        this.queueToDepartments = queueToDepartments;

        new AutoTankAdder(this);
    }

    public synchronized void addTankToGame(Tank tank) {
        while (!queueToDepartments[0].giveTankToQueue(tank)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void buyUpgradeForProductionLine(int dep, int line) {

        if (departments[dep].getLine(line) != null) {
            ProductionLine productionLine = departments[dep].getLine(line);
            //System.out.println("TEST 1!");
            if (this.takeMoney(productionLine.getLevelCost()) &&
                    productionLine.getActualLevel() < departments[dep].getMaxLevel() &&
                    !productionLine.isUpgrading()
            ) {
                departments[dep].sendSigToUpgrade(line);
                //System.out.println("TEST 3!");
            }
        }
    }

    public boolean buyNewProductionLine(int dep) {
        if (this.takeMoney(departments[dep].getNewLineCost()) &&
                departments[dep].getNumberOfProductionLines() < departments[dep].getMaxLines()) {
            departments[dep].addNewProductionLine();
            return true;
        } else return false;
    }

    private boolean takeMoney(long takeMoney) {
        if (money.getMoney() >= takeMoney) {

            synchronized (money) {
                while (money.isMoneyUpdating()) {
                    try {
                        money.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                money.setMoneyUpdating(true);
                money.changeMoney(-takeMoney);
                money.setMoneyUpdating(false);
                money.notifyAll();
            }
            return true;
        } else {
            return false;
        }
    }

    public void sellTank(int addMoney) {
        synchronized (money) {
            while (money.isMoneyUpdating()) {
                try {
                    money.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            money.setMoneyUpdating(true);
            money.changeMoney(addMoney);
            countTanksSold++;
            money.setMoneyUpdating(false);

            money.notifyAll();
        }

        levelGame = (countTanksSold / 100) + 1;

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }

    public boolean isOnMaxLevel(int dep, int line) {
        return departments[dep].getMaxLevel() == departments[dep].getLine(line).getActualLevel();
    }

    public long getUpgradeLineCost(int dep, int line) {
        return departments[dep].getLine(line).getLevelCost();
    }

    public double getLineProgress(int dep, int line) {
        return departments[dep].getLine(line).getProgressProduction();

    }

    public int getLineLevel(int dep, int line) {
        if (departments[dep].getLine(line) != null) {
            return departments[dep].getLine(line).getActualLevel();
        }
        return 0;
    }

    public long getLineEfficiency(int dep, int line) {
        if (departments[dep].getLine(line) != null) {
            return departments[dep].getLine(line).getLevelEfficiency();
        }
        return 0;
    }

    public long getDepartmentEfficiency(int dep) {
        long sum = 0;
        for (int line = 0; line < 7; line++) {
            sum += this.getLineEfficiency(dep, line);
        }
        return sum;
    }

    public boolean getIsLineUpgrading(int dep, int line) {
        return departments[dep].getLine(line).isUpgrading();
    }

    public long getMoney() {
        return money.getMoney();
    }

    public int getCountTanksSold() {
        return countTanksSold;
    }

    public int getQueueToDepartment(int dep) {
        return departments[dep].getQueueToThisDepartment().getQueueSize();
    }

    public long getNewLineCost(int dep) {
        return departments[dep].getNewLineCost();
    }

    public int getLevelGame() {
        return levelGame;
    }

    public int getRequiresForNewTanks(int dep) {
        Tank tank = new Tank(this.levelGame, 0, "test");

        return tank.getRequiredResourcesFromDepartment(dep);
    }

    public Tank getNewTank(int ID) {
        return new Tank(this.levelGame, ID, "Czolg JELEN");
    }

    public int getSpeed() {
        return speed;
    }

    public Department[] getDepartments() {
        return departments;
    }


    public String getStatus() {
        return "Game{" +
                "speed=" + speed +
                ", money=" + Money.getStringFromLong(money.getMoney()) +
                ", countTanksSold=" + countTanksSold +
                ", levelGame=" + levelGame +
                "}";

    }

    @Override
    public String toString() {
        return "Game{" +
                "speed=" + speed +
                ", money=" + money +
                ", countTanksSold=" + countTanksSold +
                ", levelGame=" + levelGame +
                ", queueToDepartments=" + Arrays.toString(queueToDepartments) +
                ", departments=" + Arrays.toString(departments) +
                '}';
    }
}
