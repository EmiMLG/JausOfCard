package es.telefonica.talentum.myapplication.model;


public class Deck {

    private int remaining;
    private String id;

    public Deck(String id) {
        this.id = id;
    }

    public Deck(){

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
