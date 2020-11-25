package junsProject0530;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class NoticeInfoModel extends AbstractTableModel{
	
	private Object[][] tableData; int cols, rows;
	String[] columnName = {"공자사항 번호","공지사항 작성일","공지사항 제목","공지사항 내용"};
	private List<NoticeInfo> list;
	
	NoticeInfoModel(){
		CRUDprocess crud = new CRUDprocess();//주문테이블 표시
		list = crud.selectNoticeInfo();
		setData();
	}
	
	void setData() {
		Iterator it = list.iterator();
		rows = list.size(); 
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			NoticeInfo info = (NoticeInfo)it.next();
			tableData[r][0] = info.notice_number;//공지사항번호
			System.out.println(tableData[r][0]);
			tableData[r][1] = info.notice_date;//공지사항날짜
			System.out.println(tableData[r][1]);
			tableData[r][2] = info.notice_title;//공지사항제목
			System.out.println(tableData[r][2]);
			tableData[r][3] = info.notice_contents;//공지사항내용
			System.out.println(tableData[r][3]);
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


public class Manager_NoticeManagement extends Content_Panel implements 
		ActionListener, MouseListener{
	
	//왼쪽화면
	Content_Table left_Table;
	
	
	//오른쪽 화면
	String[] right_LabelName = {"공지사항 작성일","공지사항 제목","공지사항 내용"};
	JLabel[] right_Label = new JLabel[3];
	JTextField labelTextField = new JTextField();
	JLabel labelDate = new JLabel("글작성가능");
	JTextArea labeTextArea = new JTextArea(50, 10);
	JScrollPane TextScroll = new JScrollPane(labeTextArea);
	JPanel[] rightLabelPanel = new JPanel[2];
	RoundButton emptyButton = new RoundButton("비우기");
	RoundButton right_EnrollButton, right_ModifyButton, right_DeleteButton;//하단버튼
	Font font = new Font("Malgun Gothic", Font.BOLD,20);//오른쪽 폰트
	int number = 0;
	String date = "글작성가능";
	String time = "";
	String title = "";//타이틀
	String content = "";//내용
	
	//생성자
	public Manager_NoticeManagement() {
		
		makeLeft();
		makeRight();
		
	}
	
	//왼쪽 내용
	void makeLeft() {
		left_Table = new Content_Table();
		left_Table.table.setModel(new NoticeInfoModel());
		left_Table.table.addMouseListener(this);
		this.addLeftComponent(left_Table);
	}

	
	//오른쪽 내용
	void makeRight() {
		

		//오른쪽 내용에 필요한 패널을 생성
		rightLabelPanel[0] = new JPanel(new GridLayout(4,1));
		rightLabelPanel[0].setBackground(Color.WHITE);
		rightLabelPanel[1] = new JPanel(new BorderLayout());
		rightLabelPanel[1].setBackground(Color.WHITE);
		
		
		//오른쪽 내용
		//공지사항 작성일
		right_Label[0] = new JLabel("공지사항 작성일");
		right_Label[0].setFont(font1);
		right_Label[0].setOpaque(true);
		right_Label[0].setBackground(color);
		right_Label[0].setForeground(Color.WHITE);
		rightLabelPanel[0].add(right_Label[0]);
		rightLabelPanel[0].add(labelDate);
		labelDate.setBackground(Color.WHITE);
		labelDate.setFont(font);
		
		//공지사항 제목
		right_Label[1] = new JLabel("공지사항 제목");
		right_Label[1].setFont(font1);
		right_Label[1].setOpaque(true);
		right_Label[1].setBackground(color);
		right_Label[1].setForeground(Color.WHITE);
		rightLabelPanel[0].add(right_Label[1]);
		rightLabelPanel[0].add(labelTextField);
		labelTextField.setBackground(Color.WHITE);
		labelTextField.setFont(font1);
		labelTextField.setText(title);
		
		
		//공지사항 내용
		right_Label[2] = new JLabel("공지사항 내용");
		right_Label[2].setFont(font1);
		right_Label[2].setOpaque(true);
		right_Label[2].setBackground(color);
		right_Label[2].setForeground(Color.white);
		rightLabelPanel[1].add("North", right_Label[2]);
		labeTextArea.setFont(font1);
		labeTextArea.setText(content);
		rightLabelPanel[1].add("Center", TextScroll);
		
		//비우기버튼
		emptyButton.setBackground(Color.WHITE);
		emptyButton.setBackground(color);
		emptyButton.setFont(font1);
		emptyButton.addActionListener(this);
		
		
		this.northPanel.setLayout(new BorderLayout());
		this.northPanel.add("North",rightLabelPanel[0]);
		this.northPanel.add("Center",rightLabelPanel[1]);
		this.northPanel.add("South", emptyButton);
		
		
		right_EnrollButton = new RoundButton("등록");
		right_EnrollButton.setFont(font1);
		right_EnrollButton.addActionListener(this);
		right_ModifyButton = new RoundButton("수정");
		right_ModifyButton.setFont(font1);
		right_ModifyButton.addActionListener(this);
		right_DeleteButton = new RoundButton("삭제");
		right_DeleteButton.setFont(font1);
		right_DeleteButton.addActionListener(this);
		
		
		this.southPanel.add(right_EnrollButton);
		this.southPanel.add(right_ModifyButton);
		this.southPanel.add(right_DeleteButton);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == right_EnrollButton && labelDate.getText().equals(date)) {
			CRUDprocess crud = new CRUDprocess();
			NoticeInfo info = new NoticeInfo();
			title = labelTextField.getText();
			content = labeTextArea.getText();
			info.setNotice_title(title);
			info.setNotice_contents(content);
			int result = crud.insertNoticeInfo(info);
			//DB에 삽입
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "글이 등록되었습니다.");
				left_Table.table.setModel(new NoticeInfoModel());
			} else {
				JOptionPane.showMessageDialog(this, "글의 등록이 실패하였습니다.");
			}
		}else if(obj == right_ModifyButton) {
			CRUDprocess crud = new CRUDprocess();
			NoticeInfo info = new NoticeInfo();
			title = labelTextField.getText();
			content = labeTextArea.getText();
			info.setNotice_number(number);
			info.setNotice_title(title);
			info.setNotice_contents(content);
			int result = crud.updateNoticeInfo(info);
			//DB에 삽입
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "글이 수정되었습니다.");
				left_Table.table.setModel(new NoticeInfoModel());
			} else {
				JOptionPane.showMessageDialog(this, "글의 수정에 실패하였습니다.");
			}
		}else if(obj == right_DeleteButton && number>0) {
			CRUDprocess crud = new CRUDprocess();
			NoticeInfo info = new NoticeInfo();
			info.setNotice_number(number);
			int result = crud.deleteNoticeInfo(info.getNotice_number());
			//DB에 삽입
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "글이 삭제되었습니다.");
				left_Table.table.setModel(new NoticeInfoModel());
			} else {
				JOptionPane.showMessageDialog(this, "글의 삭제에 실패하였습니다.");
			}
			
		}else if(obj == emptyButton) {
			labelDate.setText(date);
			labelTextField.setText("");
			labeTextArea.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectedRow = left_Table.table.getSelectedRow();
		
		number = (int) left_Table.table.getValueAt(
				selectedRow, 0);
		labelDate.setText(left_Table.table.getValueAt(
					selectedRow, 1) + "");
		labelTextField.setText(left_Table.table.getValueAt(
					selectedRow, 2) + "");
		labeTextArea.setText(left_Table.table.getValueAt(
					selectedRow, 3) + "");
			
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
}
