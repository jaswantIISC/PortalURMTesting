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
@Tag("TP_oneM2M_CSE_DMR_RET_004")
public class TP_oneM2M_CSE_DMR_RET_004 {

	String resourceTypeShortName = "#ty";
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
//	@Tag("TP_oneM2M_CSE_DMR_RET_004_CNT")
//	void TP_oneM2M_CSE_GEN_CRE_004_CNT() {
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
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(container1ResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.CONTAINER.getM2MResourceTypes().intValue(), cont.getResourceType().intValue());
//		List<Field> fields = getAllFields(Container.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
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
//	@Tag("TP_oneM2M_CSE_GEN_UPD_004_ACP")
//	void TP_oneM2M_CSE_DMR_RET_004_ACP() {
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
//		OneM2MResponse acpRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
//		acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes().intValue(), acp.getResourceType().intValue());
//		List<Field> fields = getAllFields(AccessControlPolicy.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(acp));
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		// CONTAINER UPDATE ENDS
//
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
//	@Tag("TP_oneM2M_CSE_DMR_RET_004_SUB")
//	void TP_oneM2M_CSE_DMR_RET_004_SUB() {
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
//		OneM2MResponse acpRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
//		Subscription sub = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes().intValue(), sub.getResourceType().intValue());
//		List<Field> fields = getAllFields(Subscription.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
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
//	@Tag("TP_oneM2M_CSE_DMR_RET_004_CIN")
//	void TP_oneM2M_CSE_DMR_RET_004_CIN() {
//		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
//		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
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
//		OneM2MResponse ciCreateResponse = ciCreate.createWithAnyAttribute(container1StructuredResourceId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciCreateResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		ContentInstance ci = (ContentInstance) ciCreateResponse.getPrimitiveContent().getAny();
//		String contentInstanceResourceId = ci.getResourceID();
//		String contentInstanceStructuredResourceId = container1StructuredResourceId + "/" + ci.getResourceName();
//
//		// INITIAL STATE ENDS
//
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse containerUpdateResponse = genRetrieve.retrieveResource(contentInstanceStructuredResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
//		ci = (ContentInstance) containerUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.CONTENTINSTANCE.getM2MResourceTypes().intValue(), ci.getResourceType().intValue());
//		List<Field> fields = getAllFields(ContentInstance.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(ci));
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
//		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + contentInstanceResourceId);
//		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + contentInstanceStructuredResourceId);
//	}


	// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_004_GRP")
//	void TP_oneM2M_CSE_DMR_RET_004_GRP() {
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
//		OneM2MResponse groupRetrieveResponse = genRetrieve.retrieveResource(groupStructuredResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), groupRetrieveResponse.getResponseStatusCode());
//		group = (Group) groupRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.GROUP.getM2MResourceTypes().intValue(), group.getResourceType().intValue());
//		List<Field> fields = getAllFields(Group.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
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
//	@Tag("TP_oneM2M_CSE_DMR_RET_004_PCH")
//	void TP_oneM2M_CSE_DMR_RET_004_PCH() {
//		PollingChannelCreateTestCases pcCreate = new PollingChannelCreateTestCases();
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
//		OneM2MResponse pollingChannelResponse = pcCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		PollingChannel pc = (PollingChannel) pollingChannelResponse.getPrimitiveContent().getAny();
//		container1ResourceId = pc.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + pc.getResourceName();
//
//
//		// INITIAL STATE ENDS
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse pcRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), pcRetrieveResponse.getResponseStatusCode());
//		pc = (PollingChannel) pcRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.POLLINGCHANNEL.getM2MResourceTypes().intValue(), pc.getResourceType().intValue());
//		List<Field> fields = getAllFields(PollingChannel.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(pc));
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
//		System.out.println(" POLLINGCHANNEL  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" POLLINGCHANNEL  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//	}


// FEATURE TO BE IMPLEMENTED
//	@Disabled
//	@Test
//	@Tag("TP_oneM2M_CSE_DMR_RET_004_TS")
//	void TP_oneM2M_CSE_DMR_RET_004_TS() {
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
//		OneM2MResponse pollingChannelResponse = tsCreate.createWithAnyAttribute(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		TimeSeries pc = (TimeSeries) pollingChannelResponse.getPrimitiveContent().getAny();
//		container1ResourceId = pc.getResourceID();
//		container1StructuredResourceId = aeStructuredResourceID + "/" + pc.getResourceName();
//
//		// INITIAL STATE ENDS
//		// TEST EVENT STARTS
//		// CONT RETRIEVE
//		OneM2MResponse pcRetrieveResponse = genRetrieve.retrieveResource(container1StructuredResourceId + resourceTypeShortName, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.OK.getOnem2mStatusCode(), pcRetrieveResponse.getResponseStatusCode());
//		pc = (TimeSeries) pcRetrieveResponse.getPrimitiveContent().getAny();
//		assertEquals(M2MSimpleResources.TIMESERIES.getM2MResourceTypes().intValue(), pc.getResourceType().intValue());
//		List<Field> fields = getAllFields(TimeSeries.class);
//		for (Field field : fields) {
//
//			if (!field.getName().equals("resourceType")) {
//				try {
//					field.setAccessible(true);
//					assertNull(field.get(pc));
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
//		System.out.println(" POLLINGCHANNEL  RESOURCE ID : " + container1ResourceId);
//		System.out.println(" POLLINGCHANNEL  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
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
