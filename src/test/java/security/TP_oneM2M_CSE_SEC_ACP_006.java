package security;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
import cdot.ccsp.onem2mTester.utils.GeneralDeleteTestCases;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.registration.AEUpdate;
import cdot.ccsp.security.acp.AccessControlOperations;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
import cdot.ccsp.security.acp.AccessControlPolicyUpdateTestCases;
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
import cdot.onem2m.resource.xsd.AccessControlRule.AccessControlContexts;
import cdot.onem2m.resource.xsd.SetOfAcrs;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_SEC_ACP_006")
public class TP_oneM2M_CSE_SEC_ACP_006 {

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
	@Tag("TP_oneM2M_CSE_SEC_ACP_006_CRE")
	void TP_oneM2M_CSE_SEC_ACP_006_CRE() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION



		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom( TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		SetOfAcrs setOfACRs = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_DE.getValue()));

		AccessControlContexts acrCont = new AccessControlContexts();
		Integer systemMinute = LocalDateTime.now().getMinute();
		String accessControlWindow;
		if (systemMinute == 5) {
			accessControlWindow = "* 59-04 * * * * *";
		} else if (systemMinute == 4) {
			accessControlWindow = "* 58-03 * * * * *";
		} else if (systemMinute == 3) {
			accessControlWindow = "* 57-02 * * * * *";
		} else if (systemMinute == 2) {
			accessControlWindow = "* 56-01 * * * * *";
		} else if (systemMinute == 1) {
			accessControlWindow = "* 55-00 * * * * *";
		} else if (systemMinute == 0) {
			accessControlWindow = "* 54-59 * * * * *";
		} else {
			accessControlWindow = "* " + (systemMinute - 6) + "-" + (systemMinute - 1) + " * * * * *";
		}

		acrCont.setAccessControlWindow(Arrays.asList(accessControlWindow));
		acr.setAccessControlContexts(Arrays.asList(acrCont));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));

		SetOfAcrs setOfACRSelf = new SetOfAcrs();
		AccessControlRule acrSelf = new AccessControlRule();
		acrSelf.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrSelf.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_RE_UP_DE_DI.getValue()));
		setOfACRSelf.setAccessControlRule(Arrays.asList(acrSelf));
		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRSelf, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse aeUpdateRes = aeUpdate.updateAccessControlPolicyIds(Arrays.asList(acp.getResourceID()), aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), aeUpdateRes.getResponseStatusCode());



		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		OneM2MResponse contCreateRes = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), contCreateRes.getResponseStatusCode());

		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		SetOfAcrs setOfACR = new SetOfAcrs();
		AccessControlRule acrs = new AccessControlRule();
		acrs.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrs.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_DE.getValue()));
		setOfACR.setAccessControlRule(Arrays.asList(acrs));
		OneM2MResponse acpUPdateRes = acpUpdate.updatePrivilege(setOfACR, acp.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpUPdateRes.getResponseStatusCode());

		GeneralDeleteTestCases genDel = new GeneralDeleteTestCases();
		OneM2MResponse delRes = genDel.deleteResource(acp.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.DELETED.getOnem2mStatusCode(), delRes.getResponseStatusCode());



		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}


	@Test
	@Tag("TP_oneM2M_CSE_SEC_ACP_006_RET")
	void TP_oneM2M_CSE_SEC_ACP_006_RET() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
		// AE-REGISTRATION


		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();


		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		SetOfAcrs setOfACRs = new SetOfAcrs();
		AccessControlRule acr = new AccessControlRule();
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.RE_DE.getValue()));
		AccessControlContexts acrCont = new AccessControlContexts();
		Integer systemMinute = LocalDateTime.now().getMinute();
		String accessControlWindow;
		if (systemMinute == 5) {
			accessControlWindow = "* 59-04 * * * * *";
		} else if (systemMinute == 4) {
			accessControlWindow = "* 58-03 * * * * *";
		} else if (systemMinute == 3) {
			accessControlWindow = "* 57-02 * * * * *";
		} else if (systemMinute == 2) {
			accessControlWindow = "* 56-01 * * * * *";
		} else if (systemMinute == 1) {
			accessControlWindow = "* 55-00 * * * * *";
		} else if (systemMinute == 0) {
			accessControlWindow = "* 54-59 * * * * *";
		} else {
			accessControlWindow = "* " + (systemMinute - 6) + "-" + (systemMinute - 1) + " * * * * *";
		}

		acrCont.setAccessControlWindow(Arrays.asList(accessControlWindow));
		acr.setAccessControlContexts(Arrays.asList(acrCont));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));

		SetOfAcrs setOfACRSelf = new SetOfAcrs();
		AccessControlRule acrSelf = new AccessControlRule();
		acrSelf.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrSelf.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_RE_UP_DE_DI.getValue()));
		setOfACRSelf.setAccessControlRule(Arrays.asList(acrSelf));

		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRSelf, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse aeUpdateRes = aeUpdate.updateAccessControlPolicyIds(Arrays.asList(acp.getResourceID()), aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), aeUpdateRes.getResponseStatusCode());

		GeneralRetrieveTestCases genRet = new GeneralRetrieveTestCases();
		OneM2MResponse ciResp = genRet.retrieveResource(ae.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), ciResp.getResponseStatusCode());

		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		SetOfAcrs setOfACR = new SetOfAcrs();
		AccessControlRule acrs = new AccessControlRule();
		acrs.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrs.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.RE_DE.getValue()));
		setOfACR.setAccessControlRule(Arrays.asList(acrs));
		OneM2MResponse acpUPdateRes = acpUpdate.updatePrivilege(setOfACR, acp.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpUPdateRes.getResponseStatusCode());


		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}

	@Test
	@Tag("TP_oneM2M_CSE_SEC_ACP_006_UPD")
	void TP_oneM2M_CSE_SEC_ACP_006_UPD() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;



		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
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
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.RE_DE.getValue()));
		AccessControlContexts acrCont = new AccessControlContexts();
		Integer systemMinute = LocalDateTime.now().getMinute();
		String accessControlWindow;
		if (systemMinute == 5) {
			accessControlWindow = "* 59-04 * * * * *";
		} else if (systemMinute == 4) {
			accessControlWindow = "* 58-03 * * * * *";
		} else if (systemMinute == 3) {
			accessControlWindow = "* 57-02 * * * * *";
		} else if (systemMinute == 2) {
			accessControlWindow = "* 56-01 * * * * *";
		} else if (systemMinute == 1) {
			accessControlWindow = "* 55-00 * * * * *";
		} else if (systemMinute == 0) {
			accessControlWindow = "* 54-59 * * * * *";
		} else {
			accessControlWindow = "* " + (systemMinute - 6) + "-" + (systemMinute - 1) + " * * * * *";
		}

		acrCont.setAccessControlWindow(Arrays.asList(accessControlWindow));
		acr.setAccessControlContexts(Arrays.asList(acrCont));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));

		SetOfAcrs setOfACRSelf = new SetOfAcrs();
		AccessControlRule acrSelf = new AccessControlRule();
		acrSelf.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrSelf.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_RE_UP_DE_DI.getValue()));
		setOfACRSelf.setAccessControlRule(Arrays.asList(acrSelf));

		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRSelf, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse aeUpdateRes = aeUpdate.updateAccessControlPolicyIds(Arrays.asList(acp.getResourceID()), aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), aeUpdateRes.getResponseStatusCode());


		OneM2MResponse aeUpdateResp = aeUpdate.updateLabel(Arrays.asList("UpdatedStatus"), aeResourceID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), aeUpdateResp.getResponseStatusCode());

		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		SetOfAcrs setOfACR = new SetOfAcrs();
		AccessControlRule acrs = new AccessControlRule();
		acrs.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrs.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.UP_DE.getValue()));
		setOfACR.setAccessControlRule(Arrays.asList(acrs));
		OneM2MResponse acpUPdateRes = acpUpdate.updatePrivilege(setOfACR, acp.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpUPdateRes.getResponseStatusCode());


		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

	}


	@Test
	@Tag("TP_oneM2M_CSE_SEC_ACP_006_DEL")
	void TP_oneM2M_CSE_SEC_ACP_006_DEL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;



		// INITIAL STATE STARTS
		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG
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
		acr.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acr.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.DE.getValue()));
		AccessControlContexts acrCont = new AccessControlContexts();
		Integer systemMinute = LocalDateTime.now().getMinute();
		String accessControlWindow;
		if (systemMinute == 5) {
			accessControlWindow = "* 59-04 * * * * *";
		} else if (systemMinute == 4) {
			accessControlWindow = "* 58-03 * * * * *";
		} else if (systemMinute == 3) {
			accessControlWindow = "* 57-02 * * * * *";
		} else if (systemMinute == 2) {
			accessControlWindow = "* 56-01 * * * * *";
		} else if (systemMinute == 1) {
			accessControlWindow = "* 55-00 * * * * *";
		} else if (systemMinute == 0) {
			accessControlWindow = "* 54-59 * * * * *";
		} else {
			accessControlWindow = "* " + (systemMinute - 6) + "-" + (systemMinute - 1) + " * * * * *";
		}

		acrCont.setAccessControlWindow(Arrays.asList(accessControlWindow));
		acr.setAccessControlContexts(Arrays.asList(acrCont));
		setOfACRs.setAccessControlRule(Arrays.asList(acr));

		SetOfAcrs setOfACRSelf = new SetOfAcrs();
		AccessControlRule acrSelf = new AccessControlRule();
		acrSelf.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrSelf.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.CR_RE_UP_DE_DI.getValue()));
		setOfACRSelf.setAccessControlRule(Arrays.asList(acrSelf));

		OneM2MResponse acpResponse = acpCreate.createWithCustomPrivilegeSelfPrivilege(setOfACRs, setOfACRSelf, TestingParams.ADMIN_AE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();


		AEUpdate aeUpdate = new AEUpdate();
		OneM2MResponse aeUpdateRes = aeUpdate.updateAccessControlPolicyIds(Arrays.asList(acp.getResourceID()), aeResourceID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), aeUpdateRes.getResponseStatusCode());

		GeneralDeleteTestCases genDel = new GeneralDeleteTestCases();
		OneM2MResponse ciResp = genDel.deleteResource(ae.getResourceID(), TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.ACCESS_DENIED.getOnem2mStatusCode(), ciResp.getResponseStatusCode());


		AccessControlPolicyUpdateTestCases acpUpdate = new AccessControlPolicyUpdateTestCases();
		SetOfAcrs setOfACR = new SetOfAcrs();
		AccessControlRule acrs = new AccessControlRule();
		acrs.setAccessControlOriginators(Arrays.asList(TestingParams.ADMIN_AE_ID, TestingParams.GENERAL_ADN_AE_ID));
		acrs.setAccessControlOperations(BigInteger.valueOf(AccessControlOperations.DE.getValue()));
		setOfACR.setAccessControlRule(Arrays.asList(acrs));
		OneM2MResponse acpUPdateRes = acpUpdate.updatePrivilege(setOfACR, acp.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), acpUPdateRes.getResponseStatusCode());


		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);

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
