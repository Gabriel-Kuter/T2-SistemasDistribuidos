package app;

import utils.MatrixUtils;
import model.RoadMutex;

public class Main {
    public static void main(String[] args) {
        MatrixUtils mapa = MatrixUtils.getInstance();
        mapa.generateMapFromFile("src/main/resources/casefiles/malhaTesteComplexo.txt");

        System.out.println("Tamanho: " + mapa.getRowCount() + "x" + mapa.getColCount());

        System.out.println("Entradas:");
        for (RoadMutex e : mapa.getEntrances()) {
            System.out.println("Linha: " + e.getRowIndex() + ", Coluna: " + e.getColIndex());
        }

        System.out.println("Saídas:");
        for (RoadMutex s : mapa.getExits()) {
            System.out.println("Linha: " + s.getRowIndex() + ", Coluna: " + s.getColIndex());
        }
    }
}