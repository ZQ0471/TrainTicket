package com.hdu.train.temp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
/*
* String inputFilePath = "D:\\Code\\Train\\src\\main\\java\\com\\hdu\\train\\temp\\data.json";
        String outputFilePath = "D:\\Code\\Train\\src\\main\\java\\com\\hdu\\train\\temp\\result.json";*/

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JsonFileTraversal {
    private static String lastNonNullTrainNo = null;

    public static void main(String[] args) {
        String inputFilePath = "D:\\Code\\Train\\src\\main\\java\\com\\hdu\\train\\temp\\data.json";
        String outputFilePath = "D:\\Code\\Train\\src\\main\\java\\com\\hdu\\train\\temp\\result.json";

        try {
            // 创建ObjectMapper对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 读取JSON文件为JsonNode对象
            JsonNode jsonNode = objectMapper.readTree(new File(inputFilePath));

            // 调用遍历方法处理JSON数据并将结果写入文件
            traverseJson(jsonNode, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void traverseJson(JsonNode jsonNode, String outputFilePath) {
        try {
            // 创建ObjectMapper对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 结果JsonNode，用于存储遍历结果
            JsonNode resultNode = traverse(jsonNode, objectMapper);

            // 将结果写入文件
            objectMapper.writeValue(new File(outputFilePath), resultNode);

            System.out.println("Successfully written to " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonNode traverse(JsonNode jsonNode, ObjectMapper objectMapper) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = objectMapper.createObjectNode();

            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();
                JsonNode value = entry.getValue();

                if (key.equals("train_no") && value.isTextual()) {
                    String trainNo = value.asText();
                    if (trainNo.isEmpty() && lastNonNullTrainNo != null) {
                        objectNode.put(key, lastNonNullTrainNo);
                    } else {
                        objectNode.put(key, trainNo);
                        lastNonNullTrainNo = trainNo;
                    }
                } else {
                    objectNode.set(key, traverse(value, objectMapper));
                }
            }
            return objectNode;
        } else if (jsonNode.isArray()) {
            return traverseArray(jsonNode, objectMapper);
        } else {
            return jsonNode;
        }
    }

    private static JsonNode traverseArray(JsonNode jsonNode, ObjectMapper objectMapper) {
        ArrayNode arrayNode = objectMapper.createArrayNode();

        for (JsonNode element : jsonNode) {
            JsonNode processedElement = traverse(element, objectMapper);
            arrayNode.add(processedElement);
        }

        return arrayNode;
    }
}