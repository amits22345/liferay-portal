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

package com.liferay.headless.web.experience.internal.resource.v1_0;

import com.liferay.headless.web.experience.dto.v1_0.AggregateRating;
import com.liferay.headless.web.experience.internal.dto.v1_0.AggregateRatingImpl;
import com.liferay.headless.web.experience.resource.v1_0.AggregateRatingResource;
import com.liferay.journal.model.JournalArticle;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsStatsLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/aggregate-rating.properties",
	scope = ServiceScope.PROTOTYPE, service = AggregateRatingResource.class
)
public class AggregateRatingResourceImpl
	extends BaseAggregateRatingResourceImpl {

	@Override
	public AggregateRating getAggregateRating(Long aggregateRatingId)
		throws Exception {

		return _toAggregateRating(
			_ratingsStatsLocalService.fetchStats(
				JournalArticle.class.getName(), aggregateRatingId));
	}

	private AggregateRating _toAggregateRating(RatingsStats ratingsStats) {
		return new AggregateRatingImpl() {
			{
				setBestRating(1);
				setId(ratingsStats.getStatsId());
				setRatingCount(ratingsStats.getTotalEntries());
				setRatingValue(ratingsStats.getAverageScore());
				setWorstRating(0);
			}
		};
	}

	@Reference
	private RatingsStatsLocalService _ratingsStatsLocalService;

}