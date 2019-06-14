package kajkitsu.projektPW.logic;

import kajkitsu.projektPW.Game;

public class SellProductionLine extends ProductionLine {

    public SellProductionLine(Department department, Game game, int maxLevel) {
        super(department, game, maxLevel);
    }

    @Override
    protected synchronized void sendTankToNextQueue(Tank tank) {
        game.sellTank(tankInProductionLine.sell());
        this.countTanksServiced++;
    }

    @Override
    protected void initQueue() {
        queueToThisDepartment = department.getQueueToThisDepartment();
        queueToNextDepartment = null;
    }


}
