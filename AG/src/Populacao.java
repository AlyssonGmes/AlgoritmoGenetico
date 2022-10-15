public class Populacao {
    Cromossomo[] populacao;
    int tamanhoPop = 100;
    private static int index = 0;

    public Populacao() {
        populacao = new Cromossomo[tamanhoPop];
    }

    public Populacao(Cromossomo individuo) {
        populacao = new Cromossomo[tamanhoPop];
        populacao[index] = individuo;
        index++;
    }

    public void inserirIndividuo(Cromossomo individuo) {
        populacao[index] = individuo;
        index++;
    }

    public Cromossomo[] gerarPopulacao() {
        //tamanho população
        Cromossomo[] novaPop = new Cromossomo[tamanhoPop];

        for (int i = 0; i < tamanhoPop; i++) {
            novaPop[i] = new Cromossomo();
        }

        return ordemCrescente(novaPop);
    }

    //Armazena na instância
    public void criarPopulacao(){
        populacao = gerarPopulacao();
    }

    static Cromossomo[] ordemCrescente(Cromossomo[] arranjo) {
        double aux = 0;
        Cromossomo[] novaOrdem = new Cromossomo[arranjo.length];

        for (int a = 0; a < novaOrdem.length; a++) {
            for (int b = 0; b < novaOrdem.length; b++) {
                if (arranjo[b].valorFitness <= arranjo[a].valorFitness) {
                    aux = arranjo[b].valorFitness;
                    arranjo[b].valorFitness = arranjo[a].valorFitness;
                    arranjo[a].valorFitness = aux;
                }
            }
        }

        return arranjo;
    }

    static void verPop(Populacao populacao) {
        for (Cromossomo c : populacao.populacao) {
            if (c != null) {
                System.out.println(c.valorFitness);
            }
        }
    }

    public int getTamanhoPop() {
        return tamanhoPop;
    }
}

