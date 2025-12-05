import java.util.ArrayList;
import java.util.Scanner;

public class BiglietteriaApp {
    private final int dim = 10;
    ArrayList<Biglietto> biglietti = new ArrayList<>(dim);
    Scanner input = new Scanner(System.in);
    double prezzoTotale;
    int contVip = 0;
    String sceltaString;
    public BiglietteriaApp(){
        for (int i = 0; i < dim; i++) {
            if(i<4)
                biglietti.add(new Biglietto((i + 1), "Settore A", 20.0));
            else if(i<7)
                biglietti.add(new Biglietto((i + 1), "Settore B", 30.0));
            else
                biglietti.add(new Biglietto((i + 1), "Settore VIP", 80.0));
        }
        do{
            System.out.println("1. Visualizza posti disponibili \n2. Compra biglietto \n3. Report incassi \n4. Cerca per settore\n5. Cerca per numero\n6. Esci");
            int scelta = input.nextInt();
            input.nextLine();
            switch (scelta) {
                case 1:
                    mostraBiglietti();
                    break;
                case 2:
                    vendiBiglietto();
                    break;
                case 3:
                    reportIncassi();
                    break;
                case 4:
                    cercaPerSettore();
                    break;
                case 5:
                    cercaPerNumero();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(true);
    }
    public void mostraBiglietti(){
        System.out.println("Posti disponibili:");
        for (int i = 0; i < biglietti.size(); i++) {
            if(biglietti.get(i).getIsVenduto() == false){
                System.out.println("Posto: " + biglietti.get(i).getNumeroPosto() + ", Settore: " + biglietti.get(i).getSettore() + ", Prezzo: " + biglietti.get(i).getPrezzoBase() + " euro");
            }
        }
    }
    public void vendiBiglietto(){
        int posto, scelta = 0;
        do{
            System.out.println("Inserisci il numero del posto da comprare: ");
            posto = input.nextInt();
            input.nextLine();
            posto -= 1;
            String messErrore = (posto<0 || posto>dim)? "Scelta non valida." : biglietti.get(posto).getIsVenduto()? "Biglietto già comprato" : "";
            if(!messErrore.isEmpty()){
                System.out.println("Errore: " + messErrore);
            }
            do{
                prezzoTotale += biglietti.get(posto).getPrezzoBase();
                biglietti.get(posto).setIsVenduto(true);
                contVip += biglietti.get(posto).getSettore().equals("Settore VIP") ? 1 : 0;
                System.out.println("Vuoi acquistarne un altro[1]SI[0]NO? ");
                scelta = input.nextInt();
                input.nextLine();
                if(scelta !=1 && scelta!=0){
                    System.out.println("Scelta non valida.");
                }
            }while(scelta != 1 && scelta!=0);
            if(scelta == 0){
                break;
            }
        }while((posto < 0 || posto > dim) || biglietti.get(posto).getIsVenduto() == true || scelta != 0);
        System.out.println("Vendita completata! Prezzo: [" + biglietti.get(posto).getPrezzoBase() + "] euro");
        
    }
    public void reportIncassi(){
        if(contVip>1){
            System.out.println("Prezzo totale LORDO: " + prezzoTotale*0.9 + " euro");
            System.out.println("Prezzo totale NETTO: " + prezzoTotale + " euro");
            return;
        }
        System.out.println("Prezzo totale: " + prezzoTotale + " euro");
    }
    public void cercaPerSettore(){
        boolean trovato = false;
        int contVenduti = 0;
        do{
            System.out.println("Inserisci nome del settore: ");
            sceltaString = input.nextLine();
            if(!sceltaString.equals("Settore A") && !sceltaString.equals("Settore B") && !sceltaString.equals("Settore VIP")){
                System.out.println("Settore non esistente!");
                continue;
            }
            for (int i = 0; i < biglietti.size(); i++) {
                if(biglietti.get(i).getSettore().equals(sceltaString) && biglietti.get(i).getIsVenduto()) {
                    contVenduti++;
                }
            }
        }while(!sceltaString.equals("Settore A") && !sceltaString.equals("Settore B") && !sceltaString.equals("Settore VIP"));
        System.out.println("Posti venduti: " + contVenduti + "\nPosti disponibili: " + (biglietti.size()-contVenduti));
    }
    public void cercaPerNumero(){
        int numero;
        do{
            System.out.println("Inserisci il numero da cercare: ");
            numero = input.nextInt();
            input.nextLine();
            numero -= 1;
            if(numero < 0 || numero > dim){
                System.out.println("Numero posto non esistente.");
                continue;
            }
        }while(numero < 0 || numero > dim);
        String stringa = biglietti.get(numero).getIsVenduto()? " e' stato venduto al prezzo di " + biglietti.get(numero).getPrezzoBase() + " euro" : " non e' stato venduto";
        System.out.println("Il posto numero " + (numero+1) + stringa);
    }
    public static void main(String[] args) throws Exception {
        new BiglietteriaApp();
    }
}