package com.yeahwell.common.pagenation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yeahwell.epu.common.constants.Constants;

/**
 * 功能描述：分页处理
 */

public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 当前页 */
	private int pageNumber = Constants.PAGE_NUMBER;

	/** 每页记录数，默认10条 */
	private int pageSize = Constants.PAGE_SIZE;

	/** 记录总数 */
	private long totalRecords = 0;

	/** 数据列表 */
	private List<Map<String, Object>> pageList;
	
	/** 总页数*/
	private int pageCount = 0;
	
	public Page(){
		super();
	}
	
	public Page(long totalRecords){
	    this(Constants.PAGE_NUMBER, Constants.PAGE_SIZE, totalRecords, null);
	}
	
	public Page(int pageNumber, int pageSize, long totalRecords, List<Map<String, Object>> pageList){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
		this.pageList = pageList;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			this.pageSize = Constants.PAGE_SIZE;
		}else{
			this.pageSize = pageSize;
		}
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		if(totalRecords < 0){
			this.totalRecords = 0;
		}else{
			this.totalRecords = totalRecords;
		}
	}

	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 功能描述： 设置页码。页码小于0时，修正为默认页码；页码大于总页数时，修正为最后一页。
	 */
	public void setPageNumber(int pageNumber) {
	    if(pageNumber < 0){
	        this.pageNumber = Constants.PAGE_NUMBER;
	    }else if(pageNumber > getPageCount()){
	        this.pageNumber = getPageCount();
	    }else{
	        this.pageNumber = pageNumber;
	    }
	}
	
	public void setPageList(List<Map<String, Object>> pageList) {
        this.pageList = pageList;
    }

    public List<Map<String, Object>> getPageList() {
		return pageList;
	}

	/**
	 * 得到总页数
	 * @return
	 */
	public int getPageCount() {
	    this.pageCount = (int) Math.ceil(getTotalRecords() * 1.00 / getPageSize());
		return this.pageCount;
	}

	/**
	 * 数据库分页查询时从第startIndex条数据开始查询
	 * @return
	 */
	public int getStartIndex(){
		return (getPageNumber() - 1) * getPageSize();
	}

}
