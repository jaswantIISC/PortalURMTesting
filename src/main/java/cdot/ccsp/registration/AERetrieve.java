package cdot.ccsp.registration;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.xml.datatype.DatatypeConfigurationException;

import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;

// Will map the resource to the URL todos
@Path("/csebase")
public class AERetrieve {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	@Context
	private HttpServletRequest httpRequest;

	/* final String varName = "var"; */

	// For testing retrieve being executed from main
	public static void main(String[] args) {

		AERetrieve aeServlet = new AERetrieve();
		aeServlet.register();

	}

	public String register() {

		String value = "RESPONSE";
		OneM2MResponse oneM2MResponse = new OneM2MResponse();
		try {

			System.out.println("******************[CSEController][retrieve] Inside get request");
			System.out.println("******************[CSEController][retrieve] Calling Bean class for DB access");

			//String csePOA = "http://192.168.105.148:9080";
			String csePOA = "http://192.168.105.185:8090";
			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MRequest oneM2MRequest = new OneM2MRequest();

			AERetrieval(oneM2MRequest);
			
			oneM2MResponse = originatorActions.execute(oneM2MRequest, csePOA);


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "<tag>" + value + "</tag>";

	}

	
	
	private void AERetrieval(OneM2MRequest oneM2MRequest) throws DatatypeConfigurationException {

		UUID resourceID = UUID.randomUUID();
		Object idObject = resourceID;

		
		oneM2MRequest.setTo("C0010");// Any AE-ID 
		oneM2MRequest.setFrom("C0010");
		
		oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
		oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());

		oneM2MRequest.setOperation(M2MOperation.RETRIEVE.getOperationId());
		

	}
	

}