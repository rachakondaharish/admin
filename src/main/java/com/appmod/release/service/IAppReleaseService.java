package com.appmod.release.service;

import java.util.List;

import com.appmod.release.dto.AppReleaseInfoDto;
import com.appmod.release.dto.SearchDto;

public interface IAppReleaseService {
	
List<AppReleaseInfoDto> createAppReleaseDetails(final AppReleaseInfoDto appReleaseInfoDto);
	
	List<AppReleaseInfoDto> retrieveAppReleaseDetails(final SearchDto searchDto);

}
