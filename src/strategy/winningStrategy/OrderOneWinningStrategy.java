package strategy.winningStrategy;

import exceptions.GameDrawnException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{

    private int dimension;
    private int symbolAdded;
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> cornerSymbol = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        this.symbolAdded = 0;
        for(int i = 0; i < this.dimension; i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    private boolean isCellTopLeftDiagonal(int row, int col){
        return row==col;
    }


    private boolean isCellBottomRightDiagonal(int row, int col){
        return row+col == dimension-1;
    }

    private boolean isCellCorner(int row, int col){
        if(row == 0 || row == dimension-1){
            return col == 0 || col == dimension-1;
        }
        return false;
    }


    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolAdded++;
        Player player = lastMove.getPlayer();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        char playSymbol = player.getSymbol().getSymbolChar();
        if(checkForCorner(row, col, playSymbol, lastMove) != null) return player;
        else if(checkWinnerForCol(row, col, playSymbol, lastMove) != null) return player;
        else if(checkWinnerForRow(row, col, playSymbol, lastMove) != null) return player;
        else if(checkWinnerForDiagonal(row, col, playSymbol, lastMove) != null) return player;
        if(symbolAdded == dimension*dimension) throw new GameDrawnException("Game is draw no more moves possible");
        return null;
    }

    private Player checkWinnerForRow(int row, int col, char symbol, Move lastMove){
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol, 0);
        }
        rowSymbolCount.get(row).put(symbol,  rowSymbolCount.get(row).get(symbol)+1);
        if(rowSymbolCount.get(row).get(symbol) == dimension) return lastMove.getPlayer();
        return null;
    }

    private Player checkWinnerForCol(int row, int col, char symbol, Move lastMove){
        if(!colSymbolCount.get(col).containsKey(symbol)) colSymbolCount.get(col).put(symbol, 0);
        colSymbolCount.get(col).put(symbol,  colSymbolCount.get(col).get(symbol)+1);
        if(colSymbolCount.get(col).get(symbol) == dimension) return lastMove.getPlayer();
        return null;
    }

    private Player checkWinnerForDiagonal(int row, int col, char symbol, Move lastMove){
        if(isCellTopLeftDiagonal(row, col)){
            if(!topLeftDiagonalSymbolCount.containsKey(symbol)) topLeftDiagonalSymbolCount.put(symbol, 0);
            topLeftDiagonalSymbolCount.put(symbol, topLeftDiagonalSymbolCount.get(symbol)+1);

            if(topLeftDiagonalSymbolCount.get(symbol) == dimension) return lastMove.getPlayer();
        }

        if(isCellBottomRightDiagonal(row, col)){
            if(!bottomLeftDiagonalSymbolCount.containsKey(symbol)) bottomLeftDiagonalSymbolCount.put(symbol, 0);
            bottomLeftDiagonalSymbolCount.put(symbol, bottomLeftDiagonalSymbolCount.get(symbol)+1);

            if(bottomLeftDiagonalSymbolCount.get(symbol) == dimension) return lastMove.getPlayer();
        }
        return null;
    }

    private Player checkForCorner(int row, int col, char symbol, Move lastMove){
        if(isCellCorner(row, col)){
            if(!cornerSymbol.containsKey(symbol)) cornerSymbol.put(symbol, 0);
            cornerSymbol.put(symbol, cornerSymbol.get(symbol)+1);
            if(cornerSymbol.get(symbol) == 4) return lastMove.getPlayer();
        }

        return null;
    }
}
