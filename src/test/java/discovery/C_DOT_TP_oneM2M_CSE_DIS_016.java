package discovery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import cdot.ccsp.dmr.ContainerCreateTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.security.acp.AccessControlOperations;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MStatus;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.AccessControlPolicy;
import cdot.onem2m.resource.xsd.AccessControlRule;
import cdot.onem2m.resource.xsd.Attribute;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.FilterCriteria;
import cdot.onem2m.resource.xsd.SetOfAcrs;

@Tag("C_DOT_TP_oneM2M_CSE_DIS_016")
class C_DOT_TP_oneM2M_CSE_DIS_016 {


	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	String container1ResourceId;
	String container1StructuredResourceId;
	String container2ResourceId;
	String container2StructuredResourceId;
	String container3ResourceId;
	String container3StructuredResourceId;
	String container4ResourceId;
	String container4StructuredResourceId;

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
	
	
	// Test case for level filter criteria
	@Test
	@Tag("C_DOT_TP_oneM2M_CSE_DIS_016_DIS")
	void C_DOT_TP_oneM2M_CSE_DIS_016_DIS() {
		
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

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		SetOfAcrs setOfACRs = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_DI.getValue()));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		
		
		ContainerCreateTestCases conCreate = new ContainerCreateTestCases();
		List<String> lbl = new ArrayList<String>();
		lbl.add("TESTING_LABEL");
		OneM2MResponse conRes = conCreate.createWithFixedLabel(lbl, aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), conRes.getResponseStatusCode());
		Container cont = (Container) conRes.getPrimitiveContent().getAny();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		container1ResourceId = cont.getResourceID();
		
		
		conCreate = new ContainerCreateTestCases();
		conRes = conCreate.createWithCreator(aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), conRes.getResponseStatusCode());
		cont = (Container) conRes.getPrimitiveContent().getAny();
		container2StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		container2ResourceId = cont.getResourceID();
		
		
		
		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		FilterCriteria filCri = new FilterCriteria();
		filCri.setFilterUsage(BigInteger.valueOf(1));
		List<String> dislbl = new ArrayList<String>();
		dislbl.add("TESTING_LABEL");
		filCri.setLabels(dislbl);
		List<Attribute> attrList = new ArrayList<Attribute>();
		Attribute attr = new Attribute();
		attr.setName("creator");
		attr.setValue(TestingParams.GENERAL_ADN_AE_ID);
		attrList.add(attr);
		filCri.setAttribute(attrList);
		
		filCri.setFilterOperation(BigInteger.valueOf(2));
		OneM2MResponse disRes = genRet.retrieveForDiscovery(filCri, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(disRes.getResponseStatusCode(), M2MStatus.OK.getOnem2mStatusCode());
		System.out.println(disRes.getPrimitiveContent().getAny().getClass());

		assertNotNull(disRes.getPrimitiveContent().getAny());
		List<String> listURI = (List<String>) disRes.getPrimitiveContent().getAny();
		assertTrue(listURI.size() == 2);

		assertTrue(listURI.contains(container1StructuredResourceId) && listURI.contains(container2StructuredResourceId));
		
		
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

		}
		aeId = null;
		aeResourceID = null;
		aeStructuredResourceID = null;
		container1ResourceId = null;
		container1StructuredResourceId = null;
		container2ResourceId = null;
		container2StructuredResourceId = null;
		container3ResourceId = null;
		container3StructuredResourceId = null;
		container4ResourceId = null;
		container4StructuredResourceId = null;
	}

}
