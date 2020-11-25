package junsProject0530;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main_Login extends JFrame implements ActionListener{

	Color color = new Color(237, 37, 66, 255);
	CardLayout card = new CardLayout();
	JPanel cardPanel = new JPanel(card);
	JPanel panel1 = new JPanel(new BorderLayout());
	Main panel2 = new Main();
	
	JPanel Inerpanel = new JPanel(new BorderLayout());
	JPanel login = new JPanel(new BorderLayout());
	JPanel[] loginPanel = new JPanel[3];
	JLabel[] loginLabel = new JLabel[2];
	
	JTextField id = new JTextField("");
	JPasswordField pwd = new JPasswordField();
	ImageIcon icon = new ImageIcon("src\\junsProject0530\\image\\logo1.png");
	RoundButton loginButton = new RoundButton("로그인");
	Font font1 = new Font("Malgun Gothic", Font.BOLD, 18);
	String staff_id = "";
	String staff_name = "";
	String staff_level = "";
	String staff_gender = "";
	String staff_tel = "";
	
	
	public Main_Login() {
		//카드레이아웃
		cardPanel.add(panel1, "login");
		cardPanel.add(panel2, "main");
		loginButton.addActionListener(this);
		loginButton.setFont(font1);
		loginButton.setBackground(color);
		
		
		//로그인 판넬
		loginLabel[0] = new JLabel("                                                ID");
		loginLabel[0].setFont(font1);
		loginLabel[1] = new JLabel("                                                PW");
		loginLabel[1].setFont(font1);
		
		
		loginPanel[0] = new JPanel(new GridLayout(2,1, 0, 13));
		loginPanel[0].setBackground(Color.WHITE);
		loginPanel[0].add(id);
		id.setFont(font1);
		loginPanel[0].add(pwd);
		pwd.setFont(font1);
		
		loginPanel[1] = new JPanel(new GridLayout(2,1));
		loginPanel[1].add(loginLabel[0]);
		loginPanel[1].add(loginLabel[1]);
		loginPanel[1].setBackground(Color.WHITE);
		
		loginPanel[2] = new JPanel(new BorderLayout());
		loginPanel[2].add("Center",new JLabel("                                                                                     "));
		loginPanel[2].add("West",loginButton);
		loginPanel[2].setBackground(Color.WHITE);
		
		
		
		login.add("Center",loginPanel[0]);
		login.add("West",loginPanel[1]);
		login.add("East",loginPanel[2]);
		Inerpanel.add("South",login);
		Inerpanel.setBackground(Color.WHITE);
		
		
		
		panel1.add("North", new JLabel(icon));
		panel1.add("West", new JLabel("                                                             "));
		panel1.add("East", new JLabel("                                                             "));
		panel1.add("Center", Inerpanel);
		panel1.add("South", new JLabel("                                "));
		login.setBackground(Color.WHITE);
		panel1.setBackground(Color.WHITE);
		
		
		
		this.add(cardPanel);
		this.setVisible(true);
		this.setSize(1280, 720);
		this.setBackground(Color.WHITE);
		this.setResizable(false);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == loginButton) {
			
			CRUDprocess crud = new CRUDprocess();
			StaffInfo info = new StaffInfo();
			info.setStaff_id(id.getText());
			info.setStaff_password(pwd.getText());
			
			info = crud.selectIdAndPwd(info);
			
			if(info == null) {
				JOptionPane.showMessageDialog(this, "아이디나 비밀번호가 틀렸습니다.");
			}else if(info.getStaff_level().equals("관리자")) {
				JOptionPane.showMessageDialog(this, "관리자님 환영합니다.");
				card.show(cardPanel, "main");
				panel2.staff_id = info.getStaff_id();
				System.out.println(panel2.staff_id);
				panel2.staff_name = info.getStaff_name();
				System.out.println(panel2.staff_name);
				panel2.staff_level = info.getStaff_level();
				System.out.println(panel2.staff_level);
				panel2.staff_gender = info.getStaff_gender();
				System.out.println(panel2.staff_gender);
				panel2.staff_tel = info.getStaff_tel();
				System.out.println(panel2.staff_tel);
			}else {
				JOptionPane.showMessageDialog(this, info.getStaff_name() + " 환영합니다.");
				card.show(cardPanel, "main");
				panel2.mode = "Staff";
				panel2.staff_id = info.getStaff_id();
				System.out.println(panel2.staff_id);
				panel2.staff_name = info.getStaff_name();
				System.out.println(panel2.staff_name);
				panel2.staff_level = info.getStaff_level();
				System.out.println(panel2.staff_level);
				panel2.staff_gender = info.getStaff_gender();
				System.out.println(panel2.staff_gender);
				panel2.staff_tel = info.getStaff_tel();
				System.out.println(panel2.staff_tel);
				
			}
			
		}
	}
	
	public static void main(String[] args) {
		new Main_Login();
	}

}
