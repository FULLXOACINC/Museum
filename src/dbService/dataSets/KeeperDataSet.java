package dbService.dataSets;

import java.sql.Date;

/**
 * Created by alex on 24.9.17.
 */
public class KeeperDataSet {
    int id;
    String name;
    String secondName;
    String fatherName;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFatherName() {
        return fatherName;
    }


    public KeeperDataSet(int id, String name, String secondName, String fatherName) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.fatherName = fatherName;
    }
    @Override
    public String toString() {
        return   name + " "+ secondName + " " + fatherName ;
    }
}
