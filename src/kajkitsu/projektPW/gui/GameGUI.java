package kajkitsu.projektPW.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kajkitsu.projektPW.Game;
import kajkitsu.projektPW.RunGame;
import kajkitsu.projektPW.gui.Controller;

import java.util.Timer;
import java.util.TimerTask;

public class GameGUI extends Application {

    static Game game;

    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-gui.fxml"));
        Parent root = (Parent) loader.load();

        Controller controller = loader.getController();
        controller.setGame(game);
        primaryStage.setTitle("ProjektPW github.com/Kajkitsu");
        primaryStage.setScene(new Scene(root, 1410, 360));
        primaryStage.setResizable(false);
        primaryStage.show();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                controller.updateButtonLines();
//            }
//        }, 0, 100);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    controller.updateController();
                });
            }
        }, 1000, 100);






    }


    public static void runGui(Game ngame) {
        game = ngame;
        launch();


    }
}