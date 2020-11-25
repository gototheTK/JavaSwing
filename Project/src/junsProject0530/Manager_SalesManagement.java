package junsProject0530;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;


class MonthCalendar extends JPanel implements ActionListener, ItemListener {
	
	Choice chyear;
	JLabel yLabel;
	int year;
	GregorianCalendar gc;
	Calendar ca = Calendar.getInstance();
	 String[] Month={"1","2","3","4","5","6","7", "8", "9", "10", "11", "12"};
	JButton[] Months = new JButton[12];
	 JPanel selectPanel = new JPanel();
	 GridLayout grid = new GridLayout(4,3,5,5);//행,열,수평갭,수직갭
	 Dimension dimen, dimen1;
	 int xpos, ypos;
	public MonthCalendar(){
		this.setLayout(new BorderLayout());

		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int)(dimen.getWidth()/2 - dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);//화면의 가운데에 출력
		setVisible(true);
		chyear = new Choice();
		yLabel = new JLabel("년");
		select();
	}

	public void select(){
		JPanel panel = new JPanel(grid);//7행 7열의 그리드레이아웃
		for(int i=2020; i>=2000;i--){
			chyear.add(String.valueOf(i));
		}
		for(int i = 0; i < 12;i++){//모두 42개의 버튼을 생성
			Months[i] = new JButton(Month[i]);//제목이 없는 버튼 생성
			Months[i].setBackground(Color.WHITE);
			panel.add(Months[i]);
		}
		selectPanel.add(chyear);
		selectPanel.add(yLabel);

		
		this.add(selectPanel,"North");//연도와 월을 선택할 수 있는 화면읠 상단에 출력
		this.add(panel, "Center");
		
		String y = ca.get(Calendar.YEAR)+"";
		chyear.select(y);
		chyear.addItemListener(this);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


class Dialog extends JDialog {
	
	public Dialog(JComponent component) {
		this.add(component);

		setSize(400, 300);
	}
	
	
}


public class Manager_SalesManagement extends Content_Panel implements ActionListener, MouseListener{
	
	
	public Manager_SalesManagement() {
		
		makeLeft();
		makeRight();
		
	}
	
	//왼쪽 화면
	JPanel left_Panel = new JPanel(new BorderLayout());
	RoundButton[] left_Button = new RoundButton[4];
	String[] left_ButtonLabel = {"일일 매출","일매출 그래프","월별 매출","월매출 그래프"};
	JPanel left_ButtonPanel = new JPanel(new GridLayout(4, 1, 0, 50));
	Font font = new Font("Malgun Gothic", Font.BOLD,50);//왼쪽 폰트
	
	void makeLeft() {
		
		left_ButtonPanel.setBackground(Color.WHITE);
		for(int i=0; i<left_ButtonLabel.length; i++) {
			left_Button[i] = new RoundButton(left_ButtonLabel[i]);
			left_Button[i].setFont(font);
			left_Button[i].setBackground(color);
			left_Button[i].addActionListener(this);
			left_ButtonPanel.add(left_Button[i]);
		}
		
		left_Panel.setBackground(Color.WHITE);
		left_Panel.add("North", new JLabel("              "));
		left_Panel.add("West", new JLabel("              "));
		left_Panel.add("South", new JLabel("              "));
		left_Panel.add("South", new JLabel("              "));
		left_Panel.add(left_ButtonPanel);
		this.addLeftComponent(left_Panel);
	
	}
	
	
	//오른쪽 화면
	//센터 레이아웃
	String[] right_CardName = {"DaySales", "DayChart", "MonthSales", "MonthChart"};
	CardLayout right_CardLayout = new CardLayout();
	JPanel right_CardPanel = new JPanel(right_CardLayout);
	JPanel[] right_Panel = new JPanel[right_CardName.length];
	//아래 버튼 레이아웃
	String[] right_CardButtomName = {"DaySalesButtom", "DayChartButtom", "MonthSalesButtom", "MonthChartButtom"};
	CardLayout right_CardButtomLayout = new CardLayout();
	JPanel right_CardButtomPanel = new JPanel(right_CardButtomLayout);
	JPanel[] right_ButtomPanel = new JPanel[right_CardName.length];
	
	//일별 레이아웃
	JLabel[] right_DayLabel = {new JLabel("일자") ,new JLabel("매출"), new JLabel("변동사항")};
	String Day = "";
	JLabel right_Day = new JLabel(Day);
	JPanel right_DayPanel = new JPanel(new BorderLayout());
	RoundButton right_DayButton = new RoundButton("..");
	int DaySales = 0;
	JLabel right_DaySales = new JLabel(DaySales+"");
	JTextField right_DayChange = new JTextField("");
	RoundButton right_DayCheck = new RoundButton("확인");
	RoundButton right_DayCencel = new RoundButton("수정");
	
	
	//일별 그래프
	String[] selectedDay = {"", "", "", "", "", "", ""};//차트항목
	String[] DayData = {"", "", "", "", "", "", ""};//그래프 불러올 항목
	BarchartSystem dayChart = new BarchartSystem();
	
	//월별 레이아웃
	JLabel[] right_MonthLabel = {new JLabel("일자") ,new JLabel("매출"), new JLabel("변동사항")};
	String Month = "";
	JLabel right_Month = new JLabel(Month);
	JPanel right_MonthPanel = new JPanel(new BorderLayout());
	RoundButton right_MonthButton = new RoundButton("..");
	int MonthSales = 0;
	JLabel right_MonthSales = new JLabel(MonthSales+"");
	JTextField right_MonthChange = new JTextField("");
	RoundButton right_MonthCheck = new RoundButton("확인");
	RoundButton right_MonthCencel = new RoundButton("수정");
	
	//월별 그래프
	String[] selectedMonth = {"", "", "", "", "", "", ""};//차트항목
	String[] MonthData = {"", "", "", "", "", "", ""};//그래프 불러올 항목
	BarchartSystem monthChart = new BarchartSystem();
	
	JPopupMenu popup;//팝업메뉴
	CalendarBySwing calendar;//달력
	MonthCalendar months;
	Dialog dial1;
	Dialog dial2;
	
	Dialog dial3 = new Dialog(dayChart);
	Dialog dial4 = new Dialog(monthChart);
	

	void makeRight() {
		dial4.setSize(800, 400);
		dial3.setSize(800, 400);
		
		
		//오른쪽 내용생성
		for(int i=0; i<right_CardName.length; i++) {
			//판넬 생성
			right_Panel[i] = new JPanel();
			right_Panel[i].setBackground(Color.WHITE);
			right_CardPanel.add(right_Panel[i], right_CardName[i]);
			//버튼 생성판넬생성
			right_ButtomPanel[i] = new JPanel();
			right_ButtomPanel[i].setBackground(Color.WHITE);
			right_CardButtomPanel.add(right_ButtomPanel[i], right_CardButtomName[i]);
		}
		
		//달력 생성
		calendar = new CalendarBySwing();
		dial1 = new Dialog(calendar);
		right_DayButton.addActionListener(this);
		for(int i=0; i<42; i++) {
			calendar.days[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String year = calendar.chyear.getSelectedItem();
	        		String month = calendar.chmonth.getSelectedItem();
	        		JButton btn = (JButton)e.getSource();
	        		String day = btn.getText();
	        		Day= year+"년"+month+"월"+day+"일";
	        		right_Day.setText(Day);
	        		year = year.substring(2);
	        		
	        		if(Integer.parseInt(month)<10) {
	        			month = "0" + month;
	        		}
	        		
	        		if(Integer.parseInt(day)<10) {
	        			day = "0" + day;
	        		}
	        		
	        		Day= year+month+day+"";
	        		System.out.println(Day);
	        		dial1.setVisible(false);
	            };
			});
			
		}
		

		//일별 매출 레이아웃
		right_Panel[0].setLayout(new GridLayout(10,1));
		right_DayLabel[0].setFont(font1);
		right_DayLabel[0].setOpaque(true);
		right_DayLabel[0].setBackground(color);
		right_DayLabel[0].setForeground(Color.WHITE);
		right_Panel[0].add(right_DayLabel[0]);
		right_DayButton.setFont(font1);
		right_DayButton.setBackground(color);
		right_DayPanel.add("East",right_DayButton);
		right_Day.setFont(font1);
		right_DayPanel.add(right_Day);
		right_DayPanel.setBackground(Color.WHITE);
		right_Panel[0].add(right_DayPanel);
		right_DayLabel[1].setFont(font1);
		right_DayLabel[1].setOpaque(true);
		right_DayLabel[1].setBackground(color);
		right_DayLabel[1].setForeground(Color.WHITE);
		right_Panel[0].add(right_DayLabel[1]);
		right_DaySales.setFont(font1);
		right_Panel[0].add(right_DaySales);
		right_DayLabel[2].setFont(font1);
		right_DayLabel[2].setOpaque(true);
		right_DayLabel[2].setBackground(color);
		right_DayLabel[2].setForeground(Color.WHITE);
		right_Panel[0].add(right_DayLabel[2]);
		right_DayChange.setFont(font1);
		right_Panel[0].add(right_DayChange);
		
		//일별 매출 아래 버튼
		right_ButtomPanel[0].setLayout(new FlowLayout(FlowLayout.CENTER));
		right_DayCheck.setFont(font1);
		right_DayCencel.setFont(font1);
		right_DayCheck.setBackground(color);
		right_DayCencel.setBackground(color);
		right_ButtomPanel[0].add(right_DayCheck);
		right_ButtomPanel[0].add(right_DayCencel);
		right_DayCheck.addActionListener(this);
		right_DayCencel.addActionListener(this);
		System.out.println("날짜");
		//일별 매출 그래프
		String temp1 ="";
		String temp2 = "";
		Calendar cal = Calendar.getInstance();
		for(int i=6; i>-1; i--) {
			
			
			selectedDay[i] = (cal.get(cal.MONTH)+1)+"월 "+cal.get(cal.DATE) + "" + "일";
			
			if(cal.get(cal.MONTH)+1<10) {
				temp1 = "0"+(cal.get(cal.MONTH)+1);
			}else {
				temp1 = (cal.get(cal.MONTH)+1)+"";
			}
			
			if(cal.get(cal.DATE)<10) {
				temp2 = "0"+cal.get(cal.DATE);
			}else {
				temp2 = cal.get(cal.DATE)+"";
			}
			
			DayData[i] = (cal.get(cal.YEAR)-2000)+temp1+temp2;
			cal.add(Calendar.DATE, -1);
			System.out.println("날짜 "+DayData[i]);
		}
		String str1 = "일별그래프";
		dayChart.inputItem(selectedDay, str1);
		chartdayUpdate(DayData);
		

		//월별 달력
		months = new MonthCalendar();
		dial2 = new Dialog(months);
		right_MonthButton.addActionListener(this);
		for(int i=0; i<12; i++) {
			months.Months[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	String year = months.chyear.getSelectedItem();
	        		JButton btn = (JButton)e.getSource();
	        		String month = btn.getText();
	        		Month= year+"년"+month+"월";
	        		right_Month.setText(Month);
	        		
	        		year = year.substring(2);
	        		if(Integer.parseInt(month)<10) {
	        			month = "0" + month;
	        		}
	        		Month= year+""+month;
	        		
	        		
	        		dial2.setVisible(false);
	            };
			});
			
		}
		
		
		//월별 매출 레이아웃
		right_Panel[2].setLayout(new GridLayout(10,1));
		right_MonthLabel[0].setFont(font1);
		right_MonthLabel[0].setOpaque(true);
		right_MonthLabel[0].setBackground(color);
		right_MonthLabel[0].setForeground(Color.WHITE);
		right_Panel[2].add(right_MonthLabel[0]);
		right_MonthButton.setFont(font1);
		right_MonthButton.setBackground(color);
		right_MonthPanel.add("East",right_MonthButton);
		right_Month.setFont(font1);
		right_MonthPanel.add(right_Month);
		right_MonthPanel.setBackground(Color.WHITE);
		right_Panel[2].add(right_MonthPanel);
		right_MonthLabel[1].setFont(font1);
		right_MonthLabel[1].setOpaque(true);
		right_MonthLabel[1].setBackground(color);
		right_MonthLabel[1].setForeground(Color.WHITE);
		right_Panel[2].add(right_MonthLabel[1]);
		right_MonthSales.setFont(font1);
		right_Panel[2].add(right_MonthSales);
		right_MonthLabel[2].setFont(font1);
		right_MonthLabel[2].setOpaque(true);
		right_MonthLabel[2].setBackground(color);
		right_MonthLabel[2].setForeground(Color.WHITE);
		right_Panel[2].add(right_MonthLabel[2]);
		right_MonthChange.setFont(font1);
		right_Panel[2].add(right_MonthChange);
		
		
		//월별 매출 아래 버튼
		right_ButtomPanel[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		right_MonthCheck.setFont(font1);
		right_MonthCencel.setFont(font1);
		right_MonthCheck.setBackground(color);
		right_MonthCencel.setBackground(color);
		right_ButtomPanel[2].add(right_MonthCheck);
		right_ButtomPanel[2].add(right_MonthCencel);
		right_MonthCheck.addActionListener(this);
		right_MonthCencel.addActionListener(this);
		
		
		
		//월별 매출그래프
		Calendar cal1 = Calendar.getInstance();
		for(int i=6; i>-1; i--) {
			
			selectedMonth[i] = (cal1.get(cal1.YEAR)-2000)+"년 "+(cal1.get(cal1.MONTH)+1) + "월";
			
			if(cal1.get(cal.MONTH)+1<10) {
				temp1 = "0"+(cal1.get(cal.MONTH)+1);
			}else {
				temp1 = (cal1.get(cal.MONTH)+1)+"";
			}
			
			MonthData[i] = (cal1.get(cal.YEAR)-2000)+temp1;
			cal1.add(Calendar.MONTH, -1);
			System.out.println(MonthData[i]);
		}
		String str2 = "월별그래프";
		monthChart.unit = 100000;
		monthChart.inputItem(selectedMonth, str2);
		chartMonthUpdate(MonthData);

	
		
		this.northPanel.setLayout(new BorderLayout());
		this.designPanel[3].add("Center", right_CardPanel);
		this.designPanel[3].add("South",right_CardButtomPanel);
		
	}
	
	



	//액션리스트
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object obj = arg0.getSource();
		
		if(obj == left_Button[0]) {//일별 매출
			right_CardLayout.show(right_CardPanel, right_CardName[0]);
			right_CardButtomLayout.show(right_CardButtomPanel, right_CardButtomName[0]);
		}else if(obj == left_Button[1]) {//일별 매출 그래프
			dial3.setLocation(left_Button[1].getX()*3, left_Button[1].getY());
			chartdayUpdate(DayData);
			dial3.setVisible(true);
		}else if(obj == left_Button[2]) {//월별매출
			right_CardLayout.show(right_CardPanel, right_CardName[2]);
			right_CardButtomLayout.show(right_CardButtomPanel, right_CardButtomName[2]);
		}else if(obj == left_Button[3]) {//월별매출 그래프
			dial4.setLocation(left_Button[3].getX()*3, left_Button[3].getY());
			chartMonthUpdate(MonthData);
			dial4.setVisible(true);
		}else if(obj == right_DayButton) {
			dial1.setLocation(right_DayButton.getX()*2, right_DayButton.getY());
			dial1.setVisible(true);
		}else if(obj == right_MonthButton) {
			dial2.setLocation(right_DayButton.getX()*2, right_DayButton.getY());
			dial2.setVisible(true);
		}else if(obj == right_DayCheck && !right_Day.getText().equals("")) {
			CRUDprocess crud = new CRUDprocess();
			try {
				DaySales = crud.selectSumSaledInfo(Day);
				System.out.println(DaySales);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "해당일의 정보가 존재하지않습니다.");
				System.out.println(DaySales);
			}
			right_DaySales.setText(DaySales + "");
		}else if(obj == right_DayCencel && !right_Day.getText().equals("")) {
			CRUDprocess crud = new CRUDprocess();
			OrderInfo info = new OrderInfo();
			
			
				int nums = Integer.parseInt(right_DayChange.getText());
				try {
				info.setOrder_time(Day);
				info.setOrder_list("변동 가격");
				info.setOrder_price(nums);
				info.setOrder_option("");
				System.out.println(nums);

				int result = crud.changeOrderInfo(info);
				//DB에 삽입
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "변동 되었습니다.");
				} else {
					JOptionPane.showMessageDialog(this, "변동에 실패하였씁니다.");
				}
				} catch (Exception e) {
		        	JOptionPane.showMessageDialog(this, "정보를 올바르게 입력하여주세요");
		        }
	        
		}else if(obj == right_MonthCheck && !right_Month.getText().equals("")) {
			CRUDprocess crud = new CRUDprocess();
			try {
				MonthSales = crud.selectSumSaledInfo(Month);
				System.out.println(MonthSales);
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "해당월의 정보가 존재하지않습니다.");
				System.out.println(MonthSales);
			}
			right_MonthSales.setText(MonthSales + "");
		}else if(obj == right_MonthCencel && !right_Month.getText().equals("")) {
			CRUDprocess crud = new CRUDprocess();
			OrderInfo info = new OrderInfo();
			
			
				int nums = Integer.parseInt(right_MonthChange.getText());
				try {
				info.setOrder_time(Month);
				info.setOrder_list("변동 가격");
				info.setOrder_price(nums);
				info.setOrder_option("");
				System.out.println(nums);

				int result = crud.changeOrderInfo(info);
				//DB에 삽입
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "변동 되었습니다.");
				} else {
					JOptionPane.showMessageDialog(this, "변동에 실패하였씁니다.");
				}
				} catch (Exception e) {
		        	JOptionPane.showMessageDialog(this, "정보를 올바르게 입력하여주세요");
		        }
	        
		}
		
	}
		
	


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}





	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	
	void chartdayUpdate(String[] code) {
		
		CRUDprocess crud = new CRUDprocess();
		dayChart.setTotal0(crud.selectSumSaledInfo(code[0]));
		dayChart.setTotal1(crud.selectSumSaledInfo(code[1]));
		dayChart.setTotal2(crud.selectSumSaledInfo(code[2]));
		dayChart.setTotal3(crud.selectSumSaledInfo(code[3]));
		dayChart.setTotal4(crud.selectSumSaledInfo(code[4]));
		dayChart.setTotal5(crud.selectSumSaledInfo(code[5]));
		dayChart.setTotal6(crud.selectSumSaledInfo(code[6]));
		
	}
	
	void chartMonthUpdate(String[] code) {
		
		CRUDprocess crud = new CRUDprocess();
		monthChart.setTotal0(crud.selectSumSaledInfo(code[0]));
		monthChart.setTotal1(crud.selectSumSaledInfo(code[1]));
		monthChart.setTotal2(crud.selectSumSaledInfo(code[2]));
		monthChart.setTotal3(crud.selectSumSaledInfo(code[3]));
		monthChart.setTotal4(crud.selectSumSaledInfo(code[4]));
		monthChart.setTotal5(crud.selectSumSaledInfo(code[5]));
		monthChart.setTotal6(crud.selectSumSaledInfo(code[6]));
		
	}
}
