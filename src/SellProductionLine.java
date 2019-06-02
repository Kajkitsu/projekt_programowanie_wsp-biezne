public class SellProductionLine extends ProductionLine {

    public SellProductionLine(int actualLevel, Department department , Game game) {
        super(actualLevel, department, game);
    }

    @Override
    protected void SendTankToNextQueue(Tank tank){
        game.SellTank(tank.Sell());
        this.tanksServiced++;

    }


    @Override
    public void Init(){
        queueToThisDepartment = department.getQueueToThisDepartment();
        queueToNextDepartment = null;
    }


    @Override
    public String toString() {
        return "SellProductionLine{" +
                "ID=" + ID +
                ", actualLevel=" + actualLevel +
                ", department=" + department +
                ", levelEfficiency=" + levelEfficiency +
                ", actualResources=" + actualResources +
                ", isBusy=" + isBusy +
                ", tankInDepartment=" + tankInDepartment +
                ", tanksServiced=" + tanksServiced +
                ", game=" + game +
                ", queueToThisDepartment=" + queueToThisDepartment +
                ", queueToNextDepartment=" + queueToNextDepartment +
                '}';
    }
}
