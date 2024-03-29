package it.polimi.ingsw.ViewTest;

import it.polimi.ingsw.View.User.ClientExceptionHandler;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientExceptionHandlerTest {
    private ClientExceptionHandler gui = new ClientExceptionHandler();
    public ClientExceptionHandlerTest() {
        gui.visualType(true);
    }

    /**
     * Testing the address validator for the connection
     */
    @Test
    public void addressValidatorTest() throws Exception{
        assertTrue(gui.addressValidator("192.168.1.2"));
        assertTrue(gui.addressValidator("localhost"));
        assertTrue(gui.addressValidator("www.polimi.it"));
        assertTrue(gui.addressValidator("http://www.example.com"));
        assertTrue(gui.addressValidator("https://test.example30.pizza"));
        assertTrue(gui.addressValidator("nic.arpa"));
        assertFalse(gui.addressValidator("999.999.999.999"));
        //assertFalse(gui.addressValidator("giovanni"));
        assertFalse(gui.addressValidator("193.344.cinquecento.39"));
        assertFalse(gui.addressValidator(""));
    }

    /**
     * Testing the port validator for the connection
     */
    @Test
    public void portValidatorTest() throws Exception{
        assertFalse(gui.portValidator(-239));
        assertFalse(gui.portValidator(0));
        assertFalse(gui.portValidator(80));
        assertFalse(gui.portValidator(6000000));
        assertTrue(gui.portValidator(8765));
    }

    /**
     * Testing the name validator for chosing a username in the game
     */
    @Test
    public void nameValidatorTest() throws Exception{
        assertTrue(gui.nameValidator("Giovanni"));
        assertFalse(gui.nameValidator("       "));
        assertFalse(gui.nameValidator(""));
        assertFalse(gui.nameValidator("stringwithmorethan16char"));
    }

}
