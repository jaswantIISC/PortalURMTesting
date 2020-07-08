package cdot.ccsp.flex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.FlexWrapper;

public class FlexContainerUpdate {

	ComposeRequest composeRequest = new ComposeRequest();
	OriginatorActions originatorActions = new OriginatorActionsBean();

	public OneM2MResponse updateDeviceAirConditioner(String to, String from, SecurityParams securityParams) {

		//DeviceAirConditioner airConditioner = new DeviceAirConditioner();
		FlexWrapper airConditioner = new FlexWrapper();
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestUpdate(to, from, airConditioner);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateTemperature(String to, String from, SecurityParams securityParams) {

		
		FlexWrapper temperature = new FlexWrapper();
		
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("curT0", "27.4");
		
		temperature.setOtherElements(customAttributes);
		temperature.setRootName("hd:tempe");
		
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestUpdate(to, from, temperature);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateBinarySwitch(String to, String from, SecurityParams securityParams) {

		
		FlexWrapper binarySwitch = new FlexWrapper();
		
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("powSe", Boolean.FALSE);
		binarySwitch.setOtherElements(customAttributes);
		
		binarySwitch.setRootName("hd:binSh");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestUpdate(to, from, binarySwitch	);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}
	
	public OneM2MResponse updateBinarySwitchWithCommonAttr(String to, String from, SecurityParams securityParams) {

		FlexWrapper binarySwitch = new FlexWrapper();

		List<String> labels = new ArrayList<String>();
		labels.add("TestLabel");
		binarySwitch.setLabels(labels);
		binarySwitch.setRootName("hd:binSh");
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestUpdate(to, from, binarySwitch);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateRelativeHumidity(String to, String from, SecurityParams securityParams) {

		
		FlexWrapper relativeHumidity = new FlexWrapper();
		
		
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("relHy", "85");
		relativeHumidity.setOtherElements(customAttributes);
		relativeHumidity.setRootName("hd:relHy");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestUpdate(to, from, relativeHumidity);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

	public OneM2MResponse updateEnergyConsupmtion(String to, String from, SecurityParams securityParams) {

		
		FlexWrapper energyConsumption = new FlexWrapper();
		
		
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		
		customAttributes.put("currt", "55");
		
		energyConsumption.setRootName("hd:eneCn");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestUpdate(to, from, energyConsumption);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

}