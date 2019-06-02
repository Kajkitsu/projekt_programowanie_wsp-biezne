public class SellProductionLine extends ProductionLine {

    public SellProductionLine(Department department, Game game, int maxLevel) {
        super(department, game, maxLevel);
    }

    @Override
    protected void SendTankToNextQueue(Tank tank) {
        game.SellTank(tankInDepartment.Sell());
        this.tanksServiced++;
    }


    @Override
    public void Init(){
        queueToThisDepartment = department.getQueueToThisDepartment();
        queueToNextDepartment = null;
    }


}
