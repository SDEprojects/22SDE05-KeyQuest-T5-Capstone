import com.game.controller.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void roomSetup_forTestingString_Inventory() {
        String test = "Kitchen: You are now inside the kitchen, here you may find some savory goods" + ".\nItems that can be found in this room: [drumstick, cucumber]" + ".\nYou can go to: [foyer, lounge, loft]";
        assertEquals(test, eHandler.roomSetup_forTestingString("kitchen"));
    }

    @Test
    void roomSetup_forTestingString_noInventory() {
        String test = "Foyer. You find yourself at the foyer, you can examine the cabinet, shelf, or hop towards the kitchen or the lounge. [kitchen, lounge].";
        assertEquals(test, eHandler.roomSetup_forTestingString("foyer"));
    }

    @Test
    void eventRequest() {
    }
}