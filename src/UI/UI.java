package UI;

import com.alura.conversordemoneda.APIPull;

public class UI{
    public static void WelcomeScreen(){
        System.out.println("****************************************************");
        System.out.println("*            Conversor de monedas ALURA            *");
        System.out.println("****************************************************");
        System.out.print("""
¡Bienvenidos a su conversor de monedas de confianza!
""");
        System.out.println("     COP: " + APIPull.getCurrency("COP")+" - EUR: " + APIPull.getCurrency("EUR") + " - JPY: " + APIPull.getCurrency("JPY"));
        System.out.println("""
Digite pro favor el numero del menu que le gustaria continuar.

1. Convertir de USD(US Dollar) a otra moneda.
2. Conversiones mas frecuentes.
3. Convertir otras monedas.
4. Historial de conversion.
5. Salir.

¡Gracias por elegir el Conversor de monedas ALURA!""");
    }
}
