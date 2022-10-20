package atividade;
//
import java.util.*;

public class Populacao {
    ArrayList<Cromossomo> populacao = new ArrayList<>();

    Cromossomo maisApto;

    public void criarPopulacao(int tamanho) {

        for (int i = 0; i < tamanho; i++) {
            populacao.add(new Cromossomo());
        }
    }

    public void adicionarCromossomo(Cromossomo[] cromo) {
        populacao.addAll(Arrays.asList(cromo));
    }

    public void adicionarCromossomo(Cromossomo cromo) {
        populacao.add(cromo);
    }

    public void adicionarCromossomo(Populacao pop) {
        populacao.addAll(pop.populacao);

    }

    public static Cromossomo maisApto(ArrayList<Cromossomo> populacao) {
        Cromossomo maior = populacao.get(0);

        for (Cromossomo m : populacao) {
            if (m.valorFitness > maior.valorFitness) {
                maior = m;
            }
        }

        return maior;
    }

    public static Cromossomo maisApto(Cromossomo[] arranjo) {
        Cromossomo maior = arranjo[0];

        for (Cromossomo m : arranjo) {
            if (m.valorFitness > maior.valorFitness) {
                maior = m;
            }
        }

        return maior;
    }


    public ArrayList<Cromossomo> getPopulacao() {
        return populacao;
    }

    public void verPopulacao() {
        for (Cromossomo cromossomo : populacao)
            System.out.println(cromossomo.valorFitness);
    }

    public void ordenarPopulacao() {
        byte aux[];
        for (int i = 0; i < populacao.size() - 1; ) {
            if (populacao.get(i).valorFitness > populacao.get(i + 1).valorFitness) {
                aux = populacao.get(i).cromossomoEmBinario;
                populacao.get(i).cromossomoEmBinario = populacao.get(i + 1).cromossomoEmBinario;
                populacao.get(i + 1).cromossomoEmBinario = aux;
                populacao.get(i + 1).update();
                populacao.get(i).update();
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
            }
        }
    }


    public void criarPopulacaoIntermediaria(int numPop) {
        Populacao intermediarios = new Populacao();
        Populacao aux = new Populacao();

        int i = 0;
        do {
            aux.populacao.clear();
            aux.criarPopulacao(numPop);
            intermediarios.adicionarCromossomo(Populacao.maisApto(selecionarDezAleatorios(aux.populacao)));
            i++;
        } while (i < numPop);
        aux.populacao.clear();
        populacao.addAll(intermediarios.populacao);
    }

    public static Cromossomo[] selecionarDezAleatorios(ArrayList<Cromossomo> populacao) {
        //populacao = ordenarIntermediarios(populacao);


        LinkedHashSet<Integer> aleatorios = new LinkedHashSet<>();

        if (populacao.size() < 10) {
            System.out.println("A população é menor que 10.");
            return null;
        }

        do {

            aleatorios.add(new Random().nextInt(populacao.size()));
        } while (aleatorios.size() < 10);

        Cromossomo[] sorteados = new Cromossomo[10];
        int i = 0;

        for (int n : aleatorios) {
            sorteados[i] = populacao.get(n);
            i++;
        }

        return sorteados;
    }

    public static ArrayList<Cromossomo> ordenarIntermediarios(ArrayList<Cromossomo> populacao) {
        byte aux[];
        for (int i = 0; i < populacao.size() - 1; ) {

            if (populacao.get(i).valorFitness > populacao.get(i + 1).valorFitness) {
                aux = populacao.get(i).cromossomoEmBinario;
                populacao.get(i).cromossomoEmBinario = populacao.get(i + 1).cromossomoEmBinario;
                populacao.get(i + 1).cromossomoEmBinario = aux;
                populacao.get(i + 1).update();
                populacao.get(i).update();
                if (i > 0) {
                    i--;
                }
            } else {
                i++;
            }
        }
        return populacao;
    }
}















