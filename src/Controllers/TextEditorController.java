package Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import Models.Document;
import Models.Image;
import Views.TextEditorView;

public class TextEditorController {
    private Document document;
    private TextEditorView view;

    public TextEditorController(Document document, TextEditorView view) {
        this.document = document;
        this.view = view;
    }

    // Biểu thức lambda cho sự kiện nhấp nút
    public void handleButtonClick() throws IOException {
        // Đọc văn bản từ tệp
        String text = Document.load("C:\\Users\\PC\\Downloads\\nhanvien.txt").getText();

        // Hiển thị văn bản
        view.show(text);
    }
    // Phương thức mở tệp sử dụng đệ quy
    public void openFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        if (Files.isDirectory(filePath)) {
            for (Path file : Files.newDirectoryStream(filePath)) {
                openFile(file.toString());
            }
        } else if (Files.isRegularFile(filePath)) {
            document = Document.load(path);
           // view.show(path);
        }
    }
    public void openImage(String path) throws IOException {
        // Đọc hình ảnh từ tệp
        BufferedImage image = ImageIO.read(new File("C:\\Users\\PC\\Desktop\\photo.jpg"));

        // Hiển thị hình ảnh
        Image imageView = new Image();
        imageView.setImage(image);
        view.showImage(imageView);
    }
}
