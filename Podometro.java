
/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author    - Asier Galisteo - 
 */
public class Podometro {
    // Constantes
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    // Atributos
    private String marca;
    private double altura;
    private char sexo;
    private int longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int totalDistanciaSemana;
    private int totalDistanciaFinSemana;
    private int minutos;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        minutos = 0;
        caminatasNoche = 0;

    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == 'H') {
            longitudZancada = (int)Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = (int)Math.floor(altura * ZANCADA_MUJER);
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        // total de pasos
        if (dia == SABADO) {
            totalPasosSabado = pasos;
        }
        else if (dia == DOMINGO) {
            totalPasosDomingo = pasos;
        }
        else {
            switch (dia) {
                case 1: totalPasosLaborables = pasos;
                    break;
                case 2: totalPasosLaborables += pasos;
                    break;
                case 3: totalPasosLaborables += pasos;
                    break;
                case 4: totalPasosLaborables += pasos;
                    break;
                case 5: totalPasosLaborables += pasos;
                    break;
            }
        }
        // caminatas noche
        if (horaInicio >= 2100 || horaFin > 2100) {
            caminatasNoche++;
        }
        

        
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        double metros = (double)altura / 100;
        double zancada = (double)longitudZancada / 100;
        if (sexo == 'H') {
            System.out.println("Configuración del podometro\n"
                + "*********************************" 
                + "\nAltura: " + metros + " mtos\n"
                + "Sexo: HOMBRE\n"
                + "Longitud zancada: " + zancada + " mtos");
        }
        else {
            System.out.println("Configuración del podometro\n"
                + "*********************************" 
                + "\nAltura: " + metros + " mtos\n"
                + "Sexo: MUJER\n"
                + "Longitud zancada: " + zancada + " mtos");
        }
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        System.out.println ("Estadísticas\n " + "********************\n"
                            + "Distancia recorrida toda la semana:" + totalDistanciaSemana + "KM\n"
                            + "Distancia recorrida fin de semana:" + totalDistanciaFinSemana + "KM\n"
                            + "\n"
                            + "Nº pasos dias laborables:" + totalPasosLaborables
                            +"\nNº pasos SÁBADO:" + totalPasosSabado
                            +"\nNº pasos DOMINGO:" + totalPasosDomingo
                            +"\n"
                            +"Nº caminatas realizadas a partir de las 21h:" + caminatasNoche
                            +"\n"
                            +"\nTiempo total caminado en la semana:" + minutos
                            +"\nDia/s con mas pasos caminados:");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo) {
            return "Laborables";
        }
        else if (totalPasosSabado > totalPasosLaborables && totalPasosSabado > totalPasosDomingo) {
            return "Sábado";
        }
        else {
            return "Domingo";
        }
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        minutos = 0;
        caminatasNoche = 0;
    }
}
