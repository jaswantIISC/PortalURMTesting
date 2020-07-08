package cdot.ccsp.security.acp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AccessControlPolicy;
import cdot.onem2m.resource.xsd.AccessControlRule;
import cdot.onem2m.resource.xsd.SetOfAcrs;

public class AccessControlPolicyCreateTestCases {



	public OneM2MResponse createWithCustomPrivilegeSelfPrivilege(SetOfAcrs priv, SetOfAcrs selfPriv, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setPrivileges(priv);
			acp.setSelfPrivileges(selfPriv);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithAllPrivilege(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithAllPrivilegeAndResultContent(BigInteger resultContent, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MRequest.setResultContent(resultContent);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithAllPrivilegeAndExpirationTime(String expirationTime, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			acp.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithAllPrivilegeAndResourceName(String resourceName, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			acp.setResourceName(resourceName);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithAllPrivilegeAndLabel(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			acp.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithLimitedPrivilegeAndLabel(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("CSE001"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("CSE001"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			acp.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
	


	public OneM2MResponse createWithOnlySelfPrivilege(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(null, null, Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	// THIS TEST SHOULD FAIL WITH STATUS CODE 4000 BAD REQUEST
	public OneM2MResponse createWithOnlyPrivilege(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithWildCardPrivSelfPriv(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("S*"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("CSE001"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithPatternPrivSelfPriv(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("/*/*"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("/CSE*"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
			;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	// THIS TEST SHOULD FAIL WITH STATUS CODE 4000 BAD REQUEST
	public OneM2MResponse createWithoutAnyPrivSelfPriv(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(null, null, null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithLimitedOperationPrivSelfPriv(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.RE.getValue(), Arrays.asList("/CSE00*"), AccessControlOperations.UP.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public AccessControlPolicy acpBasicCreateRequest(List<String> privOriginator, Integer privOperation, List<String> selfOriginator, Integer selfOperation) {
		AccessControlPolicy acp = new AccessControlPolicy();
		AccessControlRule acrPriv = new AccessControlRule();
		AccessControlRule acrSelfPriv = new AccessControlRule();
		
		if (privOriginator != null) {
			acrPriv.setAccessControlOriginators(privOriginator);
			acrPriv.setAccessControlOperations(BigInteger.valueOf(privOperation));
			SetOfAcrs setOfPrivAcr = new SetOfAcrs();
			setOfPrivAcr.setAccessControlRule(Arrays.asList(acrPriv));
			acp.setPrivileges(setOfPrivAcr);
		}


		if (selfOriginator != null) {
			acrSelfPriv.setAccessControlOperations(BigInteger.valueOf(selfOperation));
			acrSelfPriv.setAccessControlOriginators(selfOriginator);
			SetOfAcrs setOfSelfPrivAcr = new SetOfAcrs();
			setOfSelfPrivAcr.setAccessControlRule(Arrays.asList(acrSelfPriv));
			acp.setSelfPrivileges(setOfSelfPrivAcr);
		}


		acp.setLabels(Arrays.asList("ACP-" + Math.random()));

		return acp;

	}

}
