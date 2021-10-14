
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
    private int altura;
    private char sexo;
    private int longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
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
        altura = (int)queAltura;
        sexo = queSexo;
        if (sexo == HOMBRE) {
            longitudZancada = (int)Math.ceil((altura) * ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = (int)Math.floor((altura) * ZANCADA_MUJER);
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
        int minutosTotalesHoraSalida = (horaInicio / 100) * 60 + horaInicio % 100;
        int minutosTotalesHoraLlegada = (horaFin / 100) * 60 + horaFin % 100;
        int tiempo = minutosTotalesHoraLlegada - minutosTotalesHoraSalida;
        // total de pasos y minutos
        switch (dia) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
                minutos += tiempo;
                break;
            case SABADO: totalPasosSabado += pasos;
                minutos += tiempo;
                break;
            case DOMINGO: totalPasosDomingo += pasos;
                minutos += tiempo;
                break;
            default : pasos = 0;
        }
        // caminatas noche
        if (horaInicio >= 2100) {
            caminatasNoche++;
        }
        // cálculo de la distancia
        totalDistanciaSemana = (double)((totalPasosLaborables + totalPasosSabado + totalPasosDomingo) * longitudZancada) / 100000;
        totalDistanciaFinSemana = (double)((totalPasosSabado + totalPasosDomingo) * longitudZancada) / 100000;
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
        int hora = minutos / 60;
        int min = minutos % 60;
        System.out.println ("\n" + "Estadísticas\n" + "********************\n"
            + "Distancia recorrida toda la semana: " + totalDistanciaSemana + "KM\n"
            + "Distancia recorrida fin de semana: " + totalDistanciaFinSemana + "KM\n"
            + "\n"
            + "Nº pasos dias laborables: " + totalPasosLaborables
            +"\nNº pasos SÁBADO: " + totalPasosSabado
            +"\nNº pasos DOMINGO: " + totalPasosDomingo
            +"\n"
            +"Nº caminatas realizadas a partir de las 21h: " + caminatasNoche
            +"\n"
            +"\nTiempo total caminado en la semana: " + hora + "h" + " y " + min + "m");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
       int mayor;
        if (totalPasosLaborables >= totalPasosSabado){
            mayor = totalPasosLaborables;
            if (mayor < totalPasosDomingo){
                return "Domingo";
            }
            else if  (mayor == totalPasosDomingo && mayor != totalPasosSabado){
                return "Laborables Domingo";
            }
            else if (mayor == totalPasosDomingo && mayor == totalPasosSabado){
                return "Laborable Sábado Domingo";
            }
            else if (mayor == totalPasosSabado){
                return "Laborables Sábado";
            }
            else{
                return "Laborables";
            }
        }
        else{
            mayor = totalPasosSabado;
            if (mayor < totalPasosDomingo){
                return "Domingo";
            }
            else if (mayor == totalPasosDomingo){
                return "Sábado Domingo";
            }
            return "Sábado";
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

