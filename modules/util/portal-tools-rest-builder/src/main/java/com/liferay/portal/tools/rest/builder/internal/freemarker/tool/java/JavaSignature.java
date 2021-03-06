/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.tools.rest.builder.internal.freemarker.tool.java;

import com.liferay.portal.vulcan.yaml.openapi.Operation;
import com.liferay.portal.vulcan.yaml.openapi.PathItem;

import java.util.List;

/**
 * @author Peter Shin
 */
public class JavaSignature {

	public JavaSignature(
		String path, PathItem pathItem, Operation operation,
		List<JavaParameter> javaParameters, String methodName,
		String returnType) {

		_path = path;
		_pathItem = pathItem;
		_operation = operation;
		_javaParameters = javaParameters;
		_methodName = methodName;
		_returnType = returnType;
	}

	public List<JavaParameter> getJavaParameters() {
		return _javaParameters;
	}

	public String getMethodName() {
		return _methodName;
	}

	public Operation getOperation() {
		return _operation;
	}

	public String getPath() {
		return _path;
	}

	public PathItem getPathItem() {
		return _pathItem;
	}

	public String getReturnType() {
		return _returnType;
	}

	private final List<JavaParameter> _javaParameters;
	private final String _methodName;
	private final Operation _operation;
	private final String _path;
	private final PathItem _pathItem;
	private final String _returnType;

}