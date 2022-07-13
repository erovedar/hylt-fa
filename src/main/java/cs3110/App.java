package cs3110;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App 
{
    static String filePath = "src/main/java/cs3110/test.txt";

    static int n;   // number of states
    static Boolean[] finalStates;   // array of final/accepting states
    static String[] az; // array of symbols in alphabet
    static ArrayList<Integer[]> trstn = new ArrayList<>();  // list of transitions
    static String[] tests;  // array of tests

    public static void main( String[] args )
    {
        readFile(filePath);
        System.out.println("Finite State Automaton");
        System.out.println("1) Number of states: " + n);
        System.out.println("2) final states: " + printFin(finalStates));
        System.out.println("3) Alphabet: ");

        System.out.println("4) Transitions: ");
        System.out.println("5) Strings: ");
    }

    /* Sample text file:
     * 3        number of states
     * 0 1      accepting state(s), whitespace deliminated
     * 0 1      alphabet, whitespace deliminated
     * (0 0 0)  transitions, new line deliminated
     * (0 1 1)
     * (1 0 0)
     * (1 1 1)
     * 1000     test cases, new line deliminated
     * 10001    */
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
            for (String s : az){
                if(s.length() > 1){
                    System.out.println("Error: alphabet contains multicharacter symbols");
                    System.exit(1);
                }
            }

            char openPar = '(';
            char closePar = ')';
            while(sc.hasNext()){
                String next = sc.nextLine();
                if(next.charAt(0) == openPar && next.charAt(next.length()-1) == closePar){
                    
                }
                else{

                }
            }
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

    public static String printArr(String[] a){
        String s = "";
        
        return s;
    }

    public static String printAZ(ArrayList alph){
        String s = "";
        for(int i = 0; i<alph.size()-1; i++){
            s += alph.get(i) + ", ";
        }
        s += alph.get(alph.size()-1);
        return s;
    }
}
