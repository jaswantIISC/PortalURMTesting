package data.management.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import cdot.ccsp.dmr.ContentInstanceCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesInstanceCreateTestCases;
import cdot.ccsp.groupMgmt.GroupCreate;
import cdot.ccsp.locationPolicy.LocationPolicyCreateTestCases;
import cdot.ccsp.node.NodeCreateTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.pollingchannel.PollingChannelCreateTestCases;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.sca.EventConfigCreateTestCases;
import cdot.ccsp.sca.StatsCollectCreateTestCases;
import cdot.ccsp.sca.StatsConfigCreateTestCases;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
import cdot.ccsp.ssm.ServiceSubscribedAppRuleCreateTestCases;
import cdot.ccsp.subscription.SubscriptionCreate;
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
import cdot.onem2m.resource.xsd.EventConfig;
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.LocationPolicy;
import cdot.onem2m.resource.xsd.Node;
import cdot.onem2m.resource.xsd.PollingChannel;
import cdot.onem2m.resource.xsd.ServiceSubscribedAppRule;
import cdot.onem2m.resource.xsd.StatsCollect;
import cdot.onem2m.resource.xsd.StatsConfig;
import cdot.onem2m.resource.xsd.Subscription;
import cdot.onem2m.resource.xsd.TimeSeries;
import cdot.onem2m.resource.xsd.TimeSeriesInstance;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_CRE_001")
public class TP_oneM2M_CSE_DMR_CRE_001 {

	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	String container1ResourceId;
	String container1StructuredResourceId;

	AERegistration aeRegister = new AERegistration();

	GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();
	GeneralDeleteTestCases genDel = new GeneralDeleteTestCases();

	@BeforeAll
	static void beforeAllSetup() {
		System.out.println("##########################################################################################################");
	}

	@BeforeEach
	void beforeEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}



	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_CNT_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_CNT_CB() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// INITIAL STATE STARTS
		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertNotNull(cont.getResourceName());
		// CONTAINER UPDATE ENDS
		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// TEST EVENT ENDS
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_CNT_AE")
	void TP_oneM2M_CSE_DMR_CRE_001_CNT_AE() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
