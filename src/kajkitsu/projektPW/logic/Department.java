package kajkitsu.projektPW.logic;

import kajkitsu.projektPW.Game;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private int IDOfDepartment;
    private int numberOfProductionLines;
    private long newProductionLineCost;
    private int maxLines;
    private int maxLevel;
    private String name;
    private Game game;
    private QueueToDepartment queueToThisDepartment;
    private QueueToDepartment queueToNextDepartment;
    private List<ProductionLine> listsLine;

    public Department(int IDOfDepartment, String name, QueueToDepartment queueToThisDepartment, QueueToDepartment queueToNextDepartment, int maxLevel, int maxLines, Game game) {
        this.IDOfDepartment = IDOfDepartment;
        this.name = name;
        this.queueToThisDepartment = queueToThisDepartment;
        this.queueToNextDepartment = queueToNextDepartment;
        this.game = game;
        this.maxLevel = maxLevel;
        this.numberOfProductionLines = 0;
        this.newProductionLineCost = (long) Math.pow(100, numberOfProductionLines);
        this.maxLines = maxLines;
        listsLine = new ArrayList<>();
    }


    public void addNewProductionLine() {
        if (numberOfProductionLines < maxLines) {

            ProductionLine line;
            if (IDOfDepartment == 6) line = new SellProductionLine(this, game, maxLevel);
            else line = new ProductionLine(this, game, maxLevel);

            line.setID(numberOfProductionLines);
            numberOfProductionLines++;
            this.newProductionLineCost = (long) Math.pow(100, numberOfProductionLines);

            listsLine.add(line);
            line.start();
        }

    }

    public void sendSigToUpgrade(int line) {
        ProductionLine productionLine = getLine(line);

        productionLine.setUpgrading(true);
    }

    public ProductionLine getLine(int ID) {
        ProductionLine line;
        try {
            line = listsLine.get(ID);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return line;
    }

    public List<ProductionLine> getListsLine() {
        return listsLine;
    }

    public int getIDOfDepartment() {
        return IDOfDepartment;
    }

    public String getName() {
        return name;
    }

    public QueueToDepartment getQueueToThisDepartment() {
        return queueToThisDepartment;
    }

    public QueueToDepartment getQueueToNextDepartment() {
        return queueToNextDepartment;
    }

    public long getNewLineCost() {
        return newProductionLineCost;
    }

    public int getNumberOfProductionLines() {
        return numberOfProductionLines;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
