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
import cdot.ccsp.groupMgmt.GroupCreate;
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
import cdot.onem2m.common.enums.M2MSimpleResources;
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
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.SetOfAcrs;

//Test case for attribute filter criteria
@Tag("C_DOT_TP_oneM2M_CSE_DIS_015")
class C_DOT_TP_oneM2M_CSE_DIS_015 {


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
	
	
	
	@Test
	@Tag("C_DOT_TP_oneM2M_CSE_DIS_015_creator")
	void C_DOT_TP_oneM2M_CSE_DIS_015_creator() {
		
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
		OneM2MResponse conRes = conCreate.createWithCreator(aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), conRes.getResponseStatusCode());
		Container cont = (Container) conRes.getPrimitiveContent().getAny();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		container1ResourceId = cont.getResourceID();
		
		
		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		FilterCriteria filCri = new FilterCriteria();
		filCri.setFilterUsage(BigInteger.valueOf(1));
		List<Attribute> attrList = new ArrayList<Attribute>();
		Attribute attr = new Attribute();
		attr.setName("creator");
		attr.setValue(TestingParams.GENERAL_ADN_AE_ID);
		attrList.add(attr);
		filCri.setAttribute(attrList);
		OneM2MResponse disRes = genRet.retrieveForDiscovery(filCri, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(disRes.getResponseStatusCode(), M2MStatus.OK.getOnem2mStatusCode());
		System.out.println(disRes.getPrimitiveContent().getAny().getClass());

		assertNotNull(disRes.getPrimitiveContent().getAny());
		List<String> listURI = (List<String>) disRes.getPrimitiveContent().getAny();
		assertTrue(listURI.contains(container1ResourceId));
		
		
	}
	
	@Test
	@Tag("C_DOT_TP_oneM2M_CSE_DIS_015_maxNrOfMembers")
	void C_DOT_TP_oneM2M_CSE_DIS_015_maxNrOfMembers() {
		
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
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		assertTrue(container1Response.getPrimitiveContent().getAny() instanceof Container);
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();

		container1Response = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		assertTrue(container1Response.getPrimitiveContent().getAny() instanceof Container);
		Container cont2 = (Container) container1Response.getPrimitiveContent().getAny();
		String cont2RID = cont2.getResourceID();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		SetOfAcrs setOfACRs = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR.getValue()));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		GroupCreate grpCreate = new GroupCreate();
		Group grp = new Group();
		grp.setMemberIDs(Arrays.asList(container1ResourceId, cont2RID));
		grp.setMaxNrOfMembers(BigInteger.valueOf(20));
		grp.setMemberType(M2MSimpleResources.CONTAINER.getM2MResourceTypes());
		grp.setMembersAccessControlPolicyIDs(Arrays.asList(acp.getResourceID()));
		OneM2MResponse grpRes = grpCreate.createWithGroup(grp, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpRes.getResponseStatusCode());
		assertTrue(grpRes.getPrimitiveContent().getAny() instanceof Group);
		Group group = (Group) grpRes.getPrimitiveContent().getAny();
		
		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		FilterCriteria filCri = new FilterCriteria();
		filCri.setFilterUsage(BigInteger.valueOf(1));
		List<Attribute> attrList = new ArrayList<Attribute>();
		Attribute attr = new Attribute();
		attr.setName("maxNrOfMembers");
		attr.setValue(20);
		attrList.add(attr);
		filCri.setAttribute(attrList);
		OneM2MResponse disRes = genRet.retrieveForDiscovery(filCri, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(disRes.getResponseStatusCode(), M2MStatus.OK.getOnem2mStatusCode());
		System.out.println(disRes.getPrimitiveContent().getAny().getClass());

		assertNotNull(disRes.getPrimitiveContent().getAny());
		List<String> listURI = (List<String>) disRes.getPrimitiveContent().getAny();
		assertTrue(listURI.contains(aeStructuredResourceID+"/"+group.getResourceName()));				
		
	}
	
	
	@Test
	@Tag("C_DOT_TP_oneM2M_CSE_DIS_015_memberIDs")
	void C_DOT_TP_oneM2M_CSE_DIS_015_memberIDs() {
		
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
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		assertTrue(container1Response.getPrimitiveContent().getAny() instanceof Container);
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();

		container1Response = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		assertTrue(container1Response.getPrimitiveContent().getAny() instanceof Container);
		Container cont2 = (Container) container1Response.getPrimitiveContent().getAny();
		String cont2RID = cont2.getResourceID();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		SetOfAcrs setOfACRs = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR.getValue()));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRs, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		GroupCreate grpCreate = new GroupCreate();
		Group grp = new Group();
		grp.setMemberIDs(Arrays.asList(container1ResourceId, cont2RID));
		grp.setMaxNrOfMembers(BigInteger.valueOf(2));
		grp.setMemberType(M2MSimpleResources.CONTAINER.getM2MResourceTypes());
		grp.setMembersAccessControlPolicyIDs(Arrays.asList(acp.getResourceID()));
		OneM2MResponse grpRes = grpCreate.createWithGroup(grp, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpRes.getResponseStatusCode());
		assertTrue(grpRes.getPrimitiveContent().getAny() instanceof Group);
		Group group = (Group) grpRes.getPrimitiveContent().getAny();
		
		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		FilterCriteria filCri = new FilterCriteria();
		filCri.setFilterUsage(BigInteger.valueOf(1));
		List<Attribute> attrList = new ArrayList<Attribute>();
		Attribute attr = new Attribute();
		attr.setName("memberIDs");
		attr.setValue(container1ResourceId);
		attrList.add(attr);
		filCri.setAttribute(attrList);
		OneM2MResponse disRes = genRet.retrieveForDiscovery(filCri, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(disRes.getResponseStatusCode(), M2MStatus.OK.getOnem2mStatusCode());
		System.out.println(disRes.getPrimitiveContent().getAny().getClass());

		assertNotNull(disRes.getPrimitiveContent().getAny());
		List<String> listURI = (List<String>) disRes.getPrimitiveContent().getAny();
		assertTrue(listURI.contains(aeStructuredResourceID+"/"+group.getResourceName()));				
		
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
