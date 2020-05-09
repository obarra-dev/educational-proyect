package com.obarra.springbootjdbc.repository.handler;

import com.obarra.springbootjdbc.model.Billing;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BillingHandler implements RowCallbackHandler {
    private Map<Long, Map<String, Object>> billingsMap;

    public  BillingHandler(Map<Long, Map<String, Object>> billingsMap) {
        this.billingsMap = billingsMap;
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {

        Map<String, Object> tableMap = new HashMap<>();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int i = 1;
            while (i <= resultSetMetaData.getColumnCount()) {
                tableMap.put(resultSetMetaData.getColumnName(i), rs.getObject(i));
                i++;
            }

        billingsMap.put(Long.valueOf((Integer)tableMap.get("BILLING_ID")), tableMap);


         System.out.println(tableMap);




    }
}
