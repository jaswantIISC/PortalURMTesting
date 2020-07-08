package cdot.ccsp.locationPolicy;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MLocationSource;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.LocationPolicy;

public class LocationPolicyCreateTestCases {

	public OneM2MResponse createWithAnyAttribute(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			LocationPolicy lp = locationPolicyCreate();
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.LOCATIONPOLICY.getM2MResourceTypes(), lp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	private LocationPolicy locationPolicyCreate() {
		LocationPolicy lp = new LocationPolicy();
		lp.setLocationSource(M2MLocationSource.Network_based.getLocationSource());
		lp.setLocationTargetID(TestingParams.LOCATION_POLICY_TARGET_ID);
		lp.setLocationServer(TestingParams.LOCATION_POLICY_SERVER);
		return lp;
	}

}
