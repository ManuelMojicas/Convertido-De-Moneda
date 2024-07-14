package com.alura.conversordemoneda;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;

public class APIPull{
    static HttpResponse<String> response = null;
    static HttpRequest request;
    static HttpClient client;

    static JsonObject myJsCollection;
    static String json;
    static Gson gson;
    static JsonObject myJsCurrency;

    public static void PullRequest(String toRoad, String currencyA, String currencyB, String AmountA){
        String APIAddress = "https://v6.exchangerate-api.com/v6/7443c1d5dac2367f14c1e8a0/";

        if(toRoad.equals("latest")) {
            APIAddress += toRoad+"/"+currencyA;
        } else if(toRoad.equals("pair")) {
            if(AmountA != null) {
                APIAddress += toRoad + "/" + currencyA + "/" + currencyB + "/" + AmountA;
            }else{
                APIAddress += toRoad + "/" + currencyA + "/" + currencyB;
            }
        }

        try{
            request = HttpRequest.newBuilder().uri(URI.create(APIAddress)).build();
            client = HttpClient.newHttpClient();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (NumberFormatException e) {
            System.out.println("Hubo un problema conectando con la API\n por favor reinicie el programa o contacte a su administrador.");

        } catch (IOException | InterruptedException e) {
            System.out.println("Hubo un problema conectando con la API\n por favor reinicie el programa o contacte a su administrador.");
            throw new RuntimeException(e);

        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        }

        if (response == null) throw new AssertionError();

        json = response.body();

        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        myJsCollection = JsonParser.parseString(json).getAsJsonObject();

        if(toRoad.equals("latest")){
            myJsCurrency = JsonParser.parseString(String.valueOf(myJsCollection.get("conversion_rates"))).getAsJsonObject();
        }
    }

    public static String getCurrency(String cur) {
        NumberFormat Currency = NumberFormat.getCurrencyInstance();
        Double CurrentCurrency;
        if (cur.equals("conversion_result")) {
            CurrentCurrency = myJsCollection.get(cur).getAsDouble();
        }else{
            CurrentCurrency = Double.valueOf(String.valueOf(myJsCurrency.get(cur)));
        }
        return Currency.format(CurrentCurrency);
    }

}