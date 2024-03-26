package org.example;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Result {
    private Map<String[], List<String>> mapResult;
    public static String  timeReadFile;
    private  List<String> timeOneOperation;


    public Result() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(mapResult, result.mapResult) && Objects.equals(timeOneOperation, result.timeOneOperation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapResult, timeOneOperation);
    }

    public Map<String[], List<String>> getMapResult() {
        return mapResult;
    }

    public void setMapResult(Map<String[], List<String>> mapResult) {
        this.mapResult = mapResult;
    }

    public static String getTimeReadFile() {
        return timeReadFile;
    }

    public static void setTimeReadFile(String timeReadFile) {
        Result.timeReadFile = timeReadFile;
    }

    public List<String> getTimeOneOperation() {
        return timeOneOperation;
    }

    public void setTimeOneOperation(List<String> timeOneOperation) {
        this.timeOneOperation = timeOneOperation;
    }

    public Result(Map<String[], List<String>> mapResult, List<String> timeOneOperation) {
        this.mapResult = mapResult;
        this.timeOneOperation = timeOneOperation;
    }
}
