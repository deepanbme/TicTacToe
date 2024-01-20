package models;

import strategy.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{

    private BotPlayingDifficultyLevel botPlayingDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, PlayerType playerType, BotPlayingDifficultyLevel botPlayingDifficultyLevel, BotPlayingStrategy botPlayingStrategy) {
        super(name, symbol, playerType);
        this.botPlayingDifficultyLevel = botPlayingDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    public Move makeMove(Board board){
        Move move = botPlayingStrategy.makeMove(board, this);
        move.setPlayer(this);
        return move;
    }

    public BotPlayingDifficultyLevel getBotPlayingDifficultyLevel() {
        return botPlayingDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }
}
