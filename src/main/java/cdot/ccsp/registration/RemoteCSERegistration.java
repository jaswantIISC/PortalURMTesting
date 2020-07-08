package cdot.ccsp.registration;

import java.util.Arrays;

import javax.xml.datatype.DatatypeConfigurationException;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.RemoteCSE;

public class RemoteCSERegistration {
	
	OriginatorActions originatorActions = new OriginatorActionsBean();


	public void retrieveRemoteCse(OneM2MRequest oneM2MRequest) throws DatatypeConfigurationException {
		
		oneM2MRequest.setTo("R8"); 
		oneM2MRequest.setFrom("/MNCSE1");
		
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.RETRIEVE.getOperationId());
		oneM2MRequest.setResourceType(M2MSimpleResources.REMOTECSE.getM2MResourceTypes());				
	}	

	private void deleteRemoteCse(OneM2MRequest oneM2MRequest) throws DatatypeConfigurationException { 
		
		oneM2MRequest.setTo("R7"); 
		oneM2MRequest.setFrom("/MNCSE1");
		
//		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
//		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
		oneM2MRequest.setResourceType(M2MSimpleResources.REMOTECSE.getM2MResourceTypes());				
	}
	
	public OneM2MResponse setRemoteCse(String to, String from, SecurityParams securityParams) {


		RemoteCSE cseBase = populateMandatoryAttr();
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.REMOTECSE.getM2MResourceTypes(), cseBase);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	private RemoteCSE populateMandatoryAttr() {

		RemoteCSE remoteCSE = new RemoteCSE();
		remoteCSE.setCSEBase("R0");
		remoteCSE.setCSEID("/MNCSE1");
		remoteCSE.setRequestReachability(true);
		remoteCSE.setSupportedReleaseVersions(Arrays.asList("2", "2a", "3"));
		return remoteCSE;
	}

}
