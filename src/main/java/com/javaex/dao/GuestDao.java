package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	// 전제 리스트 가져오기
	public List<GuestVo> getList(){
		List<GuestVo> addList = sqlSession.selectList("guestbook.selectList");
		
		System.out.println("Dao addList: " + addList);
		
		return addList;		
	}
	
	// 등록 1
	public int insert1(GuestVo guestVo) {
		System.out.println("Dao insert: " + guestVo);
		
		int count = sqlSession.insert("guestbook.insert1", guestVo);
		
		return count;
	}
	
	// 등록 2 -> Map 이용
	public int insert2(String name, String pass, String content) {
		System.out.println("Dao insert2: " + name + ", " + pass + ", " + content);
		
		Map<String, Object> guestMap = new HashMap<String, Object>();
		guestMap.put("name", name);
		guestMap.put("password", pass);
		guestMap.put("content", content);
		
		int count = sqlSession.insert("guestbook.insert2", guestMap);
		
		return count;		
	}
	
	// 삭제
	public int delete(int no, String pass) {
		System.out.println("Dao delete: " + no + ", " + pass);
		
		Map<String, Object> passMap = new HashMap<String, Object>();
		passMap.put("no", no);
		passMap.put("password", pass);
		
		System.out.println(passMap.toString());
		
		int count = sqlSession.delete("guestbook.delete", passMap);
				
		return count;
	}
	
}
