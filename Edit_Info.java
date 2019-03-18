
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Edit_Info extends JFrame implements ActionListener{
    JFrame editing;
    JLabel name, name2,addr,pass,conta,we;
    JTextField fname,lname,password,contact;
    JTextArea address;
    JButton edit, clear,back;
    String username,user_password;
    
    Edit_Info(){
    initialize();
    }
    
    Edit_Info(String m,String y){
    initialize();
    username = m;
    user_password = y;
    }
    
    public void initialize(){
    editing = new JFrame();
    editing.setSize(350, 500);
    editing.getContentPane();
    editing.setLayout(null);
    editing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    we = new JLabel();
    we.setBounds(100, 10, 80, 20);
    editing.add(we);
    
    JLabel name = new JLabel("First Name :");
    name.setBounds(50, 50, 80, 20);
    editing.add(name);
    
    fname = new JTextField();
    fname.setBounds(150, 50, 100, 20);
    editing.add(fname);
    
    name2 = new JLabel("Last Name :");
    name2.setBounds(50, 90, 80, 20);
    editing.add(name2);
    
    lname = new JTextField();
    lname.setBounds(150, 90, 100, 20);
    editing.add(lname);
    
    pass = new JLabel("Password");
    pass.setBounds(50, 130, 80, 20);
    editing.add(pass);
    
    password = new JTextField();
    password.setBounds(150, 130, 100, 20);
    editing.add(password);
    
    addr = new JLabel("Address :");
    addr.setBounds(50, 170, 80, 20);
    editing.add(addr);
    
    address = new JTextArea();
    address.setBounds(150, 170, 100, 60);
    editing.add(address);
    
    conta = new JLabel("Contact no. :");
    conta.setBounds(50, 250, 80, 20);
    editing.add(conta);
    
    contact = new JTextField();
    contact.setBounds(150, 250, 100, 20);
    editing.add(contact);
    
    edit = new JButton("Edit");
    edit.setBounds(40, 300, 80, 20);
    editing.add(edit);
    edit.addActionListener(this);
    
    clear = new JButton("Clear");
    clear.setBounds(160, 300, 80, 20);
    editing.add(clear);
    clear.addActionListener(this);
    
    back = new JButton("Back");
    back.setBounds(100, 330, 80, 20);
    editing.add(back);
    back.addActionListener(this);
    
    editing.setVisible(true);
    }
    
    public static void main(String args[]){
    new Edit_Info();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == back){
    new Member_Home(username,user_password);
    editing.setVisible(false);
    }    
    
    if(e.getSource() == clear){
    fname.setText("");
    lname.setText("");
    contact.setText("");
    address.setText("");
    password.setText("");
    }
    
    if(e.getSource() == edit){
    String first_name = fname.getText();
    String last_name = lname.getText();
    
    String password1 = password.getText();
    String address1 = address.getText();
    String contact1 = contact.getText();
    
    DataObject mydataobject = new DataObject();
   
    if((first_name != null) && (last_name != null) && (contact1 != null) && (password1 != null) && (address1 != null)){
    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"',first_name='"+first_name+"',address='"+address1+"',password='"+password1+"',contact_no='"+contact1+"' WHERE username='"+username+"';");
    }
    else if((first_name != null) && (last_name != null) && (contact1 != null) && (password1 != null)){
    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"',first_name='"+first_name+"',password='"+password1+"',contact_no='"+contact1+"' WHERE username='"+username+"';");
    }
    else if((first_name != null) && (last_name != null) && (contact1 != null) && (address1 != null)){
    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"',first_name='"+first_name+"',address='"+address1+"',contact_no='"+contact1+"' WHERE username='"+username+"';");
    }
    else if((first_name != null) && (last_name != null) && (password1 != null) && (address1 != null)){
    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"',first_name='"+first_name+"',address='"+address1+"',password='"+password1+"' WHERE username='"+username+"';");
    }
    else if((first_name != null) && (contact1 != null) && (password1 != null) && (address1 != null)){
    mydataobject.setQuery("Update member_data SET first_name='"+first_name+"',address='"+address1+"',password='"+password1+"',contact_no='"+contact1+"' WHERE username='"+username+"';");
    }
    else if((last_name != null) && (contact1 != null) && (password1 != null) && (address1 != null)){
    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"',address='"+address1+"',password='"+password1+"',contact_no='"+contact1+"' WHERE username='"+username+"';");
    }
     
//    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"' WHERE username='"+username+"';");
//    if((first_name.equals("")) && (contact1.equals(null)) && (address1.equals(null)) && (password1.equals(null))){
//    mydataobject.setQuery("Update member_data SET last_name='"+last_name+"' WHERE username='"+username+"';");
//    }
//    mydataobject.setQuery("insert into member_data values(update member_data SET first_name='"+first_name+"', last_name='"+last_name+"' where username='"+username+"');");
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            JOptionPane.showMessageDialog(this,"Updated Successfully");
            fname.setText("");
    lname.setText("");
    contact.setText("");
    address.setText("");
    password.setText("");
        } catch (IOException ex) {
          
        }
    }
    
    }
    
}
