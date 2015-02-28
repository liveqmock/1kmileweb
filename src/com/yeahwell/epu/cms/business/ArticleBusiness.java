package com.yeahwell.epu.cms.business;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.admin.form.ArticleForm;
import com.yeahwell.epu.cms.enums.ArticleCategoryEnum;
import com.yeahwell.epu.cms.enums.ArticleStatusEnum;
import com.yeahwell.epu.cms.model.Article;
import com.yeahwell.epu.common.constants.Constants;
import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.common.util.DateUtil;
import com.yeahwell.epu.service.cms.ArticleService;


@Service
public class ArticleBusiness {
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 首页显示的10篇文章
	 * @param pageNumber 页数
	 * @param pageSize 每页容量
	 * @return
	 */
	public JSONObject findArticleHome(){
		int pageNumber = Constants.NUM_ONE;
		int pageSize = Constants.NUM_TEN;
		Page page = findArticlePage(pageNumber, pageSize, null, ArticleCategoryEnum.STARTED.getCode(), null);
		JSONObject json = new JSONObject();
		json.put("articles", page.getPageList());
		return json;
	}
	
	/**
	 * 分页查找文章信息
	 * @param pageNumber 页数
	 * @param pageSize 每页容量
	 * @return
	 */
	public Page findArticlePage(int pageNumber, int pageSize){
		return findArticlePage(pageNumber, pageSize, null, null, null);
	}
	
	/**
	 * 分页查找文章信息,文章状态默认为PUBLISHED
	 * @param pageNumber 页数
	 * @param pageSize 每页容量
	 * @param status 默认为PUBLISHED
	 * @param catCode 文章分类
	 * @return
	 */
	public Page findArticlePage(int pageNumber, int pageSize, String status, String catCode){
		if(StringUtils.isEmpty(status)){
			status = ArticleStatusEnum.PUBLISHED.getCode();
		}
		return findArticlePage(pageNumber, pageSize, status, null, null);
	}
	
	/**
	 * 分页查找文章信息
	 * @param pageNumber 页数
	 * @param pageSize 每页容量
	 * @param status 文章状态
	 * @param catCode 文章分类
	 * @param title 文章标题
	 * @return
	 */
	public Page findArticlePage(int pageNumber, int pageSize, String status,
			String catCode, String title){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pageNumber", pageNumber);
		paramMap.put("pageSize", pageSize);
		if(StringUtils.isNotBlank(status)){
			paramMap.put("status", status);
		}
		if(StringUtils.isNotBlank(catCode)){
			paramMap.put("catCode", catCode);
		}
		if(StringUtils.isNotBlank(title)){
			paramMap.put("title", title);
		}
		Page page = articleService.findArticlePage(paramMap);
		return page;
	}
	
	public Article findArticleById(Integer articleId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("articleId", articleId);
		Map<String, Object> articleMap =  articleService.findArticleById(paramMap);
		Article articleModel = new Article();
		BeanUtil.transMap2Bean(articleMap, articleModel);
		return articleModel;
	}
	
	/**
	 * 修改文章信息
	 * @param articleForm
	 * @return
	 */
	public boolean updateArticle(ArticleForm articleForm){
		Map<String, Object> paramMap = BeanUtil.transBean2Map(articleForm);
		paramMap.put("updateTime", DateUtil.getCurrentTime());
		return articleService.updateArticle(paramMap);
	}
	
	/**
	 * 新增文章,文章状态为待审核
	 * @param articleForm
	 * @return
	 */
	public boolean addArticle(ArticleForm articleForm){
		Map<String, Object> paramMap = BeanUtil.transBean2Map(articleForm);
		paramMap.put("status", ArticleStatusEnum.WAIT_FOR_AUDIT.getCode());
		paramMap.put("createTime", DateUtil.getCurrentTime());
		paramMap.put("updateTime", DateUtil.getCurrentTime());
		return articleService.addArticle(paramMap);
	}
	
	/**
	 * 发布文章
	 * @param articleId
	 * @return
	 */
	public boolean publishArticle(Integer articleId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("articleId", articleId);
		paramMap.put("status", ArticleStatusEnum.PUBLISHED.getCode());
		return articleService.updateArticle(paramMap);
	}
	
	/**
	 * 添加文章并发布
	 * @param articleForm
	 * @return
	 */
	public boolean addArticleAndPublish(ArticleForm articleForm){
		Map<String, Object> paramMap = BeanUtil.transBean2Map(articleForm);
		paramMap.put("createTime", DateUtil.getCurrentTime());
		paramMap.put("updateTime", DateUtil.getCurrentTime());
		paramMap.put("status", ArticleStatusEnum.PUBLISHED.getCode());
		return articleService.addArticle(paramMap);
	}
	
	/**
	 * 把文章放到回收站
	 * @param articleId
	 * @return
	 */
	public boolean recycleArticle(Integer articleId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("articleId", articleId);
		paramMap.put("status", ArticleStatusEnum.RECYCLE.getCode());
		return articleService.updateArticle(paramMap);
	}
	
	/**
	 * 把文章从回收站中恢复
	 * @param articleId
	 * @return
	 */
	public boolean recoverArticle(Integer articleId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("articleId", articleId);
		paramMap.put("status", ArticleStatusEnum.WAIT_FOR_AUDIT.getCode());
		return articleService.updateArticle(paramMap);
	}
	
	/**
	 * 把文章从数据库中删除
	 * @param articleId
	 * @return
	 */
	public boolean deleteArticle(Integer articleId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("articleId", articleId);
		return articleService.updateArticle(paramMap);
	}

}
