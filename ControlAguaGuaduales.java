package negocio;

import java.util.Scanner;

public class ControlAguaGuaduales {
    public ControlAguaGuaduales() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] consumos = new double[6];

        int opcion;
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
            }
        } while(opcion != 4);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void registrarConsumo(double[] consumos, Scanner scanner) {
        System.out.print("Ingrese el número del apartamento (1 a 6): ");

        int apto;
        for(apto = scanner.nextInt(); apto < 1 || apto > 6; apto = scanner.nextInt()) {
            System.out.print("Inválido. Ingrese un apartamento de 1 a 6: ");
        }

        System.out.print("Ingrese el consumo en m³: ");

        double consumo;
        for(consumo = scanner.nextDouble(); consumo <= (double)0.0F; consumo = scanner.nextDouble()) {
            System.out.print("Inválido. El consumo debe ser mayor a 0: ");
        }

        consumos[apto - 1] = consumo;
        System.out.println("¡Consumo registrado con éxito!");
    }

    public static void consultarFactura(double[] consumos, Scanner scanner) {
        System.out.print("Ingrese el número del apartamento (1 a 6): ");

        int apto;
        for(apto = scanner.nextInt(); apto < 1 || apto > 6; apto = scanner.nextInt()) {
            System.out.print("Inválido. Ingrese un apartamento de 1 a 6: ");
        }

        double consumo = consumos[apto - 1];
        if (consumo == (double)0.0F) {
            System.out.println("El apartamento " + apto + " no tiene consumo registrado.");
        } else {
            imprimirFactura(apto, consumo);
        }

    }

    public static void imprimirFactura(int apartamento, double consumo) {
        double cargoFijo = (double)12000.0F;
        double tarifaPorMetro;
        if (consumo <= (double)10.0F) {
            tarifaPorMetro = (double)3000.0F;
        } else if (consumo <= (double)20.0F) {
            tarifaPorMetro = (double)4500.0F;
        } else {
            tarifaPorMetro = (double)6000.0F;
        }

        double valorConsumo = consumo * tarifaPorMetro;
        double subtotal = cargoFijo + valorConsumo;
        double recargo = (double)0.0F;
        if (consumo > (double)25.0F) {
            recargo = subtotal * 0.1;
        }

        double total = subtotal + recargo;
        System.out.println("\n--- FACTURA APARTAMENTO " + apartamento + " ---");
        System.out.println("Consumo: " + consumo + " m³");
        System.out.println("Tarifa aplicada por m³: $" + tarifaPorMetro);
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);
        System.out.println("Recargo: $" + recargo);
        System.out.println("TOTAL A PAGAR: $" + total);
        if (consumo > (double)25.0F) {
            System.out.println("ALERTA: consumo excesivo");
        }

    }

    public static void generarReporte(double[] consumos) {
        double consumoTotal = (double)0.0F;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;
        double mayorConsumo = (double)-1.0F;
        int aptoMayorConsumo = 0;

        for(int i = 0; i < consumos.length; ++i) {
            if (consumos[i] == (double)0.0F) {
                ++sinRegistro;
            } else {
                ++registrados;
                consumoTotal += consumos[i];
                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    aptoMayorConsumo = i + 1;
                }

                if (consumos[i] > (double)25.0F) {
                    ++excesivos;
                }
            }
        }

        if (registrados == 0) {
            System.out.println("Aún no hay consumos registrados.");
        } else {
            double promedio = consumoTotal / (double)registrados;
            System.out.println("\n=== REPORTE GENERAL DEL CONJUNTO ===");
            System.out.println("Consumo total del conjunto: " + consumoTotal + " m³");
            System.out.println("Consumo promedio (registrados): " + promedio + " m³");
            System.out.println("Apartamento con mayor consumo: Apto " + aptoMayorConsumo + " (" + mayorConsumo + " m³)");
            System.out.println("Apartamentos sin registro: " + sinRegistro);
            System.out.println("Apartamentos con consumo excesivo (> 25 m³): " + excesivos);
        }

    }
}package negocio;

