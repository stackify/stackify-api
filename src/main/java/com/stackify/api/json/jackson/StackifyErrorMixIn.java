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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stackify.api.ApiClient;
import com.stackify.api.EnvironmentDetail;
import com.stackify.api.ErrorItem;
import com.stackify.api.WebRequestDetail;

/**
 * Jackson Mix-in class containing annotations used for serializing StackifyError objects to JSON
 * 
 * @author Eric Martin
 */
public interface StackifyErrorMixIn {

	@JsonProperty("APIClient")
	ApiClient getApiClient();

	@JsonProperty("EnvironmentDetail")
	EnvironmentDetail getEnvironmentDetail();
	
	@JsonProperty("OccurredEpochMillis")
	Date getOccurredEpochMillis();
	
	@JsonProperty("Error")
	ErrorItem getError();
	
	@JsonProperty("Tags")
	List<String> getTags();
	
	@JsonProperty("WebRequestDetail")
	WebRequestDetail getWebRequestDetail();

	@JsonProperty("ServerVariables")
	Map<String, String> getServerVariables();

	@JsonProperty("CustomProperties")
	Map<String, String> getCustomProperties();

	@JsonProperty("CustomerName")
	String getCustomerName();

	@JsonProperty("UserName")
	String getUserName();
}
