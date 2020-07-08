package cdot.ccsp.onem2mTester.utils;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.common.enums.SecurityParams.CertificateBasedParams;
import cdot.onem2m.common.enums.SecurityParams.PSKBasedParams;;

public class TestingParams {

	/*
	 * All the general parameters
	 */
	public static SecurityType SECURITY_TYPE = SecurityType.PSK;

	public static IUTPoA SECURITY_PROTOCOL = IUTPoA.HTTPSPSK;
	// Correct Date format for all date related parameters
	public static SimpleDateFormat CORRECT_SDF = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	// Incorrect data format for testing failure cases
	public static SimpleDateFormat INCORRECT_SDF = new SimpleDateFormat("ddMMdyyyy'T'HHmmss");

	public static Boolean DELETE_ALL_RESOURCES = true;

	public static String LOCATION_POLICY_TARGET_ID = "12";
	public static String LOCATION_POLICY_SERVER = "http://192.168.126.19:8080/";

	//The Resource Id of the <CSEBase> Resource
	public static String CSE_BASE_RESOURCE_ID = "R0";
	//The Resource Name of the <CSEBase> Resource
	public static String CSE_BASE_RESOURCE_NAME = "IN-CSE";
	//The CSE-ID of the <CSEBase> Resource
	public static String CSE_ID = "/CSE001";

	// The ACPIDS NOT HAVING RETRIEVE PERMISSION FOR GENERAL_ADN_AE_ID
//	public static String ACPIDs = "R08";

	//The IpAddress of the CSE
	public static String CSE_POA_IP_ADDRESS;
	//HTTP Port - This shall be blocked from outside access
	public static Integer HTTP_PORT;
	// HTTP Port - This shall be blocked from outside access
	public static Integer MQTT_PORT;
	//PSK Port - This shall be standard port where the PSK based Secure Association shall be established for HTTP Request
	public static Integer HTTPS_PSK_PORT;
	// PSK Port - This shall be standard port where the PSK based Secure Association
	// shall be established for HTTP Request
	public static Integer HTTPS_CERTIFICATE_PORT;
	// CoAP Port - This shall be blocked from outside access
	public static Integer COAP_PORT;
	// PSK Port - This shall be standard port where the PSK based Secure Association
	// shall be established for CoAP Request
	public static Integer COAPS_PSK_PORT;
	// CONTAINER MAX_BYTE_SIZE
	public static BigInteger MaxByteSize;
	// CONTAINER MAX_INSTANCE_AGE
	public static BigInteger MaxInstanceAge;
	// CONTAINER MAX_NUMBER_OF_INSTANCES
	public static BigInteger MaxNrOfInstances;

	// The CSE_POA of the CSE for listening to the MQTT request
	public static String CSE_POA_MQTT = null;
	// The CSE_POA of the CSE for listening to the HTTP TLS_PSK request
	public static String CSE_POA_HTTPS_PSK = null;
	// The CSE_POA of the CSE for listening to the HTTP TLS_PSK request
	public static String CSE_POA_HTTPS_CERTIFICATE = null;
	//The CSE_POA of the CSE for listening to the standard HTTP request
	public static String CSE_POA_HTTP = null;
	//The CSE_POA of the CSE for listening to the CoAP TLS_PSK request
	public static String CSE_POA_COAPS_PSK = null;
	//The CSE_POA of the CSE for listening to the standard CoAP request
	public static String CSE_POA_COAP = null;


	/*
	 * ADMIN-AE parameters and Secure Association Test Cases
	 */
	// The AE-ID of the Pre-Configured ADMIN-AE

	// ################### ALL CONFIG COPIED FROM
	// /home/chaitan/Desktop/temp/reallyTemp/ToolTesting/Psk08052020 path files
	// ##################################

	public static String ADMIN_AE_ID;
	public static String ADMIN_AE_RESOURCE_NAME;
	public static String ADMIN_AE_STRUCTURED_NAME = CSE_BASE_RESOURCE_NAME + "/" + ADMIN_AE_RESOURCE_NAME;
	//The PskId of the ADMIN-AE
	public static String ADMIN_AE_PSK_ID;
	//The Psk of the ADMIN-AE
	public static String ADMIN_AE_PSK;
	// CERTIFICATE PARAMS
	public static String ADMIN_AE_KS_NAME = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore010.jks";
	public static String ADMIN_AE_KS_PASS = "kstore010";
	public static String ADMIN_AE_KEY_PASS = "adn010";





