/*
 * Created on Jun 6, 2005
 *
 */
package com.metamorf.eform.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eRyan
 *
 */
public interface IAuditTrailConstant {
	
	public static Map<String, String> activityMap = new HashMap<String, String>();
	public static Map<String, String> activityDescriptionMap = new HashMap<String, String>();
	
    boolean ENABLE_LOG_AUDIT_TRAIL = true;

    String LOG_SUCCESS= "SUCCESS";
    String LOG_FAIL= "FAIL";
    
    final String ERROR_CONSTRUCTING_EVENT = "System failed to construct event of this action.";
    
    // ACTIVITY CODE FOR BACK OFFICE
	public String VMDA = "VMDA";
	public String VNOT = "VNOT";
	public String ENOT = "ENOT";
	public String ANOT = "ANOT";
	public String RNOT = "RNOT";
	public String RRNOT = "RRNOT";
	public String RENOT = "RENOT";
	public String VCUL = "VCUL";
	public String CCUL = "CCUL";
	public String ECUL = "ECUL";
	public String SUUL = "SUUL";
	public String ACUL = "ACUL";
	public String RCUL = "RCUL";
	public String RRCUL = "RRCUL";
	public String RECUL = "RECUL";
	public String VCUDL = "VCUDL";
	public String CCUDL = "CCUDL";
	public String ECUDL = "ECUDL";
	public String SUCUDL = "SUCUDL";
	public String ACUDL = "ACUDL";
	public String RCUDL = "RCUDL";
	public String RRCUDL = "RRCUDL";
	public String RECUDL = "RECUDL";
	public String ACUC = "ACUC";
	public String ABUC = "ABUC";
	public String ROFSMEM = "ROFSMEM";
	public String RROFSMEM = "RROFSMEM";
	public String REOFSMEM = "REOFSMEM";
	public String VTOL = "VTOL";
	public String STOL = "STOL";
	public String VTOLH = "VTOLH";
	public String ATOL = "ATOL";
	public String RTOL = "RTOL";
	public String RRTOL = "RRTOL";
	public String RETOL = "RETOL";
	public String VRPS = "VRPS";
	public String VPPF = "VPPF";
	public String RPPF = "RPPF";
	
	// ACTIVITY CODE FOR FRONT OFFICE
	public String VPOA = "VPOA";
	public String VPOR = "VPOR";
	public String VINA = "VINA";
	public String VINR = "VINR";
	public String VDNA = "VDNA";
	public String VDNR = "VDNR";
	public String VCNA = "VCNA";
	public String VCNR = "VCNR";
	public String VDIDV = "VDIDV";
	public String VDIDVA = "VDIDVA";
	public String VDISR = "VDISR";
	public String VDISRA = "VDISRA";
	public String VDIRE = "VDIRE";
	public String VSEAP = "VSEAP";
	public String VSERR = "VSERR";
	public String VDOR = "VDOR";
	public String RDOR = "RDOR";
	public String VDRA = "VDRA";
	public String RDRA = "RDRA";
	
	//balance inquiry
	public String VBI = "VBI"; //front
	public String VBIN = "VBIN"; //back
	
	//limit inquiry
	public String VLI = "VLI"; //front
	public String VLIN = "VLIN"; //back
	
