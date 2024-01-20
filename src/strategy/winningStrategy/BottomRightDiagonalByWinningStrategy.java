package strategy.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;

public class BottomRightDiagonalByWinningStrategy implements WinningStrategy{
    private int dimension;
    private int symbolsAdded;

    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();

    public BottomRightDiagonalByWinningStrategy(int dimension) {
        this.dimension = dimension;
        this.symbolsAdded = 0;
    }

    private boolean isCellBottomRightDiagonal(int row, int col){
        return row+col == dimension-1;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        Player player = lastMove.getPlayer();
        if(checkWinnerForBottomRightDiagonal(row, col, symbol, lastMove) != null) return player;
        return null;
    }

    private Player checkWinnerForBottomRightDiagonal(int row, int col, char symbol, Move lastMove){
        if(isCellBottomRightDiagonal(row, col)){
            if(!bottomLeftDiagonalSymbolCount.containsKey(symbol)) bottomLeftDiagonalSymbolCount.put(symbol, 0);
            bottomLeftDiagonalSymbolCount.put(symbol, bottomLeftDiagonalSymbolCount.get(symbol)+1);

            if(bottomLeftDiagonalSymbolCount.get(symbol) == dimension) return lastMove.getPlayer();
        }
        return null;
    }

}
