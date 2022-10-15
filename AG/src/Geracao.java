import java.util.Random;

public class Geracao {
    Populacao novaGeracao;

    public Geracao() {
        novaGeracao = new Populacao();
    }

    static Cromossomo[] selecionarDezAleatorios(Populacao arranjo) {
        Cromossomo[] sorteados = new Cromossomo[10];

        for (int i = 0; i < 10; i++) {
            sorteados[i] = arranjo.populacao[new Random().nextInt(arranjo.tamanhoPop)];
        }

        return sorteados;
    }

    static Cromossomo maiorValor(Cromossomo[] pop) {
        double maior = pop[0].valorFitness;
        Cromossomo maisApto = pop[0];

        for (Cromossomo n : pop) {
            if (maior < n.valorFitness) {
                maior = n.valorFitness;
                maisApto = n;
            }
        }

        return maisApto;
    }

    public static Populacao cruzamentoDeUmPonto(Populacao pop) {
        int tamanho = pop.getTamanhoPop();
        byte[] temp;
        for (int i = 0; i < tamanho - 1; i += 2) {
            temp = pop.populacao[i].cromossomoEmBinario;
            for (int j = 0; j < 22; j++) {
                if (j < 11) {
                    pop.populacao[i].cromossomoEmBinario[j] = pop.populacao[i + 1].cromossomoEmBinario[j];
                } else {
                    pop.populacao[i + 1].cromossomoEmBinario[j] = temp[j - 11];
                }
            }
        }

        return pop;
    }

    public static byte[] variacaoSimples(byte[] cromossomo) {
        if (cromossomo[new Random().nextInt(cromossomo.length)] == 1) {
            cromossomo[new Random().nextInt(cromossomo.length)] = 0;
        } else {
            cromossomo[new Random().nextInt(cromossomo.length)] = 1;
        }
        return cromossomo;
    }

    public Populacao getNovaGeracao() {
        return novaGeracao;
    }

    public void fazerMutacao() {
        novaGeracao = cruzamentoDeUmPonto(novaGeracao);
        for (Cromossomo n : novaGeracao.populacao) {
            n.update();
        }
    }
}