//		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		assertNotNull(cont.getResourceName());
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_CNT_CNT")
	void TP_oneM2M_CSE_DMR_CRE_001_CNT_CNT() {
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
		assertNotNull(cont.getResourceName());
		OneM2MResponse container2Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container2Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont2 = (Container) container2Response.getPrimitiveContent().getAny();
		String container2ResourceId = cont2.getResourceID();
		String container2StructuredResourceId = container1StructuredResourceId + "/" + cont2.getResourceName();
		assertNotNull(cont2.getResourceName());
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTAINER 2  RESOURCE ID : " + container2ResourceId);
		System.out.println(" CONTAINER 2 STRUCTURED RESOURCE ID : " + container2StructuredResourceId);

	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_ACP_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_ACP_CB() {

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// INITIAL STATE STARTS
		// CONTAINER CREATE 1
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilegeAndLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		container1ResourceId = acp.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + acp.getResourceName();
		assertNotNull(acp.getResourceName());

		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_ACP_AE")
	void TP_oneM2M_CSE_DMR_CRE_001_ACP_AE() {

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
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
		assertNotNull(acp.getResourceName());


		// CONTAINER UPDATE ENDS
		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_SUB_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_SUB_CB() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription acp = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = acp.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + acp.getResourceName();
		assertNotNull(acp.getResourceName());
		// INITIAL STATE ENDS

		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_SUB_AE")
	void TP_oneM2M_CSE_DMR_CRE_001_SUB_AE() {

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
		assertNotNull(acp.getResourceName());
		// CONTAINER UPDATE ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_SUB_CNT")
	void TP_oneM2M_CSE_DMR_CRE_001_SUB_CNT() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = TestingParams.ADMIN_AE_STRUCTURED_NAME + "/" + cont.getResourceName();
		assertNotNull(cont.getResourceName());

		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription acp = (Subscription) subResponse.getPrimitiveContent().getAny();
		String subResourceId = acp.getResourceID();
		String subStructuredResourceId = container1StructuredResourceId + "/" + acp.getResourceName();
		assertNotNull(acp.getResourceName());
		// INITIAL STATE ENDS

		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + subResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + subStructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_SUB_ACP")
	void TP_oneM2M_CSE_DMR_CRE_001_SUB_ACP() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;
		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1

		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilegeAndLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		container1ResourceId = acp.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + acp.getResourceName();
		assertNotNull(acp.getResourceName());

		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String subResourceId = sub.getResourceID();
		String subStructuredResourceId = container1StructuredResourceId + "/" + sub.getResourceName();
		assertNotNull(sub.getResourceName());
		// INITIAL STATE ENDS

		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" ACCESSCONTROLPOLICY  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACCESSCONTROLPOLICY  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + subResourceId);
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + subStructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_CIN_CNT")
	void TP_oneM2M_CSE_DMR_CRE_001_CIN_CNT() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// INITIAL STATE STARTS
		// CONTAINER CREATE 1
//		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertNotNull(cont.getResourceName());

		OneM2MResponse ciResponse = ciCreate.createWithAnyAttribute(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		String ciResourceId = ci.getResourceID();
		String ciStructuredResourceId = container1StructuredResourceId + "/" + ci.getResourceName();
		assertNotNull(cont.getResourceName());
		// CONTAINER UPDATE ENDS
		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// TEST EVENT ENDS
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ciResourceId);
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + ciStructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_GRP_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_GRP_CB() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		GroupCreate grpCreate = new GroupCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertNotNull(cont.getResourceName());

		OneM2MResponse groupCreateResponse = grpCreate.createGroupWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID), Arrays.asList(container1StructuredResourceId));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		String groupResourceId = group.getResourceID();
		String groupStructuredResourceId = aeStructuredResourceID + "/" + group.getResourceName();
		assertNotNull(group.getResourceName());
		// CONTAINER UPDATE ENDS
		genDel.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDel.deleteResource(groupStructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// TEST EVENT ENDS

		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" GROUP  RESOURCE ID : " + groupResourceId);
		System.out.println(" GROUP  STRUCTURED RESOURCE ID : " + groupStructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_GRP_AE")
	void TP_oneM2M_CSE_DMR_CRE_001_GRP_AE() {
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
		assertNotNull(ae.getResourceName());
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertNotNull(cont.getResourceName());

		OneM2MResponse groupCreateResponse = grpCreate.createGroupWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), Arrays.asList(container1StructuredResourceId));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		String groupResourceId = group.getResourceID();
		String groupStructuredResourceId = aeStructuredResourceID + "/" + group.getResourceName();
		assertNotNull(group.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" GROUP  RESOURCE ID : " + groupResourceId);
		System.out.println(" GROUP  STRUCTURED RESOURCE ID : " + groupStructuredResourceId);
	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001__SUB_GRP")
	void TP_oneM2M_CSE_DMR_CRE_001_SUB_GRP() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		SubscriptionCreate subCreate = new SubscriptionCreate();
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
		assertNotNull(ae.getResourceName());
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertNotNull(cont.getResourceName());

		OneM2MResponse groupCreateResponse = grpCreate.createGroupWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), Arrays.asList(container1StructuredResourceId));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		String groupResourceId = group.getResourceID();
		String groupStructuredResourceId = aeStructuredResourceID + "/" + group.getResourceName();
		assertNotNull(group.getResourceName());

		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), groupStructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String subResourceId = sub.getResourceID();
		String subStructuredResourceId = container1StructuredResourceId + "/" + sub.getResourceName();
		assertNotNull(sub.getResourceName());

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
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_PCH_AE")
	void TP_oneM2M_CSE_DMR_CRE_001_PCH_AE() {
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
		assertNotNull(ae.getResourceName());

		// CONTAINER CREATE 1
		OneM2MResponse pollingChannelResponse = pcCreate.createWithLabel(Arrays.asList("UNUpdated_Value"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		PollingChannel pc = (PollingChannel) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = pc.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + pc.getResourceName();
		assertNotNull(pc.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" POLLINGCHANNEL  RESOURCE ID : " + container1ResourceId);
		System.out.println(" POLLINGCHANNEL  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_NOD_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_NOD_CB() {
		NodeCreateTestCases nodeCreate = new NodeCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// CONTAINER CREATE 1
		OneM2MResponse pollingChannelResponse = nodeCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Node node = (Node) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = node.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + node.getResourceName();
		assertNotNull(node.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" POLLINGCHANNEL  RESOURCE ID : " + container1ResourceId);
		System.out.println(" POLLINGCHANNEL  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_LCP_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_LCP_CB() {
		LocationPolicyCreateTestCases lpCreate = new LocationPolicyCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// CONTAINER CREATE 1
		OneM2MResponse pollingChannelResponse = lpCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		LocationPolicy lp = (LocationPolicy) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = lp.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + lp.getResourceName();
		assertNotNull(lp.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" LOCATIONPOLICY  RESOURCE ID : " + container1ResourceId);
		System.out.println(" LOCATIONPOLICY  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_STCG_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_STCG_CB() {
		StatsConfigCreateTestCases stCgCreate = new StatsConfigCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse pollingChannelResponse = stCgCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		StatsConfig stcg = (StatsConfig) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = stcg.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + stcg.getResourceName();
		assertNotNull(stcg.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" STATSCONFIG  RESOURCE ID : " + container1ResourceId);
		System.out.println(" STATSCONFIG  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_STCL_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_STCL_CB() {
		StatsCollectCreateTestCases stClCreate = new StatsCollectCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse pollingChannelResponse = stClCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		StatsCollect stcl = (StatsCollect) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = stcl.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + stcl.getResourceName();
		assertNotNull(stcl.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" STATSCOLLECT  RESOURCE ID : " + container1ResourceId);
		System.out.println(" STATSCOLLECT  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_ASAR_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_ASAR_CB() {
		ServiceSubscribedAppRuleCreateTestCases ssarCreate = new ServiceSubscribedAppRuleCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// INITIAL STATE STARTS

		// CONTAINER CREATE 1
		OneM2MResponse pollingChannelResponse = ssarCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), pollingChannelResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		ServiceSubscribedAppRule ssar = (ServiceSubscribedAppRule) pollingChannelResponse.getPrimitiveContent().getAny();
		container1ResourceId = ssar.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + ssar.getResourceName();
		assertNotNull(ssar.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" SERVICESUBSCRIBEDAPPRULE  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SERVICESUBSCRIBEDAPPRULE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}
	
	
	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_EVCF_STCG")
	void TP_oneM2M_CSE_DMR_CRE_001_EVCF_STCG() {
		StatsConfigCreateTestCases stCgCreate = new StatsConfigCreateTestCases();
		EventConfigCreateTestCases ecCreate = new EventConfigCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		// CONTAINER CREATE 1

		OneM2MResponse scResponse = stCgCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), scResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		StatsConfig stcg = (StatsConfig) scResponse.getPrimitiveContent().getAny();
		container1ResourceId = stcg.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + stcg.getResourceName();
		assertNotNull(stcg.getResourceName());

		OneM2MResponse ecResponse = ecCreate.createWithAnyAttribute(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ecResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		EventConfig ec = (EventConfig) ecResponse.getPrimitiveContent().getAny();
		String ecResourceId = ec.getResourceID();
		String ecStructuredResourceId = container1StructuredResourceId + "/" + ec.getResourceName();
		assertNotNull(ec.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" STATSCONFIG  RESOURCE ID : " + container1ResourceId);
		System.out.println(" STATSCONFIG  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" EVENTCONFIG  RESOURCE ID : " + ecResourceId);
		System.out.println(" EVENTCONFIG  STRUCTURED RESOURCE ID : " + ecStructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_SUB_STCG")
	void TP_oneM2M_CSE_DMR_CRE_001_SUB_STCG() {
		StatsConfigCreateTestCases stCgCreate = new StatsConfigCreateTestCases();
		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1

		OneM2MResponse scResponse = stCgCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), scResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		StatsConfig stcg = (StatsConfig) scResponse.getPrimitiveContent().getAny();
		container1ResourceId = stcg.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + stcg.getResourceName();
		assertNotNull(stcg.getResourceName());

		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdated_Value"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String subResourceId = sub.getResourceID();
		String subStructuredResourceId = container1StructuredResourceId + "/" + sub.getResourceName();
		assertNotNull(sub.getResourceName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" STATSCONFIG  RESOURCE ID : " + container1ResourceId);
		System.out.println(" STATSCONFIG  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" EVENTCONFIG  RESOURCE ID : " + subResourceId);
		System.out.println(" EVENTCONFIG  STRUCTURED RESOURCE ID : " + subStructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_TS_CB")
	void TP_oneM2M_CSE_DMR_CRE_001_TS_CB() {
		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsCreateResponse = tsCreate.createWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsCreateResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + ts.getResourceName();
		assertNotNull(ts.getResourceName());
		genDel.deleteResource(container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		System.out.println(" TIMESERIES  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_TS_AE")
	void TP_oneM2M_CSE_DMR_CRE_001_TS_AE() {
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
		assertNotNull(ae.getResourceName());

		// CONTAINER CREATE 1
		OneM2MResponse tsCreateResponse = tsCreate.createWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsCreateResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + ts.getResourceName();
		assertNotNull(ts.getResourceName());
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_001_TSI_TS")
	void TP_oneM2M_CSE_DMR_CRE_001_TSI_TS() {
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
		assertNotNull(ae.getResourceName());

		// CONTAINER CREATE 1
		OneM2MResponse tsCreateResponse = tsCreate.createWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsCreateResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + ts.getResourceName();
		assertNotNull(ts.getResourceName());

		TimeSeriesInstanceCreateTestCases tsiCreate = new TimeSeriesInstanceCreateTestCases();
		OneM2MResponse tsiResponse = tsiCreate.createWithAnyAttribute(container1ResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsiResponse.getResponseStatusCode());
		TimeSeriesInstance tsi = (TimeSeriesInstance) tsiResponse.getPrimitiveContent().getAny();
		String tsiResourceId = tsi.getResourceID();
		String tsiStructuredResourceId = container1StructuredResourceId + "/" + tsi.getResourceName();
		assertNotNull(tsi.getResourceName());
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
