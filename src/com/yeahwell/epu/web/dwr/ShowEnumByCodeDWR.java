package com.yeahwell.epu.web.dwr;

import com.yeahwell.epu.cms.enums.ArticleCategoryEnum;
import com.yeahwell.epu.cms.enums.ArticleStatusEnum;
import com.yeahwell.epu.express.enums.ExpressCompanyEnum;
import com.yeahwell.epu.express.enums.OrderSourceEnum;
import com.yeahwell.epu.express.enums.PackageStatusEnum;
import com.yeahwell.epu.express.enums.PaymentStatusEnum;
import com.yeahwell.epu.express.enums.PaymentWayEnum;

public class ShowEnumByCodeDWR {

	public ShowEnumByCodeDWR() {
	}

	public String byCode(String enumType, String code) {
		String cnValue = "";
		if ("ExpressCompanyEnum".equals(enumType)) { // 物流公司
			cnValue = ExpressCompanyEnum.byCode(code);
		} else if ("PackageLockStatusEnum".equals(enumType)) { // 包裹锁定状态
			cnValue = ExpressCompanyEnum.byCode(code);
		} else if ("StationLockStatusEnum".equals(enumType)) { // 站点状态
			cnValue = ExpressCompanyEnum.byCode(code);
		} else if ("OrderSourceEnum".equals(enumType)) { // 订单来源
			cnValue = OrderSourceEnum.byCode(code);
		} else if ("PackageStatusEnum".equals(enumType)) { // 包裹状态
			cnValue = PackageStatusEnum.byCode(code);
		} else if ("PaymentStatusEnum".equals(enumType)) { // 支付状态
			cnValue = PaymentStatusEnum.byCode(code);
		} else if ("PaymentWayEnum".equals(enumType)) { // 支付方式状态
			cnValue = PaymentWayEnum.byCode(code);
		} else if ("ArticleCategoryEnum".equals(enumType)) {
			cnValue = ArticleCategoryEnum.byCode(code);
		} else if ("ArticleStatusEnum".equals(enumType)) {
			cnValue = ArticleStatusEnum.byCode(code);
		} else {
			cnValue = "其他";
		}
		return cnValue;
	}

}
