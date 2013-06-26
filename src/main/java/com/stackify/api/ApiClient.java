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

/**
 * Identifies the source project calling the Stackify API
 * 
 * <p>
 * Example:
 * <pre>
 * {@code
 * ApiClient.Builder builder = ApiClient.newBuilder();
 * builder.name("stackify-error-log4j12");
 * builder.version("1.0.0");
 * ApiClient apiClient = builder.build();
 * }
 * </pre>
 * 
 * @author Eric Martin
 */
public class ApiClient {

	/**
	 * Project name
	 */
	private final String name;
	
	/**
	 * Platform ("java")
	 */
	private final String platform = "java";
	
	/**
	 * Project version
	 */
	private final String version;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param builder The Builder object that contains all of the values for initialization
	 */
	private ApiClient(final Builder builder) {
		this.name = builder.name;
		this.version = builder.version;
	}

	/**
	 * @return A new instance of the Builder
	 */
	public static Builder newBuilder() {
		return new Builder();
	}

	/**
	 * ApiClient.Builder separates the construction of a ApiClient from its representation
	 */
	public static class Builder {

		/**
		 * The builder's name
		 */
		private String name;
				
		/**
		 * The builder's version
		 */
		private String version;
		
		/**
		 * Sets the builder's name
		 * @param name The name to be set
		 * @return Reference to the current object
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}
				
		/**
		 * Sets the builder's version
		 * @param version The version to be set
		 * @return Reference to the current object
		 */
		public Builder version(final String version) {
			this.version = version;
			return this;
		}
		
		/**
		 * @return A new object constructed from this builder
		 */
		public ApiClient build() {
			return new ApiClient(this);
		}
	}
}
