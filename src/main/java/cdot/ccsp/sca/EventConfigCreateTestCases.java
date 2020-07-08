package cdot.ccsp.sca;

import java.math.BigInteger;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.EventConfig;

public class EventConfigCreateTestCases {


	public OneM2MResponse createWithAnyAttribute(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			EventConfig ec = statsCollectCreate();
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.EVENTCONFIG.getM2MResourceTypes(), ec);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}


	private EventConfig statsCollectCreate() {
		EventConfig ec = new EventConfig();
		ec.setEventType(BigInteger.valueOf(1));
		return ec;
	}

}
