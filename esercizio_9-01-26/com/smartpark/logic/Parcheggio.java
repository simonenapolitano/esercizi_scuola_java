package com.smartpark.logic;

import java.util.ArrayList;

import com.smartpark.model.*;
import com.smartpark.exceptions.*;
import java.util.Scanner;

public class Parcheggio {
    Scanner input = new Scanner(System.in);
    private final Double tariffaOrariaAutomobile = 5.00, tariffaOrariaMotociclo = 2.50;
    private final static Integer capacitaMassima = 1;
    Integer scelta, index; 
    ArrayList<Veicolo> veicoli = new ArrayList<>();
    public Parcheggio(){
        do{
            try {
                System.out.println("[1]Registra l'entrata di un veicolo\n[2]Registra l'uscita di un veicolo e mostra il costo di sosta\n[3]Cerca un veicolo tramite la targa\n[4]Esci");
                scelta = input.nextInt();
                input.nextLine();
                switch(scelta){
                    case 1:
                        try {
                            registraEntrata();
                        } catch (CapacitaEsauritaException e) {
                            System.out.println("Errore: " + e.getMessage());
                        } finally {
                            System.out.println("Operazione di registrazione terminata.");
                        }
                        break;
                    case 2:
                        registraUscita();
                        break;
                    case 3:
                        String targa;
                        do{ 
                            System.out.println("Inserisci la targa del veicolo da cercare: ");
                            targa = input.nextLine();
                            if(targa.length() < 7){
                                System.out.println("Troppo corta per essere una targa. Reinserire:\n");
                            }
                        }while(targa.length() < 7);
                        System.out.println(cercaTramiteTarga(targa)? "Veicolo presente al posto " + index : "Veicolo non trovato");
                        break;
                    case 4:
                        System.out.println("Arrivederci!");
                        System.exit(0);
                        break;
                    default:
                        throw new SceltaNonValidaException("Scelta |" + scelta + "| non valida.");
                }
            } catch (SceltaNonValidaException e) {
                System.out.println("Errore: " + e.getMessage());
            } finally {
                scelta = 0;
            }
        }while(true);
    }
    private void registraEntrata(){
        if (veicoli.size() >= capacitaMassima) {
            throw new CapacitaEsauritaException("Capacità massima del parcheggio raggiunta (" + capacitaMassima + ")");
        }
        Integer scelta, numeroPosti, cilindrata;
        String targa, marca, orarioIngresso;
        do{ 
            System.out.println("Automobile[1] o motociclo[2]? ");
            scelta = input.nextInt();
            input.nextLine();
            if(scelta<1 || scelta>2){
                System.out.println("Scelta non valida");
            }
        }while(scelta<1 || scelta>2);
        do{ 
            System.out.println("Targa: ");
            targa = input.nextLine();
            if(targa.length() < 7){
                System.out.println("Troppo corta per essere una targa. Reinserire:\n");
            }
            if(veicoli.size() > 0 && cercaTramiteTarga(targa) == true){
                System.out.println("Veicolo gia registrato nel garage. Reinserire:\n");
            }
        }while(targa.length() < 7 || (veicoli.size() > 0 && cercaTramiteTarga(targa) == true));
        System.out.println("Marca: ");
        marca = input.nextLine();
        do{
            System.out.println("Orario di ingresso del veicolo(HH:mm): ");
            orarioIngresso = input.nextLine();
            if(controllaOrario(orarioIngresso) == false){
                System.out.println("Formato dell'orario sbagliato. Reinserire:\n");
            }
        }while(controllaOrario(orarioIngresso) == false);
        do{
            System.out.println("Numero dei posti: ");
            numeroPosti = input.nextInt();
            input.nextLine();
            if(numeroPosti < 0){
                System.out.println("Numero di posti non valido. Reinserire:\n");
            }
        }while(numeroPosti < 0);
        System.out.println("Cilindrata: ");
        cilindrata = input.nextInt();
        input.nextLine();
        if(scelta == 1){
            veicoli.add(new Automobile(targa, marca, orarioIngresso, numeroPosti, cilindrata));
        } else{
            veicoli.add(new Motociclo(targa, marca, orarioIngresso, numeroPosti, cilindrata));
        }
    }
    private void registraUscita(){
        String targa, orarioUscita;
        Veicolo veicoloScelto = null;
        if(veicoli.size() < 1){
            System.out.println("Non è presente nessun veicolo nel garage.");
            return;
        }
        do{ 
            System.out.println("Inserisci la targa del veicolo che sta uscendo: ");
            targa = input.nextLine();
            if(targa.length() < 7){
                System.out.println("Troppo corta per essere una targa. Reinserire:\n");
            }
        }while(targa.length() < 7);
        for (int i = 0; i < veicoli.size(); i++) {
            Veicolo veicolo = veicoli.get(i);
            if(!veicolo.getTarga().equals(targa) && i == (veicoli.size()-1)){
                System.out.println("Veicolo non presente nel garage.");
                return;
            } else if(veicolo.getTarga().equals(targa)){
                veicoloScelto = veicolo;
            }
        }
        do{
            System.out.println("Orario di uscita del veicolo(HH:mm): ");
            orarioUscita = input.nextLine();
            if(controllaOrario(orarioUscita) == false){
                System.out.println("Formato dell'orario sbagliato. Reinserire:\n");
            }
        }while(controllaOrario(orarioUscita) == false);
        if(veicoloScelto!=null){
            if(veicoloScelto instanceof Automobile){
                System.out.println("Il prezzo da pagare di questo veicolo è: " + calcolaPrezzo(veicoloScelto.getOrarioIngresso(), orarioUscita, tariffaOrariaAutomobile));
            } else if(veicoloScelto instanceof Motociclo){
                System.out.println("Il prezzo da pagare di questo veicolo è: " + calcolaPrezzo(veicoloScelto.getOrarioIngresso(), orarioUscita, tariffaOrariaMotociclo));
            }
        }
    }
    private boolean cercaTramiteTarga(String targa){
        for (int i = 0; i < veicoli.size(); i++) {
            Veicolo veicolo = veicoli.get(i);
            if(veicolo.getTarga().equals(targa)){
                index = (i+1);
                return true;
            }
        }
        return false;
    }
    private boolean controllaOrario(String orario){
        String[] parti = orario.split(":");
        if(!orario.contains(":")){ //controlla se il formato dell'orario è sbagliato
            return false;
        }
        if(parti.length != 2){ //controlla se il formato dell'orario è sbagliato
            return false;
        }
        try {
            Integer ore = Integer.parseInt(parti[0]), minuti = Integer.parseInt(parti[1]); 
            if(ore < 0 || ore >= 24){ //controlla se è stata inserita un'ora maggiore o uguale di 24(es 48:59)
                return false;
            } else if(minuti < 0 || minuti > 59){ //controlla se è stato inserito un minutaggio maggiore di 59(es 24:60)
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true; //ritorna vero se non viene stoppato prima
    } 
    private Double calcolaPrezzo(String orarioIngresso, String orarioUscita, Double tariffa){
        String ingresso[] = orarioIngresso.split(":");
        String uscita[] = orarioUscita.split(":");
        
        Integer minutiIngresso = Integer.parseInt(ingresso[0]) * 60 + Integer.parseInt(ingresso[1]);
        Integer minutiUscita = Integer.parseInt(uscita[0]) * 60 + Integer.parseInt(uscita[1]);

        if(minutiUscita < minutiIngresso){ //controlla se è uscito un giorno diverso da quando è entrato
            minutiUscita += 24 * 60;
        }

        Integer durataMinuti = minutiUscita - minutiIngresso;
        Double ore = durataMinuti / 60.0;
        return ore * tariffa;
    }
    public static void main(String[] args) {
        new Parcheggio();
    }
}
