package com.threezebra.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.Application;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.Department;
import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.PermissionGroup;
import com.threezebra.domain.SharedDocPrivilege;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.PermissionGroupRepository;

@Service("permissionGroupService")
public class PermissionGroupService {
	Logger log = LoggerFactory.getLogger(PermissionGroupService.class);
	@Autowired
	private ApplicationConfigurationProperties configurationProperties;
	@Autowired
	private PermissionGroupRepository permissionGroupRepository;

	@Autowired
	private DeptDocPrivilegeService deptDocPrivilegeService;
	@Autowired
	private SharedDocPrivilegeService sharedDocPrivilegeService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private JobRoleService jobRoleService;

	@Autowired
	private BaseLocationService baseLocationService;
	@Value("${prefix.distroname}")
	private String distroname;
	@Value("${prefix.dailydistroname}")
	private String dailydistroname;
	@Value("${prefix.deptflag}")
	private String deptflag;
	@Value("${prefix.unitFlag}")
	private String unitflag;
	@Value("${prefix.blocationFlag}")
	private String blocationFlag;
	@Value("${prefix.jobRoleFlag}")
	private String jobRoleFlag;
	@Value("${mapping.message}")
	private String mappingmessage;
	@Value("${prefix.userType}")
	private String userTypeflag;

	public PermissionGroup save(PermissionGroup permissionGroup) {

		return permissionGroupRepository.save(permissionGroup);
	}

	public void deleteAll() {
		permissionGroupRepository.deleteAll();

	}

	public List<PermissionGroup> findAll() {
		return permissionGroupRepository.findAll();
	}

	public PermissionGroup findByName(String permissiongroupname) {
		return permissionGroupRepository.findByName(permissiongroupname);
	}

	public PermissionGroup findById(long permissionlong) {
		return permissionGroupRepository.findById(permissionlong);
	}

