import controller.GameController;
import exceptions.NoStrategySelectedException;
import models.*;
import strategy.botPlayingStrategy.BotPlayingStrategy;
import strategy.botPlayingStrategy.RandomBotPlayingStrategy;
import strategy.winningStrategy.WinningStrategy;
import strategy.winningStrategy.WinningStrategyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter board dimension: ");
        int size = sc.nextInt();

        System.out.println("Will there be any bot: Y/N ");
        String boPresent = sc.next();

        System.out.println("Do you want to apply win by corner: Y/N ");
        String cornerStrategy = sc.next();
        if(cornerStrategy.equals("Y")) winningStrategies.add(WinningStrategyFactory.getWinningStrategy(size, WinningStrategyEnum.BY_CORNER));

        System.out.println("Do you want to apply win by top left diagonal: Y/N ");
        String topLeftDiagonalStrategy = sc.next();
        if(topLeftDiagonalStrategy.equals("Y")) winningStrategies.add(WinningStrategyFactory.getWinningStrategy(size, WinningStrategyEnum.BY_TOP_LEFT_DIAGONAL));

        System.out.println("Do you want to apply win by bottom right diagonal: Y/N ");
        String bottomRightDiagonalStrategy = sc.next();
        if(bottomRightDiagonalStrategy.equals("Y")) winningStrategies.add(WinningStrategyFactory.getWinningStrategy(size, WinningStrategyEnum.BY_BOTTOM_RIGHT_DIAGONAL));

        System.out.println("Do you want to apply win by row: Y/N ");
        String rowStrategy = sc.next();
        if(rowStrategy.equals("Y")) winningStrategies.add(WinningStrategyFactory.getWinningStrategy(size, WinningStrategyEnum.BY_ROW));

        System.out.println("Do you want to apply win by column: Y/N ");
        String columnStrategy = sc.next();
        if(columnStrategy.equals("Y")) winningStrategies.add(WinningStrategyFactory.getWinningStrategy(size, WinningStrategyEnum.BY_COL));

        if(winningStrategies.size() == 0){
            throw new NoStrategySelectedException("Atleast one winning strategy needs to be select");
        }
        List<Player> players = new ArrayList<>();
        int iteratePlayer = size-1;

        if(boPresent.equals("Y")){
            iteratePlayer = size-2;
            System.out.println("Enter name of the bot: ");
            String playerName = sc.next();
            System.out.println("Enter the symbol: ");
            String playerSymbol = sc.next();

            BotPlayingStrategy botPlayingStrategy = new RandomBotPlayingStrategy();

            players.add(new Bot(playerName, new Symbol(playerSymbol.charAt(0)), PlayerType.BOT, BotPlayingDifficultyLevel.EASY, botPlayingStrategy));
        }

        for(int i = 0; i < iteratePlayer; i++){
            System.out.println("Enter name of the player: ");
            String playerName = sc.next();
            System.out.println("Enter the symbol: ");
            String playerSymbol = sc.next();

            players.add(new Player(playerName, new Symbol(playerSymbol.charAt(0)), PlayerType.HUMAN));
        }

        Collections.shuffle(players);
        int playerIndex = 0;
        GameController gameController = new GameController();
        Game game = gameController.createGame(size, players, winningStrategies);

        while (game.getGameState().equals(GameState.IN_PROGRESS)){
            System.out.println("Current board status");
            gameController.displayBoard(game);
            Move move = gameController.executeMove(game, players.get(playerIndex%players.size()));
            playerIndex++;
            Player player = gameController.checkWinner(game, move);
            if(player != null){
                gameController.displayBoard(game);
                System.out.println(player.getName() + " has won the game ");
                break;
            }
        }
    }
}
