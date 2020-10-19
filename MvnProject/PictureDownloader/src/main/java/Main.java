import com.beust.jcommander.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] argv) {
		
		Args args = new Args();
		JCommander.newBuilder()
  		.addObject(args)
  		.build()
  		.parse(argv);

		ExecutorService executorService = Executors.newFixedThreadPool(args.count);
		Downloader downloader = new Downloader();
		String path = args.folder;
		//ArrayList<String> urls = args.urls;
		String[] urls = args.urls.split(",");
		//System.out.println(urls.size());

		for (String url : urls) {
			executorService.submit(() -> {
				downloader.download(url, path);
			});
		}
	}
}