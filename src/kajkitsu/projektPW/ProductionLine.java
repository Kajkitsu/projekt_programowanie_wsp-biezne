package kajkitsu.projektPW;

public class ProductionLine extends Thread {
    protected int ID=0;
    protected int actualLevel;
    protected int maxLevel;
    protected int levelCost;
    protected Department department;
    protected int levelEfficiency;
    protected int actualResources;
    protected boolean isBusy;
    protected Tank tankInDepartment;
    protected int tanksServiced=0;
    protected Game game;
    protected QueueToDepartment queueToThisDepartment;
    protected QueueToDepartment queueToNextDepartment;

    public ProductionLine(Department department, Game game, int maxLevel) {
        this.department = department;
        this.actualResources = 0;
        this.isBusy = false;
        this.game = game;
        this.tankInDepartment = null;
        this.maxLevel = maxLevel;

        this.actualLevel = 0;
        this.levelEfficiency = (int) Math.pow(2, actualLevel) * 10;
        this.levelCost = (int) Math.pow(4, actualLevel) * 10000;
    }


    public boolean Upgrade() {
        System.out.println("TEST 5!");
        synchronized (this){
            while(this.isBusy()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.setBusy(true);
            System.out.println("TEST 6!");

            if (actualLevel < maxLevel) {
                actualLevel++;
                //System.out.println("|||||||||||||||||||||||||||UpgradeLine||||||||||||||||||||||||||||||||||");
                levelEfficiency = (int) Math.pow(2, actualLevel) * 10;
                levelCost = (int) Math.pow(4, actualLevel) * 10000;
            }

            this.setBusy(false);

            this.notifyAll();
        }

        return true;
    }

    protected void CollectingResources(Tank tank) {
        //System.out.println(this.toString());
        actualResources=0;
        while (actualResources<tank.getRequiredResourcesFromDepartment(department)){
            try {
                Thread.sleep(100/game.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualResources+=levelEfficiency;
        }
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
                this.setBusy(true);
                tankInDepartment = this.TakeNextTank();
                if(tankInDepartment!=null){
                    this.CollectingResources(tankInDepartment);
                    this.SendTankToNextQueue(tankInDepartment);
                }
                this.setBusy(false);

                this.notifyAll();
            }

        } while (true);
    }

    public int getLevelCost() {
        return levelCost;
    }


    public void setID(int ID) {
        this.ID=ID;
    }

    public int getID() {
        return ID;
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


    public int getLevelEfficiency() {
        return levelEfficiency;
    }

    public int getActualResources() {
        return actualResources;
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
