package com.italia.marxmind.bris.enm;

/**
 * 
 * @author mark italia
 * @since 07/7/2017
 * @version 1.0
 *
 */

public enum Purpose {

	FINANCIAL(1, "FINANCIAL ASSISTANCE"),
	MEDICAL(2,"FOR MEDICAL ASSISTANCE"),
	FIN_MED(3,"FINANCIAL/MEDICAL ASSISTANCE"),
	POLICE(4,"TO SECURE POLICE CLEARANCE"),
	BUSINESS_NEW(5,"BUSINESS PERMIT MUNICIPAL APPLICATION"),
	FISCH_CAGE(6,"FISH CAGE OPERATOR MUNICIPAL PERMIT APPLICATION"),
	JOB_APPLICATION(7,"FOR LOCAL EMPLOYMENT"),
	OTHER_LEGAL_MATTERS(8,"WHATEVER LEGAL PURPOSE THIS MAY SERVE"),
	BUSINESS_RENEWAL(9,"BUSINESS PERMIT MUNICIPAL RENEWAL"),
	
	BANK_ACCOUNT(10, "OPENING A BANK ACCOUNT"),
	LENDING(11, "LENDING APPLICATION"),
	LOAN(12, "LOAN APPLICATION"),
	FINANCING(13, "FINANCING APPLICATION"),
	BURIAL(14, "INDIGENT BURIAL ASSISTANCE"),
	CALAMITY(15, "CALAMITY ASSISTANCE"),
	POSTAL(16, "POSTAL ID APPLICATION"),
	NBI(17, "TO SECURE NBI CLEARANCE"),
	DRIVER_APPLICATION(18, "DRIVERS LICENSE APPLICATION"),
	DRIVER_RENEWAL(19, "DRIVERS LICENSE RENEWAL"),
	FIREARMS_APPLICATION(20, "FIREARMS LICENSE APPLICATION"),
	FIREARMS_RENEWAL(21, "FIREARMS LICENSE APPLICATION"),
	SKYLAB(22, "SKYLAB FRANCHISE"),
	TRAVEL(23, "TRAVEL ABROAD"),
	
	LARGE_CATTLE(24, "LARGE CATTLE CERTIFICATION"),
	INDIGENT_ASSISTANCE(25, "INDIGENT ASSISTANCE"),
	LATE_BIRTH_REGISTRATION(26, "LATE REGISTRATION OF LIVE BIRTH"),
	
	MOTORCYCLE(27, "MOTORCYCLE FRANCHISE"),
	JEEPNEY(28, "JEEPNEY FRANCHISE"),
	
	VAN(29, "VAN FRANCHISE"),
	BUS(30, "BUS FRANCHISE"),
	GOOD_MORAL(31, "GOOD MORAL CERTIFICATION"),
	OJT(32, "ON JOB TRAINING"),
	INDIGENT_CERT(33, "INDIGENT CERTIFICATION"),
	INDIGENT_HOSPL_ASS(34, "INDIGENT HOSPITAL FINANCIAL ASSISTANCE"),
	DEATH_CERT(35, "REGISTRATION OF DEATH"),
	
	MOTORCYCLE_LOAN(36,"MOTORCYCLE LOAN"),
	BUSINESS_PERMIT(37, "BUSINESS PERMIT BARANGAY"),
	BUSINESS_CERTIFICATION(38, "BUSINESS CERTIFICATION"),
	CERTIFICATE_RESIDENCY(39, "PROOF OF RESIDENCY"),
	CERTIFICATE_EMPLOYMENT(40, "CERTIFICATE OF EMPLOYMENT"),
	LATE_DEATH_CERT(41,"LATE REGISTRATION OF DEATH"),
	
	SENIOR_CITIZEN_AUTHORIZATION_LETTER(42, "SENIOR CITIZEN AUTHORIZATION LETTER"),
	FORPS_AUTHORIZATION_LETTER(43, "4P's AUTHORIZATION LETTER"),
	FORPS_CERT_TRANS_ADD(44, "4P's CERTIFICATION FOR TRANSFERRED ADDRESS"),
	INDIGENT_ATTORNEYS_ASSISTANCE(45, "SEEKING LEGAL ASSISTANCE FROM THE PUBLIC ATTORNEYS OFFICE (PAO)"),
	DOCUMENTARY_STAMP(46,"TO SECURE DOCUMENTARY STAMP"),
	FISH_CAGE_RENEWAL(47,"FISH CAGE OPERATOR MUNICIPAL PERMIT RENEWAL"),
	BUSINESS_FOR_LOAN_REQUIREMENTS(48, "BUSINESS CERTIFICATION FOR LOAN REQUIREMENT"),
	EMPLOYMENT_ABROAD(49, "FOR EMPLOYMENT ABROAD"),
	PASSPORT(50, "TO SECURE PASSPORT"),
	MOTORCYCLE_LOAN_REQUIREMENT(51, "MOTORCYCLE LOAN REQUIREMENT"),
	CAR_LOAN(52, "CAR LOAN"),
	CAR_LOAN_REQUIREMENT(53, "CAR LOAN REQUIREMENT"),
	TO_TAKE_BOARD_LICENSE(54, "TO TAKE BOARD/LICENSE EXAMINATION"),
	FOR_INDIGENT_EMPLOYMENT(55, "FOR EMPLOYMENT"),
	FOR_INDIGENT_EDUCATION_SCHOLARSHIP_ASSISTANCE(56, "EDUCATION/SCHOLARSHIP ASSISTANCE"),
	REGISTRATION_OF_LIVE_BIRTH(57, "REGISTRATION OF LIVE BIRTH"),
	AFP_TRAINING(58, "AFP TRAINING"),
	MULTIPURPOSE(59, "MULTIPURPOSE"),
	SCHOOL_REQUIREMENTS(60, "SCHOOL REQUIREMENTS"),
	SPES_REQUIREMENTS(61, "SPES REQUIREMENTS"),
	MUNICIPAL_MOTORCYCLE_OPERATOR_APPLICATION(62, "MUNICIPAL MOTORCYCLE OPERATOR PERMIT APPLICATION"),
	MUNICIPAL_MOTORCYCLE_OPERATOR_RENEWAL(63, "MUNICIPAL MOTORCYCLE OPERATOR PERMIT RENEWAL"),
	LAND_OWNERSHIP(64, "LAND OWNERSHIP"),
	LOAN_REQUIREMENTS(65, "LOAN REQUIREMENTS"),
	SUMMER_JOB_REQUIREMENTS(66, "SUMMER JOB REQUIREMENTS"),
	
