package data.management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cdot.ccsp.dmr.ContainerCreateTestCases;
import cdot.ccsp.dmr.ContentInstanceCreateTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.M2MStatus;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.ContentInstance;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_RET_010")
public class TP_oneM2M_CSE_DMR_RET_010 {

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

	@Nested
	public class NestedTestCases {
		@Test
		@Tag("TP_oneM2M_CSE_DMR_RET_010")
		void TP_oneM2M_CSE_DMR_RET_010() {
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
			OneM2MResponse container1Response = contCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
			// CONTAINER CREATE 1 ENDS
			Container cont = (Container) container1Response.getPrimitiveContent().getAny();
			container1ResourceId = cont.getResourceID();
			container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();

			OneM2MResponse ciCreateResponse = ciCreate.createWithAnyAttribute(container1StructuredResourceId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciCreateResponse.getResponseStatusCode());
			// CONTAINER CREATE 1 ENDS
			ContentInstance ci = (ContentInstance) ciCreateResponse.getPrimitiveContent().getAny();
			String contentInstanceResourceId1 = ci.getResourceID();
			String contentInstanceStructuredResourceId1 = container1StructuredResourceId + "/" + ci.getResourceName();
			String resourceName = ci.getResourceName();

			ciCreateResponse = ciCreate.createWithAnyAttribute(container1StructuredResourceId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciCreateResponse.getResponseStatusCode());
			// CONTAINER CREATE 1 ENDS
			ci = (ContentInstance) ciCreateResponse.getPrimitiveContent().getAny();
			String contentInstanceResourceId2 = ci.getResourceID();
			String contentInstanceStructuredResourceId2 = container1StructuredResourceId + "/" + ci.getResourceName();

			// INITIAL STATE ENDS

			// TEST EVENT STARTS
			// CONT RETRIEVE
			OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1StructuredResourceId + "/ol", aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
			ci = (ContentInstance) containerUpdateResponse.getPrimitiveContent().getAny();
			assertEquals(contentInstanceResourceId1, ci.getResourceID());
			assertEquals(resourceName, ci.getResourceName());
			assertEquals(M2MSimpleResources.CONTENTINSTANCE.getM2MResourceTypes().intValue(), ci.getResourceType().intValue());

			// CONTAINER UPDATE ENDS

			// TEST EVENT ENDS

			System.out.println(" AE-ID : " + aeId);
			System.out.println(" AE RESOURCE ID : " + aeResourceID);
			System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
			System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
			System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

			System.out.println(" CONTENTINSTANCE 1  RESOURCE ID : " + contentInstanceResourceId1);
			System.out.println(" CONTENTINSTANCE 1  STRUCTURED RESOURCE ID : " + contentInstanceStructuredResourceId1);
			System.out.println(" CONTENTINSTANCE 2  RESOURCE ID : " + contentInstanceResourceId2);
			System.out.println(" CONTENTINSTANCE 2  STRUCTURED RESOURCE ID : " + contentInstanceStructuredResourceId2);

		}

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
