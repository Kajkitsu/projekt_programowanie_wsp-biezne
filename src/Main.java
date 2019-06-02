import java.util.Random;

public class Main {


    public static void main(String[] args){

        QueueToDepartment[] queues = new QueueToDepartment[]{
                new QueueToDepartment(500),
                new QueueToDepartment(5000),
                new QueueToDepartment(5000),
                new QueueToDepartment(1000),
                new QueueToDepartment(1000),
                new QueueToDepartment(5000),
                new QueueToDepartment(1000)
        };

        Game game = new Game(1,100000000,queues);

        Random random = new Random();

        Department[] departments = new Department[]{
                new Department(0,"Produkcja armat",queues[0],queues[1],100,game),
                new Department(1,"Odlewnia wiez",queues[1],queues[2],100,game),
                new Department(2,"Odlewnia kadlubow",queues[2],queues[3],100,game),
                new Department(3,"Produkcja motoryki",queues[3],queues[4],100,game),
                new Department(4,"Dzial elektorniki",queues[4],queues[5],100,game),
                new Department(5,"Dzial testow",queues[5],queues[6],100,game),
                new Department(6,"Dzial sprzedazy",queues[6],null,100,game),
        };

        game.setDepartments(departments);

        departments[0].AddLineToDepartment();
        departments[1].AddLineToDepartment();
        departments[2].AddLineToDepartment();
        departments[3].AddLineToDepartment();
        departments[4].AddLineToDepartment();
        departments[5].AddLineToDepartment();
        departments[6].AddLineToDepartment();



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Status status = new Status(game);
        status.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(game.toString());

        Timer timer = new Timer(0);

        Event event[] = new Event[]{
                new Event(game,1,4000,6,timer),
                new Event(game,1,8000,4,timer),
                new Event(game,1,6500,3,timer),
                new Event(game,1,6000,2,timer),
                new Event(game,1,7000,2,timer),

                new Event(game,2,5000,1,timer),
                new Event(game,2,10000,2,timer),
                new Event(game,2,11000,2,timer),
                new Event(game,2,12000,3,timer),
                new Event(game,2,10000,6,timer),
                new Event(game,2,13000,6,timer)
        };


        for(int i=0; i<1000; i++ ){
            Tank tank = new Tank(new int[]{10,10,10,10,10,10,10},"test-Tank:"+i,10000);
            game.AddTankToGame(tank);
        }

        try {
            status.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
