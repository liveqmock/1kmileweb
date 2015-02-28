package com.yeahwell.epu.merchat.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahwell.common.pagenation.Page;
import com.yeahwell.epu.cms.business.ArticleBusiness;
import com.yeahwell.epu.cms.enums.ArticleCategoryEnum;
import com.yeahwell.epu.cms.enums.ArticleStatusEnum;
import com.yeahwell.epu.cms.model.Article;
import com.yeahwell.epu.common.controller.BaseController;

@Controller
@RequestMapping("/merchant/help")
public class MerchantHelpCenterController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(MerchantHelpCenterController.class);

	@Autowired
	private ArticleBusiness articleBusiness;
	
	@RequestMapping(value = "/guide", method = RequestMethod.GET)
	public String guide(
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			final ModelMap modelMap) {
		Page page = articleBusiness.findArticlePage(pageNumber, PAGE_SIZE);
		modelMap.addAttribute("articlePage", page);
		modelMap.addAttribute("articleCatList", ArticleCategoryEnum.toList());
		return "merchant/help/guide";
	}
	
	@RequestMapping(value = "/guide/cat/{cat}", method = RequestMethod.GET)
	public String guideCat(
			@PathVariable(value = "cat") String cat,
			@RequestParam(value = STR_PAGE_NUMBER, defaultValue = PAGE_NUMBER_ONE, required = false) Integer pageNumber,
			final ModelMap modelMap) {
		if(StringUtils.isEmpty(cat)){
			return guide(pageNumber, modelMap);
		}
		Page page = articleBusiness.findArticlePage(pageNumber, PAGE_SIZE,
				ArticleStatusEnum.PUBLISHED.getCode(), cat);
		modelMap.addAttribute("articlePage", page);
		modelMap.addAttribute("articleCatList", ArticleCategoryEnum.toList());
		return "merchant/help/guide";
	}
	
	@RequestMapping(value = "/guide/detail/{articleId}", method = RequestMethod.GET)
	public String guideDetail(
			@PathVariable(value = "articleId") Integer articleId,
			final ModelMap modelMap) {
		Article article = articleBusiness.findArticleById(articleId);
		modelMap.addAttribute("article", article);
		modelMap.addAttribute("articleCatList", ArticleCategoryEnum.toList());
		return "merchant/help/guideDetail";
	}
	
	/**
	 * 首页显示10条文章
	 * @return
	 */
	@RequestMapping(value = "/listHome", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String showIndexHelp(){
		//不能使用?
		JSONObject json = articleBusiness.findArticleHome();
		return json.toString();
	}

	

}
