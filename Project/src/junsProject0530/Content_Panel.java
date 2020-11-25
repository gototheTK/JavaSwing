package junsProject0530;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Content_Panel extends JPanel{
	

	JPanel rightPanel;
	JPanel leftPanel;
	JPanel leftConentPanel;
	JPanel[] designPanel;
	JPanel northPanel;
	JPanel southPanel;
	Font font1 = new Font("Malgun Gothic", Font.BOLD, 18);
	Font font2 = new Font("Malgun Gothic", Font.BOLD, 15);
	Font font3 = new Font("Malgun Gothic", Font.BOLD, 20);
	Color color = new Color(237, 37, 66, 255);
	Border InputBorder = BorderFactory.createLineBorder(Color.WHITE);//입력필드 테두리색깔
	
	public Content_Panel() {
		this.setLayout(new GridLayout(1,2,10,0));
		
		makeLefttPanel();
		makeRightPanel();
		connectPanel();
			
	}
	
	public void addRightAllComponent(String[] str, JComponent[] component) {//오른쪽 위쪽 내용 채우기
		
		for(int i=0; i<str.length; i++) {
			JLabel label1 = new JLabel(str[i]);
			label1.setOpaque(true);
			label1.setBackground(color);
			label1.setForeground(Color.WHITE);
			label1.setFont(font1);
			component[i].setFont(font1);
			northPanel.add(label1);
			northPanel.add(component[i]);
		}
		
	}
	
	
	public void addLeftComponent(Component component) {//왼쪽 내용 채우기
		
		leftConentPanel.add("Center", component);
	}
	
	
	public void addRightComponent(String str, Component component) {//오른쪽 위쪽 내용 채우기
		JLabel label1 = new JLabel(str);
		label1.setOpaque(true);
		label1.setBackground(color);
		label1.setForeground(Color.WHITE);
		label1.setFont(font1);
		northPanel.add(label1);
		northPanel.add(component);
	}
	
	
	public void addRightBottom(Component component) {//오른쪽 아래쪽 버튼 채우기
		southPanel.add(component);
		
	}
	
	
	private void connectPanel() {
		// TODO Auto-generated method stub
		this.add(leftPanel);
		this.add(rightPanel);
	}
	
	
	private void makeLefttPanel() {
		// TODO Auto-generated method stub
		leftPanel = new JPanel(new BorderLayout());
		leftConentPanel = new JPanel(new BorderLayout());
		leftPanel.add("Center", leftConentPanel);
		leftConentPanel.setBackground(Color.WHITE);
		leftConentPanel.add("North", new JLabel("          "));
		leftConentPanel.add("East", new JLabel("      "));
		leftConentPanel.add("West", new JLabel("      "));
		leftConentPanel.add("South", new JLabel("          "));
	}

	
	private void makeRightPanel() {
		// TODO Auto-generated method stub
		//판넬 생성
		rightPanel= new JPanel(new BorderLayout());
		designPanel = new JPanel[4];//판넬 생성
		northPanel = new JPanel(new GridLayout(12,1));//위쪽 내용
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));//아래쪽 내용
		

		for(int i=0; i<4; i++) {
			if(i==3)
				designPanel[i] = new JPanel(new BorderLayout(0,10));//가운데 빨간줄을 위한
			else	
				designPanel[i] = new JPanel(new BorderLayout());
		}
		
		
		//판넬 배경지정
		designPanel[0].setBackground(Color.WHITE);
		designPanel[1].setBackground(color);
		designPanel[2].setBackground(Color.WHITE);
		designPanel[3].setBackground(color);
		northPanel.setBackground(Color.white);
		southPanel.setBackground(Color.white);
		
		//판넬에 판넬 추가
		for(int i=0; i<3; i++) {
			designPanel[i].add("North", new JLabel("          "));
			designPanel[i].add("East", new JLabel("      "));
			designPanel[i].add("West", new JLabel("      "));
			designPanel[i].add("South", new JLabel("          "));
			designPanel[i].add(designPanel[i+1]);
		}
		
		designPanel[3].add("Center", northPanel);
		designPanel[3].add("South", southPanel);
		rightPanel.add(designPanel[0]);
	}


}
