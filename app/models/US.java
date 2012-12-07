package models;

import play.*;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class US extends Model {
	
    @Id
    @Required
    public Long tweetId;
    
    @Required
    Long id;
    
    @Required
    Date date;
    
    public String geo;
    
    public String city;
    
    public String state;
    
    @Required
    @Lob
    public String tweet;
    
    @Required
    @Lob
    public String twt;
    
}
