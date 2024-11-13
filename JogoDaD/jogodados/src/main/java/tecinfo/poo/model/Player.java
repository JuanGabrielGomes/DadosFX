package tecinfo.poo.model;

public class Player {
    private String name;
    private int bet;
    private int wins;

    public Player(String name) {
        this.name = name;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }
    
    public int getBet() {
        return bet;
    }
    
    public void setBet(int bet) {
        this.bet = bet;
    }
    
    public int getWins() {
        return wins;
    }
    
    public void addWin() {
        this.wins++;
    }
}