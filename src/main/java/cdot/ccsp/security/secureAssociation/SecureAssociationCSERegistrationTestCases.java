package cdot.ccsp.security.secureAssociation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.PrimitiveContent;
import cdot.onem2m.resource.xsd.RemoteCSE;

public class SecureAssociationCSERegistrationTestCases {
	
	/*
	 * Main class: For testing individual test cases
	 */
	public static void main(String[] args){
		
		SecureAssociationCSERegistrationTestCases test = new SecureAssociationCSERegistrationTestCases();		 
//		OneM2MResponse oneM2MResponse = test.SuccessTest();
		OneM2MResponse oneM2MResponse = test.FromParameterFailTest();
		
		System.out.println("oneM2MResponse = "+oneM2MResponse);
		System.out.println("Status = "+oneM2MResponse.getResponseStatusCode());
	}
	
	/*
	 * Successful Registration of CSE (When all the setup related test cases as mentioned in the Debug Info document have been configured properly and taken care of)
	 * 
	 * Assumption: The CSE Registration will pass only if the Setup Parameters are correctly configured before the Request 
	 * Please refer the below document for all the failure test cases with their respective debug info message:	 * 
	 * Available at link: ftp://192.168.103.18/Process_M2M/CDOT_M2M/CCSP/DOCS/CSF/SECURITY/Debug%20Info%20for%20all%20Error%20Test%20Cases.docx
	 * 	
	 * Expected oneM2M RSC 	:	2001 (CREATED)
	 * HTTP status code		:	201 (CREATED)
	 */
	public static OneM2MResponse SuccessTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						 
			oneM2MRequest = testRegisterCSE(TestingParams.MN_CSE_ID);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.MN_CSE_PSK_ID, TestingParams.MN_CSE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * Failure Test Case: When trying to register an CSE with correct Security Credentials but the FROM parameter does not contain the 
	 * corresponding Pre-Configured CSE-ID
	 * Expected Debug Info Message: CSE-ID does not match with the assigned CSE-ID by the IN.
	 * 
	 * Expected oneM2M RSC 	:	4107 (SECURITY_ASSOCIATION_REQUIRED)
	 * HTTP status code		:	403 (FORBIDDEN)
	 */
	public static OneM2MResponse FromParameterFailTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testRegisterCSE(TestingParams.INCORRECT_MN_CSE_ID);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.MN_CSE_2_PSK_ID, TestingParams.MN_CSE_2_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	
	/*
	 * private methods 
	 */
	private static OneM2MRequest testRegisterCSE(String fromParameter){
		
		OneM2MRequest oneM2MRequest = new OneM2MRequest();
		
		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;

		
		oneM2MRequest.setTo(TestingParams.CSE_BASE_RESOURCE_ID); 
		oneM2MRequest.setFrom(fromParameter);
		
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.CREATE.getOperationId());
		oneM2MRequest.setResourceType(M2MSimpleResources.REMOTECSE.getM2MResourceTypes());
		
		RemoteCSE remoteCse = new RemoteCSE();
		remoteCse.setCSEID(fromParameter);
		remoteCse.setCSEBase(TestingParams.CSE_BASE_RESOURCE_ID);
		remoteCse.setCseType(BigInteger.valueOf(2));
		remoteCse.setRequestReachability(true);
		List<String> SRV = new ArrayList<String>();
		SRV.add(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		remoteCse.setSupportedReleaseVersions(SRV);
				
		PrimitiveContent primContent = new PrimitiveContent();
		primContent.setAny(remoteCse);	
		oneM2MRequest.setPrimitiveContent(primContent);
		
		return oneM2MRequest;
	}	 

}
