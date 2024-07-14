package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class newScanners {

    public int getSc(int xLimit) {

        Scanner sc = new Scanner(System.in);
        int nofTry = 0;
        int usInput = 0;

        for (int i = 0; i < 2; i++) {
            try {
                sc = new Scanner(System.in);
                usInput = sc.nextInt();
                if (usInput >= 1 && usInput <= 5){
                    return usInput;
                }
            } catch (NumberFormatException | InputMismatchException e) {
                nofTry += 1;
                if (nofTry==5){
                    System.out.println("""
Usted ha agotado el numero de intentos, regresaremos al menu principal para que vuelva a ver sus opciones.
Digite pro favor el numero del menu que le gustaria continuar.

1. Convertir de USD(US Dollar) a otra moneda.
2. Conversiones mas frecuentes.
3. Historial de conversion.
4. Stock de asociados.
5. Salir.
""");
                    nofTry = 0;
                }else {
                    System.out.println("Por favor, digite un numero entre el 1 y el " + xLimit + " para su respuesta.");
                }
                i = 0;
            }
            sc.reset();
        }
        return usInput;
    }
}
