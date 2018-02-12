package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DeviceInfo;
import com.threezebra.domain.OtherDocPrivilege;
import com.threezebra.repository.DeviceInfoRepository;
@Service
public class DeviceInfoService {
	
	@Autowired
	DeviceInfoRepository deviceInfoRepository;
	
	public DeviceInfo save(String deviceIssuedarr) {
		DeviceInfo deviceInfo=new DeviceInfo();
	     deviceInfo.setId(System.nanoTime());
	     String devNo="DEV"+deviceInfo.getId();
	     deviceInfo.setDeviceno(devNo);
	     deviceInfo.setName(deviceIssuedarr);
		return deviceInfoRepository.save(deviceInfo);
	}
	
	public DeviceInfo findByName(long id) {
		return deviceInfoRepository.findById(id);
	}

	public List<DeviceInfo> findAll() {
		// TODO Auto-generated method stub
		return deviceInfoRepository.findAll();
	}

	public void save(OtherDocPrivilege otherDocPrivilege) {
		// TODO Auto-generated method stub
		
	}

}
