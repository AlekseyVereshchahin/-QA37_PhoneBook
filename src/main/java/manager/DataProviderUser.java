package manager;

import models.Contact;
import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"qwerty@mail.com","Ff12345$"});
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});
        list.add(new Object[]{"sonya@gmail.com","Ss12345$"});
        return list.iterator();
    }

//    @DataProvider
//    public Iterator<Object[]> example(){
//        List<Object[]> list = new ArrayList<>();
//
//        return list.iterator();
//    }

    @DataProvider
    public Iterator<Object[]> loginModels(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("qwerty@mail.com").withPassword("Ff12345$")});
        list.add(new Object[]{new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$")});
        list.add(new Object[]{new User().withEmail("sonya@gmail.com").withPassword("Ss12345$")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                Contact.builder()
                        .name("Peter")
                        .lastName("Parker")
                        .address("NY")
                        .phone("553285576222")
                        .email("peterParker@gmail.com")
                        .description("all field")
                        .build()});
        list.add(new Object[]{
                Contact.builder()
                        .name("PeterRequa")
                        .lastName("Parker")
                        .address("NY")
                        .phone("553285333576")
                        .email("peterParkerReq@gmail.com")
                        .build()});

        return list.iterator();
    }



}
