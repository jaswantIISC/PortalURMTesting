package cdot.ccsp.onem2mTester.utils;

public class SecurityParams2 {

	CertificateBasedParams certificateBasedParams;
	PSKBasedParams pskBasedParams;

	public CertificateBasedParams getCertificateBasedParams() {
		return certificateBasedParams;
	}

	public void setCertificateBasedParams(CertificateBasedParams certificateBasedParams) {
		this.certificateBasedParams = certificateBasedParams;
	}

	public PSKBasedParams getPskBasedParams() {
		return pskBasedParams;
	}

	public void setPskBasedParams(PSKBasedParams pskBasedParams) {
		this.pskBasedParams = pskBasedParams;
	}

	public class PSKBasedParams {

		String kpmID;
		String kpmStr;

		public String getKpmID() {
			return kpmID;
		}

		public void setKpmID(String kpmID) {
			this.kpmID = kpmID;
		}

		public String getKpmStr() {
			return kpmStr;
		}

		public void setKpmStr(String kpmStr) {
			this.kpmStr = kpmStr;
		}
	}

	public class CertificateBasedParams {

		String ksName;
		String ksPass;
		String clientKeyPass;

		public String getKsName() {
			return ksName;
		}

		public void setKsName(String ksName) {
			this.ksName = ksName;
		}

		public String getKsPass() {
			return ksPass;
		}

		public void setKsPass(String ksPass) {
			this.ksPass = ksPass;
		}

		public String getClientKeyPass() {
			return clientKeyPass;
		}

		public void setClientKeyPass(String clientKeyPass) {
			this.clientKeyPass = clientKeyPass;
		}
	}

}
