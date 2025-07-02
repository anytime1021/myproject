package com.sboot.pro.argus.DTO;

public class PagingDTO {
    private int totalCount;
    private int currentPage;
    private int limit;
    private int offset;
    private int totalPage;
    private int pageBlockSize;
    private int startPage;
    private int endPage;

    public PagingDTO(int totalCount, int currentPage, int limit, int pageBlockSize) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.limit = limit;
        this.pageBlockSize = pageBlockSize;

        this.totalPage = (int) Math.ceil((double) totalCount / limit);
        this.offset = (currentPage - 1) * limit;

        this.startPage = ((currentPage - 1) / pageBlockSize) * pageBlockSize + 1;
        this.endPage = startPage + pageBlockSize - 1;
        if (endPage > totalPage) {
            this.endPage = totalPage;
        }
    }

	public int getTotalCount() {
		return totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getPageBlockSize() {
		return pageBlockSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}