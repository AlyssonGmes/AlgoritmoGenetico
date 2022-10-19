package atividade;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class Geracao {
    Random rnd = new Random();
    static int id = 1;
    ArrayList<Cromossomo> intermediaria = new ArrayList<>();
    ArrayList<Cromossomo> geracao = new ArrayList<>();

    public Geracao(int numGeracoes) {

    }

    public void cruzamentoDeUmPonto(ArrayList<Cromossomo> intermediaria) {
        int tamanho = intermediaria.size();

        byte[] filho1 = new byte[22];
        byte[] filho2 = new byte[22];

        for (int i = 0; i < tamanho - 2; i += 2) {
            for (int j = 0; j < 22; j++) {
                if (j < 11) {
                    filho1[j + 11] = intermediaria.get(i).cromossomoEmBinario[j];
                    filho2[j] = intermediaria.get(i).cromossomoEmBinario[j];
                } else {
                    filho1[j - 11] = intermediaria.get(i).cromossomoEmBinario[j];
                    filho2[j] = intermediaria.get(i).cromossomoEmBinario[j];
                }
            }
            intermediaria.get(i).cromossomoEmBinario = filho1;
            intermediaria.get(i).update();
            intermediaria.get(i + 1).cromossomoEmBinario = filho2;
            intermediaria.get(i + 1).update();
        }
    }

    public void mutacaoSimples(ArrayList<Cromossomo> intermediaria) {
        int temp;
        for (int i = 0; i < intermediaria.size(); i++) {
            temp = rnd.nextInt(22);
            if (intermediaria.get(i).cromossomoEmBinario[temp] == 1) {
                intermediaria.get(i).cromossomoEmBinario[temp] = 0;
            } else {
                intermediaria.get(i).cromossomoEmBinario[temp] = 1;
            }
            intermediaria.get(i).update();
        }
    }

    static Cromossomo[] selecionarDezAleatorios(Populacao arranjo) {
        LinkedHashSet<Integer> aleatorios = new LinkedHashSet<>();
        do {
            aleatorios.add(new Random().nextInt(arranjo.populacao.size()));
        } while (aleatorios.size() < 10);

        Cromossomo[] sorteados = new Cromossomo[10];
        int i = 0;
        for (int n : aleatorios) {
            sorteados[i] = arranjo.populacao.get(n);
            i++;
        }

        return sorteados;
    }
}
