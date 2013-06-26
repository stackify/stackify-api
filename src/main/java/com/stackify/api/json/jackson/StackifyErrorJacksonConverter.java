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

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackify.api.ApiClient;
import com.stackify.api.EnvironmentDetail;
import com.stackify.api.ErrorItem;
import com.stackify.api.StackifyError;
import com.stackify.api.TraceFrame;
import com.stackify.api.WebRequestDetail;
import com.stackify.api.json.StackifyErrorConverter;

/**
 * Jackson implementation for the StackifyErrorConverter
 * 
 * <p>
 * Example:
 * <pre>
 * {@code
 * StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
 * 		
 * ByteArrayOutputStream stream = new ByteArrayOutputStream();
 * converter.writeToStream(Collections.singletonList(stackifyError), stream);
 * }
 * </pre>
 * 
 * @author Eric Martin
 */
public class StackifyErrorJacksonConverter implements StackifyErrorConverter {

	/**
	 * Jackson object mapper
	 */
	private final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Default constructor
	 */
	public StackifyErrorJacksonConverter() {
		objectMapper.addMixInAnnotations(StackifyError.class, StackifyErrorMixIn.class);
		objectMapper.addMixInAnnotations(ApiClient.class, ApiClientMixIn.class);
		objectMapper.addMixInAnnotations(EnvironmentDetail.class, EnvironmentDetailMixIn.class);
		objectMapper.addMixInAnnotations(ErrorItem.class, ErrorItemMixIn.class);
		objectMapper.addMixInAnnotations(TraceFrame.class, TraceFrameMixIn.class);
		objectMapper.addMixInAnnotations(WebRequestDetail.class, WebRequestDetailMixIn.class);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}
	
	/**
	 * @see com.stackify.api.json.StackifyErrorConverter#writeToStream(com.stackify.api.StackifyError, java.io.OutputStream)
	 */
	@Override
	public void writeToStream(final List<StackifyError> errors, final OutputStream stream) {
		if (errors == null) {
			throw new NullPointerException("StackifyError is null");
		}
		
		if (stream == null) {
			throw new NullPointerException("OutputStream is null");
		}
		
		try {
			objectMapper.writer().writeValue(stream, errors);
		} catch (JsonGenerationException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
