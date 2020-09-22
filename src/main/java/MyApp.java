import lombok.SneakyThrows;
import servises.Server;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyApp {
    @SneakyThrows
    public static void main(String[] args) {
       new Server().start();
        // UserDao userDao = new UserDaoImpl();
        //System.out.println("userDao.findByName(\"Vladimir\") = " + userDao.findByName("Vladimir"));
        //userDao.createRow("Epifan", "ok");
        //userDao.updateDB("Vova", "Vladimir");
        //userDao.deleteRowDB(2);
        //userDao.allRowDB();

//        Properties props = new Properties();
//        props.load(MyApp.class.getResourceAsStream("application.properties"));
//        System.out.println("props.getProperty(\"db.url\") = " + Props.getValue("db.url"));

//        Locale local = new Locale("ru", "RU");
//        System.out.println(local.getDisplayCountry());
//        System.out.println("local.getDisplayName() = " + local.getDisplayName());

//        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundle", local);
//        System.out.println("resourceBundle.getString(\"hello\") = " + resourceBundle.getString("hello"));
    }
}
