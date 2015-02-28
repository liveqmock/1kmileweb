package com.yeahwell.epu.service.cms;

import java.util.Map;

import com.yeahwell.common.pagenation.Page;

/**
 * @author yeahwell19920525@gmail.com
 */
public interface ArticleService {
    
	/**
     * 功能描述：分页查询文章
     * @param paramMap
     * @return 
     */
    public Page findArticlePage(Map<String,Object> paramMap);
    
    /**
     * 根据id查询文章
     * @param paramMap
     * @return
     */
    public Map<String, Object> findArticleById(Map<String,Object> paramMap);
    
    /**
     * 新增文章
     * @param paramMap
     * @return
     */
    public boolean addArticle(Map<String, Object> paramMap);
    
    /**
     * 修改文章
     * @param paramMap
     * @return
     */
    public boolean updateArticle(Map<String, Object> paramMap);
    
    /**
     * 修改文章状态
     * @param paramMap
     * @return
     */
    public boolean updateArticleStatus(Map<String, Object> paramMap);
    	
    /**
     * 删除文章
     * @param paramMap
     * @return
     */
    public boolean deleteArticle(Map<String, Object> paramMap);
    
}
