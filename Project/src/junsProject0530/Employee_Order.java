package junsProject0530;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;




public class Employee_Order extends Content_Panel implements ActionListener{
	
		//생성자
		public Employee_Order() {
			
			makeleft_Order();
			makeOrder_Right();

		}
		
		
		//오른쪽 화면
		//오른쪽 가격 및 주문내역 레이블
		String[] right_LabelName = {"메뉴주문 항목","메뉴주문 총 가격","메뉴주문 결제방법"};
		JLabel[] right_Label = new JLabel[3];
		JLabel right_OrderLabel = new JLabel(" ");//주문 내역
		JLabel right_PriceLabel = new JLabel("0");//주문 가격
		//오른쪽 필요한 패널 생성자
		JPanel right_OrderCenterPanel = new JPanel();
		JScrollPane right_OrderCoenteScroll = new JScrollPane(right_OrderCenterPanel);
		JPanel right_OrderSouthPanel = new JPanel(new GridLayout(4,1));
		JPanel right_OrdarRadioPanel = new JPanel(new GridLayout(1,2));
		ButtonGroup right_OrderRadioGroup;//라디오버튼
		JRadioButton[] right_OrderRadioGroupButton;
		RoundButton right_OrderButton;//하단버튼
		RoundButton right_CancelButton;
		public void makeOrder_Right() {
			

			//오른쪽 패널생성
			//레이아웃 설정
			this.northPanel.setLayout(new BorderLayout());
			this.northPanel.setBackground(Color.WHITE);
			right_OrderCenterPanel.setLayout(new FlowLayout(0));
			right_OrderCenterPanel.setBackground(Color.WHITE);
			right_OrderSouthPanel.setBackground(Color.WHITE);
			right_OrdarRadioPanel.setBackground(Color.WHITE);
			right_OrderCoenteScroll.setBorder(new LineBorder(Color.WHITE));


			
			//내용 채우기
			//주문내역 채우기
			//주문내역
			right_Label[0] = new JLabel(right_LabelName[0]);
			right_Label[0].setFont(font3);
			right_Label[0].setOpaque(true);
			right_Label[0].setBackground(color);
			right_Label[0].setForeground(Color.WHITE);
			right_OrderLabel.setFont(font1);
			right_OrderCenterPanel.add(right_OrderLabel);
			this.northPanel.add("North", right_Label[0]);
			this.northPanel.add("Center", right_OrderCoenteScroll);
			
			
			

			//주문가격
			right_Label[1] = new JLabel(right_LabelName[1]);
			right_Label[1].setFont(font1);
			right_Label[1].setOpaque(true);
			right_Label[1].setBackground(color);
			right_Label[1].setForeground(Color.WHITE);
			right_PriceLabel.setFont(font1);
			right_OrderSouthPanel.add(right_Label[1]);
			right_OrderSouthPanel.add(right_PriceLabel);
			
			
			//결제선택방법
			//레이블생성
			right_Label[2] = new JLabel(right_LabelName[2]);
			right_Label[2].setFont(font1);
			right_Label[2].setOpaque(true);
			right_Label[2].setBackground(color);
			right_Label[2].setForeground(Color.WHITE);
			//라디오버튼생성
			right_OrderRadioGroup = new ButtonGroup();//메뉴주문결제
			right_OrderRadioGroupButton = new JRadioButton[2];
			right_OrderRadioGroupButton[0] = new JRadioButton("카드");
			right_OrderRadioGroupButton[0].setBackground(Color.WHITE);
			right_OrderRadioGroupButton[0].setFont(font1);
			right_OrderRadioGroupButton[1] = new JRadioButton("현금");
			right_OrderRadioGroupButton[1].setBackground(Color.WHITE);
			right_OrderRadioGroupButton[1].setFont(font1);
			
			//라디오버튼 그룹에 붙이기
			right_OrderRadioGroup.add(right_OrderRadioGroupButton[0]);
			right_OrderRadioGroup.add(right_OrderRadioGroupButton[1]);
			
			//라디오버튼 붙이기
			right_OrdarRadioPanel.add(right_OrderRadioGroupButton[0]);
			right_OrdarRadioPanel.add(right_OrderRadioGroupButton[1]);
			right_OrderSouthPanel.add(right_Label[2]);
			right_OrderSouthPanel.add(right_OrdarRadioPanel);
			
			
			//가격,결제선택방법 붙이기
			this.northPanel.add("South", right_OrderSouthPanel);
			
			
			//하단 버튼 만들기
			right_OrderButton = new RoundButton("주문");
			right_OrderButton.addActionListener(this);
			right_OrderButton.setFont(font1);
			right_OrderButton.setBackground(Color.RED);
			right_CancelButton = new RoundButton("취소");
			right_CancelButton.addActionListener(this);
			right_CancelButton.setFont(font1);
			right_CancelButton.setBackground(Color.RED);
			this.addRightBottom(right_OrderButton);
			this.addRightBottom(right_CancelButton);
			
		}
		
		
		
