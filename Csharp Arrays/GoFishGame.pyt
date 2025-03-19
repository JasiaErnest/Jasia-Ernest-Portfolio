# This Python program simulates a Go Fish card game where a human player competes against an AI opponent. 
# The game follows the standard rules, and the player manually selects a card rank to ask for.

# CS240 GoFishGame 

# Import necessary modules
import random
from collections import Counter

# Represents a single playing card with a rank and suit
class Card:
    def __init__(self, rank, suit):
        # Initialize the card with a rank and a suit
        self.rank = rank
        self.suit = suit
    
    # String representation of a card for easy printing
    def __repr__(self):
        return f"{self.rank} of {self.suit}"

# Represents a deck of 52 shuffled playing cards
class Deck:
    def __init__(self):
        suits = ['Hearts', 'Diamonds', 'Clubs', 'Spades']
        ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
        self.cards = [Card(rank, suit) for suit in suits for rank in ranks]
        self.shuffle()
    
    # Shuffle the deck using random.shuffle
    def shuffle(self):
        random.shuffle(self.cards)
    
    # Draw a card from the deck, if available
    def draw_card(self):
        return self.cards.pop() if self.cards else None

# Represents a player with a hand and books (completed sets of 4 cards)
class Player:
    def __init__(self, name):
        self.name = name
        self.hand = []
        self.books = []

    # Draw a specified number of cards from the deck
    def draw(self, deck, num=1):
        for _ in range(num):
            card = deck.draw_card()
            if card:
                self.hand.append(card)

    # Check if the player has at least one card of the given rank
    def has_rank(self, rank):
        return any(card.rank == rank for card in self.hand)

    # Remove and return all cards of the requested rank from the player's hand
    def give_cards(self, rank):
        matching_cards = [card for card in self.hand if card.rank == rank]
        self.hand = [card for card in self.hand if card.rank != rank]
        return matching_cards
    
    # Check if the player has a complete set of 4 matching ranks, and remove them as a 'book'
    def check_books(self):
        rank_counts = Counter(card.rank for card in self.hand)
        for rank, count in rank_counts.items():
            if count == 4:
                self.books.append(rank)
                self.hand = [card for card in self.hand if card.rank != rank]

    def __repr__(self):
        return f"{self.name}: Hand({len(self.hand)}) Books({self.books})"

# Main function that runs the Go Fish game
def go_fish_game():
    # Create and shuffle a deck of cards
    deck = Deck()
    players = [Player("Player"), Player("AI")]
    
    # Each player draws 7 initial cards from the deck
    for player in players:
        player.draw(deck, 7)
    
    turn = 0
    while deck.cards or any(player.hand for player in players):
        current_player = players[turn % 2]
        opponent = players[(turn + 1) % 2]
        
        if current_player.hand:
            if current_player.name == "Player":
                print(f"Your hand: {[card.rank for card in current_player.hand]}")
                requested_rank = input("Enter a rank to ask for: ").strip()
                while requested_rank not in [card.rank for card in current_player.hand]:
                    print("Invalid choice. Pick a rank from your hand.")
                    requested_rank = input("Enter a rank to ask for: ").strip()
            else:
                # AI selects a valid rank from its hand
                valid_ranks = [card.rank for card in current_player.hand]
                requested_rank = random.choice(valid_ranks) if valid_ranks else None
            
            print(f"{current_player.name} asks for {requested_rank}")
            
            # Check if the opponent has the requested rank
            if opponent.has_rank(requested_rank):
                received_cards = opponent.give_cards(requested_rank)
                current_player.hand.extend(received_cards)
                print(f"{opponent.name} gives {len(received_cards)} cards.")
            else:
                print(f"Go Fish! {current_player.name} draws a card.")
                current_player.draw(deck, 1)
            
            current_player.check_books()
        
        turn += 1
    
    # Determine winner
    print("Game Over!")
    for player in players:
        print(f"{player.name} has {len(player.books)} books: {player.books}")
    
    # Determine the winner by comparing the number of books
    winner = max(players, key=lambda p: len(p.books), default=None)
    if winner:
        print(f"Winner: {winner.name}!")
    else:
        print("It's a tie! No winner.")

if __name__ == "__main__":
    go_fish_game()