	public String VACG = "VACG";
	public String CACG = "CACG";
	public String VUSR = "VUSR"; //This is View User in Front Office???
	public String VAPM = "VAPM"; //This is View Approval Matrix in Front Office???
	public String VLOT = "VLOT";
	public String CLOT = "CLOT";
	public String ELOT = "ELOT";
	public String DLOT = "DLOT";
	public String CPLOT = "CPLOT";
	public String UPLOT = "UPLOT";
	public String CPOLOT = "CPOLOT";
	public String APO = "APO";
	public String RPO = "RPO";
	public String RRPO = "RRPO";
	public String REPO = "REPO";
	public String VDEL = "VDEL";
	public String CDEL = "CDEL";
	public String EDEL = "EDEL";
	public String DDEL = "DDEL";
	public String UPDEL = "UPDEL";
	public String CGRDO = "CGRDO";
	public String VGOR = "VGOR";
	public String CGOR = "CGOR";
	public String EGOR = "EGOR";
	public String DGOR = "DGOR";
	public String UPGOR = "UPGOR";
	public String CINVGR = "CINVGR";
	public String AINV = "AINV";
	public String RINV = "RINV";
	public String RRINV = "RRINV";
	public String REINV = "REINV";
	public String ATTDNINV = "ATTDNINV";
	public String ATTCNINV = "ATTCNINV";
	public String CDPO = "CDPO";
	public String UPDIS = "UPDIS";
	public String ADDV = "ADDV";
	public String RDDV = "RDDV";
	public String RRDDV = "RRDDV";
	public String ADDVA = "ADDVA";
	public String RDDVA = "RDDVA";
	public String RRDDVA = "RRDDVA";
	public String ADSR = "ADSR";
	public String RDSR = "RDSR";
	public String RRDSR = "RRDSR";
	public String ADSRA = "ADSRA";
	public String RDSRA = "RDSRA";
	public String RRDSRA = "RRDSRA";
	public String ADRE = "ADRE";
	public String RDRE = "RDRE";
	public String RRDRE = "RRDRE";
	public String ASEA = "ASEA";
	public String RSEA = "RSEA";
	public String RRSEA = "RRSEA";
	public String RRSER = "RRSER"; //no request repair for settlement release for now
	public String RRSERA = "RRSERA"; //no request repair for settlement release approval for now
	public String RESET = "RESET";
	public String VRPAY = "VRPAY";
	
	public String AETSA = "AETSA"; //approve extend tenor approve
	public String RETSA = "RETSA"; //reject extend tenor approve
	public String RRETSA = "RRETSA";
	public String VSETSA = "VSETSA";
	public String RETRR = "RETRR";
	public String VETRR = "VETRR";
	
    /* Audit trail message level */
    String LOG_LEVEL_INFO  = "INFO";
    String LOG_LEVEL_ERROR = "ERROR"; // Exception occur
    String LOG_LEVEL_DEBUG = "DEBUG";
    String LOG_LEVEL_WARN  = "WARNING";

	public String BLGN = "BLGN";
	public String BLGT = "BLGT";
	public String FLGN = "FLGN";
	public String FLGT = "FLGT";
	public String CHAP = "CHAP";
	
	/* START SYSTEM PARAMETERS AUDIT TRAIL CODE*/
	public String VSYP = "VSYP";
	public String ESYP = "ESYP";
	public String ASYP = "ASYP";
	public String RSYP = "RSYP";
	public String RRSYP = "RRSYP";
	public String RESYP = "RESYP";
	/*END SYSTEM PARAMETERS AUDIT TRAIL CODE*/
	
	/* START MINIMUM TRANSACTION AMOUNT AUDIT TRAIL CODE*/
	public String VTAC = "VTAC";
	public String ETAC = "ETAC";
	public String ATAC = "ATAC";
	public String RTAC = "RTAC";
	public String RRTAC = "RRTAC";
	public String RETAC = "RETAC";
	/*END MINIMUM TRANSACTION AMOUNT AUDIT TRAIL CODE*/
	
	/*START REFERENCE AUDIT TRAIL CODE*/
	public String VREF = "VREF";
	public String CREF = "CREF";
	public String EREF = "EREF";
	public String AREF = "AREF";
	public String RREF = "RREF";
	public String RRREF = "RRREF";
	public String REREF = "REREF";
	public String SUREF = "SUREF";
	/*END REFERENCE AUDIT TRAIL CODE*/
	
	/*START RISK PROFILER AUDIT TRAIL CODE*/
	public String VRISK = "VRISK";
	public String ERISK = "ERISK";
	/*END RISK PROFILER AUDIT TRAIL CODE*/
	
