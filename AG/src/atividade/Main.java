package atividade;
//
public class Main {
    public static void main(String[] args) {
        Populacao popInicial = new Populacao();
        popInicial.criarPopulacaoIntermediaria(10);
        int numGeracoes = 10;

        Geracao g1 = new Geracao(numGeracoes, popInicial);
        g1.populacaoGerada.verPopulacao();
        System.out.println("Mais apto: " + g1.populacaoGerada.maisApto.valorFitness);
        System.out.println("Nº de Gerações: "+Geracao.numGeracoes);
    }
}






















