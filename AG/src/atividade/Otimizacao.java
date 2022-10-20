package atividade;
//
public class Otimizacao {
    double valorA = 0, valorB = 0, resultado = 0;
    public Otimizacao(double a, double b) {
        valorA = a;
        valorB = b;
        resultado = funcaoOtimiza((int) a, (int)b);
    }
    public static double funcaoOtimiza(double a, double b) {
        return 2 * (Math.pow(a, 2)) + b - 57;
    }

}
