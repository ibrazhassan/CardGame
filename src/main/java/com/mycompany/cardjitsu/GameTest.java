package com.mycompany.cardjitsu;


import com.mycompany.cardjitsu.Card;
import com.mycompany.cardjitsu.Game;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;
    private String[] playerIds = {"Player 1", "Player 2", "Player 3"};

    @Before
    public void setUp() {
        game = new Game(playerIds);
    }

    //Test the SubmitDraw Method
    @Test
    public void testSubmitDraw() {
        String pid = game.getCurrentPlayer();
        ArrayList<Card> hand = game.getPlayerHand(pid);
        int initialHandSize = hand.size();

        game.submitDraw(pid);

        assertEquals(initialHandSize + 1, game.getPlayerHandSize(pid));
        assertFalse(game.deck.isEmpty());
    }
    
    
    //Test the SubmitCard Method
    @Test
    public void testSubmitCard() {
        String[] players = {"Alice", "Bob", "Charlie"};
        Game game = new Game(players);
        game.start(game);
        game.submitCard("Alice", 0);
        assertEquals(new Card(Card.Element.Fire, Card.Value.Two), game.getTopCard());
        assertEquals(1, game.getPlayerHandSize("Alice"));
        assertEquals(2, game.getPlayerHandSize("Bob"));
        assertEquals(1, game.getPlayerHandSize("Charlie"));
        assertEquals("Bob", game.getCurrentPlayer());
        game.submitCard("Bob", 0);
        assertEquals(new Card(Card.Element.Water, Card.Value.Two), game.getTopCard());
        assertEquals(1, game.getPlayerHandSize("Alice"));
        assertEquals(1, game.getPlayerHandSize("Bob"));
        assertEquals(1, game.getPlayerHandSize("Charlie"));
        assertEquals("Charlie", game.getCurrentPlayer());
        game.submitCard("Charlie", 0);
        assertEquals(new Card(Card.Element.Snow, Card.Value.Two), game.getTopCard());
        assertEquals(2, game.getPlayerHandSize("Alice"));
        assertEquals(1, game.getPlayerHandSize("Bob"));
        assertEquals(1, game.getPlayerHandSize("Charlie"));
        assertEquals("Alice", game.getCurrentPlayer());
        game.submitCard("Alice", 0);
        assertEquals(new Card(Card.Element.Fire, Card.Value.Three), game.getTopCard());
        assertEquals(1, game.getPlayerHandSize("Alice"));
        assertEquals(1, game.getPlayerHandSize("Bob"));
        assertEquals(1, game.getPlayerHandSize("Charlie"));
        assertEquals("Bob", game.getCurrentPlayer());
    }

    
    //Test the Player Win Method
    @Test
    public void testSubmitCardPlayerWins() {
        String[] playerIds = {"Alice", "Bob"};
        Game game = new Game(playerIds);
        game.start(game);
        game.submitCard("Alice", 0);
        game.submitCard("Bob", 0);
        game.submitCard("Alice", 0);
        game.submitCard("Bob", 0);
        game.submitCard("Alice", 0);
        game.submitCard("Bob", 0);
        String winner = game.getWinner();
        assertEquals("Alice", winner);
    }
}

