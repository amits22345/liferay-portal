package ${packagePath}.model;

<#if entity.hasCompoundPK()>
	import ${packagePath}.service.persistence.${entity.name}PK;
</#if>

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.model.ResourcedModel;
import com.liferay.portal.model.WorkflowedModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the ${entity.name} service. Represents a row in the &quot;${entity.table}&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link ${packagePath}.model.impl.${entity.name}ModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ${packagePath}.model.impl.${entity.name}Impl}.
 * </p>
 *
 * @author ${author}
 * @see ${entity.name}
 * @see ${packagePath}.model.impl.${entity.name}Impl
 * @see ${packagePath}.model.impl.${entity.name}ModelImpl
 * @generated
 */
public interface ${entity.name}Model extends
	<#if entity.isAttachedModel()>
		AttachedModel,
	</#if>

	<#if entity.isAuditedModel() && !entity.isGroupedModel()>
		AuditedModel,
	</#if>

	BaseModel<${entity.name}>

	<#if entity.isGroupedModel()>
		, GroupedModel
	</#if>

	<#if entity.isResourcedModel()>
		, ResourcedModel
	</#if>

	<#if entity.isWorkflowEnabled()>
		, WorkflowedModel
	</#if>

	{

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ${entity.humanName} model instance should use the {@link ${entity.name}} interface instead.
	 */

	/**
	 * Gets the primary key of this ${entity.humanName}.
	 *
	 * @return the primary key of this ${entity.humanName}
	 */
	public ${entity.PKClassName} getPrimaryKey();

	/**
	 * Sets the primary key of this ${entity.humanName}
	 *
	 * @param primaryKey the primary key of this ${entity.humanName}
	 */
	public void setPrimaryKey(${entity.PKClassName} primaryKey);

	<#list entity.regularColList as column>
		<#if column.name == "classNameId">
			/**
			 * Gets the class name of the model instance this ${entity.humanName} is polymorphically associated with.
			 *
			 * @return the class name of the model instance this ${entity.humanName} is polymorphically associated with
			 */
			public String getClassName();
		</#if>

		<#assign autoEscape = true>

		<#assign modelName = packagePath + ".model." + entity.name>

		<#if modelHintsUtil.getHints(modelName, column.name)??>
			<#assign hints = modelHintsUtil.getHints(modelName, column.name)>

			<#if hints["auto-escape"]??>
				<#assign autoEscapeHintValue = hints["auto-escape"]>

				<#if autoEscapeHintValue == "false">
					<#assign autoEscape = false>
				</#if>
			</#if>
		</#if>

		/**
		 * Gets the ${column.humanName} of this ${entity.humanName}.
		 *
		 * @return the ${column.humanName} of this ${entity.humanName}
		 */
		<#if autoEscape && (column.type == "String") && (column.localized == false) >
			@AutoEscape
		</#if>

		public ${column.type} get${column.methodName}();

		<#if column.localized>
			/**
			 * Gets the localized ${column.humanName} of this ${entity.humanName}. Uses the default language if no localization exists for the requested language.
			 *
			 * @param locale the locale to get the localized ${column.humanName} for
			 * @return the localized ${column.humanName} of this ${entity.humanName}
			 */
			public ${column.type} get${column.methodName}(Locale locale);

			/**
			 * Gets the localized ${column.humanName} of this ${entity.humanName}, optionally using the default language if no localization exists for the requested language.
			 *
			 * @param locale the local to get the localized ${column.humanName} for
			 * @param useDefault whether to use the default language if no localization exists for the requested language
			 * @return the localized ${column.humanName} of this ${entity.humanName}. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
			 */
			public ${column.type} get${column.methodName}(Locale locale, boolean useDefault);

			/**
			 * Gets the localized ${column.humanName} of this ${entity.humanName}. Uses the default language if no localization exists for the requested language.
			 *
			 * @param languageId the id of the language to get the localized ${column.humanName} for
			 * @return the localized ${column.humanName} of this ${entity.humanName}
			 */
			public ${column.type} get${column.methodName}(String languageId);

			/**
			 * Gets the localized ${column.humanName} of this ${entity.humanName}, optionally using the default language if no localization exists for the requested language.
			 *
			 * @param languageId the id of the language to get the localized ${column.humanName} for
			 * @param useDefault whether to use the default language if no localization exists for the requested language
			 * @return the localized ${column.humanName} of this ${entity.humanName}
			 */
			public String get${column.methodName}(String languageId, boolean useDefault);

			/**
			 * Gets a map of the locales and localized ${column.humanName} of this ${entity.humanName}.
			 *
			 * @return the locales and localized ${column.humanName}
			 */
			public Map<Locale, String> get${column.methodName}Map();
		</#if>

		<#if column.type == "boolean">
			/**
			 * Determines if this ${entity.humanName} is ${column.humanName}.
			 *
			 * @return <code>true</code> if this ${entity.humanName} is ${column.humanName}; <code>false</code> otherwise
			 */
			public boolean is${column.methodName}();
		</#if>

		/**
		<#if column.type == "boolean">
		 * Sets whether this ${entity.humanName} is ${column.humanName}.
		<#else>
		 * Sets the ${column.humanName} of this ${entity.humanName}.
		</#if>
		 *
		 * @param ${column.name} the ${column.humanName} of this ${entity.humanName}
		 */
		public void set${column.methodName}(${column.type} ${column.name});

		<#if column.localized>
			/**
			 * Sets the localized ${column.humanName} of this ${entity.humanName}.
			 *
			 * @param ${column.name} the localized ${column.humanName} of this ${entity.humanName}
			 * @param locale the locale to set the localized ${column.humanName} for
			 */
			public void set${column.methodName}(String ${column.name}, Locale locale);

			public void set${column.methodName}(String ${column.name}, Locale locale, Locale defaultLocale);

			/**
			 * Sets the localized ${column.humanNames} of this ${entity.humanName} from the map of locales and localized ${column.humanNames}.
			 *
			 * @param ${column.name}Map the locales and localized ${column.humanNames} of this ${entity.humanName}
			 */
			public void set${column.methodName}Map(Map<Locale, String> ${column.name}Map);

			public void set${column.methodName}Map(Map<Locale, String> ${column.name}Map, Locale defaultLocale);
		</#if>

		<#if (column.name == "resourcePrimKey") && entity.isResourcedModel()>
			public boolean isResourceMain();
		</#if>

		<#if column.userUuid>
			/**
			 * Gets the ${column.userUuidHumanName} of this ${entity.humanName}.
			 *
			 * @return the ${column.userUuidHumanName} of this ${entity.humanName}
			 * @throws SystemException if a system exception occurred
			 */
			public String get${column.methodUserUuidName}() throws SystemException;

			/**
			 * Sets the ${column.userUuidHumanName} of this ${entity.humanName}.
			 *
			 * @param ${column.userUuidName} the ${column.userUuidHumanName} of this ${entity.humanName}
			 */
			public void set${column.methodUserUuidName}(String ${column.userUuidName});
		</#if>
	</#list>

	<#if entity.isWorkflowEnabled()>
		/**
		 * @deprecated {@link #isApproved}
		 */
		public boolean getApproved();

		/**
		 * Determines if this ${entity.humanName} is approved.
		 *
		 * @return <code>true</code> if this ${entity.humanName} is approved; <code>false</code> otherwise
		 */
		public boolean isApproved();

		/**
		 * Determines if this ${entity.humanName} is a draft.
		 *
		 * @return <code>true</code> if this ${entity.humanName} is a draft; <code>false</code> otherwise
		 */
		public boolean isDraft();

		/**
		 * Determines if this ${entity.humanName} is expired.
		 *
		 * @return <code>true</code> if this ${entity.humanName} is expired; <code>false</code> otherwise
		 */
		public boolean isExpired();

		/**
		 * Determines if this ${entity.humanName} is pending.
		 *
		 * @return <code>true</code> if this ${entity.humanName} is pending; <code>false</code> otherwise
		 */
		public boolean isPending();
	</#if>

	<#--
	Copy methods from com.liferay.portal.model.BaseModel and java.lang.Object to
	correctly generate wrappers.
	-->

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(${entity.name} ${entity.varName});

	public int hashCode();

	public ${entity.name} toEscapedModel();

	public String toString();

	public String toXmlString();

}