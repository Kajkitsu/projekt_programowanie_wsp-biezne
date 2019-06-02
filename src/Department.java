import java.util.ArrayList;
import java.util.List;

public class Department {
    private int IDOfDepartment;
    private int numberOfProductionLines;
    private int newProductionLineCost;
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
        this.newProductionLineCost = (int) Math.pow(10, numberOfProductionLines) * 100;
        this.maxLines = maxLines;
        this.numberOfProductionLines = 0;
        listsLine = new ArrayList<>();
    }


    public void AddNewLine() {
        if (numberOfProductionLines < maxLines) {

            this.newProductionLineCost = (int) Math.pow(10, numberOfProductionLines) * 100;
            ProductionLine line;
            if (IDOfDepartment == 6) line = new SellProductionLine(this, game, maxLevel);
            else line = new ProductionLine(this, game, maxLevel);

            line.setID(numberOfProductionLines);
            numberOfProductionLines++;

            listsLine.add(line);
            line.start();
        }

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

    public int getNewLineCost() {
        return newProductionLineCost;
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
