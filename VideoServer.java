import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class VideoServer {
static Socket socket;
static VideoCapture camera;
public static void main(String[] args) {
System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
try {
ServerSocket serverSocket = new
ServerSocket(2323);
socket = serverSocket.accept();
camera = new VideoCapture(0);
if (!camera.isOpened()) {
System.out.println("Error! Camera
can’t be opened!");
return;
}
10
11
else {
System.out.println("Camera Found!");
}
Mat frame = new Mat();
while (true) {
if (camera.read(frame)) {
MatOfByte outputStream = new
MatOfByte();
Imgcodecs.imencode(".png", frame,
outputStream);
byte[] buffer = outputStream.toArray();
OutputStream outStream =
socket.getOutputStream();
DataOutputStream dataOutputStream = new
DataOutputStream(outStream);
dataOutputStream.writeInt
(buffer.length);
dataOutputStream.write(buffer, 0,
buffer.length);
}
}
} catch (IOException e) {
e.printStackTrace();
}
}
}