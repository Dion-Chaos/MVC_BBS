package spring.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class ViewControl {
	
	@Autowired
	private BbsDAO b_dao;
	
	@RequestMapping("/view")
	public ModelAndView view(String seq, String nowPage)throws Exception {
		//현재 메서드가 불려질 때 list.jsp에서 제목을 클릭할 때이다.
		//이때 get방식으로 seq=11&nowPage=1형식으로 파라미터 값들이 전달된다.
		//이를 파라미터 이름과 동일하게 한 인자들을 지정하여 자동으로 파라미터들을 받아낸다.
		
		BbsVO vo = b_dao.getBbs(seq);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		mv.addObject("seq", seq);
		mv.setViewName("view");
		
		return mv;
		
	}

}
