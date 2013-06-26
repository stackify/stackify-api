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
import com.stackify.api.ApiClient;
import com.stackify.api.json.jackson.ApiClientMixIn;

/**
 * ApiClientMixIn JUnit Test
 * 
 * @author Eric Martin
 */
public class ApiClientMixInTest {

	/**
	 * testSerialization
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testSerialization() throws JsonProcessingException {
		ApiClient.Builder builder = ApiClient.newBuilder();
		builder.name("name");
		builder.version("version");
		ApiClient apiClient = builder.build();

		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixInAnnotations(ApiClient.class, ApiClientMixIn.class);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		String json = mapper.writer().writeValueAsString(apiClient);
		
		Assert.assertEquals("{\"Name\":\"name\",\"Platform\":\"java\",\"Version\":\"version\"}", json);
	}	
}
