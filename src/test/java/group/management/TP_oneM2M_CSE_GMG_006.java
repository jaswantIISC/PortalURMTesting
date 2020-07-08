package group.management;

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
import org.junit.jupiter.api.extension.ExtendWith;

import cdot.ccsp.dmr.ContainerCreateTestCases;
import cdot.ccsp.groupMgmt.GroupCreate;
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.registration.AEUpdate;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
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
import cdot.onem2m.resource.xsd.AccessControlPolicy;
import cdot.onem2m.resource.xsd.AccessControlRule;
import cdot.onem2m.resource.xsd.AggregatedResponse;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.ResponsePrimitive;
import cdot.onem2m.resource.xsd.SetOfAcrs;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_GMG_006")
public class TP_oneM2M_CSE_GMG_006 {

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
	@Tag("TP_oneM2M_CSE_GMG_006_CRE")
	void TP_oneM2M_CSE_GMG_006_CRE() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
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
		acr.setAccessControlOperations(BigInteger.valueOf(63));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE1_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE1_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId1 = ae.getAEID();
		String ae1ResourceID = ae.getResourceID();
		String ae1StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae1Cont = contCreate.createWithResourceName("MeraNaamJokar", ae1ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae1Cont.getResponseStatusCode());
		assertTrue(ae1Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae1ContRID = (Container) ae1Cont.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE2_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE2_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId2 = ae.getAEID();
		String ae2ResourceID = ae.getResourceID();
		String ae2StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae2Cont = contCreate.createWithResourceName("MeraNaamJokar", ae2ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae2Cont.getResponseStatusCode());
		assertTrue(ae2Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae2ContRID = (Container) ae2Cont.getPrimitiveContent().getAny();

		GroupCreate grpCreate = new GroupCreate();
		Group grp = new Group();
		grp.setMemberIDs(Arrays.asList(ae2ResourceID, ae1ResourceID));
		grp.setMaxNrOfMembers(BigInteger.valueOf(2));
		grp.setMemberType(M2MSimpleResources.AE.getM2MResourceTypes());
		grp.setMembersAccessControlPolicyIDs(Arrays.asList(acp.getResourceID()));
		OneM2MResponse grpRes = grpCreate.createWithGroup(grp, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpRes.getResponseStatusCode());
		assertTrue(grpRes.getPrimitiveContent().getAny() instanceof Group);
		Group group = (Group) grpRes.getPrimitiveContent().getAny();
		assertNotNull(group.getMemberIDs());
		assertTrue(!(group.getMemberIDs().isEmpty()));
		assertTrue(group.getCurrentNrOfMembers().intValue() != 0);

		OneM2MResponse fanRes = contCreate.createWithAnyAttribute(group.getResourceID() + "/fopt/MeraNaamJokar", TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), fanRes.getResponseStatusCode());
		assertTrue(fanRes.getPrimitiveContent().getAny() instanceof AggregatedResponse);
		List<ResponsePrimitive> listRes = ((AggregatedResponse) fanRes.getPrimitiveContent().getAny()).getResponsePrimitive();

		List<String> listCIs = new ArrayList<String>();
		listCIs.add(ae1ContRID.getResourceID());
		listCIs.add(ae2ContRID.getResourceID());
		for (ResponsePrimitive indiRes : listRes) {
			assertTrue(indiRes.getPrimitiveContent().getAny() instanceof Container);
			Container cont = (Container) indiRes.getPrimitiveContent().getAny();
			assertTrue(listCIs.contains(cont.getParentID()));
		}

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}


