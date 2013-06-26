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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stackify.api.ErrorItem;
import com.stackify.api.TraceFrame;

/**
 * Jackson Mix-in class containing annotations used for serializing ErrorItem objects to JSON
 * 
 * @author Eric Martin
 */
public interface ErrorItemMixIn {

	@JsonProperty("Message")
	String getMessage();

	@JsonProperty("ErrorType")
	String getErrorType();

	@JsonProperty("ErrorTypeCode")
	String getErrorTypeCode();

	@JsonProperty("Data")
	Map<String, String> getData();

	@JsonProperty("SourceMethod")
	String getSourceMethod();

	@JsonProperty("StackTrace")
	List<TraceFrame> getStackTrace();

	@JsonProperty("InnerError")
	ErrorItem getInnerError();
}
