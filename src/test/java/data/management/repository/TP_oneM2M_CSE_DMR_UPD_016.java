package data.management.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
import cdot.ccsp.security.acp.AccessControlOperations;
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
import cdot.onem2m.resource.xsd.AccessControlRule;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.Node;
import cdot.onem2m.resource.xsd.SetOfAcrs;
import cdot.onem2m.resource.xsd.Subscription;
import cdot.onem2m.resource.xsd.TimeSeries;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_UPD_016")
public class TP_oneM2M_CSE_DMR_UPD_016 {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
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
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_AE_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_AE_ET() {

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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());

		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse containerUpdateResponse = aeUpdate.updateExpiration(expirationTime, aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, ae.getExpirationTime());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_AE_RR")
	void TP_oneM2M_CSE_DMR_UPD_016_AE_RR() {
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
		OneM2MResponse containerUpdateResponse = aeUpdate.updateRequestReachability(true, aeStructuredResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		ae = (AE) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(true, ae.getRequestReachability());
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_CNT_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_CNT_ET() {
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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		ContainerUpdateTestCases contUpdate = new ContainerUpdateTestCases();
		OneM2MResponse containerUpdateResponse = contUpdate.updateContainerExpirationTime(expirationTime, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), containerUpdateResponse.getResponseStatusCode());
		cont = (Container) containerUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, cont.getExpirationTime());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		// TEST EVENT ENDS

		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_ACP_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_ACP_ET() {

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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = acpUpdate.updateExpirationTime(expirationTime, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, acp.getExpirationTime());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_ACP_PV")
	void TP_oneM2M_CSE_DMR_UPD_016_ACP_PV() {

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
		SetOfAcrs priv = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		List<String> origi = new ArrayList<String>();
		origi.add("ALL");
		origi.add(TestingParams.ADMIN_AE_ID);
		acr.setAccessControlOriginators(origi);
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue()));
		List<AccessControlRule> acrs = new ArrayList<AccessControlRule>();
		acrs.add(acr);
		priv.setAccessControlRule(acrs);
		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = acpUpdate.updatePrivilege(priv, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
		XStream xstream = new XStream(new StaxDriver());
		String requestData = xstream.toXML(priv);
		String responseData = xstream.toXML(acp.getPrivileges());
		assertEquals(requestData, responseData);

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_ACP_SPV")
	void TP_oneM2M_CSE_DMR_UPD_016_ACP_SPV() {

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
		SetOfAcrs priv = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		List<String> origi = new ArrayList<String>();
		origi.add("ALL");
		origi.add(TestingParams.ADMIN_AE_ID);
		acr.setAccessControlOriginators(origi);
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue()));
		List<AccessControlRule> acrs = new ArrayList<AccessControlRule>();
		acrs.add(acr);
		priv.setAccessControlRule(acrs);
		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = acpUpdate.updateSelfPrivilege(priv, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		acp = (AccessControlPolicy) acpRetrieveResponse.getPrimitiveContent().getAny();
		XStream xstream = new XStream(new StaxDriver());
		String requestData = xstream.toXML(priv);
		String responseData = xstream.toXML(acp.getSelfPrivileges());
		assertEquals(requestData, responseData);

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS

		System.out.println(" ACP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" ACP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}


	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_SUB_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_SUB_ET() {

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
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_SUB_NCT")
	void TP_oneM2M_CSE_DMR_UPD_016_SUB_NCT() {

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
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_SUB_NU")
	void TP_oneM2M_CSE_DMR_UPD_016_SUB_NU() {

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
		OneM2MResponse acpRetrieveResponse = subUpdate.updateNotificationURI(Arrays.asList("http://NEWEIP:NEWPORT"), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		subscription = (Subscription) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList("http://NEWEIP:NEWPORT"), subscription.getNotificationURI());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" SUB  RESOURCE ID : " + container1ResourceId);
		System.out.println(" SUB  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_GRP_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_GRP_ET() {

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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		GroupUpdate grpUpdate = new GroupUpdate();
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateExpirtationTime(expirationTime, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, group.getExpirationTime());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_GRP_MNM")
	void TP_oneM2M_CSE_DMR_UPD_016_GRP_MNM() {

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
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateMaxNumberOfMembers(BigInteger.valueOf(10), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), group.getMaxNrOfMembers());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_GRP_MID")
	void TP_oneM2M_CSE_DMR_UPD_016_GRP_MID() {

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
		OneM2MResponse container2Response = contCreate.createWithAnyAttribute(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container2Response.getResponseStatusCode());
		Container cont2 = (Container) container2Response.getPrimitiveContent().getAny();
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
		OneM2MResponse acpRetrieveResponse = grpUpdate.updateMembersIds(Arrays.asList(cont.getResourceID(), cont2.getResourceID()), container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		group = (Group) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(Arrays.asList(cont.getResourceID(), cont2.getResourceID()), group.getMemberIDs());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		genDelete.deleteResource(cont2.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" GRP  RESOURCE ID : " + container1ResourceId);
		System.out.println(" GRP  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_NOD_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_NOD_ET() {

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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		NodeUpdateTestCases nodeUpdate = new NodeUpdateTestCases();
		OneM2MResponse acpRetrieveResponse = nodeUpdate.updateExpirationTime(expirationTime, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		node = (Node) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, node.getExpirationTime());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_NOD_NI")
	void TP_oneM2M_CSE_DMR_UPD_016_NOD_NI() {

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
		String nodeId = Double.toString(Math.random());
		OneM2MResponse acpRetrieveResponse = nodeUpdate.updateNodeId(nodeId, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpRetrieveResponse.getResponseStatusCode());
		node = (Node) acpRetrieveResponse.getPrimitiveContent().getAny();
		assertEquals(nodeId, node.getNodeID());

		GeneralDeleteTestCases genDelete = new GeneralDeleteTestCases();
		genDelete.deleteResource(container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		// CONTAINER UPDATE ENDS
		System.out.println(" NODE  RESOURCE ID : " + container1ResourceId);
		System.out.println(" NODE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}
	@Test
	@Tag("TP_oneM2M_CSE_DMR_UPD_016_TS_ET")
	void TP_oneM2M_CSE_DMR_UPD_016_TS_ET() {

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
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		TimeSeriesUpdate tsUpdate = new TimeSeriesUpdate();
		OneM2MResponse tsbUpdateResponse = tsUpdate.updateExpirationTime(expirationTime, container1StructuredResourceId, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tsbUpdateResponse.getResponseStatusCode());
		ts = (TimeSeries) tsbUpdateResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, ts.getExpirationTime());
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
