package com.mycompany.cardjitsu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author Syed Ghazi
 */
public class GroupOfCards {

    private Card[] cards;
    private int cardsInDeck;
    
    public GroupOfCards() {
        cards = new Card[40];
        reset();
    }
    
public void reset() {
    
    Card.Element[] elements = Card.Element.values();
    cardsInDeck = 0;
    
    for(int i = 1; i < elements.length; i++) {
        Card.Element element = elements[i];
        
        for(int j = 0; j < Card.Value.values().length; j++) {
            Card.Value value = Card.Value.getValue(j);
            cards[cardsInDeck++] = new Card(element, value);
            cards[cardsInDeck++] = new Card(element, value);
        }
    }
}
    
    public void replaceDeckWith(ArrayList<Card> cards) {
        this.cards = cards.toArray(new Card[cards.size()]);
        this.cardsInDeck = this.cards.length;
    }
    
    public boolean isEmpty() {
        return cardsInDeck == 0;
    }
    
    public void shuffle() {
        int n = cards.length;
        Random random = new Random();
        
        for(int i = 0; i < cards.length; i++) {
            int randomValue = i + random.nextInt(n - i);
            Card randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }
    
    public Card drawCard() throws IllegalArgumentException {
        if (isEmpty()) {
            throw new IllegalArgumentException("No more cards in Deck!");
        }
        if (cardsInDeck == 0) {
            // handle empty deck, e.g., by shuffling a new deck
        }
        return cards[--cardsInDeck];
    }
    
    public ImageIcon drawCardImage() {
        if (isEmpty()) {
            // handle empty deck, e.g., by shuffling a new deck
        }
        return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
    }
    
    public int getSize() {
        return cardsInDeck;
    }
}
