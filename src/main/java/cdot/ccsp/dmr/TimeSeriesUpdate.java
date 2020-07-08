package cdot.ccsp.dmr;

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
import cdot.onem2m.resource.xsd.TimeSeries;

public class TimeSeriesUpdate {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

	public OneM2MResponse updateLabels(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateLabelWithRCN(BigInteger rcn, List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MRequest.setResultContent(rcn);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
	public OneM2MResponse updateCreationTimeTS(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setCreationTime(sdf.format(new Date()));
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateExpirationTimeTS(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setExpirationTime(null);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateExpirationTime(String expirationTime, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
	public OneM2MResponse updateAccessControlPolicyIds(List<String> acpid, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setAccessControlPolicyIDs(acpid);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMaxNumberOfInstances(BigInteger mni, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setMaxNrOfInstances(mni);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMaxByteSize(BigInteger mbs, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setMaxByteSize(mbs);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMaxInstanceAge(BigInteger mia, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setMaxInstanceAge(mia);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updatePeriodicInterval(BigInteger pi, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setPeriodicInterval(pi);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMissingDataDetect(Boolean mdd, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setMissingDataDetect(mdd);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMissingDataMaxNumber(BigInteger mdmn, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setMissingDataMaxNr(mdmn);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMissingDataDetectTimer(BigInteger mddt, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setMissingDataDetectTimer(mddt);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateOntologyReference(String or, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = new TimeSeries();
			ts.setOntologyRef(or);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
}
