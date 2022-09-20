import com.gui.GUIClient;
import com.gui.GUI;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

class GUITest extends AssertJSwingTestCaseTemplate {
   //create fixture to handle Frame or Dialog in setUp()
    private FrameFixture window;

    @BeforeEach
    void setUp() { //initializes test fixtures, runs everytime before test method is executed
        GUIClient frame = GuiActionRunner.execute(() -> new GUIClient());
        //window = new FrameFixture(robot(), );
        window.show(); //shows test frame
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @Test
    void createMainField() {

    }

    @Test
    void createBackground() {

    }

    @Test
    void createObject_menu() {

    }

    @Test
    void setMessageText() {

    }
}