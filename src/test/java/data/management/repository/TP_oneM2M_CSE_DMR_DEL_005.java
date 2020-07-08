package data.management.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cdot.ccsp.dmr.ContainerCreateTestCases;
import cdot.ccsp.dmr.ContentInstanceCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesInstanceCreateTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MStatus;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.ContentInstance;
import cdot.onem2m.resource.xsd.TimeSeries;
import cdot.onem2m.resource.xsd.TimeSeriesInstance;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_DEL_005")
public class TP_oneM2M_CSE_DMR_DEL_005 {

	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	String container1ResourceId;
	String container1StructuredResourceId;

	AERegistration aeRegister = new AERegistration();

	GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();
	GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();

	@BeforeAll
	static void beforeAllSetup() {
		System.out.println("##########################################################################################################");
	}

	@BeforeEach
	void beforeEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_DEL_005_CNT")
	void TP_oneM2M_CSE_DMR_DEL_005_CNT() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithResultContent(BigInteger.valueOf(9), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		assertTrue(container1Response.getPrimitiveContent().getAny() instanceof Container);
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		assertNull(cont.getLabels());
		
		OneM2MResponse ciResponse = ciCreate.createWithAnyAttribute(cont.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		
		container1Response = genRetrieve.retrieveResource(cont.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		cont = (Container) container1Response.getPrimitiveContent().getAny();
		BigInteger stateTagOld = cont.getStateTag();

		OneM2MResponse delResponse = genDelete.deleteResource(ci.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.DELETED.getOnem2mStatusCode(), delResponse.getResponseStatusCode());

		container1Response = genRetrieve.retrieveResource(cont.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		cont = (Container) container1Response.getPrimitiveContent().getAny();
		BigInteger stateTagNew = cont.getStateTag();
		
		assertTrue(stateTagNew.intValue() > stateTagOld.intValue());


		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}



	@Test
	@Tag("TP_oneM2M_CSE_DMR_DEL_005_TS")
	void TP_oneM2M_CSE_DMR_DEL_005_TS() {
		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		TimeSeriesInstanceCreateTestCases tsiCreate = new TimeSeriesInstanceCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse tsResponse = tsCreate.createWithResultContentAndLabel(BigInteger.valueOf(9), Arrays.asList("NewLabel"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS

		assertTrue(tsResponse.getPrimitiveContent().getAny() instanceof TimeSeries);
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertNull(ts.getLabels());

		OneM2MResponse tsiResponse = tsiCreate.createWithAnyAttribute(ts.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsiResponse.getResponseStatusCode());
		TimeSeriesInstance tsi = (TimeSeriesInstance) tsiResponse.getPrimitiveContent().getAny();

		tsResponse = genRetrieve.retrieveResource(ts.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		//BigInteger stateTagOld = ts.getStateTag();

		OneM2MResponse delResponse = genDelete.deleteResource(tsi.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.DELETED.getOnem2mStatusCode(), delResponse.getResponseStatusCode());

		tsResponse = genRetrieve.retrieveResource(ts.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		//BigInteger stateTagNew = ts.getStateTag();
		//assertTrue(stateTagNew.intValue() > stateTagOld.intValue());


		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}



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
