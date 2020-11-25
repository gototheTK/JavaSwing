package junsProject0530;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

class StockInfoModel extends AbstractTableModel{
	
	private Object[][] tableData; int cols, rows;
	String[] columnName = {"재고 번호","재고 명","재고 용량", "재고 단위","사용 단위"};
	private List<StockInfo> list;
	
	StockInfoModel(){
		CRUDprocess crud = new CRUDprocess();//주문테이블 표시
		list = crud.selectStockInfo();
		setData();
	}
	
	void setData() {
		Iterator it = list.iterator();
		rows = list.size(); 
		cols = columnName.length;
		tableData = new Object[rows][cols];
		int r = 0;
		while(it.hasNext()) {
			StockInfo info = (StockInfo)it.next();
			tableData[r][0] = info.getStock_number();//재고번호
			System.out.println(tableData[r][0]);
			tableData[r][1] = info.getStock_name();//재고항날짜
			System.out.println(tableData[r][1]);
			tableData[r][2] = info.getStock_volume();//재고제목
			System.out.println(tableData[r][2]);
			tableData[r][3] = info.getStock_unit();//공지사항내용
			System.out.println(tableData[r][3]);
			tableData[r][4] = info.getStock_consume();//공지사항내용
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

public class Manager_StockManagement extends Content_Panel implements 
	ActionListener, MouseListener{
	
	//왼쪽 패널
	String[] left_ColumnName = {"재고 번호","재고 명","재고 용량","재고 단위","사용 단위"};//테이블 항목
	Content_Table left_Table;//테이블 생성자
	
	//오른쪽 패널
	String[ ] right_LabelName={"재고 번호","재고명","재고 용량","재고 단위","사용 단위"};//오른쪽 항목 라벨
	JTextField[] right_Componet = {new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField()};//오른쪽 컴터넌트
	RoundButton right_CheckButton, right_ModifyButton;//하단버튼
	Font font = new Font("Malgun Gothic", Font.BOLD,20);//오른쪽 폰트

	//생성자
	public Manager_StockManagement() {
		makeLeft();
		makeRight();
	}
	
	//왼쪽 내용
	void makeLeft() {
		
		left_Table = new Content_Table();
		left_Table.table.setModel(new StockInfoModel());
		left_Table.table.addMouseListener(this);
		this.addLeftComponent(left_Table);
	
	}
	
	void updateLeft() {
		left_Table.table.setModel(new StockInfoModel());
	}
	
	//오른쪽 패널
	void makeRight() {
		
		this.addRightAllComponent(right_LabelName, right_Componet);
		right_ModifyButton = new RoundButton("수정");
		right_ModifyButton.setFont(font1);
		right_ModifyButton.setBackground(color);
		right_ModifyButton.addActionListener(this);
		this.addRightBottom(right_ModifyButton);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int selectedRow = left_Table.table.getSelectedRow();
		
		right_Componet[0].setText(left_Table.table.getValueAt(
				selectedRow, 0)+ ""); 
		right_Componet[1].setText(left_Table.table.getValueAt(
					selectedRow, 1) + "");
		right_Componet[2].setText(left_Table.table.getValueAt(
					selectedRow, 2) + "");
		right_Componet[3].setText(left_Table.table.getValueAt(
					selectedRow, 3) + "");
		right_Componet[4].setText(left_Table.table.getValueAt(
				selectedRow, 4) + "");
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == right_ModifyButton) {
			CRUDprocess curd = new CRUDprocess();
			StockInfo info = new StockInfo();
			info.setStock_number(Integer.parseInt(right_Componet[0].getText()));
			info.setStock_name(right_Componet[1].getText());
			info.setStock_volume(Integer.parseInt(right_Componet[2].getText()));
			info.setStock_unit(right_Componet[3].getText());
			info.setStock_consume(Integer.parseInt(right_Componet[4].getText()));
			int result = curd.updateStockInfo(info);
			//DB에 삽입
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "재고가 수정되었습니다.");
				left_Table.table.setModel(new StockInfoModel());
			} else {
				JOptionPane.showMessageDialog(this, "재고 수정에에 실패하였습니다.");
			}
		}
		
	}

}
