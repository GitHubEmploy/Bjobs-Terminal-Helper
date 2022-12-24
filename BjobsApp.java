import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class BjobsApp extends JFrame {

  private JTextArea textArea;

  public BjobsApp() {
    // Create the text area for displaying the output
    textArea = new JTextArea();

    // Add the text area to the frame
    add(textArea);

    // Set the size and title of the frame
    setSize(600, 400);
    setTitle("bjobs Output");

    // Set the default close operation and make the frame visible
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void updateOutput() {
    // Use the ProcessBuilder class to execute the "bjobs" command
    ProcessBuilder pb = new ProcessBuilder("bjobs");
    try {
      Process p = pb.start();

      // Read the output of the command using a BufferedReader
      BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

      // Clear the text area
      textArea.setText("");

      // Read each line of the output and append it to the text area
      String line;
      while ((line = reader.readLine()) != null) {
        textArea.append(line + "\n");
      }

      // Close the reader
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    BjobsApp app = new BjobsApp();

    // Use a ScheduledThreadPoolExecutor to update the output every 1 second
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    executor.scheduleAtFixedRate(() -> app.updateOutput(), 0, 1, TimeUnit.SECONDS);
  }
}
