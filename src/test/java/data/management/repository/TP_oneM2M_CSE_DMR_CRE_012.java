package data.management.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import cdot.ccsp.dmr.ContainerCreateTestCases;
import cdot.ccsp.dmr.ContentInstanceCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesCreateTestCases;
import cdot.ccsp.dmr.TimeSeriesInstanceCreateTestCases;
import cdot.ccsp.groupMgmt.GroupCreate;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
import cdot.ccsp.sca.StatsConfigCreateTestCases;
import cdot.ccsp.security.acp.AccessControlPolicyCreateTestCases;
import cdot.ccsp.subscription.SubscriptionCreate;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MOperation;
import cdot.onem2m.common.enums.M2MReleaseVersion;
import cdot.onem2m.common.enums.M2MStatus;
import cdot.onem2m.common.enums.MediaType;
import cdot.onem2m.common.enums.NotificationContentType;
import cdot.onem2m.common.enums.NotificationEventType;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.AE;
import cdot.onem2m.resource.xsd.AccessControlPolicy;
import cdot.onem2m.resource.xsd.BatchNotify;
import cdot.onem2m.resource.xsd.Container;
import cdot.onem2m.resource.xsd.ContentInstance;
import cdot.onem2m.resource.xsd.EventNotificationCriteria;
import cdot.onem2m.resource.xsd.Group;
import cdot.onem2m.resource.xsd.StatsConfig;
import cdot.onem2m.resource.xsd.Subscription;
import cdot.onem2m.resource.xsd.TimeSeries;
import cdot.onem2m.resource.xsd.TimeSeriesInstance;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
@Tag("TP_oneM2M_CSE_DMR_CRE_012")
public class TP_oneM2M_CSE_DMR_CRE_012 {

	String aeId;
	String aeResourceID;
	String aeStructuredResourceID;
	String container1ResourceId;
	String container1StructuredResourceId;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
	XStream xstream = new XStream(new StaxDriver());

	AERegistration aeRegister = new AERegistration();

	GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();

	@BeforeAll
	static void beforeAllSetup() {
		System.out.println("##########################################################################################################");
	}

	@BeforeEach
	void beforeEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_ACPI")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_ACPI() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		List<String> acpids = new ArrayList<String>();
		acpids.add(acp.getResourceID());

