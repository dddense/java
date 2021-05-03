import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class Bot {

    private String token;
    private String key;

    public Bot() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/bot.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.token = properties.getProperty("bot.token");
        this.key = properties.getProperty("youtube.key");
    }
}
