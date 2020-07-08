package cdot.ccsp.security.secureAssociation;

import java.util.UUID;

import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;

public class SecureAssociationNonRegistrationTestCases {
	
	/*
	 * Main class: For testing individual test cases
	 */
	public static void main(String[] args){
		
		SecureAssociationNonRegistrationTestCases test = new SecureAssociationNonRegistrationTestCases();		
		OneM2MResponse oneM2MResponse = test.successTest();
//		OneM2MResponse oneM2MResponse = test.CredentialsNotUsedForRegistration();
//		OneM2MResponse oneM2MResponse = test.FromParameterDoesNotMatch();
		
		System.out.println("oneM2MResponse = "+oneM2MResponse);
		System.out.println("Status = "+oneM2MResponse.getResponseStatusCode());
	}
	
	/*
	 * Test Case Description:
	 * test the request with the correct set of psk and pskId
	 * Since, Secure Association shall be established correctly, so a proper oneM2MResponse is expected.
	 * 
	 * Expected oneM2M RSC : 2000 (as testing the retrieve request)
	 */
	public OneM2MResponse successTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testAdminAeRetrieve();					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * Test Case Description:
	 * When the Secure Credentials exists, But no AE has been registered using the Credentials
	 * 
	 * Expected oneM2M RSC 	:	4106 (ORIGINATOR_HAS_NOT_REGISTERED)
	 * Expected HTTP SC		:	403 (FORBIDDEN) 
	 */
	public OneM2MResponse CredentialsNotUsedForRegistration(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testAdminAeRetrieve();					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.UNUSED_PSK_ID, TestingParams.UNUSED_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * Test Case Description:
	 * When Security Credentials does not match the From Parameter. Using the from parameter of ADMIN_AE and credentials of ADN-AE.
	 * 
	 * Assumption: Both ADMIN_AE and ADN-AE are registered. If ADN-AE is not registered, then the case is same as CredentialsNotUsedForRegistration()
	 * 
	 * Expected oneM2M RSC 	:	4106 (ORIGINATOR_HAS_NOT_REGISTERED)
	 * Expected HTTP SC		:	403 (FORBIDDEN) 
	 */
	public OneM2MResponse FromParameterDoesNotMatch(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testAdminAeRetrieve();					
			//using the from parameter of ADMIN_AE and credentials of ADN_AE
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.GENERAL_ADN_AE_PSK_ID, TestingParams.GENERAL_ADN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * private methods 
	 */
	private OneM2MRequest testAdminAeRetrieve(){
		//	AE RETREIVE
		OneM2MRequest oneM2MRequest = new OneM2MRequest();
		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;
		oneM2MRequest.setFrom(TestingParams.ADMIN_AE_ID);
		oneM2MRequest.setTo(TestingParams.ADMIN_AE_ID);		
		oneM2MRequest.setOperation(M2MOperation.RETRIEVE.getOperationId());
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
		oneM2MRequest.setResourceType( M2MSimpleResources.AE.getM2MResourceTypes() );
		return oneM2MRequest;
	}	

}
