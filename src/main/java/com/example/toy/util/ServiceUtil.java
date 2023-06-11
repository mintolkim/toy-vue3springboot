package com.example.toy.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ServiceUtil {
    public static <T> Map<String, Object> serviceUtilMethod(Supplier<T> method , Object successMsg, String failMsg ){
        Map<String, Object> result = new HashMap<>();
        try {
            T operationResult = method.get();
            result.put("status", "200");
            result.put("message", successMsg);
            result.put("result", operationResult);
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", failMsg);
            result.put("errorMsg", e.getMessage());
        }
        return result;
    }
}
