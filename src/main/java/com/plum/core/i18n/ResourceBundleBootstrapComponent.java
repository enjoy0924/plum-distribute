package com.plum.core.i18n;

import java.util.List;

/**
 * Resource bundle bootstrap component.
 * <p>
 * Provides a convenient way to make resource bundles available via Spring config.
 * 
 * @author Roy Wetherall
 */
public class ResourceBundleBootstrapComponent
{
    /**
     * Set the resource bundles to be registered.  This should be a list of resource
     * bundle base names whose content will be made available across the repository.
     * 
     * @param resourceBundles   the resource bundles
     */
    public void setResourceBundles(List<String> resourceBundles)
    {
        for (String resourceBundle : resourceBundles)
        {
            I18NUtil.registerResourceBundle(resourceBundle);
        }
    }
}
