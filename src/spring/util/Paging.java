package spring.util;

import java.util.Iterator;

public class Paging {

	private int nowPage, rowTotal, blockList, blockPage, totalPage,
				startPage, endPage, begin, end;
	
	private boolean isPrePage; //이전 기능 가능 여부
	private boolean isNextPage;
	
	//JSP에서 표현할 페이징 HTML코드를 저장할 곳
	private StringBuffer sb;
	
	

	public Paging(int nowPage, int rowTotal, int blockList, int blockPage) {
		super();
		this.nowPage = nowPage;
		this.rowTotal = rowTotal;
		this.blockList = blockList;
		this.blockPage = blockPage;
		
		//이전기능과 다음기능을 초기화
		isPrePage = false;
		isNextPage = false;
		//isPrePage = isNextPage = false;
		
		//입력된 전체게시물의 수를 통해 전체 페이지 수를 구한다.
		totalPage = (int)Math.ceil((double)rowTotal/blockList);
		
		//현재 페이지의 값이 전체 페이지의 값보다 크다면 
		//전체 페이지의 값을 현재 페이지 값으로 정한다.
		if (nowPage > totalPage) 
			nowPage = totalPage;
		
		
		//현재 블럭의 시작페이지와 마지막페이지 값을 구하자.
		startPage = (int)((nowPage-1)/blockPage)*blockPage+1;
		endPage = startPage+blockPage-1;
		
		//마지막페이지가 전체 페이지 값을 넘지 못하도록 하자
		if (endPage > totalPage) 
			endPage = totalPage;
		
		
		//시작 게시물의 행 번호를 구하자(begin)
		begin = (nowPage-1)*blockList + 1;
		end = begin+blockList - 1;
		
		//위의 값들이 DB의 게시물들을 추출하는 행번호로 쓰임
		
		//이전 기능을 가능하도록 할지를 결정하자
		//규칙은 시작페이지의 값이 1이 아닐때만 가능
		if (startPage > 1) 
			isPrePage = true;

		//다음 기능 가능 여부 결정
		if (endPage < totalPage) 
			isNextPage = true;
		
		//위의 값들을 가지고 페이징 기법의 HTML코드를 만들어서 StringBuffer에 저장한다.
		sb = new StringBuffer("<ol class='paging'>");
		
		if (isPrePage) {
			sb.append("<li><a href='list.inc?nowPage=");
			sb.append(nowPage - blockPage);
			sb.append("'> &lt; </a></li>");
		}else {
			sb.append("<li class='disable'> &lt; </li>");
		}
		
		//페이지 번호를 출력하는 반복문
		for(int i = startPage; i <= endPage; i++){
			//번호를 출력할 당시 총페이지 수를 넘었다면 반복문 탈출
			if (i > totalPage)
				break;

			//현재 페이지 값과 i가 같다면
			if (i == nowPage) {
				sb.append("<li class='now'>");
				sb.append(i);
				sb.append("</li>");
			}else{
				sb.append("<li><a href='list.inc?nowPage=");
				sb.append(i);
				sb.append("'>");
				sb.append(i);
				sb.append("</a></li>");
			}
		}//for
		
		//다음 기능을 가능하도록 할지를 판단하여 적용
		if (isNextPage) {
			sb.append("<li><a href='list.inc?nowPage=");
			sb.append(nowPage + blockPage);
			sb.append("'> &gt; </a></li>");
		}else {
			sb.append("<li class='disable'> &gt; </li>");
		}
		sb.append("</ol>");
		
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}

	public int getBlockList() {
		return blockList;
	}

	public void setBlockList(int blockList) {
		this.blockList = blockList;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isPrePage() {
		return isPrePage;
	}

	public void setPrePage(boolean isPrePage) {
		this.isPrePage = isPrePage;
	}

	public boolean isNextPage() {
		return isNextPage;
	}

	public void setNextPage(boolean isNextPage) {
		this.isNextPage = isNextPage;
	}

	public StringBuffer getSb() {
		return sb;
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}
	
	
}