	/*START TASK LIST AUDIT TRAIL CODE*/
	public String VTL = "VTL";
	public String ETL = "ETL";
	
	/*START REGION AUDIT TRAIL CODE*/
	public String VREG = "VREG";
	public String CREG = "CREG";
	public String EREG = "EREG";
	public String AREG = "AREG";
	public String RREG = "RREG";
	public String RRREG = "RRREG";
	public String REREG = "REREG";
	public String SUREG = "SUREG";
	/*END REGION AUDIT TRAIL CODE*/
	
	/*START AREA AUDIT TRAIL CODE*/
	public String VARE = "VARE";
	public String CARE = "CARE";
	public String EARE = "EARE";
	public String AARE = "AARE";
	public String RARE = "RARE";
	public String RRARE = "RRARE";
	public String REARE = "REARE";
	public String SUARE = "SUARE";
	/*END AREA AUDIT TRAIL CODE*/
	
	/*START BRANCH LOCATION AUDIT TRAIL CODE*/
	public String VBRA = "VBRA";
	public String CBRA = "CBRA";
	public String EBRA = "EBRA";
	public String ABRA = "ABRA";
	public String RBRA = "RBRA";
	public String RRBRA = "RRBRA";
	public String REBRA = "REBRA";
	public String SUBRA = "SUBRA";
	/*END BRANCH LOCATION AUDIT TRAIL CODE*/
	
	/*START WORKFLOW PARAMETER AUDIT TRAIL CODE*/
	public String VWP = "VWP";
	public String EWP = "EWP";
	public String AWP = "AWP";
	public String RWP = "RWP";
	public String RRWP = "RRWP";
	public String REWP = "REWP";
	/*END WORKFLOW PARAMETER AUDIT TRAIL CODE*/
	
	/*START VERIFIER AUDIT TRAIL CODE*/
	public String VVER = "VVER";
	public String CVER = "CVER";
	public String EVER = "EVER";
	public String AVER = "AVER";
	public String RVER = "RVER";
	public String RRVER = "RRVER";
	public String REVER = "REVER";
	public String SUVER = "SUVER";
	public String PVER = "PVER";
	public String SDVER = "SDEVER";
	public String SOVER = "SOVER";
	public String SLVER = "SLEFA";
	/*END VERIFIER AUDIT TRAIL CODE*/
	
	/*START EFORM APPROVAL AUDIT TRAIL CODE*/
	public String VEFA = "VEFA";
	public String AEFA = "AEFA";
	public String REFA = "REFA";
	public String PEFA = "PEFA";
	public String SDEFA = "SDEEFA";
	public String SOEFA = "SOEFA";
	public String SLEFA = "SLEFA";
	/*END EFORM APPROVAL AUDIT TRAIL CODE*/
	
	/*START DEVIASI AUDIT TRAIL CODE*/
	public String VEDV = "VEDV";
	public String AEDV = "AEDV";
	public String REDV = "REDV";
	public String PEDV = "PEDV";
	public String SDEDV = "SDEDV";
	public String SOEDV = "SOEDV";
	public String SLEDV = "SLEDV";
	/*END DEVIASI AUDIT TRAIL CODE*/
	
	/*START TBO MAINTANCE AUDIT TRAIL CODE*/
	public String VTM = "VTM";
	public String RTM = "RTM";
	/*END TBO MAINTANCE AUDIT TRAIL CODE*/
	
	/*START CALLBACK AUDIT TRAIL CODE*/
	public String VCB = "VCB";
	public String WNCB = "WNCB";
	public String NACB = "NACB";
	public String FCB = "FCB";
	public String CCB = "CCB";
	/*END CALLBACK AUDIT TRAIL CODE*/
	
