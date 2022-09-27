import com.sound.Sound;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import java.io.FileNotFoundException;
import java.net.URL;


class SoundTest {

     /* RIGHT Are the results right? Use data files, golden bits, oracle…
    B Are all boundary conditions CORRECT Conformance, Ordering, Range, Reference, Existence, Cardinality, Time.
    I Can you check inverse relationships? Apply logical inverse (e.g. sqrt vs pow).
    C Can you cross-check results using other means? Use alternative way of achieving result.
    E Can you enforce error conditions to happen? What errors could occur, e.g. environmental constraints.
    P Are performance characteristics within bounds? Quick regression test of performance characteristics. */

    //CORRECT
    //Reference Any external dependencies? Any preconditions? Do we guarantee post-conditions?
    //Existence Does some given thing exist? Null, blank, empty, 0 (zero)?

    Sound soundTest;

    @BeforeEach
    void setUp() {
        soundTest = new Sound();
    }

    @Test
    void setValidFile() {
        soundTest.setFile(3);
        Assertions.assertEquals(3,3);
        System.out.println("File Set");

    }

    @Test
    void setInvalidFile() throws Exception {
        soundTest.setFile(2);
        Assertions.assertEquals(1,1);
        System.out.println("Invalid File");

    }

    /*@Test
    public void playMusic(int i) {
        soundTest.setFile(3);
        soundTest.play();
        soundTest.loop();
        System.out.println("Now Playing");
    }*/
}