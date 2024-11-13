package tecinfo.poo.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tecinfo.poo.Main;
import tecinfo.poo.model.DatabaseConnection;
import tecinfo.poo.model.PlayerScore;

public class RankingController {
    private Main main;
    private DatabaseConnection dbConnection;

    @FXML
    private TableView<PlayerScore> rankingTableView;
    @FXML
    private TableColumn<PlayerScore, String> nameColumn;
    @FXML
    private TableColumn<PlayerScore, Integer> winsColumn;

    public void initialize(Main main, DatabaseConnection dbConnection) {
    this.main = main;
    this.dbConnection = dbConnection;

    // Configurar colunas
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

    // Carregar e exibir o ranking
    updateRankingTable();
}

private void updateRankingTable() {
    List<PlayerScore> playerScores = dbConnection.getTopScores();
    ObservableList<PlayerScore> rankingData = FXCollections.observableArrayList(playerScores);
    rankingTableView.setItems(rankingData);
}



    private void updateRanking() {
        List<PlayerScore> scores = dbConnection.getTopScores();
        rankingTableView.getItems().clear();
        rankingTableView.getItems().addAll(scores);
    }

    @FXML
    private void handleBackToStart() {
        main.showStartScreen();
    }
}