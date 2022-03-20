package com.wang.learn.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

public class MapDemo {

    public static void main(String[] args) {
        Table<String, String, Integer> table = HashBasedTable.create();

        table.put("Tom", "Jan", 20);
        table.put("Tom", "Feb", 28);

        table.put("Jerry", "Jan", 29);
        table.put("Jerry", "Feb", 15);

        testTable(table);
    }

    /**
     * BiMap - 双向Map
     */
    public static void biMap() {

    }

    /**
     * Table
     * @param table
     */
    public static void testTable(Table<String, String, Integer> table){
        Integer dayCount = table.get("Jerry", "Jan");
//        System.out.println(dayCount);

        //获得key或value的集合
        Set<String> rowKeys = table.rowKeySet();
        Set<String> columnKeys = table.columnKeySet();
        Collection<Integer> values = table.values();
//        System.out.println(rowKeys + "\n" + columnKeys + "\n" + values);

        //计算key对应的所有value的和
        for(String key : table.rowKeySet()) {
            Set<Map.Entry<String, Integer>> rows = table.row(key).entrySet();
            int total = 0;
            for (Map.Entry<String, Integer> row : rows) {
                total += row.getValue();
            }
//            System.out.println(key + ": " + total);
        }

        // 转换rowKey和columnKey
        Table<String, String, Integer> table1 = Tables.transpose(table);
        Set<Table.Cell<String, String, Integer>> cells = table1.cellSet();
        cells.forEach(cell -> System.out.println(cell.getRowKey() + ", " + cell.getColumnKey() + ": " + cell.getValue()));

        //转为嵌套的Map
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        Map<String, Map<String, Integer>> columnMap = table.columnMap();
        System.out.println(rowMap + "\n" + columnMap);
    }
}
