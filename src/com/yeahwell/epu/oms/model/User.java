package com.yeahwell.epu.oms.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yeahwell.epu.oms.enums.UserStatusEnum;

public class User {

	private Integer userId;
	
	private Integer structId;
	
	/** 令牌*/
	private String token;

	/** 注册账户 */
	private String account;

	/** 真实姓名 */
	private String realName;

	/** 用户绑定邮箱 */
	private String email;

	/** 用户绑定手机 */
	private String cellphone;

	/** 用户状态 I.未激活 T.正常 U.不可用 */
	private String status;
	
	private String statusDesc;

	/** 用户类型  A.后台管理员  M.合作商管理员 S.子站点管理员 C.普通用户  */
	private String type;
	
	private String typeDesc;
	
	private Date createTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStructId() {
		return structId;
	}

	public void setStructId(Integer structId) {
		this.structId = structId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		if(StringUtils.isNotEmpty(status)){
			this.statusDesc = UserStatusEnum.byCode(status);
		}
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(StringUtils.isNotEmpty(type)){
			this.typeDesc = UserStatusEnum.byCode(type);
		}
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
