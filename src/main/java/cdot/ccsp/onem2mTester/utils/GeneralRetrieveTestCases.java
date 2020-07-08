package cdot.ccsp.onem2mTester.utils;

import java.math.BigInteger;

import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.FilterCriteria;

public class GeneralRetrieveTestCases {


	ComposeRequest composeRequest = new ComposeRequest();

	public OneM2MResponse retrieveResource(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			oneM2MRequest = ComposeRequest.oneM2MRequestRetrieve(to, from);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse retrieveResourceWithCustomRCN(int rcnValue, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			oneM2MRequest = ComposeRequest.oneM2MRequestRetrieve(to, from);
			oneM2MRequest.setResultContent(BigInteger.valueOf(rcnValue));
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse retrieveForDiscovery(FilterCriteria filCri, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			oneM2MRequest = ComposeRequest.oneM2MRequestRetrieve(to, from);
			oneM2MRequest.setFilterCriteria(filCri);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse retrieveForDiscoveryAndDRI(BigInteger dri, FilterCriteria filCri, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			oneM2MRequest = ComposeRequest.oneM2MRequestRetrieve(to, from);
			oneM2MRequest.setFilterCriteria(filCri);
			oneM2MRequest.setDesiredIdentifierResultType(dri);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

}
