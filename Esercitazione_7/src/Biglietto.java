public class Biglietto {
    private int numeroPosto;
    private String settore;
    private double prezzoBase;
    private boolean isVenduto = false;
    public Biglietto(int numeroPosto, String settore, double prezzoBase) {
        this.numeroPosto = numeroPosto;
        this.settore = settore;
        this.prezzoBase = prezzoBase;
        this.isVenduto = false;
    }
    public int getNumeroPosto() {
        return numeroPosto;
    }
    public String getSettore() {
        return settore;
    }
    public double getPrezzoBase() {
        return prezzoBase;
    }
    public boolean getIsVenduto() {
        return isVenduto;
    }
    public void setIsVenduto(boolean isVenduto) {
        this.isVenduto = isVenduto;
    }
    public static void arrotondamento(double valore){
        valore = Math.round(valore * 100) / 100; 
    }
}
