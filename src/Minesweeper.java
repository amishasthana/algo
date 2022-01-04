/*
5:47
take n * N square.
Populate random number of mines.<1>
You enter the coordinate, if no mine, expose the surrounding coordinate
If mine end of game.
If all expose then you win.


 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Minesweeper {

    class Cell {
        private final int i;
        private final int j;
        private boolean exposed = false;
        private int value;
        public final boolean isMined;
        public Cell(int x,int y,boolean mined) {
            i = x; j = y;
            isMined = mined;
        }

        public void setExposed() {
            exposed = true;
        }

        public boolean getExposed() {
            return exposed;
        }


    }//End of class

    private Cell[][] mSweep = null;
    private boolean lost = false;
    private int totalExposed = 0;//Total number of open cells so far,
    private int totalBombs = 0;//Total bombs
    private int bombsExposed = 0;//Bombs exposed so far
    private int numberOfChance = 0;//Total number of chance so far
    private int wrongGuess = 0;//Total number of wrong guess so far.

    public void init(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Can not initialize minesweeper with 0 or less rows");
        }

        mSweep = new Cell[n][n];
        for(int i = 0; i < n;i++) {
            for(int j =0; j < n;j++) {
                int rand = ThreadLocalRandom.current().nextInt(0, 10);
                boolean isBomb = false;
                if (rand < 2) {
                    totalBombs++;
                    isBomb = true;
                }
                mSweep[i][j] = new Cell(i,j,isBomb);
            }
        }
    }

    /*
        X --> Hidden
        M --> Exposed and mine
        Number --> Exposed and number of mine.
     */
    public void draw(boolean revealMine) {
        System.out.println("Current State of minesweeper");
        for(int i = 0;i < mSweep.length;i++) {
            for(int j =0; j < mSweep.length;j++) {
                Cell c = mSweep[i][j];
                if (c.exposed) {
                    if(c.isMined) {
                        System.out.print("M");//Mined and exposed.
                    } else {
                        System.out.print(c.value);//Not mined and expose the value.
                    }
                } else {
                    if (revealMine && c.isMined) {
                        System.out.print("M");//Not exposed.
                    } else {
                        System.out.print("X");//Not exposed.
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        printOtherStatus();
    }

    private void printOtherStatus() {
        System.out.println("Other status");
        System.out.println("lost "+lost);
        System.out.println("totalExposed "+totalExposed);
        System.out.println("totalBombs "+totalBombs);
        System.out.println("bombsExposed "+bombsExposed);
        System.out.println("numberOfChance "+numberOfChance);
        System.out.println("wrongGuess "+wrongGuess);
    }

    private boolean notValid(int i) {
          if ((i >= 0) && (i < mSweep.length)) {
                return false;
          }
          return true;
    }

    /*
        i and j are coordinates.
     */
    private void implementAlgo(int i, int j,boolean indicatedBomb) {
               if (notValid(i) || notValid(j)) {
                      System.out.println("Wrong value");
                      return;
               }
               numberOfChance++;
               Cell cCell = mSweep[i][j];
               if (indicatedBomb) {

                  if (cCell.isMined) {
                      bombsExposed++;
                      cCell.exposed = true;
                      return;
                  } else {
                      wrongGuess++;
                  }
               } else {
                   if (cCell.isMined) {
                       lost = true;
                   } else {
                       exposeCell(cCell);
                   }
               }

    }


    private void exposeCell(Cell c) {
        if (c == null) return;
        Deque<Cell>  sCell = new ArrayDeque<>();
        sCell.push(c);
        while(!sCell.isEmpty()) {
                 Cell cCell = sCell.pop();
                 if (!cCell.isMined && !cCell.exposed) {
                     Set<Cell> sChildren = getAllChildren(cCell);
                     int nMines = 0;
                     for(Cell child : sChildren) {
                            if (child.isMined) nMines++;
                            sCell.push(child);
                     }
                     cCell.exposed = true;
                     cCell.value = nMines;
                 }
        }

    }

    private Set<Cell> getAllChildren(Cell c) {
        Set<Cell> setOfChild = new HashSet<>();
        for(int xx= 0; xx < 3;xx++) {
            for(int yy= 0; yy < 3;yy++) {
                if ((xx == 1) && (yy == 1)) continue;
                int x = xx + c.i-1;
                int y = yy + c.j-1;
                if ((x >=0) && (x < mSweep.length) &&
                        (y >=0) && (y < mSweep.length)) {//Valid cell
                    setOfChild.add(mSweep[x][y]);
                }
            }
        }
        return setOfChild;
    }

    public void play(int chance) {
        for (int i = 0; i < chance; i++) {
            if (!lost) {
                int x = ThreadLocalRandom.current().nextInt(0, mSweep.length);
                int y = ThreadLocalRandom.current().nextInt(0, mSweep.length);
                int rand = ThreadLocalRandom.current().nextInt(0, mSweep.length);
                boolean guessMine = (rand < 2);

                if (guessMine) {
                    implementAlgo(x, y, true);
                } else {
                    implementAlgo(x, y, false);
                }
                System.out.println("Chance at "+x+","+y+","+guessMine);
                System.out.println("Did i loose? "+lost);
            } else {
                break;
            }

        }
        if (lost) {
            System.out.println("Lost!!!!");
        }
        System.out.println("At end of chances " + chance + " the board is ");
        if (lost) {
            draw(true);
        } else {
            draw(false);
        }
    }

    public static void main(String[] args) {
        Minesweeper ms = new Minesweeper();
        ms.init(10);
        ms.play(5);

    }


}
