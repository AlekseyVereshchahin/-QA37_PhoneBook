package tests;

import manager.DataProviderContact;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preConditions(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("qwerty@mail.com").withPassword("Ff12345$"));
        }
    }
    @Test(dataProvider = "contactSuccess",dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFields(Contact contact){
        logger.info("Tests run with data: --->"+contact.toString());
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test(dataProvider = "contactCSV",dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFieldsCSV(Contact contact){
        logger.info("Tests run with data: --->"+contact.toString());
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test(dataProvider = "contactSuccess",dataProviderClass = DataProviderContact.class)
    public void  addContactSuccessRequiredFields(Contact contact){
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test(groups = {"smoke"})
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Parker")
                .address("NY")
                .phone("55328557622222")
                .email("peterParker@gmail.com")
                .description("empty name")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().getScreen("src/test/screenshots/screen.png");
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }
    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Peter")
                .lastName("Parker")
                .address("")
                .phone("55328557622222")
                .email("peterParker@gmail.com")
                .description("empty address")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }
    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Peter")
                .lastName("")
                .address("NY")
                .phone("55328557622222")
                .email("peterParker@gmail.com")
                .description("empty lastname")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());

    }

    @Test(dataProvider="contactWrongPhone",dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact){
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }
    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Peter")
                .lastName("Parker")
                .address("NY")
                .phone("55328557622222")
                .email("peterParkergmail.com")
                .description("wrong email")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();

        Assert.assertTrue(app.helperContact().isAlertPresent2("Email not valid"));
        Assert.assertTrue(app.helperContact().isAddPageStillDisplayed());
    }
}
