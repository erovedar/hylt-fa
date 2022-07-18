package cs3110;

import java.util.ArrayList;

public class App 
{
    static String fileSample = "src/main/java/cs3110/test.txt";
    static String fileM1 = "src/main/java/cs3110/m1.txt";
    static String fileM2 = "src/main/java/cs3110/m2.txt";
    static String fileM3 = "src/main/java/cs3110/m3.txt";
    static String fileM4 = "src/main/java/cs3110/m4.txt";

    public static void main( String[] args )
    {
        Automaton ex = new Automaton(fileSample);
        // runTests(ex);
        System.out.println(ex.toString());
    }

    // iterates through list of tests
    public static void runTests(Automaton au){
        for(String s : au.tests){
            if(testString(s, au.finalStates, au.sym, au.moves))
                au.results.add(true);
            else
                au.results.add(false);
        }
    }

    // Determies if string exists in language
    public static Boolean testString(String str, Boolean[] fin, ArrayList<Character> az, ArrayList<String[]> mv){
        String currentState = "0";
        char nextSymbol = str.charAt(0);

        for(int i=0; i<str.length(); i++){

        }
        return true;
    }
}
