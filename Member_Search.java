
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
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Member_Search extends JFrame implements ActionListener{
JFrame search;
JLabel id, fn,ln,addr,con,we;
JTextField userid,first,last,contact;
JTextArea address;
JButton submit,clear,back;
String username,user_password;
    
Member_Search(){
initialize();
}

Member_Search(String k,String y){
initialize();
username = k;
user_password = y;
}
    
public void initialize(){
search = new JFrame();
search.getContentPane();
search.setLayout(null);
search.setSize(350, 500);
search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

we = new JLabel();
we.setBounds(100, 10, 40, 20);
search.add(we);

id = new JLabel("User id :");
id.setBounds(50, 50, 80, 20);
search.add(id);

userid = new JTextField();
userid.setBounds(150, 50, 100, 20);
search.add(userid);


fn = new JLabel("First name :");
fn.setBounds(50, 90, 80, 20);
search.add(fn);

first = new JTextField();
first.setBounds(150, 90, 100, 20);
search.add(first);
first.setEditable(false);

ln = new JLabel("Last name :");
ln.setBounds(50, 130, 80, 20);
search.add(ln);

last = new JTextField();
last.setBounds(150, 130, 100, 20);
search.add(last);
last.setEditable(false);

addr = new JLabel("Address :");
addr.setBounds(50, 170, 80, 20);
search.add(addr);

address = new JTextArea();
address.setBounds(150, 170, 100, 60);
search.add(address);
address.setEditable(false);

con = new JLabel("Contact No :");
con.setBounds(50, 250, 80, 20);
search.add(con);

contact = new JTextField();
contact.setBounds(150, 250, 100, 20);
search.add(contact);
contact.setEditable(false);

submit = new JButton("Submit");
submit.setBounds(40, 310, 100, 20);
search.add(submit);
submit.addActionListener(this);

clear = new JButton("Clear");
clear.setBounds(160, 310, 100, 20);
search.add(clear);
clear.addActionListener(this);

back = new JButton("Back");
back.setBounds(100, 350, 100, 20);
search.add(back);
back.addActionListener(this);

search.setVisible(true);
}


public static void main(String args[]){
Member_Search f = new Member_Search();
}    

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == submit){
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
       
        } catch (IOException ex) {
          
        } catch (ClassNotFoundException ex) {
          
        }
        
        
    }
    
    if(e.getSource() == clear){
    first.setText("");
    last.setText("");
    contact.setText("");
    address.setText("");
    userid.setText("");
    }
    
    if(e.getSource() == back){
    new Member_Home(username,user_password);
    search.setVisible(false);
    }
    
    }
    
}
