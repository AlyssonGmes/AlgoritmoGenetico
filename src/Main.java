import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //modelar a possível solução
        //criar a pop inicial
        // calcular a aptidão
        //operador de seleção - torneio
        // recombinação - "ajuste fino"
        //mutação - "ajuste grosso"


        double []popNova = popNova();

        double maior = 0;
        for (double a : popNova) {

            if(a > maior){
                maior = a;
            }

            System.out.println(a);
        }

        System.out.println("Maior de todos: "+maior);

    }

    //retorna um array de n bits
    static byte[] criarCromossomoBits(int num) {
        Random rnd = new Random();

        byte [] cromossomo = new byte[num];
        for (int i = 0; i < cromossomo.length; i++) {
            cromossomo[i] = (byte) rnd.nextInt(2);
        }

        return cromossomo;
    }

    //converte um array em binários para um número inteiro decimal
    static int binárioEmDecimal(byte []arr) {
        int soma = 0;
        int exp = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            soma += arr[i] * (Math.pow(2, exp));
            exp--;
        }

        return soma;
    }

    //fórmula de intervalo desejado entre -1 e 2
    static double intervaloDesejado(int num) {
        double valor = 0;

        valor = (-1 + num * (3 / (Math.pow(2, 22) - 1)));

        return valor;
    }

    //utiliza as classes anteriores e cria um array de "intervalos desejados" com os bits em decimal, a população
    static double[] criarPopulacao(int total) {
        //tamanho população
        double []populacao = new double[total];

        for (int i = 0; i < total; i++) {
            populacao[i] = intervaloDesejado(binárioEmDecimal(criarCromossomoBits(22)));
        }
        return populacao;
    }

    //aplica função fitness
    static double[] funcaoFitness(double[] populacao) {

        double []arrFitness = new double[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            arrFitness[i] = populacao[i] * Math.sin(10 * Math.PI * populacao[i]) + 1;
        }

        return ordemCrescente(arrFitness);
    }

    //ordena vetor double
    static double[] ordemCrescente(double[] arranjo) {
        double aux = 0;
        double []novaOrdem = new double[arranjo.length];

        for (int a = 0; a < novaOrdem.length; a++) {
            for (int b = 0; b < novaOrdem.length; b++) {
                if (arranjo[b] <= arranjo[a]) {
                    aux = arranjo[b];
                    arranjo[b] = arranjo[a];
                    arranjo[a] = aux;
                }
            }
        }

        return arranjo;
    }

    //seleciona dez aleatoriamente entre um arranjo
    static double[] selecionarDezAleatorios(double[] arranjo) {
        double[] topDez = new double[10];

        for (int i = 0; i < 10; i++) {
            topDez[i] = arranjo[new Random().nextInt(arranjo.length)];
        }
        return topDez;
    }

    //seleciona os dez melhores até formar 100 indivíduo e retorna o array com a nova população
    static double[] popNova() {
        double []novaPop = new double[100];
        double []dezIndividuos = new double[10];

        for (int a = 0; a < 100; a++) {
            dezIndividuos = selecionarDezAleatorios(funcaoFitness(criarPopulacao(300)));
            for (int i = 0; i < 10 && a < 100; i++, a++) {
                novaPop[a] = dezIndividuos[i];
            }
        }
        return novaPop;
    }

    //divide um array de byte no meio e troca os lados das metades
    public byte[] cruzamentoDeUmPonto(byte []cromossomo){
        byte pai1[] = new byte[11], pai2[] = new byte[11];

        for (int i = 0; i < cromossomo.length; i++) {
            if(i < (cromossomo.length/2)) {
                pai2[i] = cromossomo[i];
            }else{
                pai1[i] = cromossomo[i];
            }
        }

        for (int i = 0; i < cromossomo.length; i++) {
            if(i < (cromossomo.length/2)) {
                cromossomo[i] =  pai2[i];

            }else{
                cromossomo[i] =  pai1[i];
            }
        }

        return cromossomo;
    }
}
