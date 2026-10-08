package negocio;

import java.util.Scanner;

public class ControlAguaGuaduales {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Arreglo de 6 posiciones para los consumos (Apto 1 al 6)
        double[] consumos = new double[6];
        int opcion;

        // Bucle do-while para repetir el menú hasta seleccionar Salir (4)
        do {
            mostrarMenu();
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarConsumo(consumos, scanner);
                    break;
                case 2:
                    consultarFactura(consumos, scanner);
                    break;
                case 3:
                    generarReporte(consumos);
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    // 1. Función para mostrar las opciones en pantalla
    public static void mostrarMenu() {
        System.out.println("\n===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // 2. Función para registrar el consumo en el arreglo
    public static void registrarConsumo(double[] consumos, Scanner scanner) {
        int apto;
        double consumo;

        // Validación del número de apartamento usando ciclo while
        System.out.print("Ingrese el número del apartamento (1 a 6): ");
        apto = scanner.nextInt();
        while (apto < 1 || apto > 6) {
            System.out.print("Inválido. Ingrese un apartamento de 1 a 6: ");
            apto = scanner.nextInt();
        }

        // Validación del consumo usando ciclo while
        System.out.print("Ingrese el consumo en m³: ");
        consumo = scanner.nextDouble();
        while (consumo <= 0) {
            System.out.print("Inválido. El consumo debe ser mayor a 0: ");
            consumo = scanner.nextDouble();
        }

        // Se guarda en el arreglo (el apto 1 va en la posición 0)
        consumos[apto - 1] = consumo;
        System.out.println("¡Consumo registrado con éxito!");
    }

    // 3. Función para consultar e imprimir la factura de un apartamento
    public static void consultarFactura(double[] consumos, Scanner scanner) {
        int apto;

        System.out.print("Ingrese el número del apartamento (1 a 6): ");
        apto = scanner.nextInt();
        while (apto < 1 || apto > 6) {
            System.out.print("Inválido. Ingrese un apartamento de 1 a 6: ");
            apto = scanner.nextInt();
        }

        double consumo = consumos[apto - 1];

        // Verificar si tiene consumo registrado
        if (consumo == 0) {
            System.out.println("El apartamento " + apto + " no tiene consumo registrado.");
        } else {
            imprimirFactura(apto, consumo);
        }
    }

    // 4. Función para hacer los cálculos matemáticos del cobro
    public static void imprimirFactura(int apartamento, double consumo) {
        double cargoFijo = 12000;
        double tarifaPorMetro;

        // Determinar la tarifa según los m³ gastados
        if (consumo <= 10) {
            tarifaPorMetro = 3000;
        } else if (consumo <= 20) {
            tarifaPorMetro = 4500;
        } else {
            tarifaPorMetro = 6000;
        }

        double valorConsumo = consumo * tarifaPorMetro;
        double subtotal = cargoFijo + valorConsumo;
        double recargo = 0;

        // Aplicar el 10% extra si se supera los 25 m³
        if (consumo > 25) {
            recargo = subtotal * 0.10;
        }

        double total = subtotal + recargo;

        System.out.println("\n--- FACTURA APARTAMENTO " + apartamento + " ---");
        System.out.println("Consumo: " + consumo + " m³");
        System.out.println("Tarifa aplicada por m³: $" + tarifaPorMetro);
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);
        System.out.println("Recargo: $" + recargo);
        System.out.println("TOTAL A PAGAR: $" + total);

        if (consumo > 25) {
            System.out.println("ALERTA: consumo excesivo");
        }
    }

    // 5. Función para generar las estadísticas finales del edificio
    public static void generarReporte(double[] consumos) {
        double consumoTotal = 0;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;
        double mayorConsumo = -1;
        int aptoMayorConsumo = 0;

        // Recorrer el arreglo para contar y calcular
        for (int i = 0; i < consumos.length; i++) {
            if (consumos[i] == 0) {
                sinRegistro++;
            } else {
                registrados++;
                consumoTotal += consumos[i];

                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    aptoMayorConsumo = i + 1; // Convertir posición de arreglo a apto (0 -> 1)
                }

                if (consumos[i] > 25) {
                    excesivos++;
                }
            }
        }

        if (registrados == 0) {
            System.out.println("Aún no hay consumos registrados.");
        } else {
            double promedio = consumoTotal / registrados;

            System.out.println("\n=== REPORTE GENERAL DEL CONJUNTO ===");
            System.out.println("Consumo total del conjunto: " + consumoTotal + " m³");
            System.out.println("Consumo promedio (registrados): " + promedio + " m³");
            System.out.println("Apartamento con mayor consumo: Apto " + aptoMayorConsumo + " (" + mayorConsumo + " m³)");
            System.out.println("Apartamentos sin registro: " + sinRegistro);
            System.out.println("Apartamentos con consumo excesivo (> 25 m³): " + excesivos);
        }
    }
}