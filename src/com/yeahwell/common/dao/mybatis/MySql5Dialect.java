package com.yeahwell.common.dao.mybatis;

public class MySql5Dialect extends BaseDialect {
	
	public String getLimitString(String sql, int offset, int maxRow) {
		sql = trim(sql);
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 40).append(sql);
		if (offset > 0) {
			return pagingSelect.append(" limit ").append(offset).append(", ").append(maxRow).toString();
		}
		return pagingSelect.append(" limit ").append(maxRow).toString();
	}
	
}