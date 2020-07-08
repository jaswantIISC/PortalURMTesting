package cdot.ccsp.dmr;

import java.math.BigInteger;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.TimeSeries;

public class TimeSeriesCreateTestCases {




	public OneM2MResponse createWithAnyAttribute(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithResultContent(BigInteger resultContent, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MRequest.setResultContent(resultContent);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithResultContentAndLabel(BigInteger resultContent, List<String> label, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setLabels(label);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MRequest.setResultContent(resultContent);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMaxNumberOfInstances(BigInteger mni, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setMaxNrOfInstances(mni);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMaxByteSize(BigInteger mbs, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setMaxByteSize(mbs);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMaxInstanceAge(BigInteger mia, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setMaxInstanceAge(mia);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithLabel(List<String> label, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setLabels(label);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithResourceName(String resourceName, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setResourceName(resourceName);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithLACPIds(List<String> acpi, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setAccessControlPolicyIDs(acpi);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithExpirationTime(String expirationTime, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithCreator(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setExpirationTime(from);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithPeriodicInterval(BigInteger periodicInterval, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setPeriodicInterval(periodicInterval);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMissingDataDetect(Boolean mdd, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setMissingDataDetect(mdd);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMissingDataDetectMaxNumber(BigInteger mdn, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setMissingDataMaxNr(mdn);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMissingDataDetectTimer(BigInteger mdt, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setMissingDataDetectTimer(mdt);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithOntologyRef(String or, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeries ts = timeSeriesCreate();
			ts.setOntologyRef(or);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIES.getM2MResourceTypes(), ts);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	private TimeSeries timeSeriesCreate() {
		TimeSeries ts = new TimeSeries();

		
		return ts;
	}

}
