import com.game.controller.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.game.utility.JSONParser.getIntroductionPlayer;
import static org.junit.jupiter.api.Assertions.*;

class EventHandlerTest {

    //change void to string, add string, under add a statement
    //generate screen- are you sending the right number to be generated
    //add a generic feature

    //test components around it and hav
    //action handler, event handler , event request
    EventHandler eHandler;

    @BeforeEach
    void init(){
        eHandler = new EventHandler();
    }


    @Test
    void roomSetup_string_Inventory() {
        String test = "Kitchen: You are now inside the kitchen, here you may find some savory goods" + ".\nItems that can be found in this room: [drumstick, cucumber]" + ".\nYou can go to: [foyer, lounge, loft]";
        assertEquals(test, eHandler.roomSetup_string("kitchen"));
    }

    @Test
    void roomSetup_string_noInventory() {
        String test = "Foyer. You find yourself at the foyer, you can examine the cabinet, shelf, or hop towards the kitchen or the lounge. [kitchen, lounge].";
        assertEquals(test, eHandler.roomSetup_string("foyer"));
    }

    @Test
    void roomSetup_int_backgroundMatch() {
        assertEquals(2, eHandler.roomSetup_int("kitchen"));
    }

    @Test
    void roomSetup_int_backgroundNoMatch() {
        assertNotEquals(1, eHandler.roomSetup_int("kitchen"));
    }

    @Test
    void eventRequest_testHelp(){
        String help = getIntroductionPlayer() + "\nUse the items you found to distract mean animals, \nfind the key to unlock the garden, \nthen enjoy the carrot.";
        assertEquals(help, eHandler.eventRequest_help("help"));
    }

    @Test
    void eventRequest() {
    }

    @Test
    void winGame() {
    }
}