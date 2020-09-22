package servises;

import dao.UserDao;
import dao.UserDaoImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@RequiredArgsConstructor//для полей final
public class ClientRunnable implements Runnable, Observer{
    private final Socket socket;
    private final Server server;
    private  final UserDao userDao = new UserDaoImpl();
    private User user;


    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Client connected!");
//        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
//        printWriter.println("Message from server: connected");
//        printWriter.flush(); //скидываем буферизированные данные в поток

        BufferedReader readerFromClient =
                new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientInput;
        while ((clientInput = readerFromClient.readLine()) != null) {
            if (clientInput.startsWith("!@#$autho")) {

                final String[] authorizationStrings = clientInput.substring(9).split(" : ");

                String login = authorizationStrings[0];
                String password = authorizationStrings[1];

                user = authorization(login, password);
                if (user != null) {
                    server.addObserver(this);
                } else {
                    notifyMe("Не правильное имя пользователя или пароль");
                    notifyMe("401");
                    socket.close();
                    break;
                }

            } else if (clientInput.startsWith("!@#$reg")) {
                final String[] checkinStrings = clientInput.substring(7).split(" : ");
                String login = checkinStrings[0];
                String password = checkinStrings[1];
                user = authorization(login, password);
                if (user == null){
                    userDao.createRow(login, password);
                    notifyMe("Новый User успешно зарегистрирован!");
                } else {
                    notifyMe("Пользователь с таким именем и паролем уже зарегистрирован!");
                }

            } else {
                System.out.println(user.getName() + " : " + clientInput);
                server.notifyObserversExceptObserver(user.getName() + ":" + clientInput, this);
            }
        }
    }

    public User authorization(String name, String password){
        User user = userDao.findByName(name);
        if(user != null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }


    @SneakyThrows
    @Override
    public void notifyMe(String message) {
        PrintWriter printWriterToClient = new PrintWriter(socket.getOutputStream());
        printWriterToClient.println(message);
        printWriterToClient.flush();
    }
}