	/*START EDD APPROVAL AUDIT TRAIL CODE*/
	public String VEDD = "VEDD";
	public String CEDD = "CEDD";
	public String EEDD = "EEDD";
	public String AEDD = "AEDD";
	public String REDD = "REDD";
	public String RREDD = "RREDD";
	public String REEDD = "REEDD";
	public String SUEDD = "SUEDD";
	public String PEDD = "PEDD";
	public String SDEDD = "SDEEDD";
	public String SOEDD = "SOEDD";
	public String SLEDD = "SLEDD";
	/*END EDD APPROVAL AUDIT TRAIL CODE*/

	/*START PRE_EDD APPROVAL AUDIT TRAIL CODE*/
	public String PVEDD = "PVEDD";
	public String PCEDD = "PCEDD";
	public String PEEDD = "PEEDD";
	public String PAEDD = "PAEDD";
	public String PREDD = "PREDD";
	public String PRREDD = "PRREDD";
	public String PREEDD = "PREEDD";
	public String PSUEDD = "PSUEDD";
	public String PPEDD = "PPEDD";
	public String PSDEDD = "PSDEEDD";
	public String PSOEDD = "PSOEDD";
	public String PSLEDD = "PSLEDD";
	/*END EDD APPROVAL AUDIT TRAIL CODE*/
	
	
	/*START CONTENT MANAGER AUDIT TRAIL CODE*/
	public String VCM = "VCM";
	public String CCM = "CCM";
	public String ECM = "ECM";
	public String ACM = "ACM";
	public String RCM = "RCM";
	public String RRCM = "RRCM";
	public String RECM = "RECM";
	public String SUCM = "SUCM";
	/*END CONTENT MANAGER AUDIT TRAIL CODE*/
	
	/*START INTERNAL USERS AUDIT TRAIL CODE*/
	public String VBU = "VBU";
	public String CBU = "CBU";
	public String EINU = "EINU";
	public String SUBU = "SUBU";
	public String ABU = "ABU";
	public String RBU = "RBU";
	public String RRBU = "RRBU";
	public String REBU = "REBU";
	public String LOBU = "LOBU";
	public String ULBU = "ULBU";
	public String RPBU = "RPBU";
	public String FLBU = "FLBU";
	public String BULA = "BULA";
	public String BUUA = "BUUA";
	public String BURPA = "BURPA";
	public String DBU = "DBU";
	public String DBUA = "DBUA";
	public String DEBU = "DEBU";
	public String RIAU = "RIAU";
	/*END INTERNAL USERS AUDIT TRAIL CODE*/
	
	/*START ROLE AUDIT TRAIL CODE*/
	public String VROL = "VROL";
	public String CROL = "CROL";
	public String EROL = "EROL";
	public String DROL = "DROL";
	public String SUROL = "SUROL";
	public String AROL = "AROL";
	public String RROL = "RROL";
	public String RRROL = "RRROL";
	public String REROL = "REROL";
	/*END ROLE AUDIT TRAIL CODE*/
	
	/*START SECURITY PARAM AUDIT TRAIL CODE*/
	public String VSCP 	= "VSCP";
	public String ESCP 	= "ESCP";
	public String ASCP 	= "ASCP";
	public String RSCP 	= "RSCP";
	public String RRSCP = "RRSCP";
	public String RESCP = "RESCP";
	/*END SECURITY PARAM AUDIT TRAIL CODE*/
	
	/*START EXCHANGE RATE AUDIT TRAIL CODE*/
	public String VEXR 	= "VEXR";
	public String CEXR 	= "CEXR"; 
	public String EEXR 	= "EEXR";
	public String AEXR 	= "AEXR";
	public String REXR 	= "REXR";
	public String RREXR = "RREXR";
	public String REEXR = "REEXR";
	public String SUEXR = "SUEXR";
	/*END EXCHANGE RATE AUDIT TRAIL CODE*/
	
