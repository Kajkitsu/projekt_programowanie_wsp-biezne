package kajkitsu.projektPW.test;

import kajkitsu.projektPW.Game;

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

        } while (true);
    }

}
