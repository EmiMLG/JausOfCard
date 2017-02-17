package es.telefonica.talentum.myapplication.model;


public class Card {

    public enum Suit{
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }

    private String suit;
    private String image;
    private int remains;

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRemains() {
        return remains;
    }

    public void setRemains(int remains) {
        this.remains = remains;
    }
}
