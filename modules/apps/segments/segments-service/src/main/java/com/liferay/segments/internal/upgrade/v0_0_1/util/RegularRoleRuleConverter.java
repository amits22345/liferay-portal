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

package com.liferay.segments.internal.upgrade.v0_0_1.util;

import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.internal.criteria.contributor.UserSegmentsCriteriaContributor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true, property = "rule.converter.key=RegularRoleRule",
	service = RuleConverter.class
)
public class RegularRoleRuleConverter implements RuleConverter {

	@Override
	public void convert(
		long companyId, Criteria criteria, String typeSettings) {

		_userSegmentsCriteriaContributor.contribute(
			criteria, "(roleIds eq '" + typeSettings + "')",
			Criteria.Conjunction.AND);
	}

	@Reference(
		target = "(segments.criteria.contributor.key=" + UserSegmentsCriteriaContributor.KEY + ")"
	)
	private SegmentsCriteriaContributor _userSegmentsCriteriaContributor;

}