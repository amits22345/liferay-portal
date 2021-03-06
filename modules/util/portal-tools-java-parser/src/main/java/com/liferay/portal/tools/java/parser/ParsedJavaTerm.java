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

package com.liferay.portal.tools.java.parser;

import antlr.CommonHiddenStreamToken;

/**
 * @author Hugo Huijser
 */
public class ParsedJavaTerm {

	public ParsedJavaTerm(
		String content, Position startPosition, Position endPosition) {

		_content = content;
		_startPosition = startPosition;
		_endPosition = endPosition;
	}

	public boolean containsCommentToken() {
		return _containsCommentToken;
	}

	public String getContent() {
		return _content;
	}

	public Position getEndPosition() {
		return _endPosition;
	}

	public CommonHiddenStreamToken getPrecedingCommentToken() {
		return _precedingCommentToken;
	}

	public Position getStartPosition() {
		return _startPosition;
	}

	public void setContainsCommentToken(boolean containsCommentToken) {
		_containsCommentToken = containsCommentToken;
	}

	public void setPrecedingCommentToken(
		CommonHiddenStreamToken precedingCommentToken) {

		_precedingCommentToken = precedingCommentToken;
	}

	private boolean _containsCommentToken;
	private final String _content;
	private final Position _endPosition;
	private CommonHiddenStreamToken _precedingCommentToken;
	private final Position _startPosition;

}