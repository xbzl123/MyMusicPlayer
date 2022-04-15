package com.example.mymusicplayer.utils

import java.util.ArrayList
import java.util.HashMap

class CountryCodeToRegionCodeUtil {
    // The capacity is set to 285 as there are 214 different entries,
    // and this offers a load factor of roughly 0.75.
    // A mapping from a country code to the region codes which denote the
    // country/region represented by that country code. In the case of multiple
    // countries sharing a calling code, such as the NANPA countries, the one
    // indicated with "isMainCountryForCode" in the metadata should be first.
    val countryCodeToRegionCodeMap: MutableMap<Int, List<String>>
        init {
            // The capacity is set to 285 as there are 214 different entries,
            // and this offers a load factor of roughly 0.75.
            countryCodeToRegionCodeMap = HashMap()
            var listWithRegionCode: ArrayList<String>
            listWithRegionCode = ArrayList(3)
            listWithRegionCode.add("US")
            listWithRegionCode.add("CA")
            listWithRegionCode.add("MP")
            countryCodeToRegionCodeMap[1] = listWithRegionCode

            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("RU")
            countryCodeToRegionCodeMap[7] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("EG")
            countryCodeToRegionCodeMap[20] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ZA")
            countryCodeToRegionCodeMap[27] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GR")
            countryCodeToRegionCodeMap[30] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NL")
            countryCodeToRegionCodeMap[31] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BE")
            countryCodeToRegionCodeMap[32] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("FR")
            countryCodeToRegionCodeMap[33] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ES")
            countryCodeToRegionCodeMap[34] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("HU")
            countryCodeToRegionCodeMap[36] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IT")
            countryCodeToRegionCodeMap[39] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VA")
            countryCodeToRegionCodeMap[379] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("RO")
            countryCodeToRegionCodeMap[40] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CH")
            countryCodeToRegionCodeMap[41] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AT")
            countryCodeToRegionCodeMap[43] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GG")
            countryCodeToRegionCodeMap[1481] = listWithRegionCode

            listWithRegionCode = ArrayList(3)
            listWithRegionCode.add("GB")
            listWithRegionCode.add("IM")
            listWithRegionCode.add("JE")
            countryCodeToRegionCodeMap[44] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("DK")
            countryCodeToRegionCodeMap[45] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SE")
            countryCodeToRegionCodeMap[46] = listWithRegionCode
            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("NO")
            listWithRegionCode.add("SJ")
            countryCodeToRegionCodeMap[47] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PL")
            countryCodeToRegionCodeMap[48] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("DE")
            countryCodeToRegionCodeMap[49] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PE")
            countryCodeToRegionCodeMap[51] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MX")
            countryCodeToRegionCodeMap[52] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CU")
            countryCodeToRegionCodeMap[53] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AR")
            countryCodeToRegionCodeMap[54] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BR")
            countryCodeToRegionCodeMap[55] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CL")
            countryCodeToRegionCodeMap[56] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CO")
            countryCodeToRegionCodeMap[57] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VE")
            countryCodeToRegionCodeMap[58] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MY")
            countryCodeToRegionCodeMap[60] = listWithRegionCode
            listWithRegionCode = ArrayList(3)
            listWithRegionCode.add("AU")
            listWithRegionCode.add("CC")
            listWithRegionCode.add("CX")
            countryCodeToRegionCodeMap[61] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ID")
            countryCodeToRegionCodeMap[62] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PH")
            countryCodeToRegionCodeMap[63] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NZ")
            countryCodeToRegionCodeMap[64] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SG")
            countryCodeToRegionCodeMap[65] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TH")
            countryCodeToRegionCodeMap[66] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KZ")
            countryCodeToRegionCodeMap[73] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("JP")
            countryCodeToRegionCodeMap[81] = listWithRegionCode


            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KR")
            countryCodeToRegionCodeMap[82] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VN")
            countryCodeToRegionCodeMap[84] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CN")
            countryCodeToRegionCodeMap[86] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TR")
            countryCodeToRegionCodeMap[90] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IN")
            countryCodeToRegionCodeMap[91] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PK")
            countryCodeToRegionCodeMap[92] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AF")
            countryCodeToRegionCodeMap[93] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LK")
            countryCodeToRegionCodeMap[94] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MM")
            countryCodeToRegionCodeMap[95] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IR")
            countryCodeToRegionCodeMap[98] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SS")
            countryCodeToRegionCodeMap[211] = listWithRegionCode
            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("MA")
            listWithRegionCode.add("EH")
            countryCodeToRegionCodeMap[212] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("DZ")
            countryCodeToRegionCodeMap[213] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TN")
            countryCodeToRegionCodeMap[216] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LY")
            countryCodeToRegionCodeMap[218] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GM")
            countryCodeToRegionCodeMap[220] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SN")
            countryCodeToRegionCodeMap[221] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MR")
            countryCodeToRegionCodeMap[222] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ML")
            countryCodeToRegionCodeMap[223] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GN")
            countryCodeToRegionCodeMap[224] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CI")
            countryCodeToRegionCodeMap[225] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BF")
            countryCodeToRegionCodeMap[226] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NE")
            countryCodeToRegionCodeMap[227] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TG")
            countryCodeToRegionCodeMap[228] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BJ")
            countryCodeToRegionCodeMap[229] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MU")
            countryCodeToRegionCodeMap[230] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LR")
            countryCodeToRegionCodeMap[231] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SL")
            countryCodeToRegionCodeMap[232] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GH")
            countryCodeToRegionCodeMap[233] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NG")
            countryCodeToRegionCodeMap[234] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TD")
            countryCodeToRegionCodeMap[235] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CF")
            countryCodeToRegionCodeMap[236] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CM")
            countryCodeToRegionCodeMap[237] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CV")
            countryCodeToRegionCodeMap[238] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ST")
            countryCodeToRegionCodeMap[239] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GQ")
            countryCodeToRegionCodeMap[240] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GA")
            countryCodeToRegionCodeMap[241] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CG")
            countryCodeToRegionCodeMap[242] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CD")
            countryCodeToRegionCodeMap[243] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AO")
            countryCodeToRegionCodeMap[244] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GW")
            countryCodeToRegionCodeMap[245] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IO")
            countryCodeToRegionCodeMap[246] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AC")
            countryCodeToRegionCodeMap[247] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SC")
            countryCodeToRegionCodeMap[248] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SD")
            countryCodeToRegionCodeMap[249] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("RW")
            countryCodeToRegionCodeMap[250] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ET")
            countryCodeToRegionCodeMap[251] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SO")
            countryCodeToRegionCodeMap[252] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("DJ")
            countryCodeToRegionCodeMap[253] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KE")
            countryCodeToRegionCodeMap[254] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TZ")
            countryCodeToRegionCodeMap[255] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("UG")
            countryCodeToRegionCodeMap[256] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BI")
            countryCodeToRegionCodeMap[257] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MZ")
            countryCodeToRegionCodeMap[258] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ZM")
            countryCodeToRegionCodeMap[260] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MG")
            countryCodeToRegionCodeMap[261] = listWithRegionCode
            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("RE")
            listWithRegionCode.add("YT")
            countryCodeToRegionCodeMap[262] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ZW")
            countryCodeToRegionCodeMap[263] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NA")
            countryCodeToRegionCodeMap[264] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MW")
            countryCodeToRegionCodeMap[265] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LS")
            countryCodeToRegionCodeMap[266] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BW")
            countryCodeToRegionCodeMap[267] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SZ")
            countryCodeToRegionCodeMap[268] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KM")
            countryCodeToRegionCodeMap[269] = listWithRegionCode
            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("SH")
            listWithRegionCode.add("AC")
            countryCodeToRegionCodeMap[290] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ER")
            countryCodeToRegionCodeMap[291] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AW")
            countryCodeToRegionCodeMap[297] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("FO")
            countryCodeToRegionCodeMap[298] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GL")
            countryCodeToRegionCodeMap[299] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GI")
            countryCodeToRegionCodeMap[350] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PT")
            countryCodeToRegionCodeMap[351] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LU")
            countryCodeToRegionCodeMap[352] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IE")
            countryCodeToRegionCodeMap[353] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IS")
            countryCodeToRegionCodeMap[354] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AL")
            countryCodeToRegionCodeMap[355] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MT")
            countryCodeToRegionCodeMap[356] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CY")
            countryCodeToRegionCodeMap[357] = listWithRegionCode
            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("FI")
            listWithRegionCode.add("AX")
            countryCodeToRegionCodeMap[358] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BG")
            countryCodeToRegionCodeMap[359] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LT")
            countryCodeToRegionCodeMap[370] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LV")
            countryCodeToRegionCodeMap[371] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("EE")
            countryCodeToRegionCodeMap[372] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MD")
            countryCodeToRegionCodeMap[373] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AM")
            countryCodeToRegionCodeMap[374] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BY")
            countryCodeToRegionCodeMap[375] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AD")
            countryCodeToRegionCodeMap[376] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MC")
            countryCodeToRegionCodeMap[377] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SM")
            countryCodeToRegionCodeMap[378] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("UA")
            countryCodeToRegionCodeMap[380] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("RS")
            countryCodeToRegionCodeMap[381] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("ME")
            countryCodeToRegionCodeMap[382] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("HR")
            countryCodeToRegionCodeMap[385] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SI")
            countryCodeToRegionCodeMap[386] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BA")
            countryCodeToRegionCodeMap[387] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MK")
            countryCodeToRegionCodeMap[389] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CZ")
            countryCodeToRegionCodeMap[420] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SK")
            countryCodeToRegionCodeMap[421] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LI")
            countryCodeToRegionCodeMap[423] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("FK")
            countryCodeToRegionCodeMap[500] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BZ")
            countryCodeToRegionCodeMap[501] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GT")
            countryCodeToRegionCodeMap[502] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SV")
            countryCodeToRegionCodeMap[503] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("HN")
            countryCodeToRegionCodeMap[504] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NI")
            countryCodeToRegionCodeMap[505] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CR")
            countryCodeToRegionCodeMap[506] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PA")
            countryCodeToRegionCodeMap[507] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PM")
            countryCodeToRegionCodeMap[508] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("HT")
            countryCodeToRegionCodeMap[509] = listWithRegionCode
            listWithRegionCode = ArrayList(3)
            listWithRegionCode.add("GP")
            listWithRegionCode.add("BL")
            listWithRegionCode.add("MF")
            countryCodeToRegionCodeMap[590] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BO")
            countryCodeToRegionCodeMap[591] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GY")
            countryCodeToRegionCodeMap[592] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("EC")
            countryCodeToRegionCodeMap[593] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GF")
            countryCodeToRegionCodeMap[594] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PY")
            countryCodeToRegionCodeMap[595] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MQ")
            countryCodeToRegionCodeMap[596] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SR")
            countryCodeToRegionCodeMap[597] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("UY")
            countryCodeToRegionCodeMap[598] = listWithRegionCode

            listWithRegionCode = ArrayList(3)
            listWithRegionCode.add("AN")
            listWithRegionCode.add("BQ")
            listWithRegionCode.add("SX")
            countryCodeToRegionCodeMap[599] = listWithRegionCode
            listWithRegionCode = ArrayList(2)
            listWithRegionCode.add("CW")
            listWithRegionCode.add("TL")
            countryCodeToRegionCodeMap[670] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NF")
            countryCodeToRegionCodeMap[672] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BN")
            countryCodeToRegionCodeMap[673] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NR")
            countryCodeToRegionCodeMap[674] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PG")
            countryCodeToRegionCodeMap[675] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TO")
            countryCodeToRegionCodeMap[676] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SB")
            countryCodeToRegionCodeMap[677] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VU")
            countryCodeToRegionCodeMap[678] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("FJ")
            countryCodeToRegionCodeMap[679] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PW")
            countryCodeToRegionCodeMap[680] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("WF")
            countryCodeToRegionCodeMap[681] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("CK")
            countryCodeToRegionCodeMap[682] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NU")
            countryCodeToRegionCodeMap[683] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("WS")
            countryCodeToRegionCodeMap[685] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KI")
            countryCodeToRegionCodeMap[686] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NC")
            countryCodeToRegionCodeMap[687] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TV")
            countryCodeToRegionCodeMap[688] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PF")
            countryCodeToRegionCodeMap[689] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TK")
            countryCodeToRegionCodeMap[690] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("FM")
            countryCodeToRegionCodeMap[691] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MH")
            countryCodeToRegionCodeMap[692] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[800] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[808] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KP")
            countryCodeToRegionCodeMap[850] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("HK")
            countryCodeToRegionCodeMap[852] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MO")
            countryCodeToRegionCodeMap[853] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KH")
            countryCodeToRegionCodeMap[855] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LA")
            countryCodeToRegionCodeMap[856] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[870] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[878] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BD")
            countryCodeToRegionCodeMap[880] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[881] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[882] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[883] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TW")
            countryCodeToRegionCodeMap[886] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[888] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MV")
            countryCodeToRegionCodeMap[960] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LB")
            countryCodeToRegionCodeMap[961] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("JO")
            countryCodeToRegionCodeMap[962] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SY")
            countryCodeToRegionCodeMap[963] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IQ")
            countryCodeToRegionCodeMap[964] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KW")
            countryCodeToRegionCodeMap[965] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("SA")
            countryCodeToRegionCodeMap[966] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("YE")
            countryCodeToRegionCodeMap[967] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("OM")
            countryCodeToRegionCodeMap[968] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PS")
            countryCodeToRegionCodeMap[970] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AE")
            countryCodeToRegionCodeMap[971] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("IL")
            countryCodeToRegionCodeMap[972] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BH")
            countryCodeToRegionCodeMap[973] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("QA")
            countryCodeToRegionCodeMap[974] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BT")
            countryCodeToRegionCodeMap[975] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MN")
            countryCodeToRegionCodeMap[976] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("NP")
            countryCodeToRegionCodeMap[977] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("001")
            countryCodeToRegionCodeMap[979] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TJ")
            countryCodeToRegionCodeMap[992] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TM")
            countryCodeToRegionCodeMap[993] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AZ")
            countryCodeToRegionCodeMap[994] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GE")
            countryCodeToRegionCodeMap[995] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KG")
            countryCodeToRegionCodeMap[996] = listWithRegionCode
            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("UZ")
            countryCodeToRegionCodeMap[998] = listWithRegionCode


            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AI")
            countryCodeToRegionCodeMap[1264] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AG")
            countryCodeToRegionCodeMap[1268] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("AS")
            countryCodeToRegionCodeMap[684] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BB")
            countryCodeToRegionCodeMap[1246] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BM")
            countryCodeToRegionCodeMap[1441] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("BS")
            countryCodeToRegionCodeMap[1242] = listWithRegionCode


            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("DM")
            countryCodeToRegionCodeMap[1767] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("DO")
            countryCodeToRegionCodeMap[1809] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GD")
            countryCodeToRegionCodeMap[1473] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("GU")
            countryCodeToRegionCodeMap[1671] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("JM")
            countryCodeToRegionCodeMap[1876] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KN")
            countryCodeToRegionCodeMap[1869] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("KY")
            countryCodeToRegionCodeMap[1345] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("LC")
            countryCodeToRegionCodeMap[1758] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("MS")
            countryCodeToRegionCodeMap[1664] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("PR")
            countryCodeToRegionCodeMap[1809] = listWithRegionCode


            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TC")
            countryCodeToRegionCodeMap[1649] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("TT")
            countryCodeToRegionCodeMap[1868] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VC")
            countryCodeToRegionCodeMap[1784] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VG")
            countryCodeToRegionCodeMap[1284] = listWithRegionCode

            listWithRegionCode = ArrayList(1)
            listWithRegionCode.add("VI")
            countryCodeToRegionCodeMap[1340] = listWithRegionCode

        }

    companion object {
        fun getPhonePrefixByCountry(country: String): String {
            if (null != country) {
                CountryCodeToRegionCodeUtil().countryCodeToRegionCodeMap.map {
                    if (it.value.contains(country)) {
                        return it.key.toString()
                    }
                }
            }
            return ""
        }
    }
}