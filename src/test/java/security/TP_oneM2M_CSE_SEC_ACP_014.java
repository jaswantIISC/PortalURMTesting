package security;

import static org.junit.Assert.assertEquals;

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
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.registration.AEUpdate;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MStatus;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_SEC_ACP_014")
public class TP_oneM2M_CSE_SEC_ACP_014 {

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
	@Tag("TP_oneM2M_CSE_SEC_ACP_014_CRE")
	void TP_oneM2M_CSE_SEC_ACP_014_CRE() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();


		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse contCreateRes = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), contCreateRes.getResponseStatusCode());




		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}


	@Test
	@Tag("TP_oneM2M_CSE_SEC_ACP_014_RET")
	void TP_oneM2M_CSE_SEC_ACP_014_RET() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();



		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		OneM2MResponse ciResp = genRet.retrieveResource(TestingParams.GENERAL_ADN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), ciResp.getResponseStatusCode());



		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_SEC_ACP_014_UPD")
	void TP_oneM2M_CSE_SEC_ACP_014_UPD() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();



		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse aeUpdateRes = aeUpdate.updateLabel(Arrays.asList("UpdatedLabel"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), aeUpdateRes.getResponseStatusCode());


		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}


	@Test
	@Tag("TP_oneM2M_CSE_SEC_ACP_014_DEL")
	void TP_oneM2M_CSE_SEC_ACP_014_DEL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;



		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();


		GeneralDeleteTestCases genDel = new GeneralDeleteTestCases();
		OneM2MResponse ciResp = genDel.deleteResource(TestingParams.GENERAL_ADN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), ciResp.getResponseStatusCode());


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

			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MResponse oneM2MResponse = null;
			oneM2MRequest.setTo(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			try {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ GENERAL ADN DELETE");
				oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
			oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
			oneM2MRequest.setTo(TestingParams.ADMIN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.ADMIN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			originatorActions = new OriginatorActionsBean();
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		}
		aeId = null;
		aeResourceID = null;
		aeStructuredResourceID = null;
		container1ResourceId = null;
		container1StructuredResourceId = null;
	}

}
