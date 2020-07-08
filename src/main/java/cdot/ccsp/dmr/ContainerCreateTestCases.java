package cdot.ccsp.dmr;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.security.acp.AccessControlOperations;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MResponseType;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AccessControlPolicy;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.ResponseTypeInfo;

public class ContainerCreateTestCases {




	public OneM2MResponse createWithAnyAttribute(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
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
			Container cont = containerBasicCreate(null, null, null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MRequest.setResultContent(resultContent);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}


	public OneM2MResponse createWith_MNI_MBS_MIA(BigInteger mni, BigInteger mbs, BigInteger mia, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setMaxNrOfInstances(mni);
			cont.setMaxByteSize(mbs);
			cont.setMaxInstanceAge(mia);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
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
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setResourceName(resourceName);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithACPIDS(List<String> acpids, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setAccessControlPolicyIDs(acpids);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
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
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setExpirationTime(expirationTime);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithOntologyRef(String ontologyRef, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setOntologyRef(ontologyRef);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
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
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setCreator(from);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithMaxNumberOfInstance(BigInteger mni, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setMaxNrOfInstances(mni);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
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
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setMaxInstanceAge(mia);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
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
			Container cont = containerBasicCreate(null, null, null, null);
			cont.setMaxByteSize(mbs);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithFixedLabel(List<String> labels, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = new Container();
			cont.setLabels(labels);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
//			oneM2MResponse = originatorActions.execute(oneM2MRequest,  TestingParams.getCSEPoA(), securityParams);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithAnyAttributeNonBlockingSynch(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			ResponseTypeInfo resInfo = new ResponseTypeInfo();
			resInfo.setResponseTypeValue(M2MResponseType.NON_BLOCKING_REQUEST_SYNCH.val());
			oneM2MRequest.setResponseType(resInfo);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse containerAttributesValueUnderSystemLimit(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(TestingParams.MaxNrOfInstances.subtract(BigInteger.valueOf(1)), TestingParams.MaxByteSize.subtract(BigInteger.valueOf(1)), TestingParams.MaxInstanceAge.subtract(BigInteger.valueOf(1)), null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}


	public OneM2MResponse containerMaxNrOfInstanceAboveLimit(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(TestingParams.MaxNrOfInstances.add(BigInteger.valueOf(1)), null, null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse containerMaxByteSizeAboveLimit(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, TestingParams.MaxByteSize.add(BigInteger.valueOf(1)), null, null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse containerMaxInstanceAgeAboveLimit(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(null, null, TestingParams.MaxInstanceAge.add(BigInteger.valueOf(1)), null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse containerAttributesValueAboveSystemLimit(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Container cont = containerBasicCreate(TestingParams.MaxNrOfInstances.add(BigInteger.valueOf(1)), TestingParams.MaxByteSize.add(BigInteger.valueOf(1)), TestingParams.MaxInstanceAge.add(BigInteger.valueOf(1)), null);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse containerWithExternalACPIDs(String to, String from, SecurityParams securityParams, List<String> acpIds) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {

			Container cont = containerBasicCreate(null, null, null, acpIds);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse containerWithACPIDs(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			AccessControlPolicyCreateTestCases  acpCreate = new AccessControlPolicyCreateTestCases();
			AccessControlPolicy acp = acpCreate.acpBasicCreateRequest(Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue(), Arrays.asList("ALL"), AccessControlOperations.CR_RE_UP_DE_NO_DI.getValue());
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.ACCESSCONTROLPOLICY.getM2MResourceTypes(), acp);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
			Container cont = containerBasicCreate(null, null, null, Arrays.asList(((AccessControlPolicy) oneM2MRequest.getPrimitiveContent().getAny()).getResourceID()));
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.CONTAINER.getM2MResourceTypes(), cont);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	private Container containerBasicCreate(BigInteger maxNrOfInstances, BigInteger maxByteSize, BigInteger maxInstanceAge, List<String> acpIds) {
		Container container = new Container();

		if(maxNrOfInstances != null) {
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
