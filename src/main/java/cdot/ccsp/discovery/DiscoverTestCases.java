package cdot.ccsp.discovery;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.FilterUsage;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.Attribute;
import cdot.onem2m.resource.xsd.FilterCriteria; 

public class DiscoverTestCases {
	
	public static void main(String[] args){
		DiscoverTestCases test = new DiscoverTestCases();		 
//		OneM2MResponse oneM2MResponse = test.ContentUnacceptableTest();
//		OneM2MResponse oneM2MResponse = test.ConflictingDateTest();
//		OneM2MResponse oneM2MResponse = test.SpecialMgmtDefinitionTest();
//		OneM2MResponse oneM2MResponse = test.SpecialResourceTypeFCAttributeTest();
		OneM2MResponse oneM2MResponse = test.SuccessTest();
				
		System.out.println("oneM2MResponse = "+oneM2MResponse);
		System.out.println("Status = "+oneM2MResponse.getResponseStatusCode());
	}
	
	/*
	 * Successful Discovery of AE without any Filter Criteria: Complete Discovery of all the Resources (considering testing is being done its a fresh setup)
	 * 
	 * Expected oneM2M RSC 	:	2000 (OK)
	 * HTTP status code		:	200 (OK)
	 */
	public OneM2MResponse SuccessTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {					
			FilterCriteria fc = new FilterCriteria();
			fc.setFilterUsage(FilterUsage.DISCOVERY.getFilterUsage());
			oneM2MRequest = testDiscover(fc);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;	
	}
	
	/*
	 * Test Case Description:
	 * When the Date Format is not used in the correct format in the filter criteria
	 * 
	 * Expected oneM2M RSC 	:	4000 (BAD_REQUEST)
	 * HTTP status code		:	400 (BAD_REQUEST)
	 */
	public OneM2MResponse ContentUnacceptableTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						 
			FilterCriteria fc = new FilterCriteria();
			fc.setFilterUsage(FilterUsage.DISCOVERY.getFilterUsage());
			 
			Date currentDate = new Date();
			String currentDateStr = TestingParams.INCORRECT_SDF.format(currentDate);
			 	
			fc.setCreatedBefore(currentDateStr);
			fc.setExpireAfter(currentDateStr);
			fc.setUnmodifiedSince(currentDateStr);;
			
			oneM2MRequest = testDiscover(fc);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;	
	}
	
	/*
	 * Test Case Description:
	 * When the Before and After Dates mentioned in the Filter Criteria are not Conflicting
	 * Example: AfterDate is after Before Date i.e. defining range is illegal
	 * 
	 * Expected oneM2M RSC 	:	4102 (CONTENTS_UNACCPETABLE)
	 * HTTP status code		:	400 (BAD_REQUEST)
	 */
	public OneM2MResponse ConflictingDateTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						 
			FilterCriteria fc = new FilterCriteria();
			fc.setFilterUsage(FilterUsage.DISCOVERY.getFilterUsage());
			 
			Date currentDate = new Date();
			Date yesterDayDate = getMeYesterday();
			String currentDateStr = TestingParams.CORRECT_SDF.format(currentDate);
			String yesterDayDateStr = TestingParams.CORRECT_SDF.format(yesterDayDate);
			 	
			fc.setCreatedBefore(yesterDayDateStr); 
			fc.setCreatedAfter(currentDateStr);
			
			oneM2MRequest = testDiscover(fc);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;	
	}
	
	/*
	 * Test Case Description:
	 * Special Handling of MgmtDefinition attribute: using the attribute resource. Only one resourceType entry is allowed when attribute
	 * is used as mgmtDefinition for handling discovery of Specialized Resources
	 * 
	 * Expected oneM2M RSC 	:	4000 (CONTENTS_UNACCPETABLE)
	 * HTTP status code		:	400 (BAD_REQUEST)
	 */
	public OneM2MResponse SpecialMgmtDefinitionTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						 
			FilterCriteria fc = new FilterCriteria();
			fc.setFilterUsage(FilterUsage.DISCOVERY.getFilterUsage());  
			
			List<Attribute> value = new ArrayList<Attribute>();
			Attribute attr = new Attribute();
			attr.setName("mgmtDefinition");
			attr.setValue("1004");
			value.add(attr);
			fc.setAttribute(value);
			
			List<BigInteger> resTypeList = new ArrayList<BigInteger>();
			resTypeList.add(BigInteger.valueOf(1));
			resTypeList.add(BigInteger.valueOf(2));
			resTypeList.add(BigInteger.valueOf(13));
			fc.setResourceType(resTypeList);
			
			oneM2MRequest = testDiscover(fc);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;	
	}
	
	/*
	 * Test Case Description:
	 * When resourceType does not match the Filter Criteria Attributes. Currently, if Filter Criteria includes the FilterCriteria attributes
	 * (contentType, sizeAbove and sizeBelow), then the applicable resourceType is CI and it should be mentioned in the FC resourceType 
	 * 
	 * Expected oneM2M RSC 	:	4000 (CONTENTS_UNACCPETABLE)
	 * HTTP status code		:	400 (BAD_REQUEST)
	 */
	public OneM2MResponse SpecialResourceTypeFCAttributeTest(){
		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();
		
		try {						 
			FilterCriteria fc = new FilterCriteria();
			fc.setFilterUsage(FilterUsage.DISCOVERY.getFilterUsage());  
			fc.setSizeAbove(BigInteger.valueOf(5));
			fc.setSizeBelow(BigInteger.valueOf(100));
			
			List<BigInteger> resTypeList = new ArrayList<BigInteger>();
			resTypeList.add(BigInteger.valueOf(1));
			resTypeList.add(BigInteger.valueOf(2)); 
			fc.setResourceType(resTypeList);
			
			oneM2MRequest = testDiscover(fc);					
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return oneM2MResponse;	
	}
	
	private Date getMeYesterday(){
	     return new Date(System.currentTimeMillis()-24*60*60*1000);
	}
	
	private OneM2MRequest testDiscover(FilterCriteria fc ){

		OneM2MRequest oneM2MRequest = new OneM2MRequest();		
		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;	 
		oneM2MRequest.setTo(TestingParams.CSE_BASE_RESOURCE_ID);
		oneM2MRequest.setFrom(TestingParams.ADMIN_AE_ID);	//"admin AE AE-ID" from Chaitan
		oneM2MRequest.setOperation(M2MOperation.RETRIEVE.getOperationId());
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
		oneM2MRequest.setGroupRequestIdentifier(String.valueOf(idObject));
		oneM2MRequest.setDesiredIdentifierResultType(BigInteger.valueOf(2));		 
					
		oneM2MRequest.setFilterCriteria(fc);
		return oneM2MRequest;		
	}	

}
