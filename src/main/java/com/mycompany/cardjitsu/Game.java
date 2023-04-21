package com.mycompany.cardjitsu;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

public class Game {

    private final String[] playerIds;
    public final GroupOfCards deck;
    private final ArrayList<ArrayList<Card>> playerHand;
    private final ArrayList<Card> stockPile;
    private int currentPlayer;
    private int[] playerWins;
    private Card topCard;

    public Game(String[] pids) {
        playerIds = pids;
        deck = new GroupOfCards();
        deck.shuffle();
        stockPile = new ArrayList<>();
        playerHand = new ArrayList<>();

        for (int i = 0; i < pids.length; i++) {
            ArrayList<Card> hand = new ArrayList<>(Arrays.asList(deck.drawCard()));
            playerHand.add(hand);
        }
        playerWins = new int[playerIds.length];
    }

    public void start(Game game) {
        topCard = deck.drawCard();
    }

    public Card getTopCard() {
        return topCard;
    }

    public ImageIcon getTopCardImage() {
        return new ImageIcon(topCard.getElement() + "_" + topCard.getValue() + ".png");
    }

    public boolean isGameOver() {
        for (int wins : playerWins) {
            if (wins == 3) {
                return true;
            }
        }
        return false;
    }

    public String getCurrentPlayer() {
        return playerIds[currentPlayer];
    }

    public String getPreviousPlayer(int i) {
        int index = currentPlayer - i;
        if (index < 0) {
            index = playerIds.length + index;
        }
        return playerIds[index];
    }

    public String[] getPlayers() {
        return playerIds;
    }

    public ArrayList<Card> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size();
    }

    public Card getPlayerCard(String pid, int choice) {
        ArrayList<Card> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty();
    }

    public void submitDraw(String pid) {
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        getPlayerHand(pid).add(deck.drawCard());

        currentPlayer = (currentPlayer + 1) % playerIds.length;
    }

public void submitCard(String pid, int choice) {
    ArrayList<Card> hand = getPlayerHand(pid);
    Card card = hand.get(choice);

    hand.remove(choice);
    stockPile.add(topCard);
    topCard = card;

    int index = Arrays.asList(playerIds).indexOf(pid);
    if (card.getElement() == topCard.getElement() && card.getValue().ordinal() > topCard.getValue().ordinal()) {
        playerWins[index]++;
    } else if (card.getElement() == topCard.getElement() && card.getValue().ordinal() == topCard.getValue().ordinal()) {
        // draw
    } else {
        int previousPlayerIndex = (currentPlayer - 1 < 0) ? playerIds.length - 1 : currentPlayer - 1;
        playerWins[previousPlayerIndex]++;
    }

    currentPlayer = (currentPlayer + 1) % playerIds.length;
}



public String getWinner() {
    for (int i = 0; i < playerWins.length; i++) {
        if (playerWins[i] == 3) {
            return playerIds[i];
        }
    }
    return null;
}

public void reset() {
    deck.replaceDeckWith(stockPile);
    deck.shuffle();
    stockPile.clear();
    playerWins = new int[playerIds.length];
    currentPlayer = 0;
    for (ArrayList<Card> hand : playerHand) {
        hand.clear();
        hand.add(deck.drawCard());
    }
}
}
