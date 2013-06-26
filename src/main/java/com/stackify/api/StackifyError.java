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

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Encapsulates all details about an error that will be sent to Stackify
 * 
 * <p>
 * Example:
 * <pre>
 * {@code
 * StackifyError.Builder builder = StackifyError.newBuilder();
 * builder.apiClient(apiClient);
 * builder.environmentDetail(environment);
 * ...
 * StackifyError stackifyError = builder.build();
 * }
 * </pre>
 *
 * @author Eric Martin
 */
public class StackifyError {

	/**
	 * API Client
	 */
	private final ApiClient apiClient;
	
	/**
	 * Environment
	 */
	private final EnvironmentDetail environmentDetail;

	/**
	 * Date/time of the error
	 */
	private final Date occurredEpochMillis;
	
	/**
	 * Error details
	 */
	private final ErrorItem error;

	/**
	 * Details of the web request
	 */
	private final WebRequestDetail webRequestDetail;
	
	/**
	 * Tags
	 */
	private final List<String> tags;
	
	/**
	 * Server variables
	 */
	private final Map<String, String> serverVariables;
	
	/**
	 * Custom properties
	 */
	private final Map<String, String> customProperties;
	
	/**
	 * Customer name
	 */
	private final String customerName;
	
	/**
	 * User name
	 */
	private final String userName;

	/**
	 * @return the apiClient
	 */
	public ApiClient getApiClient() {
		return apiClient;
	}

	/**
	 * @return the environmentDetail
	 */
	public EnvironmentDetail getEnvironmentDetail() {
		return environmentDetail;
	}

	/**
	 * @return the occurredEpochMillis
	 */
	public Date getOccurredEpochMillis() {
		return occurredEpochMillis;
	}

	/**
	 * @return the error
	 */
	public ErrorItem getError() {
		return error;
	}

	/**
	 * @return the webRequestDetail
	 */
	public WebRequestDetail getWebRequestDetail() {
		return webRequestDetail;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @return the serverVariables
	 */
	public Map<String, String> getServerVariables() {
		return serverVariables;
	}

	/**
	 * @return the customProperties
	 */
	public Map<String, String> getCustomProperties() {
		return customProperties;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param builder The Builder object that contains all of the values for initialization
	 */
	private StackifyError(final Builder builder) {
		this.apiClient = builder.apiClient;
		this.environmentDetail = builder.environmentDetail;
		this.occurredEpochMillis = builder.occurredEpochMillis;
		this.error = builder.error;
		this.webRequestDetail = builder.webRequestDetail;
		this.tags = builder.tags;
		this.serverVariables = builder.serverVariables;
		this.customProperties = builder.customProperties;
		this.customerName = builder.customerName;
		this.userName = builder.userName;
	}

	/**
	 * @return A new instance of the Builder
	 */
	public static Builder newBuilder() {
		return new Builder();
	}

	/**
	 * StackifyError.Builder separates the construction of a StackifyError from its representation
	 */
	public static class Builder {

		/**
		 * The builder's apiClient
		 */
		private ApiClient apiClient;
		
		/**
		 * The builder's environmentDetail
		 */
		private EnvironmentDetail environmentDetail;
		
		/**
		 * The builder's occurredEpochMillis
		 */
		private Date occurredEpochMillis;
		
		/**
		 * The builder's error
		 */
		private ErrorItem error;
		
		/**
		 * The builder's webRequestDetail
		 */
		private WebRequestDetail webRequestDetail;
		
		/**
		 * The builder's tags
		 */
		private List<String> tags;
		
		/**
		 * The builder's serverVariables
		 */
		private Map<String,String> serverVariables;
		
		/**
		 * The builder's customProperties
		 */
		private Map<String,String> customProperties;
		
		/**
		 * The builder's customerName
		 */
		private String customerName;
		
		/**
		 * The builder's userName
		 */
		private String userName;
		
		/**
		 * Sets the builder's apiClient
		 * @param apiClient The apiClient to be set
		 * @return Reference to the current object
		 */
		public Builder apiClient(final ApiClient apiClient) {
			this.apiClient = apiClient;
			return this;
		}
		
		/**
		 * Sets the builder's environmentDetail
		 * @param environmentDetail The environmentDetail to be set
		 * @return Reference to the current object
		 */
		public Builder environmentDetail(final EnvironmentDetail environmentDetail) {
			this.environmentDetail = environmentDetail;
			return this;
		}
		
		/**
		 * Sets the builder's occurredEpochMillis
		 * @param occurredEpochMillis The occurredEpochMillis to be set
		 * @return Reference to the current object
		 */
		public Builder occurredEpochMillis(final Date occurredEpochMillis) {
			this.occurredEpochMillis = occurredEpochMillis;
			return this;
		}
		
		/**
		 * Sets the builder's error
		 * @param error The error to be set
		 * @return Reference to the current object
		 */
		public Builder error(final ErrorItem error) {
			this.error = error;
			return this;
		}
		
		/**
		 * Sets the builder's webRequestDetail
		 * @param webRequestDetail The webRequestDetail to be set
		 * @return Reference to the current object
		 */
		public Builder webRequestDetail(final WebRequestDetail webRequestDetail) {
			this.webRequestDetail = webRequestDetail;
			return this;
		}
		
		/**
		 * Sets the builder's tags
		 * @param tags The tags to be set
		 * @return Reference to the current object
		 */
		public Builder tags(final List<String> tags) {
			this.tags = tags;
			return this;
		}
		
		/**
		 * Sets the builder's serverVariables
		 * @param serverVariables The serverVariables to be set
		 * @return Reference to the current object
		 */
		public Builder serverVariables(final Map<String,String> serverVariables) {
			this.serverVariables = serverVariables;
			return this;
		}
		
		/**
		 * Sets the builder's customProperties
		 * @param customProperties The customProperties to be set
		 * @return Reference to the current object
		 */
		public Builder customProperties(final Map<String,String> customProperties) {
			this.customProperties = customProperties;
			return this;
		}
		
		/**
		 * Sets the builder's customerName
		 * @param customerName The customerName to be set
		 * @return Reference to the current object
		 */
		public Builder customerName(final String customerName) {
			this.customerName = customerName;
			return this;
		}
		
		/**
		 * Sets the builder's userName
		 * @param userName The userName to be set
		 * @return Reference to the current object
		 */
		public Builder userName(final String userName) {
			this.userName = userName;
			return this;
		}
		
		/**
		 * @return A new object constructed from this builder
		 */
		public StackifyError build() {
			return new StackifyError(this);
		}
	}
}
