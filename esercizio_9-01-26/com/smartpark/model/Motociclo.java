package com.smartpark.model;

public class Motociclo extends Veicolo {
    private Integer numeroPosti;
    private Integer cilindrata;
    public Motociclo(String targa, String marca, String orarioIngresso, Integer numeroPosti, Integer cilindrata){
        super(targa, marca, orarioIngresso);
        this.numeroPosti = numeroPosti;
        this.cilindrata = cilindrata;
    }
    public Integer getNumeroPosti(){
        return numeroPosti;
    }
    public Integer getCilindrata(){
        return cilindrata;
    }
    public void setNumeroPosti(Integer numeroPosti){
        this.numeroPosti = numeroPosti;
    }
    public void setCilindrata(Integer cilindrata){
        this.cilindrata = cilindrata;
    }
}