	@Test
	@Tag("TP_oneM2M_CSE_GMG_006_RET")
	void TP_oneM2M_CSE_GMG_006_RET() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
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
		acr.setAccessControlOperations(BigInteger.valueOf(63));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE1_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE1_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId1 = ae.getAEID();
		String ae1ResourceID = ae.getResourceID();
		String ae1StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae1Cont = contCreate.createWithResourceName("MeraNaamJokar", ae1ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae1Cont.getResponseStatusCode());
		assertTrue(ae1Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae1ContRID = (Container) ae1Cont.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE2_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE2_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId2 = ae.getAEID();
		String ae2ResourceID = ae.getResourceID();
		String ae2StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae2Cont = contCreate.createWithResourceName("MeraNaamJokar", ae2ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae2Cont.getResponseStatusCode());
		assertTrue(ae2Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae2ContRID = (Container) ae2Cont.getPrimitiveContent().getAny();

		GroupCreate grpCreate = new GroupCreate();
		Group grp = new Group();
		grp.setMemberIDs(Arrays.asList(ae2ResourceID, ae1ResourceID));
		grp.setMaxNrOfMembers(BigInteger.valueOf(2));
		grp.setMemberType(M2MSimpleResources.AE.getM2MResourceTypes());
		grp.setMembersAccessControlPolicyIDs(Arrays.asList(acp.getResourceID()));
		OneM2MResponse grpRes = grpCreate.createWithGroup(grp, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpRes.getResponseStatusCode());
		assertTrue(grpRes.getPrimitiveContent().getAny() instanceof Group);
		Group group = (Group) grpRes.getPrimitiveContent().getAny();
		assertNotNull(group.getMemberIDs());
		assertTrue(!(group.getMemberIDs().isEmpty()));
		assertTrue(group.getCurrentNrOfMembers().intValue() != 0);

		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		OneM2MResponse fanRes = genRet.retrieveResource(group.getResourceID() + "/fopt/MeraNaamJokar", TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), fanRes.getResponseStatusCode());
		assertTrue(fanRes.getPrimitiveContent().getAny() instanceof AggregatedResponse);
		List<ResponsePrimitive> listRes = ((AggregatedResponse) fanRes.getPrimitiveContent().getAny()).getResponsePrimitive();

		List<String> listCIs = new ArrayList<String>();
		listCIs.add(ae1ContRID.getResourceID());
		listCIs.add(ae2ContRID.getResourceID());
		for (ResponsePrimitive indiRes : listRes) {
			assertTrue(indiRes.getPrimitiveContent().getAny() instanceof Container);
			Container cont = (Container) indiRes.getPrimitiveContent().getAny();
			assertTrue(listCIs.contains(cont.getResourceID()));
		}

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}


	@Test
	@Tag("TP_oneM2M_CSE_GMG_006_UPD")
	void TP_oneM2M_CSE_GMG_006_UPD() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
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
		acr.setAccessControlOperations(BigInteger.valueOf(63));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE1_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE1_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId1 = ae.getAEID();
		String ae1ResourceID = ae.getResourceID();
		String ae1StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae1Cont = contCreate.createWithResourceName("MeraNaamJokar", ae1ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae1Cont.getResponseStatusCode());
		assertTrue(ae1Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae1ContRID = (Container) ae1Cont.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE2_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE2_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId2 = ae.getAEID();
		String ae2ResourceID = ae.getResourceID();
		String ae2StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae2Cont = contCreate.createWithResourceName("MeraNaamJokar", ae2ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae2Cont.getResponseStatusCode());
		assertTrue(ae2Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae2ContRID = (Container) ae2Cont.getPrimitiveContent().getAny();

		GroupCreate grpCreate = new GroupCreate();
		Group grp = new Group();
		grp.setMemberIDs(Arrays.asList(ae2ResourceID, ae1ResourceID));
		grp.setMaxNrOfMembers(BigInteger.valueOf(2));
		grp.setMemberType(M2MSimpleResources.AE.getM2MResourceTypes());
		grp.setMembersAccessControlPolicyIDs(Arrays.asList(acp.getResourceID()));
		OneM2MResponse grpRes = grpCreate.createWithGroup(grp, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpRes.getResponseStatusCode());
		assertTrue(grpRes.getPrimitiveContent().getAny() instanceof Group);
		Group group = (Group) grpRes.getPrimitiveContent().getAny();
		assertNotNull(group.getMemberIDs());
		assertTrue(!(group.getMemberIDs().isEmpty()));
		assertTrue(group.getCurrentNrOfMembers().intValue() != 0);

		AEUpdate aeUpd = new AEUpdate();
		List<String> listString = new ArrayList<String>();
		listString.add("UpdatedLabel");
		OneM2MResponse fanRes = aeUpd.updateLabel(listString, group.getResourceID() + "/fopt/MeraNaamJokar", TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), fanRes.getResponseStatusCode());
		assertTrue(fanRes.getPrimitiveContent().getAny() instanceof AggregatedResponse);
		List<ResponsePrimitive> listRes = ((AggregatedResponse) fanRes.getPrimitiveContent().getAny()).getResponsePrimitive();

		List<String> listCIs = new ArrayList<String>();
		listCIs.add(ae1ContRID.getResourceID());
		listCIs.add(ae2ContRID.getResourceID());
		for (ResponsePrimitive indiRes : listRes) {
			assertTrue(indiRes.getPrimitiveContent().getAny() instanceof Container);
			Container cont = (Container) indiRes.getPrimitiveContent().getAny();
			assertTrue(listCIs.contains(cont.getResourceID()));
			assertEquals(cont.getLabels(), listString);
		}

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}



