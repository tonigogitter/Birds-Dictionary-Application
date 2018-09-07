package birds;

/**
 * This class represents a bird record in the database. Each record consists of two
 * parts: a DataKey and the data associated with the DataKey.
 */
public class BirdRecord {

private DataKey datakey1;
    
private String about;
private String sound;
private String image;

 

    // default constructor
    public BirdRecord() {
        
    }

     // Other constructors
    public BirdRecord(DataKey datakey2,String about2, String sound2, String image2){
        datakey1= datakey2;
        about=about2;
        sound=sound2;
        image=image2;
    }
    public String getAbout(){
    return about;
    }
    
    public String getSound(){
    return sound;
    }
    
    public String getImage(){
    return image;
    }
    
    public DataKey getDataKey(){
    return datakey1;
    }
}
