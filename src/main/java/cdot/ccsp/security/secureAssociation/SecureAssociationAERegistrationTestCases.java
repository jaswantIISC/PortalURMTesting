package cdot.ccsp.security.secureAssociation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.PrimitiveContent;

public class SecureAssociationAERegistrationTestCases {
	
	/*
	 * Main class: For testing individual test cases
	 */
	public static void main(String[] args){
		
		SecureAssociationAERegistrationTestCases test = new SecureAssociationAERegistrationTestCases();		
//		OneM2MResponse oneM2MResponse = test.UsingAdminAeCredentialsTest();
//		OneM2MResponse oneM2MResponse = test.ServerPramaterBasedFailTest();
//		OneM2MResponse oneM2MResponse = test.AppRuleFailTest();
		OneM2MResponse oneM2MResponse = test.SuccessTest();
		
		System.out.println("oneM2MResponse = "+oneM2MResponse);
		System.out.println("Status = "+oneM2MResponse.getResponseStatusCode());
	}
	
	
	/*
	 * Successful Registration of AE (When all the setup related test cases as mentioned in the Debug Info document have been configured properly and taken care of)
	 * 
	 * Expected oneM2M RSC 	:	2001 (CREATED)
	 * HTTP status code		:	201 (CREATED)
	 */
	public OneM2MResponse SuccessTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			//test case with Pre-Configured AE-ID. WHen using a non-preConfigured Id, just pass "" (empty String)
			oneM2MRequest = testRegisterAE(TestingParams.GENERAL_ADN_AE_ID, TestingParams.CORRECT_ADN_AE_APP_ID);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.GENERAL_ADN_AE_PSK_ID, TestingParams.GENERAL_ADN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * There are many test cases that are dependent on the settings of the IN and the SSM server at the time of request.
	 * So, the failure response could be differentiated by the Debug Info message.
	 * 
	 * Please refer the below document for all the failure test cases with their respective debug info message:	 * 
	 * Available at link: ftp://192.168.103.18/Process_M2M/CDOT_M2M/CCSP/DOCS/CSF/SECURITY/Debug%20Info%20for%20all%20Error%20Test%20Cases.docx
	 * 
	 * Expected oneM2M RSC 	:	4107 (SECURITY_ASSOCIATION_REQUIRED)
	 * HTTP status code		:	403 (FORBIDDEN)
	 * 
	 */
	
	/*
	 * This test case is applicable for all the failure test cases mentioned in the Debug Info Document	 * 
	 * The last debug info message that shall be displayed is : App Rule Registration check failed.
	 * 
	 */
	public OneM2MResponse ServerPramaterBasedFailTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testRegisterAE(TestingParams.GENERAL_ADN_AE_ID, TestingParams.INCORRECT_ADN_AE_APP_ID);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.GENERAL_ADN_AE_PSK_ID, TestingParams.GENERAL_ADN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	//SOME TEST CASES THAT ARE INPUT PARAMETER DEPENDENT ARE WRITTEN DOWN
	
	/*
	 * Failure Test Case: When trying to register an AE using the security credentials of ADMIN-AE S0
	 * Expected Debug Info Message: Error due to using Security Credentials already registered like ADMIN-AE or Data Inconsistency. No node information found for the passed nodeId = <nodeId>
	 * 
	 * Expected oneM2M RSC 	:	4107 (SECURITY_ASSOCIATION_REQUIRED)
	 * HTTP status code		:	403 (FORBIDDEN)
	 */
	public OneM2MResponse UsingAdminAeCredentialsTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testRegisterAE(TestingParams.GENERAL_ADN_AE_ID, TestingParams.CORRECT_ADN_AE_APP_ID);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * Error Test Case: When the Incorrect APP ID is used in the AE Registration Request and thus the app rule verification fails
	 * 
	 * Expected oneM2M RSC 	:	4107 (SECURITY_ASSOCIATION_REQUIRED)
	 * HTTP status code		:	403 (FORBIDDEN)
	 */
	public OneM2MResponse AppRuleFailTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testRegisterAE(TestingParams.GENERAL_ADN_AE_ID, TestingParams.INCORRECT_ADN_AE_APP_ID);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.GENERAL_ADN_AE_PSK_ID, TestingParams.GENERAL_ADN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * private methods 
	 */
	private OneM2MRequest testRegisterAE(String fromParameter, String appId){
		
		OneM2MRequest oneM2MRequest = new OneM2MRequest();
		UUID resourceID2 = UUID.randomUUID();
		Object idObject2 = resourceID2;
		oneM2MRequest.setTo(TestingParams.CSE_BASE_RESOURCE_ID);
		oneM2MRequest.setFrom( fromParameter );
		oneM2MRequest.setOperation(M2MOperation.CREATE.getOperationId());
		
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject2));
		
		oneM2MRequest.setResourceType( M2MSimpleResources.AE.getM2MResourceTypes() );
		 		
		PrimitiveContent primContent = new PrimitiveContent();
		
		AE ae = new AE();		 
		ae.setAppID(appId);
		ae.setAppName("appNameTest");
		List<String> labelList = new ArrayList<>();
		labelList.add("testApp");
		ae.setLabels(labelList);		
		
		ae.setRequestReachability(true);
		List<String> SRV = new ArrayList<String>();
		SRV.add(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		ae.setSupportedReleaseVersions(SRV);
		
		primContent.setAny(ae); 
		oneM2MRequest.setPrimitiveContent(primContent);
		return oneM2MRequest;
	}	

	

}
