/* Finite State Automata
 * CS 3110 - Cal Poly Pomona
 * Team Blink - Conner Ramirez & Ero Vedar
 * Summer 2022
 */

package cs3110;
import java.util.ArrayList;

public class App 
{
    //static String fileSample = "src/main/java/cs3110/test.txt";
    static String LISA = "src/main/java/cs3110/m1.txt";
    static String ROSE = "src/main/java/cs3110/m2.txt";
    static String JENNIE = "src/main/java/cs3110/m3.txt";
    static String JISOO = "src/main/java/cs3110/m4.txt";

    public static void main( String[] args )
    {
        Automaton m1 = new Automaton(LISA);
        ArrayList<Boolean> m1tests = runTests(m1);
        System.out.println("\nFSA M1: LISA\n" + m1.toString());
        System.out.println(printRes(m1.tests, m1tests));

        Automaton m2 = new Automaton(ROSE);
        ArrayList<Boolean> m2tests = runTests(m2);
        System.out.println("FSA M2: ROSE\n" + m2.toString());
        System.out.println(printRes(m2.tests, m2tests));

        Automaton m3 = new Automaton(JENNIE);
        ArrayList<Boolean> m3tests =runTests(m3);
        System.out.println("FSA M3: JENNIE\n" + m3.toString());
        System.out.println(printRes(m3.tests, m3tests));

        Automaton m4 = new Automaton(JISOO);
        ArrayList<Boolean> m4tests = runTests(m4);
        System.out.println("FSA M4: JISOO\n" + m4.toString());
        System.out.println(printRes(m4.tests, m4tests));
    }

    // iterates through list of tests
    // results of string tests are stored in an arraylist
    public static ArrayList<Boolean> runTests(Automaton au){
        ArrayList<Boolean> res = new ArrayList<>();
        for(String s : au.tests){
            if(testString(s, au.finalStates, au.sym, au.moves))
                res.add(true);
            else
                res.add(false);
        }
        return res;
    }

    // Determines if string exists in language
    /* str - given string
     * fin - array of accepting states
     * az - alphabet of symbols in language
     * mv - set of available transitions (moves)
     */
    public static Boolean testString(String str, Boolean[] fin, ArrayList<Character> az, ArrayList<String[]> mv){
        int currentState = 0;
        char nextSym;

        //System.out.println("STRING: " + str);
        if(str.isEmpty())
            return fin[0];

        for(int i=0; i<str.length(); i++){
            nextSym = str.charAt(i);
            // System.out.println("Current state: " + currentState);
            // System.out.println("next sym: " + nextSym);
            Boolean found = false;
            for(String[] t : mv){
                // search for matching transition
                if(currentState==Integer.parseInt(t[0])){
                    // search for matching symbol or wildcard
                    if(nextSym==t[1].charAt(0) || 
                        (t[1].equals("Σ") && az.contains(nextSym))){
                        currentState = Integer.parseInt(t[2]);
                        //System.out.println("\tMove found:\t" + Arrays.toString(t));
                        found = true;
                        break;
                    }
                }
            }
            if(!found){
                // System.out.println("\tNo move available");
                // no transition found, reject string
                return false;
            }
            //System.out.println("\Next state: " + currentState);
        }
        // System.out.println("RESULT: " + fin[currentState] + "\n");
        // automaton has reached the end of the string
        // checks if final state is an accepting state
        return fin[currentState];
    }

    public static String printRes(ArrayList<String> t, ArrayList<Boolean> r){
        String s = "";
        for(int i=0; i<t.size(); i++){
            if(t.get(i).equals(" ")){
                s += "Λ";
            }
            else{
                s += t.get(i);
            }
            s+= "\t" + r.get(i) + "\n";
        }
        return s;
    }
}
