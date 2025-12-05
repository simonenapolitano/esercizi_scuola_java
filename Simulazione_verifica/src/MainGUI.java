
import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    /**
     * Array di oggetti contenente i diversi tipi di panini disponibili.
     */
    Articolo[] panini = {
        new Articolo("Panino", 2.00),
        new Articolo("Panino completo", 2.50),
        new Articolo("Piadina", 3.00)
    };
    /**
     * Array di oggetti contenente i diversi tipi di bibite disponibili.
     */
    Articolo[] bibite = {/**Array di bibite */
        new Articolo("Coca-cola", 2.00),
        new Articolo("Acqua", 0.80),
        new Articolo("Fanta", 2.00)
    };
    /**
     * Array di per visualizzare la quantità selezionata per ciascun articolo (panino o bibita).
     * La dimensione è pari alla somma del numero di panini e bibite.
     */
    JLabel[] quantita = new JLabel[panini.length + bibite.length];
    /**
     * Array di {@link javax.swing.JButton} per incrementare la quantità di ciascun articolo.
     */
    JButton[] bottoniplus = new JButton[panini.length + bibite.length];
    /**
     * Array di per decrementare la quantità di ciascun articolo.
     */
    JButton[] bottoniminus = new JButton[panini.length + bibite.length];
    /**
     * Etichetta per visualizzare il totale dei panini ordinati.
     */
    JLabel paniniTotale;
    /**
     * Etichetta per visualizzare il totale delle bibite ordinate.
     */
    JLabel bibiteTotale;
    /**
     * Etichetta per visualizzare il prezzo totale dell'ordine, inclusivo di sconto se applicabile.
     */
    JLabel prezzoTotale;
    /**
     * Variabile che tiene traccia del prezzo totale dell'ordine non scontato.
     */
    double prezzoTot = 0;
    /**
     * Variabile temporanea di tipo {@link Articolo} (non utilizzata in questo codice).
     */
    Articolo temp;
    /**
     * Contatore per il numero totale di panini ordinati.
     */
    int contPanini=0;
    /**
     * Contatore per il numero totale di bibite ordinate.
     */
    int contBibite=0;
    
    /**
     * Costruttore della classe {@code MainGUI}.
     * Configura la finestra (JFrame), ordina gli array di articoli,
     * aggiunge i pannelli per gli articoli e per lo scontrino, e gestisce gli
     * {@link java.awt.event.ActionListener} per i pulsanti di incremento/decremento.
     */
    public MainGUI(){
        Sorting.bubbleSort(panini);
        Sorting.bubbleSort(bibite);
        setTitle("Bar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(500, 500);
        
        /**
         * Pannello principale che contiene gli articoli (panini e bibite)
         * con i relativi prezzi, quantità e pulsanti.
         * Utilizza un {@link java.awt.GridLayout} per una disposizione a griglia.
         */
        JPanel paniniBibite = new JPanel(new GridLayout((panini.length + bibite.length), 5, 3, 3));
        
        // Ciclo per la creazione degli elementi grafici relativi ai panini
        for(int i=0; i<panini.length; i++){
            paniniBibite.add(new JLabel(String.valueOf(panini[i].getNome())));
            paniniBibite.add(new JLabel(String.valueOf(panini[i].getPrezzo()) + " €"));
            bottoniplus[i] = new JButton("+");
            bottoniminus[i] = new JButton("-");
            quantita[i] = new JLabel("0");
            
            /**  
             * Inizializzazione delle etichette per lo scontrino
             * (Nota: vengono re-inizializzate ad ogni iterazione di questo ciclo for, ma
             * saranno aggiunte al pannello scontrino solo dopo questo e il ciclo delle bibite).
            */
            paniniTotale = new JLabel("Panini: 0");
            bibiteTotale = new JLabel("Bibite: 0");
            prezzoTotale = new JLabel("Totale: 0 €");
            
            final int numero = i;
            
            /**
             * Listener per il pulsante '+' del panino.
             * Incrementa la quantità, aggiorna i contatori e il prezzo totale,
             * e ricalcola lo sconto.
             */
            bottoniplus[i].addActionListener(e ->{
                quantita[numero].setText(String.valueOf(Integer.parseInt(quantita[numero].getText()) + 1));
                contPanini +=1;
                prezzoTot += panini[numero].getPrezzo();
                paniniTotale.setText("Panini: " + String.valueOf(contPanini));
                if(contPanini>2 && contBibite>1){
                    prezzoTotale.setText(String.format("Totale: %.2f €(senza sconto %.2f €)", (prezzoTot * 0.9), prezzoTot));
                } else{
                    prezzoTotale.setText(String.format("Totale: %.2f €", prezzoTot));
                }
            });
            
            /**
             * Listener per il pulsante '-' del panino.
             * Decrementa la quantità solo se maggiore di 0, aggiorna i contatori
             * e il prezzo totale, e ricalcola lo sconto.
             */
            bottoniminus[i].addActionListener(e->{
                if (Integer.parseInt(quantita[numero].getText()) > 0) {
                    quantita[numero].setText(String.valueOf(Integer.parseInt(quantita[numero].getText()) - 1));
                    contPanini -=1;
                    prezzoTot -= panini[numero].getPrezzo();
                    paniniTotale.setText("Panini: " + String.valueOf(contPanini));
                    if(contPanini>2 && contBibite>1){
                        prezzoTotale.setText(String.format("Totale: %.2f €(senza sconto %.2f €)", (prezzoTot * 0.9), prezzoTot));
                    } else{
                        prezzoTotale.setText(String.format("Totale: %.2f €", prezzoTot));
                    }
                }
            });
            
            paniniBibite.add(quantita[i]);
            paniniBibite.add(bottoniplus[i]);
            paniniBibite.add(bottoniminus[i]);
        }
        
        // Ciclo per la creazione degli elementi grafici relativi alle bibite
        for (int i = 0; i < bibite.length; i++) {
            paniniBibite.add(new JLabel(String.valueOf(bibite[i].getNome())));
            paniniBibite.add(new JLabel(String.valueOf(bibite[i].getPrezzo()) + " €"));
            
            // Calcolo degli indici corretti per gli array unificati
            bottoniplus[i+panini.length] = new JButton("+");
            bottoniminus[i+panini.length] = new JButton("-");
            quantita[i+panini.length] = new JLabel("0");
            
            final int numero = i;
            
            /**
             * Listener per il pulsante '+' della bibita.
             * Incrementa la quantità, aggiorna i contatori e il prezzo totale,
             * e ricalcola lo sconto.
             */
            bottoniplus[i+panini.length].addActionListener(e ->{
                quantita[numero+panini.length].setText(String.valueOf(Integer.parseInt(quantita[numero+panini.length].getText()) + 1));
                contBibite +=1;
                prezzoTot += bibite[numero].getPrezzo();
                bibiteTotale.setText("Bibite: " + String.valueOf(contBibite));
                if(contPanini>2 && contBibite>1){
                    prezzoTotale.setText(String.format("Totale: %.2f €(senza sconto %.2f €)", (prezzoTot * 0.9), prezzoTot));
                } else{
                    prezzoTotale.setText(String.format("Totale: %.2f €", prezzoTot));
                }
            });
            
            /**
             * Listener per il pulsante '-' della bibita.
             * Decrementa la quantità solo se maggiore di 0, aggiorna i contatori
             * e il prezzo totale, e ricalcola lo sconto.
             */
            bottoniminus[i+panini.length].addActionListener(e->{
                if (Integer.parseInt(quantita[numero+panini.length].getText()) > 0) {
                    quantita[numero+panini.length].setText(String.valueOf(Integer.parseInt(quantita[numero+panini.length].getText()) - 1));
                    contBibite -=1;
                    prezzoTot -= bibite[numero].getPrezzo();
                    bibiteTotale.setText("Bibite: " + String.valueOf(contBibite));
                    if(contPanini>2 && contBibite>1){
                        prezzoTotale.setText(String.format("Totale: %.2f €(senza sconto %.2f €)", (prezzoTot * 0.9), prezzoTot));
                    } else{
                        prezzoTotale.setText(String.format("Totale: %.2f €", prezzoTot));
                    }
                    
                } 
            });
            
            paniniBibite.add(quantita[i+panini.length]);
            paniniBibite.add(bottoniplus[i+panini.length]);
            paniniBibite.add(bottoniminus[i+panini.length]);
        }
        
        /**
         * Pannello per visualizzare i totali dell'ordine (lo "scontrino").
         * Utilizza un {@link java.awt.GridLayout}.
         */
        JPanel scontrino = new JPanel(new GridLayout(1, 5, 3, 3));
        scontrino.add(new JLabel("Scontrino: "));
        scontrino.add(paniniTotale);
        scontrino.add(bibiteTotale);
        scontrino.add(prezzoTotale);
        
        // Aggiunta dei pannelli al JFrame
        add(paniniBibite);
        add(scontrino);
        
        // Rende visibile la finestra
        setVisible(true);
    }
    
    /**
     * Metodo principale (entry point) dell'applicazione.
     * Crea una nuova istanza della GUI.
     * @param args Argomenti della riga di comando (non utilizzati).
     */
    public static void main(String[] args) {
        new MainGUI();
    }
}