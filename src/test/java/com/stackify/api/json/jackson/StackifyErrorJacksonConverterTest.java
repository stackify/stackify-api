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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.stackify.api.ApiClient;
import com.stackify.api.EnvironmentDetail;
import com.stackify.api.ErrorItem;
import com.stackify.api.StackifyError;
import com.stackify.api.json.StackifyErrorConverter;
import com.stackify.api.json.jackson.StackifyErrorJacksonConverter;

/**
 * StackifyErrorJacksonConverter JUnit Test
 * 
 * @author Eric Martin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StackifyErrorJacksonConverter.class, ObjectMapper.class})
public class StackifyErrorJacksonConverterTest {

	/**
	 * testWriteToStream
	 */
	@Test
	public void testWriteToStream() {
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
		StackifyError error = errorBuilder.build();
				
		StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		converter.writeToStream(Collections.singletonList(error), stream);
		
		Assert.assertEquals("[{\"APIClient\":{\"Name\":\"name\",\"Platform\":\"java\",\"Version\":\"version\"},\"EnvironmentDetail\":{\"DeviceName\":\"device\",\"AppName\":\"app\",\"AppLocation\":\"location\"},\"OccurredEpochMillis\":1372779075136,\"Error\":{\"Message\":\"message\",\"ErrorType\":\"className\",\"SourceMethod\":\"methodName\"}}]", stream.toString());
	}
	
	/**
	 * testWriteToStreamWithNullError
	 */
	@Test(expected = NullPointerException.class)
	public void testWriteToStreamWithNullError() {
		StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
		converter.writeToStream(null, new ByteArrayOutputStream());
	}
	
	/**
	 * testWriteToStreamWithNullStream
	 */
	@Test(expected = NullPointerException.class)
	public void testWriteToStreamWithNullStream() {
		StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
		converter.writeToStream(Collections.singletonList(StackifyError.newBuilder().build()), null);
	}
	
	/**
	 * testWriterThrowsJsonGenerationException
	 * @throws Exception 
	 */
	@Test(expected = RuntimeException.class)
	public void testWriterThrowsJsonGenerationException() throws Exception {
		ObjectWriter writer = Mockito.mock(ObjectWriter.class);
		Mockito.doThrow(new JsonGenerationException("")).when(writer).writeValue(Mockito.any(OutputStream.class), Mockito.anyObject());
		
		ObjectMapper mapper = PowerMockito.mock(ObjectMapper.class);
		Mockito.when(mapper.writer()).thenReturn(writer);
		
		PowerMockito.whenNew(ObjectMapper.class).withNoArguments().thenReturn(mapper);

		StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
		converter.writeToStream(Collections.singletonList(StackifyError.newBuilder().build()), new ByteArrayOutputStream());
	}
	
	/**
	 * testWriterThrowsJsonMappingException
	 * @throws Exception 
	 */
	@Test(expected = RuntimeException.class)
	public void testWriterThrowsJsonMappingException() throws Exception {
		ObjectWriter writer = Mockito.mock(ObjectWriter.class);
		Mockito.doThrow(new JsonMappingException("")).when(writer).writeValue(Mockito.any(OutputStream.class), Mockito.anyObject());
		
		ObjectMapper mapper = PowerMockito.mock(ObjectMapper.class);
		Mockito.when(mapper.writer()).thenReturn(writer);
		
		PowerMockito.whenNew(ObjectMapper.class).withNoArguments().thenReturn(mapper);

		StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
		converter.writeToStream(Collections.singletonList(StackifyError.newBuilder().build()), new ByteArrayOutputStream());
	}
	
	/**
	 * testWriterThrowsIOException
	 * @throws Exception 
	 */
	@Test(expected = RuntimeException.class)
	public void testWriterThrowsIOException() throws Exception {
		ObjectWriter writer = Mockito.mock(ObjectWriter.class);
		Mockito.doThrow(new IOException()).when(writer).writeValue(Mockito.any(OutputStream.class), Mockito.anyObject());
		
		ObjectMapper mapper = PowerMockito.mock(ObjectMapper.class);
		Mockito.when(mapper.writer()).thenReturn(writer);
		
		PowerMockito.whenNew(ObjectMapper.class).withNoArguments().thenReturn(mapper);

		StackifyErrorConverter converter = new StackifyErrorJacksonConverter();
		converter.writeToStream(Collections.singletonList(StackifyError.newBuilder().build()), new ByteArrayOutputStream());
	}
}