import java.util.Scanner;

public class ControlAguaGuaduales {
    public ControlAguaGuaduales() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] consumos = new double[6];

        int opcion;
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
            }
        } while(opcion != 4);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void registrarConsumo(double[] consumos, Scanner scanner) {
        System.out.print("Ingrese el número del apartamento (1 a 6): ");

        int apto;
        for(apto = scanner.nextInt(); apto < 1 || apto > 6; apto = scanner.nextInt()) {
            System.out.print("Inválido. Ingrese un apartamento de 1 a 6: ");
        }

        System.out.print("Ingrese el consumo en m³: ");

        double consumo;
        for(consumo = scanner.nextDouble(); consumo <= (double)0.0F; consumo = scanner.nextDouble()) {
            System.out.print("Inválido. El consumo debe ser mayor a 0: ");
        }

        consumos[apto - 1] = consumo;
        System.out.println("¡Consumo registrado con éxito!");
    }

    public static void consultarFactura(double[] consumos, Scanner scanner) {
        System.out.print("Ingrese el número del apartamento (1 a 6): ");

        int apto;
        for(apto = scanner.nextInt(); apto < 1 || apto > 6; apto = scanner.nextInt()) {
            System.out.print("Inválido. Ingrese un apartamento de 1 a 6: ");
        }

        double consumo = consumos[apto - 1];
        if (consumo == (double)0.0F) {
            System.out.println("El apartamento " + apto + " no tiene consumo registrado.");
        } else {
            imprimirFactura(apto, consumo);
        }

    }

    public static void imprimirFactura(int apartamento, double consumo) {
        double cargoFijo = (double)12000.0F;
        double tarifaPorMetro;
        if (consumo <= (double)10.0F) {
            tarifaPorMetro = (double)3000.0F;
        } else if (consumo <= (double)20.0F) {
            tarifaPorMetro = (double)4500.0F;
        } else {
            tarifaPorMetro = (double)6000.0F;
        }

        double valorConsumo = consumo * tarifaPorMetro;
        double subtotal = cargoFijo + valorConsumo;
        double recargo = (double)0.0F;
        if (consumo > (double)25.0F) {
            recargo = subtotal * 0.1;
        }

        double total = subtotal + recargo;
        System.out.println("\n--- FACTURA APARTAMENTO " + apartamento + " ---");
        System.out.println("Consumo: " + consumo + " m³");
        System.out.println("Tarifa aplicada por m³: $" + tarifaPorMetro);
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);
        System.out.println("Recargo: $" + recargo);
        System.out.println("TOTAL A PAGAR: $" + total);
        if (consumo > (double)25.0F) {
            System.out.println("ALERTA: consumo excesivo");
        }

    }

    public static void generarReporte(double[] consumos) {
        double consumoTotal = (double)0.0F;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;
        double mayorConsumo = (double)-1.0F;
        int aptoMayorConsumo = 0;

        for(int i = 0; i < consumos.length; ++i) {
            if (consumos[i] == (double)0.0F) {
                ++sinRegistro;
            } else {
                ++registrados;
                consumoTotal += consumos[i];
                if (consumos[i] > mayorConsumo) {
                    mayorConsumo = consumos[i];
                    aptoMayorConsumo = i + 1;
                }

                if (consumos[i] > (double)25.0F) {
                    ++excesivos;
                }
            }
        }

        if (registrados == 0) {
            System.out.println("Aún no hay consumos registrados.");
        } else {
            double promedio = consumoTotal / (double)registrados;
            System.out.println("\n=== REPORTE GENERAL DEL CONJUNTO ===");
            System.out.println("Consumo total del conjunto: " + consumoTotal + " m³");
            System.out.println("Consumo promedio (registrados): " + promedio + " m³");
            System.out.println("Apartamento con mayor consumo: Apto " + aptoMayorConsumo + " (" + mayorConsumo + " m³)");
            System.out.println("Apartamentos sin registro: " + sinRegistro);
            System.out.println("Apartamentos con consumo excesivo (> 25 m³): " + excesivos);
        }

    }
}