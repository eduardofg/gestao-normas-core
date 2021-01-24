package org.tcc.gestao.normas.core;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class TestUtils {

	private static ObjectMapper mapper;
	
	private static ObjectMapper mapperInstance() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
		}
		
		return mapper;
	}
	
	public static String objectToJson(Object value) throws Exception {
		return mapperInstance().writeValueAsString(value);
	}
	
	public static String concatenateUrl(String... params) {
		String url = "/";

		Iterator<String> paramsAsIterator = List.of(params).iterator();
		while (paramsAsIterator.hasNext()) {
			url += paramsAsIterator.next();

			if (paramsAsIterator.hasNext()) {
				url += "/";
			}
		}
		return url;
	}
}
