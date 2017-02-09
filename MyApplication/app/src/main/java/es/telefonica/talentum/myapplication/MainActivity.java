package es.telefonica.talentum.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.telefonica.talentum.myapplication.managers.DeckApiManager;
import es.telefonica.talentum.myapplication.model.Deck;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeckApiManager apiManager = new DeckApiManager();
        apiManager.setOnNewDeckListener(new DeckApiManager.DeckApiManagerNewDeckListener() {
            @Override
            public void onNewDeck(Deck deck) {

            }
        });

        apiManager.newDeck(this);
    }
}
