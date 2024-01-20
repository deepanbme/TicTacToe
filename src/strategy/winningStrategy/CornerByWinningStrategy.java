package strategy.winningStrategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;

public class CornerByWinningStrategy implements WinningStrategy{
    private HashMap<Character, Integer> cornerSymbol = new HashMap<>();
    private int dimension;
    private int symbolsAdded;

    public CornerByWinningStrategy(int dimension) {
        this.dimension = dimension;
        this.symbolsAdded = 0;
    }

    private boolean isCellCorner(int row, int col){
        if(row == 0 || row == dimension-1){
            return col == 0 || col == dimension-1;
        }
        return false;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        Player player = lastMove.getPlayer();
        if(checkForCorner(row, col,symbol, lastMove ) != null) return player;
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
