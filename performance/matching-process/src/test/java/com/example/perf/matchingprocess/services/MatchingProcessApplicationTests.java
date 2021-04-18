package com.example.perf.matchingprocess.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({ MockitoExtension.class, SpringExtension.class })
@SpringBootTest
class MatchingProcessApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MatchingProcessService service;

	private static List<String> sourceNameList = Arrays.asList(
			"Dorri Evert",              "Cecilio Romme",            "Ernest Giovannazzi",       "Desi Braunfeld",
			"Baillie Summerell",        "Alie Phillipps",           "Stormie Flexman",          "Evangelin Ellicock",
			"Jandy Thorogood",          "Florri Seabright",         "Lynelle Drakeford",        "Shanan Danson",
			"Leora Peegrem",            "Lettie Crumpe",            "Pearla McDill",            "Bronnie Musto",
			"Garik Gauthorpp",          "Rhetta Cooksey",           "Rubin Dunk",               "Junina McComish",
			"Nichole Rigate",           "Stephanie Hertwell",       "Pia Abbot-Doyle",          "Zachary Billinge",
			"Hedvige Spaldin",          "Wait Braams",              "Veronica Farres",          "Martie Aharoni",
			"Corbie Brill",             "Marlin Souten",            "Melania Everwin",          "Sheena Ffoulkes",
			"Reinald Trudgeon",         "Korella Langshaw",         "Johnette Lermit",          "Cristin Davidsohn",
			"Kleon Tolotti",            "Meaghan Reading",          "Ianthe Fenelow",           "Bill Iddy",
			"Sile Brasner",             "Archy Fennelow",           "Hughie Manvelle",          "Analiese Bridgett",
			"Jori Folling",             "Matthieu Sennett",         "Pamelina Franceschielli",  "Devland Bestar",
			"Alexandros Concannon",     "Lezley Pittham",           "Tristam Armitt",           "Brandice Sommer",
			"Edeline Romagnosi",        "Lanae Ost",                "Guy Sugge",                "Aguie Crum",
			"Lothaire Klauer",          "Poppy Kellock",            "Therese Fominov",          "Valentine Courteney",
			"Lilian Crowcum",           "Kaspar Markovic",          "Raf Maccree",              "Chiquita Rushforth",
			"Georgine Currie",          "Aldwin McKevany",          "Ignace Leggan",            "Tulley Davet",
			"Kamilah Dalwood",          "Egor Witcherley",          "Dun Mallen",               "Cully Sweedland",
			"Winnah Hassewell",         "Alfi Castagnasso",         "Kayne Tangye",             "Shirley Duffitt",
			"Felecia Rowswell",         "Griffy Heinecke",          "Adolf Paolini",            "Flss Rudiger",
			"Lorine Harrower",          "Carrissa Cleatherow",      "Luther Killen",            "Sheelah Hovell",
			"Filberte Robinette",       "Claire Rodinger",          "Carolyn Nettleship",       "Darcy Linden",
			"Waylin Pallister",         "Cate Le Hucquet",          "Marie-jeanne Tremblot",    "Rodrigo Decruse",
			"Griswold Caulwell",        "Vasili Balnave",           "Gilbertine Brundall",      "Tova Faers",
			"Marleen Salliss",          "Jarret Blazejewski",       "Deeyn Sproson",            "Friedrich Bernardon"
	);

	private static DecimalFormat decimalFormatter = new DecimalFormat("###,###");

	@Test
	public void perfMappingProcess() {
		testPerfMappingByList(100000, 20, sourceNameList);
	}

	public void testPerfMappingByList(long mappingCount, long round, List<String> sourceList) {
		System.out.println("Start running performance test: testPerfMappingByList() - mappingCount=" + mappingCount + ", round=" + round);
		Instant startOuter = Instant.now();
		for(long i = 0; i < round; ++i) {
			Instant start = Instant.now();
			for(long j = 0; j < mappingCount; ++j) {
				service.mappingNamesByList(sourceList);
			}
			Instant end = Instant.now();
			System.out.println("Interval: performance of mapping by List: "
					+ decimalFormatter.format(Duration.between(start, end).toMillis()) + " ms");
		}
		Instant endOuter = Instant.now();
		System.out.println("Finished. Performance of mapping by List: "
				+ decimalFormatter.format(Duration.between(startOuter, endOuter).toMillis()) + " ms");
	}
}