	@Test
	@Tag("TP_oneM2M_CSE_GMG_006_DEL")
	void TP_oneM2M_CSE_GMG_006_DEL() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
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
		acr.setAccessControlOperations(BigInteger.valueOf(63));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE1_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE1_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId1 = ae.getAEID();
		String ae1ResourceID = ae.getResourceID();
		String ae1StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae1Cont = contCreate.createWithResourceName("MeraNaamJokar", ae1ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae1Cont.getResponseStatusCode());
		assertTrue(ae1Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae1ContRID = (Container) ae1Cont.getPrimitiveContent().getAny();

		aeMResponse = aeRegister.AERegisterWithACPIds(Arrays.asList(acp.getResourceID()), TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.SPARE2_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.SPARE2_ADN_AE_ID));
		ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		String aeId2 = ae.getAEID();
		String ae2ResourceID = ae.getResourceID();
		String ae2StructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ae.getResourceName();

		OneM2MResponse ae2Cont = contCreate.createWithResourceName("MeraNaamJokar", ae2ResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ae2Cont.getResponseStatusCode());
		assertTrue(ae2Cont.getPrimitiveContent().getAny() instanceof Container);
		Container ae2ContRID = (Container) ae2Cont.getPrimitiveContent().getAny();

		GroupCreate grpCreate = new GroupCreate();
		Group grp = new Group();
		grp.setMemberIDs(Arrays.asList(ae2ResourceID, ae1ResourceID));
		grp.setMaxNrOfMembers(BigInteger.valueOf(2));
		grp.setMemberType(M2MSimpleResources.AE.getM2MResourceTypes());
		grp.setMembersAccessControlPolicyIDs(Arrays.asList(acp.getResourceID()));
		OneM2MResponse grpRes = grpCreate.createWithGroup(grp, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpRes.getResponseStatusCode());
		assertTrue(grpRes.getPrimitiveContent().getAny() instanceof Group);
		Group group = (Group) grpRes.getPrimitiveContent().getAny();
		assertNotNull(group.getMemberIDs());
		assertTrue(!(group.getMemberIDs().isEmpty()));
		assertTrue(group.getCurrentNrOfMembers().intValue() != 0);

		GeneralDeleteTestCases genDel = new GeneralDeleteTestCases();
		OneM2MResponse fanRes = genDel.deleteResource(group.getResourceID() + "/fopt/MeraNaamJokar", TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), fanRes.getResponseStatusCode());
		assertTrue(fanRes.getPrimitiveContent().getAny() instanceof AggregatedResponse);
		List<ResponsePrimitive> listRes = ((AggregatedResponse) fanRes.getPrimitiveContent().getAny()).getResponsePrimitive();

		List<String> listCIs = new ArrayList<String>();
		listCIs.add(ae1ContRID.getResourceID());
		listCIs.add(ae2ContRID.getResourceID());
		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		for (String indiRes : listCIs) {
			OneM2MResponse retRes = genRet.retrieveResource(indiRes, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			assertEquals(retRes.getResponseStatusCode(), M2MStatus.NOT_FOUND.getOnem2mStatusCode());
		}

		for (ResponsePrimitive indiRes : listRes) {
			assertEquals(indiRes.getResponseStatusCode(), M2MStatus.DELETED.getOnem2mStatusCode());
		}

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_GMG_006_EXP")
//	void TP_oneM2M_CSE_GMG_006_EXP() {
//
//		// INITIAL STATE ENDS
//		// CONTAINER UPDATE ENDS
//		// TEST EVENT ENDS
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" CONTAINER RESOURCE ID : " + container1ResourceId);
//		System.out.println(" CONTAINER STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
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

			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MResponse oneM2MResponse = null;

			oneM2MRequest.setTo(TestingParams.SPARE1_ADN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			try {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ SPARE1 ADN DELETE");
				oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			oneM2MRequest.setTo(TestingParams.SPARE2_ADN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			try {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ SPARE2 ADN DELETE");
				oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

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
	}

}
