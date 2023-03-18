package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class LoginTests extends TestBase{
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("qwerty@mail.com","Ff12345$");
        app.getHelperUser().submitLogin();

        //time-->button signOut

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("qwerty@mail.com","Ff12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }


}
