package kajkitsu.projektPW;

public class Game {
    private int speed;
    private Money money;
    private int soldTank = 0;
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
        synchronized (money){
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

    }

    public void BuyUpgradeForLineFrom(int IdLine, int IdDepartment) {
        System.out.println("TEST 1!");
        if (departments[IdDepartment].getNumberOfProductionLines() > IdLine) {
            System.out.println("TEST 1,5!");
            ProductionLine line = departments[IdDepartment].getLine(IdLine);
            System.out.println("TEST 2!");
            if (this.TakeMoney(line.getLevelCost()) &&
                    line.getActualLevel() < departments[IdDepartment].getMaxLevel()

            ) {
                System.out.println("TEST 3!");
                line.Upgrade();
            }
        }

    }

    public void BuyNewLineToDepartment(int IdDepartment) {
        if (this.TakeMoney(departments[IdDepartment].getNewLineCost()) &&
                departments[IdDepartment].getNumberOfProductionLines() < departments[IdDepartment].getMaxLines()) {
            departments[IdDepartment].AddNewLine();
        }
    }


    public boolean TakeMoney(int takeMoney){
        if(money.GetMoney()>=takeMoney) {

            synchronized (money){
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

    public synchronized void AddTankToGame(Tank tank){
        while (!queueToDepartment[0].GiveTankToQueue(tank)){
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


    public String Status(){
        return "Game={"+
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
