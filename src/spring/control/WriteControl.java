package spring.control;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import spring.util.FileUploadUtil;

@Controller
public class WriteControl {
	
	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext ctx;
	
	//자동으로 채워지지 않고 Controller-servlet.xml에서 현재 객체를 선언할 때
	//<property ..../>로 값을 저장할 변수를 선언한다.
	private String uploadPath;

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("write");
		
		return "write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public ModelAndView write(BbsVO vo) throws Exception {
		
		//파일이 첨부되었는지를 판단하자
		if (vo.getUpload().getSize() > 0) {
			//파일첨부가 되어 있는 경우 이므로 파일을 원하는 곳에 저장한다.
			String path = ctx.getRealPath(uploadPath);
			
			//인자로 전달된 vo에 있는 첨부파일 가져오기
			MultipartFile mf = vo.getUpload();
			
			//첨부파일의 파일명 가져오기
			String f_name = mf.getOriginalFilename();
			
			//파일을 저장하기 전에 동일한 파일명이 있을 수 있으므로 동일한 파일명을 다른이름으로 변경한다.
			f_name = FileUploadUtil.checkSameFileName(f_name, path);
			
			mf.transferTo(new File(path, f_name));  //파일 저장
			
			//DB에 저장할 정보를 가지고 있는 vo에게도 파일명을 저장하자
			vo.setUploadFileName(f_name);
			
		}else{
			vo.setUploadFileName("");
		}
		
		vo.setIp(request.getRemoteAddr());
		
		//mybatis를 이용하여 vo를 전달하면서 값을 저장
		b_dao.writeBbs(vo);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/list.inc"); //sendRedirect list로 간다.
		return mv;
	}

}
