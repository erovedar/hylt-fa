package cs3110;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void checkFormat()
    {
        System.out.println("*".toLowerCase());
        System.out.println("A".compareTo("a")); 
        assertTrue( true );
    }

    @Test 
    public void dnr(){
        String alph = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";
        String[] spl = alph.split(alph);
        for(String s : spl){
            System.out.println(s);
        }
    }
}
