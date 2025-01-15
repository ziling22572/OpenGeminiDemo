package com.example.opm.controller;

import io.opengemini.client.api.Point;
import io.opengemini.client.api.Precision;
import io.opengemini.client.api.Query;
import io.opengemini.client.okhttp.OpenGeminiOkhttpClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lingjinli
 * @date 2025年01月13日 17:06
 * @desc 功能点
 */
@RestController
@RequestMapping("/open/gemini")
public class OpenGeminiController {
    @Resource
    private OpenGeminiOkhttpClient openGeminiOkhttpClient;
    //    todo 使用默认的数据库
    private final String db_name = "openGemini";

    /**
     * 写入数据
     *
     * @param measurement 测点
     * @param field       字段
     * @param value       值
     * @return 结果
     */
    @PostMapping("/write")
    public Object writeData(@RequestParam String measurement, @RequestParam String field, @RequestParam String value) {
        Map<String, Object> fieldMap = new HashMap<>();
        Map<String, String> tagdMap = new HashMap<>();
        fieldMap.put(field, value);
        tagdMap.put("mock", "123456");
        Point point = new Point();
        point.setMeasurement(measurement);
        point.setTime(System.currentTimeMillis());
        point.setFields(fieldMap);
        point.setTags(tagdMap);
        point.setPrecision(Precision.PRECISIONMILLISECOND);
        return openGeminiOkhttpClient.write(db_name, point);
    }

    /**
     * 查询数据
     *
     * @param query 查询语句
     * @return 查询结果
     */
    @GetMapping("/query")
    public Object queryData(@RequestParam String query) {
        Query query1 = new Query(query);
        query1.setDatabase(db_name);
        query1.setCommand(query);
        // 调用 OpenGemini 客户端的查询方法
        try {
            // 执行查询操作
            return openGeminiOkhttpClient.query(query1);
        } catch (Exception e) {
            // 异常处理
            return "Error executing query: " + e.getMessage();
        }
    }

}
