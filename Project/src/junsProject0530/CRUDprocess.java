package junsProject0530;

import java.io.InputStream;





import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;





public class CRUDprocess {

	private SqlSession getSession() {
		String path = "junsProject0530/mybatis_config.xml";//�솚寃쎌꽕�젙�뙆�씪
		InputStream is = null;//�뙆�씪�쓽 �궡�슜�쓣 �씫�쓣 媛앹껜
		try {
			is = Resources.getResourceAsStream(path);
		}catch(Exception e) {
			System.out.println("�솚寃쎌꽕�젙�뙆�씪�쓣 �씫�뒗以�");
		}//�삁�쇅泥섎━
		SqlSessionFactoryBuilder builder = 
				new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession();
		return session;
	}
	//二쇰Ц 愿��젴�궗�빆
	public int insertOrderInfo(OrderInfo order) {
		SqlSession s = getSession();
		int result = 0;//�옉�뾽�쓽 �꽦怨듭쑀臾대�� �쐞�븳 蹂��닔
		try {
			result = s.insert("loginmapper.insertOrderInfo", order);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<OrderInfo> selectOrderInfo(){
		SqlSession s = getSession();
		List<OrderInfo> outputs;
		try {
			outputs = s.selectList("loginmapper.selectOrderInfo");
			return outputs;
		}finally {
			s.close();
		}
	}
	//怨듭��궗�빆 愿��젴�궗�빆
	public int insertNoticeInfo(NoticeInfo notice) {
		SqlSession s = getSession();
		int result = 0;//�옉�뾽�쓽 �꽦怨듭쑀臾대�� �쐞�븳 蹂��닔
		try {
			result = s.insert("loginmapper.insertNoticeInfo", notice);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<NoticeInfo> selectNoticeInfo(){
		SqlSession s = getSession();
		List<NoticeInfo> outputs;
		try {
			outputs = s.selectList("loginmapper.selectNoticeInfo");
			return outputs;
		}finally {
			s.close();
		}
	}
	
	public int updateNoticeInfo(NoticeInfo notice) {
		SqlSession s = getSession();
		int result = 0;//작업의 성공유무를 위한 변수
		try {
			result = s.update("loginmapper.updateNoticeInfo", notice);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public int deleteNoticeInfo(int notice) {
		SqlSession s = getSession();
		int result = 0;//작업의 성공유무를 위한 변수
		try {
			result = s.delete("loginmapper.deleteNoticeInfo", notice);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	
	
	public int insertStaffInfo(StaffInfo staff) {
		SqlSession s = getSession();
		int result = 0;//�옉�뾽�쓽 �꽦怨듭쑀臾대�� �쐞�븳 蹂��닔
		try {
			result = s.insert("loginmapper.insertStaffInfo", staff);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<StaffInfo> selectStaffInfo(){
		SqlSession s = getSession();
		List<StaffInfo> outputs;
		try {
			outputs = s.selectList("loginmapper.selectStaffInfo");
			return outputs;
		}finally {
			s.close();
		}
	}
	
	public StaffInfo selectStaffCode(String staff_id) {
		SqlSession s = getSession();
		StaffInfo ii = null;
		try {
			ii = s.selectOne("loginmapper.selectStaffCode", staff_id);
			return ii;
		}finally {
			s.close();
		}
	}
	
	public int updateStaffInfo(StaffInfo staff) {
		SqlSession s = getSession();
		int result = 0;//작업의 성공유무를 위한 변수
		try {
			result = s.update("loginmapper.updateStaffInfo", staff);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public int updateStaffInfo2(StaffInfo staff) {
		SqlSession s = getSession();
		int result = 0;//작업의 성공유무를 위한 변수
		try {
			result = s.update("loginmapper.updateStaffInfo2", staff);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public StaffInfo selectIdAndPwd(StaffInfo staff){
		SqlSession s = getSession();
		try {
			StaffInfo info=
					s.selectOne("loginmapper.selectIdPwd", staff);
			return info;
		}finally {
			s.close();
		}
	}
	
	//재고
	public List<StockInfo> selectStockInfo(){
		SqlSession s = getSession();
		List<StockInfo> outputs;
		try {
			outputs = s.selectList("loginmapper.selectStockInfo");
			return outputs;
		}finally {
			s.close();
		}
	}
	
	public StockInfo selectStockCode(int stock_number) {
		SqlSession s = getSession();
		StockInfo ii = null;
		try {
			ii = s.selectOne("loginmapper.selectStockCode", stock_number);
			return ii;
		}finally {
			s.close();
		}
	}
	
	public int updateStockInfo(StockInfo stock) {
		SqlSession s = getSession();
		int result = 0;//작업의 성공유무를 위한 변수
		try {
			result = s.update("loginmapper.updateStockInfo", stock);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	public int selectSumSaledInfo(String code) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.selectOne("loginmapper.selectSumSaledInfo", code);
			if(result > -1) s.commit();
			else s.rollback();
			return result;
		}catch (Exception e){
			return 0;
		}finally {
			s.close();
		}
	}
	
	public int changeOrderInfo(OrderInfo order) {
		SqlSession s = getSession();
		int result = 0;//�옉�뾽�쓽 �꽦怨듭쑀臾대�� �쐞�븳 蹂��닔
		try {
			result = s.insert("loginmapper.changeOrderInfo", order);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
	
	
	
}
