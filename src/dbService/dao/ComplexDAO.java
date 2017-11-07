package dbService.dao;

import dbService.dataSets.AuthorDataSet;
import dbService.dataSets.ComplexDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4.10.17.
 */
public class ComplexDAO {
    private Executor executor;

    public ComplexDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public List<ComplexDataSet> get(String name) throws SQLException {

        String complex = executor.execQuery("select * from Complex ", complexResult -> {

            do {
                complexResult.next();
                if(complexResult.getString(2).equals(name))
                    return complexResult.getString(1);
            } while (!complexResult.isLast());
            return null;
        });
        return executor.execQuery("SELECT Cards_of_museum_items.author,Cards_of_museum_items.anotation,Cards_of_museum_items.name,Cards_of_museum_items.date_create,Complex.complex_name from Cards_of_museum_items JOIN Author ON  Cards_of_museum_items.author=Author.author JOIN Complex ON  Cards_of_museum_items.complex=Complex.complex  WHERE Complex.complex=" + complex , result -> {


            List<ComplexDataSet> complexList= new ArrayList<ComplexDataSet>();
            do{
            result.next();
            AuthorDataSet author = executor.execQuery("select * from Author where author='" + result.getInt(1) + "'", authorResult -> {
                authorResult.next();
                return new AuthorDataSet(authorResult.getInt(1), authorResult.getString(2), authorResult.getString(3), authorResult.getString(4), authorResult.getDate(5));
            });
            complexList.add(new ComplexDataSet(author,result.getString(2), result.getString(3), result.getDate(4), result.getString(5)));
            }while(!result.isLast());

            return complexList;
        });
    }
}
