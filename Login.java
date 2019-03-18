
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Login extends JFrame implements ActionListener{
    JButton signup,submit;
    JFrame login;
    JLabel user,pass,retry;
    JTextField username,password;
    JCheckBox admin;
    Login(){
    initialize1();
    }
    public void initialize1(){
    login = new JFrame();
    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    login.setSize(350,300);
    login.getContentPane();
    login.setLayout(null);
    
    user = new JLabel("Username :");
    user.setBounds(50, 50, 80, 20);
    login.add(user);
    
    username = new JTextField();
    username.setBounds(150, 50, 100, 20);
    login.add(username);
    
    admin = new JCheckBox("Admin");
    admin.setBounds(250, 50, 80, 20);
    login.add(admin);
    
    pass = new JLabel("Password :");
    pass.setBounds(50, 110, 80, 20);
    login.add(pass);
    
    password = new JTextField();
    password.setBounds(150, 110, 100, 20);
    login.add(password);
    
    retry = new JLabel();
    retry.setBounds(150, 130, 100, 20);
    login.add(retry);
    
    submit = new JButton("Submit");
    submit.setBounds(45, 170, 100, 30);
    submit.addActionListener(this);
    login.add(submit);
    
    signup = new JButton("Signup");
    signup.setBounds(160, 170, 100, 30);
    signup.addActionListener(this);
    login.add(signup);
    
    login.setVisible(true);
    
    }
    public static void main(String args[]){
    Login l1 = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == signup){
    new Member_signup();
    login.setVisible(false);
    }
    if(e.getSource() == submit){    
    String us = username.getText();
    String pa = password.getText();
    DataObject mydataobject = new DataObject();
    
    if(admin.isSelected()){
    System.out.println("Admin selected");
    if(pa.equals("admin") && us.equals("admin")){
    new Admin_Home();
    login.setVisible(false);
    }
//    mydataobject.setQuery("Select * from admin where username='"+us+"' AND password='"+pa+"';");
    mydataobject.setQuery("Select * from admin where username='"+us+"' AND password='"+pa+"';");
    mydataobject.setMemberuser(us);
    mydataobject.setMemberpassword(pa);
    
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            
            mydataobject = (DataObject)myInputStream.readObject();
            if(mydataobject.getFlag() == 1){
                System.out.println("user not found");
                
                retry.setText("Try Again");
                
                username.setText("");
                password.setText("");
            }
            else{
               System.out.println(mydataobject.getFname());
            new Admin_Home();
    login.setVisible(false);
            }

        } catch (IOException ex) {
          
        } catch (ClassNotFoundException ex) {
          
        }
    }
    
    else{
//    mydataobject.setQuery("Select * from member_data where username='"+us+"' AND password='"+pa+"';");
         mydataobject.setQuery("Select * from member_data where username='"+us+"' AND password ='"+pa+"';");
         mydataobject.setMemberuser(us);
         mydataobject.setMemberpassword(pa);
         mydataobject.setFlag(0);
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            
            mydataobject = (DataObject)myInputStream.readObject();
            if(mydataobject.getFlag() == 1){
                System.out.println("user not found");
                retry.setText("Try Again");
                username.setText("");
                password.setText("");
            }
            else{
               
               System.out.println("username = "+ us);
               System.out.println(mydataobject.getMemberuser());
            new Member_Home(us,pa);
            
    login.setVisible(false);
            }

        } catch (IOException ex) {
          
        } catch (ClassNotFoundException ex) {
          
        }
//    if((us.equals("admin")) && (pa.equals("admin"))){
//    new Admin_Home();
//    login.setVisible(false);
//    }
    }
    }
    }
    
}