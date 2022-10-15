package atividade;

import java.util.Random;

public class Cromossomo {

    byte[] cromossomoEmBinario;
    double cromossomoEmDecimal;
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

        for (byte b : arr) {
            soma += b * (Math.pow(2, exp));
            exp--;
        }

        return soma;
    }

    static double intervaloDesejado(double num) {
        return (1 + (10-1) * num/Math.pow(2, 22)-1);
    }

    static double funcaoFitness(double valorDecimal) {
        return (valorDecimal * (Math.sin(10 * Math.PI * valorDecimal)) + 1);
    }

    public void update() {
        cromossomoEmDecimal = binarioEmDecimal(cromossomoEmBinario);
        valorDesejado = intervaloDesejado(cromossomoEmDecimal);
        valorFitness = funcaoFitness(valorDesejado);
    }

    public byte[] getCromossomoEmBinario() {
        return cromossomoEmBinario;
    }

    public double getCromossomoEmDecimal() {
        return cromossomoEmDecimal;
    }

    public double getValorDesejado() {
        return valorDesejado;
    }

    public double getValorFitness() {
        return valorFitness;
    }

    public void setCromossomoEmBinario(byte[] cromossomoEmBinario) {
        this.cromossomoEmBinario = cromossomoEmBinario;
    }

    public void setCromossomoEmDecimal(double cromossomoEmDecimal) {
        this.cromossomoEmDecimal = cromossomoEmDecimal;
    }

    public void setValorDesejado(double valorDesejado) {
        this.valorDesejado = valorDesejado;
    }

    public void setValorFitness(double valorFitness) {
        this.valorFitness = valorFitness;
    }
}
















