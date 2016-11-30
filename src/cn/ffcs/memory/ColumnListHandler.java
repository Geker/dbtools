package cn.ffcs.memory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @Description:
 * @Copyright: Copyright (c) 2013 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 黄君毅 2013-4-12
 * @version 1.00.00
 * @history:
 * 
 */
public class ColumnListHandler<T> implements ResultSetHandler<List<T>> {
	private final int columnIndex;
	private final String columnName;
	private final Class<T> type;

	public ColumnListHandler(Class<T> type) {
		this(1, null, type);
	}

	public ColumnListHandler(int columnIndex, Class<T> type) {
		this(columnIndex, null, type);
	}

	public ColumnListHandler(String columnName, Class<T> type) {
		this(1, columnName, type);
	}

	private ColumnListHandler(int columnIndex, String columnName, Class<T> type) {
		this.columnIndex = columnIndex;
		this.columnName = columnName;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> handle(ResultSet rs) {
		try {
			List<T> rows = new ArrayList<T>();
			while (rs.next()) {
				rows.add((T) processColumn(rs));
			}
			return rows;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Object processColumn(ResultSet rs) throws SQLException {
		int index;
		if (this.columnName == null) {
			index = columnIndex;
		} else {
			index = rs.findColumn(columnName);
		}

		if (!type.isPrimitive() && rs.getObject(index) == null) {
			return null;
		}

		return BeanProcessor.generateValueByType(rs,index,type);
	}
}
