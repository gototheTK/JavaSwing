package junsProject0530;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;


class OrderInfoModel extends AbstractTableModel{
	
	private Object[][] tableData; int cols, rows;
	String[] columnName = {"주문 번호", "주문 시각", "주문 항목", "주문 가격", "결제 방법"};
	private List<OrderInfo> list;
	
	OrderInfoModel(){
		CRUDprocess crud = new CRUDprocess();//주문테이블 표시
		list = crud.selectOrderInfo();
		setData();
	}
	
	void setData() {
		Iterator it = list.iterator();
		rows = list.size(); 
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			OrderInfo info = (OrderInfo)it.next();
			tableData[r][0] = info.getOrder_number();//주문번호
			System.out.println(tableData[r][0]);
			tableData[r][1] = info.getOrder_time();//주문시간
			System.out.println(tableData[r][1]);
			tableData[r][2] = info.getOrder_list();//주문리스트
			System.out.println(tableData[r][2]);
			tableData[r][3] = info.getOrder_price();//주문가격
			System.out.println(tableData[r][3]);
			tableData[r][4] = info.getOrder_option();//주문옵션
			System.out.println(tableData[r][4]);
			r++;
		}
	}
	
	@Override
	public String getColumnName(int arg0) {
		return columnName[arg0];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableData[rowIndex][columnIndex];
	}
	
}






public class Employee_OrderCheck extends Content_Panel
	implements MouseListener{


	//생성자
	public Employee_OrderCheck() {
		
		makeLeft();
		makeRight();
		
	}
	
	//왼쪽 내용
	String[] left_ColumnName = {"주문 번호", "주문 시각", "주문 항목", "주문 가격", "결제 방법"};//테이블 항목
	Content_Table orderCheck_Table;//테이블 생성자
	//왼쪽내용
	void makeLeft() {
		orderCheck_Table = new Content_Table();
		orderCheck_Table.table.setModel(new OrderInfoModel());
		orderCheck_Table.table.addMouseListener(this);
		this.addLeftComponent(orderCheck_Table);
	}
	
	void updateLeft() {
		orderCheck_Table.table.setModel(new OrderInfoModel());
	}
	
	
	//오른쪽 내용
	JLabel[] right_Label = new JLabel[5];//오른쪽 항목 라벨
	JLabel[] right_LabelContent = {new JLabel(""), new JLabel(""), new JLabel(""), 
			new JLabel(""), new JLabel(""), new JLabel("")};//오른쪽 컴퍼넌트
	Font font = new Font("Malgun Gothic", Font.BOLD,20);//오른쪽 폰트
	
	
	JPanel[] right_LabelPanel = new JPanel[3];//오른쪽 라벨 판넬
	JPanel right_CenterPanel = new JPanel();//오른쪽 가운데 내용
	JScrollPane right_CoenteScroll = new JScrollPane(right_CenterPanel);
	void makeRight() {
		//레이아웃 재설정 및 판넬 설정
		this.northPanel.setLayout(new BorderLayout());

		//주문번호 및 주문시각 패널
		right_LabelPanel[0] = new JPanel(new GridLayout(4,1));
		right_LabelPanel[0].setBackground(Color.WHITE);
		right_Label[0] = new JLabel("주문 번호");
		right_Label[0].setFont(font);
		right_Label[0].setOpaque(true);
		right_Label[0].setBackground(color);
		right_Label[0].setForeground(Color.WHITE);
		right_LabelContent[0].setFont(font);
		right_LabelPanel[0].add(right_Label[0]);
		right_LabelPanel[0].add(right_LabelContent[0]);
		right_Label[1] = new JLabel("주문 시각");
		right_Label[1].setFont(font);
		right_Label[1].setOpaque(true);
		right_Label[1].setBackground(color);
		right_Label[1].setForeground(Color.WHITE);
		right_LabelContent[1].setFont(font);
		right_LabelPanel[0].add(right_Label[1]);
		right_LabelPanel[0].add(right_LabelContent[1]);
		this.northPanel.add("North", right_LabelPanel[0]);
		
		//주문항목
		right_LabelPanel[1] = new JPanel(new BorderLayout());
		right_LabelPanel[1].setBackground(Color.WHITE);
		right_Label[2] = new JLabel("주문 항목");
		right_Label[2].setFont(font);
		right_Label[2].setOpaque(true);
		right_Label[2].setBackground(color);
		right_Label[2].setForeground(Color.WHITE);
		right_LabelContent[2].setFont(font);
		right_CenterPanel.setLayout(new FlowLayout(0));
		right_CenterPanel.add(right_LabelContent[2]);
		right_LabelPanel[1].add("North", right_Label[2]);
		right_LabelPanel[1].add("Center", right_CoenteScroll);
		right_CenterPanel.setBackground(Color.WHITE);
		right_CoenteScroll.setBorder(new LineBorder(Color.WHITE));
		this.northPanel.add("Center", right_LabelPanel[1]);
		
		
		//주문 가격 및 결제 방법
		right_LabelPanel[2] = new JPanel(new GridLayout(4,1));
		right_LabelPanel[2].setBackground(Color.WHITE);
		right_Label[3] = new JLabel("주문 가격");
		right_Label[3].setFont(font);
		right_Label[3].setOpaque(true);
		right_Label[3].setBackground(color);
		right_Label[3].setForeground(Color.WHITE);
		right_LabelContent[3].setFont(font);
		right_LabelPanel[2].add(right_Label[3]);
		right_LabelPanel[2].add(right_LabelContent[3]);
		right_Label[4] = new JLabel("결제 방법");
		right_Label[4].setFont(font);
		right_Label[4].setOpaque(true);
		right_Label[4].setBackground(color);
		right_Label[4].setForeground(Color.WHITE);
		right_LabelContent[4].setFont(font);
		right_LabelPanel[2].add(right_Label[4]);
		right_LabelPanel[2].add(right_LabelContent[4]);
		this.northPanel.add("South", right_LabelPanel[2]);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int selectedRow = orderCheck_Table.table.getSelectedRow();
		int columnCount = orderCheck_Table.table.getColumnCount();
		for(int i=0; i<columnCount; i++) {
			right_LabelContent[0].setText(orderCheck_Table.table.getValueAt(
					selectedRow, 0) + "");
			right_LabelContent[1].setText(orderCheck_Table.table.getValueAt(
					selectedRow, 1) + "");
			right_LabelContent[2].setText("<html><p style=\"width:350px\">"+orderCheck_Table.table.getValueAt(
					selectedRow, 2)+"</p></html>");
			right_LabelContent[3].setText(orderCheck_Table.table.getValueAt(
					selectedRow, 3) + "");
			right_LabelContent[4].setText(orderCheck_Table.table.getValueAt(
					selectedRow, 4) + "");
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
