package cdot.ccsp.subscription;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.BatchNotify;
import cdot.onem2m.resource.xsd.EventNotificationCriteria;
import cdot.onem2m.resource.xsd.MissingData;
import cdot.onem2m.resource.xsd.OperationMonitor;
import cdot.onem2m.resource.xsd.RateLimit;
import cdot.onem2m.resource.xsd.Subscription;

public class SubscriptionCreate {
	
//	ComposeRequest composeRequest = new ComposeRequest();
	OriginatorActions originatorActions = new OriginatorActionsBean();
	
	
	public static void main(String[] args) {
		SubscriptionCreate subCreate = new SubscriptionCreate();
		subCreate.create();
	}
	
	public String create() {

		String value = "RESPONSE";
		try {
			
			String to = TestingParams.GENERAL_ADN_AE_ID;
			String from = TestingParams.GENERAL_ADN_AE_ID;

			String pskId = TestingParams.GENERAL_ADN_AE_PSK_ID;
			String psk = TestingParams.GENERAL_ADN_AE_PSK;

			System.out.println("******************[CSEController][retrieve] Inside get request");
			System.out.println("******************[CSEController][retrieve] Calling Bean class for DB access");
			OneM2MResponse oneM2MResponse = subWithEventType1(to, from, TestingParams.getSecurityParams(TestingParams.GENERAL_ADN_AE_ID));
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return "<tag>" + value + "</tag>";
	}
	
	public OneM2MResponse createWithVerifyRequest(List<String> nUList, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setNotificationURI(nUList);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithResultContent(BigInteger resultContent, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MRequest.setResultContent(resultContent);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithSubscription(Subscription sub, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithEventNotificationCriteriaAndExpirationCounter(EventNotificationCriteria enc, BigInteger expCount, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setEventNotificationCriteria(enc);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			sub.setExpirationCounter(expCount);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}
	
	public OneM2MResponse createWithEventNotificationCriteria(EventNotificationCriteria enc, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setEventNotificationCriteria(enc);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);

			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithExpirationCounter(BigInteger expireCount, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setExpirationCounter(expireCount);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithGroupID(String groupResourceId, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setGroupID(groupResourceId);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithNotifForwardURI(String nfu, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setNotificationForwardingURI(nfu);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithBatchNotify(BatchNotify bn, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setBatchNotify(bn);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithRateLimit(RateLimit rl, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setRateLimit(rl);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithPreSubNotify(BigInteger preSubNotify, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setPreSubscriptionNotify(preSubNotify);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithPendNotif(BigInteger pendNotif, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setPendingNotification(pendNotif);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithNotifStorPrior(BigInteger notifStorPrior, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setNotificationStoragePriority(notifStorPrior);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithLatestNotify(Boolean latestNotify, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setLatestNotify(latestNotify);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithNotifContType(BigInteger notifContType, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setNotificationContentType(notifContType);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithNotifEventCat(String notifEventCat, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setNotificationEventCat(notifEventCat);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}


	public OneM2MResponse createWithCreator(String creator, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setCreator(creator);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse createWithSubscriberURI(String su, String to, String from, SecurityParams securityParams) {

		OneM2MRequest oneM2MRequest = null;
		OneM2MResponse oneM2MResponse = null;
		OriginatorActions originatorActions = new OriginatorActionsBean();

		try {
			Subscription sub = new Subscription();
			sub.setSubscriberURI(su);
			List<String> nfURI = new ArrayList<String>();
			nfURI.add("http://192.168.105.141");
			sub.setNotificationURI(nfURI);
			oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), sub);
			oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oneM2MResponse;

	}

	public OneM2MResponse targetNotSubscribable(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscription(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
		}
	
	public OneM2MResponse subWithExpirationCounter(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionExpCounter(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithLabel(List<String> label, String to, String from, SecurityParams securityParams) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionLabel(label, new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}

	public OneM2MResponse subWithResourceName(String resourceName, String to, String from, SecurityParams securityParams) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionLabel(resourceName, new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}

	public OneM2MResponse subWithExpirationCounterAndACPIds(String to, String from, SecurityParams securityParams, List<String> acpIds) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionExpCounter(new Subscription(), acpIds));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}

	public OneM2MResponse subWithExpirationTime(String expirationTime, String to, String from, SecurityParams securityParams) {
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionExpirationCounter(new Subscription(), expirationTime));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}

	public OneM2MResponse subWithBatchNotify(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionBatchNotify(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithRateLimit(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionRateLimit(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithPreSubscriptionNotify(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionPreSubNotif(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithPendingNotification(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionPendingNotif(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithSubscriptionNotifStoragePriority(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionNotifiStoragePriority(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithLatestNotify(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionLatestNotif(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithEventType1(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionEventType_1(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithEventType2(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionEventType_2(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithEventType3(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionEventType_3(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithEventType4(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionEventType_4(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	//CDOT_CE_SUB_00002_00014
	
	public OneM2MResponse subWithCreatedBeforeCreatedAfter(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionCreatedBeforeCreatedAfter(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithModifiedSinceUnmodifiedSince(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionModifiedSinceUnmodifiedSince(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithStateTagBiggerSmaller(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionStateTagBiggerSmaller(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithSizeAboveSizeBelow(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionSizeAboveSizeBelow(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithAttribute(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionAttribute(new Subscription(), ""));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithChildResourceType(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionChildResourceType(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithOperationMonitor(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionOperationMonitor(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	public OneM2MResponse subWithMissingData(String to, String from, SecurityParams securityParams){
		OneM2MRequest oneM2MRequest = ComposeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.SUBSCRIPTION.getM2MResourceTypes(), populateSubscriptionMissingData(new Subscription()));
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);
	}
	
	//---------------------------------------------------*********************--------------------------------------------//
	
	private Subscription populateSubscription(Subscription sub){
		
		
		return subscriptionWithMandatoryParameters(sub);
	}
	
	//---------------------------------------------------*********************--------------------------------------------//
	
	private Subscription subscriptionWithMandatoryParameters(Subscription sub){
		List<String> nfURI = new ArrayList<String>();
		nfURI.add("http://192.168.105.141");
		sub.setNotificationURI(nfURI);
		return sub;	
	}

	private Subscription populateSubscriptionExpCounter(Subscription sub) {
		subscriptionWithMandatoryParameters(sub);
		sub.setExpirationCounter(BigInteger.valueOf(15));
		return sub;
	}
	
	private Subscription populateSubscriptionLabel(List<String> label, Subscription sub) {
		subscriptionWithMandatoryParameters(sub);
		sub.setLabels(label);
		return sub;
	}

	private Subscription populateSubscriptionLabel(String resourceName, Subscription sub) {
		subscriptionWithMandatoryParameters(sub);
		sub.setResourceName(resourceName);
		return sub;
	}

	private Subscription populateSubscriptionExpCounter(Subscription sub, List<String> acpIds) {
		subscriptionWithMandatoryParameters(sub);
		sub.setExpirationCounter(BigInteger.valueOf(15));
		sub.setAccessControlPolicyIDs(acpIds);
		return sub;
	}

	private Subscription populateSubscriptionExpirationCounter(Subscription sub, String expirationTime) {
		subscriptionWithMandatoryParameters(sub);
		sub.setExpirationCounter(BigInteger.valueOf(15));
		sub.setExpirationTime(expirationTime);
		return sub;
	}

	private Subscription populateSubscriptionBatchNotify(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		BatchNotify bN = new BatchNotify();
		bN.setNumber(BigInteger.valueOf(10));
		Duration dur;
		try {
			dur = DatatypeFactory.newInstance().newDuration("P1Y2M3DT4H5M6S");
			bN.setDuration(dur);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		sub.setBatchNotify(bN);
		return sub;
	}
	
	private Subscription populateSubscriptionRateLimit(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		RateLimit rT = new RateLimit();
		rT.setMaxNrOfNotify(BigInteger.valueOf(20));
		Duration dur;
		try {
			dur = DatatypeFactory.newInstance().newDuration("P1Y2M3DT4H5M6S");
			rT.setTimeWindow(dur);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		sub.setRateLimit(rT);
		return sub;
	}
	
	private Subscription populateSubscriptionPreSubNotif(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		sub.setPreSubscriptionNotify(BigInteger.valueOf(20));
		return sub;
	}
	
	private Subscription populateSubscriptionPendingNotif(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		sub.setPendingNotification(BigInteger.valueOf(20));
		return sub;
	}
	
	private Subscription populateSubscriptionNotifiStoragePriority(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		sub.setNotificationStoragePriority(BigInteger.valueOf(20));
		return sub;
	}
	
	private Subscription populateSubscriptionLatestNotif(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		sub.setLatestNotify(true);
		return sub;
	}
	
	private Subscription subscriptionEventCat(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		sub.setNotificationEventCat("200");
		return sub;
	}
	
	
	private Subscription populateSubscriptionEventType_1(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<BigInteger> eventType = eveNot.getNotificationEventType();
		if(eventType==null)
		{
			eventType = new ArrayList<BigInteger>();
		}
			eventType.add(BigInteger.valueOf(1));
			eveNot.setNotificationEventType(eventType);
			sub.setEventNotificationCriteria(eveNot);
		
		return sub;
	}
	
	private Subscription populateSubscriptionEventType_2(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<BigInteger> eventType = eveNot.getNotificationEventType();
		if(eventType==null)
		{
			eventType = new ArrayList<BigInteger>();
		}
			eventType.add(BigInteger.valueOf(2));
			eveNot.setNotificationEventType(eventType);
			sub.setEventNotificationCriteria(eveNot);
		
		return sub;
	}
	
	private Subscription populateSubscriptionEventType_3(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<BigInteger> eventType = eveNot.getNotificationEventType();
		if(eventType==null)
		{
			eventType = new ArrayList<BigInteger>();
		}
			eventType.add(BigInteger.valueOf(3));
			eveNot.setNotificationEventType(eventType);
			sub.setEventNotificationCriteria(eveNot);
		
		return sub;
	}
	
	private Subscription populateSubscriptionEventType_4(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<BigInteger> eventType = eveNot.getNotificationEventType();
		if(eventType==null)
		{
			eventType = new ArrayList<BigInteger>();
		}
			eventType.add(BigInteger.valueOf(4));
			eveNot.setNotificationEventType(eventType);
			sub.setEventNotificationCriteria(eveNot);
		
		return sub;
	}
	
	private Subscription populateSubscriptionCreatedBeforeCreatedAfter(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		eveNot.setCreatedBefore("20181225T121045");
		eveNot.setCreatedAfter("20180525T121045");
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionModifiedSinceUnmodifiedSince(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		eveNot.setModifiedSince("20181225T121045");
		eveNot.setUnmodifiedSince("20180525T121045");
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionStateTagBiggerSmaller(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		eveNot.setStateTagBigger(BigInteger.valueOf(10));
		eveNot.setStateTagSmaller(BigInteger.valueOf(5));
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionSizeAboveSizeBelow(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		eveNot.setSizeAbove(BigInteger.valueOf(10));
		eveNot.setSizeBelow(BigInteger.valueOf(5));
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionAttribute(Subscription sub, String attribute){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<String> attributes = eveNot.getAttribute();
		if(attributes==null)
			attributes = new ArrayList<>();
		attributes.add(attribute);
		eveNot.setAttribute(attributes);
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionChildResourceType(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<BigInteger> childResourceType = eveNot.getChildResourceType();
		if(childResourceType==null)
			childResourceType = new ArrayList<>();
		childResourceType.add(BigInteger.valueOf(4));
		eveNot.setChildResourceType(childResourceType);
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionOperationMonitor(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		List<OperationMonitor> opMonList = eveNot.getOperationMonitor();
		if(opMonList==null)
			opMonList = new ArrayList<>();
		OperationMonitor opMonitor = new OperationMonitor();
		opMonitor.setOperations(BigInteger.valueOf(2));
		opMonitor.setOriginator("S0");
		opMonList.add(opMonitor);
		eveNot.setOperationMonitor(opMonList);
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	private Subscription populateSubscriptionMissingData(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		MissingData missingData = eveNot.getMissingData();
		if(missingData==null)
			missingData = new MissingData();
		missingData.setNumber(BigInteger.valueOf(20));
		Duration dur;
		try {
			dur = DatatypeFactory.newInstance().newDuration("P1Y2M3DT4H5M6S");
			missingData.setDuration(dur);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		eveNot.setMissingData(missingData);
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}
	
	/*private Subscription subscriptionFilterOpearation(Subscription sub){
		subscriptionWithMandatoryParameters(sub);
		EventNotificationCriteria eveNot = sub.getEventNotificationCriteria();
		if(eveNot==null)
			eveNot = new EventNotificationCriteria();
		MissingData missingData = eveNot.
		if(missingData==null)
			missingData = new MissingData();
		missingData.setNumber(BigInteger.valueOf(20));
		Duration dur;
		try {
			dur = DatatypeFactory.newInstance().newDuration("P1Y2M3DT4H5M6S");
			missingData.setDuration(dur);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		eveNot.setMissingData(missingData);
		sub.setEventNotificationCriteria(eveNot);
		return sub;
	}*/
}
