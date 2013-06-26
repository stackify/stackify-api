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
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackify.api.ApiClient;
import com.stackify.api.EnvironmentDetail;
import com.stackify.api.ErrorItem;
import com.stackify.api.StackifyError;
import com.stackify.api.TraceFrame;
import com.stackify.api.WebRequestDetail;
import com.stackify.api.json.jackson.ApiClientMixIn;
import com.stackify.api.json.jackson.EnvironmentDetailMixIn;
import com.stackify.api.json.jackson.ErrorItemMixIn;
import com.stackify.api.json.jackson.StackifyErrorMixIn;
import com.stackify.api.json.jackson.TraceFrameMixIn;
import com.stackify.api.json.jackson.WebRequestDetailMixIn;

/**
 * StackifyErrorMixIn JUnit Test
 * 
 * @author Eric Martin
 */
public class StackifyErrorMixInTest {
	
	/**
	 * testSerialization
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testSerialization() throws JsonProcessingException {
		ApiClient.Builder apiBuilder = ApiClient.newBuilder();
		apiBuilder.name("name");
		apiBuilder.version("version");
		ApiClient apiClient = apiBuilder.build();

		EnvironmentDetail.Builder envBuilder = EnvironmentDetail.newBuilder();
		envBuilder.deviceName("device");
		envBuilder.appName("app");
		envBuilder.appLocation("location");
		EnvironmentDetail environment = envBuilder.build();
		
		ErrorItem.Builder detailBuilder = ErrorItem.newBuilder();
		detailBuilder.message("message");
		detailBuilder.errorType("className");
		detailBuilder.sourceMethod("methodName");
		ErrorItem errorDetail = detailBuilder.build();
				
		StackifyError.Builder errorBuilder = StackifyError.newBuilder();
		errorBuilder.apiClient(apiClient);
		errorBuilder.environmentDetail(environment);
		errorBuilder.occurredEpochMillis(new Date(1372779075136l));
		errorBuilder.error(errorDetail);
		errorBuilder.webRequestDetail(WebRequestDetail.newBuilder().httpMethod("GET").requestUrl("http://something").build());
		errorBuilder.tags(Collections.singletonList("tag1"));
		errorBuilder.serverVariables(Collections.singletonMap("serverKey", "serverValue"));
		errorBuilder.customProperties(Collections.singletonMap("customKey", "customValue"));
		errorBuilder.customerName("customerName");
		errorBuilder.userName("userName");
		
		StackifyError error = errorBuilder.build();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixInAnnotations(StackifyError.class, StackifyErrorMixIn.class);
		mapper.addMixInAnnotations(ApiClient.class, ApiClientMixIn.class);
		mapper.addMixInAnnotations(EnvironmentDetail.class, EnvironmentDetailMixIn.class);
		mapper.addMixInAnnotations(ErrorItem.class, ErrorItemMixIn.class);
		mapper.addMixInAnnotations(TraceFrame.class, TraceFrameMixIn.class);
		mapper.addMixInAnnotations(WebRequestDetail.class, WebRequestDetailMixIn.class);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		String json = mapper.writer().writeValueAsString(error);
		
		Assert.assertEquals("{\"APIClient\":{\"Name\":\"name\",\"Platform\":\"java\",\"Version\":\"version\"},\"EnvironmentDetail\":{\"DeviceName\":\"device\",\"AppName\":\"app\",\"AppLocation\":\"location\"},\"OccurredEpochMillis\":1372779075136,\"Error\":{\"Message\":\"message\",\"ErrorType\":\"className\",\"SourceMethod\":\"methodName\"},\"WebRequestDetail\":{\"HttpMethod\":\"GET\",\"RequestUrl\":\"http://something\"},\"Tags\":[\"tag1\"],\"ServerVariables\":{\"serverKey\":\"serverValue\"},\"CustomProperties\":{\"customKey\":\"customValue\"},\"CustomerName\":\"customerName\",\"UserName\":\"userName\"}", json);
	}	
}
