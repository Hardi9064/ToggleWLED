import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Toggle {
    public static void main(String[] args) throws Exception
    {
        // read the config file
        File configFile = new File("config.txt");
        int lastState = 0; // default value if config file doesn't exist
        String http = "http://";
        String hostName = "wled";
        String local = ".local/win&T=";
        if (configFile.exists()) {
            Scanner scanner = new Scanner(configFile);
            lastState = Integer.parseInt(scanner.nextLine());
            hostName = scanner.nextLine();
            scanner.close();
        }

        // toggle the state
        boolean isOn = lastState == 0;
        isOn = !isOn;
        int stateInt = isOn ? 0 : 1;

        // construct the URL and open the connection
        URL url = new URL(http + hostName + local + stateInt);
        URLConnection connection = url.openConnection();
        String contentType = connection.getContentType();

        // save the current state and hostname to the config file
        FileWriter writer = new FileWriter(configFile);
        writer.write(stateInt + "\n");
        writer.write(hostName);
        writer.close();
    }
}