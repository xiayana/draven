package com.lab8.engine.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 提取出字符串中的json
 * @author xy
 * @since 2022-03-17 18:50:08
 */
public class FindJsonUtil {
    public static List<String> format(String jsonStr) {
        Stack<JsonStack> stringStack = new Stack<JsonStack>();
        List<Integer> indexList = new LinkedList<Integer>();
        List<String> jsonList = new ArrayList<String>();
        for (int i = 0;i<jsonStr.length();i++) {
            if(jsonStr.charAt(i)=='{'||jsonStr.charAt(i)=='['){
                stringStack.push(new JsonStack(i,jsonStr.charAt(i)));
            }else if(jsonStr.charAt(i)=='}'||jsonStr.charAt(i)==']'){
                if(!stringStack.empty()){
                    JsonStack js = stringStack.peek();
                    if(jsonStr.charAt(i)=='}'&&js.getStr() =='{'){
                        js = stringStack.pop();
                    }else if(jsonStr.charAt(i)==']'&&js.getStr() =='['){
                        js = stringStack.pop();
                    }
                    indexList.add(js.getIndex());
                    indexList.add(i);
                }
                if(stringStack.empty()){
                    String tempStr= getJsonStr(indexList,jsonStr);
                    if(!(tempStr==null||tempStr.isEmpty())){
                        jsonList.add(tempStr);
                    }
                    indexList.clear();
                }
            }
        }
        if(indexList!=null && indexList.size()>0){
            String tempStr= getJsonStr(indexList,jsonStr);
            if(!(tempStr==null||tempStr.isEmpty())) {
                jsonList.add(tempStr);
            }
        }
        return jsonList;

    }

    private static String getJsonStr(List<Integer> indexList,String str) {
        String temp= "";
        for(int i = indexList.size() -1 ; i>=0 ; i=i-2){
            try {
                temp = str.substring(indexList.get(i - 1), indexList.get(i)+1);
                JSON.parse(temp);
                return str.substring(indexList.get(i - 1), indexList.get(i)+1);
            }catch (Exception e){
                continue;
            }
        }
        return null;
    }

    static class JsonStack{
        private Integer index;
        private char str;

        public JsonStack(Integer index, char str) {
            this.index = index;
            this.str = str;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Character getStr() {
            return str;
        }

        public void setStr(Character str) {
            this.str = str;
        }
    }
}
