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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson Mix-in class containing annotations used for serializing WebRequestDetail objects to JSON
 * 
 * @author Eric Martin
 */
public interface WebRequestDetailMixIn {

	@JsonProperty("UserIPAddress")
	String getUserIpAddress();

	@JsonProperty("HttpMethod")
	String getHttpMethod();

	@JsonProperty("RequestProtocol")
	String getRequestProtocol();

	@JsonProperty("RequestUrl")
	String getRequestUrl();

	@JsonProperty("RequestUrlRoot")
	String getRequestUrlRoot();

	@JsonProperty("ReferralUrl")
	String getReferralUrl();

	@JsonProperty("Headers")
	Map<String, String> getHeaders();

	@JsonProperty("Cookies")
	Map<String, String> getCookies();

	@JsonProperty("QueryString")
	Map<String, String> getQueryString();

	@JsonProperty("PostData")
	Map<String, String> getPostData();

	@JsonProperty("SessionData")
	Map<String, String> getSessionData();

	@JsonProperty("PostDataRaw")
	String getPostDataRaw();

	@JsonProperty("MVCAction")
	String getMvcAction();

	@JsonProperty("MVCController")
	String getMvcController();

	@JsonProperty("MVCArea")
	String getMvcArea();
}
