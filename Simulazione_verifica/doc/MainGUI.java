package doc;
import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private Articolo[] panini = {
        new Articolo("Prosciutto cotto", 2.00),
        new Articolo("Prosciutto crudo", 3.50),
        new Articolo("Salame", 2.50)
    };
    private Articolo[] bibite = {
        new Articolo("Coca cola", 1.50),
        new Articolo("Fanta", 2.00),
        new Articolo("Sprite", 2.50),
        new Articolo("Acqua minerale", 0.80)
    };
    private JLabel[] quantita = new JLabel[panini.length + bibite.length];
    private JButton[] bottoniPiu = new JButton[panini.length + bibite.length], bottoniMeno = new JButton[panini.length + bibite.length];
    private JLabel paniniTot = new JLabel("Panini: 0"), bibiteTot = new JLabel("Bibite: 0"), prezzoTot = new JLabel("Totale: 0 €");
    public MainGUI(){
        setTitle("Bar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        
        JPanel ordine = new JPanel(new GridLayout((panini.length + bibite.length), 5, 3, 3));
        JPanel scontrino  = new JPanel(new GridLayout(1, 4, 3, 3));
        for (int i = 0; i < panini.length; i++) {
            ordine.add(new JLabel(panini[i].getNome()));
            ordine.add(new JLabel(String.valueOf(panini[i].getPrezzo())));
            quantita[i] = new JLabel("0");
            bottoniPiu[i] = new JButton("+");
            final int index = i;
            bottoniPiu[i].addActionListener(e ->{
                quantita[index].setText(String.valueOf(Integer.parseInt(quantita[index].getText()) + 1));
            });
            bottoniMeno[i] = new JButton("-");
            bottoniMeno[i].addActionListener(e ->{
                if(Integer.parseInt(quantita[index].getText())>0){
                    quantita[index].setText(String.valueOf(Integer.parseInt(quantita[index].getText()) - 1));
                }
            });
            ordine.add(quantita[i]);
            ordine.add(bottoniPiu[i]);
            ordine.add(bottoniMeno[i]);
        }
        for (int i = 0; i < bibite.length; i++) {
            int finalIndex = i + panini.length;
            ordine.add(new JLabel(bibite[i].getNome()));
            ordine.add(new JLabel(String.valueOf(bibite[i].getPrezzo()  + " €")));
            quantita[finalIndex] = new JLabel("0");
            bottoniPiu[finalIndex] = new JButton("+");
            final int index = finalIndex;
            bottoniPiu[finalIndex].addActionListener(e ->{
                quantita[index].setText(String.valueOf(Integer.parseInt(quantita[index].getText()) + 1));
            });
            bottoniMeno[finalIndex] = new JButton("-");
            bottoniMeno[finalIndex].addActionListener(e ->{
                if(Integer.parseInt(quantita[index].getText())>0){
                    quantita[index].setText(String.valueOf(Integer.parseInt(quantita[index].getText()) - 1));
                }
            });
            ordine.add(quantita[finalIndex]);
            ordine.add(bottoniPiu[finalIndex]);
            ordine.add(bottoniMeno[finalIndex]);
        }
        scontrino.add()

        add(ordine);
        add(scontrino);


        

        setVisible(true);
    }
    public static void main(String args[]){
        new MainGUI();
    }
}
