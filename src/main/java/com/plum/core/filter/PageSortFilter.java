package com.plum.core.filter;

/**
 * 用来做分页和排序的参数对象
 *
 * Created by G_dragon on 2015/7/21.
 */

public class PageSortFilter {
    private int sizePerPage;       //每一页记录条数
    private int totalCount;        //总记录数
    private int totalPage;         //总页数
    private int currentPage;       //当前页数
    private int beginIndex;        //开始游标

    private boolean hasPrePage;    //是否有上一页
    private boolean hasNextPage;   //是否有下一页

    private String sortField;   //排序字段
    private String sortType;    //排序方式

    //构造函数，初始化参数，查询的页数以及每一页的记录条数
    public PageSortFilter(int sizePerPage, int currentPage) {
        this.sizePerPage = sizePerPage;
        this.currentPage = currentPage;
    }

    public PageSortFilter(String sortField, String sortType){
        this.sortField = sortField;
        this.sortType = sortType;
    }

    public PageSortFilter(int sizePerPage, int currentPage, String sortField, String sortType) {
        this.sizePerPage = sizePerPage;
        this.currentPage = currentPage;
        this.sortField = sortField;
        this.sortType = sortType;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

	public int getSizePerPage() {
        return sizePerPage;
	}

	public int getTotalCount() {
        return totalCount;
	}

	public int getTotalPage() {
        totalPage = (sizePerPage>0 && totalCount>0) ? (int) Math.ceil(((double)totalCount/sizePerPage)) : 0;
        return totalPage;
	}

	public int getCurrentPage() {
        return currentPage;
	}

	public int getBeginIndex() {
        beginIndex = currentPage>0 ? sizePerPage*(currentPage-1) : 0;
        return beginIndex;
	}

	public boolean isHasPrePage() {
        hasPrePage = currentPage==1 ? true : false;
        return hasPrePage;
	}

	public boolean isHasNextPage() {
        hasNextPage = (currentPage==totalPage && totalPage>0) ? true : false;
		return hasNextPage;
	}

    public String getSortField() {
        return sortField;
    }

    public String getSortType() {
        return sortType;
    }
}

