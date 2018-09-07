/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

/**
 *
 * @author Administrator
 */
public class OrderedDictionary implements OrderedDictionaryADT {
    
    public Node root=null;
    
//    public OrderedDictionary(){
//		this.root = null;
//	}
    
    
    class Node{
	BirdRecord data;
	Node left;
	Node right;	
	public Node(BirdRecord data1){
		data = data1;
		left = null;
		right = null;
	}
}
 
    
 
    
   /* Returns the Record object with key k, or It throws a DictionaryException
       says: "There is no record matches the given key", if such a record
       is not in the dictionary. 

       @param k
       @return BirdRecord
       @throws DictionaryException
     */
    @Override
    public BirdRecord find(DataKey k) throws DictionaryException 
    {
        Node current = root;
		while(current!=null){
			if(current.data.getDataKey().compareTo(k)==0){
				return current.data;
			}else if(current.data.getDataKey().compareTo(k)==1){
				current = current.left;
			}else{
				current = current.right;
			}
                }      
         throw new DictionaryException("There is no record matches the given key");
        
    }
    
    public Node findHelper2(DataKey k) throws DictionaryException 
    {
        Node current = root;
		while(current!=null){
			if(current.data.getDataKey()==k){
				return current;
			}else if(current.data.getDataKey().compareTo(k)==1){
				current = current.left;
			}else{
				current = current.right;
			}
                }      
         throw new DictionaryException("There is no record matches the given key");
        
    }
    
    
    /* Inserts r into the ordered dictionary. It throws a DictionaryException 
       if a record with the same key as r is already in the dictionary.  

       @param r
       @throws DictionaryException
     */
    @Override
    public void insert(BirdRecord r) throws DictionaryException{
    Node newNode = new Node(r);
		if(root==null){
			root = newNode;
			return;
		}
                else {
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
                         
			if(r.getDataKey().compareTo(current.data.getDataKey())==-1){	//, id<current.data, then return is
                            //-1
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}
                        
                        else{
                            if(r.getDataKey().compareTo(current.data.getDataKey())==0){
                         throw new DictionaryException(" There exists a record with the same key as r is already in the dictionary.");
                            }
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
                        
		}
        }
    }// throws DictionaryException;

    /*  Removes the record with Key k from the dictionary. It throws a 
        DictionaryException says: "No such record key exists", if the record
        is not in the dictionary. 
             
       @param k
       @throws DictionaryException
     */
    @Override
    public void remove(DataKey k) throws DictionaryException
    {
        if(isEmpty())
            throw new DictionaryException ("Dicitionary is Empty!");
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while(!(current.data.getDataKey().compareTo(k)==0)){
			parent = current;
			if(current.data.getDataKey().compareTo(k)==1){
				isLeftChild = true;
				current = current.left;
			}
			else{
				isLeftChild = false;
				current = current.right;
			}
                        if(current ==null){
				 throw new DictionaryException(" No such record key exists to remove.");
                        }
		}
		//if i am here that means we have found the node
		//Case 1: if node to be deleted has no children
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}
			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}
		}
		//Case 2 : if node to be deleted has only one child
		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}
		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}
		}else if(current.left!=null && current.right!=null){
			
			//now we have found the minimum element in the right sub tree
			Node successor	 = successorHelper2(current.data.getDataKey());
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}				
    
    }//throws DictionaryException;

    /* Returns the successor of k (the record from the ordered dictionary 
       with smallest key larger than k); It throws a DictionaryException says:
       "There is no successor for the given record key", if the given key has 
       no successor. The given key DOES NOT need to be in the dictionary. 
         
       @param k
       @return BirdRecord
       @throws DictionaryException
     */
    
    public boolean findHelper3(DataKey k) //throws DictionaryException 
    {
        Node current = root;
		while(current!=null){
			if(current.data.getDataKey().compareTo(k)==0){
				return true;
			}else if(current.data.getDataKey().compareTo(k)==1){
				current = current.left;
			}else{
				current = current.right;
			}
                }
                
                return false;
        // throw new DictionaryException("There is no record matches the given key");
        
    }
    
    
    @Override
    public BirdRecord successor(DataKey k) throws DictionaryException {
          Node temp = root;
        BirdRecord successor = null;
        while(temp !=null){
            if(temp.data.getDataKey().compareTo(k)==0 || temp.data.getDataKey().compareTo(k)==-1){
                temp = temp.right;
            }
            else if(temp.data.getDataKey().compareTo(k)==1){
                successor = temp.data;
                temp = temp.left;
            }
            
        }
        if(successor == null || successor.getDataKey().compareTo(k) == -1 ||successor.getDataKey().compareTo(k) == 0)
           throw new DictionaryException("There is no successor for the given record key");
        return successor;
    
    }
    
    
    public Boolean successorHelper3(DataKey k){
          Node temp = root;
        BirdRecord successor = null;
        while(temp !=null){
            if(temp.data.getDataKey().compareTo(k)==0 || temp.data.getDataKey().compareTo(k)==-1){
                temp = temp.right;
            }
            else if(temp.data.getDataKey().compareTo(k)==1){
                successor = temp.data;
                temp = temp.left;
            }
            
        }
        if(successor == null || successor.getDataKey().compareTo(k) == -1 ||successor.getDataKey().compareTo(k) == 0){
        return false;
        }
        return true;
    
    }
    
   
    public Node successorHelper2(DataKey k) throws DictionaryException{
    
    Node deleleNode=findHelper2(k);
    if (deleleNode.right==null)
        throw new DictionaryException("There is no successor for the given record key");
    
    Node successsor =null;
		Node successsorParent =null;
		Node current = deleleNode.right;
		while(current!=null){
			successsorParent = successsor;
			successsor = current;
			current = current.left;
		}
		//check if successor has the right child, it cannot have left child for sure
		// if it does have the right child, add it to the left of successorParent.
//		successsorParent
		if(successsor!=deleleNode.right){
			successsorParent.left = successsor.right;
			successsor.right = deleleNode.right;
		}
		return successsor;
    
    }
    

    /* Returns the predecessor of k (the record from the ordered dictionary 
       with largest key smaller than k; It throws a DictionaryException says:
       "There is no predecessor for the given record key", if the given key has 
       no predecessor. The given key DOES NOT need to be in the dictionary.  
     
       @param k
       @return BirdRecord
       @throws DictionaryException
     */
    
