package cdot.ccsp.pollingchannel;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.PollingChannel;

public class PollingChannelUpdate {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	
	public OneM2MResponse updateLabelsPollingChannel(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			PollingChannel pc = new PollingChannel();
			pc.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, pc);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateLabelsWithRCN(BigInteger rcn, List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			PollingChannel pc = new PollingChannel();
			pc.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, pc);
			oneM2MRequest.setResultContent(rcn);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateCreationTimePollingChannel(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			PollingChannel pc = new PollingChannel();
			pc.setCreationTime(sdf.format(new Date()));
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, pc);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateExpirationTimePollingChannel(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			PollingChannel pc = new PollingChannel();
			pc.setExpirationTime(null);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, pc);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
}
