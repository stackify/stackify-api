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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson Mix-in class containing annotations used for serializing EnvironmentDetail objects to JSON
 * 
 * @author Eric Martin
 */
public interface EnvironmentDetailMixIn {

	@JsonProperty("DeviceName")
	String getDeviceName();

	@JsonProperty("AppName")
 	String getAppName();

	@JsonProperty("AppLocation")
	String getAppLocation();
	
	@JsonProperty("ConfiguredAppName")
	String getConfiguredAppName();
	
	@JsonProperty("ConfiguredLocationName")
	String getConfiguredLocationName();
	
	@JsonProperty("ConfiguredEnvironmentName")
	String getConfiguredEnvironmentName();
}
