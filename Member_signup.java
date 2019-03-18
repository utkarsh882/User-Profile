
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
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Utku
 */
public class Member_signup extends JFrame implements ActionListener{
    JButton back,submit;
    JFrame frame;
    JTextField txtfname,txtlname,txtid,pass,contac;
    String first_name,last_name,username,password,address,contact;
    JTextArea address1;
    
    Member_signup(){
    initialize();
    }
    
    public void initialize(){
    frame = new JFrame();
    frame.setSize(350, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane();
    frame.setLayout(null);
    
    frame.setTitle("Member Login");
    JLabel fname = new JLabel("First Name :");
    fname.setBounds(50, 50, 80, 20);
    frame.add(fname);
    
    txtfname = new JTextField();
    txtfname.setBounds(150, 50, 100, 20);
    frame.add(txtfname);
    
    JLabel lname = new JLabel("Last Name :");
    lname.setBounds(50, 90, 80, 20);
    frame.add(lname);
    
    txtlname = new JTextField();
    txtlname.setBounds(150, 90, 100, 20);
    frame.add(txtlname);
    
    JLabel memid = new JLabel("Member Id :");
    memid.setBounds(50, 130, 80, 20);
    frame.add(memid);
    
    txtid = new JTextField();
    txtid.setBounds(150, 130, 100, 20);
    frame.add(txtid);
    
    JLabel passwor = new JLabel("Password :");
    passwor.setBounds(50, 170, 80, 20);
    frame.add(passwor);
    
    pass = new JTextField();
    pass.setBounds(150, 170, 100, 20);
    frame.add(pass);
    
    JLabel addr = new JLabel("Address :");
    addr.setBounds(50, 210, 80, 20);
    frame.add(addr);
    
    address1 = new JTextArea();
    address1.setBounds(150, 210, 100, 60);
    frame.add(address1);
    
    JLabel cont = new JLabel("Contact no. :");
    cont.setBounds(50, 290, 80, 20);
    frame.add(cont);
    
    contac = new JTextField();
    contac.setBounds(150, 290, 100, 20);
    frame.add(contac);
    
    submit = new JButton("Submit");
    submit.setBounds(40, 350, 100, 20);
    frame.add(submit);
    submit.addActionListener(this);
    
    back = new JButton("Back");
    back.setBounds(180, 350, 100, 20);
    frame.add(back);
    back.addActionListener(this);
    
    frame.setVisible(true);
    
    }
    
    public static void main(String args[]){
    
        Member_signup n = new Member_signup();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == back){
    new Login();
    frame.setVisible(false);
    }
    
    if(e.getSource() == submit){
    first_name = txtfname.getText();
    last_name = txtlname.getText();
    username = txtid.getText();
    password = pass.getText();
    address = address1.getText();
    contact = contac.getText();
    String x = "";
    DataObject mydataobject = new DataObject();    
    mydataobject.setQuery("insert into member_data values('"+username+"','"+password+"','"+first_name+"','"+last_name+"','"+address+"','"+contact+"', message='"+x+"');");
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            JOptionPane.showMessageDialog(this,"Member Details Inserted");
            txtfname.setText("");
    txtlname.setText("");
    txtid.setText("");
    pass.setText("");
    address1.setText("");
    contac.setText("");
        } catch (IOException ex) {
          
        }
        
    }
    
    }
}
