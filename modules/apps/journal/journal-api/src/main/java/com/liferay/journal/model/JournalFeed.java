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

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JournalFeed service. Represents a row in the &quot;JournalFeed&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JournalFeedModel
 * @generated
 */
@ImplementationClassName("com.liferay.journal.model.impl.JournalFeedImpl")
@ProviderType
public interface JournalFeed extends JournalFeedModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.journal.model.impl.JournalFeedImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JournalFeed, Long> ID_ACCESSOR = new Accessor<JournalFeed, Long>() {
			@Override
			public Long get(JournalFeed journalFeed) {
				return journalFeed.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JournalFeed> getTypeClass() {
				return JournalFeed.class;
			}
		};

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#getDDMRendererTemplateKey()}
	*/
	@Deprecated()
	public String getRendererTemplateId();

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#getDDMStructureKey()}
	*/
	@Deprecated()
	public String getStructureId();

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#getDDMTemplateKey()}
	*/
	@Deprecated()
	public String getTemplateId();

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#setDDMRendererTemplateKey(String)}
	*/
	@Deprecated()
	public void setRendererTemplateId(String rendererTemplateKey);

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#setDDMStructureKey(String)}
	*/
	@Deprecated()
	public void setStructureId(String structureKey);

	/**
	* @deprecated As of Wilberforce (7.0.x), replaced by {@link
	#setDDMTemplateKey(String)}
	*/
	@Deprecated()
	public void setTemplateId(String templateKey);
}