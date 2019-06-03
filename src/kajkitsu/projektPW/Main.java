package kajkitsu.projektPW;

import java.util.Random;

public class Main {


    public static void main(String[] args){

        QueueToDepartment[] queues = new QueueToDepartment[]{
                new QueueToDepartment(1000),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100)
        };

        Game game = new Game(25, 10000000, queues);

        Department[] departments = new Department[]{
                new Department(0, "Produkcja armat", queues[0], queues[1], 100, 10, game),
                new Department(1, "Odlewnia wiez", queues[1], queues[2], 100, 10, game),
                new Department(2, "Odlewnia kadlubow", queues[2], queues[3], 100, 10, game),
                new Department(3, "Produkcja motoryki", queues[3], queues[4], 100, 10, game),
                new Department(4, "Dzial elektorniki", queues[4], queues[5], 100, 10, game),
                new Department(5, "Dzial testow", queues[5], queues[6], 100, 10, game),
                new Department(6, "Dzial sprzedazy", queues[6], null, 100, 10, game),
        };

        game.setDepartments(departments);

        game.BuyNewLineToDepartment(0);
        game.BuyNewLineToDepartment(1);
        game.BuyNewLineToDepartment(2);
        game.BuyNewLineToDepartment(3);
        game.BuyNewLineToDepartment(4);
        game.BuyNewLineToDepartment(5);
        game.BuyNewLineToDepartment(6);


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

        Timer timer = new Timer(0, game);

        Event event[] = new Event[]{
                new Event(game, 2, 20, 1, timer),
                new Event(game, 1, 60, 1, timer),
                new Event(game, 2, 140, 1, timer),

                new Event(game, 2, 40, 2, timer),
                new Event(game, 1, 70, 2, timer),
                new Event(game, 2, 150, 2, timer),

                new Event(game, 2, 30, 3, timer),
                new Event(game, 2, 160, 3, timer),
                new Event(game, 1, 80, 3, timer),
                new Event(game, 3, 270, 3, timer),

                new Event(game, 3, 280, 4, timer),
                new Event(game, 1, 90, 4, timer),
                new Event(game, 1, 90, 4, timer),
                new Event(game, 1, 90, 4, timer),
                new Event(game, 4, 240, 4, timer),
                new Event(game, 2, 170, 4, timer),

                new Event(game, 1, 80, 5, timer),
                new Event(game, 3, 290, 5, timer),
                new Event(game, 2, 180, 5, timer),

                new Event(game, 1, 190, 6, timer),
                new Event(game, 2, 300, 6, timer)


        };


        for(int i=0; i<1000; i++ ){
            Tank tank = new Tank(new int[]{10, 500, 300, 100, 50, 100, 100}, "test-Tank:" + i, 10000);
            game.AddTankToGame(tank);
        }

        try {
            status.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