		OneM2MResponse container1Response = contCreate.createWithACPIDS(acpids, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(acpids, cont.getAccessControlPolicyIDs());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_ET")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_ET() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 15);
		String expirationTime = sdf.format(cal.getTime());

		OneM2MResponse container1Response = contCreate.createWithExpirationTime(expirationTime, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(expirationTime, cont.getExpirationTime());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_LBL")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_LBL() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		List<String> labels = new ArrayList<String>();
		labels.add("NewLabel");
		OneM2MResponse container1Response = contCreate.createWithFixedLabel(labels, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(labels, cont.getLabels());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_MNI")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_MNI() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithMaxNumberOfInstance(BigInteger.valueOf(10), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(BigInteger.valueOf(10), cont.getMaxNrOfInstances());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_MBS")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_MBS() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithMaxByteSize(BigInteger.valueOf(1000), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(BigInteger.valueOf(1000), cont.getMaxByteSize());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_MIA")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_MIA() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithMaxInstanceAge(BigInteger.valueOf(1000), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(BigInteger.valueOf(1000), cont.getMaxInstanceAge());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_OR")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_OR() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithOntologyRef("SomethingCalledOntologyRef", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals("SomethingCalledOntologyRef", cont.getOntologyRef());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CNT_CR")
	void TP_oneM2M_CSE_DMR_CRE_012_CNT_CR() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, cont.getCreator());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CIN_ET")
	void TP_oneM2M_CSE_DMR_CRE_012_CIN_ET() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, cont.getCreator());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String expirationTime = sdf.format(cal.getTime());

		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		OneM2MResponse ciResponse = ciCreate.createWithExpirationTime(expirationTime, container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, ci.getExpirationTime());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ci.getResourceID());
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId + "/" + ci.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CIN_LBL")
	void TP_oneM2M_CSE_DMR_CRE_012_CIN_LBL() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, cont.getCreator());

		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		OneM2MResponse ciResponse = ciCreate.createWithLabel(label, container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		assertEquals(label, ci.getLabels());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ci.getResourceID());
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId + "/" + ci.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CIN_CR")
	void TP_oneM2M_CSE_DMR_CRE_012_CIN_CR() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, cont.getCreator());

		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		OneM2MResponse ciResponse = ciCreate.createWithCreator(container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, ci.getCreator());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ci.getResourceID());
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId + "/" + ci.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CIN_CNF")
	void TP_oneM2M_CSE_DMR_CRE_012_CIN_CNF() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, cont.getCreator());

		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		OneM2MResponse ciResponse = ciCreate.createWithContentInfo("GiveCompleteInfoPlz", container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		assertEquals("GiveCompleteInfoPlz", ci.getContentInfo());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ci.getResourceID());
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId + "/" + ci.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_CIN_OR")
	void TP_oneM2M_CSE_DMR_CRE_012_CIN_OR() {
		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse container1Response = contCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), container1Response.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		Container cont = (Container) container1Response.getPrimitiveContent().getAny();
		container1ResourceId = cont.getResourceID();
		container1StructuredResourceId = aeStructuredResourceID + "/" + cont.getResourceName();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, cont.getCreator());

		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		ContentInstanceCreateTestCases ciCreate = new ContentInstanceCreateTestCases();
		OneM2MResponse ciResponse = ciCreate.createWithOntologyInfo("UltaPultaRef", container1StructuredResourceId, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), ciResponse.getResponseStatusCode());
		ContentInstance ci = (ContentInstance) ciResponse.getPrimitiveContent().getAny();
		assertEquals("UltaPultaRef", ci.getOntologyRef());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" CONTAINER  RESOURCE ID : " + container1ResourceId);
		System.out.println(" CONTAINER  STRUCTURED RESOURCE ID : " + container1StructuredResourceId);
		System.out.println(" CONTENTINSTANCE  RESOURCE ID : " + ci.getResourceID());
		System.out.println(" CONTENTINSTANCE  STRUCTURED RESOURCE ID : " + container1StructuredResourceId + "/" + ci.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_ACP_ET")
	void TP_oneM2M_CSE_DMR_CRE_012_ACP_ET() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String expirationTime = sdf.format(cal.getTime());
		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilegeAndExpirationTime(expirationTime, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, acp.getExpirationTime());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" ACCESSCONTROLPOLICY  RESOURCE ID : " + acp.getResourceID());
		System.out.println(" ACCESSCONTROLPOLICY  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + acp.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_ACP_LBL")
	void TP_oneM2M_CSE_DMR_CRE_012_ACP_LBL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		List<String> label = new ArrayList<String>();
		label.add("GabarSinghKaLabel");
		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilegeAndLabel(label, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		assertEquals(label, acp.getLabels());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" ACCESSCONTROLPOLICY  RESOURCE ID : " + acp.getResourceID());
		System.out.println(" ACCESSCONTROLPOLICY  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + acp.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_ACPI")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_ACPI() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		List<String> label = new ArrayList<String>();
		label.add("GabarSinghKaLabel");
		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilegeAndLabel(label, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();
		assertEquals(label, acp.getLabels());

		SubscriptionCreate subCreate = new SubscriptionCreate();
		List<String> acpList = new ArrayList<String>();
		acpList.add(acp.getResourceID());
		OneM2MResponse subResponse = subCreate.subWithExpirationCounterAndACPIds(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), acpList);
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(acpList, sub.getAccessControlPolicyIDs());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_ET")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_ET() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String expirationTime = sdf.format(cal.getTime());

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.subWithExpirationTime(expirationTime, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, sub.getExpirationTime());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_LBL")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_LBL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.subWithLabel(label, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(label, sub.getLabels());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_ENC")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_ENC() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		EventNotificationCriteria eventNotifCrit = new EventNotificationCriteria();
		List<BigInteger> eventType = new ArrayList<BigInteger>();
		eventType.add(NotificationEventType.CREATION_DIRECT_CHILD.getNotifEventTypeId());
		eventType.add(NotificationEventType.UPDATE_ATTRIBUTES_SUBSCRIBED_RESOURCE.getNotifEventTypeId());
		eventType.add(NotificationEventType.DELETION_SUBSCRIBED_RESOURCE.getNotifEventTypeId());
		eventNotifCrit.setNotificationEventType(eventType);
		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithEventNotificationCriteria(eventNotifCrit, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String requestData = xstream.toXML(eventNotifCrit);
		String responseData = xstream.toXML(sub.getEventNotificationCriteria());
		assertEquals(requestData, responseData);

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_EXC")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_EXC() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithExpirationCounter(BigInteger.valueOf(10), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), sub.getExpirationCounter());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_GPI")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_GPI() {

		ContainerCreateTestCases contCreate = new ContainerCreateTestCases();
		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		OneM2MResponse contResponse = contCreate.createWithAnyAttribute(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), contResponse.getResponseStatusCode());
		Container cont = (Container) contResponse.getPrimitiveContent().getAny();
		GroupCreate grpCreate = new GroupCreate();
		OneM2MResponse grpMResponse = grpCreate.CE_GMG_00001_00000(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID), Arrays.asList(cont.getResourceID()));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), grpMResponse.getResponseStatusCode());
		Group grp = (Group) grpMResponse.getPrimitiveContent().getAny();

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithGroupID(grp.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(grp.getResourceID(), sub.getGroupID());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_NFU")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_NFU() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithNotifForwardURI("http://IP:PORT/context", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals("http://IP:PORT/context", sub.getNotificationForwardingURI());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_BN")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_BN() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		BatchNotify bn = new BatchNotify();
		try {

			bn.setNumber(BigInteger.valueOf(10));
			Duration duration = DatatypeFactory.newInstance().newDuration("P0Y0M0DT0H5M0S");
			bn.setDuration(duration);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithBatchNotify(bn, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		String requestData = xstream.toXML(bn);
		String responseData = xstream.toXML(sub.getBatchNotify());
		assertEquals(requestData, responseData);

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_PSN")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_PSN() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithPreSubNotify(BigInteger.valueOf(10), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		;
		assertEquals(BigInteger.valueOf(10), sub.getPreSubscriptionNotify());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_PN")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_PN() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithPendNotif(BigInteger.valueOf(10), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), sub.getPendingNotification());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_NSP")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_NSP() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithNotifStorPrior(BigInteger.valueOf(10), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), sub.getNotificationStoragePriority());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_LN")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_LN() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithLatestNotify(true, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(true, sub.getLatestNotify());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_NCT")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_NCT() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithNotifContType(NotificationContentType.ALL_ATTRIBUTES.getNotifContentTypeId(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(NotificationContentType.ALL_ATTRIBUTES.getNotifContentTypeId(), sub.getNotificationContentType());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_NEC")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_NEC() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithNotifEventCat("SomethingRandomHere", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals("SomethingRandomHere", sub.getNotificationEventCat());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_CR")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_CR() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, sub.getCreator());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_SUB_SU")
	void TP_oneM2M_CSE_DMR_CRE_012_SUB_SU() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		SubscriptionCreate subCreate = new SubscriptionCreate();
		OneM2MResponse subResponse = subCreate.createWithSubscriberURI("http://IP:PORT/context", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subResponse.getResponseStatusCode());
		Subscription sub = (Subscription) subResponse.getPrimitiveContent().getAny();
		assertEquals("http://IP:PORT/context", sub.getSubscriberURI());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" SUBSCRIPTION  RESOURCE ID : " + sub.getResourceID());
		System.out.println(" SUBSCRIPTION  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + sub.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_STCG_CR")
	void TP_oneM2M_CSE_DMR_CRE_012_STCG_CR() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS

		// ADMIN-AE-REG
		AERegistration aeReg = new AERegistration();
		OneM2MResponse aeRegRes = aeReg.AERegisterWithAppName(TestingParams.CSE_BASE_RESOURCE_NAME, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeRegRes.getResponseStatusCode());
		// ADMIN-AE-REG

		StatsConfigCreateTestCases statConfigCreate = new StatsConfigCreateTestCases();
		OneM2MResponse statsConfigResponse = statConfigCreate.createWithCreator(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.ADMIN_AE_ID, TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), statsConfigResponse.getResponseStatusCode());
		StatsConfig statsConfig = (StatsConfig) statsConfigResponse.getPrimitiveContent().getAny();
		assertEquals(TestingParams.ADMIN_AE_ID, statsConfig.getCreator());
		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" STATSCONFIG  RESOURCE ID : " + statsConfig.getResourceID());
		System.out.println(" STATSCONFIG  STRUCTURED RESOURCE ID : " + TestingParams.CSE_BASE_RESOURCE_NAME + "/" + statsConfig.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_ACPI")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_ACPI() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		AccessControlPolicyCreateTestCases acpCreate = new AccessControlPolicyCreateTestCases();
		OneM2MResponse acpResponse = acpCreate.createWithAllPrivilege(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), acpResponse.getResponseStatusCode());
		// CONTAINER CREATE 1 ENDS
		AccessControlPolicy acp = (AccessControlPolicy) acpResponse.getPrimitiveContent().getAny();

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		List<String> acpIds = new ArrayList<String>();
		acpIds.add(acp.getResourceID());
		OneM2MResponse tsResponse = tsCreate.createWithLACPIds(acpIds, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(acpIds, ts.getAccessControlPolicyIDs());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_ET")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_ET() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		System.out.println(" ######## EXPIRATION TIME :" + expirationTime);

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithExpirationTime(expirationTime, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		System.out.println(" ######## EXPIRATION TIME RECIEVED :" + ts.getExpirationTime());
		assertEquals(expirationTime, ts.getExpirationTime());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());
	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_LBL")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_LBL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithLabel(label, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(label, ts.getLabels());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_CR")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_CR() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithCreator(TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(TestingParams.GENERAL_ADN_AE_ID, ts.getCreator());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_MNI")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_MNI() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithMaxNumberOfInstances(BigInteger.valueOf(10), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(10), ts.getMaxNrOfInstances());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_MBS")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_MBS() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithMaxByteSize(BigInteger.valueOf(1100), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(1100), ts.getMaxByteSize());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_MIA")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_MIA() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithMaxInstanceAge(BigInteger.valueOf(1100), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(1100), ts.getMaxInstanceAge());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_PEI")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_PEI() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithPeriodicInterval(BigInteger.valueOf(1100), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(1100), ts.getPeriodicInterval());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_MDD")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_MDD() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithMissingDataDetect(true, TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(true, ts.getMissingDataDetect());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_MDN")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_MDN() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithMissingDataDetectMaxNumber(BigInteger.valueOf(1000), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(TestingParams.MaxNrOfInstances, ts.getMissingDataMaxNr());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_MDT")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_MDT() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithMissingDataDetectTimer(BigInteger.valueOf(1000), TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(1000), ts.getMissingDataDetectTimer());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_OR")
	void TP_oneM2M_CSE_DMR_CRE_012_TS_OR() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithOntologyRef("UltaPultaRef", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals("UltaPultaRef", ts.getOntologyRef());

		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_ET")
	void TP_oneM2M_CSE_DMR_CRE_012_TSI_ET() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithOntologyRef("UltaPultaRef", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals("UltaPultaRef", ts.getOntologyRef());

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String expirationTime = sdf.format(cal.getTime());
		TimeSeriesInstanceCreateTestCases tsiCreate = new TimeSeriesInstanceCreateTestCases();
		OneM2MResponse tsiResponse = tsiCreate.createWithExpirationTime(expirationTime, ts.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsiResponse.getResponseStatusCode());
		TimeSeriesInstance tsi = (TimeSeriesInstance) tsiResponse.getPrimitiveContent().getAny();
		assertEquals(expirationTime, tsi.getExpirationTime());
		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());
		System.out.println(" TIMESERIESINSTANCE  RESOURCE ID : " + tsi.getResourceID());
		System.out.println(" TIMESERIESINSTANCE  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName() + "/" + tsi.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_LBL")
	void TP_oneM2M_CSE_DMR_CRE_012_TSI_LBL() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithOntologyRef("UltaPultaRef", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals("UltaPultaRef", ts.getOntologyRef());
		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		TimeSeriesInstanceCreateTestCases tsiCreate = new TimeSeriesInstanceCreateTestCases();
		OneM2MResponse tsiResponse = tsiCreate.createWithLabel(label, ts.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsiResponse.getResponseStatusCode());
		TimeSeriesInstance tsi = (TimeSeriesInstance) tsiResponse.getPrimitiveContent().getAny();
		assertEquals(label, tsi.getLabels());
		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());
		System.out.println(" TIMESERIESINSTANCE  RESOURCE ID : " + tsi.getResourceID());
		System.out.println(" TIMESERIESINSTANCE  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName() + "/" + tsi.getResourceName());

	}

	@Test
	@Tag("TP_oneM2M_CSE_DMR_CRE_012_TS_SNR")
	void TP_oneM2M_CSE_DMR_CRE_012_TSI_SNR() {

		aeStructuredResourceID = TestingParams.CSE_BASE_RESOURCE_NAME;

		// INITIAL STATE STARTS
		// AE-REGISTRATION
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
		aeStructuredResourceID = aeStructuredResourceID + "/" + ae.getResourceName();
		// CONTAINER CREATE 1

		TimeSeriesCreateTestCases tsCreate = new TimeSeriesCreateTestCases();
		OneM2MResponse tsResponse = tsCreate.createWithOntologyRef("UltaPultaRef", TestingParams.GENERAL_ADN_AE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsResponse.getResponseStatusCode());
		TimeSeries ts = (TimeSeries) tsResponse.getPrimitiveContent().getAny();
		assertEquals("UltaPultaRef", ts.getOntologyRef());
		List<String> label = new ArrayList<String>();
		label.add("NewLabel");
		TimeSeriesInstanceCreateTestCases tsiCreate = new TimeSeriesInstanceCreateTestCases();
		OneM2MResponse tsiResponse = tsiCreate.createWithSequenceNumber(BigInteger.valueOf(100), ts.getResourceID(), TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tsiResponse.getResponseStatusCode());
		TimeSeriesInstance tsi = (TimeSeriesInstance) tsiResponse.getPrimitiveContent().getAny();
		assertEquals(BigInteger.valueOf(100), tsi.getSequenceNr());
		// INITIAL STATE ENDS
		// CONTAINER UPDATE ENDS
		// TEST EVENT ENDS
		System.out.println(" AE-ID : " + aeId);
		System.out.println(" AE RESOURCE ID : " + aeResourceID);
		System.out.println(" AE STRUCTURED RESOURCE ID : " + aeStructuredResourceID);
		System.out.println(" TIMESERIES  RESOURCE ID : " + ts.getResourceID());
		System.out.println(" TIMESERIES  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName());
		System.out.println(" TIMESERIESINSTANCE  RESOURCE ID : " + tsi.getResourceID());
		System.out.println(" TIMESERIESINSTANCE  STRUCTURED RESOURCE ID : " + aeStructuredResourceID + "/" + ts.getResourceName() + "/" + tsi.getResourceName());

	}

	@AfterAll
	static void afterAllSetup() {
		System.out.println("############################################################################################################");
	}

	@AfterEach
	void afterEachSetup() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		if (TestingParams.DELETE_ALL_RESOURCES) {
//			AEReRegistration aeDelete = new AEReRegistration();
//			OneM2MResponse aeDeleteResponse = aeDelete.aeDeregister(aeResourceID, aeId, TestingParams.ADN_AE_PSK_ID, TestingParams.ADN_AE_PSK);
//			assertEquals(M2MStatus.DELETED.getOnem2mStatusCode(), aeDeleteResponse.getResponseStatusCode());

			UUID resourceID = UUID.randomUUID();
			Object idObject = resourceID;
			OneM2MRequest oneM2MRequest = new OneM2MRequest();
			oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
			oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
			oneM2MRequest.setTo(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.GENERAL_ADN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			OriginatorActions originatorActions = new OriginatorActionsBean();
			OneM2MResponse oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));

			oneM2MRequest.setRequestIdentifier(String.valueOf(idObject));
			oneM2MRequest.setContentType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setAcceptType(MediaType.JSON_RESOURCE);
			oneM2MRequest.setReleaseVersionIndicator(M2MReleaseVersion.RELEASE_2A.getReleaseVersion());
			oneM2MRequest.setTo(TestingParams.ADMIN_AE_ID);
			oneM2MRequest.setFrom(TestingParams.ADMIN_AE_ID);
			oneM2MRequest.setOperation(M2MOperation.DELETE.getOperationId());
			originatorActions = new OriginatorActionsBean();
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), TestingParams.getSecurityParams(TestingParams.ADMIN_AE_ID));
		}
		aeId = null;
		aeResourceID = null;
		aeStructuredResourceID = null;
		container1ResourceId = null;
		container1StructuredResourceId = null;
	}

}
