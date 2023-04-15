package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preConditions(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("before method finish logout");
        }
    }
    @Test
    public void loginSuccess(){

        logger.info("Start test 'loginSuccess'");
        logger.info("Test data --> email: 'qwerty@mail.com' & password 'Ff12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("qwerty@mail.com","Ff12345$");
        app.getHelperUser().submitLogin();

        //time-->button signOut

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign Out' is present");
    }

    @Test
    public void loginSuccessModel(){
        logger.info("Start test 'loginSuccessModel'");
        logger.info("Test data --> email: 'qwerty@mail.com' & password 'Ff12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("qwerty@mail.com","Ff12345$");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign Out' is present");
    }

    @Test
    public void loginWrongEmail(){
        logger.info("Start test 'loginWrongEmail'");
        logger.info("Test data --> email: 'qwertymail.com' & password 'Ff12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("qwertymail.com","Ff12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert 'Wrong email or password' is present");
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Start test 'loginWrongPassword'");
        logger.info("Test data --> email: 'qwerty@mail.com' & password 'Ff123'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("qwerty@mail.com","Ff123");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert 'Wrong email or password' is present");
    }
    @Test
    public void loginUnregisteredUser(){
        logger.info("Start test 'loginUnregisteredUser'");
        logger.info("Test data --> email: 'pop2@mail.com' & password 'Ff12345$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("pop2@mail.com","Ff12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert 'Wrong email or password' is present");
    }


}
