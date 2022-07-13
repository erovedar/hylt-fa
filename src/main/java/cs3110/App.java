package cs3110;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App 
{
    static String filePath ="";
    static int n;
    static Boolean[] finalStates;
    static String[] az;
    static ArrayList<Integer[]> trstn = new ArrayList<>();
    static String[] tests; 

    public static void main( String[] args )
    {
        readFile(filePath);
        System.out.println("Finite State Automaton");
        System.out.println("1) Number of states: " + n);
        System.out.println("2) final states: " + printFin(finalStates));
        System.out.println("3) Alphabet: ");
    }

    public static void readFile(String filePath){
        try{
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            n = Integer.parseInt(sc.nextLine());
            finalStates = new Boolean[n];
            Arrays.fill(finalStates, false);

            String acceptStates = sc.nextLine();
            String[] asSplit = acceptStates.split("\\s+");
            for(String s : asSplit){
                finalStates[Integer.parseInt(s)] = true;
            }

            String alphabet = sc.nextLine();
            az = alphabet.split("\\s+");

            sc.close();
        }
        catch(Exception e){
            System.out.println("Error reading file. Check filepath or format of contents");
            e.printStackTrace();
        }
    }

    public static String printFin(Boolean[] fin){
        String s = "";
        for(int i=0; i<fin.length; i++){
            if(fin[i]){
                s += i + " ";
            }
        }
        return s;
    }

    public static String printAZ(ArrayList<Character> alph){
        String s = "";
        for(int i = 0; i<alph.size()-1; i++){
            s += alph.get(i) + ", ";
        }
        s += alph.get(alph.size()-1);
        return s;
    }
}
