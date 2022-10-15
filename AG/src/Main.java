public class Main {
    public static void main(String[] args) {
        Populacao p1 = new Populacao();
        p1.criarPopulacao();
        Geracao g1 = new Geracao();
        int i = 0;

        //Não vai fazer mutação enquanto hovuer nulos ocupando o vetor.
        do{
            g1.novaGeracao.inserirIndividuo(Geracao.maiorValor(Geracao.selecionarDezAleatorios(p1)));
            i++;
        }while(i < g1.novaGeracao.getTamanhoPop());
        g1.fazerMutacao();
        //Populacao.verPop(g1.novaGeracao);
    }
}
