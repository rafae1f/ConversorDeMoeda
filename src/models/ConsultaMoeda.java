package models;

import com.google.gson.Gson;
import service.ConsumirApi;

public class ConsultaMoeda {
    protected static String API_KEY = "API_KEY_AQUI";
    protected static String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private Gson gson = new Gson();


    public Moeda consultaMoeda(String moedaOrigem) {
        String json;
        ConsumirApi consumirApi = new ConsumirApi();
        json = consumirApi.obterDados(BASE_URL + API_KEY + "/latest/" + moedaOrigem);
        MoedaExchangeRate moedaExchangeRate = gson.fromJson(json, MoedaExchangeRate.class);
        return new Moeda(moedaExchangeRate);
    }

}