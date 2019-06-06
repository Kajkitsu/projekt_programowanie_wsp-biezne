package kajkitsu.projektPW;

public class RunGame {

    public RunGame() {

    }


    public Game runGame() {

        QueueToDepartment[] queues = new QueueToDepartment[]{
                new QueueToDepartment(1000),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100)
        };

        Game game = new Game(1, 1000000000, queues);

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

//        game.BuyNewLineToDepartment(0);
//        game.BuyNewLineToDepartment(1);
//        game.BuyNewLineToDepartment(2);
//        game.BuyNewLineToDepartment(3);
//        game.BuyNewLineToDepartment(4);
//        game.BuyNewLineToDepartment(5);
//        game.BuyNewLineToDepartment(6);


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

        MyTimer myTimer = new MyTimer(0, game);

        Event event[] = new Event[]{
                new Event(game, 4, 50, 1, myTimer),

                new Event(game, 4, 200, 1, myTimer),
                new Event(game, 4, 400, 1, myTimer),
                new Event(game, 4, 600, 1, myTimer)

//                new Event(game, 2, 200, 1, myTimer),
//                new Event(game, 1, 600, 1, myTimer),
//                new Event(game, 2, 1400, 1, myTimer),
//
//                new Event(game, 2, 400, 2, myTimer),
//                new Event(game, 1, 700, 2, myTimer),
//                new Event(game, 2, 1500, 2, myTimer),
//
//                new Event(game, 2, 300, 3, myTimer),
//                new Event(game, 2, 1600, 3, myTimer),
//                new Event(game, 1, 800, 3, myTimer),
//                new Event(game, 3, 2700, 3, myTimer),
//
//                new Event(game, 3, 2800, 4, myTimer),
//                new Event(game, 1, 900, 4, myTimer),
//                new Event(game, 1, 900, 4, myTimer),
//                new Event(game, 1, 900, 4, myTimer),
//                new Event(game, 4, 2400, 4, myTimer),
//                new Event(game, 2, 1700, 4, myTimer),
//
//                new Event(game, 1, 800, 5, myTimer),
//                new Event(game, 3, 2900, 5, myTimer),
//                new Event(game, 2, 1800, 5, myTimer),
//
//                new Event(game, 1, 1900, 6, myTimer),
//                new Event(game, 2, 3000, 6, myTimer)


        };

        return game;

    }


}
