package manager;

import models.Contact;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
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
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        //read from file and add to list
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Data.csv")));
        String line = reader.readLine(); //qwerty@gmail.com,Ff12345$
        while (line!=null){
            String[] all = line.split(",");// [qwerty@gmail.com] [Ff12345$]
            list.add(new Object[]{new User().withEmail(all[0]).withPassword(all[1])});
            line= reader.readLine();//noa@gmail.com,Nnoa12345$
        }

        return list.iterator();
    }


}