	//A wrong Psk Id that is not configured through the SSM
	public static String INCORRECT_PSK_ID = "AAAA7F8E2F782D96396608DFD45A1111@cdot.in";

	//A wrong Psk that is not configured through the SSM and does not exists in the platform
	public static String INCORRECT_PSK = "BBBBDD1301E0AD8F8C4AA7021E0CB69AFC2AE75DA6C7EC344444155555666666";


	//A Psk Id that exists in the system but there has not been and will not be a AE Registration using these credentials
	//Thus, the credential could be used for testing some failure cases
	public static String UNUSED_PSK_ID = "1D81314ABBA33CA26A577B01F9F2A677@cdot.in";
	//A Psk that exists in the system corresponding to the above Psk Id
	public static String UNUSED_PSK = "85335AADEFFBE1E2C42C762BDD15CC586FB5D67AC81996CC097D6AF79E11BFA6";


	/*
	 * Parameters are for CSE Registration Test Cases
	 */
	//The MN-CSE-ID of the MN-CSE to be registered. All CSE have a Pre-Configured CSE-ID that is to be used for Registration
	public static String MN_CSE_ID = "/MNCSE1";
	//The Psk Id configured for the MN-CSE to be registered
	public static String MN_CSE_PSK_ID = "EEF03B2F121640035D79B482462C5C83@cdot.in";
	//The corresponding Psk of the above Psk Id
	public static String MN_CSE_PSK = "904EFF88615B7B2AE96CA3D0A916CAD10FD34286A1A8AAE405B0585E6E56D95A";	


	//An Incorrect value of the MN-CSE-ID that shall be used of testing the FromParameterFailTest()
	public static String INCORRECT_MN_CSE_ID = "/MNCSE101";

	//A Psk Id that exists in the system but there has not been and will not be a CSE Registration using these credentials
	//Thus, the credential could be used for testing some failure cases
	public static String MN_CSE_2_PSK_ID = "92298C63FB15720E5019C75D4899C73B@cdot.in";

	//The corresponding Psk of the above Psk Id 
	public static String MN_CSE_2_PSK = "7015D4129910E2CADC9E56719C9407B7230A75C819590B853A926184BAFDAED9";	

	/*
	 * Parameters are for AE Registration Test Cases
	 */

	//A correct App-Id that shall pass the App Rule Test (App Rule set as ADN-AE*)
	public static String CORRECT_ADN_AE_APP_ID = "ADN-AE-XYZ";

	//An incorrect App-Id that shall not pass the app Rule Test (App Rule set as ADN-AE*)
	public static String INCORRECT_ADN_AE_APP_ID = "ABC-XYZ";

	// In case of Pre-Configured AE-ID, provide the ID. otherwise leave an empty
	// String
	public static String GENERAL_ADN_AE_ID;
	public static String GENERAL_ADN_AE_NAME;
	// The Psk ID of the ADN-AE to the registered configured through SSM
	public static String GENERAL_ADN_AE_PSK_ID;
	// The Psk of the above Psk
	public static String GENERAL_ADN_AE_PSK;

	// CERTIFICATE PARAMS
	public static String GENERAL_ADN_AE_KS_NAME = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore011.jks";
	public static String GENERAL_ADN_AE_KS_PASS = "kstore011";
	public static String GENERAL_ADN_AE_KEY_PASS = "adn011";

	// SPARE AE 1
	public static String SPARE1_ADN_AE_ID;
	public static String SPARE1_ADN_AE_NAME;
	public static String SPARE1_ADN_AE_PSK_ID;
	public static String SPARE1_ADN_AE_PSK;

	// CERTIFICATE PARAMS
	public static String SPARE1_ADN_AE_KS_NAME = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore012.jks";
	public static String SPARE1_ADN_AE_KS_PASS = "kstore012";
	public static String SPARE1_ADN_AE_KEY_PASS = "adn012";

	// SPARE AE 2
	public static String SPARE2_ADN_AE_ID;
	public static String SPARE2_ADN_AE_NAME;
	public static String SPARE2_ADN_AE_PSK_ID;
	public static String SPARE2_ADN_AE_PSK;

