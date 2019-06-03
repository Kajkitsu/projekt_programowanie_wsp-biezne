package kajkitsu.projektPW;

public class Event extends Thread {
    Game game;
    int type;
    Timer timer;
    int timeOfEvent;
    int ID;

    public Event(Game game, int type, int time, int ID, Timer timer) {
        this.game = game;
        this.type=type;
        this.timeOfEvent=time;
        this.ID=ID;
        this.timer=timer;
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


            if(timeOfEvent-timer.getGlobalTime()<0){
                if(type==1){
                    game.BuyNewLineToDepartment(ID);
                }
                if(type==2){
                    game.BuyUpgradeForLineFrom(0, ID);
                }
                if (type == 3) {
                    game.BuyUpgradeForLineFrom(1, ID);
                }
                if (type == 4) {
                    game.BuyUpgradeForLineFrom(2, ID);
                }

            }


        } while (timer.getGlobalTime()<=timeOfEvent);
    }
}
