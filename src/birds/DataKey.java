package birds;

public class DataKey {

    private String birdName;
    private int birdSize;
	// default constructor
	public DataKey() {
	 
	}
        
	// other constructors
        public DataKey(String bname, int bsize){
        birdName=bname;
        birdSize=bsize;
        }
        
        
	/**
	 * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
	 * than k, and it returns 1 otherwise. 
	 */
public int compareTo(DataKey k) {
            if(birdSize == k.getbirdSize() && birdName.toLowerCase().equals(k.getbirdName().toLowerCase()))
                return 0;
            else if(birdSize < k.getbirdSize() || (birdSize == k.getbirdSize() && birdName/*.toLowerCase()*/.compareTo(k.getbirdName()/*.toLowerCase()*/) < 0))
                return -1;
            else
                return 1;
            
	}
        
        public String getbirdName(){
        return birdName;
        }
        
        public int getbirdSize(){
        return birdSize;
        }
        
}
