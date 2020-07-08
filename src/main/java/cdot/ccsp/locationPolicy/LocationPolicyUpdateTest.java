/*
 * package cdot.ccsp.locationPolicy;
 * 
 * import java.util.UUID;
 * 
 * import javax.xml.datatype.DatatypeConfigurationException; import
 * javax.xml.datatype.DatatypeFactory; import javax.xml.datatype.Duration;
 * 
 * import org.junit.BeforeClass; import org.junit.Test;
 * 
 * import cdot.onem2m.common.enums.M2MOperation; import
 * cdot.onem2m.resource.xsd.LocationPolicy; import
 * cdot.onem2m.resource.xsd.PrimitiveContent; import
 * cdot.onem2m.resource.xsd.RequestPrimitive;
 * 
 * public class LocationPolicyUpdateTest {
 * 
 *//**
	 * Test method for
	 * {@link cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean#execute(cdot.onem2m.resource.xsd.RequestPrimitive)}.
	 *//*
		 * 
		 * // AESecurityTestCertificate aeSecurityTestCertificate = new
		 * AESecurityTestCertificate(); AEServlet aeServlet = new AEServlet(); static
		 * RequestPrimitive requestPrimitive = new RequestPrimitive(); static
		 * LocationPolicy lcp = new LocationPolicy(); PrimitiveContent primContent = new
		 * PrimitiveContent();
		 * 
		 * @Parameter(0) public String input;
		 * 
		 * @Parameter(1) public String expectedOutput;
		 * 
		 * @Parameters public static Collection<String[]> testConditions() { String
		 * expectedOutputs[][] = { { "", "2001" }, { "0", "2001" }, { "null", "2001" },
		 * { "PT6S", "2001" } }; return Arrays.asList(expectedOutputs); }
		 * 
		 * @Test public void testExecuteRequestPrimitiveString() { assertEquals("2004",
		 * aeServlet.execute(buildRequestPrimitiveVaryLUP("PT0S"))); }
		 * 
		 * @BeforeClass public static void beforeClass() {
		 * System.out.println("Before Class"); requestPrimitive.setTo("R7358");
		 * requestPrimitive.setFrom("S0");
		 * requestPrimitive.setOperation(M2MOperation.UPDATE.getOperationId()); }
		 * 
		 * public RequestPrimitive buildRequestPrimitiveVaryLUP(String LUP) { UUID
		 * resourceID = UUID.randomUUID(); Object idObject = resourceID;
		 * requestPrimitive.setRequestIdentifier(String.valueOf(idObject));
		 * lcp.getLocationUpdatePeriod().clear();
		 * 
		 * Duration duration = null; try { switch (LUP) { case "null":
		 * lcp.getLocationUpdatePeriod().add(duration); break; case "0": duration =
		 * DatatypeFactory.newInstance().newDuration("PT0S");
		 * lcp.getLocationUpdatePeriod().add(duration); break; case "":
		 * System.out.println("Empty Duration"); break; default: duration =
		 * DatatypeFactory.newInstance().newDuration(LUP);
		 * lcp.getLocationUpdatePeriod().add(duration); } } catch
		 * (DatatypeConfigurationException e) { e.printStackTrace(); }
		 * primContent.setAny(lcp); requestPrimitive.setPrimitiveContent(primContent);
		 * 
		 * return requestPrimitive; }
		 * 
		 * }
		 */