using System;
using System.Collections.Generic;

public class Deck {
    private List<Card> cards;

    // Constructor to populate deck with 52 cards
    public Deck()
    {
        cards = new List<Card>();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 2; j <= 14; j++)
            {
                cards.Add(new Card(j));
            }
        }
    }

    // Getter method for cards
    public List<Card> GetCards()
    {
        return cards;
    }

    // Method to shuffle the deck
    public void Shuffle()
    {
        Random random = new Random();
        for (int i = 0; i < cards.Count; i++)
        {
            int randomIndex = random.Next(cards.Count);
            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
    }

    // Method to print the deck
    public void Print()
    {
        foreach (Card card in cards)
        {
            Console.Write(card.GetRank() + ", ");
        }
    }

    // Method to return size of the deck
    public int Size()
    {
        return cards.Count;
    }
}

