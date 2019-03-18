
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;


public class Member_Home extends JFrame implements ActionListener{
    JFrame home;
    JLabel we,nam,det,name,name1,cont, contact;
    JButton searchmember,editdetails,logout;
    String username, user_password;
    JTextArea mes;
    
    Member_Home(){
    initialize();
    
    }
    
    Member_Home(String n, String y){
    initialize();
    username = n;
    user_password = y;
    DataObject mydataobject = new DataObject();    
//    mydataobject.setQuery("Select * from member_data where username='"+username+"' AND password='"+user_password+"';");
    mydataobject.setQuery("Select * from member_data where username='"+username+"' AND password ='"+user_password+"';");
    mydataobject.setMemberuser(username);
    mydataobject.setMemberpassword(user_password);
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            
            mydataobject = (DataObject)myInputStream.readObject();
            nam.setText(mydataobject.getFname()+" "+mydataobject.getLname());
            name1.setText(mydataobject.getFname()+" "+mydataobject.getLname());
            contact.setText(mydataobject.getContact());
            mes.setText(mydataobject.getMessage());
       
        } catch (IOException ex) {
          
        } catch (ClassNotFoundException ex) {
          
        }
    }
    
    public void initialize(){
    home = new JFrame();
    home.setSize(310, 400);
    home.getContentPane();
    home.setTitle("Homepage");
    home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    home.setLayout(null);
    
    we = new JLabel("Welcome :");
    we.setBounds(80, 20, 60, 20);
    home.add(we);
    
    nam = new JLabel();
    nam.setBounds(150, 20, 100, 20);
    home.add(nam);
    
    det = new JLabel("Current Details");
    det.setBounds(90, 55, 100, 20);
    home.add(det);
    
    JSeparator x = new JSeparator();
    x.setBounds(0, 50, 500, 10);
    home.add(x);
    
    name = new JLabel("Full Name :");
    name.setBounds(50, 90, 70, 20);
    home.add(name);
    
    name1 = new JLabel();
    name1.setBounds(130, 90, 120, 20);
    home.add(name1);
    
    cont = new JLabel("Contact No :");
    cont.setBounds(50, 130, 80, 20);
    home.add(cont);
    
    contact = new JLabel();
    contact.setBounds(130, 130, 80, 20);
    home.add(contact); 
    
    searchmember = new JButton("Search Member");
    searchmember.setBounds(60, 230, 150, 20);
    home.add(searchmember);
    searchmember.addActionListener(this);
    
    editdetails = new JButton("Edit Info");
    editdetails.setBounds(40, 200, 80, 20);
    home.add(editdetails);
    editdetails.addActionListener(this);
    
    logout = new JButton("Logout");
    logout.setBounds(150, 200, 80, 20);
    home.add(logout);
    logout.addActionListener(this);
    
    JLabel mi = new JLabel("Message from admin :");
    mi.setBounds(40, 270, 150, 20);
    home.add(mi);
    
    mes = new JTextArea();
    mes.setBounds(40, 300, 150, 40);
    home.add(mes);
    
    home.setVisible(true);
    }
    
    public static void main(String args[]){
    new Member_Home();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == searchmember){
    new Member_Search(username,user_password);
    home.setVisible(false);
    }
    
    if(e.getSource() == editdetails){
    new Edit_Info(username,user_password);
    home.setVisible(false);
    }
    
    if(e.getSource() == logout){
    new Login();
    home.setVisible(false);
    }
    
    }
    
}
