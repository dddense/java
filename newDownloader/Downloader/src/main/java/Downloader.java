package main.java;

import java.net.*;
import java.io.*;
import java.util.*;

public class Downloader {

	public void download(String url, String path) {

		try {
			System.out.println("Start");
			URL link  = new URL(url);
			InputStream in = new BufferedInputStream(link.openStream());
			File file = new File(String.valueOf(UUID.randomUUID()));
			System.out.println(file.getName());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(path + "/" + file.getName() + ".jpg"));
			int i = in.read();
			while (i >= 0) {
            	out.write(i);
            	i = in.read();
        	}
       		in.close();
        	out.close();
        	System.out.println("Success!");
		} catch (MalformedURLException e1) {
			throw new IllegalArgumentException(e1);
		} catch (FileNotFoundException e3) {
			throw new IllegalArgumentException(e3);
		} catch (IOException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
}