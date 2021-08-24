import java.util.LinkedList;
import java.util.Queue;

public class Game {
    
    String[][] tabuleiro;
    int[][] visited;
    int[] knight; //posição inicial


    //movement vectors
    //movement n        : 1, 2, 3, 4, 5, 6, 7, 8      
    static int[] mRow = {-2,-1,+1,+2,+2,+1,-1,-2};
    static int[] mCol = {+1,+2,+2,+1,-1,-2,-2,-1};
   
    //contador sepa rola ein
    LinkedList<String[]> data;
   
    //queue interface 
    Queue<String> queue = new LinkedList<>();
    
    //counter queue interface
    Queue<Integer> counter = new LinkedList<>();

    public Game(String[][] tabuleiro, int[] knight) {
        this.tabuleiro = tabuleiro;
        visited = new int[tabuleiro.length][tabuleiro[0].length];
        this.knight = knight; 
        data = new LinkedList<>();
        startGame();
    }

    //Breadth-First Search 
    public void startGame() {
        queue.add(knight[0] + "," + knight[1]);
        counter.add(0);
        while (!queue.isEmpty()) {
            String pop = queue.remove(); //takes next element
            Integer popdepth = counter.remove();

            //atribui e converte se necessario o valor da coluna e da linha que o cavalo ira/esta (se no começo do jogo).
            int newRow = Integer.parseInt(pop.split(",")[0]);
            int newCol = Integer.parseInt(pop.split(",")[1]);


            //check boundaries
            newRow = checkRowBoundaries(newRow);
            newCol = checkColBoundaries(newCol);            
            //check if tile has been visited before
            if (!checkVisited(newRow, newCol)) 
                continue;
            //check if tile is prohibited
            if (!isProhibited(newRow, newCol)) 
                continue;
            //check if found exit
            if (victory(newRow, newCol)) {
                System.out.println("O cavaleiro chegou no campo de saida S [ linha ][ coluna ] -> " + 
                tabuleiro[newRow][newCol] + " [ " + newRow + " ] [ " + newCol + " ]" +
                "em " + counter.remove() + " movimentos!");
                break;
            }

            //add next jumps to the queue
            queue.add( ( newRow + mRow[0] ) + "," + ( newCol + mCol[0] ) ); //movimento 1
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[1] ) + "," + ( newCol + mCol[1] ) ); //          2
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[2] ) + "," + ( newCol + mCol[2] ) ); //          3
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[3] ) + "," + ( newCol + mCol[3] ) ); //          4
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[4] ) + "," + ( newCol + mCol[4] ) ); //          5
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[5] ) + "," + ( newCol + mCol[5] ) ); //          6
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[6] ) + "," + ( newCol + mCol[6] ) ); //          7
            counter.add(popdepth+1);
            queue.add( ( newRow + mRow[7] ) + "," + ( newCol + mCol[7] ) ); //          8
            counter.add(popdepth+1);

        }
    }
    
    //check if won
    public boolean victory(int newRow, int newCol) {
        if (tabuleiro[newRow][newCol].equals("S")) {
            return true;
        }
        return false;
    }
    //check if tile is marked with an x 
    public boolean isProhibited(int newRow, int newCol) {
        if (tabuleiro[newRow][newCol].equals("x")) {
            return false;
        }
        return true; 
    }
    
    //marca na cola de visitados
    public boolean checkVisited(int newRow, int newCol) {
        if(visited[newRow][newCol] == 1) {
            return false;
        }
        visited[newRow][newCol] = 1;
        return true ;
    }

 //check if movement exceeds board boundaries
    public int checkRowBoundaries(int newRow) {
        if (newRow < 0) { //newRol esta acima do tabuleiro
            newRow = newRow + tabuleiro.length;
            return newRow;
        } 
        else if (newRow > tabuleiro.length - 1) { // newRow esta abaixo do tabuleiro
            newRow = newRow - tabuleiro.length;
        return newRow;    
        }
        return newRow;
    }

    //check if movement exceeds board boundaries
    public int checkColBoundaries(int newCol) {
        if (newCol < 0) { //newCol esta à esquerda do tabuleiro
            newCol = newCol + tabuleiro[0].length;
        }
        else if(newCol > tabuleiro[0].length - 1) { //newCol esta à direita do tabuleiro
            newCol = newCol - tabuleiro[0].length;
        }
        return newCol;
    }

}
