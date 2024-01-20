package models;

import exceptions.DuplicateSymbolException;
import exceptions.InvalidBoardDimensionException;
import exceptions.InvalidBotCountException;
import exceptions.InvalidNumberOfPlayerException;
import strategy.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {

    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private List<WinningStrategy> winningStrategies;
    private GameState gameState;

    private Game(List<Player> players, Board board, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = board;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private void validateBotCount(){
            int botCount = 0;

            for(Player player : players){
                if (player.getPlayerType().equals(PlayerType.BOT)) botCount++;
            }

            if(botCount > 1) throw new InvalidBotCountException("Bot cannot be more than 1");
        }

        private void validateDimension(){
            if(this.getDimension() < 3 || this.getDimension() > 10) throw new InvalidBoardDimensionException("Board size should be between 3 and 10");
        }

        private void validateNumberOfPlayers(){
            if(players.size() != this.dimension-1) throw new InvalidNumberOfPlayerException("number of players should be dimension-1");
        }

        private void validateUniqueSymbols(){
            HashSet<Character> set = new HashSet<>();
            for(Player player : players){
                set.add(player.getSymbol().getSymbolChar());
            }

            if(players.size() != set.size()) throw new DuplicateSymbolException("duplicate symbols were chosen");
        }

        private void validate(){
            validateDimension();
            validateBotCount();
            validateNumberOfPlayers();
            validateUniqueSymbols();
        }

        public Game build(){
            validate();
            return new Game(players, new Board(dimension), winningStrategies);
        }
    }
}
