import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Args {

	@Parameter(names = "--mode")
	public String mode;

	@Parameter(names = "--count")
	public int count = 1;
	
	@Parameter(names = "--files"/*, listConverter = FileListConverter.class*/)
	public String urls;
	//public List<String> urls;

	@Parameter(names = "--folder")
	public String folder;
}