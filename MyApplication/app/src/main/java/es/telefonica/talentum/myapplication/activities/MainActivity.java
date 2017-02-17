package es.telefonica.talentum.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import es.telefonica.talentum.myapplication.R;
import es.telefonica.talentum.myapplication.managers.DeckApiManager;
import es.telefonica.talentum.myapplication.model.Deck;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main___card_left_text)
    TextView cardLeft;
    @BindView(R.id.activity_main___card_image)
    ImageView imageCard;

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
