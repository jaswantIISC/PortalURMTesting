package cdot.ccsp.ssm;

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
import cdot.onem2m.resource.xsd.ServiceSubscribedAppRule;

public class ServiceSubscribedAppRuleCreateTestCases {


	public OneM2MResponse createWithAnyAttribute(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			ServiceSubscribedAppRule ssar = serviceSubscribedAppRule();
			ssar.setApplicableCredIDs(Arrays.asList("CredID-" + Math.random()));
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SERVICESUBSCRIBEDAPPRULE.getM2MResourceTypes(), ssar);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithLabel(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			ServiceSubscribedAppRule ssar = serviceSubscribedAppRule();
			ssar.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SERVICESUBSCRIBEDAPPRULE.getM2MResourceTypes(), ssar);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithACPIds(List<String> acpIds, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			ServiceSubscribedAppRule ssar = serviceSubscribedAppRule();
			ssar.setAccessControlPolicyIDs(acpIds);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SERVICESUBSCRIBEDAPPRULE.getM2MResourceTypes(), ssar);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}


	private ServiceSubscribedAppRule serviceSubscribedAppRule() {
		ServiceSubscribedAppRule ssar = new ServiceSubscribedAppRule();
		return ssar;
	}

}
