package dbService;

import dbService.dao.ComplexDAO;
import dbService.dao.FondToFondMoveDAO;
import dbService.dao.MuseumItemsDAO;
import dbService.dao.MuseumToOrganizationMoveDAO;
import dbService.dataSets.ComplexDataSet;
import dbService.dataSets.FondToFondMoveDataSet;
import dbService.dataSets.MuseumItemsDataSet;
import dbService.dataSets.MuseumToOrganizationMoveDataSet;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by alex on 17.7.17.
 */
public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMysqlConnection();
    }


    public MuseumItemsDataSet getMuseumItem(int id) throws DBException {
        try {
            return (new MuseumItemsDAO(connection).get(id));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<ComplexDataSet> getByComplex(String name) throws DBException {
        try {
            return (new ComplexDAO(connection).get(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
//
//    public void addUser(String login,String password,String firstName,String lastName) throws DBException {
//        try {
//            connection.setAutoCommit(false);
//            UsersDAO dao = new UsersDAO(connection);
//            dao.createTable();
//            dao.insertUser(login,password,firstName,lastName);
//            new MessegeDAO(connection).createMessagesTable(login);
//            connection.commit();
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ignore) {
//            }
//            throw new DBException(e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException ignore) {
//            }
//        }
//    }
//
//    public void sendMessege(String recipient, String sender, String message) throws DBException {
//        try {
//            connection.setAutoCommit(false);
//            new MessegeDAO(connection).sendMessage(recipient,sender,message);
//            connection.commit();
//
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ignore) {
//            }
//            throw new DBException(e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException ignore) {
//            }
//        }
//    }
//
//    public Map<String, ArrayList<MessegeDataSet>> getMesseges(String login) throws DBException {
//        try {
//            return (new MessegeDAO(connection).getMessages(login));
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }

    @SuppressWarnings("UnusedDeclaration")
    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("lab_2?").          //db name
                    append("user=root&").          //Login
                    append("password=1130324");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMuseumItems(String authorName, String authorSecondName, String authorFatherName, long birthData, String name, String anotation, long createDate, String complex, String fond, String keeperName, String keeperSecondName, String keeperFatherName) throws DBException {
        try {
            connection.setAutoCommit(false);
            new MuseumItemsDAO(connection).add(authorName, authorSecondName,authorFatherName, birthData, name, anotation, createDate, complex, fond, keeperName, keeperSecondName,keeperFatherName);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void updateMuseumItems(int id,String authorName, String authorSecondName, String authorFatherName, long birthData, String name, String anotation, long createDate, String complex, String fond, String keeperName, String keeperSecondName, String keeperFatherName) throws DBException {
        try {
            connection.setAutoCommit(false);
            new MuseumItemsDAO(connection).update(id,authorName, authorSecondName,authorFatherName, birthData, name, anotation, createDate, complex, fond, keeperName, keeperSecondName,keeperFatherName);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void deleteMuseumItems(int id, String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            new MuseumItemsDAO(connection).delete(id,name);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void addFondToFondMove(int id, int endFondID, long moveDate) throws DBException {
        try {
            connection.setAutoCommit(false);
            new FondToFondMoveDAO(connection).add(id,endFondID,moveDate);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public FondToFondMoveDataSet getFondMove(int id,long date) throws DBException {
        try {
            FondToFondMoveDataSet res=new FondToFondMoveDAO(connection).get(id,date);
            if(res==null) throw new DBException(new Throwable());
            return (new FondToFondMoveDAO(connection).get(id,date));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    public int getFondId(String name) throws DBException {
        return (new MuseumItemsDAO(connection).getFondId(name));
    }
    public String getFondName(int id) throws DBException {
        return (new MuseumItemsDAO(connection).getFondName(id));
    }

    public void addOrMove(String exhibitName, String adress, String phoneNumber, String FIO, String exhibitionAdress, String exhibitionName, Date exhibitionStart, Date exhibitionEnd) throws DBException {
        try {
            connection.setAutoCommit(false);
            new MuseumToOrganizationMoveDAO(connection).add(exhibitName, adress,phoneNumber, FIO,exhibitionAdress, exhibitionName, exhibitionStart,exhibitionEnd);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public MuseumToOrganizationMoveDataSet getOrMove(int id, long date){
        try {
            MuseumToOrganizationMoveDataSet res=new MuseumToOrganizationMoveDAO(connection).get(id,date);
            if(res==null) throw new DBException(new Throwable());
            return res;
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
