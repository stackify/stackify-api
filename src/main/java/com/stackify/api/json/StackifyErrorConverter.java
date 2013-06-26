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
package com.stackify.api.json;

import java.io.OutputStream;
import java.util.List;

import com.stackify.api.StackifyError;

/**
 * Interface for serializing a list of StackifyError objects and writing the JSON string to an output stream.
 * 
 * @author Eric Martin
 */
public interface StackifyErrorConverter {

	/**
	 * Serializes the object to the stream
	 * @param errors Stackify error objects
	 * @param stream The output stream
	 */
	void writeToStream(final List<StackifyError> errors, final OutputStream stream);
	
}
