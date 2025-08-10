import com.google.gson.Gson;

import java.util.Map;
import java.util.Scanner;

public class Conversor {
    public static void main(String[] args) {
        ConsultaMoneda consulta = new ConsultaMoneda();
        String json = consulta.obtenerDatosMoneda("USD");

        Gson gson = new Gson();
        TasaCambio tasas = gson.fromJson(json, TasaCambio.class);

        Map<String, Double> conversiones = tasas.getConversion_rates();

        Scanner teclado = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("Bienvenido al conversor de Monedas");
            System.out.println("Monedas disponibles: ");
            System.out.println("1. ARS (Peso Argentino)");
            System.out.println("2. BOB (Boliviano)");
            System.out.println("3. BRL (Real Brasileño)");
            System.out.println("4. CLP (Peso Chileno)");
            System.out.println("5. COP (Peso Colombiano)");
            System.out.println("6. USD (Dolar Estadounidense)");

            System.out.println("\nIngrese el código de la moneda de ORIGEN (ej: USD): ");
            String monedaOrigen = teclado.nextLine().toUpperCase();

            System.out.println("Ingrese el código de la moneda de DESTINO (ej: ARS): ");
            String monedaDestino = teclado.nextLine().toUpperCase();

            System.out.println("Ingrese el monto a convertir: ");
            double monto = teclado.nextDouble();
            teclado.nextLine();

            if (conversiones.containsKey(monedaOrigen) && conversiones.containsKey(monedaDestino)) {
                double tasaOrigen = conversiones.get(monedaOrigen);
                double tasaDestino = conversiones.get(monedaDestino);

                double montoConvertido = (monto / tasaOrigen) * tasaDestino;

                System.out.printf("\n%.2f %s equivale a %.2f %s\n", monto, monedaOrigen, montoConvertido, monedaDestino);
            } else {
                System.out.println("Código de moneda no válido.");
            }

            System.out.println("\n¿Desea realizar otra conversión? (s/n): ");
            String respuesta = teclado.nextLine();

            if (!respuesta.equalsIgnoreCase("s")){
                continuar = false;
                System.out.println("Gracias por usar el conversor de monedas.");
            }
        }
        teclado.close();
    }
}
