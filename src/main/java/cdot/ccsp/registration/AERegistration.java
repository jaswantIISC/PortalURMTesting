package cdot.ccsp.registration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.PermittedMediaTypes;

public class AERegistration {

	ComposeRequest composeRequest = new ComposeRequest();
	OriginatorActions originatorActions = new OriginatorActionsBean();

	public static void main(String[] args) {

		AERegistration registration = new AERegistration();
		registration.register();

	}

	public String register() {

		String value = "RESPONSE";
		try {

			System.out.println("******************[CSEController][retrieve] Inside get request");
			System.out.println("******************[CSEController][retrieve] Calling Bean class for DB access");

			String to = TestingParams.CSE_BASE_RESOURCE_ID;
			String from = "";
			String pskId = "";
			String psk = "";
			OneM2MResponse oneM2MResponse = AERegisterWithEmptyFrom(to, from, TestingParams.getSecurityParams(TestingParams.ADN_AE_EMPTY_FROM));
			// AERegisterWithC();
			// AERegisterWithS();
			// AERegisterWithPreProvisionedAEID();
			// AERegisterWithoutAppID();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "<tag>" + value + "</tag>";

	}

	public OneM2MResponse AERegisterWithAE(AE ae, String to, String from, SecurityParams securityParams) {


		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithACPIds(List<String> acpids, String to, String from, SecurityParams securityParams) {

		AE ae = populateMandatoryAttr();
		ae.setAccessControlPolicyIDs(acpids);
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithEmptyFrom(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), populateMandatoryAttr());
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithC(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), populateMandatoryAttr());
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	// Test this case for both IN-CSE and MN-CSE
	// In case registrar is MN-CSE, it communicates with IN-CSE to get a unique
	// SP-Domain AE-ID
	public OneM2MResponse AERegisterWithS(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), populateMandatoryAttr());
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithPreProvisionedAEID(String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), populateMandatoryAttr());
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithoutAppID(String to, String from, SecurityParams securityParams) {

		AE ae = populateMandatoryAttr();
		ae.setAppID(null);
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithAppName(String to, String from, SecurityParams securityParams) {

		AE ae = populateMandatoryAttr();
		ae.setAppName("TestApp");

		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse AERegisterWithPOA(String to, String from, SecurityParams securityParams) {

		AE ae = populateMandatoryAttr();
		List<String> poa = new ArrayList<>();
		poa.add("http://www.xxx.yyy.zzz:abcd");
		ae.setPointOfAccess(poa);

		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.AE.getM2MResourceTypes(), ae);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	private AE populateMandatoryAttr() {
		AE ae = new AE();

//		ae.setResourceName(TestingParams.GENERAL_ADN_AE_NAME);
		ae.setRequestReachability(true);
		ae.setAppID("ra1.cdot.in.CDOTApps-xyzz");
		ae.setAppName("TestAppName");
		ae.setLabels(Arrays.asList("TemporaryLabel"));
		ae.setPointOfAccess(Arrays.asList("http://IP:PORT"));
		ae.setNodeLink("KuchTohGadbadHaiDaya");
		// ae.setContentSerialization(Arrays.asList(PermittedMediaTypes.APPLICATION_XML,
		// PermittedMediaTypes.APPLICATION_JSON));
		List<String> supportedRelVer = new ArrayList<String>();
		supportedRelVer.add(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
		supportedRelVer.add(M2MReleaseVersion.RELEASE_3.getReleaseVersion());
		ae.setSupportedReleaseVersions(supportedRelVer);
		ae.setContentSerialization(Arrays.asList(PermittedMediaTypes.APPLICATION_JSON, PermittedMediaTypes.APPLICATION_XML));

		return ae;
	}
}