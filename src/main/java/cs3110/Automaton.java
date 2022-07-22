package cs3110;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/* Generic data structure for finite automaton with data provided from a file
 * Sample text file:
 * 3        number of states // n
 * 0 1      accepting state(s), whitespace deliminated // finalStates
 * 0 1      alphabet, whitespace deliminated // sym
 * (0 0 0)  transitions, new line deliminated // moves
 * (0 1 1)  (presentState nextSymbol nextState)
 * (1 0 0)
 * (1 1 1)
 * 1000     test cases, new line deliminated // tests
 * 10001    */

public class Automaton{
    int n;                              // number of states
    Boolean[] finalStates;              // array of final/accepting states
    ArrayList<Character> sym;           // stores symbols in alphabet
    ArrayList<String[]> moves;          // stores transitions
    ArrayList<String> tests;            // stores tests
    ArrayList<Boolean> results;         // stores results

    public Automaton(String filePath){
        sym = new ArrayList<>();
        moves = new ArrayList<>();
        tests = new ArrayList<>();
        results = new ArrayList<>();

        try{
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            // read number of states
            n = Integer.parseInt(sc.nextLine());
            // initialize accepting states, default to false
            finalStates = new Boolean[n];
            Arrays.fill(finalStates, false);

            // read accepting states, split into array 
            // set corresponding values to true
            String[] acceptStates = sc.nextLine().split("\\s+");
            for(String s : acceptStates){
                finalStates[Integer.parseInt(s)] = true;
            }

            String alphabet = sc.nextLine();
            String[] az = alphabet.split("\\s+");
            // Data validation: symbols should only have length of 1
            for (String s : az){
                if(s.length() > 1){
                    System.out.println("Error: Check alphabet: " + alphabet + "\n");
                    System.exit(1);
                }
                sym.add(s.charAt(0));
            }

            char openPar = '(';
            char closePar = ')';
            while(sc.hasNext()){
                String li = sc.nextLine();
                if(li.charAt(0) == openPar && li.charAt(li.length()-1) == closePar){
                    // transition detected by parentheses
                    // remove parentheses and split into array
                    li = li.substring(1, li.length()-1);
                    String[] tr = li.split("\\s+");
    
                    // input validation
                    if(tr.length != 3){                    
                        // expected: three numbers only
                        System.out.println("Error: Check transition: " + li + "\n");
                        System.exit(1);
                    }
                    if(Integer.parseInt(tr[0]) >= n || Integer.parseInt(tr[0]) < 0 || 
                    Integer.parseInt(tr[2]) >= n || Integer.parseInt(tr[2]) < 0){
                        // states should not be greater than n
                        System.out.println("Error: Transition out of bounds: " + li + "\n");
                        System.exit(1);
                    }
                    moves.add(tr);
                }
                else{
                    // assume test string
                    tests.add(li);
                }
            }
            sc.close();
        }
        catch(Exception e){
            System.out.println("Error reading file. Check filepath or format of contents");
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        String s = "Finite State Automaton\n";
        s += "1) Number of states: " + n + "\n";
        s += "2) Final state(s): " + printFin(finalStates) + "\n";
        s += "3) Alphabet: " + printAlph(sym) + "\n";
        s += "4) Transitions:\n" + printMoves(moves);
        s += "5) Tests: " + tests;
        s += "\nResults: " + printRes(tests, results);
        return s;
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

    public static String printAlph(ArrayList<Character> a){
        String s = "";
        for(char c: a){
            s += c + " ";
        }
        return s;
    }

    public static String printMoves(ArrayList<String[]> tr){
        String s = "";
        for(String[] t : tr){
            s += "\t" + Arrays.toString(t) + "\n";
        }
        return s;
    }

    public static String printRes(ArrayList<String> t, ArrayList<Boolean> r){
        String s = "";
        for(int i=0; i<t.size(); i++){
            s+= t.get(i) + "\t" + r.get(i) + "\n";
        }
        return s;
    }
}
