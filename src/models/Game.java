package models;

import exceptions.DuplicateSymbolException;
import exceptions.InvalidBoardDimensionException;
import exceptions.InvalidBotCountException;
import exceptions.InvalidNumberOfPlayerException;
import strategy.WinningStrategy;

import java.util.*;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayer;
    private List<WinningStrategy> winningStrategies;

    private Game(List<Player> players, Board board, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = board;
        this.moves = new ArrayList<Move>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayer = 0;
        this.winningStrategies = winningStrategies;
    }

    public static class Builder{
        private List<WinningStrategy> winningStrategies;
        private List<Player> players;
        int dimension;

        public static Builder builder(){
            return new Builder();
        }

        private Builder() {
            this.winningStrategies = new ArrayList<WinningStrategy>();
            this.players = new ArrayList<Player>();
            this.dimension = 0;
        }

        public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
        }

        public void setPlayers(List<Player> players) {
            this.players = players;
        }

        public void setDimension(int dimension) {
            this.dimension = dimension;
        }

        public void addPlayer(Player player){
            players.add(player);
        }
        public void addWinningStrategy(WinningStrategy winningStrategy){
            winningStrategies.add(winningStrategy);
        }

        private void validateBotCounts(){
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)) botCount++;
            }

            if(botCount > 1){
                throw  new InvalidBotCountException("Bot count is more than 1");
            }
        }

        private void validateDimensions(){
            if(dimension<3 || dimension > 10){
                throw new InvalidBoardDimensionException("Board dimension should be between 3-10");
            }
        }

        private void validateNumberOfPlayer(){
            if(players.size() != dimension-1){
                throw new InvalidNumberOfPlayerException("Number of players should be dimension-1");
            }
        }

        private void validateUniqueSymbolsForAllPlayers(){
            HashSet<Character> set = new HashSet<>();

            for(Player player : players){
                set.add(player.getSymbol().getSymbolChar());
            }

            if(set.size() != players.size()){
                throw new DuplicateSymbolException("Symbols already chosen by other players");
            }
        }

        private void validate(){
            validateBotCounts();
            validateDimensions();
            validateNumberOfPlayer();
            validateUniqueSymbolsForAllPlayers();
        }


        private Game build(){
            validate();
            return new Game(players, new Board(dimension), winningStrategies);
        }
    }


}
