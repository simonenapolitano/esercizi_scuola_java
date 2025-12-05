package doc;

public class Articolo {
    private String nome;
    private double prezzo;
    private final double iva = 0.22;
    public Articolo(String nome, double prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }
    public String getNome(){
        return nome;
    }
    public double getPrezzo(){
        return prezzo;
    }
    public double getIva(){
        return iva;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }
}
