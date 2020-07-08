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
import cdot.ccsp.registration.RemoteCSERegistration;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MStatus;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.RemoteCSE;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_REG_RET_006")
public class TP_oneM2M_CSE_REG_RET_006 {

	String remoteCSEID;
	String remoteCSEResourceID;
	String remoteCSEStructuredResourceID;

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
		@Tag("TP_oneM2M_CSE_REG_RET_006")
		void TP_oneM2M_CSE_REG_RET_006() {

			remoteCSEStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

			// INITIAL STATE STARTS
			// REMOTE-CSE-REGISTRATION
			OneM2MResponse aeMResponse = remoteCSERemote.setRemoteCse(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.REMOTE_CSE_ID, TestingParams.getSecurityParams(TestingParams.REMOTE_CSE_ID));
			RemoteCSE remoteCSE = (RemoteCSE) aeMResponse.getPrimitiveContent().getAny();
			assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
			//  REMOTE-CSE-REGISTRATION DONE
			remoteCSEID = remoteCSE.getCSEID();
			remoteCSEResourceID = remoteCSE.getResourceID();
			remoteCSEStructuredResourceID = remoteCSEStructuredResourceID + "/" + remoteCSE.getResourceName();
			// INITIAL STATE ENDS

			// TEST EVENT STARTS
			// REMOTE CSE RETRIEVE
			OneM2MResponse cseRetrieveResponse = retResource.retrieveResource(remoteCSEResourceID, TestingParams.REMOTE_CSE_ID, TestingParams.getSecurityParams(TestingParams.REMOTE_CSE_ID));
			assertEquals(M2MStatus.OK.getOnem2mStatusCode(), cseRetrieveResponse.getResponseStatusCode());
			// REMOTE CSE RETRIEVE ENDS

			// TEST EVENT ENDS

			System.out.println(" REMOTE-CSE-ID : " + remoteCSEID);
			System.out.println(" REMOTE-CSE RESOURCE ID : " + remoteCSEResourceID);
			System.out.println(" REMOTE-CSE STRUCTURED RESOURCE ID : " + remoteCSEStructuredResourceID);

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

			UUID resourceID = UUID.randomUUID();
			Object idObject = resourceID;
			OneM2MRequest oneM2MRequest = new OneM2MRequest();
			oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
			oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
			oneM2MRequest.setTo(remoteCSEResourceID);
//			oneM2MRequest.setTo("R1162");
//			oneM2MRequest.setFrom("/MNCSE1");
			oneM2MRequest.setFrom(TestingParams.REMOTE_CSE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MResponse oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.REMOTE_CSE_KPSA_ID, TestingParams.REMOTE_CSE_KPSA);
		}
		remoteCSEID = null;
		remoteCSEResourceID = null;
		remoteCSEStructuredResourceID = null;
	}

}
