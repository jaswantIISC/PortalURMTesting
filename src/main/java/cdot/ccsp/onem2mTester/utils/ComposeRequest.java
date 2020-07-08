package cdot.ccsp.onem2mTester.utils;

import java.math.BigInteger;
import java.util.UUID;

import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.resource.xsd.PrimitiveContent;
import cdot.onem2m.resource.xsd.Resource;

public class ComposeRequest {

	public static OneM2MRequest oneM2MRequestCreate(String to,String from,BigInteger resourceType, Resource resource) {
		
		OneM2MRequest oneM2MRequest = new OneM2MRequest(); 
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		oneM2MRequest.setTo(to);
		oneM2MRequest.setFrom(from);
		oneM2MRequest.setOperation(M2MOperation.CREATE.getOperationId());
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setResourceType(resourceType);
		UUID requestID = UUID.randomUUID();
		Object idObject = requestID;
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
		PrimitiveContent pc = new PrimitiveContent();
		pc.setAny(resource);
		oneM2MRequest.setPrimitiveContent( pc );
		return  oneM2MRequest;
	}

	public static  OneM2MRequest oneM2MRequestRetrieve(String to,String from) {
		OneM2MRequest oneM2MRequest = new OneM2MRequest(); 
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		oneM2MRequest.setTo(to);
		oneM2MRequest.setFrom(from);
		oneM2MRequest.setOperation(M2MOperation.RETRIEVE.getOperationId());
		UUID requestID = UUID.randomUUID();
		Object idObject = requestID;
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
		return  oneM2MRequest;
	}


	public static OneM2MRequest oneM2MRequestUpdate(String to,String from, Resource resource) {
		OneM2MRequest oneM2MRequest = new OneM2MRequest(); 
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		oneM2MRequest.setTo(to);
		oneM2MRequest.setFrom(from);
		oneM2MRequest.setOperation(M2MOperation.UPDATE.getOperationId());
		UUID requestID = UUID.randomUUID();
		Object idObject = requestID;
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
		PrimitiveContent pc = new PrimitiveContent();
		pc.setAny(resource);
		oneM2MRequest.setPrimitiveContent( pc );
		return  oneM2MRequest;
	}

	public static OneM2MRequest oneM2MRequestDelete(String to,String from) {
		OneM2MRequest oneM2MRequest = new OneM2MRequest(); 
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		oneM2MRequest.setTo(to);
		oneM2MRequest.setFrom(from);
		oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
		UUID requestID = UUID.randomUUID();
		Object idObject = requestID;
		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
		return  oneM2MRequest;
	}

}