	// CERTIFICATE PARAMS
	public static String SPARE2_ADN_AE_KS_NAME = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore013.jks";
	public static String SPARE2_ADN_AE_KS_PASS = "kstore013";
	public static String SPARE2_ADN_AE_KEY_PASS = "adn013";



	// AE CREDENTIALS, THIS AE-ID NOT ALLOWED TO REGISTERED AT THE TIME OF CREATION
	// OF NODE/AE DETAILS
	public static String TP_oneM2M_CSE_REG_CRE_004_ADN_AE_ID_NOT_ALLOWED; // SET THIS VALUE TO SOME RANDOM VALUE OTHER THEN PROPERTIES FILE VALUE
	public static String TP_oneM2M_CSE_REG_CRE_004_ADN_AE_NAME_NOT_ALLOWED;
	// The Psk ID of the ADN-AE to the registered configured through SSM
	public static String TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_ID_NOT_ALLOWED;
	// The Psk of the above Psk
	public static String TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_NOT_ALLOWED;

	// AE CREDENTIALS, THIS AE-ID ALLOWED TO REGISTERED AT THE TIME OF CREATION
	// OF NODE/AE DETAILS
	public static String TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED; // IN THIS TEST CASE, FROM VALUE IS ONLY 'S'
	public static String TP_oneM2M_CSE_REG_CRE_005_ADN_AE_NAME_ALLOWED = "";
	// The Psk ID of the ADN-AE to the registered configured through SSM
	public static String TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ID_ALLOWED;
	// The Psk of the above Psk
	public static String TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ALLOWED;


	// PRE CONFIGURED AE-ID WITH S
	public static String ADN_AE_ID_PRE_CONFIGURED_WITH_S;
	public static String ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_S;
	public static String ADN_AE_PSK_PRE_CONFIGURED_WITH_S;
	// CERTIFICATE PARAMS
	public static String ADN_AE_ID_PRE_CONFIGURED_KS_NAME_WITH_S = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore010.jks";
	public static String ADN_AE_ID_PRE_CONFIGURED_KS_PASS_WITH_S = "kstore010";
	public static String ADN_AE_ID_PRE_CONFIGURED_KEY_PASS_WITH_S = "adn010";

	// PRE CONFIGURED AE-ID WITH C
	public static String ADN_AE_ID_PRE_CONFIGURED_WITH_C;
	public static String ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_C;
	public static String ADN_AE_PSK_PRE_CONFIGURED_WITH_C;
	// CERTIFICATE PARAMS
	public static String ADN_AE_ID_PRE_CONFIGURED_KS_NAME_WITH_C = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore010.jks";
	public static String ADN_AE_ID_PRE_CONFIGURED_KS_PASS_WITH_C = "kstore010";
	public static String ADN_AE_ID_PRE_CONFIGURED_KEY_PASS_WITH_C = "adn010";


	// EMPTY PRE CONFIGURED AE-ID
	public static String ADN_AE_EMPTY_FROM;
	public static String ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED;
	public static String ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED;

	// CERTIFICATE PARAMS
	public static String ADN_AE_EMPTY_FROM_KS_NAME = "/media/chaitan/official/workspaces/workspace-oxygen/CCSP_oneM2M_Tester_02032020/keystore014.jks";
	public static String ADN_AE_EMPTY_FROM_KS_PASS = "kstore014";
	public static String ADN_AE_EMPTY_FROM_KEY_PASS = "adn014";

	// REMOTECSE CONFIGS
	public static String REMOTE_CSE_KPSA;
	public static String REMOTE_CSE_KPSA_ID;
	public static String REMOTE_CSE_ID;

	//A correct App Id that shall pass the App Rule Test (App Rule set as LMC-AE*)
	// public static String CORRECT_LMC_AE_APP_ID = "LMC-AE-XYZ";

	/*
	 * //In case of Pre-Configured AE-ID, provide the ID. otherwise leave an empty
	 * String public static String LMC_AE_ID = "S0001";
	 * 
	 * //The Psk ID of the LMC-AE to the registered configured through SSM public
	 * static String LMC_AE_PSK_ID = "F912E631D631C1E55AAA2E9EB23C8041@cdot.in";
	 * 
	 * //The Psk of the above Psk public static String LMC_AE_PSK =
	 * "5E9FF9804A2997828C6B33B2D81AF6D617C0E628BDBAC19421F0BB7766030181";
	 */

