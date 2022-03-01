package lib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {
    private Properties properties = new Properties();

    public GetProperties() {
        try {
            String fileDirectory = Thread.currentThread().getContextClassLoader().getResource("resource.properties").getPath();
            this.properties.load(new FileInputStream(fileDirectory));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
