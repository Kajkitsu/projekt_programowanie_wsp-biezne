package kajkitsu.projektPW;

import kajkitsu.projektPW.gui.GameGUI;
import kajkitsu.projektPW.logic.Department;
import kajkitsu.projektPW.logic.QueueToDepartment;
import kajkitsu.projektPW.test.Status;


public class RunGame {

    private static int startMoney = 1000;

    public static void main(String[] args) {

        QueueToDepartment[] queues = new QueueToDepartment[]{
                new QueueToDepartment(1000),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100),
                new QueueToDepartment(100)
        };

        Game game = new Game(1, startMoney, queues);

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

        Status status = new Status(game);
        status.start();

        GameGUI.runGui(game);

    }

}
