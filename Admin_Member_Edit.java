
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


public class Admin_Member_Edit extends JFrame implements ActionListener{
    JFrame editor;
    JLabel name, name2,addr,pass,conta,we;
    JTextField fname,lname,password,contact,userid;
    JTextArea address;
    JButton edit, clear,back;
    String username;
    
    Admin_Member_Edit(){
    initialize();
    }
    
    
    
    public void initialize(){
    editor = new JFrame();
    editor.setSize(350, 500);
    editor.getContentPane();
    editor.setLayout(null);
    editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    
    JLabel id = new JLabel("User Id :");
    id.setBounds(50, 10, 80, 20);
    editor.add(id);
    
    userid = new JTextField();
    userid.setBounds(150, 10, 100, 20);
    editor.add(userid);
    
    JLabel name = new JLabel("First Name :");
    name.setBounds(50, 50, 80, 20);
    editor.add(name);
    
    fname = new JTextField();
    fname.setBounds(150, 50, 100, 20);
    editor.add(fname);
    
    name2 = new JLabel("Last Name :");
    name2.setBounds(50, 90, 80, 20);
    editor.add(name2);
    
    lname = new JTextField();
    lname.setBounds(150, 90, 100, 20);
    editor.add(lname);
    
    pass = new JLabel("Password");
    pass.setBounds(50, 130, 80, 20);
    editor.add(pass);
    
    password = new JTextField();
    password.setBounds(150, 130, 100, 20);
    editor.add(password);
    
    addr = new JLabel("Address :");
    addr.setBounds(50, 170, 80, 20);
    editor.add(addr);
    
    address = new JTextArea();
    address.setBounds(150, 170, 100, 60);
    editor.add(address);
    
    conta = new JLabel("Contact no. :");
    conta.setBounds(50, 250, 80, 20);
    editor.add(conta);
    
    contact = new JTextField();
    contact.setBounds(150, 250, 100, 20);
    editor.add(contact);
    
    edit = new JButton("Edit");
    edit.setBounds(40, 300, 80, 20);
    editor.add(edit);
    edit.addActionListener(this);
    
    clear = new JButton("Clear");
    clear.setBounds(160, 300, 80, 20);
    editor.add(clear);
    clear.addActionListener(this);
    
    back = new JButton("Back");
    back.setBounds(100, 330, 80, 20);
    editor.add(back);
    back.addActionListener(this);
    
    editor.setVisible(true);
    }
    
    public static void main(String args[]){
    new Admin_Member_Edit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == back){
        Admin_Home admin_Home = new Admin_Home();
    editor.setVisible(false);
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
    String username = userid.getText();
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
            userid.setText("");
            password.setText("");
            address.setText("");
            contact.setText("");
        } catch (IOException ex) {
          
        }
    }
    
    }
    
}
