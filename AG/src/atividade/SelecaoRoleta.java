package atividade;

import java.util.Random;
//
public class SelecaoRoleta {
    public static void gerarRoleta(Populacao populacao) {
        Random rnd = new Random();
        double s = somatorioSelection(populacao);
        double alfa = rnd.nextInt((int)s);
        int j = 0;

        double iSoma = 0;
        do {
            iSoma += populacao.populacao.get(j).valorFitness;
            j = j + 1;
        } while (iSoma < alfa && j < populacao.populacao.size());

    }

    public static double somatorioSelection(Populacao pop) {
        double somatorio = 0;

        for (int i = 0; i < pop.populacao.size(); i++) {
            somatorio += pop.populacao.get(i).valorFitness;
        }

        return somatorio;
    }
}

