package com.yeahwell.common.dao.spring;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.common.constants.Constants;

/**
 * @Repository:表示持久层组件
 * @author yeahwell
 * 
 */
public class SpringDaoSupport {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * jdbcTemplate属性注入必须
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 执行sql语句,如DDL语句.
	 * 
	 * @param sql
	 */
	public void execute(String sql) {
		jdbcTemplate.execute(sql);
	}

	/**
	 * 提供对表的增加、删除、修改操作
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param args
	 *            可变变参
	 * @return 受影响的行数
	 */
	public int update(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 批量更新多条记录
	 * 
	 * @param sql
	 *            多条sql组成的数组(不带参数的)
	 * @return 影响行数数组
	 */
	public int[] batchUpdate(String[] sql) {
		return jdbcTemplate.batchUpdate(sql);
	}

	/**
	 * 查询得到一个List
	 * @param sql   查询语句
	 * @param rowMapper  
	 * @param args  补充参数
	 * @return
	 */
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
		return jdbcTemplate.query(sql, rowMapper, args);
	}

	public int queryForInt(String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, Integer.class, args);
	}

	public long queryForLong(String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, Long.class, args);
	}
	
	public <T> T queryForObject(String sql, Class<T> requiredType, Object... args){
		return jdbcTemplate.queryForObject(sql, requiredType, args);
	}

	public <T> T queryForObject(String sql, RowMapper<T> rowMapper,
			Object... args) {
		return jdbcTemplate.queryForObject(sql, rowMapper, args);
	}

	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	public SqlRowSet queryForRowSet(String sql, Object... args) {
		return jdbcTemplate.queryForRowSet(sql, args);
	}

	/**
	 * 分页查询
	 * @param countSql  计算总记录数sql语句  如select count(id) from table
	 * @param pageSql   分页查询语句,根据不同数据库有不同的sql语句  如select * from table limit offset,maxRow
	 * @param rowMapper 为了便于将Model类逐一赋值到List中
	 * @param args[0] pageNumber 请求页
	 * @param args[1] pageSize 每页容量
	 * @param args...  其他查询参数
	 * @return
	 */
	public Page queryForPage(String countSql, String pageSql, Object... args) {

		// 得到总记录数
		long totalRecords = queryForLong(countSql, args);
		Page page = new Page(totalRecords);
		
		int pageNumber = 1;
		int pageSize = Constants.NUM_TEN;
		if (totalRecords > 0) {
			try {
				pageNumber = Integer.valueOf(String.valueOf(args[0]));
				pageSize = Integer.valueOf(String.valueOf(args[1]));
			} catch (NumberFormatException nfe) {
				pageNumber = Constants.NUM_ONE;
				pageSize = Constants.NUM_TEN;
			}
		}
	    //设置请求页数
        page.setPageNumber(pageNumber);
        //设置每页显示记录数
        page.setPageSize(pageSize);
		
		//把第一个参数pageNumber转换为startIndex
		args[0] = (pageNumber - 1) * pageSize;
		//放入数据
		List<Map<String, Object>> pageList = new ArrayList<Map<String, Object>>();
		pageList = queryForList(pageSql, args);
	    //设置每页中的数据
        page.setPageList(pageList);
		return page;
	}

	/**
	 * 获取本地的Connection对象
	 * 
	 * @return
	 */
	public Connection getNativeConn() {
		// 从当前线程绑定的数据连接获取连接
		Connection conn = DataSourceUtils.getConnection(jdbcTemplate
				.getDataSource());
		try {
			// jdbcTemplate获得提取器，再获得本地连接
			conn = jdbcTemplate.getNativeJdbcExtractor().getNativeConnection(
					conn);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

}