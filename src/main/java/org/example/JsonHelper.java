package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonHelper {
    private final String path;

    public JsonHelper(String path) {
        this.path = path;
    }

    public void createOrUpdate(Result result) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("\"initTime\":").append(result.getTimeReadFile()).append(", ");
            sb.append("\"result\":[");
            sb.append("\n");
            List<String> list = result.getMapResult().keySet().stream().toList();
            for (int i = 0; i < list.size(); i++) {
                sb.append("{");
                sb.append("\"search\":\"").append(list.get(i)).append("\", ");
                List<String> strings = result.getMapResult().get(list.get(i));
                String string = String.join("\", \"", strings);

                sb.append("\"result\":").append("[").append("\"").append(string).append("\"], ");
                sb.append("\"time\":").append(result.getTimeOneOperation().get(i));
                sb.append("}");
                if (i < list.size() - 1) {
                    sb.append(", ");
                }
                sb.append("\n");

            }
            sb.append("]");
            sb.append("}");
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в JSON файл: " + e.getMessage());
        }
    }
}
