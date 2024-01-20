package strategy.winningStrategy;

import models.WinningStrategyEnum;

public class WinningStrategyFactory {

    public static WinningStrategy getWinningStrategy(int dimension, WinningStrategyEnum winningStrategyEnum){
        switch (winningStrategyEnum){
            case BY_CORNER: return new CornerByWinningStrategy(dimension);
            case BY_BOTTOM_RIGHT_DIAGONAL: return new BottomRightDiagonalByWinningStrategy(dimension);
        }
        return new OrderOneWinningStrategy(dimension);
    }
}
