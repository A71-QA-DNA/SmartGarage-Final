package com.smartgarage.pages;

import com.testframework.PropertiesManager;
import com.testframework.core.BaseWebPage;

public class BaseSmartGaragePage extends BaseWebPage {
    protected BaseSmartGaragePage(String pageUrl) {
        super(pageUrl);
    }

    @Override
    public String getBasePageUrl() {
        return PropertiesManager.getConfigProperties().getProperty("smartGarageUrl");
    }
}
