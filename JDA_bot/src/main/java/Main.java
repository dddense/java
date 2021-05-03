import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {

        Bot bot = new Bot();
        try {
            JDA jda = JDABuilder.createDefault(bot.getToken()).build();
        } catch (LoginException e) {
            throw new IllegalStateException(e);
        }
    }
}
