import java.util.List;

public class Status extends Thread {
    List<ProductionLine> departmentsList;
    Department departments[];
    Game game;

    public Status(Game game) {
        this.game = game;
        this.departments = game.getDepartments();
    }


    @Override
    public void run() {

        do {
            for(int i=0; i<7;i++){
                for(ProductionLine productionLine : departments[i].getListsLine()){
                    System.out.println(productionLine.Status());
                }
            }

            System.out.println(game.Status());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } while (true);
    }
}
