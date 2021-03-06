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

package com.liferay.frontend.image.editor.integration.document.library.internal.display.context;

import com.liferay.document.library.display.context.BaseDLViewFileVersionDisplayContext;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.frontend.image.editor.integration.document.library.internal.display.context.logic.ImageEditorDLDisplayContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ambrín Chaudhary
 */
public class ImageEditorDLViewFileVersionDisplayContext
	extends BaseDLViewFileVersionDisplayContext {

	public ImageEditorDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext parentDLDisplayContext,
		HttpServletRequest request, HttpServletResponse response,
		FileVersion fileVersion, ResourceBundle resourceBundle,
		DLURLHelper dlurlHelper) {

		super(_UUID, parentDLDisplayContext, request, response, fileVersion);

		_resourceBundle = resourceBundle;
		_dlurlHelper = dlurlHelper;

		try {
			FileEntry fileEntry = null;

			if (fileVersion != null) {
				fileEntry = fileVersion.getFileEntry();
			}

			_fileEntry = fileEntry;

			_imageEditorDLDisplayContextHelper =
				new ImageEditorDLDisplayContextHelper(
					fileVersion, request, dlurlHelper);
		}
		catch (PortalException pe) {
			throw new SystemException(
				"Unable to create image editor document library view file " +
					"version display context for file version " + fileVersion,
				pe);
		}
	}

	@Override
	public Menu getMenu() throws PortalException {
		Menu menu = super.getMenu();

		List<MenuItem> menuItems = menu.getMenuItems();

		if (!_imageEditorDLDisplayContextHelper.isShowImageEditorAction()) {
			return menu;
		}

		ImageEditorDLDisplayContextHelper imageEditorDLDisplayContextHelper =
			new ImageEditorDLDisplayContextHelper(
				fileVersion, request, _dlurlHelper);

		menuItems.add(
			imageEditorDLDisplayContextHelper.
				getJavacriptEditWithImageEditorMenuItem(_resourceBundle));

		return menu;
	}

	@Override
	public List<ToolbarItem> getToolbarItems() throws PortalException {
		List<ToolbarItem> toolbarItems = super.getToolbarItems();

		if (!_imageEditorDLDisplayContextHelper.isShowImageEditorAction()) {
			return toolbarItems;
		}

		ImageEditorDLDisplayContextHelper imageEditorDLDisplayContextHelper =
			new ImageEditorDLDisplayContextHelper(
				fileVersion, request, _dlurlHelper);

		toolbarItems.add(
			imageEditorDLDisplayContextHelper.
				getJavacriptEditWithImageEditorToolbarItem(_resourceBundle));

		return toolbarItems;
	}

	private static final UUID _UUID = UUID.fromString(
		"ec0c6ec4-8671-4c9e-94a3-8c6bcca0437c");

	private final DLURLHelper _dlurlHelper;
	private final FileEntry _fileEntry;
	private final ImageEditorDLDisplayContextHelper
		_imageEditorDLDisplayContextHelper;
	private final ResourceBundle _resourceBundle;

}