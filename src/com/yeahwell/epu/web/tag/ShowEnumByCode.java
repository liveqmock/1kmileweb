package com.yeahwell.epu.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.yeahwell.epu.cms.enums.ArticleCategoryEnum;
import com.yeahwell.epu.cms.enums.ArticleStatusEnum;
import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.express.enums.OrderSourceEnum;
import com.yeahwell.epu.express.enums.PackageLockStatusEnum;
import com.yeahwell.epu.express.enums.PackageStatusEnum;
import com.yeahwell.epu.express.enums.PaymentStatusEnum;
import com.yeahwell.epu.express.enums.PaymentWayEnum;
import com.yeahwell.epu.oms.enums.ChildStationStatusEnum;
import com.yeahwell.epu.oms.enums.MasterStationStatusEnum;

public class ShowEnumByCode extends SimpleTagSupport {

	private String enumType;

	private String code;

	@Override
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		PageContext pageContext = (PageContext) context;
		JspWriter out = pageContext.getOut(); // PrintWrite类的使用缓冲的版本JspWriter
		String cnValue = "";
		if ("ExpressCompanyEnum".equals(enumType)) { 
			cnValue = ExpressCompanyEnum.byCode(code);
		} else if ("PackageLockStatusEnum".equals(enumType)) {
			cnValue = PackageLockStatusEnum.byCode(code);
		} else if ("ChildStationStatusEnum".equals(enumType)) { 
			cnValue = ChildStationStatusEnum.byCode(code);
		}else if ("MasterStationStatusEnum".equals(enumType)) { 
			cnValue = MasterStationStatusEnum.byCode(code);
		} else if ("OrderSourceEnum".equals(enumType)) { 
			cnValue = OrderSourceEnum.byCode(code);
		} else if ("PackageStatusEnum".equals(enumType)) { 
			cnValue = PackageStatusEnum.byCode(code);
		} else if ("PaymentStatusEnum".equals(enumType)) { 
			cnValue = PaymentStatusEnum.byCode(code);
		} else if ("PaymentWayEnum".equals(enumType)) {
			cnValue = PaymentWayEnum.byCode(code);
		} else if ("ArticleCategoryEnum".equals(enumType)) {
			cnValue = ArticleCategoryEnum.byCode(code);
		} else if ("ArticleStatusEnum".equals(enumType)) {
			cnValue = ArticleStatusEnum.byCode(code);
		} else {
			cnValue = "其他";
		}
		out.write(cnValue);
		super.doTag();
	}

	public String getEnumType() {
		return enumType;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
