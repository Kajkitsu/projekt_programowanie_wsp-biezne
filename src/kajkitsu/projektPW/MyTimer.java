package kajkitsu.projektPW;

public class MyTimer extends Thread {
    private int globalTime;
    private Game game;

    MyTimer(int globalTime, Game game) {
        this.globalTime=globalTime;
        this.game = game;
        this.start();
    }

    public int getGlobalTime() {
        return globalTime;
    }

    @Override
    public void run() {

        do {

            try {
                Thread.sleep(100 / game.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            globalTime += 1;

            //System.out.println("Global time: "+globalTime);


        } while (true);
    }

}
