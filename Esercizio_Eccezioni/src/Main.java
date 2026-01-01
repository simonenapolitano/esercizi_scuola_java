public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Divisione: " + Calcolatrice.dividi(3, 0));
        } catch (ArithmeticException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}
