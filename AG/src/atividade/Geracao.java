package atividade;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class Geracao {
    Random rnd = new Random();
    Populacao populacaoGerada;
    public static int numGeracoes = 0;

    public Geracao(int numGeracoes, Populacao popInicial) {
        this.populacaoGerada = criarGeracoes(numGeracoes, popInicial);
    }

    public Populacao criarGeracoes(int numGeracoes, Populacao populacao) {
        /*
        1º cria pop
        2º seleciona os dez
        3º pega o mais apto
        4º faz cruzamento na pop
        5º adiciona o mais apto no cruzamento
        6º repete n vezes do 2 ao 5 (1 geração)
         */

        if (numGeracoes == 0) {
            populacao.maisApto = Populacao.maisApto(populacao.populacao);
            return populacao;
        }

        Cromossomo temp = Populacao.maisApto(Geracao.selecionarDezAleatorios(populacao));

        if (temp.valorFitness >= 2.85027) {
            if (populacaoGerada == null) {
                populacaoGerada = populacao;
                populacaoGerada.maisApto = temp;
                return populacaoGerada;
            }

            System.out.println("Encontrado na geração " + numGeracoes + 1 + "!");
            this.numGeracoes = 0;
            return populacao;
        }

        populacao.populacao.remove(temp);
        populacao = cruzamentoDeUmPonto(populacao);
        //mutacaoSimples(populacao);
        populacao.adicionarCromossomo(temp);
        this.numGeracoes++;

        return criarGeracoes(numGeracoes - 1, populacao);
    }

    public void cruzamentoDeUmPonto(ArrayList<Cromossomo> intermediaria) {
        ArrayList<Cromossomo> temp = intermediaria;
        int tamanho = intermediaria.size();

        byte[] filho1 = new byte[22];
        byte[] filho2 = new byte[22];

        for (int i = 0; i < tamanho; i += 2) {
            for (int j = 0; j < 22; j++) {
                if (j < 11) {
                    filho1[j] = intermediaria.get(i + 1).cromossomoEmBinario[j + 11];
                    filho2[j + 11] = intermediaria.get(i + 1).cromossomoEmBinario[j];
                } else {
                    filho1[j] = temp.get(i).cromossomoEmBinario[j - 11];
                    filho2[j - 11] = temp.get(i).cromossomoEmBinario[j];
                }
            }
            intermediaria.get(i).cromossomoEmBinario = filho1;
            intermediaria.get(i).update();
            intermediaria.get(i + 1).cromossomoEmBinario = filho2;
            intermediaria.get(i + 1).update();
        }
    }

    public Populacao cruzamentoDeUmPonto(Populacao intermediaria) {
        Populacao temp = intermediaria;

        for (int i = 0; i < intermediaria.populacao.size() - 1; i += 2) {
            for (int j = 0; j < 22; j++) {
                if (j < 11) {
                    //inicial
                    intermediaria.populacao.get(i).cromossomoEmBinario[j] = intermediaria.populacao.get(i + 1).cromossomoEmBinario[j + 11];
                    //secundario
                    intermediaria.populacao.get(i + 1).cromossomoEmBinario[j] = intermediaria.populacao.get(i).cromossomoEmBinario[j + 11];
                } else {
                    //inicial
                    intermediaria.populacao.get(i).cromossomoEmBinario[j] = temp.populacao.get(i + 1).cromossomoEmBinario[j - 11];
                    //secundario
                    intermediaria.populacao.get(i + 1).cromossomoEmBinario[j] = temp.populacao.get(i).cromossomoEmBinario[j - 11];
                }
                intermediaria.populacao.get(i).update();
                intermediaria.populacao.get(i + 1).update();
            }
        }

        return intermediaria;
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

    public void mutacaoSimples(Populacao intermediaria) {
        int temp;
        for (int i = 0; i < intermediaria.populacao.size(); i++) {
            temp = rnd.nextInt(22);
            if (intermediaria.populacao.get(i).cromossomoEmBinario[temp] == 1) {
                intermediaria.populacao.get(i).cromossomoEmBinario[temp] = 0;
            } else {
                intermediaria.populacao.get(i).cromossomoEmBinario[temp] = 1;
            }
            intermediaria.populacao.get(i).update();
        }
    }

    static Cromossomo[] selecionarDezAleatorios(Populacao arranjo) {
       // arranjo.ordenarPopulacao();
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
