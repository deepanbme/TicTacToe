package strategy.botPlayingStrategy;

import models.BotPlayingDifficultyLevel;

public class BotPlayingFactory {

    public BotPlayingStrategy getBotPlayingStrategyForDifficulty(BotPlayingDifficultyLevel level){
        switch (level){
            case EASY: return new RandomBotPlayingStrategy();
            case MEDIUM: return new RandomBotPlayingStrategy();
            case DIFFICULT: return new RandomBotPlayingStrategy();
        }
        return null;
    }
}
