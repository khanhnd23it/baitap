package Models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Document {
    private String text;

    public Document() {
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Phương thức lưu văn bản vào tệp sử dụng Stream
    public void save(String filePath) throws IOException {
        List<String> lines = Stream.of(text).toList();
        Files.write(Paths.get(filePath), lines);
    }

    // Phương thức tải văn bản từ tệp sử dụng Stream
    public static Document load(String filePath) throws IOException {
        Document doc = new Document();
        doc.text = Files.lines(Paths.get(filePath)).collect(Collectors.joining("\n"));
        return doc;
    }
}
