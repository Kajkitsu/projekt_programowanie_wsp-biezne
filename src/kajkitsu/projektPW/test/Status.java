package kajkitsu.projektPW.test;

import kajkitsu.projektPW.logic.Department;
import kajkitsu.projektPW.Game;
import kajkitsu.projektPW.logic.ProductionLine;

public class Status extends Thread {
    private Department[] departments;
    private Game game;

    public Status(Game game) {
        this.game = game;
        this.departments = game.getDepartments();
    }


    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        do {
            for(int i = 0; i<7; i++){
                for(ProductionLine productionLine : departments[i].getListsLine()){
                    System.out.println(productionLine.getStatus());
                }
            }
            System.out.println(game.getStatus());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println();


        } while (true);
    }
}
