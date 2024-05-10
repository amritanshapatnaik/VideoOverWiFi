import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;
import java.io.*;
import java.net.*;

public class VideoClient {
    static Socket socket;

public static void main(String[] args) {
try {
socket = new Socket("localhost", 2323);
while (true) {
InputStream inStream =
socket.getInputStream();
DataInputStream dataInputStream = new
DataInputStream(inStream);
int length = dataInputStream.readInt();
byte[] buffer = new byte[length];
12
13
dataInputStream.readFully(buffer, 0, buffer.length);
Mat frame = Imgcodecs.imdecode(new MatOfByte(buffer), Imgcodecs.IMREAD_UNCHANGED);
HighGui.imshow("Frame", frame);
HighGui.waitKey(1);
}
} catch (IOException e) {
e.printStackTrace();
}
}
}