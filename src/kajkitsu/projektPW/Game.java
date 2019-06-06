package kajkitsu.projektPW;

public class Game {
    private int speed;
    private Money money;
    private int soldTank = 0;
    private int levelGame = 0;
    private QueueToDepartment queueToDepartment[];
    private Department[] departments;

    public Game(int speed, int money, QueueToDepartment[] queueToDepartment) {
        this.speed = speed;
        this.money = new Money(money);
        this.soldTank = 0;
        this.queueToDepartment = queueToDepartment;
    }

    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {

        this.speed = speed;

    }

    public void SellTank(int addMoney) {
        synchronized (money) {
            while (money.isMoneyUpdating()) {
                try {
                    money.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            money.setMoneyUpdating(true);
            money.ChangeMoney(addMoney);
            soldTank++;
            money.setMoneyUpdating(false);

            money.notifyAll();
        }

        levelGame = (soldTank / 100) + 1;

    }

    public long getUpgradeLineCost(int dep, int line) {
        return departments[dep].getLine(line).getLevelCost();
    }

    public double getLineProgress(int dep, int line) {
        return departments[dep].getLine(line).getProgress();

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
        return money.GetMoney();
    }

    public int getSoldTank() {
        return soldTank;
    }

    public int getQueueToDepartment(int dep) {
        return departments[dep].getQueueToThisDepartment().getQueueSize();
    }

    public long getNewLineCost(int dep) {
        return departments[dep].getNewLineCost();
    }

    public boolean isOnMaxLevel(int dep, int line) {
        return departments[dep].getMaxLevel() == departments[dep].getLine(line).getActualLevel();
    }

    public void BuyUpgradeForLineFrom(int dep, int line) {

        if (departments[dep].getLine(line) != null) {
            ProductionLine productionLine = departments[dep].getLine(line);
            System.out.println("TEST 1!");
            if (this.TakeMoney(productionLine.getLevelCost()) &&
                    productionLine.getActualLevel() < departments[dep].getMaxLevel() &&
                    !productionLine.isUpgrading()
            ) {
                departments[dep].sendSigToUpgrade(line);
                System.out.println("TEST 3!");
            }


        }

    }

    public int getLevelGame() {
        return levelGame;
    }

    public boolean BuyNewLineToDepartment(int IdDepartment) {
        if (this.TakeMoney(departments[IdDepartment].getNewLineCost()) &&
                departments[IdDepartment].getNumberOfProductionLines() < departments[IdDepartment].getMaxLines()) {
            departments[IdDepartment].AddNewLine();
            return true;
        } else return false;
    }


    public boolean TakeMoney(long takeMoney) {
        if (money.GetMoney() >= takeMoney) {

            synchronized (money) {
                while (money.isMoneyUpdating()) {
                    try {
                        money.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                money.setMoneyUpdating(true);
                money.ChangeMoney(-takeMoney);
                money.setMoneyUpdating(false);

                money.notifyAll();
            }
            return true;
        } else {
            return false;
        }

    }

    public Tank getNewTank(int ID) {
        int level = this.getLevelGame();
        int[] req = new int[]{level * 100, level * 100, level * 100, level * 100, level * 100, level * 100, level * 100};

        req[level % 7] *= 3;
        req[(level + 3) % 7] *= 2;
        req[(level + 6) % 7] *= 4;

        return new Tank(req, "Tank l" + level + " Sn:" + ID, (int) Math.pow(10, level));

    }

    public synchronized void AddTankToGame(Tank tank) {
        while (!queueToDepartment[0].GiveTankToQueue(tank)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public Department[] getDepartments() {
        return departments;
    }

    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }


    public String Status() {
        return "Game={" +
                " speed=" + speed +
                ", money=" + money.GetMoney() +
                ", soldTank=" + soldTank +
                "}";

    }

    @Override
    public String toString() {
        return "Game{" +
                "speed=" + speed +
                ", money=" + money +
                ", soldTank=" + soldTank +
                '}';
    }
}
