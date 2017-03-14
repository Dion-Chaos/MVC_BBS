package spring.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.Paging;

public class ListControl implements Controller {

	@Autowired
	private BbsDAO b_dao;
	
	//페이징 기법을 위한 변수들
	public static final int BLOCK_LIST = 5;
	public static final int BLOCK_PAGE = 3; //한 블럭당 페이지 수
	
	int nowPage; //현재페이지 값
	int rowTotal; //총 게시물의 수
	String pageCode; //페이징 처리된 HTML코드
	
	//추후 검색 기능이 추가된다면...
	String searchType, searchValue;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 여기는 사용자가 브라우저 창에서 list.inc로 요청 했을 때 수행하는 곳
		
		//현재 페이지 값을 얻어낸다.
		String c_page = request.getParameter("nowPage");
		
		if (c_page == null) {
			nowPage = 1;
		}else{
			nowPage = Integer.parseInt(c_page);
		}
		
		//게시판을 구별하는 문자열(bname)
		String bname = "BBS"; //request.getParameter("bname");
		
		rowTotal = b_dao.getTotalCount(bname);
		
		//페이징 객체를 생성
		Paging page = new Paging(nowPage, rowTotal, BLOCK_LIST, BLOCK_PAGE);
		
		//위의 객체가 가지는 HTML코드를 얻어내자
		StringBuffer sb = page.getSb();
		pageCode = sb.toString(); //HTML코드
		
		int begin = page.getBegin();
		int end = page.getEnd();
		
		Map<String, String> map = new HashMap<>();
		map.put("bname", bname);
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		
		//목록 가져오기
		BbsVO[] ar = b_dao.getList(map);
		
		//JSP에서 표현할 수 있도록 ModelandView에 ar을 저장한다.
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		mv.addObject("nowPage", nowPage);
		mv.addObject("pageCode", pageCode);
		mv.addObject("rowTotal", rowTotal);
		mv.addObject("block_list", BLOCK_LIST);
		
		mv.setViewName("list");
		
		return mv;
	}

}
