package ${configYAML.apiPackagePath}.internal.resource.${versionDirName};

<#compress>
	<#list openAPIYAML.components.schemas?keys as schemaName>
		import ${configYAML.apiPackagePath}.dto.${versionDirName}.${schemaName};
		import ${configYAML.apiPackagePath}.internal.dto.${versionDirName}.${schemaName}Impl;
	</#list>
</#compress>

import ${configYAML.apiPackagePath}.resource.${versionDirName}.${schemaName}Resource;

import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.multipart.MultipartBody;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.LocalDateTimeUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * @author ${configYAML.author}
 * @generated
 */
@Generated("")
@Path("/${openAPIYAML.info.version}")
public abstract class Base${schemaName}ResourceImpl implements ${schemaName}Resource {

	<#list javaTool.getJavaSignatures(openAPIYAML, schemaName) as javaSignature>
		<#compress>
			<#list javaTool.getMethodAnnotations(javaSignature) as methodAnnotation>
				${methodAnnotation}
			</#list>

			@Override
			<@compress single_line=true>
				public ${javaSignature.returnType} ${javaSignature.methodName}(
					<#list javaSignature.javaParameters as javaParameter>
						${javaTool.getParameterAnnotation(javaParameter)} ${javaParameter.parameterType} ${javaParameter.parameterName}

						<#if javaParameter_has_next>
							,
						</#if>
					</#list>
				) throws Exception {
			</@compress>
		</#compress>

		<#if stringUtil.equals(javaSignature.returnType, "boolean")>
			return false;
		<#elseif javaSignature.returnType?contains("Page<")>
			return Page.of(Collections.emptyList());
		<#else>
			return new ${javaSignature.returnType}Impl();
		</#if>

		}
	</#list>

	protected <T, R> List<R> transform(List<T> list, UnsafeFunction<T, R, Throwable> unsafeFunction) {
		return TransformUtil.transform(list, unsafeFunction);
	}

	@Context
	protected AcceptLanguage acceptLanguage;

	@Context
	protected Company company;

}