package dbService.dataSets;

import java.sql.Date;

/**
 * Created by alex on 24.9.17.
 */
public class MuseumItemsDataSet {
    private int id;
    private AuthorDataSet author;
    private String anotation;
    private String name;
    private Date createDate;
    private String complex;
    private String fond;
    private KeeperDataSet keeper;

    public MuseumItemsDataSet(int id,String name, AuthorDataSet author, String anotation, Date createDate, String complex, String fond, KeeperDataSet keeper) {
        this.id = id;
        this.author = author;
        this.anotation = anotation;
        this.createDate = createDate;
        this.name = name;
        this.complex = complex;
        this.fond = fond;
        this.keeper = keeper;
    }

    public int getId() {
        return id;
    }

    public AuthorDataSet getAuthor() {
        return author;
    }

    public String getAnotation() {
        return anotation;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public String getComplex() {
        return complex;
    }

    public String getFond() {
        return fond;
    }

    public KeeperDataSet getKeeper() {
        return keeper;
    }

    @Override
    public String toString() {
        return
                "\n id=" + id +
                "\n author=" + author +
                "\n anotation='" + anotation + '\'' +
                "\n name='" + name + '\'' +
                "\n createDate=" + createDate +
                "\n complex='" + complex + '\'' +
                "\n fond='" + fond + '\'' +
                "\n keeper=" + keeper +
                '\n';
    }
}
