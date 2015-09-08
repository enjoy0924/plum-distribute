package com.plum.core.page;

/**
 * ����һ�����ݿ��ҳ��ѯ�����࣬���������û��Setter������ֻ������һ�ι��캯����������һ��ֵ
 *
 * Created by G_dragon on 2015/7/21.
 */

public class PageUtils {
    private int sizePerPage;       //ÿһҳ�����ʾ�ļ�¼��
    private int totalCount;        //�ܵļ�¼��
    private int totalPage;         //һ����Ϊ����ҳ
    private int currentPage;       //��ǰҳ��
    private int beginIndex;        //��ѯ��¼���
    private boolean hasPrePage;    //�Ƿ�����һҳ
    private boolean hasNextPage;   //�Ƿ�����һҳ

    public PageUtils(int sizePerPage, int totalCount, int currentPage) {
        this.sizePerPage = sizePerPage;
        this.totalCount = totalCount;

        this.totalPage = (sizePerPage>0 && totalCount>0) ? (int) Math.ceil(((double)totalCount/sizePerPage)) : 0;
        this.currentPage = currentPage;
        this.beginIndex = currentPage>0 ? sizePerPage*(currentPage-1) : 0;
        this.hasPrePage = currentPage==1 ? true : false;
        this.hasNextPage = (currentPage==this.totalPage && this.totalPage>0) ? true : false;
    }

	public int getSizePerPage() {
        return sizePerPage;
	}

	public int getTotalCount() {
        return totalCount;
	}

	public int getTotalPage() {
        return totalPage;
	}

	public int getCurrentPage() {
        return currentPage;
	}

	public int getBeginIndex() {
        return beginIndex;
	}

	public boolean isHasPrePage() {
        return hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}
}

