public class ProductionLine extends Thread {
    protected int ID=0;
    protected int actualLevel;
    protected Department department;
    protected int levelEfficiency;
    protected int actualResources;
    protected boolean isBusy;
    protected Tank tankInDepartment;
    protected int tanksServiced=0;
    protected Game game;
    protected QueueToDepartment queueToThisDepartment;
    protected QueueToDepartment queueToNextDepartment;

    public ProductionLine(int actualLevel, Department department, Game game) {
        this.actualLevel = actualLevel;
        this.levelEfficiency=(int)Math.pow(10, actualLevel)*10;
        this.department = department;
        this.actualResources = 0;
        this.isBusy = false;
        this.game = game;
        this.tankInDepartment = null;
    }

//    public ProductionLine(ProductionLine productionLine) {
//        this.actualLevel = productionLine.getActualLevel();
//        this.departmentNumber = productionLine.getDepartmentNumber();
//        this.levelCost = productionLine.getLevelCost();
//        this.levelEfficiency = productionLine.getLevelEfficiency();
//        this.actualResources = 0;
//        this.maxLevel = productionLine.getMaxLevel();
//        this.isBusy = false;
//        this.name = productionLine.getNameDepartment();
//        this.game = productionLine.getGame();
//        this.tankInDepartment = null;
//        this.ID=game.AssignID();
//    }

    public ProductionLine(){

    }


    public synchronized boolean Upgrade(int level){
            synchronized (this){
                while(this.isBusy()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.setBusy(true);
                actualLevel=level;
                //System.out.println("|||||||||||||||||||||||||||Upgrade||||||||||||||||||||||||||||||||||");
                levelEfficiency=(int)Math.pow(10, level)*10;
                this.setBusy(false);
            }

            try {
                this.notifyAll();
            } catch (Exception e) {
                ;
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
            while (queueToNextDepartment.isBusy()){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queueToNextDepartment.setBusy(true);
            while(!queueToNextDepartment.GiveTankToQueue(tank));
            this.tanksServiced++;
            queueToNextDepartment.setBusy(false);
        }
        try {
            queueToNextDepartment.notifyAll();
        } catch (Exception e) {
            ;
        }

    }

    protected Tank TakeNextTank(){
        Tank tank = null;
        synchronized (queueToThisDepartment){
            while (queueToThisDepartment.isBusy()){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queueToThisDepartment.setBusy(true);
            tank=queueToThisDepartment.TakeTankFromQueue();
            queueToThisDepartment.setBusy(false);
        }
        try {
            queueToThisDepartment.notifyAll();
        } catch (Exception e) {
            ;
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
            }
            try {
                this.notifyAll();
            } catch (Exception e) {
                ;
            }


        } while (true);
    }

    public void setID(int ID) {
        this.ID=ID;
    }

    public int getID() {
        return ID;
    }

    public synchronized boolean isBusy() {
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
                " aLevel=" + actualLevel +
                ", iBusy=" + isBusy +
                ", tank=" + tankInDepartment +
                ", tServiced=" + tanksServiced +
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
