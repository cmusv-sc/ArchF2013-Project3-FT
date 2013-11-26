package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

public class BugReport {

    private static final long serialVersionUID = 1L;

	@Id
    public Long id;
    
    @Constraints.Required
    public String title;

    public String description;
    
    public boolean solved = false;
    
    @Formats.DateTime(pattern="MM/dd/yy")
    public Date creationDate;

    @Formats.DateTime(pattern="MM/dd/yy")
    public Date updateDate;
    
   // @ManyToOne
    //public User owner;
        
    public static List<BugReport> getAll() {
        List<BugReport> reports = new ArrayList<BugReport>();
        for (int i=0; i < 3; i++) {
            BugReport bugReport = new BugReport();
            bugReport.title = "Report #" + i;
            reports.add(bugReport);
        }
                    
        return reports;
    }

    public String toString() {
        return "BugReport #" + id;
    }

}