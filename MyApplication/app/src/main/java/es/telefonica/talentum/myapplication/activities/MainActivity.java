package es.telefonica.talentum.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.telefonica.talentum.myapplication.R;
import es.telefonica.talentum.myapplication.managers.CardApiManager;
import es.telefonica.talentum.myapplication.managers.DeckApiManager;
import es.telefonica.talentum.myapplication.model.Card;
import es.telefonica.talentum.myapplication.model.Deck;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main___card_left_text)TextView cardLeft;
    @BindView(R.id.activity_main___card_image)ImageView imageCard;
    @BindView(R.id.activity_main___new_deck)Button newDeck;

    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DeckApiManager apiManager = new DeckApiManager();
        apiManager.setOnNewDeckListener(new DeckApiManager.DeckApiManagerNewDeckListener() {
            @Override
            public void onNewDeck(Deck deck) {

            }
        });

        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeckApiManager apiManager = new DeckApiManager();
                apiManager.setOnNewDeckListener(new DeckApiManager.DeckApiManagerNewDeckListener() {
                    @Override
                    public void onNewDeck(Deck deckFromJson) {
                        cardLeft.setText("" + deckFromJson.getRemaining());
                        deck = deckFromJson;
                    }
                });

            }
        });

        imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardApiManager cardApiManager = new CardApiManager();
                cardApiManager.setListener(new CardApiManager.CardApiManagerNewCardListener() {
                    @Override
                    public void onNewCard(Card card) {
                        Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back);
                        cardLeft.setText("" + card.getRemains());
                        if (card.getRemains()==0){
                            newDeck.setVisibility(View.VISIBLE);
                            imageCard.setVisibility(View.INVISIBLE);
                            //finalCard.setVisibility(View.VISIBLE);
                            Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back);
                        }
                    }
                });
                cardApiManager.newCard(v.getContext(), deck);

            }
        });

        apiManager.newDeck(this);
    }
}
