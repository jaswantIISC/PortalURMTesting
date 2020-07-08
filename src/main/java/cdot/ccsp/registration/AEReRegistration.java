package cdot.ccsp.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
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
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.PrimitiveContent;

// Will map the resource to the URL todos
@Path("/csebase")
public class AEReRegistration {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	@Context
	private HttpServletRequest httpRequest;
	OriginatorActions originatorActions = new OriginatorActionsBean();

	/* final String varName = "var"; */

	// For testing retrieve being executed from main
	public static void main(String[] args) {

		AEReRegistration aeServlet = new AEReRegistration();
		aeServlet.register();

	}

	public String register() {

		String value = "RESPONSE";
		OneM2MResponse oneM2MResponse = new OneM2MResponse();
		try {

			System.out.println("******************[CSEController][retrieve] Inside get request");
			System.out.println("******************[CSEController][retrieve] Calling Bean class for DB access");

			//String csePOA = "http://192.168.105.148:9080";
			String csePOA = "https://192.168.105.185:8080";
			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MRequest oneM2MRequest = new OneM2MRequest();

			//AERegister(oneM2MRequest);
			AEDeRegister(oneM2MRequest);
			//AEReRegister(oneM2MRequest);

			oneM2MResponse = originatorActions.execute(oneM2MRequest, csePOA,"725C30EB8C2C4E6FEE9B23789A26DDDA@cdot.in","A43DAF8BA0A5606BF3321925EAEC5F05E43A6710F220A73BA23627DC48CDA375");


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "<tag>" + value + "</tag>";

	}

	
	public OneM2MResponse aeDeregister(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestDelete(to, from);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}
	
	private void AERegister(OneM2MRequest oneM2MRequest) throws DatatypeConfigurationException {

		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;

		
		oneM2MRequest.setTo("R0"); 
		oneM2MRequest.setFrom("C0010");
		
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.CREATE.getOperationId());
		oneM2MRequest.setResourceType(M2MSimpleResources.AE.getM2MResourceTypes());
		
		AE cont = new AE();
		cont.setAppID("TestApp");
		cont.setRequestReachability(true);
		List<String> SRV = new ArrayList<String>();
		SRV.add(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		cont.setSupportedReleaseVersions(SRV);
		
		PrimitiveContent primContent = new PrimitiveContent();
		primContent.setAny(cont);	
		oneM2MRequest.setPrimitiveContent(primContent);

	}
	
	private void AEDeRegister(OneM2MRequest oneM2MRequest) throws DatatypeConfigurationException {

		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;

		
		oneM2MRequest.setTo("S0010"); 
		oneM2MRequest.setFrom("S0010");
		
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
		

	}
	
	private void AEReRegister(OneM2MRequest oneM2MRequest) throws DatatypeConfigurationException {

		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;

		
		oneM2MRequest.setTo("R0"); 
		oneM2MRequest.setFrom("C0010");
		
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.CREATE.getOperationId());
		oneM2MRequest.setResourceType(M2MSimpleResources.AE.getM2MResourceTypes());
		
		AE cont = new AE();
		cont.setAppID("TestApp");
		cont.setRequestReachability(true);
		List<String> SRV = new ArrayList<String>();
		SRV.add(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		cont.setSupportedReleaseVersions(SRV);
		
		PrimitiveContent primContent = new PrimitiveContent();
		primContent.setAny(cont);	
		oneM2MRequest.setPrimitiveContent(primContent);

	}



}