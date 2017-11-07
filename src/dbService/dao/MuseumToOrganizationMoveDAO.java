package dbService.dao;

import dbService.dataSets.MuseumToOrganizationMoveDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by alex on 9.10.17.
 */
public class MuseumToOrganizationMoveDAO {
    private Executor executor;

    public MuseumToOrganizationMoveDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void add(String exhibitName, String adress, String phoneNumber, String FIO, String exhibitionAdress, String exhibitionName, Date exhibitionStart, Date exhibitionEnd) throws SQLException {
        Integer id = executor.execQuery("select inventory_number from Cards_of_museum_items where name='" + exhibitName+"';", fondResult -> {
            fondResult.next();
            return fondResult.getInt(1);
        });
        System.out.println("insert into Museum_to_Organization_move(inventory_number,name,adress ,tel_number,FIO,exhibition_adress ,exhibition_name, exhibition_start, exhibition_end) values (" + id + ",'" + exhibitName +"','" + adress + "','" + phoneNumber +"','" + FIO +"','" + exhibitionAdress +"','" + exhibitionName +"','" + exhibitionStart +"','" + exhibitionEnd + "');");
        executor.execUpdate("insert into Museum_to_Organization_move(inventory_number,name,adress ,tel_number,FIO,exhibition_adress ,exhibition_name, exhibition_start, exhibition_end) values (" + id + ",'" + exhibitName +"','" + adress + "','" + phoneNumber +"','" + FIO +"','" + exhibitionAdress +"','" + exhibitionName +"','" + exhibitionStart +"','" + exhibitionEnd + "');");

    }

    public MuseumToOrganizationMoveDataSet get(int id, long date) throws SQLException {
        return executor.execQuery("select * from Museum_to_Organization_move where inventory_number='" + id + "'", result -> {

            do {
                result.next();
                if (date <= result.getDate(9).getTime() && date>=result.getDate(8).getTime())
                    return new MuseumToOrganizationMoveDataSet(id, result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getDate(8),result.getDate(9));


            } while (!result.isLast());


            return null;
        });


    }
}
