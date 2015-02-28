package com.yeahwell.common.dao.hibernate;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import com.yeahwell.common.pagenation.SimplePage;
import com.yeahwell.epu.common.constants.Constants;

/**
 * spring3.1开始不提供（没有这个东西了）Hibernate4的 DaoSupport和Template,而是直接使用原生的Hibernate4
 * API
 * 
 * @author yeahwell
 * @param <T>
 *            需要进行操作的域模型
 * @param <PK>
 *            主键类型
 */
public class GenericHibernateDao<T, PK extends Serializable> {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * ---------------------------------------------------------------------
	 * 以下是GenericEntityDao内容
	 * ---------------------------------------------------------------------
	 */
	private Class<T> entityClass; // 实体类

	public void setEntityClass(Class<T> type) {
		this.entityClass = type;
	}

	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	/**
	 * 默认空的构造方法
	 */
	public GenericHibernateDao() {
	}

	/**
	 * 
	 * @param entityClass
	 *            实体类
	 */
	public GenericHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 根据sessionFactory获得当前的session
	 * 
	 * @return
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 添加
	 * 
	 * @param entityObject
	 */
	public void save(T entityObject) {
		getSession().save(entityObject);
	}

	/**
	 * 存储实体或更新实体内容到数据库
	 * 
	 * @param entityObject
	 */
	public void saveOrUpdate(T entityObject) {
		getSession().saveOrUpdate(entityObject);
	}

	/**
	 * 更新
	 * 
	 * @param entityObject
	 */
	public void update(T entityObject) {
		getSession().update(entityObject);
	}

	/**
	 * 删除一条记录,需要设置实体类的id
	 * 
	 * @param entityObject
	 */
	public void delete(T entityObject) {
		getSession().delete(entityObject);
	}

	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 */
	public void deleteById(PK id) {
		this.delete(this.getById(id));
	}

	public void load(T entityObject, PK id) {
		getSession().load(entityObject, id);
	}

	/**
	 * 根据主键获取实体。如果没有相应的实体，抛出异常 get与load的区别-----load返回的是代理对象,等到真正用到对象的时候才会发出sql语句
	 * 1.无论是load还是get,都会查找缓存(一级缓存),如果没有才会从数据库中查找,调用clear()方法可以强制清除session缓存
	 * 2.调用flush()方法可以强制进行从内存到数据库的同步
	 * */
	@SuppressWarnings("unchecked")
	public T loadById(PK id) {
		return (T) getSession().load(this.entityClass, id);
	}

	/**
	 * 根据主键获取实体。如果没有相应的实体，返回 null 不存在对应记录时,get与load表现不一样
	 * get与load的区别--------get直接从数据库加载,不会延迟
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getById(PK id) {
		return (T) getSession().get(this.entityClass, id);
	}

	/**
	 * 强迫装载对象和它的集合,使用了触发器的数据字段比较适合使用
	 * 
	 * @param entityObject
	 */
	public void refresh(T entityObject) {
		getSession().refresh(entityObject);
	}

	/**
	 * 消除与 Hibernate Session 的关联
	 * 
	 * @param entityObject
	 */
	public void evict(T entityObject) {
		getSession().evict(entityObject);
	}

	/**
	 * 如果对象已在本session中持久化了,覆盖原有的<br>
	 * 如果session中没有对应对象,从数据库加载<br>
	 * 如果是脱管对象,则什么都不做
	 * 
	 * @param entityObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T merge(T entityObject) {
		return (T) getSession().merge(entityObject);
	}

	public StatelessSession getNativeStatelessHibernateSession() {
		return sessionFactory.openStatelessSession();
	}

	/*
	 * ---------------------------------------------------------------------
	 * 以下是GenericDao内容
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.<br>
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	protected Query createQuery(String hql, Object... values) {
		// 这里的false表示不创建session保证,当前操作在spring同一个事务的管理下
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据hql查询查询出实体List信息
	 * 
	 * @param <AnyType>
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <AnyType> List<AnyType> listAll(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 执行一些必须的sql语句把内存中的对象同步到数据库中
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * 清除对象缓存
	 */
	public void clear() {
		getSession().clear();
	}

	/**
	 * 返回iterator接口类型的结果
	 * 
	 * @param <AnyType>
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <AnyType> Iterator<AnyType> iterator(String hql, Object... values) {
		return createQuery(hql, values).iterate();
	}

	/**
	 * 执行本地查询获得SQLQuery对象<br>
	 * 可以调用addEntity(*.class).list();获得对应实体list集合<br>
	 * addEntity.add(*.class).addJoin(*.class).list();获得一对多代理对象<br>
	 * 更多用法见google
	 * 
	 * @param sql
	 * @return
	 */
	public SQLQuery nativeSqlQuery(String sql) {
		return getSession().createSQLQuery(sql);
	}

	/**
	 * @param <E>
	 * @param countHql 计算数据总条数的hql语句(就是带count(*)的hql)
	 * @param pageHql 分页查询语句
	 * @param args[0] pageNumber 请求页
	 * @param args[1] pageSize 每页容量
	 * @param args... 其他查询参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> SimplePage<E> queryByPage(String countHql, String pageHql, Object... args) {
		// 计算总记录数
		List<E> countlist = listAll(countHql, args);
		Long totalRecords = (Long) countlist.get(0);
		SimplePage<E> page = new SimplePage<E>(totalRecords);
		
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
		
		Query query = createQuery(pageHql, args);
		List<E> pageList = query.setFirstResult(page.getStartIndex())
				.setMaxResults(pageSize).list();
		page.setPageList(pageList);

		return page;
	}

}
