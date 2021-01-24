package ru.itis.download.utils;

import java.util.*;

public class FileListConverter implements IStringConverter {

	@Override
	public List<String> convert(String files) {

		String[] urls = files.split(",");
		List<String> urlList = new ArrayList<>();

		for (String url : urls)
			urlList.add(url);

		return urlList;
	}
}