import javax.swing.*;
import java.awt.*;

public class Prova extends JFrame {
    Articolo[] panini = {
        new Articolo("Hamburger", 5.00),
        new Articolo("Toast", 3.00),
        new Articolo("Prosciutto e mozzarella", 4.00)
    };
    Articolo[] bibite = {
        new Articolo("Coca cola", 2.00),
        new Articolo("Sprite", 2.50),
        new Articolo("Acqua minerale", 0.80),
        new Articolo("Fanta", 1.50)
    };
    JButton[] bottoniPiu = new JButton[bibite.length + panini.length];
    JButton[] bottoniMeno = new JButton[bibite.length + panini.length];
    JLabel[] quantita = new JLabel[bibite.length + panini.length];
    JLabel totPanini = new JLabel("Panini: 0");
    JLabel totBibite = new JLabel("Bibite: 0");
    JLabel totPrezzo = new JLabel("Totale: 0 €");
    int contPanini=0, contBibite=0;
    double prezzoTotale = 0;
    public Prova(){
        setTitle("Bar");
        setSize(500, 500);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel ordini = new JPanel(new GridLayout((panini.length + bibite.length), 5, 5, 5));
        JPanel scontrino = new JPanel(new GridLayout(1, 4, 5, 5)); 
        for(int i=0; i<panini.length; i++){
            ordini.add(new JLabel(panini[i].getNome()));
            ordini.add(new JLabel(panini[i].getPrezzo() + " €"));
            quantita[i] = new JLabel("0");
            bottoniPiu[i] = new JButton("+");
            final int numero = i;
            bottoniPiu[i].addActionListener(e ->{
                quantita[numero].setText(String.valueOf(Integer.valueOf(quantita[numero].getText()) + 1));
                contPanini +=1;
                prezzoTotale += panini[numero].getPrezzo();
                totPanini.setText("Panini: " + contPanini);
                if(contPanini>2 && contBibite>1){
                    totPrezzo.setText(String.format("Totale: %.2f €(senza sconto di %.2f €)", (prezzoTotale*0.9), prezzoTotale));
                } else{
                    totPrezzo.setText(String.format("Totale: %.2f €", prezzoTotale));
                }
                
            });
            bottoniMeno[i] = new JButton("-");
            bottoniMeno[i].addActionListener(e ->{
                if(Integer.parseInt(quantita[numero].getText())>0){
                    quantita[numero].setText(String.valueOf(Integer.valueOf(quantita[numero].getText()) - 1));
                    contPanini -=1;
                    prezzoTotale -= panini[numero].getPrezzo();
                    totPanini.setText("Panini: " + contPanini);
                    if(contPanini>2 && contBibite>1){
                        totPrezzo.setText(String.format("Totale: %.2f €(senza sconto di %.2f €)", (prezzoTotale*0.9), prezzoTotale));
                    } else{
                        totPrezzo.setText(String.format("Totale: %.2f €", prezzoTotale));
                    }
                }
            });
            ordini.add(quantita[i]);
            ordini.add(bottoniPiu[i]);
            ordini.add(bottoniMeno[i]);
        }
        for (int i = 0; i < bibite.length; i++) {
            ordini.add(new JLabel(bibite[i].getNome()));
            ordini.add(new JLabel(bibite[i].getPrezzo() + " €"));
            quantita[i+panini.length] = new JLabel("0");
            bottoniPiu[i+panini.length] = new JButton("+");
            final int numero = i;
            bottoniPiu[i+panini.length].addActionListener(e ->{
                quantita[numero+panini.length].setText(String.valueOf(Integer.valueOf(quantita[numero+panini.length].getText()) + 1));
                contBibite +=1;
                prezzoTotale += bibite[numero].getPrezzo();
                totBibite.setText("Bibite: " + contBibite);
                if(contPanini>2 && contBibite>1){
                    totPrezzo.setText(String.format("Totale: %.2f €(senza sconto di %.2f €)", (prezzoTotale*0.9), prezzoTotale));
                } else{
                    totPrezzo.setText(String.format("Totale: %.2f €", prezzoTotale));
                }
            });
            bottoniMeno[i+panini.length] = new JButton("-");
            bottoniMeno[i+panini.length].addActionListener(e ->{
                if(Integer.parseInt(quantita[numero+panini.length].getText())>0){
                    quantita[numero+panini.length].setText(String.valueOf(Integer.valueOf(quantita[numero+panini.length].getText()) - 1));
                    contBibite -=1;
                    prezzoTotale -= bibite[numero].getPrezzo();
                    totBibite.setText("Bibite: " + contBibite);
                    if(contPanini>2 && contBibite>1){
                        totPrezzo.setText(String.format("Totale: %.2f €(senza sconto di %.2f €)", (prezzoTotale*0.9), prezzoTotale));
                    } else{
                        totPrezzo.setText(String.format("Totale: %.2f €", prezzoTotale));
                    }
                }
            });
            ordini.add(quantita[i+panini.length]);
            ordini.add(bottoniPiu[i+panini.length]);
            ordini.add(bottoniMeno[i+panini.length]);
        }
        scontrino.add(new JLabel("Scontrino: "));
        scontrino.add(totPanini);
        scontrino.add(totBibite);
        scontrino.add(totPrezzo);
        add(ordini);
        add(scontrino);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Prova();
    }
}
