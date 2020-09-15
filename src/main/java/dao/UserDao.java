package dao;

import model.User;
//Create-Read-Update-Delete
public interface UserDao {
    //вывести login и password по определенному name (Read)
    User findByName(String name);
    //добавить строку в БД (Create)
    void createRow(String name, String password);
    //исправить данные в строке по name (Update)
    void updateDB(String loginOld, String loginNew);
    //удалить строку из БД (Delete)
    void deleteRowDB(int id);
    //Вывести все login и password БД
    void allRowDB();
}
