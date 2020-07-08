package data.management.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import cdot.ccsp.dmr.ContainerCreateTestCases;
import cdot.ccsp.dmr.ContainerUpdateTestCases;
import cdot.ccsp.dmr.TimeSeriesCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesUpdate;
import cdot.ccsp.groupMgmt.GroupCreate;
import cdot.ccsp.groupMgmt.GroupUpdate;
import cdot.ccsp.node.NodeCreateTestCases;
import cdot.ccsp.node.NodeUpdateTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.registration.AEUpdate;
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
import cdot.onem2m.resource.xsd.BatchNotify;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.EventNotificationCriteria;
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.Node;
import cdot.onem2m.resource.xsd.RateLimit;
import cdot.onem2m.resource.xsd.Subscription;
import cdot.onem2m.resource.xsd.TimeSeries;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_UPD_015")
public class TP_oneM2M_CSE_DMR_UPD_015 {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	String aeId="S011";
	String aeResourceID="S011";
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
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_AE_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_AE_LBL() {

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
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse containerUpdateResponse = aeUpdate.updateLabel(Arrays.asList("UpdatedLabelValue"), aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedLabelValue"), ae.getLabels());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_AE_ACPI")
	void TP_oneM2M_CSE_DMR_UPD_015_AE_ACPI() {
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
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		String acpResourceId = acp.getResourceID();

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse containerUpdateResponse = aeUpdate.updateAccessControlPolicyIds(Arrays.asList(acpResourceId), aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(acpResourceId), ae.getAccessControlPolicyIDs());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_AE_APN")
	void TP_oneM2M_CSE_DMR_UPD_015_AE_APN() {
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
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse containerUpdateResponse = aeUpdate.updateApplicaitonName("MyNewAlias", aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals("MyNewAlias", ae.getAppName());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_AE_POA")
	void TP_oneM2M_CSE_DMR_UPD_015_AE_POA() {
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

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse containerUpdateResponse = aeUpdate.updatePointOfAccess(Arrays.asList("http://IP1:PORT1", "http://IP2:PORT2"), aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("http://IP1:PORT1", "http://IP2:PORT2"), ae.getPointOfAccess());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_AE_OR")
	void TP_oneM2M_CSE_DMR_UPD_015_AE_OR() {
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

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse containerUpdateResponse = aeUpdate.updateOntologyReference("WhatTheFishIsOntologyReference", aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals("WhatTheFishIsOntologyReference", ae.getOntologyRef());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_CNT_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_CNT_LBL() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();

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
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + cont.getResourceName();

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerLabel(Arrays.asList("UpdatedContainerLabel"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedContainerLabel"), cont.getLabels());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_CNT_ACPI")
	void TP_oneM2M_CSE_DMR_UPD_015_CNT_ACPI() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + cont.getResourceName();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		String acpResourceId = acp.getResourceID();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerAccessControlPolicyIds(Arrays.asList(acpResourceId), container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(acpResourceId), cont.getAccessControlPolicyIDs());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(acpResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_CNT_MNI")
	void TP_oneM2M_CSE_DMR_UPD_015_CNT_MNI() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + cont.getResourceName();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerMaxNrOfInstances(BigInteger.valueOf(15), container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(15), cont.getMaxNrOfInstances());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_CNT_MBS")
	void TP_oneM2M_CSE_DMR_UPD_015_CNT_MBS() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + cont.getResourceName();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerMaxByteSize(BigInteger.valueOf(10000), container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10000), cont.getMaxByteSize());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_CNT_MIA")
	void TP_oneM2M_CSE_DMR_UPD_015_CNT_MIA() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + cont.getResourceName();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerMaxInstanceAge(BigInteger.valueOf(150000), container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(150000), cont.getMaxInstanceAge());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_CNT_OR")
	void TP_oneM2M_CSE_DMR_UPD_015_CNT_OR() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + cont.getResourceName();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONT RETRIEVE
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerOntologyRef("NewOntologyRef", container1ResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals("NewOntologyRef", cont.getOntologyRef());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_ACP_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_ACP_LBL() {

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		container1ResourceId = acp.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + acp.getResourceName();
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = acpUpdate.updateLabelsACP(Arrays.asList("UpdatedLabel"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedLabel"), acp.getLabels());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_ACPI")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_ACPI() {

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
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		String acpResourceId = acp.getResourceID();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse acpRetrieveResponse = subUpdate.updateACPIDs(Arrays.asList(acpResourceId), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		subscription = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(acpResourceId), subscription.getAccessControlPolicyIDs());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(acpResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_LBL() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse acpRetrieveResponse = subUpdate.updateLabelsSUB(Arrays.asList("UpdatedLabel"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		subscription = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedLabel"), subscription.getLabels());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_ENC")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_ENC() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("Un_Updated_Value"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		EventNotificationCriteria enc = new EventNotificationCriteria();
		List<BigInteger> eventNotifType = new ArrayList<BigInteger>();
		eventNotifType.add(BigInteger.valueOf(1));
		eventNotifType.add(BigInteger.valueOf(3));
		enc.setNotificationEventType(eventNotifType);
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateEventNotificationCriteria(enc, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
//		assertEquals(enc, subscription.getEventNotificationCriteria());

		XStream xstream = new XStream(new StaxDriver());
		String requestData = xstream.toXML(enc);
		String responseData = xstream.toXML(subscription.getEventNotificationCriteria());
		assertEquals(requestData, responseData);

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_EXC")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_EXC() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("Un_Updated_Value"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateExpirationCounter(BigInteger.valueOf(100), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(100), subscription.getExpirationCounter());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_GPI")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_GPI() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_ID + "/" + TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();

		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(Arrays.asList("UnUpdatedValue"), aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		GroupCreate groupCreate = new GroupCreate();
		OneM2MResponse groupCreateResponse = groupCreate.createGroupWithLabel(Arrays.asList("SubsriptionKaGroup"), aeResourceID, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), Arrays.asList(cont.getResourceID()));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateGroupId(group.getResourceID(), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(group.getResourceID(), subscription.getGroupID());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_NFU")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_NFU() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateNotificationForwardingURI("http://IP:PORT/SomeRandomPattern", container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals("http://IP:PORT/SomeRandomPattern", subscription.getNotificationForwardingURI());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUb  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_BN")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_BN() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		BatchNotify bn = new BatchNotify();
		try {

			bn.setNumber(BigInteger.valueOf(10));
			Duration duration = DatatypeFactory.newInstance().newDuration("P0Y0M0DT0H5M0S");
			bn.setDuration(duration);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateBatchNotify(bn, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		XStream xstream = new XStream(new StaxDriver());
		String requestData = xstream.toXML(bn);
		String responseData = xstream.toXML(subscription.getBatchNotify());
		assertEquals(requestData, responseData);

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_RL")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_RL() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		RateLimit rl = new RateLimit();
		try {

			rl.setMaxNrOfNotify(BigInteger.valueOf(10));
			Duration duration = DatatypeFactory.newInstance().newDuration("P0Y0M0DT0H5M0S");
			rl.setTimeWindow(duration);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateRateLimit(rl, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		XStream xstream = new XStream(new StaxDriver());
		String requestData = xstream.toXML(rl);
		String responseData = xstream.toXML(subscription.getRateLimit());
		assertEquals(requestData, responseData);

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		// CONTAINER UPDATE ENDS

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUb  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_PN")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_PN() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updatePendingNotification(BigInteger.valueOf(10), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), subscription.getPendingNotification());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_NSP")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_NSP() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateNotificationStoragePriority(BigInteger.valueOf(10), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), subscription.getNotificationStoragePriority());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_LN")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_LN() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateLatestNotify(true, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(true, subscription.getLatestNotify());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	// BONUS TEST CASE
	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_NCT")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_NCT() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateNotificationContentType(BigInteger.valueOf(1), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(1), subscription.getNotificationContentType());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_NEC")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_NEC() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("Un_Updated_Value"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse subUpdateResponse = subUpdate.updateNotificationEventCategory("SomeEventCategory", container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), subUpdateResponse.getResponseStatusCode());
		subscription = (Subscription) subUpdateResponse.getPrimitiveContent().getAny();
		assertEquals("SomeEventCategory", subscription.getNotificationEventCat());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	// BONUS TESTCASE
	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_SUB_ET")
	void TP_oneM2M_CSE_DMR_UPD_015_SUB_ET() {

		SubscriptionCreate subCreate = new SubscriptionCreate();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse subResponse = subCreate.subWithLabel(Arrays.asList("UnUpdatedValue"), TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Subscription subscription = (Subscription) subResponse.getPrimitiveContent().getAny();
		container1ResourceId = subscription.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + subscription.getResourceName();
		// CONTAINER CREATE 1 ENDS\
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		SubscriptionUpdate subUpdate = new SubscriptionUpdate();
		OneM2MResponse acpRetrieveResponse = subUpdate.updateExpirationTime(expirationTime, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		subscription = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, subscription.getExpirationTime());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_GRP_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_GRP_LBL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		GroupCreate groupCreate = new GroupCreate();
		OneM2MResponse groupCreateResponse = groupCreate.CE_GMG_00001_00000(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID), Arrays.asList(cont.getResourceID()));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = group.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + group.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		GroupUpdate grpUpdate = new GroupUpdate();
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateLabelsGRP(Arrays.asList("UpdatedLabel"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedLabel"), group.getLabels());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_GRP_ACPI")
	void TP_oneM2M_CSE_DMR_UPD_015_GRP_ACPI() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		GroupCreate groupCreate = new GroupCreate();
		OneM2MResponse groupCreateResponse = groupCreate.CE_GMG_00001_00000(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID), Arrays.asList(cont.getResourceID()));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = group.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + group.getResourceName();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		String acpResourceId = acp.getResourceID();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		GroupUpdate grpUpdate = new GroupUpdate();
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateAccessControlPolicyIdsGRP(Arrays.asList(acpResourceId), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(acpResourceId), group.getAccessControlPolicyIDs());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(acpResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));

		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_GRP_MACP")
	void TP_oneM2M_CSE_DMR_UPD_015_GRP_MACP() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		GroupCreate groupCreate = new GroupCreate();
		OneM2MResponse groupCreateResponse = groupCreate.CE_GMG_00001_00000(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID), Arrays.asList(cont.getResourceID()));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = group.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + group.getResourceName();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		String acpResourceId = acp.getResourceID();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		GroupUpdate grpUpdate = new GroupUpdate();
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateMemberAccessControlPolicyIdsGRP(Arrays.asList(acpResourceId), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(acpResourceId), group.getMembersAccessControlPolicyIDs());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(acpResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_GRP_GN")
	void TP_oneM2M_CSE_DMR_UPD_015_GRP_GN() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse container1Response = contCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		GroupCreate groupCreate = new GroupCreate();
		OneM2MResponse groupCreateResponse = groupCreate.CE_GMG_00001_00000(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID), Arrays.asList(cont.getResourceID()));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Group group = (Group) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = group.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + group.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		GroupUpdate grpUpdate = new GroupUpdate();
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateGroupNameGRP("UpdatedGroupName", container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals("UpdatedGroupName", group.getGroupName());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_NOD_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_NOD_LBL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		NodeCreateTestCases nodeCreate = new NodeCreateTestCases();
		OneM2MResponse groupCreateResponse = nodeCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Node node = (Node) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = node.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + node.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		NodeUpdateTestCases nodeUpdate = new NodeUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = nodeUpdate.updateLabels(Arrays.asList("UpdatedLabel"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		node = (Node) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedLabel"), node.getLabels());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_NOD_ACPI")
	void TP_oneM2M_CSE_DMR_UPD_015_NOD_ACPI() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		NodeCreateTestCases nodeCreate = new NodeCreateTestCases();
		OneM2MResponse groupCreateResponse = nodeCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Node node = (Node) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = node.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + node.getResourceName();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		String acpResourceId = acp.getResourceID();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		NodeUpdateTestCases nodeUpdate = new NodeUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = nodeUpdate.updateAccessControlPolicyIds(Arrays.asList(acpResourceId), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		node = (Node) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(acpResourceId), node.getAccessControlPolicyIDs());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(acpResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_NOD_HCL")
	void TP_oneM2M_CSE_DMR_UPD_015_NOD_HCL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		NodeCreateTestCases nodeCreate = new NodeCreateTestCases();
		OneM2MResponse groupCreateResponse = nodeCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), groupCreateResponse.getResponseStatusCode());
		Node node = (Node) groupCreateResponse.getPrimitiveContent().getAny();
		container1ResourceId = node.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + node.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		NodeUpdateTestCases nodeUpdate = new NodeUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = nodeUpdate.updateHostedCSELinks("NewHostingCSELink", container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		node = (Node) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals("NewHostingCSELink", node.getHostedCSELink());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_ACPI")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_ACPI() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse2 = acpCreate.createWithAllPrivilege(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		AccessControlPolicy secondACPResource = (AccessControlPolicy) acpResponse2.getPrimitiveContent().getAny();
		String secondACPResourceID = secondACPResource.getResourceID();

		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateAccessControlPolicyIds(Arrays.asList(secondACPResourceID), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(secondACPResourceID), ts.getAccessControlPolicyIDs());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_LBL")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_LBL() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateLabels(Arrays.asList("UpdatedLabel"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("UpdatedLabel"), ts.getLabels());
		// CONTAINER UPDATE ENDS

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_MNI")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_MNI() {

		
		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		System.out.println(" ############# sending create request.....");
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" ############# create request response returned ..........");
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		System.out.println(" ############# create asserted ..........");
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		System.out.println(" ############# sending update request.....");
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateMaxNumberOfInstances(BigInteger.valueOf(15), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" ############# update request response.....");
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(15), ts.getMaxNrOfInstances());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		System.out.println(" ############# sending  delete request.....");
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_MBS")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_MBS() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateMaxByteSize(BigInteger.valueOf(10000), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10000), ts.getMaxByteSize());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_MIA")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_MIA() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateMaxInstanceAge(BigInteger.valueOf(10000), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10000), ts.getMaxInstanceAge());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_PEI")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_PEI() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updatePeriodicInterval(BigInteger.valueOf(1000), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(1000), ts.getPeriodicInterval());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_MDD")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_MDD() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateMissingDataDetect(true, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(true, ts.getMissingDataDetect());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_MDN")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_MDN() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS

		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE

		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateMissingDataMaxNumber(BigInteger.valueOf(200), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(200), ts.getMissingDataMaxNr());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_MDT")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_MDT() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateMissingDataDetectTimer(BigInteger.valueOf(200), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(200), ts.getMissingDataDetectTimer());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_015_TS_OR")
	void TP_oneM2M_CSE_DMR_UPD_015_TS_OR() {

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// CONTAINER CREATE 1
		OneM2MResponse tsResponse = tsCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		container1ResourceId = ts.getResourceID();
		container1StructuredResourceId = TestingParams.CSE_BASE_RESOURCE_NAME + "/" + ts.getResourceName();
		// CONTAINER CREATE 1 ENDS
		// INITIAL STATE ENDS

		// TEST EVENT STARTS
		// CONTAINER UPDATE
		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateOntologyReference("SomeOntologyReference", container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals("SomeOntologyReference", ts.getOntologyRef());
		// CONTAINER UPDATE ENDS
		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		System.out.println(" TS  RESOURCE ID : " + container1ResourceId);
		System.out.println(" TS  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
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
