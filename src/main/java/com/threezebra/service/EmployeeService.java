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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.threezebra.common.TokenGeneratorUtil;
import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.AdditionalLocation;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.Department;
import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.JobRole;
import com.threezebra.onesimple.dto.EmployeeJson;
import com.threezebra.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeService {
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
		@Autowired
	    private ApplicationConfigurationProperties configurationProperties;
	 	
	    @Autowired 
	    private EmployeeRepository employeeRepository;
	    
	    @Autowired
	    MongoTemplate mongoTemplate;
	    
	    public EmpDetail save(EmpDetail empDetail) {
	    		return employeeRepository.save(empDetail);
	    }

	    public List<EmpDetail> findAll() {
	        return employeeRepository.findAll();
	    }

		public EmpDetail findById(long id) {
			return employeeRepository.findById(id);
		}
		
		public List<EmpDetail> findByBaseLocation(BaseLocation baseLocation) {
			return employeeRepository.findByBaseLocation(baseLocation);
		}
		
		public void remove(List<EmpDetail> empDetails) {
			employeeRepository.delete(empDetails);
		}
		
	public List<EmpDetail> findDuplicateEmployee(String phoneNum, String email) {
		List<EmpDetail> empDetails = employeeRepository.findDuplicateEmployee(phoneNum, email);
		return empDetails;
	}

	public List<EmpDetail> findDupSaveEmp(EmployeeJson employeeJson) {
		Query query = new Query();
		if (employeeJson != null) {
			if (employeeJson.getFirstName() != null) {
				query.addCriteria(Criteria.where("firstName").is(employeeJson.getFirstName()));
			}
			if (employeeJson.getLastName() != null) {
				query.addCriteria(Criteria.where("lastName").is(employeeJson.getLastName()));
			}
			if (employeeJson.getPersonalPhoneNum() != null) {
				query.addCriteria(Criteria.where("personalPhoneNum").is(employeeJson.getPersonalPhoneNum()));
			}
			if (employeeJson.getPersonalEmail() != null) {
				query.addCriteria(Criteria.where("personalEmail").is(employeeJson.getPersonalEmail()));
			}
			if (employeeJson.getBaseLocation() != 0) {
				query.addCriteria(Criteria.where("baseLocation._id").is(employeeJson.getBaseLocation()));
			}
			if (employeeJson.getUnit() != 0) {
				query.addCriteria(Criteria.where("unit._id").is(employeeJson.getUnit()));
			}
			if (employeeJson.getDepartment() != 0) {
				query.addCriteria(Criteria.where("department._id").is(employeeJson.getDepartment()));
			}
			if (employeeJson.getUserType() != 0) {
				query.addCriteria(Criteria.where("userType._id").is(employeeJson.getUserType()));
			}
			if (employeeJson.getJobRole() != 0) {
				query.addCriteria(Criteria.where("jobRole._id").is(employeeJson.getJobRole()));
			}
			if (employeeJson.getJobTitle() != 0) {
				query.addCriteria(Criteria.where("jobTitle._id").is(employeeJson.getJobTitle()));
			}
			if (employeeJson.getAccessStartDate() != null) {
				query.addCriteria(Criteria.where("accessStartDate").is(employeeJson.getAccessStartDate()));
			}
			if (employeeJson.getWorkEmail() != null) {
				query.addCriteria(Criteria.where("workEmail").is(employeeJson.getWorkEmail()));
			}
			if (employeeJson.getPermittedNumDevices() != null) {
				query.addCriteria(Criteria.where("permittedNumDevices").is(employeeJson.getPermittedNumDevices()));
			}
			if (employeeJson.getAccessSusStartDate() != null) {
				query.addCriteria(Criteria.where("accessSusStartDate").is(employeeJson.getAccessSusStartDate()));
			}
			if (employeeJson.getAccessRenwStartDate() != null) {
				query.addCriteria(Criteria.where("accessRenwStartDate").is(employeeJson.getAccessRenwStartDate()));
			}
			if (employeeJson.getAccessEndDate() != null) {
				query.addCriteria(Criteria.where("accessEndDate").is(employeeJson.getAccessEndDate()));
			}
			if (employeeJson.getSpecialRole() != 0) {
				query.addCriteria(Criteria.where("specialRole._id").is(employeeJson.getSpecialRole()));
			}
			if (employeeJson.getAdditionalLocation() != null) {
				query.addCriteria(Criteria.where("additionalLocation").is(employeeJson.getAdditionalLocation()));
			}
			if (employeeJson.getUniqueId() != null) {
				query.addCriteria(Criteria.where("uniqueId").is(employeeJson.getUniqueId()));
			}
			if (employeeJson.getDeviceIssued() != null) {
				query.addCriteria(Criteria.where("deviceIssued").is(employeeJson.getDeviceIssued()));
			}
			
			query.addCriteria(Criteria.where("saveFlag").is("1"));
			
		}
		
		List<EmpDetail> empDetails = mongoTemplate.find(query, EmpDetail.class);
		return empDetails;
	}


	public List<EmpDetail> findByAdditionalLocation(AdditionalLocation additionalLocation) {
		return employeeRepository.findByAdditionalLocation(additionalLocation);
	}

	public List<EmpDetail> findByBaselLocation(BaseLocation baseLocation) {
		 return employeeRepository.findByBaseLocation(baseLocation);
	}
	
	public String createUserInAD(String accessToken, EmpDetail empDetail){
		HttpClient httpclient = HttpClients.createDefault();
		StringEntity entity = null;
		String responseString = null;
		String userObjectID = null;
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accountEnabled", true);
			jsonObject.put("displayName",empDetail.getFirstName()+""+empDetail.getLastName());
			jsonObject.put("mailNickname", empDetail.getFirstName());
			JSONObject passwordProfileObj = new JSONObject();
			passwordProfileObj.put("password", "Welcome@123");
			passwordProfileObj.put("forceChangePasswordNextLogin", false);
			jsonObject.put("passwordProfile",passwordProfileObj);
			jsonObject.put("userPrincipalName", empDetail.getWorkEmail());
			jsonObject.put("usageLocation", "US");
			entity = new StringEntity(jsonObject.toString());
			URIBuilder builder = new URIBuilder("https://graph.windows.net/myorganization/users");
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");
			
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.addHeader("Content-Type","application/json");
			request.addHeader("Authorization", accessToken);
			request.setEntity(entity);
			HttpResponse response = httpclient.execute(request);
			if (response != null) {
				responseString = EntityUtils.toString(response.getEntity());
			}
			System.out.println(responseString);
			JSONObject jsonResObject = new JSONObject(responseString);
			userObjectID = jsonResObject.getString("objectId");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userObjectID;
	}
	
	public List<String> getUsersFromAD(String accessToken){
		HttpClient httpclient = HttpClients.createDefault();
		String userInfo = null;
		List<String> userList = new ArrayList<>();
		try
		{
			URIBuilder builder = new URIBuilder("https://graph.windows.net/myorganization/users");
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");
			// Specify values for optional parameters, as needed
			// builder.setParameter("$filter", "startswith(displayName,'A')");
			
			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);
			request.addHeader("Authorization", accessToken);
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				userInfo = EntityUtils.toString(entity);
			}
			System.out.println(userInfo);
			JSONObject jsonObject = new JSONObject(userInfo);
			JSONArray jsonArr = (JSONArray) jsonObject.get("value");
			if(jsonArr != null && jsonArr.length() > 0) {
				for(int i = 0;i < jsonArr.length();i++) {
					JSONObject userObject = (JSONObject)jsonArr.get(i);
					userList.add((String)userObject.get("userPrincipalName"));
				}
			}
	       
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return userList;
	}
	
	public int assignUserLicense(String userAccount, String application,String accessToken){
		HttpClient httpclient = HttpClients.createDefault();
		StringEntity entity = null;
		int statusCode = 0;
		try {
			JSONObject rootJsonObject = new JSONObject();
			JSONArray assignLicenseArray = new JSONArray();
			JSONArray removeLicenseArray = new JSONArray();
			
			if ("Office365".equalsIgnoreCase(application)) {
				JSONObject assignLicenseJson = new JSONObject();
				assignLicenseJson.put("disabledPlans", new JSONArray());
				assignLicenseJson.put("skuId", "6fd2c87f-b296-42f0-b197-1e91e994b900");
				assignLicenseArray.put(assignLicenseJson);
			}
			
			if ("EMS".equalsIgnoreCase(application)) {
				JSONObject assignLicenseJson = new JSONObject();
				assignLicenseJson.put("disabledPlans", new JSONArray());
				assignLicenseJson.put("skuId", "6fd2c87f-b296-42f0-b197-1e91e994b900");
				assignLicenseArray.put(assignLicenseJson);
			}
			rootJsonObject.put("addLicenses", assignLicenseArray);
			rootJsonObject.put("removeLicenses", removeLicenseArray);
			
			entity = new StringEntity(rootJsonObject.toString());
			URIBuilder builder = new URIBuilder("https://graph.windows.net/myorganization/users/"+userAccount+"/assignLicense");
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");
			
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.addHeader("Content-Type","application/json");
			request.addHeader("Authorization", accessToken);
			request.setEntity(entity);
			HttpResponse response = httpclient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			logger.info("%%%%%%-----"+EntityUtils.toString(response.getEntity()));			
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
		return statusCode;
	}

	

	public List<EmpDetail> findByJobRole(JobRole jobRole) {
		return employeeRepository.findByJobRole(jobRole);
	}
	
	
	public int mapUserAndGroup(String empObjID,String groupObjID,String accessToken) {
		HttpClient httpclient = HttpClients.createDefault();
		StringEntity entity = null;
		int statusCode = 0;
		try {
			JSONObject rootJsonObject = new JSONObject();
			rootJsonObject.put("url", "https://graph.windows.net/DemonstrationOneSimple.onmicrosoft.com/directoryObjects/"+empObjID);
			entity = new StringEntity(rootJsonObject.toString());
			URIBuilder builder = new URIBuilder("https://graph.windows.net/DemonstrationOneSimple.onmicrosoft.com/groups/"+groupObjID+"/$links/members");
			// Specify values for the following required parameters
			builder.setParameter("api-version", "1.6");
			
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.addHeader("Content-Type","application/json");
			request.addHeader("Authorization", accessToken);
			request.setEntity(entity);
			HttpResponse response = httpclient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
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

	public int inviteToManager(String accessToken) {
		HttpClient httpclient = HttpClients.createDefault();
		StringEntity entity = null;
		int statusCode = 0;
		Query query = new Query();
		query.addCriteria(Criteria.where("isInvited").is("0"));
		query.addCriteria(Criteria.where("saveFlag").is("0"));
		List<EmpDetail> empList = mongoTemplate.find(query, EmpDetail.class);
		StringBuilder htmlBuilder = new StringBuilder();
		
		if (empList != null) {
			htmlBuilder.append("<table border='0'><tbody>");
			htmlBuilder.append("<tr><th>Name &amp; Email Address</th><th>Job Title</th><th>Location</th></tr><tr>");
			for (EmpDetail empDetail : empList) {
				htmlBuilder.append("<tr><td>" + empDetail.getFirstName() + " " + empDetail.getLastName() + "("
						+ empDetail.getPersonalEmail() + ")</td><td>" + empDetail.getJobTitle().getName() + "</td><td>"
						+ empDetail.getBaseLocation().getName() + "</td></tr>");
			}
			htmlBuilder.append("</tbody></table>");
		}
		
		try {
			JSONObject rootObj = new JSONObject();
			JSONObject messageObj = new JSONObject();
			JSONObject emailBody = new JSONObject();
			emailBody.put("ContentType","HTML");
			emailBody.put("Content",htmlBuilder.toString());			
			JSONArray ToRecipients = new JSONArray();
			JSONObject toObject = new JSONObject();
			toObject.put("EmailAddress", new JSONObject().put("Address", "pramod.chandra@valuelabs.com"));
			ToRecipients.put(toObject);
			messageObj.put("Subject","Pending users list");
			messageObj.put("Body", emailBody);
			messageObj.put("ToRecipients", ToRecipients);
			rootObj.put("Message", messageObj);
			entity = new StringEntity(rootObj.toString());
			URIBuilder builder = new URIBuilder("https://outlook.office.com/api/v2.0/me/sendmail");
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.addHeader("Content-Type","application/json");
			request.addHeader("Authorization", "Bearer "+accessToken);
			request.setEntity(entity);
			HttpResponse response = httpclient.execute(request);
			statusCode = response.getStatusLine().getStatusCode();
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
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
}
