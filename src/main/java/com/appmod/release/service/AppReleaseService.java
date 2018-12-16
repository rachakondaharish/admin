package com.appmod.release.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appmod.release.domain.AppReleaseInfo;
import com.appmod.release.dto.AppReleaseInfoDto;
import com.appmod.release.dto.SearchDto;
import com.appmod.release.repository.AppReleaseInfoRepository;

@Service
public class AppReleaseService implements IAppReleaseService {
	
	@Autowired
	private AppReleaseInfoRepository releaseInfoRepo;
	
	@Autowired
	private Mapper dozerMapper;
	
	@Override
	public List<AppReleaseInfoDto> createAppReleaseDetails(final AppReleaseInfoDto appReleaseInfoDto) {
		final AppReleaseInfo appReleaseInfo = dozerMapper.map(appReleaseInfoDto, AppReleaseInfo.class);
		releaseInfoRepo.save(appReleaseInfo);
		return retrieveAppReleaseDetails(null);
	}

	@Override
	public List<AppReleaseInfoDto> retrieveAppReleaseDetails(final SearchDto searchDto) {
		List<AppReleaseInfo> appReleaseInfos = null;
		if(searchDto == null || searchDto.getReleaseDate() == null || searchDto.getSystemCode() == null) {
			appReleaseInfos = releaseInfoRepo.findAll();
		}
		else if(searchDto.getReleaseDate()!=null){
			appReleaseInfos = releaseInfoRepo.findByReleaseDate(searchDto.getReleaseDate());
		}
		else if(searchDto.getSystemCode()!=null){
			appReleaseInfos = releaseInfoRepo.findBySystemCode(searchDto.getSystemCode());
		}
		final List<AppReleaseInfoDto> releaseDtos = new ArrayList<>();
		for (final AppReleaseInfo appReleaseInfo : appReleaseInfos) {
			releaseDtos.add(dozerMapper.map(appReleaseInfo, AppReleaseInfoDto.class));
		}
		return releaseDtos;
	}

}
