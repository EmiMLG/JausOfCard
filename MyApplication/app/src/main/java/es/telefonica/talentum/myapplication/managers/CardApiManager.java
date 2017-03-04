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

import es.telefonica.talentum.myapplication.model.Card;
import es.telefonica.talentum.myapplication.model.Deck;

public class CardApiManager{

    public interface CardApiManagerNewCardListener{
        public void onNewCard(Card card);
    }

    private CardApiManagerNewCardListener listener;

    public void setListener(CardApiManagerNewCardListener listener) {
        this.listener = listener;
    }

    private static final String BASE_URL = "https://deckofcardsapi.com/api/deck/";
    private static final String FINAL_URL = "/draw/?count=1";

    public void newCard(Context context, Deck deck){

        String url = BASE_URL+deck.getId()+FINAL_URL;
        Log.d("URL",url);

        RequestQueue queue = Volley.newRequestQueue(context);


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);
                parseJSON(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("HORROR", "Connection went to shit to the tracks");
            }
        });

        queue.add(request);
    }

    private void parseJSON(String response) {
        Reader reader= new StringReader(response);
        Gson gson = new GsonBuilder().create();

        CardEntity cardEntity = gson.fromJson(reader, CardEntity.class);

        Card card = new Card();
        card.setImage(cardEntity.getCards().get(0).getImage());

       /* if(cardEntity.getCards().get(0).getSuit().equals(Card.Suit.CLUBS)){
            card.setSuit(Card.Suit.CLUBS);
        }else if(cardEntity.getCards().get(0).getSuit().equals(Card.Suit.HEARTS)){
            card.setSuit(Card.Suit.HEARTS);
        }else if(cardEntity.getCards().get(0).getSuit().equals(Card.Suit.SPADES)){
            card.setSuit(Card.Suit.SPADES);
        }else if(cardEntity.getCards().get(0).getSuit().equals(Card.Suit.DIAMONDS)) {
            card.setSuit(Card.Suit.DIAMONDS);
        }*/
        card.setRemains(cardEntity.getRemaining());

        if(listener !=null){
            listener.onNewCard(card);
        }
    }

}
