package com.yeahwell.epu.admin.model;

public class SessionUser {

	private Integer userId;
	
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

	/** 用户类型  A.后台管理员  M.合作商管理员 S.子站点管理员 C.普通用户  */
	private String type;
	
	private Integer structId;
	
	private String structName;
	
	private Integer level;
	
	private Integer parentId;
	
	private String structType;
	
	private Integer stationId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
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
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStructId() {
		return structId;
	}

	public void setStructId(Integer structId) {
		this.structId = structId;
	}

	public String getStructName() {
		return structName;
	}

	public void setStructName(String structName) {
		this.structName = structName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getStructType() {
		return structType;
	}

	public void setStructType(String structType) {
		this.structType = structType;
	}

}
