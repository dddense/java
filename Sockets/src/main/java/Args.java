import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


@Parameters(separators = "=")
public class Args {

    @Parameter(names = {"--server-ip", "--i"})
    public String host = "localhost";

    @Parameter(names = {"--server-port", "--p"})
    public int port = 7777;
}
