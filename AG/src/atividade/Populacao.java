package atividade;

import java.util.ArrayList;
import java.util.Arrays;

public class Populacao {
    ArrayList<Cromossomo> populacao = new ArrayList<>();
    public void criarPopulacao(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            populacao.add(new Cromossomo());
        }
    }

    public void adicionarCromossomo(Cromossomo[] cromo){
        populacao.addAll(Arrays.asList(cromo));
    }

    public static Cromossomo maisApto(Cromossomo[] arranjo){

        Cromossomo maior = arranjo[0];

        for(Cromossomo m : arranjo){
            if(m.valorFitness > maior.valorFitness){
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

}















