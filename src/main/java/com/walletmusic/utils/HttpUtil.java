package com.walletmusic.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public HttpUtil(String value) {
		this.value = value;
	}
	
	// json to class
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	// read json to string
	public static HttpUtil of(BufferedReader reader ) {
		StringBuilder sb = new StringBuilder();
		try {
			String line = reader.readLine();
			while( line != null ) {
				sb.append(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
