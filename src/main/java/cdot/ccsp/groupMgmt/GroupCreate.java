package cdot.ccsp.groupMgmt;


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
import cdot.onem2m.resource.xsd.Group;

/**
 * @author neeta
 *
 */
public class GroupCreate {
	
	public enum ConsistencyStrategy{
		ABANDON_MEMBER(BigInteger.valueOf(1)),
		ABANDON_GROUP(BigInteger.valueOf(2)),
		SET_MIXED(BigInteger.valueOf(3));

		private BigInteger consistencyStrategy;

		private ConsistencyStrategy(BigInteger consistencyStrategy){
			this.consistencyStrategy = consistencyStrategy;
		}

		public BigInteger getConsistencyStrategy() {
			return consistencyStrategy;
		}

		public void setConsistencyStrategy(BigInteger consistencyStrategy) {
			this.consistencyStrategy = consistencyStrategy;
		}

	}

	public OneM2MResponse createWithResultContent(BigInteger resultContent, List<String> memberIds, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group group = new Group();
			group.setMemberIDs(memberIds);
			group.setMaxNrOfMembers(BigInteger.valueOf(20));
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), group);
			oneM2MRequest.setResultContent(resultContent);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithGroup(Group grp, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), grp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMemberIds(List<String> memberIds, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Group group = new Group();
			group.setMemberIDs(memberIds);
			group.setMaxNrOfMembers(BigInteger.valueOf(20));
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), group);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	OriginatorActions originatorActions = new OriginatorActionsBean();
	

	public OneM2MResponse CreateGroupWithMemberIdsAndACPIds(String to, String from, SecurityParams securityParams, List<String> memberIds, List<String> acpIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroup(memberIds, acpIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	

	public OneM2MResponse CE_GMG_00001_00000(String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroup(memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}
	
	public OneM2MResponse createGroupWithLabel(List<String> label, String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupWithLabel(label, memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}

	public OneM2MResponse createGroupWithResourceName(String resourceName, String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupWithResourceName(resourceName, memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}

	public OneM2MResponse CE_GMG_00001_00001(String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupLocalMembers(memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}
	
	public OneM2MResponse CE_GMG_00001_00002(String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupRemoteMembers(memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}

	public OneM2MResponse CE_GMG_00002_00002(String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupConsistencyStrategyAM(memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}
	
	public OneM2MResponse CE_GMG_00002_00003(String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupConsistencyStrategyAG(memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}
	
	public OneM2MResponse CE_GMG_00002_00004(String to, String from, SecurityParams securityParams, List<String> memberIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.GROUP.getM2MResourceTypes(), populateGroupConsistencyStrategySM(memberIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}
	
	//---------------------------------************************************---------------------------------//

	private Group populateGroup(List<String> memberIds, List<String> acpIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		group.setAccessControlPolicyIDs(acpIds);
		return GroupWithMandatoryParams(group);
	}
	
	private Group populateGroup(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithMandatoryParams(group);
	}
	
	private Group populateGroupWithLabel(List<String> label, List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		group.setLabels(label);
		return GroupWithMandatoryParams(group);
	}

	private Group populateGroupWithResourceName(String resourceName, List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		group.setResourceName(resourceName);
		return GroupWithMandatoryParams(group);
	}

	private Group populateGroupMandatoryParameters(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithMandatoryParams(group);

	}
	
	private Group populateGroupLocalMembers(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithLocalMembers(group);
	}
	
	private Group populateGroupRemoteMembers(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithLocalMembers(group);
	}
	
	private Group populateGroupConsistencyStrategyAM(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithConsistentcyStrategyAbandonMember(group);
	}
	
	private Group populateGroupConsistencyStrategyAG(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithConsistentcyStrategyAbandonGroup(group);
	}
	
	private Group populateGroupConsistencyStrategySM(List<String> memberIds) {
		Group group = new Group();
		group.setMemberIDs(memberIds);
		return GroupWithConsistentcyStrategySetMixed(group);
	}
	
	//---------------------------------************************************---------------------------------//
	
	private Group GroupWithMandatoryParams(Group group){
//		List<String> members = new ArrayList<String>();
//		group.setMemberIDs(members);
		group.setMaxNrOfMembers(BigInteger.valueOf(20));
		return group;
	}
	
	private Group GroupWithLocalMembers(Group group){
		GroupWithMandatoryParams(group);
//		List<String> members = group.getMemberIDs();
//		//Add local members
		
		return group;
	}
	
	private Group GroupWithRemoteMembers(Group group){
		GroupWithMandatoryParams(group);
//		List<String> members = group.getMemberIDs();
//		//Add remote members
		
		return group;
	}
	
	private Group GroupWithConsistentcyStrategyAbandonMember(Group group){
		GroupWithMandatoryParams(group);
		group.setConsistencyStrategy(ConsistencyStrategy.ABANDON_MEMBER.consistencyStrategy);
		
		return group;
	}
	
	private Group GroupWithConsistentcyStrategyAbandonGroup(Group group){
		GroupWithMandatoryParams(group);
		group.setConsistencyStrategy(ConsistencyStrategy.ABANDON_GROUP.consistencyStrategy);
	
		return group;
	}
	
	private Group GroupWithConsistentcyStrategySetMixed(Group group){
		GroupWithMandatoryParams(group);
		group.setConsistencyStrategy(ConsistencyStrategy.SET_MIXED.consistencyStrategy);
	
		return group;
	}
}
