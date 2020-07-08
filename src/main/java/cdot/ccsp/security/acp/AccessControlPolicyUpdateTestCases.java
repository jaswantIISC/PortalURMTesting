package cdot.ccsp.security.acp;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AccessControlPolicy;
import cdot.onem2m.resource.xsd.AccessControlRule;
import cdot.onem2m.resource.xsd.SetOfAcrs;

public class AccessControlPolicyUpdateTestCases {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");


	public OneM2MResponse updateLabelsACP(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
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
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
			oneM2MRequest.setResultContent(rcn);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateCreationACP(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setCreationTime(sdf.format(new Date()));
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateSelfPrivilegeACP(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = new AccessControlPolicy();
			SetOfAcrs setOfACRs = new SetOfAcrs();
			setOfACRs.setAccessControlRule(Arrays.asList(new AccessControlRule()));
			acp.setSelfPrivileges(setOfACRs);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
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
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updatePrivilege(SetOfAcrs privilege, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setPrivileges(privilege);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateSelfPrivilege(SetOfAcrs selfPrivilege, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicy acp = new AccessControlPolicy();
			acp.setSelfPrivileges(selfPrivilege);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

}
