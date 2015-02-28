package com.yeahwell.epu.oms.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.yeahwell.epu.oms.enums.CorpStructTypeEnum;

public class CorpStruct {

	private Integer structId;
	
	private String structName;
	
	private Integer level;
	
	private Integer parentId;
	
	private String type;
	
	private String typeDesc;
	
	private Date createTime;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(StringUtils.isNotEmpty(type)){
			this.typeDesc = CorpStructTypeEnum.byCode(type);
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
