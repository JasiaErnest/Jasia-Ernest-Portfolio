using System;
using System.Collections.Generic;
using System.Threading;

public class GoFishGame
{
    private Deck deck;
    private Hand playerHand;
    private Hand opponentHand;
    private List<Card> playerBooks;
    private List<Card> opponentBooks;
    private IEnumerator<Card> playerIterator;
    private IEnumerator<Card> opponentIterator;
    private bool playerTurn;
    private int lastOpponentRank;

    // Constructor, initializes the game. Populates the deck, shuffles it, and deals hands to the players.
    public GoFishGame()
    {
        deck = new Deck();
        deck.Shuffle();

        playerHand = new Hand();
        opponentHand = new Hand();

        for (int i = 0; i < 7; i++)
        {
            playerHand.AddCard(deck.GetCards()[0]);
            deck.GetCards().RemoveAt(0);
            opponentHand.AddCard(deck.GetCards()[0]);
            deck.GetCards().RemoveAt(0);
        }

        playerBooks = new List<Card>();
        opponentBooks = new List<Card>();
        playerTurn = true;
        lastOpponentRank = -1;
    }

    // Method to play the game
    public void PlayGame()
    {
        // var scanner = new Scanner();
        AnnounceGameStart();

        // Pause for a second to simulate thinking
        Thread.Sleep(2000);

        // Main game loop
        while (playerBooks.Count < 7 && opponentBooks.Count < 7)
        {
            if (playerTurn)
            {
                Console.WriteLine("Your turn!");

                // Pause for a second to simulate thinking
                Thread.Sleep(500);
                Console.Write("Your hand: ");
                PrintHand(playerHand);
                Console.WriteLine();
                Console.Write("Your books: ");
                DisplayBooks(playerBooks);
                Console.Write("Enter the rank you want to ask for (2-14, where 11=J, 12=Q, 13=K, 14=A): ");
                int playerRank = int.Parse(Console.ReadLine());
                PlayerTurn(playerRank);

                // Pause for a second to simulate thinking
                Thread.Sleep(1000);
            }
            else
            {
                if (deck.Size() == 0)
                {
                    Console.WriteLine("Deck is empty! Game over!");
                    break;
                }
                OpponentTurn();

                // Pause for a second to simulate thinking
                Thread.Sleep(1000);
            }
        }
        DeclareWinner();
    }

    // Method for player's turn
    private void PlayerTurn(int playerRank)
    {
        CheckForBooks(playerHand, playerBooks);
        bool opponentHasCard = false;
        // Iterate through opponent's hand to check for card
        opponentIterator = opponentHand.GetCards().GetEnumerator();
        List<Card> cardsToRemove = new List<Card>();
        while (opponentIterator.MoveNext())
        {
            Card card = opponentIterator.Current;
            if (card.GetRank() == playerRank)
            {
                opponentHasCard = true;
                cardsToRemove.Add(card);
                playerHand.AddCard(card);
            }
        }
        foreach (Card card in cardsToRemove)
        {
            opponentHand.RemoveCard(card);
        }

        // Pause for a second to simulate thinking
        Thread.Sleep(1000);

        // Check opponent's hand for card. If not found, player draws a card from the deck
        if (opponentHasCard)
        {
            Console.WriteLine("Opponent has the card(s) you asked for!");
        }
        else
        {
            Console.WriteLine("Go fish!");
            if (deck.GetCards().Count > 0)
            {
                playerHand.AddCard(deck.GetCards()[0]);
                deck.GetCards().RemoveAt(0);
                Thread.Sleep(500);
                Console.WriteLine("You drew a " + playerHand.GetCards()[playerHand.GetCards().Count - 1].GetRank());
            } 
            playerTurn = false;
        }

        CheckForBooks(playerHand, playerBooks);
    }

