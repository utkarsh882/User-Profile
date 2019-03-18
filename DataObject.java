
import java.io.Serializable;

public class DataObject implements Serializable{

	protected String Adminuser, Adminpassword,Memberuser, Memberpassword, Contact, Address;
        protected String Query,Fname,Lname,Message;
        protected int Flag;

	DataObject(){
		Adminuser = "";
                Adminpassword = "";
	}

	public String getAdminuser(){
		return Adminuser;
	}

	public void setAdminuser(String inAdminuser){
		Adminuser = inAdminuser;
	}
        public String getAdminpassword(){
            return Adminpassword;
        }
        
        public void setAdminpassword(String inAdminpassword){
                Adminpassword = inAdminpassword;
        }
        
        public String getMemberuser(){
                return Memberuser;
        }
        
        public void setMemberuser(String inMemberuser){
                Memberuser = inMemberuser;
        }
        
        public String getMemberpassword(){
                return Memberpassword;
        }
        
        public void setMemberpassword(String inMemberpassword){
                Memberpassword = inMemberpassword;
        }
        
        public String getContact(){
                return Contact;
        }
        
        public void setContact(String inContact){
                Contact = inContact;
        }
        
        public String getAddress(){
                return Address;
        }
        
        public void setAddress(String inAddress){
                Address = inAddress;
        }
        
        public String getQuery(){
                return Query;
        }
        
        public void setQuery(String inQuery){
                Query = inQuery;
        }
        
        public String getFname(){
                return Fname;
        }
        
        public void setFname(String inFname){
                Fname = inFname;
        }
        
        public String getLname(){
                return Lname;
        }
        
        public void setLname(String inLname){
                Lname = inLname;
        }
        
        public int getFlag(){
                return Flag;
        }
        
        public void setFlag(int inFlag){
                Flag = inFlag;
        }
        
        public String getMessage(){
                return Message;
        }
        
        public void setMessage(String inMessage){
                Message = inMessage;
        }
        
}
