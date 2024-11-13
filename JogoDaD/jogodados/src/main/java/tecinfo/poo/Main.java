package tecinfo.poo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tecinfo.poo.controller.GameController;
import tecinfo.poo.controller.RankingController;
import tecinfo.poo.controller.StartController;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.DiceGame;
import tecinfo.poo.model.Player;

public class Main extends Application {
    private Stage primaryStage;
    private DatabaseConnection dbConnection = new DatabaseConnection();
    private DiceGame diceGame = new DiceGame();
    private List<Player> players = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showStartScreen();
    }

    public void showStartScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/startScreen.fxml"));
            Pane root = loader.load();
            StartController startController = loader.getController();
            startController.initialize(this, players);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGameScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/gameScreen.fxml"));
            Pane root = loader.load();
            GameController gameController = loader.getController();
            gameController.initialize(this, diceGame, dbConnection, players);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRankingScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/rankingScreen.fxml"));
            Pane root = loader.load();
            RankingController rankingController = loader.getController();
            rankingController.initialize(this, dbConnection);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
