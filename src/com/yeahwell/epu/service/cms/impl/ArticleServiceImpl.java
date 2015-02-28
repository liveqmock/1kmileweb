package com.yeahwell.epu.service.cms.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.dao.suning.BaseDalClient;
import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.service.cms.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private BaseDalClient baseDalClient;

	@Override
	public Page findArticlePage(Map<String, Object> paramMap) {
		String countSqlId = "articleMapper.queryArticleCount";
		String pageSqlId = "articleMapper.queryArticlePage";
		return baseDalClient.queryForPage(countSqlId, pageSqlId, paramMap);
	}

	@Override
	public Map<String, Object> findArticleById(Map<String, Object> paramMap) {
		return baseDalClient.queryForMap("articleMapper.queryArticleById", paramMap);
	}
	
	@Override
	public boolean addArticle(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("articleMapper.insertArticle",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}

	@Override
	public boolean updateArticle(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("articleMapper.updateArticle",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}
	
	@Override
	public boolean updateArticleStatus(Map<String, Object> paramMap) {
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("articleMapper.updateArticleStatus",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}
	
	public boolean deleteArticle(Map<String, Object> paramMap){
		int affectedRows = 0;
		affectedRows = baseDalClient.execute("articleMapper.deleteArticle",
				paramMap);
		return (affectedRows > 0) ? true : false;
	}

}
