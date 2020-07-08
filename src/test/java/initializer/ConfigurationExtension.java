package initializer;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import cdot.ccsp.onem2mTester.utils.TestingParams;

public class ConfigurationExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

	private static boolean started = false;

	@Override
	public void beforeAll(ExtensionContext context) {

		if (!started) {

			started = true;
			// Your "before all tests" startup logic goes here
			// The following line registers a callback hook when the root test context is
			// shut down
			context.getRoot().getStore(GLOBAL).put("any unique name", this);
			System.out.println(" ################# TESTING BEFOREALL ###############################");
			System.out.println(" ################# LOOK I'M EXECUTING OPENING ONCE ONLY ###############################");
			String filesMapping = System.getProperty("user.dir") + "/utils/securityCredentials/credentialFileMapping.txt";
			System.out.println("PATH OF UTILS: " + filesMapping);
			Properties properties = new Properties();
			Enumeration<String> enums = null;

			Properties indProperties = new Properties();
			// Enumeration<String> indEnums = null;

			String individualFiles = null;

			try {
				properties.load(new FileInputStream(new File(filesMapping)));
				enums = (Enumeration<String>) properties.propertyNames();

				try {
					final String ADMIN_AE = properties.getProperty("ADMIN_AE");
					if (ADMIN_AE == null) {
						System.err.println("ADMIN_AE CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("ADN_AE_ID_CONFIGURED_WITH_C CREDENTIAL FILE VALUE IS NOT NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + ADMIN_AE;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();
						TestingParams.ADMIN_AE_ID = indProperties.getProperty("AE-ID");
						TestingParams.ADMIN_AE_PSK_ID = indProperties.getProperty("kpsaId");
						TestingParams.ADMIN_AE_PSK = indProperties.getProperty("kpsa");

						System.out.println("ADMIN_AE_ID : " + TestingParams.ADMIN_AE_ID + " ADMIN_AE_PSK_ID :  " + TestingParams.ADMIN_AE_PSK_ID + "  ADMIN_AE_PSK : " + TestingParams.ADMIN_AE_PSK);

					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String ADN_AE_EMPTY_FROM = properties.getProperty("ADN_AE_EMPTY_FROM");
					if (ADN_AE_EMPTY_FROM == null) {
						System.err.println("ADN_AE_EMPTY_FROM CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("ADN_AE_EMPTY_FROM CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + ADN_AE_EMPTY_FROM;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.ADN_AE_EMPTY_FROM = indProperties.getProperty("AE-ID");
						TestingParams.ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED = indProperties.getProperty("kpsaId");
						TestingParams.ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED = indProperties.getProperty("kpsa");

						System.out.println("ADN_AE_EMPTY_FROM : " + TestingParams.ADN_AE_EMPTY_FROM + " ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED :  " + TestingParams.ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED + " ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED : " + TestingParams.ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED);

					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String ADN_AE_ID_CONFIGURED_WITH_C = properties.getProperty("ADN_AE_ID_CONFIGURED_WITH_C");
					if (ADN_AE_ID_CONFIGURED_WITH_C == null) {
						System.err.println("ADN_AE_ID_CONFIGURED_WITH_C CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("ADN_AE_ID_CONFIGURED_WITH_C CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + ADN_AE_ID_CONFIGURED_WITH_C;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.ADN_AE_ID_PRE_CONFIGURED_WITH_C = indProperties.getProperty("AE-ID");
						TestingParams.ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_C = indProperties.getProperty("kpsaId");
						TestingParams.ADN_AE_PSK_PRE_CONFIGURED_WITH_C = indProperties.getProperty("kpsa");

						System.out.println("ADN_AE_ID_PRE_CONFIGURED_WITH_C : " + TestingParams.ADN_AE_ID_PRE_CONFIGURED_WITH_C + " ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_C :  " + TestingParams.ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_C + " ADN_AE_PSK_PRE_CONFIGURED_WITH_C : " + TestingParams.ADN_AE_PSK_PRE_CONFIGURED_WITH_C);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String ADN_AE_ID_CONFIGURED_WITH_S = properties.getProperty("ADN_AE_ID_CONFIGURED_WITH_S");
					if (ADN_AE_ID_CONFIGURED_WITH_S == null) {
						System.err.println("ADN_AE_ID_CONFIGURED_WITH_S CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {

						System.out.println("ADN_AE_ID_CONFIGURED_WITH_S CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + ADN_AE_ID_CONFIGURED_WITH_S;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.ADN_AE_ID_PRE_CONFIGURED_WITH_S = indProperties.getProperty("AE-ID");
						TestingParams.ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_S = indProperties.getProperty("kpsaId");
						TestingParams.ADN_AE_PSK_PRE_CONFIGURED_WITH_S = indProperties.getProperty("kpsa");

						System.out.println("ADN_AE_ID_PRE_CONFIGURED_WITH_S : " + TestingParams.ADN_AE_ID_PRE_CONFIGURED_WITH_S + " ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_S :  " + TestingParams.ADN_AE_PSK_ID_PRE_CONFIGURED_WITH_S + " ADN_AE_PSK_PRE_CONFIGURED_WITH_S : " + TestingParams.ADN_AE_PSK_PRE_CONFIGURED_WITH_S);

					}

				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String AE_ID_ALLOWED = properties.getProperty("AE_ID_ALLOWED");
					if (AE_ID_ALLOWED == null) {
						System.err.println("AE_ID_ALLOWED CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("AE_ID_ALLOWED CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + AE_ID_ALLOWED;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED = indProperties.getProperty("AE-ID");
						TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ID_ALLOWED = indProperties.getProperty("kpsaId");
						TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ALLOWED = indProperties.getProperty("kpsa");

						System.out.println("TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED : " + TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_ID_ALLOWED + "TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ID_ALLOWED :  " + TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ID_ALLOWED + " TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ALLOWED : " + TestingParams.TP_oneM2M_CSE_REG_CRE_005_ADN_AE_PSK_ALLOWED);

					}

				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String AE_ID_NOT_ALLOWED = properties.getProperty("AE_ID_NOT_ALLOWED");
					if (AE_ID_NOT_ALLOWED == null) {
						System.err.println("AE_ID_NOT_ALLOWED CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("AE_ID_NOT_ALLOWED CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + AE_ID_NOT_ALLOWED;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						// BELOW AE-ID APPENDED WITH RANDOM STRING TO MAKE IT NOT ALLOWED AE-ID AS PER
						// TESTCASE DESCRIPTION
						TestingParams.TP_oneM2M_CSE_REG_CRE_004_ADN_AE_ID_NOT_ALLOWED = indProperties.getProperty("AE-ID") + "-" + Math.random();
						TestingParams.TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_ID_NOT_ALLOWED = indProperties.getProperty("kpsaId");
						TestingParams.TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_NOT_ALLOWED = indProperties.getProperty("kpsa");

						System.out.println("TP_oneM2M_CSE_REG_CRE_004_ADN_AE_ID_NOT_ALLOWED : " + TestingParams.TP_oneM2M_CSE_REG_CRE_004_ADN_AE_ID_NOT_ALLOWED + " TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_ID_NOT_ALLOWED :  " + TestingParams.TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_ID_NOT_ALLOWED + " TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_NOT_ALLOWED : " + TestingParams.TP_oneM2M_CSE_REG_CRE_004_ADN_AE_PSK_NOT_ALLOWED);

					}

				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String AE_WITHOUT_PREPROVISIONED_AE_ID = properties.getProperty("AE_WITHOUT_PREPROVISIONED_AE_ID");
					if (AE_WITHOUT_PREPROVISIONED_AE_ID == null) {
						System.err.println("AE_WITHOUT_PREPROVISIONED_AE_ID CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("AE_WITHOUT_PREPROVISIONED_AE_ID CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + AE_WITHOUT_PREPROVISIONED_AE_ID;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.ADN_AE_EMPTY_FROM = indProperties.getProperty("AE-ID");
						TestingParams.ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED = indProperties.getProperty("kpsaId");
						TestingParams.ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED = indProperties.getProperty("kpsa");

						System.out.println("ADN_AE_EMPTY_FROM : " + TestingParams.ADN_AE_EMPTY_FROM + " ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED :  " + TestingParams.ADN_AE_EMPTY_FROM_PSK_ID_PRE_CONFIGURED + " ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED : " + TestingParams.ADN_AE_EMPTY_FROM_PSK_PRE_CONFIGURED);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}


				try {

					final String GENERAL_ADN_AE = properties.getProperty("GENERAL_ADN_AE");
					if (GENERAL_ADN_AE == null) {
						System.err.println("GENERAL_ADN_AE CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("GENERAL_ADN_AE CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + GENERAL_ADN_AE;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.GENERAL_ADN_AE_ID = indProperties.getProperty("AE-ID");
						TestingParams.GENERAL_ADN_AE_PSK_ID = indProperties.getProperty("kpsaId");
						TestingParams.GENERAL_ADN_AE_PSK = indProperties.getProperty("kpsa");

						System.out.println("GENERAL_ADN_AE_ID : " + TestingParams.GENERAL_ADN_AE_ID + " GENERAL_ADN_AE_PSK_ID :  " + TestingParams.GENERAL_ADN_AE_PSK_ID + " GENERAL_ADN_AE_PSK : " + TestingParams.GENERAL_ADN_AE_PSK);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {


					final String MN = properties.getProperty("MN");
					if (MN == null) {
						System.err.println("MN CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("MN CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + MN;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.REMOTE_CSE_ID = indProperties.getProperty("CSE_NODE_DATA.cseId");
						TestingParams.REMOTE_CSE_KPSA_ID = indProperties.getProperty("CSE_SYSTEM_POLICIES.KPSA_ID");
						TestingParams.REMOTE_CSE_KPSA = indProperties.getProperty("CSE_SYSTEM_POLICIES.KPSA");

						System.out.println("REMOTE_CSE_ID : " + TestingParams.REMOTE_CSE_ID + " REMOTE_CSE_KPSA_ID :  " + TestingParams.REMOTE_CSE_KPSA_ID + " REMOTE_CSE_KPSA : " + TestingParams.REMOTE_CSE_KPSA);

					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String SPARE1_ADN_AE = properties.getProperty("SPARE1_ADN_AE");
					if (SPARE1_ADN_AE == null) {
						System.err.println("SPARE1_ADN_AE CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("SPARE1_ADN_AE CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + SPARE1_ADN_AE;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.SPARE1_ADN_AE_ID = indProperties.getProperty("AE-ID");
						TestingParams.SPARE1_ADN_AE_PSK_ID = indProperties.getProperty("kpsaId");
						TestingParams.SPARE1_ADN_AE_PSK = indProperties.getProperty("kpsa");

						System.out.println("SPARE1_ADN_AE_ID : " + TestingParams.SPARE1_ADN_AE_ID + " SPARE1_ADN_AE_PSK_ID :  " + TestingParams.SPARE1_ADN_AE_PSK_ID + " SPARE1_ADN_AE_PSK : " + TestingParams.SPARE1_ADN_AE_PSK);
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

				try {

					final String SPARE2_ADN_AE = properties.getProperty("SPARE2_ADN_AE");
					if (SPARE2_ADN_AE == null) {
						System.err.println("SPARE2_ADN_AE CREDENTIAL FILE VALUE IS NULL");
						System.exit(0);
					} else {
						System.out.println("SPARE2_ADN_AE CREDENTIAL FILE VALUE IS NOT-NULL");
						individualFiles = System.getProperty("user.dir") + "/utils/securityCredentials/" + SPARE2_ADN_AE;
						indProperties.load(new FileInputStream(new File(individualFiles)));
						// indEnums = (Enumeration<String>) indProperties.propertyNames();

						TestingParams.SPARE2_ADN_AE_ID = indProperties.getProperty("AE-ID");
						TestingParams.SPARE2_ADN_AE_PSK_ID = indProperties.getProperty("kpsaId");
						TestingParams.SPARE2_ADN_AE_PSK = indProperties.getProperty("kpsa");

						System.out.println("SPARE2_ADN_AE_ID : " + TestingParams.SPARE2_ADN_AE_ID + " SPARE2_ADN_AE_PSK_ID :  " + TestingParams.SPARE2_ADN_AE_PSK_ID + " SPARE2_ADN_AE_PSK : " + TestingParams.SPARE2_ADN_AE_PSK);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					System.exit(0);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			TestingParams.CSE_POA_IP_ADDRESS  = properties.getProperty("CSE_POA_IP_ADDRESS");
			System.out.println("CSE_POA_IP_ADDRESS : " + TestingParams.CSE_POA_IP_ADDRESS);
			TestingParams.HTTP_PORT  = Integer.parseInt(properties.getProperty("HTTP_PORT"));
			System.out.println("HTTP_PORT : " + TestingParams.HTTP_PORT);
			TestingParams.HTTPS_PSK_PORT  = Integer.parseInt(properties.getProperty("HTTPS_PSK_PORT"));
			System.out.println("HTTPS_PSK_PORT : " + TestingParams.HTTPS_PSK_PORT);
			TestingParams.HTTPS_CERTIFICATE_PORT  = Integer.parseInt(properties.getProperty("HTTPS_CERTIFICATE_PORT"));
			System.out.println("HTTPS_CERTIFICATE_PORT : " + TestingParams.HTTPS_CERTIFICATE_PORT);
			TestingParams.COAP_PORT = Integer.parseInt(properties.getProperty("COAP_PORT"));
			System.out.println("COAP_PORT : " + TestingParams.COAP_PORT);
			TestingParams.COAPS_PSK_PORT  = Integer.parseInt(properties.getProperty("COAPS_PSK_PORT"));
			System.out.println("COAPS_PSK_PORT : " + TestingParams.COAPS_PSK_PORT);
			TestingParams.MQTT_PORT  = Integer.parseInt(properties.getProperty("MQTT_PORT"));
			System.out.println("MQTT_PORT : " + TestingParams.MQTT_PORT);
			TestingParams.MaxByteSize = new BigInteger(properties.getProperty("MAX_BYTE_SIZE"));
			System.out.println("MaxByteSize : " + TestingParams.MaxByteSize.intValue());
			TestingParams.MaxInstanceAge  = new BigInteger(properties.getProperty("MAX_INSTANCE_AGE"));
			System.out.println("MaxInstanceAge : " + TestingParams.MaxInstanceAge.intValue());
			TestingParams.MaxNrOfInstances  = new BigInteger(properties.getProperty("MAX_NR_OF_INSTANCE"));
			System.out.println("MaxNrOfInstances : " + TestingParams.MaxNrOfInstances.intValue());


			if (TestingParams.CSE_POA_IP_ADDRESS == null || TestingParams.HTTP_PORT == null || TestingParams.HTTPS_PSK_PORT == null || TestingParams.HTTPS_CERTIFICATE_PORT == null || TestingParams.COAP_PORT == null || TestingParams.COAPS_PSK_PORT == null || TestingParams.MQTT_PORT == null || TestingParams.MaxByteSize == null || TestingParams.MaxInstanceAge == null || TestingParams.MaxNrOfInstances == null) {
				System.exit(0);
			} else {
				// The CSE_POA of the CSE for listening to the MQTT request
				TestingParams.CSE_POA_MQTT = "mqtt://" + TestingParams.CSE_POA_IP_ADDRESS + ":" + TestingParams.MQTT_PORT;
				// The CSE_POA of the CSE for listening to the HTTP TLS_PSK request
				TestingParams.CSE_POA_HTTPS_PSK = "https://" + TestingParams.CSE_POA_IP_ADDRESS + ":" + TestingParams.HTTPS_PSK_PORT;

				// The CSE_POA of the CSE for listening to the HTTP TLS_PSK request
				TestingParams.CSE_POA_HTTPS_CERTIFICATE = "https://" + TestingParams.CSE_POA_IP_ADDRESS + ":" + TestingParams.HTTPS_CERTIFICATE_PORT;

				// The CSE_POA of the CSE for listening to the standard HTTP request
				TestingParams.CSE_POA_HTTP = "http://" + TestingParams.CSE_POA_IP_ADDRESS + ":" + TestingParams.HTTP_PORT;

				// The CSE_POA of the CSE for listening to the CoAP TLS_PSK request
				TestingParams.CSE_POA_COAPS_PSK = "coaps://" + TestingParams.CSE_POA_IP_ADDRESS + ":" + TestingParams.COAPS_PSK_PORT;

				// The CSE_POA of the CSE for listening to the standard CoAP request
				TestingParams.CSE_POA_COAP = "coap://" + TestingParams.CSE_POA_IP_ADDRESS + ":" + TestingParams.COAP_PORT;
			}
		

		}
	}

	@Override
	public void close() {
		// Your "after all tests" logic goes here
		System.out.println(" ################# LOOK I'M EXECUTING CLOSING ONCE ONLY ###############################");
	}
}