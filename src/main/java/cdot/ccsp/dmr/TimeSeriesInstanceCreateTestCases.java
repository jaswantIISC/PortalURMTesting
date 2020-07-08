package cdot.ccsp.dmr;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.TimeSeriesInstance;

public class TimeSeriesInstanceCreateTestCases {


	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

	public OneM2MResponse createWithAnyAttribute(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeriesInstance ci = contentInstanceCreate("ContentIsThisWhichDumbData");
			Calendar cal = Calendar.getInstance();
//			cal.add(Calendar.MONTH, 1);
			String creationTime = sdf.format(cal.getTime());
			ci.setDataGenerationTime(creationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIESINSTANCE.getM2MResourceTypes(), ci);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithContentValue(String content, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeriesInstance ci = new TimeSeriesInstance();
			ci.setContent(content);
			Calendar cal = Calendar.getInstance();
//			cal.add(Calendar.MONTH, 1);
			String creationTime = sdf.format(cal.getTime());
			ci.setDataGenerationTime(creationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIESINSTANCE.getM2MResourceTypes(), ci);
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
			TimeSeriesInstance ci = new TimeSeriesInstance();
			ci.setContent("SomeThingRandom");
			ci.setExpirationTime(expirationTime);
			Calendar cal = Calendar.getInstance();
//			cal.add(Calendar.MONTH, 1);
			String creationTime = sdf.format(cal.getTime());
			ci.setDataGenerationTime(creationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIESINSTANCE.getM2MResourceTypes(), ci);
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
			TimeSeriesInstance ci = new TimeSeriesInstance();
			ci.setContent("SomeThingRandom");
			ci.setLabels(label);
			Calendar cal = Calendar.getInstance();
//			cal.add(Calendar.MONTH, 1);
			String creationTime = sdf.format(cal.getTime());
			ci.setDataGenerationTime(creationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIESINSTANCE.getM2MResourceTypes(), ci);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithSequenceNumber(BigInteger seqNr, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			TimeSeriesInstance ci = new TimeSeriesInstance();
			ci.setContent("SomeThingRandom");
			ci.setSequenceNr(seqNr);
			Calendar cal = Calendar.getInstance();
//			cal.add(Calendar.MONTH, 1);
			String creationTime = sdf.format(cal.getTime());
			ci.setDataGenerationTime(creationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.TIMESERIESINSTANCE.getM2MResourceTypes(), ci);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}


	private TimeSeriesInstance contentInstanceCreate(String content) {
		TimeSeriesInstance tsi = new TimeSeriesInstance();

		if (content != null) {
			System.out.println(" ################################### content : " + content);
			tsi.setContent(content);
		} else {
			System.out.println(" ################################### content : ContentIsThisWhichRandomData");
			tsi.setContent("ContentIsThisWhichRandomData");
		}
		
		return tsi;
	}

}
