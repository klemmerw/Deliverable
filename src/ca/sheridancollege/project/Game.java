/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Game {

    private static String name = "Go Fish";//the title of the game
    private ArrayList<Player> players;// the players of the game
    private int userScore = 0; // The starting score for the user
    private final int oppScore = 0; // The starting score for the opponent
    private final boolean userWin = false; // If the userWin variable = true, increment userScore by adding 1.
    private final boolean oppWin = false; // If the oppWin variable = true, increment the oppScore by adding 1.   
    private static Card[] userDeck = new Card[7]; //The starting user deck of 7 cards
    private static Card[] opponentDeck = new Card[7]; //The starting opponent deck of 7 cards
    private static Card[] gameDeck = new Card[38]; // The starting game deck of 38 cards, equalling to 52 cards in total

    public Game(String name) {
        this.name = name;
        players = new ArrayList();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    public abstract void play();
    
    //The userGoFish method that will print out Go Fish when it is the opponents turn
    public static void userGoFish() {
        System.out.println("------------------------------- \nOpponent: Go Fish!"
                        + "\nA card was added to your deck.");
    }
    
    //The opponentGoFish method that will print Go Fish when its the users turn
    public static void opponentGoFish() {
        System.out.println("------------------------------- \nYou: Go Fish!"
                        + "\nA card was added to there deck.");
    }
    
    //The userTurn method that will be executed after the opponents turn
    public static void userTurn(){
        for (int i = 0; i < userDeck.length; i++)
        {
            Card c = new Card();
            c.setSuit(Card.SUITS[(int)(Math.random() * 4)]);
            userDeck[i] = c;
        }
        for (Card card : userDeck) 
        {
            System.out.println("Your Cards: " + card.getSuit());
            System.out.println("Your Turn: ");
        }
    }
    
    //The opponentTurn method that will be executed when the user gets Go Fish or gets a correct card.
    public static void opponentTurn() {
        for (int i = 0; i < userDeck.length; i++)
        {
            Card c = new Card();
            c.setSuit(Card.SUITS[(int)(Math.random() * 4)]);
            userDeck[i] = c;
        }
        
        //Displaying the user Cards to update the user despite being the opponents turn
        System.out.println("Your Cards: ");
        for (Card card : userDeck) 
        {
            System.out.println(card.getSuit());
        }
        
        //The opponent will guess for a random card everytime its the opponent turn, with the user replying with y or n
        Card opponentGuess = new Card();
        opponentGuess.setSuit(Card.SUITS[(int)(Math.random() * 4)]);
        System.out.println("Opponent: Do you have a " + opponentGuess.getSuit() + "? (y/n): ");
        Scanner input = new Scanner(System.in);
        char cardGuess = 'y';
        
        //A try-catch block to catch a string index out of bounds exception
        try {
        cardGuess = input.next().charAt('y');
        } 
        catch (StringIndexOutOfBoundsException sioobe) {
        if(cardGuess == 'y')
        {
            System.out.println("------------------------------- \nOpponent: Yes, I have " + Card.userSuit); 
        }
        else {
            opponentGoFish();
        }
        userTurn();
        }
    }
    
    //The main method which will ask for the username and display the instructions
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        System.out.println("Type in your Username: ");
        String userName = input.nextLine();
        userName = Player.getName();
        
        System.out.println("Hello " + userName + ", Welcome to " + name + "! \nInstructions:  "
                + "\n-------------------------------"
                + "\nYou will be playing against a bot, each will recieve 7 random cards from the deck. "
                + "\nYour goal is to ask for a card from the opponent to add to your deck. "
                + "\nIf you have 4 of the same, that is one point. "
                + "\nThe winner will be declared by who has more points at the end. May the best player win!");
        
        //Creating the user deck of random cards
        for (int i = 0; i < userDeck.length; i++)
        {
            Card c = new Card();
            c.setSuit(Card.SUITS[(int)(Math.random() * 4)]);
            userDeck[i] = c;
        }
        
        //Creating the game deck of 38 cards
        for (int i = 0; i < gameDeck.length; i++)
        {
            Card deck = new Card();
            deck.setSuit(Card.SUITS[(int)(Math.random() * 4) + 34]);
            gameDeck[i] = deck; 
        }
        
        //Displaying the users deck of 7 cards
        System.out.println("Your Deck: ");
        for (Card card : userDeck) 
        {
            System.out.println(card.getSuit());
        }
        
        //Creating the opponents deck of cards
        for (int j = 0; j < opponentDeck.length; j++)
        {
            Card opponentCard = new Card();
            opponentCard.setSuit(Card.SUITS[(int)(Math.random() * 4)]);
            opponentDeck[j] = opponentCard;
        }
        
        //Getting the user input for the first card to add to their deck
        System.out.println("Please enter a card to add to your deck: ");
        Card.userSuit = input.nextLine();
        
        //Creating the opponents deck of cards
        for (int j = 0; j < opponentDeck.length; j++)
        {
            Card opponentCard = new Card();
            opponentCard.setSuit(Card.SUITS[(int)(Math.random() * 4)]);
            opponentDeck[j] = opponentCard;
        
        /*If the user guess matches, execute this line and add that guessed card to the user deck, and subtract it from 
        the opponents deck.*/
        if(Card.userSuit.equals(opponentCard.getSuit().toLowerCase())) {
            System.out.println("------------------------------- \nOpponent: Yes, I have " + Card.userSuit); 
            userTurn();
        }
        else {
            userGoFish();
            opponentTurn();
        }
        }
    }

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public abstract void declareWinner();

}//end class
