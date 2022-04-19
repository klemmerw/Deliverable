/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public class Card {
    //default modifier for child classes

    //Variable suit and userSuit for the game class
    private String suit;
    public static String userSuit;
    
    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    
    //The array created for each type of suit
    public static final String [] CARDS = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};
    
    public Card() {
    }
    
    public Card(String  suit) {
        this.suit = suit;
    }
    
//    @Override
//    public abstract String toString();

    /**
     * @return the suit
     */
    public String getCard() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setCard(String suit) {
        this.suit = suit;
    }

    /**
     * @return the userSuit
     */
    public static String getUserCard() {
        return userSuit;
    }

    /**
     * @param userCard the userCard to set
     */
    public void setUserCard(String userCard) {
        this.userSuit = userCard;
    }
}
