import java.util.Random;

public class Main {
    public static void main(String[] args) {
        double popNova[] = popNova();

        for (double a : popNova) {
            System.out.println(a);
        }

    }

    //retorna um array de n bits
    public static byte[] criarCromossomoBits(int num) {

        Random rnd = new Random();

        byte cromossomo[] = new byte[num];
        for (int i = 0; i < cromossomo.length; i++) {
            cromossomo[i] = (byte) rnd.nextInt(2);
        }

        return cromossomo;
    }

    //converte um array de bits para um número inteiro decimal
    public static int bitsEmDecimal(byte arr[]) {
        int soma = 0;
        int exp = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            soma += arr[i] * (Math.pow(2, exp));
            exp--;
        }

        return soma;
    }

    //fórmula de intervalo desejado entre -1 e 2
    public static double intervaloDesejado(int num) {
        double valor = 0;

        valor = (-1 + num * (3 / (Math.pow(2, 22) - 1)));

        return valor;
    }

    //utiliza as classes anteriores e cria um array de "intervalos desejados" com os bits em decimal
    public static double[] criarPopulacao(int total) {
        //tamanho população
        double populacao[] = new double[total];

        for (int i = 0; i < total; i++) {
            populacao[i] = intervaloDesejado(bitsEmDecimal(criarCromossomoBits(22)));
        }
        return populacao;
    }

    //aplica função fitness
    public static double[] funcaoFitness(double[] populacao) {

        double arrFitness[] = new double[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            arrFitness[i] = populacao[i] * Math.sin(10 * Math.PI * populacao[i]) + 1;
        }

        return ordemCrescente(arrFitness);
    }

    //ordena vetor double
    public static double[] ordemCrescente(double[] arranjo) {
        double aux = 0;
        double[] novaOrdem = new double[arranjo.length];

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
    public static double[] selecionarDezAleatorios(double[] arranjo) {
        double[] topDez = new double[10];

        for (int i = 0; i < 10; i++) {
            topDez[i] = arranjo[new Random().nextInt(300)];
        }
        return topDez;
    }

    //seleciona os dez melhores até formar 100 indivíduo
    public static double[] popNova() {
        double novaPop[] = new double[100];
        double dezIndividuos[] = new double[10];

        // selecionarDezMelhores(funcaoFitness(criarPopulacao(300)));
        for (int a = 0; a < 100; a++) {
            dezIndividuos = selecionarDezAleatorios(funcaoFitness(criarPopulacao(300)));
            for (int i = 0; i < 10 && a < 100; i++, a++) {
                novaPop[a] = dezIndividuos[i];
            }
        }
        return novaPop;
    }
}