	/*START COMPANY USER AUDIT TRAIL CODE*/
	public String VCOU = "VCOU";
	public String CCOU = "CCOU";
	public String ECOU = "ECOU";
	public String SUCOU = "SUCOU";
	public String ACOU = "ACOU";
	public String RCOU = "RCOU";
	public String RRCOU = "RRCOU";
	public String RECOU = "RECOU";
	public String LOCOU = "LOCOU";
	public String ULCOU = "ULCOU";
	public String RPCOU = "RPCOU";
	public String FLCOU = "FLCOU";
	public String CULA = "CULA";
	public String CUUA = "CUUA";
	public String CURPA = "CURPA";
	public String CUD = "CUD";
	public String DCUA = "DCUA";
	/*END COMPANY AUDIT TRAIL CODE*/

	/*START COMPANY AUDIT TRAIL CODE*/
	public String VCOM = "VCOM";
	public String CCOM = "CCOM";
	public String ECOM = "ECOM";
	public String SUCOM = "SUCOM";
	public String ACOM = "ACOM";
	public String RCOM = "RCOM";
	public String RRCOM = "RRCOM";
	public String RECOM = "RECOM";
	/*END COMPANY USER AUDIT TRAIL CODE*/
	
	/*START COMPANY CHARGES AUDIT TRAIL CODE*/
	public String VCCHG = "VCCHG";
	public String ECCHG = "ECCHG";
	public String ACCHG = "ACCHG";
	public String RCCHG = "RCCHG";
	public String RRCCHG = "RRCCHG";
	public String RECCHG = "RECCHG";
	/*END COMPANY CHARGES AUDIT TRAIL CODE*/
	
	/*START BANK ACCOUNT AUDIT TRAIL CODE*/
	public String VCA = "VCA";
	public String CCA = "CCA";
	public String ECA = "ECA";
	public String SUCA = "SUCA";
	public String ACA = "ACA";
	public String RCA = "RCA";
	public String RRCA = "RRCA";
	public String RECA = "RECA";	
	/*END BANK ACCOUNT AUDIT TRAIL CODE*/
	
	/*START APPROVAL MATRIX AUDIT TRAIL CODE*/
	public String VCAM = "VCAM";
	public String CCAM = "CCAM";
	public String ECAM = "ECAM";
	public String SUCAM = "SUCAM";
	public String ACAM = "ACAM";
	public String RCAM = "RCAM";
	public String RRCAM = "RRCAM";
	public String RECAM = "RECAM";
	/*END APPROVAL MATRIX AUDIT TRAIL CODE*/
	
	/*START ROOT COMMUNITY AUDIT TRAIL CODE*/
	public String VROC = "VROC";
	public String CROC = "CROC";
	public String EROC = "EROC";
	public String SUROC = "SUROC";
	public String AROC = "AROC";
	public String RROC = "RROC";
	public String RRROC = "RRROC";
	public String REROC = "REROC";
	/*END ROOT COMMUNITY AUDIT TRAIL CODE*/
	
	/*START COMMUNITY AUDIT TRAIL CODE*/
	public String VCMU = "VCMU";
	public String CCMU = "CCMU";
	public String ECMU = "ECMU";
	public String SUCMU = "SUCMU";
	public String ACMU = "ACMU";
	public String RCMU = "RCMU";
	public String RRCMU = "RRCMU";
	public String RECMU = "RECMU";
	/*END COMMUNITY AUDIT TRAIL CODE*/
	
	/*START PREDEFINED BENEFICIARY AUDIT TRAIL CODE*/
	public String VPRB = "VPRB";
	public String CPRB = "CPRB";
	public String EPRB = "EPRB";
	public String SUPRB = "SUPRB";
	public String APRB = "APRB";
	public String RPRB = "RPRB";
	public String RRPRB = "RRPRB";
	public String REPRB = "REPRB";	
	/*END PREDEFINED BENEFICIARY AUDIT TRAIL CODE*/
	
