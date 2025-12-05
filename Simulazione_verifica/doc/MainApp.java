package doc;
import java.util.Scanner;
public class MainApp {
    private Scanner input = new Scanner(System.in);
    private Articolo[] panini = {
        new Articolo("Prosciutto cotto", 3.00),
        new Articolo("Salame", 2.50),
        new Articolo("Prosciutto crudo", 3.50)
    };
    private Articolo[] bibite = {
        new Articolo("Coca cola", 1.50),
        new Articolo("Fanta", 2.00),
        new Articolo("Sprite", 2.50),
        new Articolo("Acqua minerale", 0.80)
    };
    private int scelta, paninoBibita, quantita, contPanini = 0, contBibite = 0;
    private double prezzoTotale = 0;
    private String scontrino = "";
    public MainApp(){
        do{
            Sorting.bubbleSort(panini);
            Sorting.bubbleSort(bibite);
            System.out.println("Scegli cosa fare: \n [1]Visualizza panini \n [2]Visualizza bibite \n [3]Visualizza lo scontrino ed esci\nScegli: ");
            scelta = input.nextInt();
            switch (scelta) {
                case 1:
                    for (int i = 0; i < panini.length; i++) {
                        System.out.println("Panino " + (i+1) + ": " + panini[i].getNome() + " " + panini[i].getPrezzo() + " euro");
                    }
                    do{
                        System.out.println("Scegli che panino prendere: ");
                        paninoBibita = input.nextInt();
                        if(paninoBibita>panini.length || paninoBibita<=0){
                            System.out.println("Scelta non valida. Reinserire: ");
                        }
                    }while(paninoBibita>panini.length || paninoBibita<=0);
                    System.out.println("Quanti? ");
                    quantita = input.nextInt();
                    contPanini += quantita;
                    prezzoTotale += quantita * panini[paninoBibita-1].getPrezzo();
                    scontrino += panini[paninoBibita-1].getNome() + " " + " " + quantita + " " + panini[paninoBibita-1].getPrezzo() + " euro\n";
                    break;
                case 2:
                    for (int i = 0; i < bibite.length; i++) {
                        System.out.println("Bibita " + (i+1) + ": " + bibite[i].getNome() + " " + bibite[i].getPrezzo() + " euro");
                    }
                    do{
                        System.out.println("Scegli che bibita prendere: ");
                        paninoBibita = input.nextInt();
                        if(paninoBibita>bibite.length || paninoBibita<=0){
                            System.out.println("Scelta non valida. Reinserire: ");
                        }
                    }while(paninoBibita>bibite.length || paninoBibita<=0);
                    System.out.println("Quanti? ");
                    quantita = input.nextInt();
                    contBibite += quantita;
                    prezzoTotale += quantita * bibite[paninoBibita-1].getPrezzo();
                    scontrino += bibite[paninoBibita-1].getNome() + " " + " " + quantita + " " + bibite[paninoBibita-1].getPrezzo() + " euro\n";
                    break;
                case 3:
                    System.out.println("Scontrino: \n" + scontrino);
                    if(contPanini>2 && contBibite>1){
                        System.out.println("Prezzo: " + (prezzoTotale * 0.9) + " euro(senza sconto " + prezzoTotale + " euro)");
                    } else {
                        System.out.println("Prezzo: " + prezzoTotale + " euro");
                    }
                    
                    return;
                default:
                    System.out.println("Scelta non valida.");
                    break;
            }
        }while(true);
    }
    public static void main(String[] args) {
        new MainApp();
    }
}
