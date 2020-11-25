package junsProject0530;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class StaffInfoModel extends AbstractTableModel{
	
	private Object[][] tableData; int cols, rows;
	String[] columnName = {"사원 ID","사원 이름","사원 직급","사원 성별", "사원 연락처"};
	private List<StaffInfo> list;
	
	StaffInfoModel(){
		CRUDprocess crud = new CRUDprocess();//주문테이블 표시
		list = crud.selectStaffInfo();
		setData();
	}
	
	void setData() {
		Iterator it = list.iterator();
		rows = list.size(); 
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			StaffInfo info = (StaffInfo)it.next();
			tableData[r][0] = info.getStaff_id();//사원ID
			System.out.println(tableData[r][0]);
			tableData[r][1] = info.getStaff_name();//사원 이름
			System.out.println(tableData[r][1]);
			tableData[r][2] = info.getStaff_level();//사원 직급
			System.out.println(tableData[r][2]);
			tableData[r][3] = info.getStaff_gender();//사원 성별
			System.out.println(tableData[r][3]);
			tableData[r][4] = info.getStaff_tel();//사원 연락처
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


public class Manager_EmployeeManagement extends Content_Panel
	implements ActionListener, MouseListener{
	
	//왼쪽 패널
	Content_Table left_Table;//테이블 생성자
	
	//오른쪽 패널
	String[] right_LabelName = {"사원 ID", "사원 이름","사원 직급","사원 성별", "사원 연락처", "사원 패스워드"};
	JTextField[] right_Component = {new JTextField(""), new JTextField(""), new JTextField(""),
				new JTextField(""), new JTextField(""), new JTextField("")}; 
	RoundButton right_ModifyButton, right_EnrollButton;//하단버튼
	Font font = new Font("Malgun Gothic", Font.BOLD,20);//오른쪽 폰트
	
	
	
	public Manager_EmployeeManagement() {
		
		makeLest();
		makeRight();
		
		
	}
	
	void updateLeft() {
		left_Table.table.setModel(new StaffInfoModel());
	}
	
	
	void makeLest() {
	
		left_Table = new Content_Table();
		left_Table.table.setModel(new StaffInfoModel());
		left_Table.table.addMouseListener(this);
		this.addLeftComponent(left_Table);
		
	}
	
	
	//오른쪽 내용
	void makeRight() {

				
		this.addRightAllComponent(right_LabelName, right_Component);
		right_ModifyButton = new RoundButton("수정");
		right_ModifyButton.setFont(font1);
		right_ModifyButton.setBackground(color);
		right_ModifyButton.addActionListener(this);
		right_EnrollButton = new RoundButton("등록");
		right_EnrollButton.setFont(font1);
		right_EnrollButton.setBackground(color);
		right_EnrollButton.addActionListener(this);
		this.addRightBottom(right_ModifyButton);
		this.addRightBottom(right_EnrollButton);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object obj = arg0.getSource();
		
		if(obj == right_EnrollButton) {
			
			String code = right_Component[0].getText();
			
			if(code.equals("")) {
				JOptionPane.showMessageDialog(this, "아이디는 반드시 입력해야 합니다.");
			}else {
			
			CRUDprocess crud = new CRUDprocess();
			StaffInfo info = crud.selectStaffCode(code);
			
			if(info==null) {
				info = new StaffInfo();
				info.setStaff_id(right_Component[0].getText());
				info.setStaff_name(right_Component[1].getText());
				info.setStaff_level(right_Component[2].getText());
				info.setStaff_gender(right_Component[3].getText());
				info.setStaff_tel(right_Component[4].getText());
				info.setStaff_password(right_Component[5].getText());
				int result = crud.insertStaffInfo(info);
				//DB에 삽입
				if(result > 0) {
					JOptionPane.showMessageDialog(this, "등록이 완료되었습니다");
				} else {
					JOptionPane.showMessageDialog(this, "등록에 실패하였습니다.");
				}
				left_Table.table.setModel(new StaffInfoModel());
			}else {
				JOptionPane.showMessageDialog(this, "해당 아디니는 이미 존재합니다.");
			}
			right_Component[5].setText("");
			}
		}else if(obj == right_ModifyButton) {
			CRUDprocess crud = new CRUDprocess();
			StaffInfo info = new StaffInfo();
			
			info.setStaff_id(right_Component[0].getText());
			info.setStaff_name(right_Component[1].getText());
			info.setStaff_level(right_Component[2].getText());
			info.setStaff_gender(right_Component[3].getText());
			info.setStaff_tel(right_Component[4].getText());
			info.setStaff_password(right_Component[5].getText());
			int result = crud.updateStaffInfo(info);
			//DB에 삽입
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "수정이 완료되었습니다");
			} else {
				JOptionPane.showMessageDialog(this, "수정에 실패하였습니다.");
			}
			left_Table.table.setModel(new StaffInfoModel());
		}
		right_Component[5].setText("");
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int selectedRow = left_Table.table.getSelectedRow();
		
		right_Component[0].setText(left_Table.table.getValueAt(
				selectedRow, 0) +"");
		right_Component[1].setText(left_Table.table.getValueAt(
					selectedRow, 1) + "");
		right_Component[2].setText(left_Table.table.getValueAt(
					selectedRow, 2) + "");
		right_Component[3].setText(left_Table.table.getValueAt(
					selectedRow, 3) + "");
		right_Component[4].setText(left_Table.table.getValueAt(
				selectedRow, 4) + "");
		right_Component[5].setText("");
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
