import UI.*;
import com.alura.conversordemoneda.APIPull;
import com.google.gson.JsonSyntaxException;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.alura.conversordemoneda.APIPull.getCurrency;

public class Main{
    public static void main (String[] args){
        try {
        APIPull.PullRequest("latest","USD",null,null);
        newScanners sCn = new newScanners();
        Scanner sc = new Scanner(System.in);
        List<Object> HistoricalTransaction = new ArrayList<>();
        String gotCurrencyA;
        String gotCurrencyB;
        String AmountA;
        String DefaultTransaction;
        String itAdd;
        NumberFormat fAmountA = NumberFormat.getCurrencyInstance();
        Double CurrentCurrency;
        int inputA;

        for (int i = 0; i < 3; i++) {

                UI.WelcomeScreen();

                inputA = sCn.getSc(5);

                if (inputA == 1) {
                    System.out.println("Por favor, digte la moneda la cual desea saber su conversion en dolares y la cantidad a convertir:");
                    System.out.println("Moneda a convertir a dolar, ejemplo USD, COP o JEP: ");
                    gotCurrencyA = sc.nextLine();
                    System.out.println("Cantidad a convertir: (Introduca el valor sin simbolos o espacios, Solo en cantidades Double. Ejemplo: 4,500.90)");
                    AmountA = sc.nextLine();
                    APIPull.PullRequest("pair", "USD", gotCurrencyA, AmountA);
                    CurrentCurrency = Double.valueOf(AmountA);
                    System.out.println("Su conversion esta lista! su conversion de " + fAmountA.format(CurrentCurrency) + " de USD a " + gotCurrencyA + ", da un total de: " + getCurrency("conversion_result") + " en " + gotCurrencyA);
                    itAdd = "Su conversion fue de " + fAmountA.format(CurrentCurrency) + " USD a " + gotCurrencyA + ", da un total de: " + getCurrency("conversion_result") + " en " + gotCurrencyA;
                    HistoricalTransaction.add(itAdd);
                }
                if (inputA == 2) {
                    System.out.println("Por favor, digite la conversion de moneda que le gustaria solicitar:");
                    System.out.println("1. de USD a COP");
                    System.out.println("2. de USD a EUR");
                    System.out.println("3. de USD a JEP");
                    DefaultTransaction = sc.nextLine();
                    System.out.println("Ingrese la cantidad a convertir:");
                    AmountA = sc.nextLine();
                    CurrentCurrency = Double.valueOf(AmountA);

                    switch (DefaultTransaction) {
                        case "1" -> {
                            APIPull.PullRequest("pair", "USD", "COP", AmountA);
                            System.out.println("Su conversion de USD de: " + fAmountA.format(CurrentCurrency) + " a COP, es de: " + getCurrency("conversion_result"));
                            itAdd = "Su conversion fue de " + fAmountA.format(CurrentCurrency) + " USD a COP, y fue de: " + getCurrency("conversion_result");
                            HistoricalTransaction.add(itAdd);
                        }
                        case "2" -> {
                            APIPull.PullRequest("pair", "USD", "EUR", AmountA);
                            System.out.println("Su conversion de USD de: " + fAmountA.format(CurrentCurrency) + "a EUR, es de: " + getCurrency("conversion_result"));
                            itAdd = "Su conversion fue de " + fAmountA.format(CurrentCurrency) + " USD a EUR, y fue de: " + getCurrency("conversion_result");
                            HistoricalTransaction.add(itAdd);
                        }
                        case "3" -> {
                            APIPull.PullRequest("pair", "USD", "JEP", AmountA);
                            System.out.println("Su conversion de USD de: " + fAmountA.format(CurrentCurrency) + "a JEP, es de: " + getCurrency("conversion_result"));
                            itAdd = "Su conversion fue de " + fAmountA.format(CurrentCurrency) + " USD a JEP, y fue de: " + getCurrency("conversion_result");
                            HistoricalTransaction.add(itAdd);
                        }
                    }
                }
                if (inputA == 3){
                    System.out.println("Por favor, digite el nombre de la primera moneda: ");
                    gotCurrencyA = sc.nextLine();

                    System.out.println("Por favor, digite el nombre de la Segunda moneda: ");
                    gotCurrencyB = sc.nextLine();

                    System.out.println("Por favor, digite la cantidad a convertir: ");
                    AmountA = sc.nextLine();

                    CurrentCurrency = Double.valueOf(AmountA);

                    APIPull.PullRequest("pair", gotCurrencyA, gotCurrencyB, AmountA);

                    System.out.println("La conversion de " + gotCurrencyA + " a " + gotCurrencyB + " Equivale a: " + getCurrency("conversion_result"));
                    itAdd = "Su conversion fue de " + fAmountA.format(CurrentCurrency) + " de " + gotCurrencyA + " a " + gotCurrencyB + " y fue de: " + getCurrency("conversion_result");
                    HistoricalTransaction.add(itAdd);

                    APIPull.PullRequest("pair", gotCurrencyB, gotCurrencyA, AmountA);
                    System.out.println("La conversion de " + gotCurrencyB + " a " + gotCurrencyA + " Equivale a: " + getCurrency("conversion_result"));
                    itAdd = "Su conversion fue de " + fAmountA.format(CurrentCurrency) + " de " + gotCurrencyB + " a " + gotCurrencyA + " y fue de: " + getCurrency("conversion_result");
                    HistoricalTransaction.add(itAdd);
                }
                if (inputA == 4) {
                    if (!HistoricalTransaction.isEmpty()) {
                        System.out.println("Su historial de transactiones es el siguiente: ");
                        for (Object item : HistoricalTransaction) {
                            System.out.println(item);
                        }
                    }else{
                        System.out.println("Aun no se ha efectuado ninguna transaccion. ");
                    }
                }

                System.out.println("¿Desea hacer alguna transaccion mas?");
                System.out.println("1. Si\n2. No");
                DefaultTransaction = sc.nextLine();
                if (DefaultTransaction.equals("2")) {
                    System.exit(0);
                } else {
                    i = 0;
                }

        }
        } catch (NumberFormatException e) {
            System.out.println("Hubo un problema conectando con la API\n por favor reinicie el programa o contacte a su administrador.");
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
