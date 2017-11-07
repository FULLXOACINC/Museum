package dbService.dataSets;

import java.sql.Date;

/**
 * Created by alex on 4.10.17.
 */
public class ComplexDataSet {
    private AuthorDataSet author;
    private String anotation;
    private String name;
    private String complex;
    private Date createDate;

    public ComplexDataSet(AuthorDataSet author, String anotation, String name, Date createDate, String complex) {
        this.author = author;
        this.anotation = anotation;
        this.name = name;
        this.complex = complex;
        this.createDate = createDate;
    }

    public AuthorDataSet getAuthor() {
        return author;
    }

    public String getAnotation() {
        return anotation;
    }

    public String getName() {
        return name;
    }

    public String getComplex() {
        return complex;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "\n author=" + author +
                "\n anotation='" + anotation + '\'' +
                "\n name='" + name + '\'' +
                "\n complex='" + complex + '\'' +
                "\n createDate=" + createDate +
                "\n";
    }
}
