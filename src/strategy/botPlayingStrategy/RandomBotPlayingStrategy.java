package strategy.botPlayingStrategy;

import models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move makeMove(Board board, Player player) {
        int dimension = board.getSize();
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    board.getBoard().get(i).get(j).setPlayer(player);
                    board.getBoard().get(i).get(j).setCellState(CellState.FILLED);
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null;
    }
}
