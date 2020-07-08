package cdot.ccsp.security.acp;

/**
*COPYRIGHT (c) 2016, C-DOT. All rights reserved.
*No part of this file may be reproduced in any form without prior written permission
from C-DOT.
*Author : Chaitan Yadav
*Creation Date : 18 July 2016
*Supported Release : OneM2M Release 1
*Documents Referred : {
Document Name   Version    Section
TS-0001         2.9      
TS-0004			2.8
}
*Supported XSD : XSDv1.8
*Description: refer xsd file CDT-enumerationTypes-v1_8_0.xsd for more info about Operation code    
**/ 

public enum AccessControlOperations {
	CR(1),RE(2),CR_RE(3),UP(4),CR_UP(5),RE_UP(6),CR_UP_RE(7),DE(8),CR_DE(9),RE_DE(10),CR_RE_DE(11),UP_DE(12),
	CR_UP_DE(13),RE_UP_DE(14),CR_RE_UP_DE(15),NO(16),CR_NO(17),RE_NO(18),CR_RE_NO(19),UP_NO(20),CR_UP_NO(21),
	RE_UP_NO(22),CR_RE_UP(23),DE_NO(24),CR_DE_NO(25),RE_DE_NO(26),CR_RE_DE_NO(27),UP_DE_NO(28),CR_UP_DE_NO(29),
	RE_UP_DE_NO(30),CR_RE_UP_DE_NO(31),DI(32),CR_DI(33),RE_DI(34),CR_RE_DI(35),UP_DI(36),CR_UP_DI(37),RE_UP_DI(38),
	CR_RE_UP_DI(39),DE_DI(40),CR_DE_DI(41),RE_DE_DI(42),CR_RE_DE_DI(43),UP_DE_DI(44),CR_UP_DE_DI(45),RE_UP_DE_DI(46),
	CR_RE_UP_DE_DI(47),NO_DI(48),CR_NO_DI(49),RE_NO_DI(50),CR_RE_NO_DI(51),UP_NO_DI(52),CR_UP_NO_DI(53),RE_UP_NO_DI(54),
	CR_RE_DI_UP(55),DE_NO_DI(56),CR_DE_NO_DI(57),RE_DE_NO_DI(58),CR_RE_DE_NO_DI(59),UP_DE_NO_DI(60),CR_UP_DE_NO_DI(61),
	RE_UP_DE_NO_DI(62),CR_RE_UP_DE_NO_DI(63);
	
	
	private int value;
	
	
	public int getValue() {
		return value;
	}


	private AccessControlOperations(int value) {
		this.value = value;
	}

	public static String getName(int value){

		for(AccessControlOperations e : AccessControlOperations.values()){
			if(value == e.value)
				return e.name();
		}
		return null;
	}

}