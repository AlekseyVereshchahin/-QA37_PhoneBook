package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RemoveContactsTests extends TestBase {

    @BeforeClass
    public void preConditions(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("qwerty@mail.com").withPassword("Ff12345$"));
        }
//        app.helperContact().provideContacts();//if list<3 --> add 3 contacts

    }

    @Test
    public void removeFirstContact(){
        //Assert size list less one

    }

    @Test
    public void removeAllContacts(){
        //Assert "No contacts here"
    }
}
