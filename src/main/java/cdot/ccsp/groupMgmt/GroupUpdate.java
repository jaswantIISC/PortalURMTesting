package cdot.ccsp.groupMgmt;

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
import cdot.onem2m.resource.xsd.Group;

public class GroupUpdate {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

	public OneM2MResponse updateLabelsGRP(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
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
			Group grp = new Group();
			grp.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MRequest.setResultContent(rcn);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
	public OneM2MResponse updateAccessControlPolicyIdsGRP(List<String> acpi, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setAccessControlPolicyIDs(acpi);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMemberAccessControlPolicyIdsGRP(List<String> macpi, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setMembersAccessControlPolicyIDs(macpi);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateCreationTimeGRP(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setCreationTime(sdf.format(new Date()));
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateGroupNameGRP(String groupName, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setGroupName(groupName);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMaxNumberOfInstancesGRP(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setMaxNrOfMembers(null);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateExpirtationTime(String expirationTime, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMaxNumberOfMembers(BigInteger maxNrOfMembers, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setMaxNrOfMembers(maxNrOfMembers);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateMembersIds(List<String> mids, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group grp = new Group();
			grp.setMemberIDs(mids);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
}