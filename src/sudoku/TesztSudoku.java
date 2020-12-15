package sudoku;

import static sudoku.BepitettSudoku.MegoldandoSudoku;

public class TesztSudoku {
    public static void main(String[] args) {
        BepitettSudoku proba = new BepitettSudoku(MegoldandoSudoku);
        
        proba.Megjelenit();

        if (proba.Algoritmus()) {
            System.out.println("Sikerült megoldnai");
            proba.Megjelenit();
        } else {
            System.out.println("Nem lehet megoldani");
        }
    }

}
