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

package com.liferay.portlet.asset.lar;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.lar.BaseStagedModelDataHandlerTestCase;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.test.TransactionalMethodRule;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.util.test.AssetTestUtil;

import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Mate Thurzo
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class AssetVocabularyStagedModelDataHandlerTest
	extends BaseStagedModelDataHandlerTestCase {

	@Rule
	public TransactionalMethodRule transactionalMethodRule =
		new TransactionalMethodRule();

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		return AssetTestUtil.addVocabulary(group.getGroupId());
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return AssetVocabularyLocalServiceUtil.
				getAssetVocabularyByUuidAndGroupId(uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return AssetVocabulary.class;
	}

}