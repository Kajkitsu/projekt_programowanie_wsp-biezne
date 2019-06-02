public class SellDepartment extends Department {

    public SellDepartment(int actualLevel, int departmentNumber, int newDepartamentCost, int levelCost, int levelEfficiency, int maxLevel, String name, Game game) {
        super(actualLevel, departmentNumber, newDepartamentCost, levelCost, levelEfficiency, maxLevel, name, game);
    }

    @Override
    protected void Service(Tank tank){
        tank.setInDepartment(true);
        //System.out.println(this.toString());
        this.CollectingResources(tank);

        game.SellTank(tankInDepartment.Sell());


        tank.setInDepartment(false);
    }

    @Override
    public void Init(){
        queueToThisDepartment = game.getQueueToDepartment(departmentNumber);
        queueToNextDepartment = null;
    }


}
