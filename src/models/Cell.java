package models;

public class Cell {

    private Player player;
    private CellState cellState;
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public Cell(Player player, int row, int col) {
        this.player = player;
        this.cellState = CellState.FILLED;
        this.row = row;
        this.col = col;
    }

    public void displayCell(){
        if(player == null){
            System.out.print("| |");
        } else if(cellState.equals(CellState.BLOCKED)){
            System.out.print("||||");
        } else {
            System.out.print("|" + player.getSymbol().getSymbolChar()+ "|");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
