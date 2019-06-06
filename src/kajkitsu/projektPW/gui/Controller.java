package kajkitsu.projektPW.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import kajkitsu.projektPW.Game;
import kajkitsu.projektPW.logic.Money;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Game game;
    Button upgradeButton[][];
    ProgressBar progressBar[][];
    Text textEfe[][];
    Text textLevel[][];
    Text textQueue[];
    Text textSumEfe[];
    Text textReq[];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    public Controller(Game game) {
//        this.game = game;
//
//        //this.initControllerSpeed();
//    }

    public void setGame(Game game) {
        this.game = game;
        this.initController();

//        UpdateController updateController = new UpdateController(this);
//        updateController.start();
    }

    public void updateController() {
        this.updateButtonLines();
        this.updateButtonSpeed();
        this.updateProgressBarsLines();
        this.updateProgressBarsLines();
        this.updateTextDepartament();
        this.updateTextLeftStats();
        this.updateTextRightStats();
    }




    public void initController() {

        this.assignButtonsLines();
        this.assignProgressBarsLines();
        this.assignTextDepartament();
        this.assignTextRightStatus();

        this.initButtonsUpgrade();

    }

    public void assignTextRightStatus() {
        textQueue = new Text[]{
                textActualQueueDep0,
                textActualQueueDep1,
                textActualQueueDep2,
                textActualQueueDep3,
                textActualQueueDep4,
                textActualQueueDep5,
                textActualQueueDep6
        };

        textSumEfe = new Text[]{
                effDep0Text,
                effDep1Text,
                effDep2Text,
                effDep3Text,
                effDep4Text,
                effDep5Text,
                effDep6Text
        };
        textReq = new Text[]{
                tankReqDep0Text,
                tankReqDep1Text,
                tankReqDep2Text,
                tankReqDep3Text,
                tankReqDep4Text,
                tankReqDep5Text,
                tankReqDep6Text
        };

    }

    public void assignButtonsLines() {
        upgradeButton = new Button[][]{
                {
                        buttonUpgradeDep0Line0,
                        buttonUpgradeDep0Line1,
                        buttonUpgradeDep0Line2,
                        buttonUpgradeDep0Line3,
                        buttonUpgradeDep0Line4,
                        buttonUpgradeDep0Line5,
                        buttonUpgradeDep0Line6
                },
                {
                        buttonUpgradeDep1Line0,
                        buttonUpgradeDep1Line1,
                        buttonUpgradeDep1Line2,
                        buttonUpgradeDep1Line3,
                        buttonUpgradeDep1Line4,
                        buttonUpgradeDep1Line5,
                        buttonUpgradeDep1Line6
                },
                {
                        buttonUpgradeDep2Line0,
                        buttonUpgradeDep2Line1,
                        buttonUpgradeDep2Line2,
                        buttonUpgradeDep2Line3,
                        buttonUpgradeDep2Line4,
                        buttonUpgradeDep2Line5,
                        buttonUpgradeDep2Line6
                },
                {
                        buttonUpgradeDep3Line0,
                        buttonUpgradeDep3Line1,
                        buttonUpgradeDep3Line2,
                        buttonUpgradeDep3Line3,
                        buttonUpgradeDep3Line4,
                        buttonUpgradeDep3Line5,
                        buttonUpgradeDep3Line6
                },
                {
                        buttonUpgradeDep4Line0,
                        buttonUpgradeDep4Line1,
                        buttonUpgradeDep4Line2,
                        buttonUpgradeDep4Line3,
                        buttonUpgradeDep4Line4,
                        buttonUpgradeDep4Line5,
                        buttonUpgradeDep4Line6
                },
                {
                        buttonUpgradeDep5Line0,
                        buttonUpgradeDep5Line1,
                        buttonUpgradeDep5Line2,
                        buttonUpgradeDep5Line3,
                        buttonUpgradeDep5Line4,
                        buttonUpgradeDep5Line5,
                        buttonUpgradeDep5Line6
                },
                {
                        buttonUpgradeDep6Line0,
                        buttonUpgradeDep6Line1,
                        buttonUpgradeDep6Line2,
                        buttonUpgradeDep6Line3,
                        buttonUpgradeDep6Line4,
                        buttonUpgradeDep6Line5,
                        buttonUpgradeDep6Line6
                },
        };
    }

    public void assignTextDepartament() {
        textEfe = new Text[][]{
                {
                        textEfeDep0Line0,
                        textEfeDep0Line1,
                        textEfeDep0Line2,
                        textEfeDep0Line3,
                        textEfeDep0Line4,
                        textEfeDep0Line5,
                        textEfeDep0Line6
                },
                {
                        textEfeDep1Line0,
                        textEfeDep1Line1,
                        textEfeDep1Line2,
                        textEfeDep1Line3,
                        textEfeDep1Line4,
                        textEfeDep1Line5,
                        textEfeDep1Line6
                },
                {
                        textEfeDep2Line0,
                        textEfeDep2Line1,
                        textEfeDep2Line2,
                        textEfeDep2Line3,
                        textEfeDep2Line4,
                        textEfeDep2Line5,
                        textEfeDep2Line6
                },
                {
                        textEfeDep3Line0,
                        textEfeDep3Line1,
                        textEfeDep3Line2,
                        textEfeDep3Line3,
                        textEfeDep3Line4,
                        textEfeDep3Line5,
                        textEfeDep3Line6
                },
                {
                        textEfeDep4Line0,
                        textEfeDep4Line1,
                        textEfeDep4Line2,
                        textEfeDep4Line3,
                        textEfeDep4Line4,
                        textEfeDep4Line5,
                        textEfeDep4Line6
                },
                {
                        textEfeDep5Line0,
                        textEfeDep5Line1,
                        textEfeDep5Line2,
                        textEfeDep5Line3,
                        textEfeDep5Line4,
                        textEfeDep5Line5,
                        textEfeDep5Line6
                },
                {
                        textEfeDep6Line0,
                        textEfeDep6Line1,
                        textEfeDep6Line2,
                        textEfeDep6Line3,
                        textEfeDep6Line4,
                        textEfeDep6Line5,
                        textEfeDep6Line6
                }
        };

        textLevel = new Text[][]{
                {
                        textLevelDep0Line0,
                        textLevelDep0Line1,
                        textLevelDep0Line2,
                        textLevelDep0Line3,
                        textLevelDep0Line4,
                        textLevelDep0Line5,
                        textLevelDep0Line6
                },
                {
                        textLevelDep1Line0,
                        textLevelDep1Line1,
                        textLevelDep1Line2,
                        textLevelDep1Line3,
                        textLevelDep1Line4,
                        textLevelDep1Line5,
                        textLevelDep1Line6
                },
                {
                        textLevelDep2Line0,
                        textLevelDep2Line1,
                        textLevelDep2Line2,
                        textLevelDep2Line3,
                        textLevelDep2Line4,
                        textLevelDep2Line5,
                        textLevelDep2Line6
                },
                {
                        textLevelDep3Line0,
                        textLevelDep3Line1,
                        textLevelDep3Line2,
                        textLevelDep3Line3,
                        textLevelDep3Line4,
                        textLevelDep3Line5,
                        textLevelDep3Line6
                },
                {
                        textLevelDep4Line0,
                        textLevelDep4Line1,
                        textLevelDep4Line2,
                        textLevelDep4Line3,
                        textLevelDep4Line4,
                        textLevelDep4Line5,
                        textLevelDep4Line6
                },
                {
                        textLevelDep5Line0,
                        textLevelDep5Line1,
                        textLevelDep5Line2,
                        textLevelDep5Line3,
                        textLevelDep5Line4,
                        textLevelDep5Line5,
                        textLevelDep5Line6
                },
                {
                        textLevelDep6Line0,
                        textLevelDep6Line1,
                        textLevelDep6Line2,
                        textLevelDep6Line3,
                        textLevelDep6Line4,
                        textLevelDep6Line5,
                        textLevelDep6Line6
                }

        };

    }

    public void initButtonsUpgrade() {
        for (int dep = 0; dep < 7; dep++) {
            for (int line = 0; line < 7; line++) {
                upgradeButton[dep][line].setDisable(line != 0);
                upgradeButton[dep][line].setText((line == 0) ? ("Kup za: " + Money.getStringFromLong(game.getNewLineCost(dep))) : ("Zablokowany"));
            }
        }
    }

    public void assignProgressBarsLines() {
        progressBar = new ProgressBar[][]{
                {
                        progerssTankDep0Line0,
                        progerssTankDep0Line1,
                        progerssTankDep0Line2,
                        progerssTankDep0Line3,
                        progerssTankDep0Line4,
                        progerssTankDep0Line5,
                        progerssTankDep0Line6
                },
                {
                        progerssTankDep1Line0,
                        progerssTankDep1Line1,
                        progerssTankDep1Line2,
                        progerssTankDep1Line3,
                        progerssTankDep1Line4,
                        progerssTankDep1Line5,
                        progerssTankDep1Line6
                },
                {
                        progerssTankDep2Line0,
                        progerssTankDep2Line1,
                        progerssTankDep2Line2,
                        progerssTankDep2Line3,
                        progerssTankDep2Line4,
                        progerssTankDep2Line5,
                        progerssTankDep2Line6
                },
                {
                        progerssTankDep3Line0,
                        progerssTankDep3Line1,
                        progerssTankDep3Line2,
                        progerssTankDep3Line3,
                        progerssTankDep3Line4,
                        progerssTankDep3Line5,
                        progerssTankDep3Line6
                },
                {
                        progerssTankDep4Line0,
                        progerssTankDep4Line1,
                        progerssTankDep4Line2,
                        progerssTankDep4Line3,
                        progerssTankDep4Line4,
                        progerssTankDep4Line5,
                        progerssTankDep4Line6
                },
                {
                        progerssTankDep5Line0,
                        progerssTankDep5Line1,
                        progerssTankDep5Line2,
                        progerssTankDep5Line3,
                        progerssTankDep5Line4,
                        progerssTankDep5Line5,
                        progerssTankDep5Line6
                },
                {
                        progerssTankDep6Line0,
                        progerssTankDep6Line1,
                        progerssTankDep6Line2,
                        progerssTankDep6Line3,
                        progerssTankDep6Line4,
                        progerssTankDep6Line5,
                        progerssTankDep6Line6
                },
        };

    }

    public void updateProgressBarsLines() {
        for (int dep = 0; dep < 7; dep++) {
            for (int line = 0; line < 7; line++) {
                if (upgradeButton[dep][line].getText().contains("Ulepsz")) {
                    progressBar[dep][line].setProgress(game.getLineProgress(dep, line));
                }
            }
        }

    }

    public void updateButtonSpeed() {
        speedButtonx1.setDisable(game.getSpeed() == 1);
        speedButtonx2.setDisable(game.getSpeed() == 2);
        speedButtonx4.setDisable(game.getSpeed() == 4);
        speedButtonx8.setDisable(game.getSpeed() == 8);
        speedButtonx16.setDisable(game.getSpeed() == 16);
        speedButtonx32.setDisable(game.getSpeed() == 32);
    }

    public void updateButtonLines() {
        for (int dep = 0; dep < 7; dep++) {
            for (int line = 0; line < 7; line++) {
                if (upgradeButton[dep][line].getText().contains("Kup")) {
                    upgradeButton[dep][line].setText("Kup za: " + Money.getStringFromLong(game.getNewLineCost(dep)));
                    if (game.getMoney() < game.getNewLineCost(dep)) upgradeButton[dep][line].setDisable(true);
                    else upgradeButton[dep][line].setDisable(false);
                }
                if (upgradeButton[dep][line].getText().contains("Ulepsz")) {
                    if (game.isOnMaxLevel(dep, line)) {
                        upgradeButton[dep][line].setText("Maksymalny level");
                        upgradeButton[dep][line].setDisable(true);
                    } else {
                        upgradeButton[dep][line].setText("Ulepsz za: " + Money.getStringFromLong(game.getUpgradeLineCost(dep, line)));
                        if (game.getIsLineUpgrading(dep, line) || game.getMoney() < game.getUpgradeLineCost(dep, line))
                            upgradeButton[dep][line].setDisable(true);
                        else upgradeButton[dep][line].setDisable(false);
                    }
                }
            }
        }
    }

    public void controllerUpgradeButton(ActionEvent e) {
        for (int dep = 0; dep < 7; dep++) {
            for (int line = 0; line < 7; line++) {
                if (e.getSource() == upgradeButton[dep][line]) {
                    if (upgradeButton[dep][line].getText().contains("Kup")) {
                        if (game.BuyNewLineToDepartment(dep)) {
                            if (line < 6) {
                                upgradeButton[dep][line + 1].setText("Kup za: " + Money.getStringFromLong(game.getNewLineCost(dep)));
                                upgradeButton[dep][line + 1].setDisable(true);
                            }
                            upgradeButton[dep][line].setText("Ulepsz za: " + Money.getStringFromLong(game.getUpgradeLineCost(dep, line)));
                            upgradeButton[dep][line].setDisable(true);
                        }

                    } else if (upgradeButton[dep][line].getText().contains("Ulepsz")) {
                        game.BuyUpgradeForLineFrom(dep, line);
                        upgradeButton[dep][line].setDisable(true);

                    }
                    return;

                }
            }
        }

    }

    public void controllerSpeedButton(ActionEvent e) {
        if (e.getSource() == speedButtonx1) game.setSpeed(1);
        if (e.getSource() == speedButtonx2) game.setSpeed(2);
        if (e.getSource() == speedButtonx4) game.setSpeed(4);
        if (e.getSource() == speedButtonx8) game.setSpeed(8);
        if (e.getSource() == speedButtonx16) game.setSpeed(16);
        if (e.getSource() == speedButtonx32) game.setSpeed(32);
    }


    public void updateTextDepartament() {
        for (int dep = 0; dep < 7; dep++) {
            for (int line = 0; line < 7; line++) {
                textLevel[dep][line].setText("Poziom: " + game.getLineLevel(dep, line));
                textEfe[dep][line].setText("Efektywnosc: " + Money.getStringFromLong(game.getLineEfficiency(dep, line)));
            }
        }
    }

    public void updateTextLeftStats() {
        moneyText.setText("Pieniadze: " + Money.getStringFromLong(game.getMoney()));
        levelText.setText("Poziom gry: " + game.getLevelGame());
        tankProducedText.setText("Liczba wyprodukownych czołgów: " + game.getSoldTank());
        queueTankProductionText.setText("Kolejka do fabryki: " + game.getQueueToDepartment(0));

    }

    public void updateTextRightStats() {
        for (int dep = 0; dep < 7; dep++) {
            textQueue[dep].setText("" + game.getQueueToDepartment(dep));
            textSumEfe[dep].setText(Money.getStringFromLong(game.getDepartmentEfficiency(dep)));

        }

    }





    @FXML // fx:id="speedText"
    private Text speedText;

    @FXML // fx:id="speedButtonx1"
    private Button speedButtonx1;

    @FXML // fx:id="speedButtonx2"
    private Button speedButtonx2;

    @FXML // fx:id="speedButtonx4"
    private Button speedButtonx4;

    @FXML // fx:id="speedButtonx8"
    private Button speedButtonx8;

    @FXML // fx:id="speedButtonx16"
    private Button speedButtonx16;

    @FXML // fx:id="speedButtonx32"
    private Button speedButtonx32;

    @FXML // fx:id="moneyText"
    private Text moneyText;

    @FXML // fx:id="levelText"
    private Text levelText;

    @FXML // fx:id="tankProducedText"
    private Text tankProducedText;

    @FXML // fx:id="queueTankProductionText"
    private Text queueTankProductionText;

    @FXML // fx:id="textActualQueueDep0"
    private Text textActualQueueDep0; // Value injected by FXMLLoader

    @FXML // fx:id="textActualQueueDep1"
    private Text textActualQueueDep1; // Value injected by FXMLLoader

    @FXML // fx:id="textActualQueueDep2"
    private Text textActualQueueDep2; // Value injected by FXMLLoader

    @FXML // fx:id="textActualQueueDep3"
    private Text textActualQueueDep3; // Value injected by FXMLLoader

    @FXML // fx:id="textActualQueueDep4"
    private Text textActualQueueDep4; // Value injected by FXMLLoader

    @FXML // fx:id="textActualQueueDep5"
    private Text textActualQueueDep5; // Value injected by FXMLLoader

    @FXML // fx:id="textActualQueueDep6"
    private Text textActualQueueDep6; // Value injected by FXMLLoader

    @FXML // fx:id="effDep0Text"
    private Text effDep0Text; // Value injected by FXMLLoader

    @FXML // fx:id="effDep1Text"
    private Text effDep1Text; // Value injected by FXMLLoader

    @FXML // fx:id="effDep2Text"
    private Text effDep2Text; // Value injected by FXMLLoader

    @FXML // fx:id="effDep3Text"
    private Text effDep3Text; // Value injected by FXMLLoader

    @FXML // fx:id="effDep4Text"
    private Text effDep4Text; // Value injected by FXMLLoader

    @FXML // fx:id="effDep5Text"
    private Text effDep5Text; // Value injected by FXMLLoader

    @FXML // fx:id="effDep6Text"
    private Text effDep6Text; // Value injected by FXMLLoader

    @FXML // fx:id="textLevelDep0Line0"
    private Text textLevelDep0Line0;

    @FXML // fx:id="textEfeDep0Line0"
    private Text textEfeDep0Line0;

    @FXML // fx:id="progerssTankDep0Line0"
    private ProgressBar progerssTankDep0Line0;

    @FXML // fx:id="buttonUpgradeDep0Line0"
    private Button buttonUpgradeDep0Line0;

    @FXML // fx:id="textLevelDep0Line1"
    private Text textLevelDep0Line1;

    @FXML // fx:id="textEfeDep0Line1"
    private Text textEfeDep0Line1;

    @FXML // fx:id="progerssTankDep0Line1"
    private ProgressBar progerssTankDep0Line1;

    @FXML // fx:id="buttonUpgradeDep0Line1"
    private Button buttonUpgradeDep0Line1;

    @FXML // fx:id="textLevelDep0Line2"
    private Text textLevelDep0Line2;

    @FXML // fx:id="textEfeDep0Line2"
    private Text textEfeDep0Line2;

    @FXML // fx:id="progerssTankDep0Line2"
    private ProgressBar progerssTankDep0Line2;

    @FXML // fx:id="buttonUpgradeDep0Line2"
    private Button buttonUpgradeDep0Line2;

    @FXML // fx:id="textLevelDep0Line3"
    private Text textLevelDep0Line3;

    @FXML // fx:id="textEfeDep0Line3"
    private Text textEfeDep0Line3;

    @FXML // fx:id="progerssTankDep0Line3"
    private ProgressBar progerssTankDep0Line3;

    @FXML // fx:id="buttonUpgradeDep0Line3"
    private Button buttonUpgradeDep0Line3;

    @FXML // fx:id="textLevelDep0Line4"
    private Text textLevelDep0Line4;

    @FXML // fx:id="textEfeDep0Line4"
    private Text textEfeDep0Line4;

    @FXML // fx:id="progerssTankDep0Line4"
    private ProgressBar progerssTankDep0Line4;

    @FXML // fx:id="buttonUpgradeDep0Line4"
    private Button buttonUpgradeDep0Line4;

    @FXML // fx:id="textLevelDep0Line5"
    private Text textLevelDep0Line5;

    @FXML // fx:id="textEfeDep0Line5"
    private Text textEfeDep0Line5;

    @FXML // fx:id="progerssTankDep0Line5"
    private ProgressBar progerssTankDep0Line5;

    @FXML // fx:id="buttonUpgradeDep0Line5"
    private Button buttonUpgradeDep0Line5;

    @FXML // fx:id="textLevelDep0Line6"
    private Text textLevelDep0Line6;

    @FXML // fx:id="textEfeDep0Line6"
    private Text textEfeDep0Line6;

    @FXML // fx:id="progerssTankDep0Line6"
    private ProgressBar progerssTankDep0Line6;

    @FXML // fx:id="buttonUpgradeDep0Line6"
    private Button buttonUpgradeDep0Line6;

    @FXML // fx:id="textLevelDep1Line0"
    private Text textLevelDep1Line0;

    @FXML // fx:id="textEfeDep1Line0"
    private Text textEfeDep1Line0;

    @FXML // fx:id="progerssTankDep1Line0"
    private ProgressBar progerssTankDep1Line0;

    @FXML // fx:id="buttonUpgradeDep1Line0"
    private Button buttonUpgradeDep1Line0;

    @FXML // fx:id="textLevelDep1Line1"
    private Text textLevelDep1Line1;

    @FXML // fx:id="textEfeDep1Line1"
    private Text textEfeDep1Line1;

    @FXML // fx:id="progerssTankDep1Line1"
    private ProgressBar progerssTankDep1Line1;

    @FXML // fx:id="buttonUpgradeDep1Line1"
    private Button buttonUpgradeDep1Line1;

    @FXML // fx:id="textLevelDep1Line2"
    private Text textLevelDep1Line2;

    @FXML // fx:id="textEfeDep1Line2"
    private Text textEfeDep1Line2;

    @FXML // fx:id="progerssTankDep1Line2"
    private ProgressBar progerssTankDep1Line2;

    @FXML // fx:id="buttonUpgradeDep1Line2"
    private Button buttonUpgradeDep1Line2;

    @FXML // fx:id="textLevelDep1Line3"
    private Text textLevelDep1Line3;

    @FXML // fx:id="textEfeDep1Line3"
    private Text textEfeDep1Line3;

    @FXML // fx:id="progerssTankDep1Line3"
    private ProgressBar progerssTankDep1Line3;

    @FXML // fx:id="buttonUpgradeDep1Line3"
    private Button buttonUpgradeDep1Line3;

    @FXML // fx:id="textLevelDep1Line4"
    private Text textLevelDep1Line4;

    @FXML // fx:id="textEfeDep1Line4"
    private Text textEfeDep1Line4;

    @FXML // fx:id="progerssTankDep1Line4"
    private ProgressBar progerssTankDep1Line4;

    @FXML // fx:id="buttonUpgradeDep1Line4"
    private Button buttonUpgradeDep1Line4;

    @FXML // fx:id="textLevelDep1Line5"
    private Text textLevelDep1Line5;

    @FXML // fx:id="textEfeDep1Line5"
    private Text textEfeDep1Line5;

    @FXML // fx:id="progerssTankDep1Line5"
    private ProgressBar progerssTankDep1Line5;

    @FXML // fx:id="buttonUpgradeDep1Line5"
    private Button buttonUpgradeDep1Line5;

    @FXML // fx:id="textLevelDep1Line6"
    private Text textLevelDep1Line6;

    @FXML // fx:id="textEfeDep1Line6"
    private Text textEfeDep1Line6;

    @FXML // fx:id="progerssTankDep1Line6"
    private ProgressBar progerssTankDep1Line6;

    @FXML // fx:id="buttonUpgradeDep1Line6"
    private Button buttonUpgradeDep1Line6;

    @FXML // fx:id="textLevelDep2Line0"
    private Text textLevelDep2Line0;

    @FXML // fx:id="textEfeDep2Line0"
    private Text textEfeDep2Line0;

    @FXML // fx:id="progerssTankDep2Line0"
    private ProgressBar progerssTankDep2Line0;

    @FXML // fx:id="buttonUpgradeDep2Line0"
    private Button buttonUpgradeDep2Line0;

    @FXML // fx:id="textLevelDep2Line1"
    private Text textLevelDep2Line1;

    @FXML // fx:id="textEfeDep2Line1"
    private Text textEfeDep2Line1;

    @FXML // fx:id="progerssTankDep2Line1"
    private ProgressBar progerssTankDep2Line1;

    @FXML // fx:id="buttonUpgradeDep2Line1"
    private Button buttonUpgradeDep2Line1;

    @FXML // fx:id="textLevelDep2Line2"
    private Text textLevelDep2Line2;

    @FXML // fx:id="textEfeDep2Line2"
    private Text textEfeDep2Line2;

    @FXML // fx:id="progerssTankDep2Line2"
    private ProgressBar progerssTankDep2Line2;

    @FXML // fx:id="buttonUpgradeDep2Line2"
    private Button buttonUpgradeDep2Line2;

    @FXML // fx:id="textLevelDep2Line3"
    private Text textLevelDep2Line3;

    @FXML // fx:id="textEfeDep2Line3"
    private Text textEfeDep2Line3;

    @FXML // fx:id="progerssTankDep2Line3"
    private ProgressBar progerssTankDep2Line3;

    @FXML // fx:id="buttonUpgradeDep2Line3"
    private Button buttonUpgradeDep2Line3;

    @FXML // fx:id="textLevelDep2Line4"
    private Text textLevelDep2Line4;

    @FXML // fx:id="textEfeDep2Line4"
    private Text textEfeDep2Line4;

    @FXML // fx:id="progerssTankDep2Line4"
    private ProgressBar progerssTankDep2Line4;

    @FXML // fx:id="buttonUpgradeDep2Line4"
    private Button buttonUpgradeDep2Line4;

    @FXML // fx:id="textLevelDep2Line5"
    private Text textLevelDep2Line5;

    @FXML // fx:id="textEfeDep2Line5"
    private Text textEfeDep2Line5;

    @FXML // fx:id="progerssTankDep2Line5"
    private ProgressBar progerssTankDep2Line5;

    @FXML // fx:id="buttonUpgradeDep2Line5"
    private Button buttonUpgradeDep2Line5;

    @FXML // fx:id="textLevelDep2Line6"
    private Text textLevelDep2Line6;

    @FXML // fx:id="textEfeDep2Line6"
    private Text textEfeDep2Line6;

    @FXML // fx:id="progerssTankDep2Line6"
    private ProgressBar progerssTankDep2Line6;

    @FXML // fx:id="buttonUpgradeDep2Line6"
    private Button buttonUpgradeDep2Line6;

    @FXML // fx:id="textLevelDep3Line0"
    private Text textLevelDep3Line0;

    @FXML // fx:id="textEfeDep3Line0"
    private Text textEfeDep3Line0;

    @FXML // fx:id="progerssTankDep3Line0"
    private ProgressBar progerssTankDep3Line0;

    @FXML // fx:id="buttonUpgradeDep3Line0"
    private Button buttonUpgradeDep3Line0;

    @FXML // fx:id="textLevelDep3Line1"
    private Text textLevelDep3Line1;

    @FXML // fx:id="textEfeDep3Line1"
    private Text textEfeDep3Line1;

    @FXML // fx:id="progerssTankDep3Line1"
    private ProgressBar progerssTankDep3Line1;

    @FXML // fx:id="buttonUpgradeDep3Line1"
    private Button buttonUpgradeDep3Line1;

    @FXML // fx:id="textLevelDep3Line2"
    private Text textLevelDep3Line2;

    @FXML // fx:id="textEfeDep3Line2"
    private Text textEfeDep3Line2;

    @FXML // fx:id="progerssTankDep3Line2"
    private ProgressBar progerssTankDep3Line2;

    @FXML // fx:id="buttonUpgradeDep3Line2"
    private Button buttonUpgradeDep3Line2;

    @FXML // fx:id="textLevelDep3Line3"
    private Text textLevelDep3Line3;

    @FXML // fx:id="textEfeDep3Line3"
    private Text textEfeDep3Line3;

    @FXML // fx:id="progerssTankDep3Line3"
    private ProgressBar progerssTankDep3Line3;

    @FXML // fx:id="buttonUpgradeDep3Line3"
    private Button buttonUpgradeDep3Line3;

    @FXML // fx:id="textLevelDep3Line4"
    private Text textLevelDep3Line4;

    @FXML // fx:id="textEfeDep3Line4"
    private Text textEfeDep3Line4;

    @FXML // fx:id="progerssTankDep3Line4"
    private ProgressBar progerssTankDep3Line4;

    @FXML // fx:id="buttonUpgradeDep3Line4"
    private Button buttonUpgradeDep3Line4;

    @FXML // fx:id="textLevelDep3Line5"
    private Text textLevelDep3Line5;

    @FXML // fx:id="textEfeDep3Line5"
    private Text textEfeDep3Line5;

    @FXML // fx:id="progerssTankDep3Line5"
    private ProgressBar progerssTankDep3Line5;

    @FXML // fx:id="buttonUpgradeDep3Line5"
    private Button buttonUpgradeDep3Line5;

    @FXML // fx:id="textLevelDep3Line6"
    private Text textLevelDep3Line6;

    @FXML // fx:id="textEfeDep3Line6"
    private Text textEfeDep3Line6;

    @FXML // fx:id="progerssTankDep3Line6"
    private ProgressBar progerssTankDep3Line6;

    @FXML // fx:id="buttonUpgradeDep3Line6"
    private Button buttonUpgradeDep3Line6;

    @FXML // fx:id="textLevelDep4Line0"
    private Text textLevelDep4Line0;

    @FXML // fx:id="textEfeDep4Line0"
    private Text textEfeDep4Line0;

    @FXML // fx:id="progerssTankDep4Line0"
    private ProgressBar progerssTankDep4Line0;

    @FXML // fx:id="buttonUpgradeDep4Line0"
    private Button buttonUpgradeDep4Line0;

    @FXML // fx:id="textLevelDep4Line1"
    private Text textLevelDep4Line1;

    @FXML // fx:id="textEfeDep4Line1"
    private Text textEfeDep4Line1;

    @FXML // fx:id="progerssTankDep4Line1"
    private ProgressBar progerssTankDep4Line1;

    @FXML // fx:id="buttonUpgradeDep4Line1"
    private Button buttonUpgradeDep4Line1;

    @FXML // fx:id="textLevelDep4Line2"
    private Text textLevelDep4Line2;

    @FXML // fx:id="textEfeDep4Line2"
    private Text textEfeDep4Line2;

    @FXML // fx:id="progerssTankDep4Line2"
    private ProgressBar progerssTankDep4Line2;

    @FXML // fx:id="buttonUpgradeDep4Line2"
    private Button buttonUpgradeDep4Line2;

    @FXML // fx:id="textLevelDep4Line3"
    private Text textLevelDep4Line3;

    @FXML // fx:id="textEfeDep4Line3"
    private Text textEfeDep4Line3;

    @FXML // fx:id="progerssTankDep4Line3"
    private ProgressBar progerssTankDep4Line3;

    @FXML // fx:id="buttonUpgradeDep4Line3"
    private Button buttonUpgradeDep4Line3;

    @FXML // fx:id="textLevelDep4Line4"
    private Text textLevelDep4Line4;

    @FXML // fx:id="textEfeDep4Line4"
    private Text textEfeDep4Line4;

    @FXML // fx:id="progerssTankDep4Line4"
    private ProgressBar progerssTankDep4Line4;

    @FXML // fx:id="buttonUpgradeDep4Line4"
    private Button buttonUpgradeDep4Line4;

    @FXML // fx:id="textLevelDep4Line5"
    private Text textLevelDep4Line5;

    @FXML // fx:id="textEfeDep4Line5"
    private Text textEfeDep4Line5;

    @FXML // fx:id="progerssTankDep4Line5"
    private ProgressBar progerssTankDep4Line5;

    @FXML // fx:id="buttonUpgradeDep4Line5"
    private Button buttonUpgradeDep4Line5;

    @FXML // fx:id="textLevelDep4Line6"
    private Text textLevelDep4Line6;

    @FXML // fx:id="textEfeDep4Line6"
    private Text textEfeDep4Line6;

    @FXML // fx:id="progerssTankDep4Line6"
    private ProgressBar progerssTankDep4Line6;

    @FXML // fx:id="buttonUpgradeDep4Line6"
    private Button buttonUpgradeDep4Line6;

    @FXML // fx:id="textLevelDep5Line0"
    private Text textLevelDep5Line0;

    @FXML // fx:id="textEfeDep5Line0"
    private Text textEfeDep5Line0;

    @FXML // fx:id="progerssTankDep5Line0"
    private ProgressBar progerssTankDep5Line0;

    @FXML // fx:id="buttonUpgradeDep5Line0"
    private Button buttonUpgradeDep5Line0;

    @FXML // fx:id="textLevelDep5Line1"
    private Text textLevelDep5Line1;

    @FXML // fx:id="textEfeDep5Line1"
    private Text textEfeDep5Line1;

    @FXML // fx:id="progerssTankDep5Line1"
    private ProgressBar progerssTankDep5Line1;

    @FXML // fx:id="buttonUpgradeDep5Line1"
    private Button buttonUpgradeDep5Line1;

    @FXML // fx:id="textLevelDep5Line2"
    private Text textLevelDep5Line2;

    @FXML // fx:id="textEfeDep5Line2"
    private Text textEfeDep5Line2;

    @FXML // fx:id="progerssTankDep5Line2"
    private ProgressBar progerssTankDep5Line2;

    @FXML // fx:id="buttonUpgradeDep5Line2"
    private Button buttonUpgradeDep5Line2;

    @FXML // fx:id="textLevelDep5Line3"
    private Text textLevelDep5Line3;

    @FXML // fx:id="textEfeDep5Line3"
    private Text textEfeDep5Line3;

    @FXML // fx:id="progerssTankDep5Line3"
    private ProgressBar progerssTankDep5Line3;

    @FXML // fx:id="buttonUpgradeDep5Line3"
    private Button buttonUpgradeDep5Line3;

    @FXML // fx:id="textLevelDep5Line4"
    private Text textLevelDep5Line4;

    @FXML // fx:id="textEfeDep5Line4"
    private Text textEfeDep5Line4;

    @FXML // fx:id="progerssTankDep5Line4"
    private ProgressBar progerssTankDep5Line4;

    @FXML // fx:id="buttonUpgradeDep5Line4"
    private Button buttonUpgradeDep5Line4;

    @FXML // fx:id="textLevelDep5Line5"
    private Text textLevelDep5Line5;

    @FXML // fx:id="textEfeDep5Line5"
    private Text textEfeDep5Line5;

    @FXML // fx:id="progerssTankDep5Line5"
    private ProgressBar progerssTankDep5Line5;

    @FXML // fx:id="buttonUpgradeDep5Line5"
    private Button buttonUpgradeDep5Line5;

    @FXML // fx:id="textLevelDep5Line6"
    private Text textLevelDep5Line6;

    @FXML // fx:id="textEfeDep5Line6"
    private Text textEfeDep5Line6;

    @FXML // fx:id="progerssTankDep5Line6"
    private ProgressBar progerssTankDep5Line6;

    @FXML // fx:id="buttonUpgradeDep5Line6"
    private Button buttonUpgradeDep5Line6;

    @FXML // fx:id="textLevelDep6Line0"
    private Text textLevelDep6Line0;

    @FXML // fx:id="textEfeDep6Line0"
    private Text textEfeDep6Line0;

    @FXML // fx:id="progerssTankDep6Line0"
    private ProgressBar progerssTankDep6Line0;

    @FXML // fx:id="buttonUpgradeDep6Line0"
    private Button buttonUpgradeDep6Line0;

    @FXML // fx:id="textLevelDep6Line1"
    private Text textLevelDep6Line1;

    @FXML // fx:id="textEfeDep6Line1"
    private Text textEfeDep6Line1;

    @FXML // fx:id="progerssTankDep6Line1"
    private ProgressBar progerssTankDep6Line1;

    @FXML // fx:id="buttonUpgradeDep6Line1"
    private Button buttonUpgradeDep6Line1;

    @FXML // fx:id="textLevelDep6Line2"
    private Text textLevelDep6Line2;

    @FXML // fx:id="textEfeDep6Line2"
    private Text textEfeDep6Line2;

    @FXML // fx:id="progerssTankDep6Line2"
    private ProgressBar progerssTankDep6Line2;

    @FXML // fx:id="buttonUpgradeDep6Line2"
    private Button buttonUpgradeDep6Line2;

    @FXML // fx:id="textLevelDep6Line3"
    private Text textLevelDep6Line3;

    @FXML // fx:id="textEfeDep6Line3"
    private Text textEfeDep6Line3;

    @FXML // fx:id="progerssTankDep6Line3"
    private ProgressBar progerssTankDep6Line3;

    @FXML // fx:id="buttonUpgradeDep6Line3"
    private Button buttonUpgradeDep6Line3;

    @FXML // fx:id="textLevelDep6Line4"
    private Text textLevelDep6Line4;

    @FXML // fx:id="textEfeDep6Line4"
    private Text textEfeDep6Line4;

    @FXML // fx:id="progerssTankDep6Line4"
    private ProgressBar progerssTankDep6Line4;

    @FXML // fx:id="buttonUpgradeDep6Line4"
    private Button buttonUpgradeDep6Line4;

    @FXML // fx:id="textLevelDep6Line5"
    private Text textLevelDep6Line5;

    @FXML // fx:id="textEfeDep6Line5"
    private Text textEfeDep6Line5;

    @FXML // fx:id="progerssTankDep6Line5"
    private ProgressBar progerssTankDep6Line5;

    @FXML // fx:id="buttonUpgradeDep6Line5"
    private Button buttonUpgradeDep6Line5;

    @FXML // fx:id="textLevelDep6Line6"
    private Text textLevelDep6Line6;

    @FXML // fx:id="textEfeDep6Line6"
    private Text textEfeDep6Line6;

    @FXML // fx:id="progerssTankDep6Line6"
    private ProgressBar progerssTankDep6Line6;

    @FXML // fx:id="buttonUpgradeDep6Line6"
    private Button buttonUpgradeDep6Line6;

    @FXML // fx:id="tankReqDep0Text"
    private Text tankReqDep0Text; // Value injected by FXMLLoader

    @FXML // fx:id="tankReqDep1Text"
    private Text tankReqDep1Text; // Value injected by FXMLLoader

    @FXML // fx:id="tankReqDep2Text"
    private Text tankReqDep2Text; // Value injected by FXMLLoader

    @FXML // fx:id="tankReqDep3Text"
    private Text tankReqDep3Text; // Value injected by FXMLLoader

    @FXML // fx:id="tankReqDep4Text"
    private Text tankReqDep4Text; // Value injected by FXMLLoader

    @FXML // fx:id="tankReqDep5Text"
    private Text tankReqDep5Text; // Value injected by FXMLLoader

    @FXML // fx:id="tankReqDep6Text"
    private Text tankReqDep6Text; // Value injected by FXMLLoader



}