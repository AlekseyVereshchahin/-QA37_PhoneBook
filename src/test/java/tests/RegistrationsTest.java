package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationsTest extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000); //give random number
        int z = (int) (System.currentTimeMillis() / 1000) / 3600; //give random number

        User user = new User().withEmail("pop" + i + "@gmail.com").withPassword("Pop12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isContactHereDisplayed());

    }

    @Test
    public void registrationWrongEmail() {
       User user = new User().withEmail("popgmail.com").withPassword("Pop12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationWrongPassword() {

        User user = new User().withEmail("pop@gmail.com").withPassword("pop12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }

    @Test
    public void registrationRegisteredUser() {

        User user = new User().withEmail("qwerty@gmail.com").withPassword("Ff12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }


}
