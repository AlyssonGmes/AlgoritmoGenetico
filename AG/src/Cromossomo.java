import java.util.Random;

public class Cromossomo {
    byte[] cromossomoEmBinario;
    int cromossomoEmDecimal;
    double valorDesejado;
    double valorFitness;

    public Cromossomo() {
        cromossomoEmBinario = criarCromossomoBits(22);
        cromossomoEmDecimal = binarioEmDecimal(cromossomoEmBinario);
        valorDesejado = intervaloDesejado(cromossomoEmDecimal);
        valorFitness = funcaoFitness(valorDesejado);
    }

    public static byte[] criarCromossomoBits(int num) {
        Random rnd = new Random();

        byte[] cromossomo = new byte[num];
        for (int i = 0; i < cromossomo.length; i++) {
            cromossomo[i] = (byte) rnd.nextInt(2);
        }

        return cromossomo;
    }

    static int binarioEmDecimal(byte[] arr) {
        int soma = 0;
        int exp = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            soma += arr[i] * (Math.pow(2, exp));
            exp--;
        }

        return soma;
    }

    static double intervaloDesejado(int num) {
        double valor = 0;

        valor = (-1 + num * (3 / (Math.pow(2, 22) - 1)));

        return valor;
    }

    static double funcaoFitness(double populacao) {
        return populacao * Math.sin(10 * Math.PI * populacao + 1);
    }

    public void update() {
        cromossomoEmDecimal = binarioEmDecimal(cromossomoEmBinario);
        valorDesejado = intervaloDesejado(cromossomoEmDecimal);
        valorFitness = funcaoFitness(valorDesejado);
    }
}

  /*

        static double[] funcaoFitness(double[] populacao) {


        double []arrFitness = new double[populacao.length];
        for (int i = 0; i < populacao.length; i++) {
            arrFitness[i] = populacao[i] * Math.sin(10 * Math.PI * populacao[i]) + 1;
        }

        return ordemCrescente(arrFitness);


        /*    static double[] intervaloDesejado(int num[]) {
        double []temp = new double[num.length];

        for(int i = 0; i < num.length; i++) {
            temp[i] = (-1 + temp[i] * (3 / (Math.pow(2, 22) - 1)));
        }

        return temp;
         }

   */