		//왼쪽 패널
		JPanel left_Order;//왼쪽 패널
		JPanel left_OrderCardContent;//왼쪽 내용 패널
		CardLayout left_OrderCard;//왼쪽 내용 전환패널
		JPanel left_OrderLeftMenu;//왼쪽 메뉴패널
		
		RoundButton[] left_OrderLeftMenuButton;//왼쪽 메뉴버튼들
		
		JScrollPane left_BurgurScroll;//햄버거스크롤
		JPanel left_BurgurPanel;//햄버거메뉴패널
		
		JScrollPane left_ChikenScroll;
		JPanel left_ChikenPanel;//치킨메뉴패널
		
		JScrollPane left_DrinkScroll;
		JPanel left_DrinkPanel;//음료메뉴패널
		
		RoundButton[][] left_MenuButton;
		String[][] left_MenuLabel = {	//햄버거메뉴0~8
										{"디럭스버거", "3500"}, 
										{"마살라버거", "3500"},
										{"불싸이버거", "3000"},
										{"싸이버거", "3000"},
										{"언빌리버블버거", "3500"},
										{"인크레더블버거", "3500"},
										{"할라피뇨통살버거", "4000"},
										{"화이트갈릭버거", "4000"},
										{"휠렛버거", "4000"},
										//치킨메뉴9~13
										{"마살라치킨", "12000"}, 
										{"불사치킨", "13000"},
										{"양념치킨", "12000"},
										{"조청치킨", "13000"},
										{"치파오치킨", "13000"},
										{"후라이드치킨", "11000"},
										//음료메뉴9~14
										{"라떼", "2500"}, 
										{"레몬티", "2500"},
										{"아메리카노", "2000"},
										{"오렌지쥬스", "2000"},
										{"청포도에이드", "3500"},
										{"콜라", "2000"},
										{"큐브치즈빙수", "3000"},
										{"큐브치즈빙수컵", "4000"},
										{"홍차", "3500"},
		};
		
		
		LinkedHashMap<String , int[][]> MenuMap = new LinkedHashMap<String, int[][]>();
		public void setMenu() {
			MenuMap.put("디럭스버거", new int[][] {{101,1}, {102, 2}, {103, 1}, {104, 1}, {105, 2}, {106, 3}});
			MenuMap.put("마살라버거", new int[][] {{101,1}, {102, 1}, {104, 1} , {105, 2}});
			MenuMap.put("불싸이버거", new int[][] {{101,1}, {102, 1}, {103, 1}, {105, 2}, {106, 1}});
			MenuMap.put("싸이버거", new int[][] {{101,1}, {102, 1}, {103, 1}, {105, 2}, {106, 3}, {108, 1}});
			MenuMap.put("언빌리버블버거", new int[][] {{101,1}, {102, 2}, {103, 1}, {104, 1}, {105, 3}, {106, 5}, {107, 2}, {108, 2}, {109, 3}});
			MenuMap.put("인크레더블버거", new int[][] {{101,1}, {102, 2}, {103, 2}, {104, 2}, {105, 3}, {106, 4}, {107, 2},{108, 1}, {109, 2}});
			MenuMap.put("할라피뇨통살버거", new int[][] {{101,1}, {102, 2}, {103, 2}, {104, 3}, {105, 2}, {106, 5}, {108, 2}});
			MenuMap.put("화이트갈릭버거", new int[][] {{101,1}, {102, 1}, {103, 1}, {104, 2}, {105, 3}, {106, 2}, {107, 3}, {108, 1}, {109, 5}});
			MenuMap.put("휠렛버거", new int[][] {{101,1}, {102, 2}, {103, 2}, {104, 2}, {105, 2}, {106, 3}, {109, 2}});
			MenuMap.put("치파오치킨", new int[][] {{201,1}, {202, 1}, {209, 1}});
			MenuMap.put("마살라치킨", new int[][] {{201,1}, {203, 1}});
			MenuMap.put("불사치킨", new int[][] {{201,1}, {204, 1}, {208, 1}});
			MenuMap.put("양념치킨", new int[][] {{201,1}, {205, 1}, {207, 1}});
			MenuMap.put("후라이드치킨", new int[][] {{201,1}, {205, 1}, {208, 1}});
			MenuMap.put("조청치킨", new int[][] {{201,1}, {205, 1}, {207, 1}});
			MenuMap.put("라떼", new int[][] {{301,2}, {302, 5}});
			MenuMap.put("레몬티", new int[][] {{303,3}});
			MenuMap.put("아메리카노", new int[][] {{301,2}});
			MenuMap.put("오렌지주스", new int[][] {{304,3}});
			MenuMap.put("큐브치즈빙수", new int[][] {{302,1}, {305, 3}, {306, 2}, {307, 3}, {308, 3}});
			MenuMap.put("큐브치즈빙수컵", new int[][] {{302,1}, {305, 1}, {306, 1}, {307, 1}, {308, 1}});
			MenuMap.put("청포도에이드", new int[][] {{310, 3}});
			MenuMap.put("콜라", new int[][] {{309, 5}});

			}
		
		
		//왼쪽패널만들기
		public void makeleft_Order() {
			
			setMenu();//메뉴
			
			//왼쪽 패널
			left_Order = new JPanel(new BorderLayout());;//왼쪽 패널
			left_Order.setBackground(Color.WHITE);
			left_OrderCard = new CardLayout();//왼쪽 내용 전환패널
			left_OrderCardContent = new JPanel(left_OrderCard);//왼쪽 내용 패널

			
			//메뉴 패널,버튼 생성
			left_OrderLeftMenu = new JPanel(new GridLayout(3,1,0,3));
			left_OrderLeftMenu.setBackground(Color.RED);
			left_OrderLeftMenuButton = new RoundButton[3];	//메뉴버튼 생성
			String[] left_ButtonLabel = {"버거", "치킨", "음료"};
			
			for(int i=0; i<left_ButtonLabel.length; i++) {//메뉴 버튼 생성
				left_OrderLeftMenuButton[i] = new RoundButton(left_ButtonLabel[i]);
				left_OrderLeftMenuButton[i].setFont(font1);
				left_OrderLeftMenuButton[i].setBackground(color);
				left_OrderLeftMenuButton[i].addActionListener(this);
				left_OrderLeftMenu.add(left_OrderLeftMenuButton[i]);
			}
			
			left_Order.add("West", left_OrderLeftMenu);
			
			
			
			//메뉴생성
			left_MenuButton = new RoundButton[left_MenuLabel.length][2];//메뉴단추생성
			//햄버거메뉴
			//햄버거메뉴 카드페이지내용
			left_BurgurPanel = new JPanel();//햄버거메뉴패널
			left_BurgurPanel.setLayout(new BoxLayout(left_BurgurPanel, BoxLayout.Y_AXIS));
			left_BurgurPanel.setBackground(Color.WHITE);
			left_BurgurScroll = new JScrollPane(left_BurgurPanel);
			
			//치킨메뉴
			//치킨메뉴 카드페이지내용
			left_ChikenPanel = new JPanel();//햄버거메뉴패널
			left_ChikenPanel.setLayout(new BoxLayout(left_ChikenPanel, BoxLayout.Y_AXIS));
			left_ChikenPanel.setBackground(Color.WHITE);
			left_ChikenScroll = new JScrollPane(left_ChikenPanel);

			//음료메뉴
			//음료메뉴 카드페이지내용
			left_DrinkPanel = new JPanel();//햄버거메뉴패널
			left_DrinkPanel.setLayout(new BoxLayout(left_DrinkPanel, BoxLayout.Y_AXIS));
			left_DrinkPanel.setBackground(Color.WHITE);
			left_DrinkScroll = new JScrollPane(left_DrinkPanel);
			
			
			//버거 내용 채우기
			for(int i=0; i<left_MenuLabel.length; i++) {
				//이미지 및 아래 라벨생성
				Icon icon = new ImageIcon("src\\junsProject0530\\image\\menu" + i + ".jpg");
				JLabel iconLabel = new JLabel(icon);
				JLabel TextLabel = new JLabel(left_MenuLabel[i][0] + 
						" " +left_MenuLabel[i][1]);
				JPanel TempPanel = new JPanel();
				TextLabel.setFont(font1);
				TempPanel.setBackground(Color.WHITE);
				
				left_MenuButton[i][0] = new RoundButton("+");
				left_MenuButton[i][0].setFont(font1);
				left_MenuButton[i][0].setBackground(color);
				left_MenuButton[i][1] = new RoundButton(" -");
				left_MenuButton[i][1].setFont(font1);
				left_MenuButton[i][1].setBackground(color);
				OrderButtonListener action = new OrderButtonListener(left_MenuButton[i][0], left_MenuButton[i][1], i);
				left_MenuButton[i][0].addActionListener(action);
				left_MenuButton[i][1].addActionListener(action);
				TempPanel.add(TextLabel);
				TempPanel.add(left_MenuButton[i][0]);
				TempPanel.add(left_MenuButton[i][1]);
				
				//중심잡기
				iconLabel.setAlignmentX(CENTER_ALIGNMENT);
				TextLabel.setAlignmentX(CENTER_ALIGNMENT);
				TempPanel.setAlignmentX(CENTER_ALIGNMENT);
				
				if(i<9) {//1~8까지 버거
					//이미지 및 라벨추가
					left_BurgurPanel.add(iconLabel);
					left_BurgurPanel.add(TempPanel);
				}else if(i<15) {//9~14 치킨
					//이미지 및 라벨추가
					left_ChikenPanel.add(iconLabel);
					left_ChikenPanel.add(TempPanel);
				}else if(i<23) {//14~23까지 음료
					//이미지 및 라벨추가
					left_DrinkPanel.add(iconLabel);
					left_DrinkPanel.add(TempPanel);
				}

			}
			
			
					
			
			//카드내용에 붙이기
			left_OrderCardContent.add(left_BurgurScroll, "Burgur");
			left_OrderCardContent.add(left_ChikenScroll, "Chicken");
			left_OrderCardContent.add(left_DrinkScroll, "Drink");

			
			left_Order.add("Center",left_OrderCardContent);
			this.addLeftComponent(left_Order);
			
			
		}
		


		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			
			if(obj == left_OrderLeftMenuButton[0]) {
				left_OrderCard.show(left_OrderCardContent, "Burgur");
			}else if(e.getSource() == left_OrderLeftMenuButton[1]) {
				left_OrderCard.show(left_OrderCardContent, "Chicken");
			}else if(obj == left_OrderLeftMenuButton[2]) {
				left_OrderCard.show(left_OrderCardContent, "Drink");
			}else if(obj == right_OrderButton && !orderList.equals("")) {//주문시
				
				if(right_OrderRadioGroupButton[0].isSelected()) {
					JOptionPane.showMessageDialog(this, "카드를 입력해주세요.");
					CRUDprocess crud = new CRUDprocess();
					OrderInfo info = new OrderInfo();
					
					info.setOrder_list(orderList);
					info.setOrder_price(orderPrice);
					info.setOrder_option(right_OrderRadioGroupButton[0].getText());
					int result = crud.insertOrderInfo(info);
					//DB에 삽입
					if(result > 0) {
						
						for(Map.Entry<String, int[]> elem : orderMap.entrySet()){// 주문 출력
							  
				            String key = elem.getKey();
				            
				            if(MenuMap.containsKey(key)) {
					            int[][] temp = MenuMap.get(key);
					            StockInfo stock;
					            for(int i=0; i<temp.length; i++) {
									stock = crud.selectStockCode(temp[i][0]);
									int vol = stock.getStock_volume() - stock.getStock_consume()*temp[i][1];
									stock.setStock_volume(vol);
									crud.updateStockInfo(stock);
					            }
					       }
						}
						
						JOptionPane.showMessageDialog(this, "주문되었습니다.");
						
					} else {
						JOptionPane.showMessageDialog(this, "주문이 실패하였씁니다.");
					}
					
					
						
					
				}else if(right_OrderRadioGroupButton[1].isSelected()){
					CRUDprocess crud = new CRUDprocess();
					OrderInfo info = new OrderInfo();
					
					info.setOrder_list(orderList);
					info.setOrder_price(orderPrice);
					System.out.println("주문 가격 :" + orderPrice);
					info.setOrder_option(right_OrderRadioGroupButton[1].getText());
					int result = crud.insertOrderInfo(info);
					//DB에 삽입
					if(result > 0) {
						
						for(Map.Entry<String, int[]> elem : orderMap.entrySet()){// 주문 출력
							  
				            String key = elem.getKey();
				            
				            if(MenuMap.containsKey(key)) {
					            int[][] temp = MenuMap.get(key);
					            StockInfo stock;
					            for(int i=0; i<temp.length; i++) {
									stock = crud.selectStockCode(temp[i][0]);
									int vol = stock.getStock_volume() - stock.getStock_consume()*temp[i][1];
									stock.setStock_volume(vol);
									crud.updateStockInfo(stock);
					            }
					       }
						}
						JOptionPane.showMessageDialog(this, "주문되었습니다.");
					} else {
						JOptionPane.showMessageDialog(this, "주문이 실패하였씁니다.");
					}
				}
				
			}else if(obj == right_CancelButton) {//주문 취소시
				orderList = "";
				right_OrderLabel.setText("");
				right_PriceLabel.setText("0");
				right_OrderRadioGroup.clearSelection();
				orderMap.clear();
			}
			
		}
		
		
		
		String orderList = "";//주문 리스트
		int orderPrice = 0;// 주문 가격
		LinkedHashMap<String , int[]> orderMap = new LinkedHashMap<String, int[]>();
		//버튼 액션  리스너
		class OrderButtonListener implements ActionListener {

			
			RoundButton plus;
			RoundButton minus;
			String name;
			int price;

			
			public OrderButtonListener(RoundButton plus, RoundButton minus, int index) {
				
				this.plus = plus;
				this.minus = minus;
				this.name = left_MenuLabel[index][0];
				this.price = Integer.parseInt(left_MenuLabel[index][1]);

			};
			
			 public void actionPerformed(ActionEvent e) {
				 Object obj = e.getSource();
				  if(obj == plus) {
					  
					  if(orderMap.containsKey(name)) {//개수 플러스
						  
						  orderMap.get(name)[1] = orderMap.get(name)[1]+1;
						  
					  }else {
						  
						  orderMap.put(name, new int[] {price, 1});
						
					  }
					  
					  orderList = "";
					  orderPrice = 0;
					  for(Map.Entry<String, int[]> elem : orderMap.entrySet()){// 주문 출력
						  
				            String key = elem.getKey();
				            int[] value = elem.getValue();
				            orderPrice = orderPrice + value[0]*value[1];
				            orderList = orderList + key + "x"+ value[1] + "  ";
				            
				        }
					  right_OrderLabel.setText("<html><p style=\"width:350px\">"+orderList+"</p></html>");
					  right_PriceLabel.setText("" + orderPrice);
					  
					  
				  }else if (obj == minus) {//목록 마이너스
					  
					  if(orderMap.containsKey(name)) {
						  
						  if(orderMap.get(name)[1] != 1)
							  orderMap.get(name)[1] = orderMap.get(name)[1]-1;
						  else
							  orderMap.remove(name);
						  
					  }
					  
					  orderList = "";
					  orderPrice = 0;
					  for(Map.Entry<String, int[]> elem : orderMap.entrySet()){// 주문 출력
						  
				            String key = elem.getKey();
				            int[] value = elem.getValue();
				            orderPrice = orderPrice + value[0]*value[1];
				            orderList = orderList + key + "x"+ value[1] + "  ";
				            
				        }
					  right_OrderLabel.setText("<html><p style=\"width:350px\">"+orderList+"</p></html>");
					  right_PriceLabel.setText("" + orderPrice);
					  
					
				 }
				  
			 }

		}

}
