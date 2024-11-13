package tecinfo.poo.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tecinfo.poo.Main;
import tecinfo.poo.model.Player;

public class StartController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField betTextField;
    @FXML
    private ListView<String> playerListView;
    @FXML
    private Button startGameButton;

    private Main main;
    private List<Player> players;
    private ObservableList<String> playerListDisplay;

    public void initialize(Main main, List<Player> players) {
        this.main = main;
        this.players = players;
        this.playerListDisplay = FXCollections.observableArrayList();
        playerListView.setItems(playerListDisplay);
    }

    @FXML
    private void handleAddPlayer() {
        String name = nameTextField.getText().trim();
        String betText = betTextField.getText().trim();
        
        if (name.isEmpty() || betText.isEmpty()) {
            System.out.println("Nome e aposta são obrigatórios.");
            return;
        }

        int bet;
        try {
            bet = Integer.parseInt(betText);
            if (bet < 2 || bet > 12) {
                System.out.println("A aposta deve ser entre 2 e 12.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("A aposta deve ser um número inteiro.");
            return;
        }

        Player newPlayer = new Player(name);
        newPlayer.setBet(bet);
        players.add(newPlayer);
        playerListDisplay.add(name + " - Aposta: " + bet);

        // Clear the input fields after adding the player
        nameTextField.clear();
        betTextField.clear();
    }

    @FXML
    private void handleStartGame() {
        if (players.isEmpty()) {
            System.out.println("Adicione pelo menos um jogador para começar o jogo.");
            return;
        }
        
        main.showGameScreen();
    }
}
