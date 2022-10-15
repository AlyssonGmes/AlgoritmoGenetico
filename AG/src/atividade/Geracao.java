package atividade;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class Geracao {
    static int id = 1;
    ArrayList<Cromossomo> intermediaria = new ArrayList<>();

    public Geracao(int numGeracoes) {
        Populacao p1 = new Populacao();
        p1.criarPopulacao(1000);

        for (int i = 0; i < numGeracoes; i++) {
            gerarIntermediarios(100);
            cruzamentoDeUmPonto(intermediaria);
            Cromossomo[] sorteados = Geracao.selecionarDezAleatorios(p1);
            p1.adicionarCromossomo(sorteados);
            id++;
            if(id%2000 == 0){
                System.out.println("Buscando...");
            }
        }

        for(Cromossomo d : intermediaria) {
            for (Cromossomo c : intermediaria) {
                Otimizacao atv = new Otimizacao(Cromossomo.intervaloDesejado(c.cromossomoEmDecimal),Cromossomo.intervaloDesejado(d.cromossomoEmDecimal));
                if(atv.resultado == 0){
                    System.out.println("\nValor A: "+(int)Cromossomo.intervaloDesejado(c.cromossomoEmDecimal));
                    System.out.println("Valor B: "+(int)Cromossomo.intervaloDesejado(d.cromossomoEmDecimal));
                    System.out.println("Geração: "+id/2000+"ª\n");
                    return;
                }
            }
        }
    }

    public void gerarIntermediarios(int numIntermediarios) {
        do {
            criarGeracao(1000);
        } while (intermediaria.size() < numIntermediarios);
    }

    public void criarGeracao(int numPop) {
        Populacao p1 = new Populacao();
        p1.criarPopulacao(numPop);
        Cromossomo[] sorteados = Geracao.selecionarDezAleatorios(p1);
        intermediaria.add(Populacao.maisApto(sorteados));
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