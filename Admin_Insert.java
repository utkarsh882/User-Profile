
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
public class Admin_Insert extends JFrame implements ActionListener{
    JButton back,submit;
    JFrame frame1;
    JTextField txtfname,txtlname,txtid,pass,contact;
    JTextArea addres;
    JLabel label;
    
    Admin_Insert(){
    initialize();
    }
    
    public void initialize(){
    frame1 = new JFrame();
    frame1.setSize(350, 500);
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.getContentPane();
    frame1.setLayout(null);
    
    frame1.setTitle("Member Login");
    JLabel fname = new JLabel("First Name :");
    fname.setBounds(50, 50, 80, 20);
    frame1.add(fname);
    
    txtfname = new JTextField();
    txtfname.setBounds(150, 50, 100, 20);
    frame1.add(txtfname);
    
    JLabel lname = new JLabel("Last Name :");
    lname.setBounds(50, 90, 80, 20);
    frame1.add(lname);
    
    txtlname = new JTextField();
    txtlname.setBounds(150, 90, 100, 20);
    frame1.add(txtlname);
    
    JLabel memid = new JLabel("Member Id :");
    memid.setBounds(50, 130, 80, 20);
    frame1.add(memid);
    
    txtid = new JTextField();
    txtid.setBounds(150, 130, 100, 20);
    frame1.add(txtid);
    
    JLabel password = new JLabel("Password :");
    password.setBounds(50, 170, 80, 20);
    frame1.add(password);
    
    pass = new JTextField();
    pass.setBounds(150, 170, 100, 20);
    frame1.add(pass);
    
    JLabel addr = new JLabel("Address :");
    addr.setBounds(50, 210, 80, 20);
    frame1.add(addr);
    
    addres = new JTextArea();
    addres.setBounds(150, 210, 100, 60);
    frame1.add(addres);
    
    JLabel cont = new JLabel("Contact no. :");
    cont.setBounds(50, 290, 80, 20);
    frame1.add(cont);
    
    contact = new JTextField();
    contact.setBounds(150, 290, 100, 20);
    frame1.add(contact);
    
    submit = new JButton("Submit");
    submit.setBounds(40, 350, 100, 20);
    frame1.add(submit);
    submit.addActionListener(this);
    
    label = new JLabel();
    label.setBounds(40, 370, 100, 20);
    frame1.add(label);
    
    
    back = new JButton("Back");
    back.setBounds(180, 350, 100, 20);
    frame1.add(back);
    back.addActionListener(this);
    
    frame1.setVisible(true);
    
    }
    
    public static void main(String args[]){
    
        Admin_Insert n = new Admin_Insert();
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == back){
    new Admin_Home();
    frame1.setVisible(false);
    }
    
    if(e.getSource() == submit){
        String first_name = txtfname.getText();
        String last_name = txtlname.getText();
        String username = txtid.getText();
        String password = pass.getText();
        String address = addres.getText();
        String contact1 = contact.getText();
        
    DataObject mydataobject = new DataObject();    
    mydataobject.setQuery("insert into member_data values('"+username+"','"+password+"','"+first_name+"','"+last_name+"','"+address+"','"+contact1+"')");
        try {
            System.out.println("Before socket");
            Socket socketToServer = new Socket("afsconnect1.njit.edu", 33007);
            System.out.println("After socket");
            
           
            ObjectOutputStream myOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream myInputStream = new ObjectInputStream(socketToServer.getInputStream());
            myOutputStream.writeObject(mydataobject);
            System.out.println("End of query sending");
            JOptionPane.showMessageDialog(this, "Added Successfully");
            txtfname.setText("");
        txtlname.setText("");
        txtid.setText("");
        pass.setText("");
        addres.setText("");
        contact.setText("");
            
        } catch (IOException ex) {
          
        }
        
        
    }
    
    }
}
