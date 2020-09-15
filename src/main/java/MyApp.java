import dao.UserDao;
import dao.UserDaoImpl;
import servises.Server;

public class MyApp {
    public static void main(String[] args) {
       //new Server().start();
        UserDao userDao = new UserDaoImpl();
        //System.out.println("userDao.findByName(\"Vladimir\") = " + userDao.findByName("Vladimir"));
        //userDao.createRow("Epifan", "ok");
        //userDao.updateDB("Vova", "Vladimir");
        userDao.deleteRowDB(2);
        userDao.allRowDB();
    }
}
