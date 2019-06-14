package kajkitsu.projektPW.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableGUI extends Application {

    public void startNewWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/table.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("ProjektPW github.com/Kajkitsu");
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                this.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/table.fxml"));
        Parent root = loader.load();


        primaryStage.setTitle("ProjektPW github.com/Kajkitsu");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            try {
                this.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }


    public static void runTable() {
        launch();
    }


}
