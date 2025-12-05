package doc;

public class Sorting {
    public static void bubbleSort(Articolo[] da_ordinare){
        Articolo temp;
        for (int i = 0; i < da_ordinare.length-1; i++) {
            for (int j = 0; j < da_ordinare.length-1; j++) {
                if(da_ordinare[j].getPrezzo() < da_ordinare[j+1].getPrezzo()){
                    temp = da_ordinare[j];
                    da_ordinare[j] = da_ordinare[j+1];
                    da_ordinare[j+1] = temp;
                }
            }
        }
    }
}
