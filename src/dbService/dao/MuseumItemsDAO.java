package dbService.dao;

import dbService.dataSets.AuthorDataSet;
import dbService.dataSets.KeeperDataSet;
import dbService.dataSets.MuseumItemsDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by alex on 24.9.17.
 */
public class MuseumItemsDAO {
    private Executor executor;

    public MuseumItemsDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public MuseumItemsDataSet get(int id) throws SQLException {
        return executor.execQuery("select * from Cards_of_museum_items where inventory_number='" + id+"'", result -> {
            result.next();
            AuthorDataSet author= executor.execQuery("select * from Author where author='"+result.getInt(3)+"'",authorResult -> {
                authorResult.next();
                return new AuthorDataSet(authorResult.getInt(1),authorResult.getString(2),authorResult.getString(3),authorResult.getString(4),authorResult.getDate(5));
            });

            KeeperDataSet keeper= executor.execQuery("select * from Keeper where keeper='"+result.getInt(8)+"'", keeperResult -> {
                keeperResult.next();
                return new KeeperDataSet(keeperResult.getInt(1),keeperResult.getString(2),keeperResult.getString(3),keeperResult.getString(4));
            });
            String fond= executor.execQuery("select * from Fond where fond='"+result.getInt(7)+"'", fondResult -> {
                fondResult.next();
                return fondResult.getString(2);
            });
            String complex= executor.execQuery("select * from Complex where complex='"+result.getInt(6)+"'", complexResult -> {
                complexResult.next();
                return complexResult.getString(2);
            });

            return new MuseumItemsDataSet(result.getInt(1), result.getString(2),author, result.getString(4), result.getDate(5), complex, fond ,keeper);
        });
    }

