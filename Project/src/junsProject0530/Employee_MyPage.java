package junsProject0530;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class Employee_NoticeInfoModel extends AbstractTableModel{
	
	private Object[][] tableData; int cols, rows;
	String[] columnName = {"공자사항 번호","공지사항 작성일","공지사항 제목","공지사항 내용"};
	private List<NoticeInfo> list;
	
	Employee_NoticeInfoModel(){
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


public class Employee_MyPage extends Content_Panel implements 
	ActionListener, MouseListener{
	
	//왼쪽 내용
	Content_Table left_Table;
	
	//오른쪽 내용
	String[] right_LabelName = {"사원 ID","사원 이름","사원 직급","사원 성별"};//오른쪽 항목 라벨
	JLabel[] right_Componet = new JLabel[4];//오른쪽 컴퍼넌트
	JTextField right_Componet2 = new JTextField();
	RoundButton right_ModifyButton, right_EnrollButton;//하단버튼
	Font font = new Font("Malgun Gothic", Font.BOLD,20);//오른쪽 폰트
	String staff_id = "";
	String staff_name = "";
	String staff_level = "";
	String staff_gender = "";
	String staff_tel = "";
	
	//생성자
	public Employee_MyPage() {
		
		makeLeft();
		makeRight();
		
	}
	
	//왼쪽 내용
	void makeLeft() {
		left_Table = new Content_Table();
		left_Table.table.setModel(new Employee_NoticeInfoModel());
		left_Table.table.addMouseListener(this);
		this.addLeftComponent(left_Table);
	
	}
	
	//오른쪽 내용
	void makeRight() {
		
		right_Componet[0]= new JLabel();
		right_Componet[0].setFont(font1);
		right_Componet[1] = new JLabel();
		right_Componet[1].setFont(font1);
		right_Componet[2] = new JLabel();
		right_Componet[2].setFont(font1);
		right_Componet[3] = new JLabel();
		right_Componet[3].setFont(font1);
		right_Componet2.setFont(font1);
		

		//하단 버튼
		right_ModifyButton =new RoundButton("수정");
		right_ModifyButton.setFont(font1);
		right_ModifyButton.setBackground(color);
		right_ModifyButton.addActionListener(this);

		
		this.addRightAllComponent(right_LabelName, right_Componet);
		this.addRightComponent("사원 연락처", right_Componet2);
		this.addRightBottom(right_ModifyButton);
	}
	
	void updateLeft() {
		left_Table.table.setModel(new Employee_NoticeInfoModel());


		right_Componet[0].setText(staff_id);
		right_Componet[0].setFont(font1);
		right_Componet[1].setText(staff_name);
		right_Componet[1].setFont(font1);
		right_Componet[2].setText(staff_level);
		right_Componet[2].setFont(font1);
		right_Componet[3].setText(staff_gender);
		right_Componet[3].setFont(font1);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectedRow = left_Table.table.getSelectedRow();
		
		JOptionPane.showMessageDialog(this,left_Table.table.getValueAt(
						selectedRow, 3) );

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		

		
		if(obj == right_ModifyButton) {
			CRUDprocess crud = new CRUDprocess();
			StaffInfo info = new StaffInfo();
			info.setStaff_id(staff_id);
			info.setStaff_name(staff_name);
			info.setStaff_level(staff_level);
			info.setStaff_gender(staff_gender);
			info.setStaff_tel(staff_tel);
			int result = crud.updateStaffInfo2(info);
			//DB에 삽입
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "수정이 완료되었습니다");
			} else {
				JOptionPane.showMessageDialog(this, "수정에 실패하였습니다.");
			}
			
		}
	}

}
