package dbService.dataSets;

import java.sql.Date;

/**
 * Created by alex on 24.9.17.
 */
public class AuthorDataSet {
    int id;
    String name;
    String secondName;
    String fatherName;
    Date birthDate;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public AuthorDataSet(int id, String name, String secondName, String fatherName, Date birthDate) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return   name + " "+ secondName + " " + fatherName ;
    }
}
