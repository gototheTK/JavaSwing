package junsProject0530;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BarchartSystem extends JPanel
	implements ActionListener{
	
	String[] items = {"", "", "", "", "", "", ""};
	String name = "매출";
	int unit = 10000;
	String unitstr = "단위 : "+"원";
	Font font2 = new Font("Malgun Gothic", Font.BOLD, 15);
	Font font3 = new Font("Malgun Gothic", Font.BOLD, 20);
	Font font4 = new Font("Malgun Gothic", Font.BOLD, 50);
	private int total0 =0, total1=20, total2=40, total3=60;
	private int total4=30, total5=50, total6=70;



	@Override
	public void paint(Graphics g) {
		unitstr = "단위 : "+unit+"원";
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		//그려질 영역을 지운다. (0,0)에서 (패널의 가로길이,패널의 세로길이)	
		g.setColor(Color.BLACK);//막대그래프의 색지정
		g.drawLine(50, 250, 750, 250);//수평선을 그린다.
		g.setFont(font2);
		for(int cnt=1; cnt<11; cnt++) {
			g.drawString(cnt*10+"", 25, 255-20*cnt);
			g.drawLine(50, 250-20*cnt, 750, 250-20*cnt);
		}//10점부터100점까지 점수와 수평선을 그린다.(10회 반복)
		g.drawLine(50, 20, 50, 250);//수직선을 그린다.
		g.drawString(unitstr, 25, 290);
		g.setFont(font3);
		g.drawString(items[0], 70, 270);
		g.drawString(items[1], 180, 270);
		g.drawString(items[2], 280, 270);
		g.drawString(items[3], 380, 270);
		g.drawString(items[4], 480, 270);
		g.drawString(items[5], 580, 270);
		g.drawString(items[6], 680, 270);
		g.setFont(font4);
		g.drawString(name, 250, 330);
		
		g.setFont(font2);
		
		g.setColor(Color.RED);//막대그래프의 색지정
		if(total0 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(110, 250-total0*2, 10, total0*2);
			g.drawString(total0+"", 105, 240-total0*2);
		}
		if(total1 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(210, 250-total1*2, 10, total1*2);
			g.drawString(total1+"", 205, 240-total1*2);
		}
		if(total2 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(310, 250-total2*2, 10, total2*2);
			g.drawString(total2+"", 305, 240-total2*2);
		}
		if(total3 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(410, 250-total3*2, 10, total3*2);
			g.drawString(total3+"", 405, 240-total3*2);
		}
		if(total4 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(510, 250-total4*2, 10, total4*2);
			g.drawString(total4+"", 505, 240-total4*2);
		}
		if(total5 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(610, 250-total5*2, 10, total5*2);
			g.drawString(total5+"", 605, 240-total5*2);
		}
		if(total6 > 0) {//값이 있는 경우에만 그래프를 그림
			g.fillRect(710, 250-total6*2, 10, total6*2);
			g.drawString(total6+"", 705, 240-total6*2);
		}
		
		
		
	}

	
	public int getTotal0() {
		return total0;
	}


	public void setTotal0(int total0) {
		this.total0 = (total0/unit);
	}


	public int getTotal1() {
		return total1;
	}


	public void setTotal1(int total1) {
		this.total1 = (total1/unit);
	}


	public int getTotal2() {
		return total2;
	}


	public void setTotal2(int total2) {
		this.total2 = (total2/unit);
	}


	public int getTotal3() {
		return total3;
	}


	public void setTotal3(int total3) {
		this.total3 = (total3/unit);
	}


	public int getTotal4() {
		return total4;
	}


	public void setTotal4(int total4) {
		this.total4 = (total4/unit);
	}


	public int getTotal5() {
		return total5;
	}


	public void setTotal5(int total5) {
		this.total5 = (total5/unit);
	}


	public int getTotal6() {
		return total6;
	}


	public void setTotal6(int total6) {
		this.total6 = (total6/unit);
	}


	public BarchartSystem() {
		
	}
	
	
	
	void inputItem(String[] item, String title) {
		for(int i=0; i<7; i++) {
			this.items[i] = item[i];
		}
		this.name = title;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}







