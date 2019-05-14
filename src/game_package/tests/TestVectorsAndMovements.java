package game_package.tests;

import static org.junit.Assert.*; // кавооо, что за static импорты?

import game_package.util.Vector2f;
import org.junit.*;

public class TestVectorsAndMovements {

    private Vector2f vector;
    private Vector2f another_vector;

    @Before
    public void prepareTests(){
        vector = new Vector2f(2, 2);
        another_vector = new Vector2f(3 , 3);
    }

    @Test
    public void checkOperations(){
        vector.setVector(another_vector);
        assertTrue(vector.x == 3 && vector.y == 3);
        prepareTests();
    }

    @Test
    public void arithmeticOperation(){
        vector.addX(4);
        vector.addY(-1);
        assertTrue(vector.x == 9 && vector.y == 4);
        prepareTests();
    }

    @Test
    public void checkStringConvert(){
        assertTrue(vector.toString().equals("5, 5"));
        prepareTests();
    }


}
