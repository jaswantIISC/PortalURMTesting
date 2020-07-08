package cdot.ccsp.flex;


import java.util.HashMap;
import java.util.Map;

import cdot.ccsp.onem2mTester.utils.ComposeRequest;
import cdot.ccsp.onem2mTester.utils.TestingParams;
import cdot.ccsp.utils.HAIMCONTAINERDEFINITION;
import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.M2MSimpleResources;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;
import cdot.onem2m.resource.xsd.FlexWrapper;

public class FlexContainerCreate {
	
	ComposeRequest composeRequest = new ComposeRequest();
	OriginatorActions originatorActions = new OriginatorActionsBean();
	
	
	public OneM2MResponse createDeviceAirConditioner(String to, String from, SecurityParams securityParams) {

		//DeviceAirConditioner airConditioner = new DeviceAirConditioner();
		FlexWrapper airConditioner = new FlexWrapper();
		airConditioner.setContainerDefinition(HAIMCONTAINERDEFINITION.DEVICEAIRCONDITIONER);
		airConditioner.setResourceName("AirConditioner");
		airConditioner.setRootName("hd:deACr");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.FLEXCONTAINER.getM2MResourceTypes(), airConditioner);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}
	
	public OneM2MResponse createTemperature(String to, String from, SecurityParams securityParams) {

		//Temperature  temperature = new Temperature();
		FlexWrapper temperature = new FlexWrapper();
		temperature.setContainerDefinition(HAIMCONTAINERDEFINITION.TEMPERATURE);
		temperature.setResourceName("Temperature");
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("curT0", "23.5");
		customAttributes.put("unit", "Celcius");
		temperature.setOtherElements(customAttributes);
		temperature.setRootName("hd:tempe");
		
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.FLEXCONTAINER.getM2MResourceTypes(), temperature);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}
	
	public OneM2MResponse createBinarySwitch(String to, String from, SecurityParams securityParams) {

		//BinarySwitch   binarySwitch = new BinarySwitch();
		FlexWrapper binarySwitch = new FlexWrapper();
		binarySwitch.setContainerDefinition(HAIMCONTAINERDEFINITION.BINARYSWITCH);
		binarySwitch.setResourceName("Switch");
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("powSe", Boolean.TRUE);
		binarySwitch.setOtherElements(customAttributes);
		
		binarySwitch.setRootName("hd:binSh");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.FLEXCONTAINER.getM2MResourceTypes(), binarySwitch);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}
	
	public OneM2MResponse createRelativeHumidity(String to, String from, SecurityParams securityParams) {

		//RelativeHumidity relativeHumidity = new RelativeHumidity();
		FlexWrapper relativeHumidity = new FlexWrapper();
		
		relativeHumidity.setContainerDefinition(HAIMCONTAINERDEFINITION.RELATIVEHUMIDITY);
		relativeHumidity.setResourceName("Humidity");
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("relHy", "70");
		relativeHumidity.setOtherElements(customAttributes);
		relativeHumidity.setRootName("hd:relHy");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.FLEXCONTAINER.getM2MResourceTypes(), relativeHumidity);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}
	
	public OneM2MResponse createEnergyConsupmtion(String to, String from, SecurityParams securityParams) {

		//EnergyConsumption energyConsumption = new EnergyConsumption();
		FlexWrapper energyConsumption = new FlexWrapper();
		
		energyConsumption.setContainerDefinition(HAIMCONTAINERDEFINITION.ENERGYCONSUMPTION);
		energyConsumption.setResourceName("Energy Consumption");
		Map<String,Object> customAttributes = new HashMap<String,Object>();
		customAttributes.put("abECn", "10");
		customAttributes.put("currt", "50");
		customAttributes.put("power", "90");
		energyConsumption.setRootName("hd:eneCn");
		
		OneM2MRequest oneM2MRequest = composeRequest.oneM2MRequestCreate(to, from, M2MSimpleResources.FLEXCONTAINER.getM2MResourceTypes(), energyConsumption);
		return originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams);

	}

}