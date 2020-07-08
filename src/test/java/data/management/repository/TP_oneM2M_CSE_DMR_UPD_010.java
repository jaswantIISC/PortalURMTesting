package data.management.repository;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_UPD_010")
public class TP_oneM2M_CSE_DMR_UPD_010 {

	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	String container1ResourceId;
	String container1StructuredResourceId;

	AERegistration aeRegister = new AERegistration();

	GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();

	@BeforeAll
	static void beforeAllSetup() {
		System.out.println("##########################################################################################################");
	}

	@BeforeEach
	void beforeEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_UPD_010_CNT")
//	void TP_oneM2M_CSE_DMR_UPD_010_CNT() {
//		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = cont.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
//		BigInteger stateTag = cont.getStateTag();
//		System.out.println("1st State StateTag : " + stateTag);
//
//		SubscriptionCreate subCreate = new SubscriptionCreate();
//		OneM2MResponse subCreateResponse = subCreate.subWithLabel(Arrays.asList("SomeRandomLabel"), container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subCreateResponse.getResponseStatusCode());
//		Subscription subcription = (Subscription) subCreateResponse.getPrimitiveContent().getAny();
//		String subResourceID = subcription.getResourceID();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//
//		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
//		OneM2MResponse subUpdateResponse = subUpdate.updateLabelsSUB(Arrays.asList("SomeRandomLabelUpdateToThis"), subResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
//
//		GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();
//		OneM2MResponse contRetrieveResponse = genRetrieve.retrieveResource(container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), contRetrieveResponse.getResponseStatusCode());
//		cont = (Container) contRetrieveResponse.getPrimitiveContent().getAny();
//		BigInteger stateTag2 = cont.getStateTag();
//		System.out.println("2nd State StateTag : " + stateTag2);
//
//		
//		//assertTrue("Previous (" + stateTag + ") should be smaker than current (" + stateTag2 + ")", stateTag2.intValue() > stateTag.intValue());
//		assertTrue(stateTag2.intValue() > stateTag.intValue());
//		// CONTAINER UPDATE ENDS
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//
//	}

//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_UPD_010_TS")
//	void TP_oneM2M_CSE_DMR_UPD_010_TS() {
//		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
//		;
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = tsCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		TimeSeries cont = (TimeSeries) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = cont.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
//		BigInteger stateTag = cont.getStateTag();
//		System.out.println("1st State StateTag : " + stateTag);
//
//		SubscriptionCreate subCreate = new SubscriptionCreate();
//		OneM2MResponse subCreateResponse = subCreate.subWithLabel(Arrays.asList("SomeRandomLabel"), container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subCreateResponse.getResponseStatusCode());
//		Subscription subcription = (Subscription) subCreateResponse.getPrimitiveContent().getAny();
//		String subResourceID = subcription.getResourceID();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//
//		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
//		OneM2MResponse subUpdateResponse = subUpdate.updateLabelsSUB(Arrays.asList("SomeRandomLabelUpdateToThis"), subResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
//
//		GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();
//		OneM2MResponse contRetrieveResponse = genRetrieve.retrieveResource(container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), contRetrieveResponse.getResponseStatusCode());
//		cont = (TimeSeries) contRetrieveResponse.getPrimitiveContent().getAny();
//		BigInteger stateTag2 = cont.getStateTag();
//		System.out.println("2nd State StateTag : " + stateTag2);
//		assertNotEquals(stateTag2.intValue(), stateTag.intValue());
//		//assertTrue("Previous (" + stateTag + ") should be smaker than current (" + stateTag2 + ")", stateTag2.intValue() > stateTag.intValue());
//		// CONTAINER UPDATE ENDS
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//
//	}

	@AfterAll
	static void afterAllSetup() {
		System.out.println("############################################################################################################");
	}

	@AfterEach
	void afterEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		if (TestingParams.DELETE_ALL_RESOURCES) {
//			AEReRegistration aeDelete = new AEReRegistration();
//			OneM2MResponse aeDeleteResponse = aeDelete.aeDeregister(aeResourceID, aeId, TestingParams.ADN_AE_PSK_ID, TestingParams.ADN_AE_PSK);
//			assertEquals(M2MStatus.DELETED.getOnem2mStatusCode(), aeDeleteResponse.getResponseStatusCode());

			UUID resourceID = UUID.randomUUID();
			Object idObject = resourceID;
			OneM2MRequest oneM2MRequest = new OneM2MRequest();
			oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
			oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
			oneM2MRequest.setTo(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MResponse oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		}
		aeId = null;
		aeResourceID = null;
		aeStructuredResourceID = null;
		container1ResourceId = null;
		container1StructuredResourceId = null;
	}

}
