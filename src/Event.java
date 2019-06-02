import java.util.List;

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

            //System.out.println("To event: "+String.valueOf(timeOfEvent-timer.getGlobalTime()));


            if(timeOfEvent-timer.getGlobalTime()<0){
                if(type==1){
                    //System.out.println("globalTime>=timeOfEvent");
                    game.BuyUpgradeForDepartment(ID);
                }
                if(type==2){
                    //System.out.println("globalTime>=timeOfEvent");
                    game.BuyNewLineDepartment(ID);
                }

            }


        } while (timer.getGlobalTime()<=timeOfEvent);
    }
}
