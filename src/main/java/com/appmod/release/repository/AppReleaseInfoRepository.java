package com.appmod.release.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.appmod.release.domain.AppReleaseInfo;
@Repository
public interface AppReleaseInfoRepository extends MongoRepository<AppReleaseInfo, String> {
	
	List<AppReleaseInfo> findBySystemCode(String systemCode);
	List<AppReleaseInfo> findByReleaseDate(String releaseDate);

}
