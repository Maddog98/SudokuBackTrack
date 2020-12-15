package sudoku;

public class BepitettSudoku {

    public static int[][] MegoldandoSudoku = {
        {9, 0, 0, 1, 0, 0, 0, 0, 5},
        {0, 0, 5, 0, 9, 0, 2, 0, 1},
        {8, 0, 0, 0, 4, 0, 0, 0, 0},
        {0, 0, 0, 0, 8, 0, 0, 0, 0},
        {0, 0, 0, 7, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 2, 6, 0, 0, 9},
        {2, 0, 0, 3, 0, 0, 0, 0, 6},
        {0, 0, 0, 2, 0, 0, 9, 0, 0},
        {0, 0, 1, 9, 0, 4, 5, 7, 0},
    };

    private int[][] tabla;
    public int ures_cellak = 0; // üres cellák
    public int racs_meret = 9; // a játék rácsának a mérete

    public BepitettSudoku(int[][] tabla) {
        this.tabla = new int[racs_meret][racs_meret];

        for (int i = 0; i < racs_meret; i++) {
            for (int j = 0; j < racs_meret; j++) {
                this.tabla[i][j] = tabla[i][j];
            }
        }
    }

    //3 db korlátozás beépítése ,hogy érvényes szabályok alapján mennyen a játék
    //az azott sorban van e már lehetséges érték:
    private boolean sorbanSzamok_vizs(int sor, int szam) {
        for (int i = 0; i < racs_meret; i++) {
            if (tabla[sor][i] == szam) {
                return true;
            }
        }
        return false;
    }

    //a rácsot alkotó cellákban van e szám 1-9-ig
    //az azott oszlopban van e már lehetséges érték:
    private boolean oszlopSzamok_vizs(int oszlop, int szam) {
        for (int i = 0; i < racs_meret; i++) {
            if (tabla[i][oszlop] == szam) {
                return true;
            }
        }
        return false;
    }

    //a kisebb 3x3-mas rácsokban megvannak e számok 1-9-ig
    // 3*3 rács ellenőrzése:
    private boolean piciRacs_vizs(int sor, int oszlop, int szam) {
        int a = sor - sor % 3;
        int b = oszlop - oszlop % 3;

        for (int i = a; i < a + 3; i++) {
            for (int j = b; j < b + 3; j++) {
                if (tabla[i][j] == szam) {
                    return true;
                }
            }
        }
        return false;
    }

    // sorok és oszlopob ellenorzése ,hogy jók e:
    // összerakott ellenörzés:
    private boolean Teljes_vizs(int sor, int oszlop, int szam) {
        return !sorbanSzamok_vizs(sor, szam) && !oszlopSzamok_vizs(oszlop, szam) && !piciRacs_vizs(sor, oszlop, szam);
    }

    //BackTrack Algoritmos implementálása:
    public boolean Algoritmus() {
        for (int sor = 0; sor < racs_meret; sor++) {
            for (int oszlop = 0; oszlop < racs_meret; oszlop++) {
                // üres cellák keresése:
                if (tabla[sor][oszlop] == ures_cellak) {
                    // we try possible numbers
                    for (int number = 1; number <= racs_meret; number++) {
                        if (Teljes_vizs(sor, oszlop, number)) {
                            // number ok. it respects sudoku constraints
                            tabla[sor][oszlop] = number;

                            //visszalépés
                            if (Algoritmus()) {
                                return true;
                            } else { //ha nem jó szám van ott akkor ures cellává alakul és majd új szám fog bekerülni
                                tabla[sor][oszlop] = ures_cellak;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void Megjelenit() {
        for (int i = 0; i < racs_meret; i++) {
            for (int j = 0; j < racs_meret; j++) {
                System.out.print(" " + tabla[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
