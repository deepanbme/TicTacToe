package models;

import java.util.Scanner;

public class Player {
    private static int idCounter = 0;
    private String name;
    private int id;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.id = idCounter++;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board){
        System.out.println(this.getName() + "! Please enter the row to play");
        int row = scanner.nextInt();
        System.out.println(this.getName() + "! Please enter the col to play");
        int col = scanner.nextInt();

        board.getBoard().get(row).get(col).setPlayer(this);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        return new Move(this, new Cell(this, row, col));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
