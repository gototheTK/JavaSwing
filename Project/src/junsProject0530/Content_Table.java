package junsProject0530;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Content_Table extends JPanel {
	JTable table;
	JPanel panel; 
	JScrollPane scroll;
	
	public Content_Table() {
		table = new JTable();//테이블생성
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setPreferredSize(new Dimension(460, 510));
		panel = new JPanel();
		panel.add(scroll);
	
		this.add(panel);
		this.setBackground(Color.WHITE);
	}
	
}
