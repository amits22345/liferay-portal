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

package com.liferay.dynamic.data.mapping.form.evaluator.internal.functions;

import com.liferay.dynamic.data.mapping.constants.DDMConstants;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionObserver;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionObserverAware;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionParameterAccessor;
import com.liferay.dynamic.data.mapping.expression.DDMExpressionParameterAccessorAware;
import com.liferay.dynamic.data.mapping.expression.UpdateFieldPropertyRequest;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.KeyValuePair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	factory = DDMConstants.EXPRESSION_FUNCTION_FACTORY_NAME,
	service = {
		DDMExpressionFunction.Function2.class, DDMExpressionObserverAware.class,
		DDMExpressionParameterAccessorAware.class
	}
)
public class SetOptionsFunction
	implements DDMExpressionFunction.Function2<String, String, Boolean>,
			   DDMExpressionObserverAware, DDMExpressionParameterAccessorAware {

	@Override
	public Boolean apply(String field, String json) {
		if ((_ddmExpressionObserver == null) ||
			(_ddmExpressionParameterAccessor == null)) {

			return false;
		}

		UpdateFieldPropertyRequest.Builder builder =
			UpdateFieldPropertyRequest.Builder.newBuilder(
				field, "options",
				createKeyValuePairList(
					json, _ddmExpressionParameterAccessor.getLocale()));

		_ddmExpressionObserver.updateFieldProperty(builder.build());

		return true;
	}

	@Override
	public String getName() {
		return "setOptions";
	}

	@Override
	public void setDDMExpressionObserver(
		DDMExpressionObserver ddmExpressionObserver) {

		_ddmExpressionObserver = ddmExpressionObserver;
	}

	@Override
	public void setDDMExpressionParameterAccessor(
		DDMExpressionParameterAccessor ddmExpressionParameterAccessor) {

		_ddmExpressionParameterAccessor = ddmExpressionParameterAccessor;
	}

	protected List<KeyValuePair> createKeyValuePairList(
		String value, Locale locale) {

		String languageId = LanguageUtil.getLanguageId(locale);

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				String.valueOf(value));

			Iterator<String> keys = jsonObject.keys();

			while (keys.hasNext()) {
				String currentLanguageId = keys.next();

				if (currentLanguageId.equals(languageId)) {
					JSONArray jsonArray = jsonObject.getJSONArray(
						currentLanguageId);

					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject optionJSONObject = jsonArray.getJSONObject(
							i);

						KeyValuePair keyValuePair = new KeyValuePair(
							optionJSONObject.getString("value"),
							optionJSONObject.getString("label"));

						keyValuePairs.add(keyValuePair);
					}

					break;
				}
			}
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		return keyValuePairs;
	}

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		SetOptionsFunction.class);

	private DDMExpressionObserver _ddmExpressionObserver;
	private DDMExpressionParameterAccessor _ddmExpressionParameterAccessor;

}