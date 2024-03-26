package org.example;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Result {
    private Map<String, List<String>> mapResult;
    public  String  timeReadFile;
    private  List<String> timeOneOperation;



    public Result(Map<String, List<String>> mapResult, List<String> timeOneOperation) {
        this.mapResult = mapResult;
        this.timeOneOperation = timeOneOperation;
    }

    public Result() {
    }


    public Map<String, List<String>> getMapResult() {
        return mapResult;
    }

    public void setMapResult(Map<String, List<String>> mapResult) {
        this.mapResult = mapResult;
    }

    public  String getTimeReadFile() {
        return timeReadFile;
    }

    public void setTimeReadFile(String timeReadFile) {
        this.timeReadFile = timeReadFile;
    }

    public List<String> getTimeOneOperation() {
        return timeOneOperation;
    }

    public void setTimeOneOperation(List<String> timeOneOperation) {
        this.timeOneOperation = timeOneOperation;
    }
}
