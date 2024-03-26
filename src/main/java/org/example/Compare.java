package org.example;

import java.awt.geom.RectangularShape;
import java.util.*;
import java.util.stream.Collectors;

public class Compare {
    private final List<String[]> listTxt;
    private final Map<String,String> mapCsv;

    public Compare(List<String[]> listTxt, Map<String, String> mapCsv) {
        this.listTxt = listTxt;
        this.mapCsv = mapCsv;
    }

    public Result compareWord(){
        String glasn = "ауоеюяийыэ";
        Result resultClass = new Result();
        List<String> listTimeOneOperation = new ArrayList<>();
        Map<String[], List<String>> result = new HashMap<>();
        for (var line : mapCsv.keySet()){
            int countWord = line.split(" ").length;
            boolean flag = false;
            long start = System.currentTimeMillis();
            for (var array : listTxt){
                int count = 0;
                for (var word : array){
                    String changingWord = word.toLowerCase().replaceAll(",", "")
                            .replaceAll("\\.", "")
                            .replaceAll("\\?", "")
                            .replaceAll("!", "")
                            .toLowerCase();
                    //Проверка на падежи
                    String last = String.valueOf(changingWord.charAt(changingWord.length()-1));
                    if (glasn.contains(last)){
                        changingWord = changingWord.substring(0,changingWord.length()-1);
                    }
                    if (line.contains(changingWord)){
                        count++;
                    }
                }
                String maybe = mapCsv.get(line);
                if ((double) count /countWord >= 0.7) { //Если процент слов совпадает на 70+ %
                    if (result.get(array) == null) {
                        flag = true;
                        List<String> list = new ArrayList<>();
                        list.add(mapCsv.get(line));
                        result.put(array, list);
                        resultClass.setMapResult(result);
                    } else {
                        flag = true;
                        List<String> strings = result.get(array);
                        strings.add(mapCsv.get(line));
                        result.put(array,strings);
                        Map<String[], List<String>> mapResult = resultClass.getMapResult();
                        mapResult.put(array,strings);
                    }
                }
            }
            long stop = System.currentTimeMillis();
            if (flag){
                listTimeOneOperation.add(String.valueOf(stop-start));
            }

        }
        resultClass.setTimeOneOperation(listTimeOneOperation);
        return resultClass;
    }


//    public Result compareWord2(){
//        for (var line : listTxt){   //Проходимся по листу запросов
//
//        }
//
//    }



}
