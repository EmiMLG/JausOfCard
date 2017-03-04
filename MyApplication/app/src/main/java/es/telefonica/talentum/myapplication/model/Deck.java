package es.telefonica.talentum.myapplication.model;


public class Deck {
    public Deck(){
    }

    private int remaining;
    private String id;

    public Deck(int remaining) {
        this.remaining = remaining;
    }

    public Deck(String id) {
        this.id = id;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
