package GF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 12.03.14
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
public class Lottery {

    private int [] winningCombination;
    private int [] maches;
    private int redBallCount = 0;
    private int prizes = 0;
    private int count;
    ArrayList<String> names;
    ArrayList<int[]> values;
    ArrayList<int[]> results;

    public Lottery(){
        winningCombination = new int[6];
        maches = new int[6];
        names = new ArrayList<String>();
        values = new ArrayList<int[]>();
        results = new ArrayList<int[]>();
        ballGenerator();
    }

    //Generates winning combination
    private void ballGenerator(){
        Random random = new Random();
        int newBall = random.nextInt(58) + 1;
        for(int i = 0; i < winningCombination.length - 1; i++){
            while (findTheSame(newBall)){
                newBall = random.nextInt(58) + 1;
            }
            winningCombination[i] = newBall;
        }
        winningCombination[5] = random.nextInt(34) + 1;
    }
    //Additional method for ballGenerator. Cheking for the same ball in sequence
    private boolean findTheSame(int numer){
        for(int i = 0; i < winningCombination.length; i++){
            if(winningCombination[i] == numer){
                return true;
            }
        }
        return false;
    }
    //need for parsing numbers
    private boolean isInteger(String s){
        for(int i = 0; i < s.length(); i++){
            if((s.charAt(i) > '0') && (s.charAt(i) <= '9')){
                return true;
            }
        }
        return false;
    }
    //getting data from file
    private void getEnteries() throws IOException {

        File file = new File("gameInput.csv");
        Scanner sc = new Scanner(file);
        String line;
        Scanner lineScanner;
        int i = 0;
        String tempString = "";
        int [] tempInt;

        while(sc.hasNext()){
            i = 0;
            tempString = "";
            tempInt = new int[6];

            line = sc.nextLine();
            lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            while(lineScanner.hasNext()){
                String k = lineScanner.next();
                if(isInteger(k) == false){
                    tempString += k;
                }else{
                    tempInt[i] = Integer.parseInt(k);
                    i = i + 1;
                }
            }
            names.add(tempString);
            values.add(tempInt);
            count++;
        }
    }
    //cheking for white ball maches
    private int whiteMaches(int [] a){
        int result = 0;
        for(int i = 0; i < a.length-1; i++){
            for(int j = 0; j < winningCombination.length - 1; j++){
                if(a[i] == winningCombination[j]){
                    result++;
                }
            }
        }
        return result;
    }
    //cheking for red ball maches
    private int redMaches(int [] a){
        int result = 0;
        if(a[a.length-1] == winningCombination[winningCombination.length-1]){
            result = 1;
        }
        return result;
    }
    //counting player results
    private int countResult(int [] a){
        int sum = 0;

        if(a[0] == 5 && a[1] == 1){
            sum = 20000000;
        }else
        if(a[0] == 5 && a[1] == 0){
            sum = 1000000;
        }else
        if(a[0] == 4 && a[1] == 1){
            sum = 10000;
        }else
        if(a[0] == 4 && a[1] == 0){
            sum = 100;
        }else
        if(a[0] == 3 && a[1] == 1){
            sum = 100;
        }else
        if(a[0] == 3 && a[1] == 0){
            sum = 7;
        }else
        if(a[0] == 2 && a[1] == 1){
            sum = 4;
        }else
        if(a[0] == 1 && a[1] == 1){
            sum = 4;
        }else
        if(a[0] == 0 && a[1] == 1){
            sum = 4;
        }

        for(int j = 5; j >= 0; j--){
            if(a[0] == j){
                maches[j]++;
            }
        }
        if(a[1] == 1){
            redBallCount++;
        }
        return sum;
    }
    //Remember player results
    private void findMaches(){
        int [] temp;
        int i;
        for(int[] l : values){
            i = 0;
            temp = new int[3];
            temp[i++] = whiteMaches(l);
            temp[i++] = redMaches(l);
            temp[i++] = countResult(temp);
            prizes+=temp[2];
            results.add(temp);
        }
    }
    //Printing results to file
    private void PrintResults() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("gameResults.csv"));
        for(int i = 0; i < count; i++){
            writer.write(names.get(i) + " ");
            for(int j = 0; j < results.get(i).length; j++){
                writer.write(results.get(i)[j] + " ");
            }
            writer.write("\n");
        }
        writer.write("\n");
        writer.write("White mathes, people:\n");
        for(int i = 0; i < 6; i++){
            writer.write(i + " " + maches[i] + "\n");
        }
        writer.write("Red match: " + redBallCount);
        writer.write("\nTicket collection: " + count * 2);
        writer.write("\nPrizes: " + prizes);
        writer.write("\nEarnings: " + ((count*2) - prizes));
        writer.close();
    }


    //Prints our winnig combination to console
    public void Print(int [] a){
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String [] args){
        Lottery lottery = new Lottery();
        System.out.println("Winnig combination. 5 white balls and 1 red ball:");
        lottery.Print(lottery.winningCombination);
        try {
            lottery.getEnteries();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lottery.findMaches();
        try {
            lottery.PrintResults();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
