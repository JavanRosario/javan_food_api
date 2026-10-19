package com.javanfood.javanfood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;


public class ResourseUtils {


	public static String getContentFromResourse(String resourseName) {
		try {
			InputStream stream = ResourseUtils.class.getResourceAsStream(resourseName);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException();
		}

	}
}
