package com.italia.marxmind.bris.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.italia.marxmind.bris.controller.BCard;
import com.italia.marxmind.bris.controller.Cases;
import com.italia.marxmind.bris.controller.Clearance;
import com.italia.marxmind.bris.controller.Customer;
import com.italia.marxmind.bris.controller.Livelihood;
import com.italia.marxmind.bris.enm.CaseKind;
import com.italia.marxmind.bris.enm.CaseStatus;
import com.italia.marxmind.bris.enm.DateFormat;
import com.italia.marxmind.bris.enm.DocTypes;
import com.italia.marxmind.bris.enm.Purpose;
import com.italia.marxmind.bris.reports.Reports;
import com.italia.marxmind.bris.utils.DateUtils;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

/**
 * 
 * @author mark italia
 * @since 04/05/2018
 * @version 1.0
 *
 */
@Named("rptBean")
@ViewScoped
@Data
public class ReportGeneratorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 677898454546731L;

	
	private List<Reports> rpts;
	private Date dateFrom;
	private Date dateTo;
	
	private boolean detailedClerance;
	private boolean includeClearance;
	private boolean detailedIds;
	private boolean includeIds;
	private boolean includeIdHolderName;
	private boolean includeCases;
	private boolean detailedCases;
	private boolean includeDispenseCheck;
	private boolean detailedCheck;
	private boolean includeIra;
	private boolean detailedIra;
	private boolean includeMOOE;
	private boolean detailedMooe;
	private boolean includeIncome;
	private boolean detailedIncome;
	private boolean includeBusiness;
	private boolean detailedBusiness;
	private boolean includeFishcages;
	private boolean detailedFishcages;
	private boolean includeRegistedPerson;
	private boolean detailedRegPerson;
	private boolean includeCivilStatus;
	private boolean detailedCivilStatus;
	private boolean includeAgeBracket;
	private boolean detailedAgeBracker;
	private boolean includePerPurok;
	private boolean detailedPurok;
	
	@PostConstruct
	public void init() {
		rpts = new ArrayList<Reports>();
		if(dateFrom==null){
			String date = DateUtils.getCurrentYear() + "-" + (DateUtils.getCurrentMonth()>=10? DateUtils.getCurrentMonth() : "0" + DateUtils.getCurrentMonth()) + "-01";
			dateFrom = DateUtils.convertDateString(date, DateFormat.YYYY_MM_DD());
		}
		if(dateTo==null){
			String date = DateUtils.getLastDayOfTheMonth("yyyy-MM-dd", DateUtils.getCurrentDateYYYYMMDD(), Locale.TAIWAN);
			dateTo = DateUtils.convertDateString(date, DateFormat.YYYY_MM_DD());
		}
	}
	
	public void clearFlds(){
		detailedClerance=false;
		includeClearance=false;
		detailedIds=false;
		includeIds=false;
		includeIdHolderName=false;
		includeCases=false;
		detailedCases=false;
		includeDispenseCheck=false;
		detailedCheck=false;
		includeIra=false;
		detailedIra=false;
		includeMOOE=false;
		detailedMooe=false;
		includeIncome=false;
		detailedIncome=false;
		includeBusiness=false;
		detailedBusiness=false;
		includeFishcages=false;
		detailedFishcages=false;
		includeRegistedPerson=false;
		detailedRegPerson=false;
		includeCivilStatus=false;
		detailedCivilStatus=false;
		includeAgeBracket=false;
		detailedAgeBracker=false;
		includePerPurok=false;
		detailedPurok=false;
	}
	
	public void loadReport(){
		
		rpts = Collections.synchronizedList(new ArrayList<Reports>());
		
		//setIncludeClearance(false);
		//setDetailedClerance(true);
		if(isIncludeClearance()){
			
			Reports rpt = new Reports();
			rpt.setF1("CLEARANCE/CERTIFICATE");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
			clearanceInfo();
			rpt = new Reports();
			rpt.setF1("");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
		}
		
		//setIncludeIds(false);
		//setDetailedIds(true);
		//setIncludeIdHolderName(true);
		if(isIncludeIds()){
			Reports rpt = new Reports();
			rpt.setF1("ISSUED IDs");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
			idsInfo();
			rpt = new Reports();
			rpt.setF1("");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
		}
		
		//setIncludeCases(true);
		//setDetailedCases(true);
		if(isIncludeCases()){
			Reports rpt = new Reports();
			rpt.setF1("RECORDED CASES");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
			casesInfo();
			rpt = new Reports();
			rpt.setF1("");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
		}
		
		if(isIncludeBusiness()){
			Reports rpt = new Reports();
			rpt.setF1("BUSINESS");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
			businessInfo();
			rpt = new Reports();
			rpt.setF1("");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("");
			rpts.add(rpt);
		}
		
	}
	
	private void businessInfo(){
		String[] params = new String[0];
		String sql = " AND live.isactivelive=1 AND live.livelihoodtype!=1";// AND (live.casedate>=? AND live.casedate<=?) ";
		//params[0] = DateUtils.convertDate(getDateFrom(), DateFormat.YYYY_MM_DD());
		//params[1] = DateUtils.convertDate(getDateTo(), DateFormat.YYYY_MM_DD());
		
		List<Livelihood> biz = Livelihood.retrieve(sql, params);
		
		if(isDetailedBusiness()){
			
			
			
		}else{
			
		}
		
		
	}
	
	private void casesInfo(){
		String sql = " AND ciz.caseisactive=1 AND (ciz.casedate>=? AND ciz.casedate<=?) ";
		String[] params = new String[2];
		params[0] = DateUtils.convertDate(getDateFrom(), DateFormat.YYYY_MM_DD());
		params[1] = DateUtils.convertDate(getDateTo(), DateFormat.YYYY_MM_DD());
		
		List<Cases> cases = Cases.retrieve(sql, params);
		
		
		
		
		if(isDetailedCases()){
			
			List<Cases> czs = Collections.synchronizedList(new ArrayList<Cases>());
			Map<Integer, List<Cases>> mapStatus = Collections.synchronizedMap(new HashMap<Integer, List<Cases>>());
			Map<String, Map<Integer, List<Cases>>> mapCases = Collections.synchronizedMap(new HashMap<String, Map<Integer, List<Cases>>>());
			
			for(Cases cz : cases){
				String type = CaseKind.typeName(cz.getKind());
				if(CaseKind.OTHERS.getId()==cz.getKind()){
					type = cz.getOtherCaseName();
				}
				int status = cz.getStatus();
				
				if(mapCases!=null && mapCases.size()>0){
					
					if(mapCases.containsKey(type)){
						
						if(mapCases.get(type).containsKey(status)){
							mapCases.get(type).get(status).add(cz);
						}else{
							czs = Collections.synchronizedList(new ArrayList<Cases>());
							czs.add(cz);
							mapCases.get(type).put(status, czs);
						}
						
					}else{
						
						czs = Collections.synchronizedList(new ArrayList<Cases>());
						mapStatus = Collections.synchronizedMap(new HashMap<Integer, List<Cases>>());
						
						czs.add(cz);
						mapStatus.put(status, czs);
						mapCases.put(type, mapStatus);
						
					}
					
				}else{
					czs.add(cz);
					mapStatus.put(status, czs);
					mapCases.put(type, mapStatus);
				}
				
			}
			
			Reports rpt = new Reports();
			rpt.setF1("KIND");
			rpt.setF2("STATUS");
			rpt.setF3("INVOLVED PERSON/S");
			rpt.setF4("TOTAL");
			rpts.add(rpt);
			
			int grandTotal = 0;
			for(String type : mapCases.keySet()){
				int cnt = 0;
				rpt = new Reports();
				rpt.setF1(type);
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("");
				rpts.add(rpt);
				for(int stat : mapCases.get(type).keySet()){
					rpt = new Reports();
					rpt.setF1("");
					rpt.setF2(CaseStatus.typeName(stat));
					rpt.setF3("");
					rpt.setF4("");
					rpts.add(rpt);
					for(Cases c : mapCases.get(type).get(stat)){
						rpt = new Reports();
						rpt.setF1("");
						rpt.setF2("");
						rpt.setF3(c.getComplainants() + " vs " + c.getRespondents());
						rpt.setF4("");
						rpts.add(rpt);
						cnt +=1;
					}
				}
				rpt = new Reports();
				rpt.setF1("");
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("" + cnt);
				rpts.add(rpt);
				grandTotal += cnt;
			}
			rpt = new Reports();
			rpt.setF1("Grand Total");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("" + grandTotal);
			rpts.add(rpt);
			
			
		}else{
			List<Cases> czs = Collections.synchronizedList(new ArrayList<Cases>());
			Map<String, List<Cases>> mapCases = Collections.synchronizedMap(new HashMap<String, List<Cases>>());
			for(Cases cz : cases){
				String type = CaseKind.typeName(cz.getKind());
				if(CaseKind.OTHERS.getId()==cz.getKind()){
					type = cz.getOtherCaseName();
				}
				
				if(mapCases!=null && mapCases.size()>0){
					
					if(mapCases.containsKey(type)){
						mapCases.get(type).add(cz);
					}else{
						czs = Collections.synchronizedList(new ArrayList<Cases>());
						czs.add(cz);
						mapCases.put(type, czs);
					}
					
				}else{
					
					czs.add(cz);
					mapCases.put(type, czs);
					
				}
				
				
			}
			
			Reports rpt = new Reports();
			rpt.setF1("KIND");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("TOTAL");
			rpts.add(rpt);
			
			int cnt = 0;
			for(String type : mapCases.keySet()){
				
				rpt = new Reports();
				rpt.setF1(type);
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("" + mapCases.get(type).size());
				rpts.add(rpt);
				cnt += mapCases.get(type).size();
			}
			
			rpt = new Reports();
			rpt.setF1("GRAND TOTAL");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4(""+cnt);
			rpts.add(rpt);
			
		}
		
		
		
	}
	
	private void idsInfo(){
		
		String sql = " AND crd.isactivebid=1 AND (crd.datetrans>=? AND crd.datetrans<=?) ";
		String[] params = new String[2];
		params[0] = DateUtils.convertDate(getDateFrom(), DateFormat.YYYY_MM_DD());
		params[1] = DateUtils.convertDate(getDateTo(), DateFormat.YYYY_MM_DD());
		
		List<BCard> cards= BCard.retrieve(sql, params);
		
		if(isDetailedIds()){
			List<BCard> cardList = Collections.synchronizedList(new ArrayList<BCard>());
			Map<Long, List<BCard>> mapPur = Collections.synchronizedMap(new HashMap<Long, List<BCard>>());
			//Map<Long, Map<Long, List<BCard>>> mapCard = Collections.synchronizedMap(new HashMap<Long, Map<Long, List<BCard>>>());
			for(BCard card : cards){
				card.setTaxpayer(Customer.retrieve(card.getTaxpayer().getCustomerid()));
				long idPurok = card.getTaxpayer().getPurok().getId();
				if(mapPur!=null && mapPur.size()>0){
					
					if(mapPur.containsKey(idPurok)){
						
						//cardList = mapPur.get(idPurok);
						mapPur.get(idPurok).add(card);
						
					}else{
						
						cardList = Collections.synchronizedList(new ArrayList<BCard>());
						//mapPur = Collections.synchronizedMap(new HashMap<Long, List<BCard>>());
						cardList.add(card);
						mapPur.put(idPurok, cardList);
						//mapCard.put(idPurok, mapPur);
					}
					
				}else{
					cardList.add(card);
					mapPur.put(idPurok, cardList);
					//mapCard.put(idPurok, mapPur);
				}
			}
			
			Reports rpt = new Reports();
			rpt.setF1("PUROK/SITIO/ZONE");
			rpt.setF2("ID");
			rpt.setF3("NAME");
			rpt.setF4("TOTAL");
			rpts.add(rpt);
			int grandTotal = 0;
			for(Long purId : mapPur.keySet()){
				rpt = new Reports();
				rpt.setF1(mapPur.get(purId).get(0).getTaxpayer().getPurok().getPurokName());
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("");
				rpts.add(rpt);
				int cnt = 0;
				if(isIncludeIdHolderName()){
					for(BCard c  : mapPur.get(purId)){
						rpt = new Reports();
						rpt.setF1("");
						rpt.setF2(c.getTaxpayer().getCardno());
						rpt.setF3(c.getTaxpayer().getFullname());
						rpt.setF4("");
						rpts.add(rpt);
						cnt += 1;
					}
				}else{
					cnt = mapPur.get(purId).size();
				}
				rpt = new Reports();
				rpt.setF1("TOTAL");
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4(""+ cnt);
				rpts.add(rpt);
				grandTotal += cnt;
			}
			
			rpt = new Reports();
			rpt.setF1("GRAND TOTAL");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4(""+ grandTotal);
			rpts.add(rpt);
			
		}else{
			
				Reports rpt = new Reports();
				rpt.setF1("TOTAL ISSUED ID's");
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4(""+ cards.size());
				rpts.add(rpt);
			
		}
		
		
	}
	
	private void clearanceInfo(){
		
		String sql = " AND clz.isactiveclearance=1 AND (clz.clearissueddate>=? AND clz.clearissueddate<=?) ";
		String[] params = new String[2];
		params[0] = DateUtils.convertDate(getDateFrom(), DateFormat.YYYY_MM_DD());
		params[1] = DateUtils.convertDate(getDateTo(), DateFormat.YYYY_MM_DD());
		
		List<Clearance> clrs = Clearance.retrieve(sql, params);
		
		//clearance
		List<Clearance>  mapClr = Collections.synchronizedList(new ArrayList<Clearance>());
		//purpose
		Map<Integer, List<Clearance>> mapPurpose = Collections.synchronizedMap(new HashMap<Integer, List<Clearance>>());
		//clearance type
		Map<Integer, Map<Integer,List<Clearance>>> mapDocs = Collections.synchronizedMap(new HashMap<Integer,Map<Integer,List<Clearance>>>());
		
		if(isDetailedClerance()){
			
			for(Clearance cl : clrs){
				
				if(DocTypes.CERTIFICATE_OPEN_TITLE.getId()==cl.getDocumentType()){
					cl.setDocumentType(DocTypes.CERTIFICATE.getId());
				}else if(DocTypes.CLEARANCE_OPEN_TITLE.getId()==cl.getDocumentType()){
					cl.setDocumentType(DocTypes.CLEARANCE.getId());
				}
				
				if(mapDocs!=null){
					
					if(mapDocs.containsKey(cl.getDocumentType())){
						
						if(mapDocs.get(cl.getDocumentType()).containsKey(cl.getPurposeType())){
							
							mapClr = mapDocs.get(cl.getDocumentType()).get(cl.getPurposeType());
							mapClr.add(cl);
							mapDocs.get(cl.getDocumentType()).put(cl.getPurposeType(), mapClr);
							
							
						}else{
							
							mapClr = Collections.synchronizedList(new ArrayList<Clearance>());
							//mapPurpose = Collections.synchronizedMap(new HashMap<Integer, List<Clearance>>());
							mapClr.add(cl);
							mapDocs.get(cl.getDocumentType()).put(cl.getPurposeType(), mapClr);
							
						}
						
					}else{
						
						mapClr = Collections.synchronizedList(new ArrayList<Clearance>());
						mapPurpose = Collections.synchronizedMap(new HashMap<Integer, List<Clearance>>());
						
						mapClr.add(cl);
						mapPurpose.put(cl.getPurposeType(), mapClr);
						mapDocs.put(cl.getDocumentType(), mapPurpose);
						
					}
					
				}else{
					
					mapClr.add(cl);
					mapPurpose.put(cl.getPurposeType(), mapClr);
					mapDocs.put(cl.getDocumentType(), mapPurpose);
					
				}
				
			}
			
			Reports rpt = new Reports();
			rpt.setF1("DOCUMENT");
			rpt.setF2("PURPOSE");
			rpt.setF3("");
			rpt.setF4("TOTAL");
			rpts.add(rpt);
			int grandTotal = 0;
			for(Integer docId : mapDocs.keySet()){
				rpt = new Reports();
				rpt.setF1(DocTypes.typeName(docId));
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("");
				rpts.add(rpt);
				int cnt = 0;
				for(Integer purId : mapDocs.get(docId).keySet()){
					rpt = new Reports();
					rpt.setF1("");
					rpt.setF2(Purpose.typeName(purId));
					rpt.setF3("");
					rpt.setF4("" + mapDocs.get(docId).get(purId).size());
					rpts.add(rpt);
					cnt += mapDocs.get(docId).get(purId).size();
				}
				rpt = new Reports();
				rpt.setF1("TOTAL ISSUED");
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("" + cnt);
				rpts.add(rpt);
				grandTotal += cnt;
			}
			
			rpt = new Reports();
			rpt.setF1("GRAND TOTAL");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4(""+grandTotal);
			rpts.add(rpt);
						
		}else{
			
			List<Clearance> clr = Collections.synchronizedList(new ArrayList<Clearance>());
			Map<Integer, List<Clearance>> mapDoc = Collections.synchronizedMap(new HashMap<Integer,List<Clearance>>());
			
			for(Clearance cl : clrs){
				if(DocTypes.CERTIFICATE_OPEN_TITLE.getId()==cl.getDocumentType()){
					cl.setDocumentType(DocTypes.CERTIFICATE.getId());
				}else if(DocTypes.CLEARANCE_OPEN_TITLE.getId()==cl.getDocumentType()){
					cl.setDocumentType(DocTypes.CLEARANCE.getId());
				}
				
				if(mapDoc!=null){
					
					if(mapDoc.containsKey(cl.getDocumentType())){
						
						mapDoc.get(cl.getDocumentType()).add(cl);
						
					}else{
						clr = Collections.synchronizedList(new ArrayList<Clearance>());
						clr.add(cl);
						mapDoc.put(cl.getDocumentType(), clr);
					}
					
				}else{
					clr.add(cl);
					mapDoc.put(cl.getDocumentType(), clr);
				}
				
			}
			
			Reports rpt = new Reports();
			rpt.setF1("DOCUMENT");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4("TOTAL");
			rpts.add(rpt);
			int grandTotal= 0;
			for(Integer type : mapDoc.keySet()){
				
				rpt = new Reports();
				rpt.setF1(DocTypes.typeName(type));
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4("");
				rpts.add(rpt);
				
				rpt = new Reports();
				rpt.setF1("TOTAL ISSUED");
				rpt.setF2("");
				rpt.setF3("");
				rpt.setF4(""+ mapDoc.get(type).size());
				rpts.add(rpt);
				
				grandTotal += mapDoc.get(type).size();
			}
			
			rpt = new Reports();
			rpt.setF1("GRAND TOTAL");
			rpt.setF2("");
			rpt.setF3("");
			rpt.setF4(""+grandTotal);
			rpts.add(rpt);
			/*for(Reports r : rpts){
				System.out.println(r.getF1());
			}*/
			
			
		}
		
		
	}
	
}