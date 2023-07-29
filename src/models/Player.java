package models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;
    private Scanner scanner;
    private Long id;

    public Player(Symbol symbol, String name, PlayerType playerType, Long id) {
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
        this.id = id;
    }

    public Move makeMove(Board board){
        System.out.println("Please enter the row for move");
        int row = scanner.nextInt();
        System.out.println("Please enter the col for move");
        int col = scanner.nextInt();

        //validate the move and throw exception or msg
        return new Move(new Cell(row, col, this), this);
    }
    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