    // Method for opponent's turn
    private void OpponentTurn()
    {
        CheckForBooks(opponentHand, opponentBooks);
        Console.WriteLine("Opponent's turn!");
        Console.Write("Opponent's books: ");
        DisplayBooks(opponentBooks);
        bool playerHasCard = false;

        // Generate a random card, of which the opponent possesses to ask the player for
        var rand = new Random();
        int opponentRank;
        if (opponentHand.GetCards().Count != 0 && playerHand.GetCards().Count != 0)
        {
            do
            {
                int randomIndex = rand.Next(opponentHand.GetCards().Count);
                opponentRank = opponentHand.GetCards()[randomIndex].GetRank();
            } while (opponentRank == lastOpponentRank);
            lastOpponentRank = opponentRank;
            Console.WriteLine("Opponent asks for a: " + opponentRank);

            List<Card> cardsToRemove = new List<Card>();
            playerIterator = playerHand.GetCards().GetEnumerator();
            while (playerIterator.MoveNext())
            {
                Card card = playerIterator.Current;
                if (card.GetRank() == opponentRank)
                {
                    playerHasCard = true;
                    cardsToRemove.Add(card);
                    opponentHand.AddCard(card);
                }
            }
            foreach (Card card in cardsToRemove)
            {
                playerHand.RemoveCard(card);
            }
        }
        else
        {
            Console.WriteLine("Opponent's hand is empty! Opponent draws a card from the deck.");
        }

        // Pause for a second to simulate thinking
        Thread.Sleep(1000);

        // Check player's hand for card. If not found, opponent draws a card from the deck
        if (playerHasCard)
        {
            Console.WriteLine("You had the card(s) your opponent asked for!");
        }
        else
        {
            Console.WriteLine("Opponent Goes Fish!");
            if (deck.GetCards().Count > 0)
            {
                opponentHand.AddCard(deck.GetCards()[0]);
                deck.GetCards().RemoveAt(0);
            }
            playerTurn = true;
        }
        CheckForBooks(opponentHand, opponentBooks);
    }

    // Method to check for books in a player's hand
    private void CheckForBooks(Hand hand, List<Card> books)
    {
        
        for (int i = 2; i <= 14; i++)
        {
            int count = 0;
            foreach (Card card in hand.GetCards())
            {
                if (card.GetRank() == i)
                {
                    count++;
                }
            }
            if (count == 2)
            {
                List<Card> cardsToRemove = new List<Card>();
                var iterator = hand.GetCards().GetEnumerator();
                while (iterator.MoveNext())
                {
                    Card card = iterator.Current;
                    if (card.GetRank() == i)
                    {
                        cardsToRemove.Add(card);
                    }
                }
                foreach (Card card in cardsToRemove)
                {
                    hand.RemoveCard(card);
                }
                books.Add(new Card(i));
                break; // Exit the loop once a book is found to avoid infinite loop
            }
        }
        
        
    }

    // Method to print the player's hand
    public void PrintHand(Hand hand)
    {
        hand.Print();
    }

    // Method to announce the start of the game
    public void AnnounceGameStart()
    {
        Console.WriteLine("Welcome to Go Fish! The goal of the game is to collect more \"books\" of cards than your opponent. ");
        Console.WriteLine("A book is a set of four cards with the same rank. Each turn you may ask your opponent for a card/cards of a specific rank. ");
        Console.WriteLine("If your opponent has that card or cards, they must give them to you, and you get another turn. ");
        Console.WriteLine("If your opponent does not have the card or cards, they say \"Go fish!\" and you must draw a card from the deck. ");
        Console.Write("To start, you've been dealt the hand: ");
        PrintHand(playerHand);
        Console.WriteLine();
    }

    // Method to display a player's books
    public void DisplayBooks(List<Card> books)
    {
        foreach (Card card in books)
        {
            if (card.GetRank() == 11)
            {
                Console.Write("J, ");
            }
            else if (card.GetRank() == 12)
            {
                Console.Write("Q, ");
            }
            else if (card.GetRank() == 13)
            {
                Console.Write("K, ");
            }
            else if (card.GetRank() == 14)
            {
                Console.Write("A, ");
            }
            else
            {
                Console.Write(card.GetRank() + ", ");
            }
        }
        Console.WriteLine();
    }

    // Method to declare the winner of the game
    public void DeclareWinner()
    {
        Console.Write("Your books: ");
        DisplayBooks(playerBooks);
        Console.Write("Opponent's books: ");
        DisplayBooks(opponentBooks);
        if (playerBooks.Count > opponentBooks.Count)
        {
            Console.WriteLine("Congratulations! You win!");
        }
        else if (playerBooks.Count < opponentBooks.Count)
        {
            Console.WriteLine("You lose! Better luck next time!");
        }
        else
        {
            Console.WriteLine("It's a tie!");
        }
    }
}