//package cdot.ccsp.locationPolicy;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.UUID;
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.Duration;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameter;
//import org.junit.runners.Parameterized.Parameters;
//
//import cdot.ccsp.onem2mTester.utils.TestingParams;
//import cdot.onem2m.common.dto.OneM2MRequest;
//import cdot.onem2m.common.enums.M2MLocationSource;
//import cdot.onem2m.common.enums.M2MOperation;
//import cdot.onem2m.common.enums.M2MSimpleResources;
//import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
//import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
//import cdot.onem2m.resource.xsd.LocationPolicy;
//import cdot.onem2m.resource.xsd.PrimitiveContent;
//
//@RunWith(Parameterized.class)
//public class LocationPolicyCreateTest {
//
//	OriginatorActions originatorActions = new OriginatorActionsBean();
//	static OneM2MRequest oneM2MRequest = new OneM2MRequest();
//	static LocationPolicy lcp = new LocationPolicy();
//	static int targetId = 0;
//	PrimitiveContent primContent = new PrimitiveContent();
//
//	// fields used together with @Parameter must be public
//	@Parameter(0)
//	public String lup;
//	@Parameter(1)
//	public String locationTargetId;
//	@Parameter(2)
//	public String locationServer;
//	@Parameter(3)
//	public String responseStatus;
//
//	@Parameters
//	public static Collection<String[]> testConditions() {
//		/*
//		 * Testcases` 1 : no location targetId , 4000 2 : no location server, 4000 4 :
//		 * invalid location server, 4000 3 : invalid locationUpdatePeriod, 4000 5 :
//		 * locationUpdatePeriod > 0, subscription creation, successful CI creation,
//		 * locationStatus set. 2001 6 : locationUpdatePeriod <= 0, successful CI
//		 * creation, locationStatus set. 2001 7 : locationContainerName already exists
//		 * at the CSE, 4000
//		 */
//		String expectedOutputs[][] = { { "PT0S", null, "http://192.168.5.126:8090/", "4000" }, { "PT0S", "abc", "  ", "4000" }, { "PT0S", "abc", "http://192.168.5.126:8070/", "4000" }, { "-PT45S", "abc", "  ", "4000" }, { "PT70S", "12", "http://192.168.5.126:8090/", "2001" }, { "PT0S", "13", "http://192.168.5.126:8090/", "2001" } };
////		String expectedOutputs[][] = {{ "0", "2001" }, { "PT6S", "2001" }};
////		String expectedOutputs[][] = {{ "PT6S", "2001" }};
////		String expectedOutputs[][] = {{ "PT10S", "2001" }};
//
//		return Arrays.asList(expectedOutputs);
//	}
//
//	@Test
//	public void testExecuteRequestPrimitiveString() {
//		try {
//			assertEquals(responseStatus, (originatorActions.execute(buildRequestPrimitiveVaryLUP(lup, locationTargetId, locationServer),  TestingParams.getCSEPoA(), TestingParams.ADMIN_AE_PSK_ID, TestingParams.ADMIN_AE_PSK)).getResponseStatusCode().toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@BeforeClass
//	public static void beforeClass() {
//		System.out.println("Before Class");
//		oneM2MRequest.setTo(TestingParams.CSE_BASE_RESOURCE_ID);
//		oneM2MRequest.setFrom(TestingParams.ADMIN_AE_ID);
//		oneM2MRequest.setOperation(M2MOperation.CREATE.getOperationId());
//		oneM2MRequest.setResourceType((M2MSimpleResources.LOCATIONPOLICY.getM2MResourceTypes()));
//		lcp.setLocationSource(M2MLocationSource.Network_based.getLocationSource());
//	}
//
//	public OneM2MRequest buildRequestPrimitiveVaryLUP(String LUP, String locationTargetId, String locationServer) {
//		UUID resourceID = UUID.randomUUID();
//		Object idObject = resourceID;
//		oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
//		lcp.setLocationTargetID(locationTargetId);
//		lcp.setLocationServer(locationServer);
//
////		lcp.setLocationContainerName(""+targetId);
//
////COMMENTED		lcp.getLocationUpdatePeriod().clear();
//
//		Duration duration = null;
//		try {
//			switch (LUP) {
//			case "null":
//				// COMMENTED lcp.getLocationUpdatePeriod().add(duration);
//				lcp.setLocationUpdatePeriod(Arrays.asList(duration));
//				break;
//			case "0":
//				duration = DatatypeFactory.newInstance().newDuration("PT0S");
//				// COMMENTED lcp.getLocationUpdatePeriod().add(duration);
//				lcp.setLocationUpdatePeriod(Arrays.asList(duration));
//				break;
//			case "":
//				System.out.println("Empty Duration");
//				break;
//			default:
//				duration = DatatypeFactory.newInstance().newDuration(LUP);
//				// COMMENTED lcp.getLocationUpdatePeriod().add(duration);
////				lcp.setLocationUpdatePeriod(Arrays.asList(duration));
//				lcp.setLocationUpdatePeriod(Arrays.asList(duration));
//			}
//		} catch (DatatypeConfigurationException e) {
//			e.printStackTrace();
//		}
//		primContent.setAny(lcp);
//		oneM2MRequest.setPrimitiveContent(primContent);
//		return oneM2MRequest;
//	}
//
//}