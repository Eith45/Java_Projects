import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 08.04.14
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class MatrixMultiply {
    private int N;
    private int row;
    private int col;
    private int [][] A;
    private int [][] B;
    private int [][] C;
    Scanner scanner;
    File file;

    public MatrixMultiply() throws IOException {
        file = new File("Matrix.txt");
        scanner = new Scanner(new FileReader(file));
        N = scanner.nextInt();
        A = new int[N][N];
        B = new int[N][N];
        C = new int[N][N];
    }

    public void PrintMatrixToFile(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("Non threaded matrix multiplication result:\n");
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    writer.write(C[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void matrixMultiply(){
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    for(int k = 0; k < N; k++){
                        C[row][col] += A[row][k] * B[k][col];
                    }
                }
            }
    }
    public void readAMatrix(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = scanner.nextInt();
            }
        }
    }
    public void readBMatrix(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = scanner.nextInt();
            }
        }
    }

    public static void main(String [] args) throws FileNotFoundException {
        try {
            MatrixMultiply m = new MatrixMultiply();
            m.readAMatrix();
            m.readBMatrix();
            m.matrixMultiply();
            m.PrintMatrixToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
