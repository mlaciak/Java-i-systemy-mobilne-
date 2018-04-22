import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {
        String expectedOutput = "bu"+System.lineSeparator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.k();
        assertEquals(expectedOutput, outContent.toString());
    }
}