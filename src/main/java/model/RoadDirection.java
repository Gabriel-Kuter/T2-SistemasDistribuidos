package model;

import java.util.HashMap;
import java.util.Map;

/*
* Mapeia os tipos de blocos da malha rodoviaria
 */
public enum RoadDirection {

    NADA(0, "/assets/vazio.png"),
    CIMA(1, "/assets/via_cima.png"),
    DIREITA(2, "/assets/via_direita.png"),
    BAIXO(3, "/assets/via_baixo.png"),
    ESQUERDA(4, "/assets/via_esquerda.png"),
    CRUZAMENTO_CIMA(5, "/assets/cruzamento.png"),
    CRUZAMENTO_DIREITA(6, "/assets/cruzamento.png"),
    CRUZAMENTO_BAIXO(7, "/assets/cruzamento.png"),
    CRUZAMENTO_ESQUERDA(8, "/assets/cruzamento.png"),
    CRUZAMENTO_CIMA_DIREITA(9, "/assets/cruzamento.png"),
    CRUZAMENTO_CIMA_ESQUERDA(10, "/assets/cruzamento.png"),
    CRUZAMENTO_BAIXO_DIREITA(11, "/assets/cruzamento.png"),
    CRUZAMENTO_BAIXO_ESQUERDA(12, "/assets/cruzamento.png");

    private int number;
    private String path;
    private static Map directionsMap = new HashMap<Integer, String>();

    private RoadDirection(int number, String path) {
        this.number = number;
    }

    public static String getDirectionPath(int number) {
        return (String) directionsMap.get(number);
    }

    public int getNumber() {
        return number;
    }

    public static void toMap() {
        for (RoadDirection direction : RoadDirection.values()) {
            directionsMap.put(direction.number, direction.path);
        }
    }

}