import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    JLabel risultato;
    Articolo temp;
    Articolo[] panini = {
        new Articolo("Panino", 2.00),
        new Articolo("Panino completo", 2.50),
        new Articolo("Piadina", 3.00)
    };
    Articolo[] bibite = {
        new Articolo("Coca-cola", 2.00),
        new Articolo("Acqua", 0.80),
        new Articolo("Fanta", 2.00)
    };
    public MainGUI(){
        Sorting.bubbleSort(panini);
        Sorting.bubbleSort(bibite);
        setTitle("Bar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(500, 500);
        JPanel paniniBibite = new JPanel(new GridLayout((panini.length + bibite.length), 3, 3, 3));
        for(int i=0; i<panini.length; i++){
                paniniBibite.add(new JLabel(String.valueOf(panini[i].getNome())));
                paniniBibite.add(new JLabel(String.valueOf(panini[i].getPrezzo())));
                paniniBibite.add(new JButton("Aggiungi al carrello"));
        }
        for (int i = 0; i < bibite.length; i++) {
            paniniBibite.add(new JLabel(String.valueOf(bibite[i].getNome())));
                paniniBibite.add(new JLabel(String.valueOf(bibite[i].getPrezzo())));
                paniniBibite.add(new JButton("Aggiungi al carrello"));
        }
        add(paniniBibite);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MainGUI();
    }
}
