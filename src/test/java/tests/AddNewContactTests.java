package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass
    public void preConditions(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("qwerty@mail.com").withPassword("Ff12345$"));
        }
    }
    @Test
    public void addContactSuccessAllFields(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Peter"+i)
                .lastName("Parker")
                .address("NY")
                .phone("553285576"+i)
                .email("peterParker"+i+"@gmail.com")
                .description("all field")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);

        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void  addContactSuccessRequiredFields(){
        int i= new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("PeterRequa"+i)
                .lastName("Parker")
                .address("NY")
                .phone("553285576"+i)
                .email("peterParker"+i+"@gmail.com")
                .build();
        app.helperContact().openContactForm();
        app.helperContact().fillContactForm(contact);
        app.helperContact().saveContact();
        Assert.assertTrue(app.helperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.helperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
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

    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("Peter")
                .lastName("Parker")
                .address("NY")
                .phone("")
                .email("peterParker@gmail.com")
                .description("wrong phone")
                .build();
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
