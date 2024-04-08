import java.io.IOException;

import Controllers.TextEditorController;
import Models.Document;
import Views.TextEditorView;

public class Main {
    public static void main(String[] args) throws IOException {
        Document document = new Document();
        TextEditorView view = new TextEditorView(document);
        TextEditorController controller = new TextEditorController(document, view);

        // Gán biểu thức lambda cho sự kiện nhấp nút
        controller.handleButtonClick();

        // Mở tệp sử dụng đệ quy
       controller.openFile("C:\\Users\\PC\\Downloads\\nhanvien.txt"); // Thay thế bằng đường dẫn thực tế
      //  controller.openFile("C:\\Users\\PC\\Desktop\\photo.jpg");
     // Hiển thị nội dung văn bản
       // view.show(null)
    }
}

