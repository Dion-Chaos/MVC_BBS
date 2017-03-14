<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check(ff){
		//유효성 검사
		
		ff.submit();
	}
</script>
</head>
<body>

	<form action="answer.inc" method="post" 
	enctype="multipart/form-data">
		<input type="hidden" name="bname" value="BBS"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="center" height="10"></td>
        </tr>
        <tr>
          <td align="center"><u><b>BBS 답변쓰기</b></u></td>
        </tr>
        <tr>
          <td align="center" valign="top">
          	<%@ include file="insertForm.jsp" %>
          </td>
        </tr>
        <tr>
          <td height="19"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
	</form>
</body>
</html>












