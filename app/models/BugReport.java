package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;
import java.sql.*;
import play.db.*;
import play.db.jpa.*;

public class BugReport {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;
    
    @Constraints.Required
    public String title;

    public String description;
    
    public boolean solved = false;
    
    @Formats.DateTime(pattern="MM/dd/yy")
    public java.sql.Date creationDate;

    @Formats.DateTime(pattern="MM/dd/yy")
    public java.sql.Date updateDate;
    
   // @ManyToOne
    //public User owner;
    @play.db.jpa.Transactional
    public static Collection getAll() {
        Connection connection = DB.getConnection();
        //This should clearly not be here, but for the moment i'll leave it
        //we need to learn how H2 works, it's an in-memory db so i think there's two instances running
        try{
            Query query = JPA.em().createNativeQuery("CREATE TABLE BUG_REPORT ( title VARCHAR(255) )");
            query.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Didn't create table");
        }

        Query query2 = JPA.em().createNativeQuery("SELECT * FROM BUG_REPORT");
        Collection collection = query2.getResultList();
        return collection;
    }

    @play.db.jpa.Transactional
    public boolean save(){
        Connection connection = DB.getConnection();
        //"INSERT INTO BUG_REPORT VALUES ("Name")";
        //Query query = JPA.em().createNativeQuery("INSERT INTO BUG_REPORT VALUES (" + this.title + ")" );
        //query.executeUpdate();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO BUG_REPORT (title) VALUES ('" + this.title + "')");
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        return true;
    }

    public String toString() {
        return "BugReport #" + id;
    }
    
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

}