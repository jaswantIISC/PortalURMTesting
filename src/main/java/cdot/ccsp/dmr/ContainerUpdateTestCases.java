package cdot.ccsp.dmr;

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
import cdot.onem2m.resource.xsd.Container;

public class ContainerUpdateTestCases {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

	public OneM2MResponse updateContainerOntologyRef(String ontologyRef, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setOntologyRef(ontologyRef);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateLocationID(String locationId, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setLocationID(locationId);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerAccessControlPolicyIds(List<String> acpIds, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = new Container();
			cont.setAccessControlPolicyIDs(acpIds);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerLabel(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			// CONTAINER CREATE
			Container cont = new Container();
			cont.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerLabelOnlyUpdate(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			// CONTAINER UPADTE START
			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerCreationTime(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			// CONTAINER UPADTE START
			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setCreationTime(sdf.format(new Date()));
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerExpirationTime(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			// CONTAINER UPADTE START
			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setExpirationTime(null);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerExpirationTime(String expirationTime, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			// CONTAINER UPADTE START
			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerMaxNrOfInstances(BigInteger maxNrOfInstances, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setMaxNrOfInstances(maxNrOfInstances);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerMaxByteSize(BigInteger maxByteSize, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setMaxByteSize(maxByteSize);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateContainerMaxInstanceAge(BigInteger maxInstanceAge, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setMaxInstanceAge(maxInstanceAge);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse updateLabelWithRCNValue(BigInteger rcn, List<String> label, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container contForUpdate = containerBasicCreate(null, null, null, null);
			contForUpdate.setLabels(label);
			oneM2MRequest = ComposeRequest.oneM2MRequestUpdate(to, from, contForUpdate);
			oneM2MRequest.setResultContent(rcn);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			// CONTAINER UPADTE ENDS

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

//	private OneM2MResponse containerWithACPIDs(String to, String from) {
//
//		OneM2MRequest oneM2MRequest = null;
//		OneM2MResponse oneM2MResponse = null;
//		OriginatorActions originatorActions = new OriginatorActionsBean();
//
//		try {
//			AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
//			AccessControlPolicy acp = acpCreate.acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
//			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
//			oneM2MResponse = originatorActions.executeWithoutSecureAssociation(oneM2MRequest, TestingParams.getCSEPoA());
//			Container cont = containerBasicCreate(null, null, null, Arrays.asList(((AccessControlPolicy) oneM2MRequest.getPrimitiveContent().getAny()).getResourceID()));
//			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
//			oneM2MResponse = originatorActions.executeWithoutSecureAssociation(oneM2MRequest, TestingParams.getCSEPoA());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return oneM2MResponse;
//
//	}

	private Container containerBasicCreate(BigInteger maxNrOfInstances, BigInteger maxByteSize, BigInteger maxInstanceAge, List<String> acpIds) {
		Container container = new Container();

		if (maxNrOfInstances != null) {
			System.out.println(" ################################### maxNrOfInstances : " + maxNrOfInstances);
			container.setMaxNrOfInstances(maxNrOfInstances);
		}

		if (maxByteSize != null) {
			System.out.println(" ###################################  maxByteSize : " + maxByteSize);
			container.setMaxByteSize(maxByteSize);
		}

		if (maxInstanceAge != null) {
			System.out.println("  ################################### maxInstanceAge : " + maxInstanceAge);
			container.setMaxInstanceAge(maxInstanceAge);
		}

		if (acpIds != null && !acpIds.isEmpty()) {
			System.out.println("  ################################### acpIds : " + acpIds);
			container.setAccessControlPolicyIDs(acpIds);
		}

		container.setLabels(Arrays.asList("CONT-" + Math.random()));

		return container;

	}

}
