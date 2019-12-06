package org.apache.ofbiz.service.version;

import java.util.Map;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class ServiceVersion {

	private static final String module = ServiceVersion.class.getName();

	/**
	 * Read version.txt from classpath.
	 * 
	 * /framework/service/config/version.properties
	 * 
	 * Jenkins store there build version.
	 * 
	 * @param dctx
	 * @param context
	 * @return Map<String, Object> (result.version)
	 */
	public static Map<String, Object> getVersion(DispatchContext dctx, Map<String, ? extends Object> context) {
		Map<String, Object> result = null;

		String version = "0";
		Debug.logVerbose("=============================Debug===== ServiceVersion", module);
		try {
			version = UtilProperties.getPropertyValue("version", "version");
		} catch (Exception e) {
			Debug.logError(e, module);
		}
		result = ServiceUtil.returnSuccess("ServiceVersion execute: " + version);
		result.put("version", version);
		Debug.logVerbose("=============================Debug===== Version: " + version, module);

		return result;
	}

	/**
	 * Read version value from component (edi.version.properties, timekeeping.version.properties).
	 * 
	 * /specialpurpose/component/config/component.version.properties$version
	 * 
	 * @param dctx
	 * @param context
	 * @return Map<String, Object> (result.version)
	 */
	public static Map<String, Object> getVersionComponent(DispatchContext dctx, Map<String, ? extends Object> context) {
		Map<String, Object> result = null;

		String version = "0";
		String component = (String) context.get("component");
		Debug.logVerbose("=============================Debug===== getVersionComponent: " + component, module);
		try {
			version = UtilProperties.getPropertyValue(component+".version", "version");
		} catch (Exception e) {
			Debug.logError(e, module);
		}
		result = ServiceUtil.returnSuccess("ServiceVersionComponent execute: " + version);
		result.put("version", version);
		Debug.logVerbose("=============================Debug===== Version: " + version, module);

		return result;
	}

}
