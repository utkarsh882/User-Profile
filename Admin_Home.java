
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Admin_Home extends JFrame implements ActionListener{
    JFrame adminhome;
    JButton searchmem, insertmem,logout,edit_member;

    Admin_Home(){
    initialize();
    }
    
    public void initialize(){
    adminhome = new JFrame();
    adminhome.setSize(500, 250);
    adminhome.getContentPane();
    adminhome.setLayout(null);
    adminhome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JLabel we = new JLabel(" Welcome Admin ");
    we.setBounds(200, 10, 100, 20);
    adminhome.add(we);
    
    searchmem = new JButton("Search a member");
    searchmem.setBounds(50, 50, 150, 20);
    adminhome.add(searchmem);
    searchmem.addActionListener(this);
    
    insertmem = new JButton("Insert a member");
    insertmem.setBounds(250, 50, 150, 20);
    adminhome.add(insertmem);
    insertmem.addActionListener(this);
    
    edit_member = new JButton("Edit a member");
    edit_member.setBounds(50, 100, 150, 20);
    adminhome.add(edit_member);
    edit_member.addActionListener(this);
    
    logout = new JButton("Logout");
    logout.setBounds(250, 100, 150, 20);
    adminhome.add(logout);
    logout.addActionListener(this);
    
    adminhome.setVisible(true);
    }
    
    public static void main(String args[]){
    new Admin_Home();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == searchmem){
    new Admin_Member_Search();
    adminhome.setVisible(false);
    }
    
    if(e.getSource() == insertmem){
    new Admin_Insert();
    adminhome.setVisible(false);
    }
    
    if(e.getSource() == logout){
    new Login();
    adminhome.setVisible(false);
    }
    
    if(e.getSource() == edit_member){
    new Admin_Member_Edit();
    adminhome.setVisible(false);
    }
    
    }
   
}
