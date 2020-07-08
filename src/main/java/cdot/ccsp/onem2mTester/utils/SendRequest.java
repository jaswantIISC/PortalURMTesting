package cdot.ccsp.onem2mTester.utils;

import java.io.IOException;

import cdot.onem2m.common.dto.OneM2MRequest;
import cdot.onem2m.common.dto.OneM2MResponse;
import cdot.onem2m.common.enums.SecurityParams;
import cdot.onem2m.platform.originatorActions.business.OriginatorActions;
import cdot.onem2m.platform.originatorActions.business.OriginatorActionsBean;

public class SendRequest {

	public OneM2MResponse sendRequest(OneM2MRequest oneM2MRequest, SecurityParams securityParams) {

		OriginatorActions originatorActions = new OriginatorActionsBean();
		OneM2MResponse oneM2MResponse = null;

		try {
			if (securityParams == null) {

				oneM2MResponse = originatorActions.executeWithoutSecureAssociation(oneM2MRequest, TestingParams.getCSEPoA());

			} else if (securityParams.getCertificateBasedParams() != null) {

				oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams.getCertificateBasedParams().getKsName(), securityParams.getCertificateBasedParams().getKsPass(), securityParams.getCertificateBasedParams().getClientKeyPass());

			} else if (securityParams.getPskBasedParams() != null) {

				oneM2MResponse = originatorActions.execute(oneM2MRequest, TestingParams.getCSEPoA(), securityParams.getPskBasedParams().getKpmID(), securityParams.getPskBasedParams().getKpmStr());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return oneM2MResponse;
	}
}
