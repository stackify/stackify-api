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

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackify.api.WebRequestDetail;
import com.stackify.api.json.jackson.WebRequestDetailMixIn;

/**
 * WebRequestDetailMixIn JUnit Test
 * 
 * @author Eric Martin
 */
public class WebRequestDetailMixInTest {
	
	/**
	 * testSerialization
	 * @throws JsonProcessingException 
	 */
	@Test
	public void testSerialization() throws JsonProcessingException {
		WebRequestDetail.Builder builder = WebRequestDetail.newBuilder();
		builder.userIpAddress("userIpAddress");
		builder.httpMethod("httpMethod");
		builder.requestProtocol("requestProtocol");
		builder.requestUrl("requestUrl");
		builder.requestUrlRoot("requestUrlRoot");
		builder.referralUrl("referralUrl");
		builder.headers(Collections.singletonMap("headersKey", "headersValue"));
		builder.cookies(Collections.singletonMap("cookiesKey", "cookiesValue"));
		builder.queryString(Collections.singletonMap("queryStringKey", "queryStringValue"));
		builder.postData(Collections.singletonMap("postDataKey", "postDataValue"));
		builder.sessionData(Collections.singletonMap("sessionDataKey", "sessionDataValue"));
		builder.postDataRaw("postDataRaw");
		builder.mvcAction("mvcAction");
		builder.mvcController("mvcController");
		builder.mvcArea("mvcArea");
		WebRequestDetail webRequest = builder.build();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixInAnnotations(WebRequestDetail.class, WebRequestDetailMixIn.class);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		String json = mapper.writer().writeValueAsString(webRequest);
		
		Assert.assertEquals("{\"UserIPAddress\":\"userIpAddress\",\"HttpMethod\":\"httpMethod\",\"RequestProtocol\":\"requestProtocol\",\"RequestUrl\":\"requestUrl\",\"RequestUrlRoot\":\"requestUrlRoot\",\"ReferralUrl\":\"referralUrl\",\"Headers\":{\"headersKey\":\"headersValue\"},\"Cookies\":{\"cookiesKey\":\"cookiesValue\"},\"QueryString\":{\"queryStringKey\":\"queryStringValue\"},\"PostData\":{\"postDataKey\":\"postDataValue\"},\"SessionData\":{\"sessionDataKey\":\"sessionDataValue\"},\"PostDataRaw\":\"postDataRaw\",\"MVCAction\":\"mvcAction\",\"MVCController\":\"mvcController\",\"MVCArea\":\"mvcArea\"}", json);
	}
}
