import java.util.Random;

enum Suit {
    Clubs, Diamonds, Hearts, Spades
}

enum Rank {
    Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
}

// Node class for Linked List
class Node {
    Card card;
    Node next;

    public Node(Card card) {
        this.card = card;
        this.next = null;
    }
}

// Queue class implemented using Linked List
class Queue {
    Node front, rear;

    public Queue() {
        this.front = this.rear = null;
    }

    public void enqueue(Card card) {
        Node temp = new Node(card);
        if (this.rear == null) {
            this.front = this.rear = temp;
        } else {
            this.rear.next = temp;
            this.rear = temp;
        }
    }

    public Card dequeue() {
        if (this.front == null) {
            return null;
        }
        Card card = this.front.card;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        return card;
    }
}

// Card class
class Card {
    Suit suit;
    Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        return this.rank + " of " + this.suit;
    }
}

// Player class
class Player {
    String name;
    Queue deck;

    public Player(String name) {
        this.name = name;
        this.deck = new Queue();
    }

    public void addCard(Card card) {
        this.deck.enqueue(card);
    }

    public void sortDeck() {
        Card[] cards = new Card[9];
        for (int i = 0; i < 9; i++) {
            cards[i] = this.deck.dequeue();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (getRankValue(cards[i].rank) > getRankValue(cards[j].rank)) {
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            this.deck.enqueue(cards[i]);
        }
    }

    public void printDeck() {
        for (int i = 0; i < 9; i++) {
            System.out.println(this.deck.dequeue());
        }
    }

    private int getRankValue(Rank rank) {
        switch (rank) {
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
                return 10;
            case Jack:
                return 11;
            case Queen:
                return 12;
            case King:
                return 13;
            case Ace:
                return 14;
            default:
                return 0;
        }
    }
}

// Node class for Player Queue
class PlayerNode {
    Player player;
    PlayerNode next;

    public PlayerNode(Player player) {
        this.player = player;
        this.next = null;
    }
}

// PlayerQueue class implemented using Linked List
class PlayerQueue {
    PlayerNode front, rear;

    public PlayerQueue() {
        this.front = this.rear = null;
    }

    public void enqueue(Player player) {
        PlayerNode temp = new PlayerNode(player);
        if (this.rear == null) {
            this.front = this.rear = temp;
        } else {
            this.rear.next = temp;
            this.rear = temp;
        }
    }

    public Player dequeue() {
        if (this.front == null) {
            return null;
        }
        Player player = this.front.player;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        return player;
    }
}

public class DeckofCards {
    public static void main(String[] args) {
        // Initialize deck of cards
        Card[][] deck = new Card[4][13];
        int index = 0;
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck[suit.ordinal()][rank.ordinal()] = new Card(suit, rank);
                index++;
            }
        }

        // Shuffle the cards
               Random random = new Random();
        for (int i = 0; i < 52; i++) {
            int randomIndex = random.nextInt(52);
            Card temp = deck[i / 13][i % 13];
            deck[i / 13][i % 13] = deck[randomIndex / 13][randomIndex % 13];
            deck[randomIndex / 13][randomIndex % 13] = temp;
        }

        // Distribute 9 cards to 4 players
        Player[] players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        int cardIndex = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                players[j].addCard(deck[cardIndex / 13][cardIndex % 13]);
                cardIndex++;
            }
        }

        // Sort the deck of each player
        for (int i = 0; i < 4; i++) {
            players[i].sortDeck();
        }

        // Create a player queue
        PlayerQueue playerQueue = new PlayerQueue();
        for (int i = 0; i < 4; i++) {
            playerQueue.enqueue(players[i]);
        }

        // Print the cards received by each player
        while (true) {
            Player player = playerQueue.dequeue();
            if (player == null) {
                break;
            }
            System.out.println("Cards received by " + player.name + ":");
            player.printDeck();
            System.out.println();
        }
    }
}
