package junsProject0530;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CalendarBySwing extends JPanel implements WindowListener, ActionListener, ItemListener{

	Choice chyear, chmonth;
	JLabel yLabel, mLabel;
	GregorianCalendar gc;
	int year, month;
	JLabel[] dayLabel = new JLabel[7];
	 String[] day={"일","월","화","수","목","금","토"};
	JButton[] days = new JButton[42];//7���� 6���̹Ƿ� 42���� ��ư�ʿ�
	 JPanel selectPanel = new JPanel();
	 GridLayout grid = new GridLayout(7,7,5,5);//��,��,����,������
	 Calendar ca = Calendar.getInstance();
	 Dimension dimen, dimen1;
	 int xpos, ypos;
	public CalendarBySwing(){
		this.setLayout(new BorderLayout());

		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int)(dimen.getWidth()/2 - dimen1.getWidth()/2);
		ypos = (int)(dimen.getHeight()/2 - dimen1.getHeight()/2);
		setLocation(xpos, ypos);//ȭ���� ����� ���
		setVisible(true);
		chyear = new Choice(); chmonth = new Choice();
		yLabel = new JLabel("년"); mLabel = new JLabel("월");
		init();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		
	}
	public void init(){
		select();
		calendar();
	}
	
	public void select(){
		JPanel panel = new JPanel(grid);//7�� 7���� �׸��巹�̾ƿ�
		for(int i=2020; i>=2000;i--){
			chyear.add(String.valueOf(i));
		}
		for(int i=1; i <=12; i++){
			chmonth.add(String.valueOf(i));
		}
		for(int i = 0; i < day.length;i++){//���� �̸��� ���̺� ���
			dayLabel[i] = new JLabel(day[i],JLabel.CENTER);
			panel.add(dayLabel[i]);
			dayLabel[i].setBackground(Color.GRAY);//��ǻ� �ǹ̰� ����. �ٲ��� ����.
		}
		dayLabel[6].setForeground(Color.BLUE);//����� ����
		dayLabel[0].setForeground(Color.RED);//�Ͽ��� ����
		for(int i = 0; i < 42;i++){//��� 42���� ��ư�� ����
			days[i] = new JButton("");//������ ���� ��ư ����
			if(i % 7 == 0)
				days[i].setForeground(Color.RED);//�Ͽ��� ��ư�� ��
			else if(i % 7 == 6)
				days[i].setForeground(Color.BLUE);//����� ��ư�� ��
			else
				days[i].setForeground(Color.BLACK);

			panel.add(days[i]);
		}
		selectPanel.add(chyear);
		selectPanel.add(yLabel);
		selectPanel.add(chmonth);
		selectPanel.add(mLabel);
		
		this.add(selectPanel,"North");//������ ���� ������ �� �ִ� ȭ���� ��ܿ� ���
		this.add(panel, "Center");
		
		String m = (ca.get(Calendar.MONTH)+1)+"";
		String y = ca.get(Calendar.YEAR)+"";
		chyear.select(y);
		chmonth.select(m);
		chyear.addItemListener(this);
		chmonth.addItemListener(this);
	}
	public void calendar(){
		year = Integer.parseInt(chyear.getSelectedItem());
		month=Integer.parseInt(chmonth.getSelectedItem());
		gc = new GregorianCalendar(year, month-1, 1);
		int max = gc.getActualMaximum(gc.DAY_OF_MONTH);//�ش� ���� �ִ� �� �� ȹ��
		int week = gc.get(gc.DAY_OF_WEEK);//�ش� ���� ���� ����
//		System.out.println("DAY_OF_WEEK:"+week);
		String today = Integer.toString(ca.get(Calendar.DATE));//���� ��¥ ȹ��
		String today_month = Integer.toString(ca.get(Calendar.MONTH)+1);//������ �� ȹ��
//		System.out.println("today:"+today);
		for(int i = 0; i < days.length; i++){
			days[i].setEnabled(true);
		}
		for(int i = 0; i < week-1; i++){//������ ������ ��ư�� ��Ȱ��ȭ
			days[i].setEnabled(false);
		}
		for(int i = week; i< max+week; i++){
			days[i-1].setText((String.valueOf(i-week+1)));
			days[i-1].setBackground(Color.WHITE);
			if(today_month.equals(String.valueOf(month))){//������ ���� �ް� ���� ���� ���
				if(today.equals(days[i-1].getText())){//��ư�� ��¥�� ���ó�¥�� ��ġ�ϴ� ���
					days[i-1].setBackground(Color.CYAN);//��ư�� ���� ����
				}
			}
		}
		for(int i = (max+week-1); i < days.length; i++){//��¥�� ���� ��ư�� ��Ȱ��ȭ
			days[i].setEnabled(false);
		}
//		System.out.println("max+week:"+(max+week)+",week:"+week);
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		arg0.getWindow().dispose();

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Color color = this.getBackground();
		if(arg0.getStateChange()==ItemEvent.SELECTED){
			for(int i = 0; i < 42; i++){//���̳� ���� ���õǸ� ������ �޷��� ����� ���� �׸���.
				if( !days[i].getText().equals("")){
					days[i].setText("");//������ ��¥�� ����
					days[i].setBackground(color);//�޷��� ������ ������ ������ ��ư�� ������ ������.
				}
			}
			calendar();
		}

	}

}
