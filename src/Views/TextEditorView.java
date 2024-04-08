package Views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Models.Document;
import Models.Image;

public class TextEditorView {
    private Document document;
    private JPanel mainPanel;

    public TextEditorView(Document document) {
        this.document = document;
        mainPanel = new JPanel();
    }

    public void show(String text) {
    	System.out.println(text);
    	mainPanel.removeAll();
        mainPanel.add(new JLabel(text));

        // Cập nhật giao diện
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }
    public void showImage(Image imageView) {
        // Hiển thị hình ảnh
        mainPanel.removeAll();
        mainPanel.add(imageView);

        // Cập nhật giao diện
        SwingUtilities.updateComponentTreeUI(mainPanel);
    }
}
