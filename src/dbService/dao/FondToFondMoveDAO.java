package dbService.dao;

import dbService.dataSets.FondToFondMoveDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by alex on 8.10.17.
 */
public class FondToFondMoveDAO {
    private Executor executor;

    public FondToFondMoveDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void add(int id, int endFondID, long moveDate) throws SQLException {
        Integer startFondId = 0;
        try {
            startFondId = executor.execQuery("select fond from Cards_of_museum_items where inventory_number=" + id, fondResult -> {
                fondResult.next();
                return fondResult.getInt(1);
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if (startFondId.equals(endFondID))
            return;

        executor.execUpdate("insert into Fond_to_fond_move(inventory_number,fond_start,fond_end,date_of_move) values (" + id + "," + startFondId + "," + endFondID + ",'" + new Date(moveDate) + "');");
        executor.execUpdate("update Cards_of_museum_items set fond=" + endFondID + " where inventory_number=" + id);

    }

    public FondToFondMoveDataSet get(int id, long date) throws SQLException {
        return executor.execQuery("select * from Fond_to_fond_move where inventory_number='" + id + "'", result -> {
            Long startDate;
            Long endDate = null;
            do {
                result.next();
                if (endDate == null) {
                    endDate = result.getDate(4).getTime();
                    if (date <= endDate)
                        return new FondToFondMoveDataSet(id, "" + result.getInt(2), "" + result.getInt(3), result.getDate(4));
                } else {
                    startDate = endDate;
                    endDate = result.getDate(4).getTime();
                    if (date <= endDate && date>=startDate)
                        return new FondToFondMoveDataSet(id, "" + result.getInt(2), "" + result.getInt(3), new Date(startDate));

                }


            } while (!result.isLast());


            return null;
        });


    }

}
