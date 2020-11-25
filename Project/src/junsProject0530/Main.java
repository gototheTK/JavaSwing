package junsProject0530;

import java.awt.BorderLayout;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Main extends JPanel implements ActionListener{

	Color color = new Color(237, 37, 66, 200);
	
	JPanel[] employeeMenu;
	RoundButton[] employee_Button;//사원모드 메뉴
	String[] employeeStr = {"주문 페이지", "주문 내역", "마이 페이지"};
	
	RoundButton[] change_Button;//관리자, 사원 메뉴
	String[] changeStr = {"사원", "관리자"};
	
	JPanel[] managerMenu;
	RoundButton[] manager_Button;//관리자모드 메뉴
	String[] managerStr = {"공지사항 관리", "사원 관리", "재고 관리", "매출 관리"};

	RoundButton exit_Button;//나가기버튼
	
	Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
	
	JPanel menu_Panel[];//0.사원모드 1.관리자 사원 전환 메뉴 2.관리자메뉴
	
	CardLayout menu_Card;//사워 및 관리자 전환 레이아웃
	JPanel menu_Card_Panel;
	
	
	//메뉴화면 폰트
	Font font1 = new Font("Malgun Gothic", Font.BOLD, 30);//위쪽메뉴
	Font font2 = new Font("Malgun Gothic", Font.BOLD, 25);//오른쪽메뉴
	
	
	//내용 변수
	CardLayout content_Card;
	JPanel content_Card_Panel;
	Employee_Order emplyoee_Order;
	Employee_OrderCheck employee_OrderCheck;
	Employee_MyPage employee_Mypage;
	Manager_NoticeManagement manager_NoticeManagement;
	Manager_EmployeeManagement manager_EmployeeManagement;
	Manager_StockManagement manager_StockManagement;
	Manager_SalesManagement manager_SalesManagement;
	

	
	String mode = "Manager";//로그인 모드변수
	
	static String staff_id = "";
	static String staff_name = "";
	static String staff_level = "";
	static String staff_gender = "";
	static String staff_tel = "";
	
	
	//메인 생성자
	public Main() {
		this.setLayout(new BorderLayout());
		makeMenu();
		makeContent();
		
		
		this.setSize(1280, 720);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		
	}
	
	
	
	//메뉴버튼 클릭시 내용 전환
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object obj = arg0.getSource();
		
		if(obj == employee_Button[0]) {//사원 주문
			content_Card.show(content_Card_Panel, "Order");
		}
		else if(obj == employee_Button[1]) {//사원 주문확인
			content_Card.show(content_Card_Panel, "OrderCheck");
			employee_OrderCheck.updateLeft();
		}
		else if(obj == employee_Button[2]) {//사원 마이페이지
			content_Card.show(content_Card_Panel, "MyPage");
			employee_Mypage.staff_id = staff_id;
			employee_Mypage.staff_name =staff_name;
			employee_Mypage.staff_level = staff_level;
			employee_Mypage.staff_gender = staff_gender;
			employee_Mypage.staff_tel = staff_tel;
			employee_Mypage.updateLeft();
		}
		else if(obj == change_Button[0]) {//사원 전환
			menu_Card.show(menu_Card_Panel, "Employee_Menu");
		}
		else if(obj == change_Button[1] && mode == "Manager") {//관리자전환
			menu_Card.show(menu_Card_Panel, "Manager_Menu");
		}
		else if(obj == manager_Button[0]) {//관리자 공지사항
			content_Card.show(content_Card_Panel, "Notice");
		}
		else if(obj == manager_Button[1]) {//관리자 사원관리
			content_Card.show(content_Card_Panel, "Employee");
			manager_EmployeeManagement.updateLeft();
		}
		else if(obj == manager_Button[2]) {//관리자 재고관리
			content_Card.show(content_Card_Panel, "Stock");
			manager_StockManagement.updateLeft();
			
		}
		else if(obj == manager_Button[3]) {//관리자 매출관리
			content_Card.show(content_Card_Panel, "Sales");
		}
		else if(obj == exit_Button) {
			System.exit(0);
		}
		
	}
	
	
	
	//가운데 내용만들기
	void makeContent() {
		
		//각 카드레이아웃 페이지 생성
		emplyoee_Order = new Employee_Order();
		employee_OrderCheck = new Employee_OrderCheck();
		employee_Mypage = new Employee_MyPage();
		manager_NoticeManagement = new Manager_NoticeManagement();
		manager_EmployeeManagement = new Manager_EmployeeManagement();
		manager_StockManagement = new Manager_StockManagement();
		manager_SalesManagement = new Manager_SalesManagement();
		
		
		
		
		content_Card = new CardLayout();
		content_Card_Panel = new JPanel(content_Card);
		content_Card_Panel.add("Order", emplyoee_Order);
		content_Card_Panel.add("OrderCheck", employee_OrderCheck);
		content_Card_Panel.add("MyPage", employee_Mypage);
		content_Card_Panel.add("Notice", manager_NoticeManagement);
		content_Card_Panel.add("Employee", manager_EmployeeManagement);
		content_Card_Panel.add("Stock", manager_StockManagement);
		content_Card_Panel.add("Sales", manager_SalesManagement);
		
		
		
		
		this.add("Center", content_Card_Panel);
	}
	
	//메뉴화면 만들기
	void makeMenu() {
		menu_Panel = new JPanel[4];
		employee_Button = new RoundButton[employeeStr.length];
		change_Button = new RoundButton[changeStr.length];
		manager_Button = new RoundButton[managerStr.length];
		
		
		//판넬 생성
		menu_Panel[0] = new JPanel();//사원 메뉴
		menu_Panel[0].setLayout(new GridLayout(1,3));
		menu_Panel[1] = new JPanel();//전환 메뉴
		menu_Panel[1].setLayout(new GridLayout(2,1));
		menu_Panel[2] = new JPanel();//관리자 메뉴
		menu_Panel[2].setLayout(new GridLayout(1,3));
		menu_Panel[3] = new JPanel();//나가기 
		menu_Panel[3].setLayout(new FlowLayout(FlowLayout.LEFT));
		menu_Card = new CardLayout();
		menu_Card_Panel = new JPanel(menu_Card);
		
		
		//메뉴에 넣을 판넬
		employeeMenu = new JPanel[3];
		managerMenu = new JPanel[3];
		
		for(int i=0; i<employeeStr.length; i++) {
			
			employee_Button[i] = new RoundButton(employeeStr[i]);
			employee_Button[i].setFont(font1);
			employee_Button[i].setBackground(color);
			employee_Button[i].addActionListener(this);
			
		}
		
		
		
		//로고 이미지 생성하고 크기 변경
		ImageIcon icon = new ImageIcon("src\\junsProject0530\\image\\logo1.png");
		Image img = icon.getImage();
		Image im2 = img.getScaledInstance(440, 60, Image.SCALE_DEFAULT);;
		icon = new ImageIcon(im2);
		JLabel logo1 = new JLabel(icon);
		JLabel logo2 = new JLabel(icon);
		
		
		
		//사원 메뉴 패널 설정들
		employeeMenu[0] = new JPanel(new GridLayout(1,2));
		employeeMenu[1] = new JPanel(new BorderLayout());
		employeeMenu[2] = new JPanel(new GridLayout(1,1));
		employeeMenu[0].add(employee_Button[0]);
		employeeMenu[0].add(employee_Button[1]);
		employeeMenu[1].setBackground(Color.WHITE);
		employeeMenu[1].add("Center",logo1);
		employeeMenu[2].add(employee_Button[2]);
		menu_Panel[0].add(employeeMenu[0]);
		menu_Panel[0].add(logo1);
		menu_Panel[0].setBackground(Color.WHITE);
		menu_Panel[0].add(employeeMenu[2]);
		
		
		
		//전환메뉴
		for(int i=0; i<changeStr.length; i++) {
			change_Button[i] = new RoundButton(changeStr[i]);
			change_Button[i].setBackground(color);
			change_Button[i].setFont(font2);
			change_Button[i].addActionListener(this);
			menu_Panel[1].add(change_Button[i]);
		
		}
		
		
		
		//관리자메뉴
		for(int i=0; i<managerStr.length; i++) {
			
			manager_Button[i] = new RoundButton(managerStr[i]);
			manager_Button[i].setBackground(color);
			manager_Button[i].setFont(font1);
			manager_Button[i].addActionListener(this);
			
		
		}
		
		
		
		//관리자메뉴 생성
		managerMenu = new JPanel[3];
		managerMenu[0] = new JPanel(new GridLayout(1,2));
		managerMenu[1] = new JPanel(new BorderLayout());
		managerMenu[2] = new JPanel(new GridLayout(1,2));
		managerMenu[0].add(manager_Button[0]);
		managerMenu[0].add(manager_Button[1]);
		managerMenu[1].add("Center", logo2);
		managerMenu[1].setBackground(Color.WHITE);
		managerMenu[2].add(manager_Button[2]);
		managerMenu[2].add(manager_Button[3]);
		menu_Panel[2].add(managerMenu[0]);
		menu_Panel[2].add(managerMenu[1]);
		menu_Panel[2].add(managerMenu[2]);
		
		
		
		//나가기버튼
		exit_Button = new RoundButton("나가기");
		exit_Button.setBackground(color);
		exit_Button.setFont(font1);
		exit_Button.addActionListener(this);
		menu_Panel[3].add(exit_Button);
		menu_Panel[3].setBackground(Color.WHITE);
		
		
		
		//메뉴 카드레이아웃
		menu_Card_Panel.add("Employee_Menu", menu_Panel[0]);
		menu_Card_Panel.add("Manager_Menu", menu_Panel[2]);
		
		
		
		this.add("North", menu_Card_Panel);
		this.add("East", menu_Panel[1]);
		this.add("South", menu_Panel[3]);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	
}
