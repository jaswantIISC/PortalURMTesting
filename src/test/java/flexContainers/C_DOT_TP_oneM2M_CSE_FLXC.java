package flexContainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cdot.ccsp.flex.FlexContainerCreate;
import cdot.ccsp.flex.FlexContainerUpdate;
import cdot.ccsp.onem2mTester.utils.GeneralRetrieveTestCases;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.registration.AERegistration;
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
import cdot.onem2m.resource.xsd.EventNotificationCriteria;
import cdot.onem2m.resource.xsd.FlexWrapper;
import cdot.onem2m.resource.xsd.Subscription;
import initializer.ConfigurationExtension;

@ExtendWith({ ConfigurationExtension.class })
public class C_DOT_TP_oneM2M_CSE_FLXC {
	
	String aeId;
	String aeResourceID;
	String pskId = TestingParams.GENERAL_ADN_AE_PSK_ID;
	String psk = TestingParams.GENERAL_ADN_AE_PSK;
	AERegistration aeRegister = new AERegistration();
	FlexContainerCreate flexCreate = new FlexContainerCreate();
	FlexContainerUpdate flexUpdate = new FlexContainerUpdate();
	GeneralRetrieveTestCases genRetrieve = new GeneralRetrieveTestCases();
	
	
	@Test
	@Tag("C_DOT_TP_oneM2M_CSE_FLXC_001")
	void C_DOT_TP_oneM2M_CSE_FLXC_001( ) {
		
		// INITIAL STATE STARTS
		// AE-REGISTRATION        
		OneM2MResponse aeMResponse = aeRegister.AERegisterWithEmptyFrom(TestingParams.CSE_BASE_RESOURCE_ID, TestingParams.GENERAL_ADN_AE_ID, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		AE ae = (AE) aeMResponse.getPrimitiveContent().getAny();
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), aeMResponse.getResponseStatusCode());
		// AE-REGISTRATION DONE
		aeId = ae.getAEID();
		aeResourceID = ae.getResourceID();
				
		//DeviceAirConditioner CREATE
		
		OneM2MResponse deviceResponse = flexCreate.createDeviceAirConditioner(aeId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), deviceResponse.getResponseStatusCode());
		String deviceRId = ((FlexWrapper)deviceResponse.getPrimitiveContent().getAny()).getResourceID();
		
		//BinarySwitch CREATE as a child of DeviceAirConditioner
		OneM2MResponse binarySwitchResponse = flexCreate.createBinarySwitch(deviceRId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), binarySwitchResponse.getResponseStatusCode());
		String binarySwitchRId = ((FlexWrapper)binarySwitchResponse.getPrimitiveContent().getAny()).getResourceID();
		
		//Temperature CREATE as a child of DeviceAirConditioner
		OneM2MResponse tempResponse = flexCreate.createTemperature(deviceRId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), tempResponse.getResponseStatusCode());
		String tempRId = ((FlexWrapper)tempResponse.getPrimitiveContent().getAny()).getResourceID();
		
		
		SubscriptionCreate subsCreate = new SubscriptionCreate();
		
		Subscription sub = new Subscription();
		
		EventNotificationCriteria eveNot = new EventNotificationCriteria();
		
		List<BigInteger> eventType = new ArrayList<BigInteger>();

		eventType.add(NotificationEventType.UPDATE_ATTRIBUTES_SUBSCRIBED_RESOURCE.getNotifEventTypeId());
		eveNot.setNotificationEventType(eventType);
		sub.setEventNotificationCriteria(eveNot);
		sub.setNotificationContentType(NotificationContentType.MODIFIED_ATTRIBUTES.getNotifContentTypeId());
		List<String> nURI = new ArrayList<String>();
		nURI.add("http://192.168.105.141");
		sub.setNotificationURI(nURI);
		
		OneM2MResponse subsResponse = subsCreate.createWithSubscription(sub, tempRId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.CREATED.getOnem2mStatusCode(), subsResponse.getResponseStatusCode());
		
		
		OneM2MResponse tempUpdateResponse = flexUpdate.updateTemperature(tempRId, aeId, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
		assertEquals(M2MStatus.UPDATED.getOnem2mStatusCode(), tempUpdateResponse.getResponseStatusCode());
			
		
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
		}
		aeId = null;
		aeResourceID = null;
		
	}

}