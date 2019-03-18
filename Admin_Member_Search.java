
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Admin_Member_Search extends JFrame implements ActionListener{
    JFrame adminsearch;
    JLabel id, fn,ln,addr,con;
    JTextField userid,first,last,contact;
    JTextArea address;
    JButton search,clear,back,delete,m;

    Admin_Member_Search(){
    initialize();
    }
    
    public void initialize(){
    adminsearch = new JFrame();
    adminsearch.setSize(350, 450);
    adminsearch.getContentPane();
    adminsearch.setLayout(null);
    adminsearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    id = new JLabel("User id :");
    id.setBounds(50, 50, 80, 20);
    adminsearch.add(id);

    userid = new JTextField();
    userid.setBounds(150, 50, 100, 20);
    adminsearch.add(userid);


    fn = new JLabel("First name :");
    fn.setBounds(50, 90, 80, 20);
    adminsearch.add(fn);

    first = new JTextField();
    first.setBounds(150, 90, 100, 20);
    adminsearch.add(first);
    first.setEditable(false);

    ln = new JLabel("Last name :");
    ln.setBounds(50, 130, 80, 20);
    adminsearch.add(ln);

    last = new JTextField();
    last.setBounds(150, 130, 100, 20);
    adminsearch.add(last);
    last.setEditable(false);

    addr = new JLabel("Address :");
    addr.setBounds(50, 170, 80, 20);
    adminsearch.add(addr);

    address = new JTextArea();
    address.setBounds(150, 170, 100, 60);
    adminsearch.add(address);
    address.setEditable(false);

    con = new JLabel("Contact No :");
    con.setBounds(50, 250, 80, 20);
    adminsearch.add(con);

    contact = new JTextField();
    contact.setBounds(150, 250, 100, 20);
    adminsearch.add(contact);
    contact.setEditable(false);
    
    search = new JButton("Search");
    search.setBounds(40, 300, 80, 20);
    adminsearch.add(search);
    search.addActionListener(this);
    
    clear = new JButton("Clear");
    clear.setBounds(160, 300, 80, 20);
    adminsearch.add(clear);
    clear.addActionListener(this);
    
    back = new JButton("Back");
    back.setBounds(40, 340, 80, 20);
    adminsearch.add(back);
    back.addActionListener(this);
    
    delete = new JButton("Delete");
    delete.setBounds(160, 340, 80, 20);
    adminsearch.add(delete);
    delete.addActionListener(this);
    
//    m = new JButton("Message");
//    m.setBounds(80, 380, 120, 20);
//    adminsearch.add(m);
//    m.addActionListener(this);
    
    adminsearch.setVisible(true);
    }
    
    public static void main(String args[]){
    new Admin_Member_Search();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == search){
        String nam = userid.getText();
    
      DataObject mydataobject = new DataObject();    
//    mydataobject.setQuery("Select * from member_data where username='"+nam+"';");
      mydataobject.setQuery("Select * from member_data where username='"+nam+"';");
    mydataobject.setMemberuser(nam);
    mydataobject.setFlag(1);
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            
            mydataobject = (DataObject)myInputStream.readObject();
            first.setText(mydataobject.getFname());
            System.out.println(mydataobject.getLname());
            last.setText(mydataobject.getLname());
            address.setText(mydataobject.getAddress());
            contact.setText(mydataobject.getContact());
    userid.setEditable(false);
    }   catch (IOException ex) {
           
        } catch (ClassNotFoundException ex) {
           
        }
    }
    
    if(e.getSource() == clear){
    first.setText("");
    userid.setText("");
    last.setText("");
    address.setText("");
    contact.setText("");
    userid.setEditable(true);
    }
    
    if(e.getSource() == back){
    new Admin_Home();
    adminsearch.setVisible(false);
    }
    
    if(e.getSource() == m){
    String value = JOptionPane.showInputDialog(this,"Enter message");
    System.out.println(value);
    String id = userid.getText();
    DataObject mydataobject = new DataObject();    
    mydataobject.setQuery("Update member_data SET message='"+value+"' where username='"+id+"';");
        try {
           
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            JOptionPane.showMessageDialog(this, "Message is send");

       
        } catch (IOException ex) {
        
        }
    
    }
    
    if(e.getSource() == delete){
    String id = userid.getText();
    DataObject mydataobject = new DataObject();    
    mydataobject.setQuery("Delete from member_data where username='"+id+"';");
        try {
           
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            JOptionPane.showMessageDialog(this, "Data has been deldeted");
            first.setText("");
    userid.setText("");
    last.setText("");
    address.setText("");
    contact.setText("");
    userid.setEditable(true);
            
//            mydataobject = (DataObject)myInputStream.readObject();
//            first.setText(mydataobject.getFname());
//            System.out.println(mydataobject.getLname());
//            last.setText(mydataobject.getLname());
//            address.setText(mydataobject.getAddress());
//            contact.setText(mydataobject.getContact());
       
        } catch (IOException ex) {
        
        }
//        catch (ClassNotFoundException ex) {
//          
//        }
    }
    
    }
   
}