//    Node deleleNode=findHelper2(k);
//    if (deleleNode.left==null)
//        throw new DictionaryException(" There is no predecessor for the given record key");
//    
//    Node successsor =null;
//		Node successsorParent =null;
//		Node current = deleleNode.left;
//		while(current!=null){
//			successsorParent = successsor;
//			successsor = current;
//			current = current.right;
//		}
//		//check if successor has the right child, it cannot have left child for sure
//		// if it does have the right child, add it to the left of successorParent.
////		successsorParent
//		if(successsor!=deleleNode.left){
//			successsorParent.left = successsor.left;
//			successsor.left = deleleNode.left;
//		}
//		return successsor.data;
    @Override
    public BirdRecord predecessor(DataKey k) throws DictionaryException
    {
        Node temp = root;
        BirdRecord predecessor = null;
        while(temp !=null){
            if(temp.data.getDataKey().compareTo(k)==0 || temp.data.getDataKey().compareTo(k)==1){
                temp = temp.left;
            }
            else if(temp.data.getDataKey().compareTo(k)==-1){
                predecessor = temp.data;
                temp = temp.right;
            }
            
        }
        if(predecessor == null || predecessor.getDataKey().compareTo(k) == 1 ||predecessor.getDataKey().compareTo(k) == 0)
           throw new DictionaryException("There is no predecessor for the given record key");
        return predecessor;
    
    }
    
    
    public Boolean predecessorHelper2(DataKey k) throws DictionaryException
    {
        Node temp = root;
        BirdRecord predecessor = null;
        while(temp !=null){
            if(temp.data.getDataKey().compareTo(k)==0 || temp.data.getDataKey().compareTo(k)==1){
                temp = temp.left;
            }
            else if(temp.data.getDataKey().compareTo(k)==-1){
                predecessor = temp.data;
                temp = temp.right;
            }
            
        }
        if(predecessor == null || predecessor.getDataKey().compareTo(k) == 1 ||predecessor.getDataKey().compareTo(k) == 0)
           return false;
        return true;
    
    }
    

    /* Returns the record with smallest key in the ordered dictionary. 
       It throws a DictionaryException says:"Dictionary is empty", if the 
       dictionary is empty.   

       @return BirdRecord
       @throws DictionaryException
     */
    @Override
    public BirdRecord smallest() throws DictionaryException{
    Node current= root;
        
        while(current.left!=null){
                current= current.left;
		}
    if (isEmpty()==true)
        throw new DictionaryException(" Dictionary is empty");
    
    return current.data;
    
    }

    /* Returns the record with largest key in the ordered dictionary. 
       It throws a DictionaryException says:"Dictionary is empty", if the 
       dictionary is empty.  
       @return BirdRecord
       @throws DictionaryException
     */
    @Override
    public BirdRecord largest() throws DictionaryException{
     Node current= root;
        
        while(current.right!=null){
                current= current.right;
		}
    if (isEmpty()==true)
        throw new DictionaryException(" Dictionary is empty");
    
    return current.data;
    
    
    
    }

    /* Returns true if the dictionary is empty, and true otherwise. 

       @return boolean
     */
    @Override
    public boolean isEmpty(){
    if (root==null)
    return true;
    
    else 
        return false;
}
    
    public void display(Node root){
		if(root!=null){
			display(root.left);
			System.out.print(" " + root.data.getDataKey().getbirdName()+ " "+root.data.getDataKey().getbirdSize());
			display(root.right);
		}
	}
}
