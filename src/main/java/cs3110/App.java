/* Finite State Automata
 * CS 3110 - Cal Poly Pomona
 * Team Blink - Connor Ramirez & Ero Vedar
 * Summer 2022
 */

package cs3110;
import java.util.ArrayList;
import java.util.Arrays;

public class App 
{
    static String fileSample = "src/main/java/cs3110/test.txt";
    static String LISA = "src/main/java/cs3110/m1.txt";
    static String ROSE = "src/main/java/cs3110/m2.txt";
    static String JENNIE = "src/main/java/cs3110/m3.txt";
    static String JISOO = "src/main/java/cs3110/m4.txt";

    public static void main( String[] args )
    {
        Automaton ex = new Automaton(fileSample);
        runTests(ex);
        System.out.println(ex.toString());
    }

    // iterates through list of tests
    // results of string tests are stored in an arraylist
    public static void runTests(Automaton au){
        for(String s : au.tests){
            if(testString(s, au.finalStates, au.sym, au.moves))
                au.results.add(true);
            else
                au.results.add(false);
        }
    }

    // Determines if string exists in language
    public static Boolean testString(String str, Boolean[] fin, ArrayList<Character> az, ArrayList<String[]> mv){
        int currentState = 0;
        char nextSym;

        System.out.println("STRING: " + str);
        if(str.isEmpty())
            return fin[0];

        for(int i=0; i<str.length(); i++){
            nextSym = str.charAt(i);
            System.out.println("Current state: " + currentState);
            System.out.println("next sym: " + nextSym);
            Boolean found = false;
            for(String[] t : mv){
                // search for matching transition
                if(currentState==Integer.parseInt(t[0])){
                    // search for matching symbol or wildcard
                    if(nextSym==t[1].charAt(0) || (t[1].equals("*") && az.contains(nextSym))){
                        currentState = Integer.parseInt(t[2]);
                        System.out.println("\tMove found:\t" + Arrays.toString(t));
                        found = true;
                        break;
                    }
                }
            }
            if(!found){
                System.out.println("\tNo move available");
                return false;
            }
            System.out.println("\tNew state: " + currentState);
        }
        System.out.println("RESULT: " + fin[currentState] + "\n");
        return fin[currentState];
    }
}
