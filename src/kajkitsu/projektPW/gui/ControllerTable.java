package kajkitsu.projektPW.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kajkitsu.projektPW.MySQL;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTable implements Initializable {

    @FXML
    private TableView<TankRecord> tableView;

    @FXML
    private TableColumn<TankRecord, Integer> idColumn;

    @FXML
    private TableColumn<TankRecord, String> nameColumn;

    @FXML
    private TableColumn<TankRecord, Integer> serialNumberColumn;

    @FXML
    private TableColumn<TankRecord, Integer> levelColumn;

    @FXML
    private TableColumn<TankRecord, String> dateColumn;

    @FXML
    private TableColumn<TankRecord, Integer> resourcesColumn;

    private ObservableList<TankRecord> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.updateController();

    }

    public void updateController() {
        data = FXCollections.observableArrayList(MySQL.getRecords());
        idColumn.setCellValueFactory(new PropertyValueFactory<TankRecord, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<TankRecord, String>("name"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<TankRecord, Integer>("serialNumber"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<TankRecord, Integer>("level"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<TankRecord, String>("date"));
        resourcesColumn.setCellValueFactory(new PropertyValueFactory<TankRecord, Integer>("resources"));
        tableView.setItems(data);
        // this.tableView.setItems(MySQL.getRecords());

    }

}