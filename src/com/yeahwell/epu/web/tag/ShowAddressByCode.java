package com.yeahwell.epu.web.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yeahwell.epu.oms.business.AddressBusiness;
import com.yeahwell.epu.oms.model.Address;

public class ShowAddressByCode extends SimpleTagSupport {

	private String addressType;

	private String addressCode;

	@Override
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		PageContext pageContext = (PageContext) context;
		JspWriter out = pageContext.getOut(); // PrintWrite类的使用缓冲的版本JspWriter
		String addressName = "";
		Address address = null;
		ServletContext servletContext = ((PageContext) getJspContext())
				.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		AddressBusiness addressBusiness = (AddressBusiness) wac
				.getBean(AddressBusiness.class);
		if ("province".equals(addressType)) {
			address = addressBusiness.findProvince(addressCode);
			if (null != address) {
				addressName = address.getAddressName();
				if (StringUtils.isEmpty(addressName)) {
					addressName = "其他省";
				}
			} else {
				addressName = "其他省";
			}
		} else if ("city".equals(addressType)) {
			address = addressBusiness.findCity(addressCode);
			if (null != address) {
				addressName = address.getAddressName();
				if (StringUtils.isEmpty(addressName)) {
					addressName = "其他城市";
				}
			} else {
				addressName = "其他城市";
			}
		} else if ("district".equals(addressType)) {
			address = addressBusiness.findDistrict(addressCode);
			if (null != address) {
				addressName = address.getAddressName();
				if (StringUtils.isEmpty(addressName)) {
					addressName = "其他区县";
				}
			} else {
				addressName = "其他区县";
			}
		} else {
			addressName = "其他";
		}
		out.write(addressName);
		super.doTag();
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

}