	CHED_SCHOLARSHIP(67, "CHED SCHOLARSHIP"),
	TESDA_REQUIREMENT(68, "TESDA REQUIREMENT"),
	KABUGWASON_REQUIREMENT(69, "KABUGWASON REQUIREMENT"),
	SCHOLARSHIP_APPLICATION(70, "SCHOLARSHIP APPLICATION"),
	CONFIRMATION_APPLICATION(71, "CERTIFICATE OF CONFIRMATION APPLICATION"),
	
	DIRECT_SELLER_AGENT_APPLICATION(72, "DIRECT SELLER AGENT APPLICATION"),
	
	SCHOLARSHIP_REQUIREMENTS(73, "SCHOLARSHIP REQUIREMENTS"),
	ESC_SCHOLARSHIP_REQUIREMENTS(74, "EDUCATION SERVICE CONTRACT (ESC) SCHOLARSHIP REQUIREMENT"),
	
	TREE_OWNERSHIP(75, "TREE OWNERSHIP"),
	OWWA(76, "OWWA REQUIREMENTS"),
	SCHOOL_REG(77, "SCHOOL REGISTRATION"),
	RETIREMENT_BUSINESS(78,"RETIREMENT OF BUSINESS"),
	SOLO_PARENT(79,"SOLO PARENT"),
	
	CERTIFY_PROOF_IDENTITY(80, "CERTIFY PROOF OF IDENTITY"), 
	
	PHILHEALTH(81, "PHILHEALTH REQUIREMENTS"),
	LAND_ASSESSTMENT(82, "LAND ASSESSMENT"),
	WEDDING_REQ(83, "WEDDING REQUIREMENTS"),
	
	PAG_IBIG_LOAN_REQUIREMENT(84, "PAG-BIG LOAN REQUIREMENT"),
	SSS_LOAN_REQUIREMENT(85, "SSS LOAN REQUIREMENT"),
	PAG_IBIG_LOAN(86, "PAG-BIG LOAN"),
	SSS_LOAN(87, "SSS LOAN"),
	TRIBAL_LAND_CERTIFICATION(88, "TRIBAL LAND CERTIFICATION"),
	
	BANK_REQUIREMENT(89, "BANK REQUIREMENT"),
	PNP_APPLICATION(90, "PNP APPLICATION"),
	
	TES_APPLICATION(91, "TES Application"),
	
	SOCOTECO_APPLICATION(92, "SOCOTECO APPLICATION"),
	SECURING_PERMIT_FIRECRACKERS(93,"SECURING PERMIT TO DISPLAY AND SELLING FIRE CRACKERS"),
	BALIK_PROBINSYA_PROGRAM(94, "BALIK PROBINSYA PROGRAM"),
	APPLIANCE_LOAN(95,"APPLIANCE LOAN"),
	APPLIANCE_LOAN_REQUIREMENT(96,"APPLIANCE LOAN REQUIREMENT"),
	PHILHEALTH_TRANSACTION_INDIGENT(97,"PHILHEALTH TRANSACTION"),
	FOOD_INDIGENT_ASSISTANCE(98,"FOOD ASSISTANCE"),
	TO_SEEK_PERSONAL_MEDICAL_ASSISTANCE(99,"TO SEEK PERSONAL MEDICAL ASSISTANCE"),
	TO_SEEK_MEDICAL_ASSISTANCE_OPD(100,"TO SEEK MEDICAL ASSISTANCE (OPD)"),
	BUSINESS_LOAN_BANK_REQUIREMENTS(101, "BUSINESS LOAN FOR BANK REQUIREMENT");
	
	private int id;
	private String name;
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	private Purpose(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public static String typeName(int id){
		for(Purpose type : Purpose.values()){
			if(id==type.getId()){
				return type.getName();
			}
		}
		return Purpose.OTHER_LEGAL_MATTERS.getName();
	}
	public static int typeId(String name){
		for(Purpose type : Purpose.values()){
			if(name.equalsIgnoreCase(type.getName())){
				return type.getId();
			}
		}
		return Purpose.OTHER_LEGAL_MATTERS.getId();
	}
	
	
}
