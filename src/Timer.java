public class Timer extends Thread  {
    int globalTime;

    Timer(int globalTime){
        this.globalTime=globalTime;
        this.start();
    }

    public int getGlobalTime() {
        return globalTime;
    }

    @Override
    public void run() {

        do {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            globalTime+=10;
            //System.out.println("Global time: "+globalTime);


        } while (true);
    }

}
