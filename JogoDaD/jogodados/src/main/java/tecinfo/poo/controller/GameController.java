package tecinfo.poo.controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tecinfo.poo.Main;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.DiceGame;
import tecinfo.poo.model.Player;

public class GameController {
    private Main main;
    private DiceGame diceGame;
    private DatabaseConnection dbConnection;
    private List<Player> players;

    @FXML
    private Label resultLabel;
    @FXML
    private Label winnerLabel;
    @FXML
    private ImageView dice1ImageView;
    @FXML
    private ImageView dice2ImageView;
    @FXML
    private Button viewRankingButton;

    public void initialize(Main main, DiceGame diceGame, DatabaseConnection dbConnection, List<Player> players) {
        this.main = main;
        this.diceGame = diceGame;
        this.dbConnection = dbConnection;
        this.players = players;
    }

    @FXML
private void handleRollDice() {
    int[] results = diceGame.rollDice();
    int diceTotal = results[0] + results[1];
    updateDiceDisplay(results);

    resultLabel.setText("Total: " + diceTotal);

    Player winner = null;
    for (Player player : players) {
        if (diceGame.checkWin(player, diceTotal)) {
            player.addWin();
            dbConnection.saveScore(player.getName(), player.getWins()); // Atualiza o número de vitórias no banco
            winner = player;
        }
    }
    
    if (winner != null) {
        winnerLabel.setText("Vencedor: " + winner.getName() + " (Aposta: " + winner.getBet() + ")");
    } else {
        winnerLabel.setText("Nenhum vencedor nesta rodada.");
    }
}


    private void updateDiceDisplay(int[] results) {
        String dice1_image = "dice" + results[0] + ".jpg";
        dice1ImageView.setImage(new Image(dice1_image));
        String dice2_image = "dice" + results[1] + ".jpg";
        dice2ImageView.setImage(new Image(dice2_image));
    }

    @FXML
    private void handleViewRanking() {
        main.showRankingScreen();
    }

    @FXML
    private void handleBackToStart() {
        main.showStartScreen();
    }
}