	public PermissionGroup crateADGroup(PermissionGroup permissionGroup, String accessToken) {

		HttpClient httpclient = HttpClients.createDefault();
		StringEntity entity = null;
		HttpResponse response = null;
		String responseString = null;
		int statusCode = 0;
		try {
			JSONObject rootJsonObject = new JSONObject();
			rootJsonObject.put("displayName", permissionGroup.getName());
			rootJsonObject.put("mailNickname", permissionGroup.getName().replaceAll("\\s+", ""));
			rootJsonObject.put("mailEnabled", false);
			rootJsonObject.put("securityEnabled", true);
			entity = new StringEntity(rootJsonObject.toString());
			URIBuilder builder = new URIBuilder("https://graph.windows.net/myorganization/groups");
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Authorization", "Bearer " + accessToken);
			request.setEntity(entity);
			response = httpclient.execute(request);
			if (response != null) {
				statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 0 && statusCode == 201) {
					responseString = EntityUtils.toString(response.getEntity());
					JSONObject responseObject = new JSONObject(responseString);
					String objectID = (String) responseObject.get("objectId");
					if (objectID != null) {
						permissionGroup.setAadObjectId(objectID);
						permissionGroupRepository.save(permissionGroup);
					}
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissionGroup;
	}

	public List<PermissionGroup> getADGroups(String accessToken, String groupName) {
		HttpClient httpclient = HttpClients.createDefault();
		String groupInfo = null;
		List<PermissionGroup> groupObjectList = new ArrayList<>();
		try {
			URIBuilder builder = new URIBuilder("https://graph.windows.net/myorganization/groups");
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");
			// Specify values for optional parameters, as needed
			builder.setParameter("$filter", "displayName eq '" + groupName + "')");
			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);
			request.addHeader("Authorization", "Bearer " + accessToken);
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				groupInfo = EntityUtils.toString(entity);
				if (groupInfo != null) {
					JSONObject jsonObject = new JSONObject(groupInfo);
					JSONArray jsonArr = (JSONArray) jsonObject.get("value");
					if (jsonArr != null && jsonArr.length() > 0) {
						for (int i = 0; i < jsonArr.length(); i++) {
							JSONObject userObject = (JSONObject) jsonArr.get(i);
							PermissionGroup permissionGroup = new PermissionGroup();
							permissionGroup.setName(userObject.getString("displayName"));
							permissionGroup.setAadObjectId(userObject.getString("objectId"));
							groupObjectList.add(permissionGroup);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupObjectList;
	}

	public int deleteADGroup(String groupObjectId, String accessToken) {
		HttpClient httpclient = HttpClients.createDefault();
		HttpResponse response = null;
		int statusCode = 0;
		try {
			URIBuilder builder = new URIBuilder("https://graph.windows.net/myorganization/groups/" + groupObjectId);
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");

			URI uri = builder.build();
			HttpDelete request = new HttpDelete(uri);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Authorization", "Bearer " + accessToken);
			request.addHeader("Access-Control-Allow-Origin", "*");
			request.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
			request.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
			response = httpclient.execute(request);
			if (response != null) {
				statusCode = response.getStatusLine().getStatusCode();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusCode;
	}

	public boolean containsName(final List<PermissionGroup> permissionGroupList, final String permissionGroup) {
		return permissionGroupList.stream().filter(o -> o.getName().equals(permissionGroup)).findFirst().isPresent();
	}

	public void updatePermissionGroup(Unit unit, List<Department> departmentlist, List<UserType> usertypeList,
			List<JobRole> jobRoleList) {
		DeptDocPrivilege deptDocPrivilege = deptDocPrivilegeService.findByName("On-Line View, Print, Edit");
		SharedDocPrivilege sharedDocPrivilege = sharedDocPrivilegeService.findSharedDocPrivilege("On-Line View Only");
		List<Application> applications = applicationService.findAll();
		for (Department department : departmentlist) {
			for (UserType userType : usertypeList) {
				jobRoleList = jobRoleService.findByUserType(userType);
				for (JobRole jobRole : jobRoleList) {
					if (!(jobRole.getName().contains("InfoRecipient") || jobRole.getName().contains("Daily"))) {
						List<BaseLocation> baselocationlist = baseLocationService.findAll();
						for (BaseLocation baseLocation : baselocationlist) {
							PermissionGroup permissionGroup = new PermissionGroup();
							StringBuilder permissiongroupname = new StringBuilder();
							permissionGroup.setApplication(applications);
							permissionGroup.setId(System.nanoTime());
							permissionGroup.setUnit(unit.getName());
							permissionGroup.setJobRole(jobRole.getName());
							permissionGroup.setDepartment(department.getName());
							permissionGroup.setDeptDocPrivilege(deptDocPrivilege);
							permissionGroup.setSharedDocPrivilege(sharedDocPrivilege);
							if (blocationFlag.equals("TRUE")) {
								permissiongroupname.append(baseLocation.getName());
							}
							if (unitflag.equals("TRUE")) {
								permissiongroupname.append(".").append(unit.getName());
							}
							if (deptflag.equals("TRUE")) {
								permissiongroupname.append(".").append(department.getName());
							}
							if (jobRoleFlag.equals("TRUE")) {
								permissiongroupname.append(".").append(jobRole.getName());
							}

							log.info("%%%%%%%%%%%%%%" + permissiongroupname, "group name is");
							int count = 0;
							log.info("%%%%%%%%%" + count++, "no of records");
							permissionGroup.setName(permissiongroupname.toString());
							update(permissionGroup);
						}

					}

				}
			}

		}

	}

	public void update(PermissionGroup permissionGroup) {
		List<PermissionGroup> permissionGroupList = permissionGroupRepository.findAll();

		if (containsName(permissionGroupList, permissionGroup.getName()) == false) {
			permissionGroupRepository.save(permissionGroup);
		} else {
			PermissionGroup pergrp = permissionGroupRepository.findByName(permissionGroup.getName());
			permissionGroupRepository.delete(pergrp);
			permissionGroupRepository.save(permissionGroup);
		}

	}

}
