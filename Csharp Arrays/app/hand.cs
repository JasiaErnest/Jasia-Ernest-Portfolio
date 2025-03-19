using System;
using System.Collections.Generic;

public class Hand {
    private List<Card> hand;

    public Hand() {
        hand = new List<Card>();
    }

    public void AddCard(Card card) {
        hand.Add(card);
    }

    public void Print() {
        foreach (Card card in hand) {
            if (card.GetRank() == 11) {
                Console.Write("J, ");
            }
            else if (card.GetRank() == 12) {
                Console.Write("Q, ");
            }
            else if (card.GetRank() == 13) {
                Console.Write("K, ");
            }
            else if (card.GetRank() == 14) {
                Console.Write("A, ");
            }
            else {
                Console.Write(card.GetRank() + ", ");
            }
        }
    }

    public void RemoveCard(Card card) {
        hand.Remove(card);
    }

    public List<Card> GetCards() {
        return hand;
    }
}

