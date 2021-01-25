package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/guest")
public class GuestBookController {
	// 메소드 단위로 기능을 정의하는 spring
	
			// 필드
			@Autowired
			private GuestDao guestDao;
		
			// 생성자
			// 메소드 g/s
			
			/******** 메소드 일반*********/
			// 메소드 마다 기능 1개씩 --> 기능 마다 url 부여

			// Handler mapping
			
			// 전체 리스트
			@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
			public String list(Model model) {
				System.out.println("list");
				
				List<GuestVo> guestList = guestDao.getList();
				
				// 리스트 확인
				System.out.println("controller: " + guestList);
				
				// data 보내기
				model.addAttribute("guestList", guestList);
				
				return "addList";
			}
			
			// 등록 1번째 방법
			@RequestMapping(value="/add1", method= {RequestMethod.GET, RequestMethod.POST})
			public String add(@ModelAttribute GuestVo guestVo) {
				System.out.println("add1");
				int count = guestDao.insert1(guestVo);
				
				return "redirect:/guest/list";
			}
			
			// 등록 2번째 방법
			@RequestMapping(value="/add2", method= {RequestMethod.GET, RequestMethod.POST})
			public String add2(@RequestParam("name") String name, @RequestParam("password") String pass, @RequestParam("content") String content) {
				System.out.println("add2");
				
				// 파마미터 값 확인
				System.out.println("controller add2: " + name + ", " + pass + ", " + content);
				
				int count = guestDao.insert2(name, pass, content);

				return "redirect:/guest/list";
			}
			
			// 삭제 폼
			@RequestMapping(value="/dForm", method= {RequestMethod.GET, RequestMethod.POST})
			public String dForm() {
				System.out.println("dForm");
				
				return "deleteForm";
			}
			
			// 삭제
			@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
			public String delete(@RequestParam("no") int no, @RequestParam("password") String pass) {
				System.out.println("delete");
				
				// 파라미터 값 확인
				System.out.println("controller delete: " + no + ", " + pass);
				
				int check = guestDao.delete(no, pass);
				
				if(check==1) {
					System.out.println("삭제 성공");
					
					return "redirect:/guest/list";
				} else {
					System.out.println("삭제 실패");
					
					return "redirect:/guest/dForm?result=fail&no="+no;
				}
			}
			
}
