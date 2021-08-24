import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;



public class Terminal {
    String[][] tabuleiro;
    int row;
    int column;
    LinkedList<String[]> set;
    //String[] casos = {"caso100.txt","caso150.txt","caso200.txt","caso250.txt","caso300.txt","caso350.txt","caso400.txt","caso450.txt","caso500.txt","caso550.txt"};

    public Terminal() {
        String[] aux;
                set = new LinkedList<>();
                
                //for(int i = 0; i <= casos.length ; ++i) {
                    row = 0;
                    column = 0;
                    Path path1 = Paths.get("caso550.txt");
                    try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            aux = line.split("");
                            set.add(aux);
                        }
                        
                    } catch (IOException e) {
                        System.err.format("Erro na leitura do arquivo: ", e);        
                    }
                    System.out.print(path1.toString() + ": ");
                    setTabuleiro();
                    setGame();
                //}
    }


    public void setGame(){

        //busca pela casa contendo "C"
        int[] knight = {};
        for (int i = 0; i <row; ++i) {
            for(int j = 0; j < column; ++j) {
                if (tabuleiro[i][j].equals("C")) {
                    knight = new int[] {i, j};
                }
            }
        }
        Game g = new Game(tabuleiro, knight);
    }

    public void setTabuleiro(){
        //set tamanho do tabuleiro
        row = set.size();
        column = set.get(0).length;
        tabuleiro = new String[row][column];

        //preenche o tabuleiro
        int j = 0;
        for(String[] s : set) {
            tabuleiro[j] = s;
            ++j;
        }
    }
}