	/*START DISBURSEMENT AUDIT TRAIL CODE*/
	public String CDINV = "CDINV";
	public String VDIAP = "VDIAP";
	public String ADAP = "ADAP";
	public String RDAP = "RDAP";
	public String RRDAP = "RRDAP";
	public String VDIBR = "VDIBR";
	public String ADBR = "ADBR";
	public String RDBR = "RDBR";
	public String RRDBR = "RRDBR";
	public String VDIBRA = "VDIBRA";
	public String ADBRA = "ADBRA";
	public String RDBRA = "RDBRA";
	public String RRDBRA = "RRDBRA";
	public String VDIRR = "VDIRR";
	public String REDIS = "REDIS";
	/*END DISBURSEMENT AUDIT TRAIL CODE*/
	
	/*START SETTLEMENT AUDIT TRAIL CODE*/
	public String VSEOF = "VSEOF"; //kata harini
	public String VSERE = "VSERE";
	public String ASER = "ASER";
	public String RSER = "RSER";
	public String VSEREA = "VSEREA";
	public String ASERA = "ASERA";
	public String RSERA = "RSERA";
	public String CSEOL = "CSEOL";
	
	public String VETSE = "VETSE";
	public String CETSE = "CETSE"; //create extend tenor
	public String AETSE = "AETSE";
	public String RETSE = "RETSE";
	public String VETSEA = "VETSEA";
	public String AETSEA = "AETSEA";
	public String RETSEA = "RETSEA";
	/*END SETTLEMENT AUDIT TRAIL CODE*/
	
	/*START PRINCIPAL AUDIT TRAIL CODE*/
	public String VPRI = "VPRI";
	public String CPRI = "CPRI";
	public String EPRI = "EPRI";
	public String SUPRI = "SUPRI";
	public String APRI = "APRI";
	public String RPRI = "RPRI";
	public String RRPRI = "RRPRI";	
	public String REPRI = "REPRI";	
	/*END PRINCIPAL AUDIT TRAIL CODE*/
	
	/*START MEMBER AUDIT TRAIL CODE*/
	public String VMEM = "VMEM";
	public String CMEM = "CMEM";
	public String EMEM = "EMEM";
	public String SUMEM = "SUMEM";
	public String AMEM = "AMEM";
	public String RMEM = "RMEM";
	public String RRMEM = "RRMEM";	
	public String REMEM = "REMEM";	
	/*END MEMBER AUDIT TRAIL CODE*/
	
	/*START FINANCING SCHEMA AUDIT TRAIL CODE*/
	public String VFSC = "VFSC";
	public String CFSC = "CFSC";
	public String EFSC = "EFSC";
	public String SUFSC = "SUFSC"; 
	/*END FINANCING SCHEMA AUDIT TRAIL CODE*/
	
	/*START OVERRIDE FINANCING SCHEME AUDIT TRAIL CODE*/
	public String OFSMEM = "OFSMEM";
	public String AOFSMEM = "AOFSMEM";
	/*END OVERRIDE FINANCING SCHEME AUDIT TRAIL CODE*/
	
	/*START REPORT AUDIT TRAIL CODE*/
	public String VRBAC = "VRBAC";
	public String VRBA = "VRBA";
	public String VRCHL = "VRCHL";
	public String VRCHS = "VRCHS";
	public String VRCHR = "VRCHR";
	public String VRCOM = "VRCOM";
	public String VRCOU = "VRCOU";
	public String VRCP = "VRCP";
	public String VRDHO = "VRDHO";
	public String VRDOC = "VRDOC";
	public String VRDTR = "VRDTR";
	public String VRADV = "VRADV";
	public String VRFPA = "VRFPA";
	public String VRFTR = "VRFTR";
	public String VRHC = "VRHC";
	public String VRACT = "VRACT";
	public String VRHOLFO = "VRHOLFO";
	public String VRLOA = "VRLOA";
	public String VRIR = "VRIR";
	public String VRLIM = "VRLIM";
	public String VRMAH = "VRMAH";
	public String VRPDOC = "VRPDOC";
	public String VRREC = "VRREC";
	public String VRTHS = "VRTHS";
	public String VRTTS = "VRTTS";
	public String VRTRN = "VRTRN";
	public String VRWUS = "VRWUS";
	public String VRFAC = "VRFAC";
	public String TRVHR = "TRVHR";
	public String TRCPHR = "TRCPHR";
	public String TRCCHR = "TRCCHR";
	public String TRPHR = "TRPHR";
	public String FOVRHOLFO = "FOVRHOLFO";
	public String FOCPHRL = "FOCPHRL";
	public String FOCCHRL = "FOCCHRL";
	public String FOPDHRL = "FOPDHRL";
	public String VRNFPA = "VRNFPA";
	/*END REPORT AUDIT TRAIL CODE*/
	
