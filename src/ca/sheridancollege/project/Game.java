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
 * @modified by William Klemmer
 * @modified by Jacob O'hearon
 */
public abstract class Game {

    // the players of the game
    private ArrayList<Player> players;
     // The starting score for the user and opponent
    private int userScore = 0, oppScore = 0;
    //If the userWin or oppWin variable = true, increment userScore by adding 1.
    private final boolean userWin = false, oppWin = false;
    //The starting user deck and opponentDeck of 7 cards
    private static ArrayList<String> userDeck = new ArrayList<>(), opponentDeck = new ArrayList<>();
    // The starting game deck of 38 cards, equalling to 52 cards in total
    private static ArrayList<String>   gameDeck     = new ArrayList<>();


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
//        for (String userDeck1 : userDeck) {
//            Card c = new Card();
//            c.setCard(Card.CARDS[(int)(Math.random() * 13)]);
//            userDeck.add(c.getCard());
//        }
        for (int i = 0; i < userDeck.size(); i++)
        {
            System.out.println("Your Cards: " + userDeck.get(i));
            System.out.println("Your Turn: ");
        }
    }
    
    //The opponentTurn method that will be executed when the user gets Go Fish or gets a correct card.
    public static void opponentTurn() {
        for (int i = 0; i < userDeck.size(); i++)
        {
            Card c = new Card();
            c.setCard(Card.CARDS[(int)(Math.random() * 13)]);
            userDeck.set(i, c.getCard());
        }
        
        //Displaying the user Cards to update the user despite being the opponents turn
        System.out.println("Your Cards: ");
        for (int i=0; i < userDeck.size(); i++) 
        {
            System.out.println(userDeck.get(i));
        }
        
        //The opponent will guess for a random card everytime its the opponent turn, with the user replying with y or n
        Card opponentGuess = new Card();
        opponentGuess.setCard(Card.CARDS[(int)(Math.random() * 13)]);
        System.out.println("Opponent: Do you have a " + opponentGuess.getCard() + "? (y/n): ");
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
//        System.out.println("Type in your Username: ");
//        String userName = input.nextLine();
//        userName = Player.getName();
        
        System.out.println("Hello and welcome to Go Fish!"
                + "\n-------------------------------"
                + "\nInstructions:"
                + "\nYou will be playing against a bot, each will recieve 7 random cards from the deck. "
                + "\nYour goal is to ask for a card from the opponent to add to your deck. "
                + "\nIf you have 4 of the same, that is one point. "
                + "\nThe winner will be declared by who has more points at the end. May the best player win!");
        
        //Creating the user deck of random cards
        for (int i = 0; i < 7; i++)
        {
            Card c = new Card();
            c.setCard(Card.CARDS[(int)(Math.random() * 13)]);
            userDeck.add(c.getCard());
        }
        
        //Creating the game deck of 38 cards
        for (int i = 0; i < gameDeck.size(); i++)
        {
            Card deck = new Card();
            deck.setCard(Card.CARDS[(int)(Math.random() * 13)]);
            gameDeck.add(deck.getCard()); 
        }
        
        //Displaying the users deck of 7 cards
        System.out.println("Your Deck: ");
        for (String str : userDeck) 
        {
            System.out.println(str);
        }
        
        //Creating the opponents deck of cards
        for (int j = 0; j <= 7; j++)
        {
            Card opponentCard = new Card();
            opponentCard.setCard(Card.CARDS[(int)(Math.random() * 13)]);
            opponentDeck.add(opponentCard.getCard());
        }
        
        //Getting the user input for the first card to add to their deck
        System.out.println("Please enter a card to add to your deck: ");
        Card.userSuit = input.nextLine();
        
        //Creating the opponents deck of cards
        for (int j = 0; j < 7; j++)
        {
            Card opponentCard = new Card();
            opponentCard.setCard(Card.CARDS[(int)(Math.random() * 13)]);
            opponentDeck.set(j, opponentCard.getCard());
        
            /*If the user guess matches, execute this line and add that guessed card to the user deck, and subtract it from 
            the opponents deck.*/
            if(Card.userSuit.equals(opponentCard.getCard().toLowerCase())) 
            {
                System.out.println("------------------------------- \nOpponent: Yes, I have " + Card.userSuit); 
                userTurn();
            }
            else 
            {
                userGoFish();
                opponentTurn();
            }
        }
    }

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public void declareWinner() {
        
        boolean loop = true;
        while(loop == true){
        
        if(userScore > oppScore)
        {
            System.out.println(userScore + "\n" + oppScore + "\nGG! You Won!");
        }
        else if(oppScore > userScore)
        {
            System.out.println(userScore + "\n" + oppScore + "\nSorry, you lost.");
        }
        else
        {
            System.out.println(userScore + "\n" + oppScore + "\nLooks like it was a tie!");
        }
        
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to try again? (y/n) ");
                String answer = input.nextLine();
                
                loop = input.nextLine().trim().equalsIgnoreCase("y");
                
                if(loop == false)
                    System.out.println("Thank you for playing!");
        }
            
    }

}//end class
