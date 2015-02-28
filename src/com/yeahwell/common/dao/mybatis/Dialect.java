package com.yeahwell.common.dao.mybatis;

public abstract interface Dialect
{
  public abstract boolean supportsLimit();

  public abstract boolean supportOffsetLimit();

  public abstract String getLimitString(String paramString, int paramInt1, int paramInt2);
}