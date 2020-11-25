package junsProject0530;

import java.sql.Date;

public class OrderInfo {
	
	private int order_number;
	private String odrer_time;
	private String order_list;
	private int order_price;
	private String order_option;
	

	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getOrder_time() {
		return odrer_time;
	}
	public void setOrder_time(String order_time) {
		this.odrer_time = order_time;
	}
	public String getOrder_list() {
		return order_list;
	}
	public void setOrder_list(String order_list) {
		this.order_list = order_list;
	}
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public String getOrder_option() {
		return order_option;
	}
	public void setOrder_option(String order_option) {
		this.order_option = order_option;
	}

}
