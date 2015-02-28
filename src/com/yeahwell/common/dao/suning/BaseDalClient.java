package com.yeahwell.common.dao.suning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suning.framework.dal.client.support.DefaultDalClient;
import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.common.constants.Constants;

/**
 * 继承DalClient，扩展分页
 * 
 * @author yeahwell
 */
public class BaseDalClient extends DefaultDalClient {

	private Logger logger = LoggerFactory.getLogger(BaseDalClient.class);

	/**
	 * 
	 * @param countSqlId
	 * @param pageSqlId
	 * @param args[0] pageNumber 请求页
	 * @param args[1] pageSize 每页容量
	 * @param args...  其他查询参数
	 * @return
	 */
	public Page queryForPage(String countSqlId, String pageSqlId,
			Map<String, Object> paramMap) {
		// 得到总记录数
		long totalRecords = super.queryForObject(countSqlId, paramMap,
				Long.class);
		logger.debug("得到总记录数为" + totalRecords);
		Page page = new Page(totalRecords);
		int pageNumber = 1;
		int pageSize = Constants.NUM_TEN;
		if (totalRecords > 0) {
			try {
				pageNumber = Integer.valueOf(String.valueOf(paramMap
						.get(Constants.STR_PAGE_NUMBER)));
				pageSize = Integer.valueOf(String.valueOf(paramMap
						.get(Constants.STR_PAGE_SIZE)));
				logger.debug("请求当前页" + pageNumber + ",每页容量" + pageSize);
			} catch (NumberFormatException nfe) {
				pageNumber = Constants.NUM_ONE;
				pageSize = Constants.NUM_TEN;
			}
		}
		//设置请求页数
		page.setPageNumber(pageNumber);
		//设置每页显示记录数
		page.setPageSize(pageSize);
		
		List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
		//计算offset并设置到参数中
		paramMap.put("pageSize", pageSize);
		paramMap.put("startIndex", (pageNumber - 1) * pageSize);
		pageList = super.queryForList(pageSqlId, paramMap);
		//设置每页中的数据
		page.setPageList(pageList);
		
		return page;
	}

}
