package Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import DAO.Conn;

public class EchoChatMultiServer {

    public static final int NUM_OF_THREAD = 4;
    public final static int SERVER_PORT = 7;

    public static void main(String[] args) {
        // Tạo ExecutorService với số luồng cố định
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);

        // Sử dụng try-with-resources để đảm bảo ServerSocket được đóng đúng cách
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client ...");

            // Vòng lặp để chấp nhận nhiều kết nối từ client
            while (true) {
                try {
                    // Chấp nhận kết nối từ client
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);

                    // Tạo một WorkerThread để xử lý kết nối
                    WorkerThread handler = new WorkerThread(socket);
                    executor.execute(handler);
                } catch (IOException e) {
                    System.err.println("Connection Error: " + e.getMessage());
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            // Đảm bảo tắt ExecutorService đúng cách
            shutdownExecutor(executor);
        }
    }

    // Phương thức tắt ExecutorService một cách an toàn
    private static void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Executor did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
