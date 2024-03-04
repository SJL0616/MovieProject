package movie.vo;

public class PageContext {
	private final int PAGESIZE = 10; // 페이지 크기
	private final int INDEXSIZE = 10; // 인덱스 크기

	private int currentPage; // 현재 페이지 번호
	private int totalItems; // 총 항목 수
	private int totalPages; // 총 페이지 수
	private int startIndex; // 시작 인덱스
	private int endIndex; // 시작 인덱스
	
	private int startPage; // 시작 인덱스
	private int endPage; // 시작 인덱스

	public PageContext(int currentPage, int totalItems) {
		this.currentPage = currentPage;
		this.totalItems = totalItems;
		this.totalPages = calcTotalPages();
		calcPage();
		calcIndex();
	}
	
	// 전체 페이지 수 계산
	private int calcTotalPages() {
		int pages = totalItems / PAGESIZE;
		if(totalItems % PAGESIZE != 0) {
			pages +=1;
		}
		return pages;
	}
	
	private void calcPage() {
		int multi = ((currentPage-1) / INDEXSIZE);
		this.startPage = 1;
		if(multi >= 1) {
			this.startPage = (multi * INDEXSIZE) +1;
		}
		
		this.endPage  = this.startPage + INDEXSIZE;
		if(this.endPage > this.totalPages) {
			this.endPage = this.totalPages;
		}
	}
	
	private void calcIndex() {
		this.startIndex = 0;
		if(currentPage > 1) {
			this.startIndex = (currentPage-1) * PAGESIZE;
		}
		
		this.endIndex =this.startIndex + PAGESIZE;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return PAGESIZE;
	}


	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
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
	
	public int getPAGESIZE() {
		return PAGESIZE;
	}

	public int getINDEXSIZE() {
		return INDEXSIZE;
	}

}