	/*START DOMESTIC BANK AUDIT TRAIL CODE*/
	public String VDB = "VDB";
	public String CDB = "CDB";
	public String EDB = "EDB";
	public String ADB = "ADB";
	public String RDB = "RDB";
	public String RRDB = "RRDB";
	public String REDB = "REDB";
	public String SUDB = "SUDB";
	/*END DOMESTIC BANK AUDIT TRAIL CODE*/
	
	/*START INTERNATIONAL BANK AUDIT TRAIL CODE*/
	public String VIBA = "VIBA";
	public String CIBA = "CIBA";
	public String EIBA = "EIBA";
	public String AIBA = "AIBA";
	public String RIBA = "RIBA";
	public String RRIBA = "RRIBA";
	public String REIBA = "REIBA";
	public String SUIBA = "SUIBA";
	/*END INTERNATIONAL BANK AUDIT TRAIL CODE*/
	
	/*START GLOBAL CHARGES TRAIL CODE*/
	public String VGC = "VGC";
	public String EGC = "EGC";
	public String SUGC = "SUGC";
	public String AGC = "AGC";
	public String RGC = "RGC";
	public String RRGC = "RRGC";
	public String REGC = "REGC";
	/*END GLOBAL CHARGES TRAIL CODE*/

	/*START PHYSICAL DOCUMENT AUDIT TRAIL CODE*/
	public String VPDC = "VPDC";
	public String CPDC = "CPDC";
	public String EPDC = "EPDC";
	public String SUPDC = "SUPDC";
	public String APDC = "APDC";
	public String RPDC = "RPDC";
	public String RRPDC = "RRPDC";
	public String REPDC = "REPDC";
	/*END PHYSICAL DOCUMENT AUDIT TRAIL CODE*/
	
	/*START SERVICE CHARGES TEMPLATE AUDIT TRAIL CODE*/
	public String VSCT = "VSCT";
	public String CSCT = "CSCT";
	public String ESCT = "ESCT";
	public String DSCT = "DSCT";
	public String ASCT = "ASCT";
	public String RSCT = "RSCT";
	public String RRSCT = "RRSCT";
	public String RESCT = "RESCT";
	public String SUSCT = "SUSCT";
	/*END SERVICE CHARGES TEMPLATE AUDIT TRAIL CODE*/ 
	
	
	
	/*START ACCESS VIOLATION*/
	public String ACCVIOL = "ACCVIOL";
	/*END ACCESS VIOLATION*/
	
	/*START INVOICE AUDIT TRAIL CODE*/
	public String CINV = "CINV";
	public String VINV = "VINV";
	public String EINV = "EINV";
	public String DINV = "DINV";
	public String UPINV= "UPINV";
	/*END INVOICE AUDIT TRAIL CODE*/
	
	/*START PURCHASE ORDER AUDIT TRAIL CODE*/
	public String CPUR = "CPUR";
	public String VPUR = "VPUR";
	public String EPUR = "EPUR";
	public String DPUR = "DPUR";
	public String UPPUR= "UPPUR";
	public String CINVPO= "CINVPO";
	public String CDOPO= "CDOPO";
	public String DFPO= "DFPO";
	public String DAFPO= "DAFPO";
	public String UPDFPO= "UPDFPO";
	
