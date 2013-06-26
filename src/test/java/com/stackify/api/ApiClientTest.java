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
package com.stackify.api;

import org.junit.Assert;
import org.junit.Test;

import com.stackify.api.ApiClient;

/**
 * ApiClient JUnit Test
 * 
 * @author Eric Martin
 */
public class ApiClientTest {

	/**
	 * testBuilder
	 */
	@Test
	public void testBuilder() {
		String name = "name";
		String version = "version";

		ApiClient.Builder builder = ApiClient.newBuilder();
		builder.name(name);
		builder.version(version);
		ApiClient apiClient = builder.build();
		
		Assert.assertNotNull(apiClient);
		
		Assert.assertEquals(name, apiClient.getName());
		Assert.assertEquals("java", apiClient.getPlatform());
		Assert.assertEquals(version, apiClient.getVersion());		
	}
}
