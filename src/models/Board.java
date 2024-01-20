package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;

    private List<List<Cell>> board;

    public Board(int dimension) {
        this.size = dimension;
        this.board = new ArrayList<>();

        for(int i = 0; i < dimension; i++){
            this.getBoard().add(new ArrayList<>());
            for(int j = 0; j < dimension; j++){
                this.getBoard().get(i).add(new Cell(i, j));
            }
        }
    }

    public void display(){
        for(int i = 0; i < size; i++){
            List<Cell> cells = board.get(i);
            for(int j = 0; j < size; j++){
                cells.get(j).displayCell();
            }
            System.out.println();
        }
        System.out.println("-----");
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

}
