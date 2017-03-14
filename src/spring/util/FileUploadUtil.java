package spring.util;

import java.io.File;

public class FileUploadUtil {
	
	public static String checkSameFileName(String filename, String path){
		//파일이름을 가려내자 (imsi.txt)
		int period = filename.lastIndexOf(".");
		
		String f_name = filename.substring(0, period); //imsi
		String suffix = filename.substring(period); //.txt
		
		//전체경로 만들기
		String saveFileName = path + System.getProperty("file.separator") + filename;
		
		//파일이 존재하는지를 알기위해서는 무조건 java.io.File객체를 만들어서 exists()로 확인해야 함
		File f = new File(saveFileName);
		
		//같은 이름이 있다면 번호를 붙여야 하므로 번호를 가지는 변수를 선언한다.
		int idx = 1;
		while (f != null && f.exists()) {
			//동일한 이름의 파일이 있는 경우 파일명을 변경
			StringBuffer sb = new StringBuffer();
			sb.append(f_name);
			sb.append(idx++);
			sb.append(suffix);
			
			filename = sb.toString(); // imsi1.txt
			
			//다시 존재여부를 확인하기 위해 전체경로를 다시 만든다.
			saveFileName = path + System.getProperty("file.separator") + filename;
			
			f = new File(saveFileName);
		}//while
		
		return filename; //중복되지 않는 이름을 반환한다.
		
	}

}
