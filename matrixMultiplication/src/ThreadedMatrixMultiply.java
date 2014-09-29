import java.io.*;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 08.04.14
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class ThreadedMatrixMultiply {
    private int N;
    private int row;
    private int col;
    private int [][] A;
    private int [][] B;
    private int [][] C;
    Scanner scanner;
    File file;
    Thread [] threads;
    private int NumberOfThreads;

    public ThreadedMatrixMultiply() throws FileNotFoundException {
        file = new File("Matrix.txt");
        scanner = new Scanner(new FileReader(file));
        N = scanner.nextInt();
        NumberOfThreads = N*N;
        threads = new Thread[NumberOfThreads];
        A = new int[N][N];
        B = new int[N][N];
        C = new int[N][N];

    }
    public void matrixMultiply(){
        int threadCount = 0;
        try{
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    threads[threadCount] = new Thread(new Worker(row, col, A, B, C));
                    threads[threadCount].start();
                    threads[threadCount].join();
                    threadCount++;
                }
            }
        }catch (InterruptedException i){}
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
    public void PrintMatrixToFile(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("Threaded matrix multiplication result:\n");
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

    public static void main(String [] args){
        ThreadedMatrixMultiply th;
        try {
            th = new ThreadedMatrixMultiply();
            th.readAMatrix();
            th.readBMatrix();
            th.matrixMultiply();
            th.PrintMatrixToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class Worker implements Runnable{
    private int row;
    private int col;
    private int A[][];
    private int B[][];
    private int C[][];

    public Worker(int row, int col, int A[][], int B[][], int C[][] )
    {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }
    public void run() {
        for(int i = 0; i < A.length; i++){
            C[row][col] += A[row][i] * B[i][col];
        }
    }
}
