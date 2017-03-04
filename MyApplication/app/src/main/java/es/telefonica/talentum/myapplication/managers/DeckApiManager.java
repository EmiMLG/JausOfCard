package es.telefonica.talentum.myapplication.managers;


import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;

import es.telefonica.talentum.myapplication.model.Deck;

public class DeckApiManager {

    public interface DeckApiManagerNewDeckListener{

        public void onNewDeck(Deck deck);
    }
    
    private DeckApiManagerNewDeckListener listener;

    public void setOnNewDeckListener(DeckApiManagerNewDeckListener listener){
        this.listener = listener;
    }

    private static final String NEW_DECK_REQUEST = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";

    public void newDeck(Context context) { //Sirve para salir de su memoria, y llegar a diferentes sitios.
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(NEW_DECK_REQUEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //al ok
                Log.d("RESPONSE", response);
                parseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Houston, tenemos un problema.
                Log.e("HORROR", "Conection went to shit to the tracks");
            }
        });

        queue.add(request);
    }

    private void parseJson(String response) {
        Gson gson = new GsonBuilder().create();
        Reader reader = new StringReader(response);

        DeckEntity deckEntity = gson.fromJson(reader,DeckEntity.class);

        Deck deck = new Deck();
        deck.setId(deckEntity.getDeck_id());
        deck.setRemaining(deckEntity.getRemaining());

        if (listener!= null){
            listener.onNewDeck(deck);
        }
    }
}
