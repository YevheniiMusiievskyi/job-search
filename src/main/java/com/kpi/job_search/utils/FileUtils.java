package com.kpi.job_search.utils;

import com.sun.istack.Nullable;
import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	public static String getExtension(@Nullable String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return "";
		}

		return fileName.substring(StringUtils.lastIndexOf(fileName, "."));
	}
}
