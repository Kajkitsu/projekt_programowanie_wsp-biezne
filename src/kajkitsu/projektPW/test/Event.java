package kajkitsu.projektPW.test;

import kajkitsu.projektPW.Game;
import kajkitsu.projektPW.logic.Tank;

public class Event extends Thread {
    private Game game;
    private int type;
    private MyTimer myTimer;
    private int timeOfEvent;
    private int ID;

    public Event(Game game, int type, int time, int ID, MyTimer myTimer) {
        this.game = game;
        this.type=type;
        this.timeOfEvent=time;
        this.ID=ID;
        this.myTimer = myTimer;
        this.start();
    }


    @Override
    public void run() {
        do {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (timeOfEvent - myTimer.getGlobalTime() < 0) {
                if(type==1){
                    game.buyNewProductionLine(ID);
                }
                if(type==2){
                    game.buyUpgradeForProductionLine(0, ID);
                }
                if (type == 3) {
                    game.buyUpgradeForProductionLine(1, ID);
                }
                if (type == 4) {
                    for (int i = 0; i < 100; i++) {
                        Tank tank = new Tank(new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000}, "test-Tank:" + i, 10000);
                        game.addTankToGame(tank);
                    }
                }

            }


        } while (myTimer.getGlobalTime() <= timeOfEvent);
    }
}
