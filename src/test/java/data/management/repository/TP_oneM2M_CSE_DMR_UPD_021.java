package data.management.repository;

import static org.junit.Assert.assertEquals;
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
import cdot.ccsp.dmr.ContainerUpdateTestCases;
import cdot.ccsp.dmr.ContentInstanceCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesInstanceCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesUpdate;
import cdot.ccsp.groupMgmt.GroupCreate;
import cdot.ccsp.groupMgmt.GroupUpdate;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.pollingchannel.PollingChannelCreateTestCases;
import cdot.ccsp.pollingchannel.PollingChannelUpdate;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
import cdot.ccsp.security.acp.AccessControlPolicyUpdateTestCases;
import cdot.ccsp.subscription.SubscriptionCreate;
import cdot.ccsp.subscription.SubscriptionUpdate;
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
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.ContentInstance;
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.PollingChannel;
import cdot.onem2m.resource.xsd.ResourceWrapper;
import cdot.onem2m.resource.xsd.Subscription;
import cdot.onem2m.resource.xsd.TimeSeries;
import cdot.onem2m.resource.xsd.TimeSeriesInstance;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_UPD_021")
public class TP_oneM2M_CSE_DMR_UPD_021 {

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



	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_021_CNT")
	void TP_oneM2M_CSE_DMR_UPD_021_CNT() {
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
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();

		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		OneM2MResponse ciResponse = ciCreate.createWithAnyAttribute(container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		String ciResourceId = ci.getResourceID();
		String ciStructuredResourceId = container1StructuredResourceId + "/" + ci.getResourceName();
		// INITIAL STATE ENDS


		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateLabelWithRCNValue(BigInteger.valueOf(1), Arrays.asList("NewLabel"), container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());

		if (!(containerUpdateResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper)) {
			cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
			assertEquals(Arrays.asList("NewLabel"), cont.getLabels());
			assertEquals(null, cont.getChildResource());
			assertTrue(!(containerUpdateResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper));
		}

		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ciResourceId);
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + ciStructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_021_GRP")
	void TP_oneM2M_CSE_DMR_UPD_021_GRP() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		GroupCreate grpCreate = new GroupCreate();
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
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();

		OneM2MResponse groupCreateResponse = grpCreate.createGroupWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), Arrays.asList(container1StructuredResourceId));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		String groupResourceId = group.getResourceID();
		String groupStructuredResourceId = aeStructuredResourceID + "/" + group.getResourceName();

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), groupStructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String subResourceId = sub.getResourceID();
		String subStructuredResourceId = groupStructuredResourceId + "/" + sub.getResourceName();

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		GroupUpdate grpUpdate = new GroupUpdate();
		OneM2MResponse groupRetrieveResponse = grpUpdate.updateLabelsWithRCN(BigInteger.valueOf(1), Arrays.asList("NewLabel"), groupStructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), groupRetrieveResponse.getResponseStatusCode());
		if(!(groupRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper)) {
			group = (Group) groupRetrieveResponse.getPrimitiveContent().getAny();
			assertEquals(Arrays.asList("NewLabel"), group.getLabels());
			assertEquals(null, group.getChildResource());
			assertTrue(!(groupRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper));
		}
	

		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" GROUP  RESOURCE ID : " + groupResourceId);
		System.out.println(" GROUP  STRUCTURED RESOURCE ID : " + groupStructuredResourceId);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + subResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + subStructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_021_ACP")
	void TP_oneM2M_CSE_DMR_UPD_021_ACP() {

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_ID + "/" + TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilegeAndLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		container1ResourceId = acp.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + acp.getResourceName();

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String subResourceId = sub.getResourceID();
		String subStructuredResourceId = container1StructuredResourceId + "/" + sub.getResourceName();

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = acpUpdate.updateLabelsWithRCN(BigInteger.valueOf(1), Arrays.asList("NewLabel"), container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		if (!(acpRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper)) {
			acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
			assertEquals(Arrays.asList("NewLabel"), acp.getLabels());
			assertEquals(null, acp.getChildResource());
			assertTrue(!(acpRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper));
		}
		// CONTAINER UPDATE ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + subResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + subStructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_021_PCH")
	void TP_oneM2M_CSE_DMR_UPD_021_PCH() {
		PollingChannelCreateTestCases pcCreate = new PollingChannelCreateTestCases();
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
		OneM2MResponse pollingChannelResponse = pcCreate.createWithLabel(Arrays.asList("UNUpdated_Value"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		PollingChannel pc = (PollingChannel) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = pc.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + pc.getResourceName();

//		SubscriptionCreate subCreate = new SubscriptionCreate();
//		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
//		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
//		// CONTAINER CREATE 1 ENDS
//		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
//		String subResourceId = sub.getResourceID();
//		String subStructuredResourceId = container1StructuredResourceId + "/" + sub.getResourceName();

		// INITIAL STATE ENDS
		// TEST EVENT STARTS
		// CONT RETRIEVE
		PollingChannelUpdate pcUpdate = new PollingChannelUpdate();
		OneM2MResponse pcRetrieveResponse = pcUpdate.updateLabelsWithRCN(BigInteger.valueOf(1), Arrays.asList("NewLabel"), container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), pcRetrieveResponse.getResponseStatusCode());
		if (!(pcRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper)) {
			pc = (PollingChannel) pcRetrieveResponse.getPrimitiveContent().getAny();
			assertEquals(Arrays.asList("NewLabel"), pc.getLabels());
			assertEquals(null, pc.getChildResource());
			assertTrue(!(pcRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper));
		}

		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" POLLINGCHANNEL  RESOURCE ID : " + container1ResourceId);
		System.out.println(" POLLINGCHANNEL  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
//		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + subResourceId);
//		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + subStructuredResourceId);
	}



	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_021_SUB")
	void TP_oneM2M_CSE_DMR_UPD_021_SUB() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_ID + "/" + TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription acp = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = acp.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + acp.getResourceName();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse acpRetrieveResponse = subUpdate.updateLabelWithRCN(BigInteger.valueOf(1), Arrays.asList("NewLabel"), container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		if (!(acpRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper)) {
			acp = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
			assertEquals(Arrays.asList("NewLabel"), acp.getLabels());
			assertEquals(null, acp.getChildResource());
			assertTrue(!(acpRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper));
		}

		// CONTAINER UPDATE ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}



	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_021_TS")
	void TP_oneM2M_CSE_DMR_UPD_021_TS() {
		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
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
		OneM2MResponse tsCreateResponse = tsCreate.createWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsCreateResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + ts.getResourceName();


		TimeSeriesInstanceCreateTestCases tsiCreate = new TimeSeriesInstanceCreateTestCases();
		OneM2MResponse tsiResponse = tsiCreate.createWithAnyAttribute(container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsiResponse.getResponseStatusCode());
		TimeSeriesInstance tsi = (TimeSeriesInstance) tsiResponse.getPrimitiveContent().getAny();
		String tsiResourceId = tsi.getResourceID();
		String tsiStructuredResourceId = container1StructuredResourceId + "/" + tsi.getResourceName();

		// INITIAL STATE ENDS
		// TEST EVENT STARTS
		// CONT RETRIEVE
		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse pcRetrieveResponse = tsUpdate.updateLabelWithRCN(BigInteger.valueOf(1), Arrays.asList("NewLabel"), container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), pcRetrieveResponse.getResponseStatusCode());
		if (!(pcRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper)) {
			ts = (TimeSeries) pcRetrieveResponse.getPrimitiveContent().getAny();
			assertEquals(Arrays.asList("NewLabel"), ts.getLabels());
			assertEquals(null, ts.getChildResource());
			assertTrue(!(pcRetrieveResponse.getPrimitiveContent().getAny() instanceof ResourceWrapper));
		}



		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" TIME-SERIES-INSTANCE  RESOURCE ID : " + tsiResourceId);
		System.out.println(" TIME-SERIES-INSTANCE  STRUCTURED RESOURCE ID : " + tsiStructuredResourceId);
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