	/*MY TASK FO FOR PURCHASE ORDER*/
	public String FOMYPO = "FOMYPO";
	public String FOAPO = "FOAPO";
	public String FORPO = "FORPO";
	public String FORRPO = "FORRPO";
	public String FOREPO= "FOREPO";
	
	/*MY TASK BO FOR PURCHASE ORDER BANK RELEASE CHECKER*/
	public String VPOBRS = "VPOBRS";
	public String APOBRS = "APOBRS";
	public String RPOBRS = "RPOBRS";
	public String VPOBRD = "VPOBRD";
	public String APOBRD= "APOBRD";
	public String RPOBRD= "RPOBRD";
	
	/*END PURCHASE ORDER AUDIT TRAIL CODE*/
	
	/*START CREDIT NOTE AUDIT TRAIL CODE*/
	public String CCN = "CCN";
	public String VCN = "VCN";
	public String ECN = "ECN";
	public String DCN = "DCN";
	public String UPCN = "UPCN";
	/*END CREDIT NOTE AUDIT TRAIL CODE*/
	
	/*START DEBIT NOTE AUDIT TRAIL CODE*/
	public String CDN = "CDN";
	public String VDN = "VDN";
	public String EDN = "EDN";
	public String DDN = "DDN";
	public String UPDN = "UPDN";
	/*END DEBIT NOTE AUDIT TRAIL CODE*/
	
	/*START ACCOUNT INFORMATION AUDIT TRAIL CODE*/
	public String VTI = "VTI";
	/*END ACCOUNT INFORMATION AUDIT TRAIL CODE*/
	
	/*START HOLIDAY AUDIT TRAIL CODE*/
	public String VHOL = "VHOL";
	public String EHOL = "EHOL";
	public String CHOL = "CHOL";
	public String AHOL = "AHOL";
	public String RHOL = "RHOL";
	public String RRHOL = "RRHOL";
	public String REHOL = "REHOL";
	/*END HOLIDAY AUDIT TRAIL CODE*/

	/*START RATE CONVERSION*/
	public String DRCONV = "DRCONV";
	public String SRCONV = "SRCONV";
	public String ERCONV = "ERCONV";
	/*END RATE CONVERSION*/

	/*REPORT*/
	public String SFREP = "SFREP";
	public String ACREP = "ACREP";
	public String FFREP = "ACREP";
	public String PREP = "PREP";
	public String RFREP = "PREP";
	public String RRREP = "RRREP";
	public String SLAREP = "SLAREP";
	public String OFREP = "OFREP";
	/*END REPORT*/
	
	/*START REPORT EXCEPTION HANDLING*/
	public String DEH	= "DEH"; // Detail Exception Handling
	public String MEH	= "MEH"; // Modify Exception Handling
	public String AEH	= "AEH"; // Approve Exception Handling
	public String REH	= "REH"; // Reject Exception Handling
	/*END REPORT EXCEPTION HANDLING*/
	
	/*START ARCHIVE ORDER*/
	public String VARC  = "VARC"; // View Archive Order
	public String CARC	= "CARC"; // Create Archive Order
	public String CCARC	= "CCARC"; // Cancel Archive Order
	/*END ARCHIVE ORDER*/
	
	/*START FORCE FULLY ACTIVE*/
	public String VWFFA	= "VWFFA"; // View Force Fully Active
	public String SCFFA	= "SCFFA"; // Search Force Fully Active
	public String SBFFA	= "SBFFA"; // Submit Force Fully Active
	/*END FORCE FULLY ACTIVE*/
	
	/*START APPROVE CMS*/
	public String VWACMS	= "VWACMS"; // View Aprove CMS
	public String SCACMS	= "SCACMS"; // Search Approve CMS
	public String SBACMS	= "SBACMS"; // Submit Approve CMS
	/*END APPROVE CMS*/
}