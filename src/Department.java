import java.util.ArrayList;
import java.util.List;

public class Department {
    private int IDOfDepartment;
    private int numberOfProductionLines=0;
    private int level=1;
    private int levelCost;
    private int newDepartmentCost;
    private int maxLevel;
    private String name;
    private Game game;
    private QueueToDepartment queueToThisDepartment;
    private QueueToDepartment queueToNextDepartment;
    private List<ProductionLine> listsLine;

    public Department(int IDOfDepartment, String name, QueueToDepartment queueToThisDepartment, QueueToDepartment queueToNextDepartment, int maxLevel, Game game) {
        this.IDOfDepartment = IDOfDepartment;
        this.name = name;
        this.queueToThisDepartment = queueToThisDepartment;
        this.queueToNextDepartment = queueToNextDepartment;
        this.game = game;
        this.maxLevel=maxLevel;
        this.levelCost = (int)Math.pow(20, level)*100;
        this.newDepartmentCost = (int)Math.pow(20, level)*100;
        listsLine = new ArrayList<>();
    }

    public Department(int actualLevel, int departmentNumber, int newDepartamentCost, int levelCost, int levelEfficiency, int maxLevel, String name, Game game) {
    }

    public void AddLineToDepartment(){
        if(game.TakeMoney(levelCost+newDepartmentCost)){
            //System.out.println("!!!!!!!!!!!!! Buy in department size:"+listsLine.size()+" ID:"+IDOfDepartment);
            newDepartmentCost= (int)Math.pow(20, level)*100;
            ProductionLine line;
            if(IDOfDepartment==6) line = new SellProductionLine(level,this,game);
            else line = new ProductionLine(level, this, game);
            line.setID(numberOfProductionLines);
            numberOfProductionLines++;
            listsLine.add(line);
            //System.out.println("!!!!!!!!!!!!! Buy before start");
            line.start();
           // System.out.println("!!!!!!!!!!!!! Buy in department size:"+listsLine.size()+" ID:"+IDOfDepartment);
        }

    }

    public void Upgrade(){
        if(game.TakeMoney(levelCost)){
            level++;
            levelCost=(int)Math.pow(20, level)*100;
            for(ProductionLine productionLine : listsLine){
                productionLine.Upgrade(level);
            }
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
}
