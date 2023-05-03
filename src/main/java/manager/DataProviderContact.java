package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public static Iterator<Object[]> contactSuccess(){
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
    @DataProvider
    public static Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("555")
                .email("peterParkerReq@gmail.com")
                .description("rruygfs")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("555325314534623636")
                .email("peterParkerReq@gmail.com")
                .description("rruygfs")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("adsfer333333")
                .email("peterParkerReq@gmail.com")
                .description("rruygfs")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("")
                .email("peterParkerReq@gmail.com")
                .description("rruygfs")
                .build()});


        return list.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/Contact.csv"));
        String line = reader.readLine();
        while (line!=null){
            String []all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line= reader.readLine();
        }
        return list.iterator();
    }


}
