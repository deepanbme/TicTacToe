package strategy.botPlayingStrategy;

import models.Board;
import models.Move;
import models.Player;

public interface BotPlayingStrategy {
    public Move makeMove(Board board, Player player);
}