    public void add(String authorName, String authorSecondName, String authorFatherName, long birthData, String name, String anotation, long createDate, String complex, String fond, String keeperName, String keeperSecondName, String keeperFatherName) throws SQLException {
        AuthorDataSet author = null;
        try {
            author = executor.execQuery("select * from Author where second_name='" + authorSecondName + "'", authorResult -> {
                authorResult.next();
                return new AuthorDataSet(authorResult.getInt(1), authorResult.getString(2), authorResult.getString(3), authorResult.getString(4), authorResult.getDate(5));
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if (author == null)
            try {
                executor.execUpdate("insert into Author (name,second_name,father_name,birth_data) values ('" + authorName + "','" + authorSecondName + "','" + authorFatherName + "','" + new Date(birthData) + "');");
                author = executor.execQuery("select * from Author where second_name='" + authorSecondName + "'", authorResult -> {
                    authorResult.next();
                    return new AuthorDataSet(authorResult.getInt(1), authorResult.getString(2), authorResult.getString(3), authorResult.getString(4), authorResult.getDate(5));
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }

        Integer compl = null;
        try {
            compl = executor.execQuery("select * from Complex where complex_name='" + complex + "'", complexResult -> {
                complexResult.next();
                return complexResult.getInt(1);
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if (compl == null)
            try {
                executor.execUpdate("insert into Complex (complex_name) values ('" + complex + "');");
                compl = executor.execQuery("select * from Complex where complex_name='" + complex + "'", complexResult -> {
                    complexResult.next();
                    return complexResult.getInt(1);
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        Integer fondId = null;
        try {
            fondId = executor.execQuery("select * from Fond where fond_name='" + fond + "'", fondResult -> {
                fondResult.next();
                return fondResult.getInt(1);
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if (fondId == null)
            try {
                executor.execUpdate("insert into Fond (fond_name) values ('" + fond + "');");
                fondId = executor.execQuery("select * from Fond where fond_name='" + fond + "'", fondResult -> {
                    fondResult.next();
                    return fondResult.getInt(1);
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }

        KeeperDataSet keeper = null;
        try {
            keeper = executor.execQuery("select * from Keeper where second_name='" + keeperSecondName + "'", keeperResult -> {
                keeperResult.next();
                return new KeeperDataSet(keeperResult.getInt(1), keeperResult.getString(2), keeperResult.getString(3), keeperResult.getString(4));
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if (keeper == null)
            try {
                executor.execUpdate("insert into Keeper (name,second_name,father_name) values ('" + keeperName + "','" + keeperSecondName + "','" + keeperFatherName + "');");
                keeper = executor.execQuery("select * from Keeper where second_name='" + keeperSecondName + "'", keeperResult -> {
                    keeperResult.next();
                    return new KeeperDataSet(keeperResult.getInt(1), keeperResult.getString(2), keeperResult.getString(3), keeperResult.getString(4));
                });
            } catch (SQLException e) {
                e.printStackTrace();

                executor.execUpdate("insert into Cards_of_museum_items(author,name,anotation,date_create,complex,fond,keeper) values (" + author.getId() + ",'" + name + "','" + anotation + "','" + new Date(createDate) + "'," + author.getId() + "," + fondId + "," + keeper.getId() + ");");


            }
    }
    public void update(int id,String authorName, String authorSecondName, String authorFatherName, long birthData, String name, String anotation, long createDate, String complex, String fond, String keeperName, String keeperSecondName, String keeperFatherName) throws SQLException {
        AuthorDataSet author=null;
        try {
            author= executor.execQuery("select * from Author where second_name='"+authorSecondName+"'",authorResult -> {
                authorResult.next();
                return new AuthorDataSet(authorResult.getInt(1),authorResult.getString(2),authorResult.getString(3),authorResult.getString(4),authorResult.getDate(5));
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if(author==null)
            try {
                executor.execUpdate("insert into Author (name,second_name,father_name,birth_data) values ('"+authorName+"','"+authorSecondName+"','"+authorFatherName+"','"+new Date(birthData)+"');");
                author= executor.execQuery("select * from Author where second_name='"+authorSecondName+"'",authorResult -> {
                    authorResult.next();
                    return new AuthorDataSet(authorResult.getInt(1),authorResult.getString(2),authorResult.getString(3),authorResult.getString(4),authorResult.getDate(5));
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }

        Integer compl=null;
        try {
            compl= executor.execQuery("select * from Complex where complex_name='"+complex+"'",complexResult -> {
                complexResult.next();
                return complexResult.getInt(1);
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if(compl==null)
            try {
                executor.execUpdate("insert into Complex (complex_name) values ('"+complex+"');");
                compl= executor.execQuery("select * from Complex where complex_name='"+complex+"'",complexResult -> {
                    complexResult.next();
                    return complexResult.getInt(1);
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        Integer fondId=null;
        try {
            fondId= executor.execQuery("select * from Fond where fond_name='"+fond+"'",fondResult -> {
                fondResult.next();
                return fondResult.getInt(1);
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if(fondId==null)
            try {
                executor.execUpdate("insert into Fond (fond_name) values ('"+fond+"');");
                fondId= executor.execQuery("select * from Fond where fond_name='"+fond+"'",fondResult -> {
                    fondResult.next();
                    return fondResult.getInt(1);
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }

        KeeperDataSet keeper=null;
        try {
            keeper= executor.execQuery("select * from Keeper where second_name='"+keeperSecondName+"'",keeperResult -> {
                keeperResult.next();
                return new KeeperDataSet(keeperResult.getInt(1),keeperResult.getString(2),keeperResult.getString(3),keeperResult.getString(4));
            });
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        if(keeper==null)
            try {
                executor.execUpdate("insert into Keeper (name,second_name,father_name) values ('"+keeperName+"','"+keeperSecondName+"','"+keeperFatherName+"');");
                keeper= executor.execQuery("select * from Keeper where second_name='"+keeperSecondName+"'",keeperResult -> {
                    keeperResult.next();
                    return new KeeperDataSet(keeperResult.getInt(1),keeperResult.getString(2),keeperResult.getString(3),keeperResult.getString(4));
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        System.out.println(author.getId());
//        System.out.println(compl);
//        System.out.println(fondId);
//        System.out.println(keeper.getId());
//        System.out.println("insert into Cards_of_museum_items(author,name,anotation,date_create,complex,fond,keeper) values ("+author.getId()+",'"+name+"','"+anotation+"','"+new Date(createDate)+"',"+author.getId()+","+fondId+","+keeper.getId()+");");
        executor.execUpdate("update Cards_of_museum_items set author="+author.getId()+",name='"+name+"',anotation='"+anotation+"',date_create='"+new Date(createDate)+"',fond="+fondId+",complex="+compl+",keeper="+keeper.getId()+" where inventory_number="+id);



    }

    public void delete(int id,String name) throws SQLException {
        executor.execQuery("select * from Cards_of_museum_items where inventory_number='" + id+"' and name='" + name+"';", result -> {
            result.next();
            return result.getString(2);
        });
        executor.execUpdate("delete from Cards_of_museum_items where inventory_number="+id+" and name='"+name+"';");



    }

    public int getFondId(String name){
        try {
            return executor.execQuery("select * from Fond where fond_name='"+name+"'",result -> {
                result.next();
                return result.getInt(1);
            });
        } catch (SQLException e) {
            return -1;
        }
    }
    public String getFondName(int id){
        try {
            return executor.execQuery("select * from Fond where fond='"+id+"'",result -> {
                result.next();
                return result.getString(2);
            });
        } catch (SQLException e) {
            return null;
        }
    }
}
