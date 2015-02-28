package com.yeahwell.epu.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.admin.form.ArticleForm;
import com.yeahwell.epu.cms.business.ArticleBusiness;
import com.yeahwell.epu.cms.enums.ArticleCategoryEnum;
import com.yeahwell.epu.cms.model.Article;
import com.yeahwell.epu.common.controller.BaseController;
import com.yeahwell.epu.common.model.OperatorResult;

@Controller
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseController{

	private Logger logger = LoggerFactory
			.getLogger(AdminArticleController.class);
	
	@Autowired
	private ArticleBusiness articleBusiness;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			final ModelMap modelMap) {
		logger.info("进入文章管理页面");
		Page page = articleBusiness.findArticlePage(pageNumber, PAGE_SIZE);
		modelMap.addAttribute("page", page);
		return "admin/article/listArticle";
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String publish(
			final ModelMap modelMap) {
		modelMap.addAttribute("articleCategoryList", ArticleCategoryEnum.toList());
		//return "admin/article/publishArticle";
		return "admin/article/publishArticleKindEditor";
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String doPublish(
			@ModelAttribute(value = "articleForm") final ArticleForm articleForm,
			final ModelMap modelMap) {
		boolean addResult = articleBusiness.addArticleAndPublish(articleForm);
		if(addResult){
			return "redirect:/admin/article/list";
		}else{
			return "redirect:/admin/article/publish";
		}
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "articleId") Integer articleId,
			final ModelMap modelMap) {
		Article articleModel = articleBusiness.findArticleById(articleId);
		modelMap.addAttribute("article", articleModel);
		modelMap.addAttribute("articleCategoryList", ArticleCategoryEnum.toList());
		return "admin/article/updateArticle";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public OperatorResult doUpdate(
			@ModelAttribute(value = "articleForm") final ArticleForm articleForm,
			final ModelMap modelMap) {
		final OperatorResult result = new OperatorResult();
		boolean updateResult = articleBusiness.updateArticle(articleForm);
		if(updateResult){
			result.setFlag("true");
		}else{
			result.setFlag("false");
		}
		return result;
	}
	
	@RequestMapping(value = "/recycle", method = RequestMethod.POST)
	public String doRecycle(
			@RequestParam(value = "articleId") Integer articleId,
			final ModelMap modelMap) {
		articleBusiness.recycleArticle(articleId);
		return "admin/article/TODO";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String doDelete(
			@RequestParam(value = "articleId") Integer articleId,
			final ModelMap modelMap) {
		articleBusiness.deleteArticle(articleId);
		return "admin/article/TODO";
	}
	
	
}
