package registration;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.registration.RemoteCSERegistration;
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
@Tag("TP_oneM2M_CSE_REG_CRE_005")
public class TP_oneM2M_CSE_REG_CRE_005 {

	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	AERegistration aeRegister = new AERegistration();
	RemoteCSERegistration remoteCSERemote = new RemoteCSERegistration();
	GeneralRetrieveTestCases retResource = new GeneralRetrieveTestCases();

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
		@Tag("TP_oneM2M_CSE_REG_CRE_005")
		void TP_oneM2M_CSE_REG_CRE_005() {

			aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
			// NOTE-----------------THIS TEST CASE SHOULD CHECK THE CSE LOGS TO CONFIRM THE
			// TEST CASE EXECUTION. /////

			
			// INITIAL STATE ENDS

			// TEST EVENT STARTS

			// INITIAL STATE STARTS
			// AE-REGISTRATION
			OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED, TestingParams.getSecurityParams(TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED));
			assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
			System.out.println("########################### RESPONSE STATUS CODE: " + aeMResponse.getResponseStatusCode());
			// AE-REGISTRATION DONE
			// TEST EVENT ENDS

			if (aeMResponse.getPrimitiveContent() != null && aeMResponse.getPrimitiveContent().getAny() != null) {
				AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
				aeId = ae.getAEID();
				aeResourceID = ae.getResourceID();
				aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();

				System.out.println(" AE-ID : " + aeId);
				System.out.println(" AE RESOURCE ID : " + aeResourceID);
				System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
			}

		}
	}

	@AfterAll
	static void afterAllSetup() {
		System.out.println("############################################################################################################");
	}

	@AfterEach
	void afterEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		if (TestingParams.DELETE_ALL_RESOURCES && aeId != null) {

			UUID resourceID = UUID.randomUUID();
			Object idObject = resourceID;
			OneM2MRequest oneM2MRequest = new OneM2MRequest();
			oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
			oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
			oneM2MRequest.setTo(aeResourceID);
			oneM2MRequest.setFrom(aeId);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MResponse oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ID_ALLOWED, TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ALLOWED);
		}

		aeId = null;
		aeResourceID = null;
		aeStructuredResourceID = null;
	}

}
