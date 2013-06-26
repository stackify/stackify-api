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

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackify.api.TraceFrame;
import com.stackify.api.json.jackson.TraceFrameMixIn;

/**
 * TraceFrameMixIn JUnit Test
 * 
 * @author Eric Martin
 */
public class TraceFrameMixInTest {

	/**
	 * testSerialization
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testSerialization() throws JsonProcessingException {
		TraceFrame.Builder builder = TraceFrame.newBuilder();
		builder.codeFileName("fileName");
		builder.lineNum(Integer.valueOf(14));
		builder.method("methodName");
		builder.libraryName("libraryName");
		TraceFrame stackFrame = builder.build();

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixInAnnotations(TraceFrame.class, TraceFrameMixIn.class);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		String json = mapper.writer().writeValueAsString(stackFrame);
		
		Assert.assertEquals("{\"CodeFileName\":\"fileName\",\"LineNum\":14,\"Method\":\"methodName\",\"LibraryName\":\"libraryName\"}", json);
	}
}
