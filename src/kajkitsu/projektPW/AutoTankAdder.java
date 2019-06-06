package kajkitsu.projektPW;

public class AutoTankAdder extends Thread {
    Game game;


    public AutoTankAdder(Game game) {
        this.game = game;
        this.start();
    }


    @Override
    public void run() {
        do {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (game.getQueueToDepartment(0) < 10) {

                for (int i = 0; i < 100; i++) {
                    game.AddTankToGame(game.getNewTank(i));
                }
            }


        } while (true);
    }
}
