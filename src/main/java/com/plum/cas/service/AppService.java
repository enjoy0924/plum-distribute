package com.plum.cas.service;

import com.plum.cas.dto.App;
import com.plum.core.service.BaseService;

public interface AppService extends BaseService<App, Long> {

    Long findAppIdByAppKey(String appKey);
}
