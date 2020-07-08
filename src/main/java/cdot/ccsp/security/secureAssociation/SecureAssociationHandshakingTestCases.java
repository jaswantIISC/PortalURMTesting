package cdot.ccsp.security.secureAssociation;

import java.util.UUID;

import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;

public class SecureAssociationHandshakingTestCases {
	
	/*
	 * Main class: For testing individual test cases
	 */
	public static void main(String[] args){
		
		SecureAssociationHandshakingTestCases associationHandshakingTestCases = new SecureAssociationHandshakingTestCases();
		OneM2MResponse oneM2MResponse = associationHandshakingTestCases.SuccessTest();
		
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
	public OneM2MResponse SuccessTest(){
		
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
	 * test the request with the pskId set incorrectly. 
	 * Since, oneM2M communication is not started before establishing Secure Association, so an error in this case shall return the oneM2MResponse as null
	 * 
	 * Expected oneM2M RSC : NUll Pointer Exception (As Expected oneM2MResponse is null)  
	 */
	public OneM2MResponse IncorrectPskIdTest(){
		
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testAdminAeRetrieve();					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.INCORRECT_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;		
	}
	
	/*
	 * Test Case Description:
	 * test the request with the psk set incorrectly. 
	 * Since, oneM2M communication is not started before establishing Secure Association, so an error in this case shall return the oneM2MResponse as null
	 * 
	 * Expected oneM2M RSC : NUll Pointer Exception (As Expected oneM2MResponse is null)  
	 */
	public OneM2MResponse IncorrectPskTest(){
		
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						
			oneM2MRequest = testAdminAeRetrieve();					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.INCORRECT_PSK);
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
