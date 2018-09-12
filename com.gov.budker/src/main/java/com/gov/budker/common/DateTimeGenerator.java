package com.gov.budker.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTimeGenerator {

    public List<Map> getYear() {
        List<Map> dataList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        for (int i = 1970; i < 2150; i++) {
            result = new HashMap<>();
            result.put("year", i);
            dataList.add(result);
        }
        return dataList;
    }

    public List<Map> getMonth() {
        List<Map> result = new ArrayList<>();
        Map<String, Object> tp = new HashMap<>();
        tp.put("monthId", 1);
        tp.put("monthName", "Januari");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 2);
        tp.put("monthName", "Februari");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 3);
        tp.put("monthName", "Maret");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 4);
        tp.put("monthName", "April");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 5);
        tp.put("monthName", "Mei");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 6);
        tp.put("monthName", "Juni");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 7);
        tp.put("monthName", "Juli");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 8);
        tp.put("monthName", "Agustus");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 9);
        tp.put("monthName", "September");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 10);
        tp.put("monthName", "Oktober");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 11);
        tp.put("monthName", "November");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("monthId", 12);
        tp.put("monthName", "Desember");
        result.add(tp);
        return result;
    }

    public List<Map> getTriwulan() {
        List<Map> result = new ArrayList<>();
        Map<String, Object> tp = new HashMap<>();
        tp.put("twId", 1);
        tp.put("twName", "1 (Satu)");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("twId", 2);
        tp.put("twName", "2 (Dua)");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("twId", 3);
        tp.put("twName", "3 (Tiga)");
        result.add(tp);
        tp = new HashMap<>();
        tp.put("twId", 4);
        tp.put("twName", "4 (Empat)");
        result.add(tp);
        return result;
    }
}
