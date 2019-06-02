import java.util.ArrayList;
import java.util.List;

public class Game {
    int speed;
    Money money;
    int soldTank=0;

    List<Tank> tanksList;
//    List<ProductionLine> productionList;
    protected QueueToDepartment queueToDepartment[];
    protected int IDOfDepartment = 1;
    Department[] departments;

    public Game(int speed, int money, QueueToDepartment[] queueToDepartment) {
        this.speed = speed;
        this.money = new Money(money);
        this.soldTank = 0;
        this.tanksList = new ArrayList<Tank>();
//        this.productionList = new ArrayList<ProductionLine>();
        this.queueToDepartment = queueToDepartment;
    }
    public int AssignID(){
        return IDOfDepartment++;
    }



    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public synchronized void SellTank(int addMoney){

        synchronized (money){
            while (money.isMoneyUpdating()) {
                try {
                    money.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            money.setMoneyUpdating(true);
            money.ChangeMoney(addMoney);
            soldTank++;
            money.setMoneyUpdating(false);
        }
        try {
            money.notifyAll();
        } catch (Exception e) {
            ;
        }
    }

    public void BuyUpgradeForDepartment(int ID){
        //System.out.println("!!!!!!!!");
        //System.out.println("!!!!!!!!!!!!! Upgrade ID:"+ID);
        departments[ID].Upgrade();
        //System.out.println("!!!!!!!!!!!!! FinishUpgrade ID:"+ID);
    };

    public void BuyNewLineDepartment(int ID){
        //System.out.println("!!!!!!!!");
        //System.out.println("!!!!!!!!!!!!! New ID:"+ID);
        departments[ID].AddLineToDepartment();
        //System.out.println("!!!!!!!!!!!!! FinishNew ID:"+ID);
    };






//    public synchronized void AddProductionLine(ProductionLine productionLine){
//        productionLine.setID(this.AssignID());
//        productionList.add(productionLine);
//        productionLine.start();
//    }


    public boolean TakeMoney(int takeMoney){
//        System.out.println("if");
        if(money.GetMoney()>=takeMoney) {

            synchronized (money){
                while (money.isMoneyUpdating()) {
                    try {
                        money.wait();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                money.setMoneyUpdating(true);
                money.ChangeMoney(-takeMoney);
                money.setMoneyUpdating(false);
            }
            try {
                money.notifyAll();
            } catch (Exception e) {
                ;
            }
//            System.out.println("notify");
            return true;
        }
        else {
//            System.out.println("false");
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

//    public List<ProductionLine> getProductionList() {
//        return productionList;
//    }


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
