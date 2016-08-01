package matrix;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 03.02.14
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public class BreakingTheNumbers{


    private static int N;

    public BreakingTheNumbers(int N){
        this.N = N;
        int [][] a = new int[N][N];
        print(coock(a));
    }

    public void print(int [][] a){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int [][] coock(int [][] a){
        int foot = 0;
        int side = N;
        int count = 0;

        while(foot < side/2.0){

            for(int i = foot; i < side - foot; i++){
                a[foot][i] = count;
                count++;
            }

            for(int i = foot; i < side -  foot - 1; i++){
                a[i][side - foot - 1] = count;
                count++;
            }

            for(int i = side-foot-1; i > foot; i--){
                a[side-foot-1][i] = count;
                count++;
            }
            for(int i = side-foot - 1; i > foot; i--){
                a[i][foot] = count;
                count++;
            }
            foot++;
        }

        return a;
    }

    public static void main(String [] args){

        new BreakingTheNumbers(10);

    }
}
