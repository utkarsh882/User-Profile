
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Server {

    public static void main(String args[]) throws IOException{
    Socket incoming;
    ServerSocket myServerSocket = new ServerSocket(33007);
    for(;;){
			incoming = myServerSocket.accept();
			
      new ThreadedDataObjectHandler(incoming).start();
    }
    }  
}

class ThreadedDataObjectHandler extends Thread{
  DataObject myObject = null;
  Socket incoming;
  ObjectOutputStream myOutputStream;
  ObjectInputStream myInputStream;
  int execute=0;
  public ThreadedDataObjectHandler(Socket i) 
    { 
		  incoming = i;
    }
    public void run(){
    System.out.println("New user");
      String url = "sql2.njit.edu";
      String ucid = "umc3";
      String dbPass = "ewPXO8uqo";
      Connection connection = null;
      Statement statement = null;
      ResultSet rs = null;

    try{

      Class.forName("com.mysql.jdbc.Driver").newInstance();
      connection = DriverManager.getConnection("jdbc:mysql://"+url+"/"+ucid+"?user="+ucid+"&password="+dbPass);
      if(connection!=null)
      {
         System.out.println("Connection successful");
      }
      statement = connection.createStatement();

			//ObjectOutputStream 
      myOutputStream = new ObjectOutputStream(incoming.getOutputStream());

			//ObjectInputStream 
      myInputStream =  new ObjectInputStream(incoming.getInputStream());

			myObject = (DataObject)myInputStream.readObject();
      }catch(Exception e){System.out.println(e);}
      try{
      if((myObject.getQuery()).contains("Update") || (myObject.getQuery()).contains("insert") || (myObject.getQuery()).contains("Delete"))
      {
          System.out.println("inside update");
      System.out.println("Query is "+myObject.getQuery());
      int i = statement.executeUpdate(myObject.getQuery());
      System.out.println(i+" rows updated");
      execute=1;
      }
      else{
      execute = 0;
      String str = myObject.getQuery();
      System.out.println(str);
      
      }
    }catch(Exception e){System.out.println("In update catch");e.printStackTrace();}

try{
    if(execute ==0){
        rs = statement.executeQuery(myObject.getQuery());
        if(myObject.getFlag() == 1){
        
        
      
           rs.next();
               
               
               if(rs.getString("username").equals(myObject.getMemberuser())){    
               if((myObject.getQuery()).contains("member_data")){
      myObject.setFlag(0);
      myObject.setMemberuser(rs.getString("username"));
      myObject.setMemberpassword(rs.getString("password"));
      myObject.setFname(rs.getString("first_name"));
      myObject.setLname(rs.getString("last_name"));
      myObject.setContact(rs.getString("contact_no"));
      myObject.setAddress(rs.getString("address"));
      myObject.setMessage(rs.getString("message"));
      System.out.println(rs.getString("last_name"));
     }
      if((myObject.getQuery()).contains("admin")){
      myObject.setAdminuser(rs.getString("username"));    
      myObject.setAdminpassword(rs.getString("password"));
      }
               }}
               
        else{
            
                    rs.next();
        if((myObject.getQuery()).contains("member_data")){             
                     if((rs.getString("username").equals(myObject.getMemberuser())) && (rs.getString("password").equals(myObject.getMemberpassword()))){
                System.out.println("DAta");
                
//      if((myObject.getQuery()).contains("member_data")){
      myObject.setFlag(0);
      myObject.setMemberuser(rs.getString("username"));
      myObject.setMemberpassword(rs.getString("password"));
      myObject.setFname(rs.getString("first_name"));
      myObject.setLname(rs.getString("last_name"));
      myObject.setContact(rs.getString("contact_no"));
      myObject.setAddress(rs.getString("address"));
      myObject.setMessage(rs.getString("message"));
      System.out.println(rs.getString("last_name"));
     }
      
               }
        
//       if((myObject.getQuery()).contains("admin")){
//      myObject.setAdminuser(rs.getString("username"));    
//      myObject.setAdminpassword(rs.getString("password"));
//      }
                     else{
               myObject.setFlag(1);
               }
                      
                       
               
       
           }
        
//    }
        
      
     		rs.close();
myOutputStream.writeObject(myObject); 
        
       
}


System.out.println("Message sending");



      
			myOutputStream.close();
			
			myInputStream.close();

			//myServerSocket.close();
      incoming.close();

	
		}catch(Exception e){
   System.out.println("In last catch");
			System.out.println(e);
		}
	}
 }