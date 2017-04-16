package net.brightblack9911.pcrCore.basic;

import java.sql.ResultSet;

public interface Query
{
	public boolean existRecord();
	
	public ResultSet getResult();
}
