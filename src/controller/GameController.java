package controller;

import models.*;
import strategy.winningStrategy.WinningStrategy;
import strategy.winningStrategy.WinningStrategyFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        try{
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
        }catch (Exception e){
            System.out.println("Could not start game, something went wrong");
        }

        return null;
    }

    public void displayBoard(Game game){
        game.getBoard().display();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public Move executeMove(Game game, Player player){
        Move move = player.makeMove(game.getBoard());
        game.getMoves().add(move);
        return move;
    }

    public Player checkWinner(Game game, Move lastMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player = winningStrategy.checkWinner(game.getBoard(), lastMove);
            if(player != null) return player;
        }
        return null;
    }
}
