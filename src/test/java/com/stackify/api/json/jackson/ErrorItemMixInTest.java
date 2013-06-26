/*
 * Copyright 2013 Stackify
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stackify.api.json.jackson;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackify.api.ErrorItem;
import com.stackify.api.TraceFrame;
import com.stackify.api.json.jackson.ErrorItemMixIn;
import com.stackify.api.json.jackson.TraceFrameMixIn;

/**
 * ErrorItemMixIn JUnit Test
 * 
 * @author Eric Martin
 */
public class ErrorItemMixInTest {

	/**
	 * testSerialization
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testSerialization() throws JsonProcessingException {
		ErrorItem.Builder builder = ErrorItem.newBuilder();
		builder.message("message");
		builder.errorType("className");
		builder.sourceMethod("methodName");
		builder.errorTypeCode("errorTypeCode");
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("key1", "value1");
		data.put("key2", "value2");
		builder.data(data);
		
		TraceFrame.Builder frameBuilder = TraceFrame.newBuilder();
		frameBuilder.codeFileName("fileName");
		frameBuilder.lineNum(Integer.valueOf(14));
		frameBuilder.method("methodName");
		frameBuilder.libraryName("libraryName");
		builder.stackTrace(Collections.singletonList(frameBuilder.build()));
		
		ErrorItem.Builder causeBuilder = ErrorItem.newBuilder();
		causeBuilder.message("causeMessage");
		causeBuilder.errorType("causeClassName");
		causeBuilder.sourceMethod("causeMethodName");
		builder.innerError(causeBuilder.build());
		
		ErrorItem errorDetail = builder.build();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixInAnnotations(ErrorItem.class, ErrorItemMixIn.class);
		mapper.addMixInAnnotations(TraceFrame.class, TraceFrameMixIn.class);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
				
		String json = mapper.writer().writeValueAsString(errorDetail);
		
		Assert.assertEquals("{\"Message\":\"message\",\"ErrorType\":\"className\",\"ErrorTypeCode\":\"errorTypeCode\",\"Data\":{\"key2\":\"value2\",\"key1\":\"value1\"},\"SourceMethod\":\"methodName\",\"StackTrace\":[{\"CodeFileName\":\"fileName\",\"LineNum\":14,\"Method\":\"methodName\",\"LibraryName\":\"libraryName\"}],\"InnerError\":{\"Message\":\"causeMessage\",\"ErrorType\":\"causeClassName\",\"SourceMethod\":\"causeMethodName\"}}", json);
	}
}