	/*
	 * Discovery Parameters - for testing Discovery based Success and Failure Test Cases
	 */

	static String csePOA = null;
	public static String getCSEPoA() {


		if (SECURITY_PROTOCOL == IUTPoA.HTTP) {
			csePOA = CSE_POA_HTTP;
		} else if (SECURITY_PROTOCOL == IUTPoA.HTTPSPSK) {
			csePOA = CSE_POA_HTTPS_PSK;
		} else if (SECURITY_PROTOCOL == IUTPoA.HTTPSCERTIFICATE) {
			csePOA = CSE_POA_HTTPS_CERTIFICATE;
		} else if (SECURITY_PROTOCOL == IUTPoA.COAP) {
			csePOA = CSE_POA_COAP;
		} else if (SECURITY_PROTOCOL == IUTPoA.COAPSPSK) {
			csePOA = CSE_POA_COAPS_PSK;
		} else if (SECURITY_PROTOCOL == IUTPoA.MQTT) {
			csePOA = CSE_POA_MQTT;
		} else {
			;
		}

		return csePOA;
	}

	public static SecurityParams getSecurityParams(String AEID) {

		SecurityParams securityParams = new SecurityParams();

		if (SECURITY_TYPE == SecurityType.CERTIFICATE) {

			CertificateBasedParams certificateBasedParams = securityParams.new CertificateBasedParams();
			
			
			if(AEID.equalsIgnoreCase(ADMIN_AE_ID)) {
				
				certificateBasedParams.setKsName(ADMIN_AE_KS_NAME);
				certificateBasedParams.setKsPass(ADMIN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(ADMIN_AE_KEY_PASS);
				
			}else if(AEID.equalsIgnoreCase(GENERAL_ADN_AE_ID)) {
				
				certificateBasedParams.setKsName(GENERAL_ADN_AE_KS_NAME);
				certificateBasedParams.setKsPass(GENERAL_ADN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(GENERAL_ADN_AE_KEY_PASS);
				
			}else if(AEID.equalsIgnoreCase(ADN_AE_EMPTY_FROM)) {
				
				certificateBasedParams.setKsName(ADN_AE_EMPTY_FROM_KS_NAME);
				certificateBasedParams.setKsPass(ADN_AE_EMPTY_FROM_KS_PASS);
				certificateBasedParams.setClientKeyPass(ADN_AE_EMPTY_FROM_KEY_PASS);
				
			} else if(AEID.equalsIgnoreCase(SPARE1_ADN_AE_ID)) {
				
				certificateBasedParams.setKsName(SPARE1_ADN_AE_KS_NAME);
				certificateBasedParams.setKsPass(SPARE1_ADN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(SPARE1_ADN_AE_KEY_PASS);
				
			}else if(AEID.equalsIgnoreCase(SPARE2_ADN_AE_ID)) {
				
				certificateBasedParams.setKsName(SPARE2_ADN_AE_KS_NAME);
				certificateBasedParams.setKsPass(SPARE2_ADN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(SPARE2_ADN_AE_KEY_PASS);
				
			} else if(AEID.equalsIgnoreCase(TP_oneM2M_CSE_REG_CRE_004_ADN_AE_ID_NOT_ALLOWED)) {
				
				certificateBasedParams.setKsName(SPARE2_ADN_AE_KS_NAME);
				certificateBasedParams.setKsPass(SPARE2_ADN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(SPARE2_ADN_AE_KEY_PASS);
				
			} else if(AEID.equalsIgnoreCase(TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED)) {
				
				certificateBasedParams.setKsName(SPARE2_ADN_AE_KS_NAME);
				certificateBasedParams.setKsPass(SPARE2_ADN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(SPARE2_ADN_AE_KEY_PASS);
				
			}	else if(AEID.equalsIgnoreCase(REMOTE_CSE_ID)) {
				
				certificateBasedParams.setKsName(SPARE2_ADN_AE_KS_NAME);
				certificateBasedParams.setKsPass(SPARE2_ADN_AE_KS_PASS);
				certificateBasedParams.setClientKeyPass(SPARE2_ADN_AE_KEY_PASS);
				
			}  else if(AEID.equalsIgnoreCase(ADN_AE_ID_PRE_CONFIGURED_WITH_S)) {
				
				certificateBasedParams.setKsName(ADN_AE_ID_PRE_CONFIGURED_KS_NAME_WITH_S);
				certificateBasedParams.setKsPass(ADN_AE_ID_PRE_CONFIGURED_KS_PASS_WITH_S);
				certificateBasedParams.setClientKeyPass(ADN_AE_ID_PRE_CONFIGURED_KEY_PASS_WITH_S);
				
			}else if(AEID.equalsIgnoreCase(ADN_AE_ID_PRE_CONFIGURED_WITH_C)) {
				
				
				certificateBasedParams.setKsName(ADN_AE_ID_PRE_CONFIGURED_KS_NAME_WITH_C);
				certificateBasedParams.setKsPass(ADN_AE_ID_PRE_CONFIGURED_KS_PASS_WITH_C);
				certificateBasedParams.setClientKeyPass(ADN_AE_ID_PRE_CONFIGURED_KEY_PASS_WITH_C);	
			}

		securityParams.setCertificateBasedParams(certificateBasedParams);
			


		} else if (SECURITY_TYPE == SecurityType.PSK) {

			PSKBasedParams pskBasedParams = securityParams.new PSKBasedParams();

			
			if(AEID.equalsIgnoreCase(ADMIN_AE_ID)) {
				
				pskBasedParams.setKpmID(ADMIN_AE_PSK_ID);
				pskBasedParams.setKpmStr(ADMIN_AE_PSK);
				
			} else if(AEID.equalsIgnoreCase(GENERAL_ADN_AE_ID)) {
				
				pskBasedParams.setKpmID(GENERAL_ADN_AE_PSK_ID);
				pskBasedParams.setKpmStr(GENERAL_ADN_AE_PSK);
				
			} else if(AEID.equalsIgnoreCase(ADN_AE_EMPTY_FROM)) {
				
				pskBasedParams.setKpmID(ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED);
				pskBasedParams.setKpmStr(ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED);
				
			} else if(AEID.equalsIgnoreCase(SPARE1_ADN_AE_ID)) {
				
				pskBasedParams.setKpmID(SPARE1_ADN_AE_PSK_ID);
				pskBasedParams.setKpmStr(SPARE1_ADN_AE_PSK);
				
			} else if(AEID.equalsIgnoreCase(SPARE2_ADN_AE_ID)) {
				
				pskBasedParams.setKpmID(SPARE2_ADN_AE_PSK_ID);
				pskBasedParams.setKpmStr(SPARE2_ADN_AE_PSK);
				
			} else if(AEID.equalsIgnoreCase(TP_oneM2M_CSE_REG_CRE_004_ADN_AE_ID_NOT_ALLOWED)) {
				
				pskBasedParams.setKpmID(TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_ID_NOT_ALLOWED);
				pskBasedParams.setKpmStr(TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_NOT_ALLOWED);
				
			} else if(AEID.equalsIgnoreCase(TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED)) {
				
				pskBasedParams.setKpmID(TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ID_ALLOWED);
				pskBasedParams.setKpmStr(TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ALLOWED);
				
			} else if(AEID.equalsIgnoreCase(REMOTE_CSE_ID)) {
				
				pskBasedParams.setKpmID(REMOTE_CSE_KPSA_ID);
				pskBasedParams.setKpmStr(REMOTE_CSE_KPSA);
				
			} else if(AEID.equalsIgnoreCase(ADN_AE_ID_PRE_CONFIGURED_WITH_S)) {
				
				pskBasedParams.setKpmID(ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_S);
				pskBasedParams.setKpmStr(ADN_AE_PSK_PRE_CONFIGURED_WITH_S);
				
			} else if(AEID.equalsIgnoreCase(ADN_AE_ID_PRE_CONFIGURED_WITH_C)) {
				
				pskBasedParams.setKpmID(ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_C);
				pskBasedParams.setKpmStr(ADN_AE_PSK_PRE_CONFIGURED_WITH_C);
			}
		
			securityParams.setPskBasedParams(pskBasedParams);


		} else if (SECURITY_TYPE == SecurityType.NONE) {

			securityParams = null; // No security

		}

		return securityParams;
	}




}