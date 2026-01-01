package com.smartpark.model;

public class Veicolo {
    private String targa;
    private String marca;
    private String orarioIngresso;
    public Veicolo(String targa, String marca, String orarioIngresso){
        this.targa = targa;
        this.marca = marca;
        this.orarioIngresso = orarioIngresso;
    }
    public String getTarga(){
        return targa;
    }
    public String getMarca(){
        return marca;
    }
    public String getOrarioIngresso(){
        return orarioIngresso;
    }
    public void setTarga(String targa){
        this.targa = targa;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setOrarioIngresso(String orarioIngresso){
        this.orarioIngresso = orarioIngresso;
    }
}
