package data.management.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@Tag("TP_oneM2M_CSE_DMR_RET_015")
public class TP_oneM2M_CSE_DMR_RET_015 {

	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	String container1ResourceId;
	String container1StructuredResourceId;
	String creationTimeOfResourceShortName = "#ct";

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
//	@Tag("TP_oneM2M_CSE_GEN_UPD_015_ACP")
//	void TP_oneM2M_CSE_DMR_RET_015_ACP() {
//
//		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_ID + "/" + TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.CSE_ID + "/" + aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
//		container1ResourceId = acp.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + acp.getResourceName();
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONTAINER UPDATE
//		OneM2MResponse acpRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
//		acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes().intValue(), acp.getResourceType().intValue());
//		assertNotNull(acp.getCreationTime());
//		List<Field> fields = getAllFields(AccessControlPolicy.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(acp));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//		// CONTAINER UPDATE ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_AE")
//	void TP_oneM2M_CSE_GEN_CRE_015_AE() {
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
//
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(aeStructuredResourceID + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.AE.getM2MResourceTypes().intValue(), ae.getResourceType().intValue());
//		assertNotNull(ae.getCreationTime());
//		List<Field> fields = getAllFields(AE.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(ae));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_CNT")
//	void TP_oneM2M_CSE_GEN_CRE_015_CNT() {
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
//		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = cont.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1ResourceId + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.CONTAINER.getM2MResourceTypes().intValue(), cont.getResourceType().intValue());
//		assertNotNull(cont.getCreationTime());
//		List<Field> fields = getAllFields(Container.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(cont));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
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


	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_SUB")
//	void TP_oneM2M_CSE_DMR_RET_015_SUB() {
//
//		SubscriptionCreate subCreate = new SubscriptionCreate();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_ID + "/" + TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse subResponse = subCreate.subWithExpirationCounter(TestingParams.CSE_ID + "/" + aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		Subscription acp = (Subscription) subResponse.getPrimitiveContent().getAny();
//		container1ResourceId = acp.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + acp.getResourceName();
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONTAINER UPDATE
//		OneM2MResponse acpRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
//		Subscription sub = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes().intValue(), sub.getResourceType().intValue());
//		assertNotNull(sub.getCreationTime());
//		List<Field> fields = getAllFields(Subscription.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(sub));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_GRP")
//	void TP_oneM2M_CSE_DMR_RET_015_GRP() {
//		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
//		GroupCreate grpCreate = new GroupCreate();
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
//		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = cont.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
//
//		OneM2MResponse groupCreateResponse = grpCreate.CE_GMG_00001_00000(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), Arrays.asList(container1StructuredResourceId));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
//		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
//		String groupResourceId = group.getResourceID();
//		String groupStructuredResourceId = aeStructuredResourceID + "/" + group.getResourceName();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//
//		OneM2MResponse groupRetrieveResponse = genRetrieve.retrieveResource(groupStructuredResourceId + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), groupRetrieveResponse.getResponseStatusCode());
//		group = (Group) groupRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.GROUP.getM2MResourceTypes().intValue(), group.getResourceType().intValue());
//		assertNotNull(group.getCreationTime());
//		List<Field> fields = getAllFields(Group.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(group));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//		System.out.println(" GROUP  RESOURCE ID : " + groupResourceId);
//		System.out.println(" GROUP  STRUCTURED RESOURCE ID : " + groupStructuredResourceId);
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_LCP")
//	void TP_oneM2M_CSE_DMR_RET_015_LCP() {
//		LocationPolicyCreateTestCases lcpCreate = new LocationPolicyCreateTestCases();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// ADMIN-AE-REG
//		AERegistration aeReg = new AERegistration();
//		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
//		// ADMIN-AE-REG
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = lcpCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		LocationPolicy lcp = (LocationPolicy) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = lcp.getResourceID();
//		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + lcp.getResourceName();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		lcp = (LocationPolicy) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.LOCATIONPOLICY.getM2MResourceTypes().intValue(), lcp.getResourceType().intValue());
//		assertNotNull(lcp.getCreationTime());
//		List<Field> fields = getAllFields(LocationPolicy.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(lcp));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		// CONTAINER UPDATE ENDS
//
//		GeneralDeleteTestCases deleteResource = new GeneralDeleteTestCases();
//		deleteResource.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" LOCATIONPOLICY  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" LOCATIONPOLICY  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//	}
//	}


	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_NOD")
//	void TP_oneM2M_CSE_DMR_RET_015_NOD() {
//		NodeCreateTestCases nodeCreate = new NodeCreateTestCases();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// ADMIN-AE-REG
//		AERegistration aeReg = new AERegistration();
//		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
//		// ADMIN-AE-REG
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = nodeCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		Node node = (Node) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = node.getResourceID();
//		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + node.getResourceName();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		node = (Node) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.NODE.getM2MResourceTypes().intValue(), node.getResourceType().intValue());
//		assertNotNull(node.getCreationTime());
//		List<Field> fields = getAllFields(Node.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(node));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//		GeneralDeleteTestCases deleteResource = new GeneralDeleteTestCases();
//		deleteResource.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_REQ")
//	void TP_oneM2M_CSE_DMR_RET_015_REQ() {
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
//		OneM2MResponse container1Response = contCreate.createWithAnyAttributeNonBlockingSynch(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.ACCEPTED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
////		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
//		String requestResourceId = (String) container1Response.getPrimitiveContent().getAny();
//		System.out.println(" REQUEST RESOURCE ID : " + requestResourceId);
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(requestResourceId + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		Request request = (Request) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.REQUEST.getM2MResourceTypes().intValue(), request.getResourceType().intValue());
//		assertNotNull(request.getCreationTime());
//		List<Field> fields = getAllFields(Request.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(request));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		GeneralDeleteTestCases deleteResource = new GeneralDeleteTestCases();
//		deleteResource.deleteResource(requestResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" REQUEST  RESOURCE ID : " + container1ResourceId);
//
//	}


	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_STCG")
//	void TP_oneM2M_CSE_DMR_RET_015_STCG() {
//		StatsConfigCreateTestCases statsConfigtCreate = new StatsConfigCreateTestCases();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// ADMIN-AE-REG
//		AERegistration aeReg = new AERegistration();
//		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
//		// ADMIN-AE-REG
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = statsConfigtCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		StatsConfig statConfig = (StatsConfig) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = statConfig.getResourceID();
//		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + statConfig.getResourceName();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		statConfig = (StatsConfig) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.STATSCONFIG.getM2MResourceTypes().intValue(), statConfig.getResourceType().intValue());
//		assertNotNull(statConfig.getCreationTime());
//		List<Field> fields = getAllFields(StatsConfig.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(statConfig));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		GeneralDeleteTestCases deleteResource = new GeneralDeleteTestCases();
//		deleteResource.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" STATCOLLECT  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" STATCOLLECT  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_STCL")
//	void TP_oneM2M_CSE_DMR_RET_015_STCL() {
//		StatsCollectCreateTestCases statsCollectCreate = new StatsCollectCreateTestCases();
//		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
//
//		// INITIAL STATE STARTS
//		// ADMIN-AE-REG
//		AERegistration aeReg = new AERegistration();
//		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
//		// ADMIN-AE-REG
//		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
//		// AE-REGISTRATION DONE
//		aeId = ae.getAEID();
//		aeResourceID = ae.getResourceID();
//		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
//		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = statsCollectCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		StatsCollect statCollect = (StatsCollect) container1Response.getPrimitiveContent().getAny();
//		container1ResourceId = statCollect.getResourceID();
//		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + statCollect.getResourceName();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		statCollect = (StatsCollect) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.STATSCOLLECT.getM2MResourceTypes().intValue(), statCollect.getResourceType().intValue());
//		assertNotNull(statCollect.getCreationTime());
//		List<Field> fields = getAllFields(StatsCollect.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(statCollect));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		GeneralDeleteTestCases deleteResource = new GeneralDeleteTestCases();
//		deleteResource.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//	}

	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_015_TS")
//	void TP_oneM2M_CSE_DMR_RET_015_TS() {
//		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
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
//
//		// CONTAINER CREATE 1
//		OneM2MResponse tsCreateResponse = tsCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsCreateResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		TimeSeries ts = (TimeSeries) tsCreateResponse.getPrimitiveContent().getAny();
//		container1ResourceId = ts.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + ts.getResourceName();
//
//		// INITIAL STATE ENDS
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse pcRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + creationTimeOfResourceShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), pcRetrieveResponse.getResponseStatusCode());
//		ts = (TimeSeries) pcRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.TIMESERIES.getM2MResourceTypes().intValue(), ts.getResourceType().intValue());
//		assertNotNull(ts.getCreationTime());
//		List<Field> fields = getAllFields(TimeSeries.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("creationTime")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(ts));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
//		// TEST EVENT ENDS
//
//		System.out.println(" AE-ID : " + aeId);
//		System.out.println(" AE RESOURCE ID : " + aeResourceID);
//		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
//		System.out.println(" TIMESERIES  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
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

	private List<Field> getAllFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null) {
			fields.addAll(getAllFields(superClazz));
		}

		return fields;
	}

}
