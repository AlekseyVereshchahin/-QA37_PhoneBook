package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class LoginTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preConditions(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("before method finish logout");
        }
    }
    @Test(dataProvider = "loginData",dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){

        logger.info("Start test 'loginSuccess'");
        logger.info("Test data --> email: "+email+" & password: "+password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password);
        app.getHelperUser().submitLogin();

        //time-->button signOut

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign Out' is present");
    }


    @Test(dataProvider = "loginModels",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){
        logger.info("Start test 'loginSuccessModel'");
        logger.info("Test data --> "+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign Out' is present");
    }

    @Test(dataProvider = "loginFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel_DP(User user){
        logger.info("Start test 'loginSuccessModel'");
        logger.info("Test data --> "+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign Out' is present");
    }

    @Test(groups = "smoke")
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
