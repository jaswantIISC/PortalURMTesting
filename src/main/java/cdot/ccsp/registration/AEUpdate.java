package cdot.ccsp.registration;

import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;

public class AEUpdate {

	ComposeRequest composeRequest = new ComposeRequest();
	OriginatorActions originatorActions = new OriginatorActionsBean();

	public OneM2MResponse updateLabel(List<String> label, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setLabels(label);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateAccessControlPolicyIds(List<String> acpi, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setAccessControlPolicyIDs(acpi);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateApplicaitonName(String appName, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setAppName(appName);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updatePointOfAccess(List<String> poa, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setPointOfAccess(poa);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateOntologyReference(String ontRef, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setOntologyRef(ontRef);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateExpiration(String expiration, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setExpirationTime(expiration);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateRequestReachability(Boolean rr, String to, String from, SecurityParams securityParams) {

		AE ae = new AE();
		ae.setRequestReachability(rr);
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}


}