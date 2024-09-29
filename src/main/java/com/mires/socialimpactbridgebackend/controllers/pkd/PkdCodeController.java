package com.mires.socialimpactbridgebackend.controllers.pkd;

import com.google.gson.*;
import com.mires.common.objects.pkdCode.PkdCode;
import com.mires.socialimpactbridgebackend.service.pkd.PkdCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/pkdCodes")
public class PkdCodeController {
    private final PkdCodeService pkdCodeService;

    @Autowired
    public PkdCodeController(PkdCodeService pkdCodeService) {
        this.pkdCodeService = pkdCodeService;
    }

    @GetMapping
    @CrossOrigin
    public List<PkdCode> getAllPkdCodes() {
        return pkdCodeService.getAllPkdCodes();
    }

    @PostMapping(value = "/getByClassCode", produces = "application/json")
    @CrossOrigin
    public ResponseEntity<String> getPkdCodeByClassCode(@RequestBody Map<String, Object> body) {
        return pkdCodeService.getPkdCodeByClassCode((String)body.get("codeClass"));
    }

    @PostMapping("/addPkdCode")
    @CrossOrigin
    public String addPkdCode() {
        final String input = "[\n" +
                "        {\n" +
                "            \"01\": \"UPRAWY ROLNE; CHÓW I HODOWLA ZWIERZĄT;ŁOWIECTWO; WŁĄCZAJĄC DZIAŁALNOŚĆ USŁUGOWĄ\",\n" +
                "            \"sekcja\": \"SEKCJA A\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"02\": \"LEŚNICTWO I POZYSKIWANIE DREWNA\",\n" +
                "            \"sekcja\": \"SEKCJA A\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"03\": \"RYBACTWO\",\n" +
                "            \"sekcja\": \"SEKCJA A\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"05\": \"WYDOBYWANIE WĘGLA KAMIENNEGO I WĘGLA BRUNATNEGO (LIGNITU)\",\n" +
                "            \"sekcja\": \"SEKCJA B\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"06\": \"GÓRNICTWO ROPY NAFTOWEJ I GAZU ZIEMNEGO\",\n" +
                "            \"sekcja\": \"SEKCJA B\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"07\": \"GÓRNICTWO RUD METALI\",\n" +
                "            \"sekcja\": \"SEKCJA B\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"08\": \"POZOSTAŁE GÓRNICTWO I WYDOBYWANIE\",\n" +
                "            \"sekcja\": \"SEKCJA B\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"09\": \"DZIAŁALNOŚĆ USŁUGOWA WSPOMAGAJĄCA GÓRNICTWO I WYDOBYWANIE\",\n" +
                "            \"sekcja\": \"SEKCJA B\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"10\": \"PRODUKCJA ARTYKUŁÓW SPOŻYWCZYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"11\": \"PRODUKCJA NAPOJÓW\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"12\": \"PRODUKCJA WYROBÓW TYTONIOWYCH\n\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"13\": \"PRODUKCJA WYROBÓW TEKSTYLNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"14\": \"PRODUKCJA ODZIEŻY\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"15\": \"PRODUKCJA SKÓR I WYROBÓW ZE SKÓR WYPRAWIONYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"16\": \"PRODUKCJA WYROBÓW Z DREWNA ORAZ KORKA; Z WYŁĄCZENIEM MEBLI; PRODUKCJA WYROBÓW ZE SŁOMY I MATERIAŁÓW UŻYWANYCH DO WYPLATANIA\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"17\": \"PRODUKCJA PAPIERU I WYROBÓW Z PAPIERU\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"18\": \"POLIGRAFIA I REPRODUKCJA ZAPISANYCH NOŚNIKÓW INFORMACJI\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"19\": \"WYTWARZANIE I PRZETWARZANIE KOKSU I PRODUKTÓW RAFINACJI ROPY NAFTOWEJ\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"20\": \"PRODUKCJA CHEMIKALIÓW I WYROBÓW CHEMICZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"21\": \"PRODUKCJA PODSTAWOWYCH SUBSTANCJI FARMACEUTYCZNYCH ORAZ LEKÓW I POZOSTAŁYCH WYROBÓW FARMACEUTYCZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"22\": \"PRODUKCJA WYROBÓW Z GUMY I TWORZYW SZTUCZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"23\": \"PRODUKCJA WYROBÓW Z POZOSTAŁYCH MINERALNYCH SUROWCÓW NIEMETALICZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"24\": \"PRODUKCJA METALI\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"25\": \"PRODUKCJA METALOWYCH WYROBÓW GOTOWYCH; Z WYŁĄCZENIEM MASZYN I URZĄDZEŃ\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"26\": \"PRODUKCJA KOMPUTERÓW; WYROBÓW ELEKTRONICZNYCH I OPTYCZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"27\": \"PRODUKCJA URZĄDZEŃ ELEKTRYCZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"28\": \"PRODUKCJA MASZYN I URZĄDZEŃ; GDZIE INDZIEJ NIESKLASYFIKOWANA\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"29\": \"PRODUKCJA POJAZDÓW SAMOCHODOWYCH; PRZYCZEP I NACZEP; Z WYŁĄCZENIEM MOTOCYKLI\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"30\": \"PRODUKCJA POZOSTAŁEGO SPRZĘTU TRANSPORTOWEGO\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"31\": \"PRODUKCJA MEBLI\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"32\": \"POZOSTAŁA PRODUKCJA WYROBÓW\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"33\": \"NAPRAWA; KONSERWACJA I INSTALOWANIE MASZYN I URZĄDZEŃ\",\n" +
                "            \"sekcja\": \"SEKCJA C\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"35\": \"WYTWARZANIE I ZAOPATRYWANIE W ENERGIĘ ELEKTRYCZNĄ; GAZ; PARĘ WODNĄ; GORĄCĄ WODĘ I POWIETRZE DO UKŁADÓW KLIMATYZACYJNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA D\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"38\": \"DZIAŁALNOŚĆ ZWIĄZANA ZE ZBIERANIEM; PRZETWARZANIEM I UNIESZKODLIWIANIEM ODPADÓW; ODZYSK SUROWCÓW\",\n" +
                "            \"sekcja\": \"SEKCJA E\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"41\": \"ROBOTY BUDOWLANE ZWIĄZANE ZE WZNOSZENIEM BUDYNKÓW\",\n" +
                "            \"sekcja\": \"SEKCJA F\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"42\": \"ROBOTY ZWIĄZANE Z BUDOWĄ OBIEKTÓW INŻYNIERII LĄDOWEJ I WODNEJ\",\n" +
                "            \"sekcja\": \"SEKCJA F\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"43\": \"ROBOTY BUDOWLANE SPECJALISTYCZNE\",\n" +
                "            \"sekcja\": \"SEKCJA F\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"45\": \"HANDEL HURTOWY I DETALICZNY POJAZDAMI SAMOCHODOWYMI; NAPRAWA POJAZDÓW SAMOCHODOWYCH\",\n" +
                "            \"sekcja\": \"SEKCJA G\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"46\": \"HANDEL HURTOWY; Z WYŁĄCZENIEM HANDLU POJAZDAMI SAMOCHODOWYMI\",\n" +
                "            \"sekcja\": \"SEKCJA G\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"47\": \"HANDEL DETALICZNY; Z WYŁĄCZENIEM HANDLU DETALICZNEGO POJAZDAMI SAMOCHODOWYMI\",\n" +
                "            \"sekcja\": \"SEKCJA G\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"49\": \"TRANSPORT LĄDOWY ORAZ TRANSPORT RUROCIĄGOWY\",\n" +
                "            \"sekcja\": \"SEKCJA H\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"50\": \"TRANSPORT WODNY\",\n" +
                "            \"sekcja\": \"SEKCJA H\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"51\": \"TRANSPORT LOTNICZY\",\n" +
                "            \"sekcja\": \"SEKCJA H\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"52\": \"MAGAZYNOWANIE I DZIAŁALNOŚĆ USŁUGOWA WSPOMAGAJĄCA TRANSPORT\",\n" +
                "            \"sekcja\": \"SEKCJA H\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"53\": \"DZIAŁALNOŚĆ POCZTOWA I KURIERSKA\",\n" +
                "            \"sekcja\": \"SEKCJA H\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"55\": \"ZAKWATEROWANIE\",\n" +
                "            \"sekcja\": \"SEKCJA I\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"56\": \"DZIAŁALNOŚĆ USŁUGOWA ZWIĄZANA Z WYŻYWIENIEM\",\n" +
                "            \"sekcja\": \"SEKCJA I\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"58\": \"DZIAŁALNOŚĆ WYDAWNICZA\",\n" +
                "            \"sekcja\": \"SEKCJA J\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"59\": \"DZIAŁALNOŚĆ ZWIĄZANA Z PRODUKCJĄ FILMÓW; NAGRAŃ WIDEO; PROGRAMÓW TELEWIZYJNYCH; NAGRAŃ DŹWIĘKOWYCH I MUZYCZNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA J\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"60\": \"NADAWANIE PROGRAMÓW OGÓLNODOSTĘPNYCH I ABONAMENTOWYCH\",\n" +
                "            \"sekcja\": \"SEKCJA J\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"61\": \"TELEKOMUNIKACJA\",\n" +
                "            \"sekcja\": \"SEKCJA J\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"62\": \"DZIAŁALNOŚĆ ZWIĄZANA Z OPROGRAMOWANIEM I DORADZTWEM W ZAKRESIE INFORMATYKI ORAZ DZIALALNOŚĆ POWIĄZANA\",\n" +
                "            \"sekcja\": \"SEKCJA J\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"63\": \"DZIAŁALNOŚĆ USŁUGOWA W ZAKRESIE INFORMACJI\",\n" +
                "            \"sekcja\": \"SEKCJA J\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"64\": \"FINANSOWA DZIAŁALNOŚĆ USŁUGOWA; Z WYŁĄCZENIEM UBEZPIECZEŃ I FUNDUSZÓW EMERYTALNYCH\",\n" +
                "            \"sekcja\": \"SEKCJA K\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"65\": \"UBEZPIECZENIA; REASEKURACJA ORAZ FUNDUSZE EMERYTALNE; Z WYŁĄCZENIEM OBOWIĄZKOWEGO UBEZPIECZENIA SPOŁECZNEGO\",\n" +
                "            \"sekcja\": \"SEKCJA K\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"66\": \"DZIAŁALNOŚĆ WSPOMAGAJĄCA USŁUGI FINANSOWE ORAZ UBEZPIECZENIA I FUNDUSZE EMERYTALNE\",\n" +
                "            \"sekcja\": \"SEKCJA K\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"68\": \"DZIAŁALNOŚĆ ZWIĄZANA Z OBSŁUGĄ RYNKU NIERUCHOMOŚCI\",\n" +
                "            \"sekcja\": \"SEKCJA L\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"69\": \"DZIAŁALNOŚĆ PRAWNICZA; RACHUNKOWO-KSIĘGOWA I DORADZTWO PODATKOWE\",\n" +
                "            \"sekcja\": \"SEKCJA M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"70\": \"DZIAŁALNOŚĆ FIRM CENTRALNYCH (HEAD OFFICES); DORADZTWO ZWIĄZANE Z ZARZĄDZANIEM\",\n" +
                "            \"sekcja\": \"SEKCJA M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"71\": \"DZIAŁALNOŚĆ W ZAKRESIE ARCHITEKTURY I INŻYNIERII; BADANIA I ANALIZY TECHNICZNE\",\n" +
                "            \"sekcja\": \"SEKCJA M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"72\": \"BADANIA NAUKOWE I PRACE ROZWOJOWE\",\n" +
                "            \"sekcja\": \"SEKCJA M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"73\": \"REKLAMA; BADANIE RYNKU I OPINII PUBLICZNEJ\",\n" +
                "            \"sekcja\": \"SEKCJA M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"74\": \"POZOSTAŁA DZIAŁALNOŚĆ PROFESJONALNA; NAUKOWA I TECHNICZNA\",\n" +
                "            \"sekcja\": \"SEKCJA M\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"77\": \"WYNAJEM I DZIERŻAWA\",\n" +
                "            \"sekcja\": \"SEKCJA N\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"78\": \"DZIAŁALNOŚĆ ZWIĄZANA Z ZATRUDNIENIEM\",\n" +
                "            \"sekcja\": \"SEKCJA N\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"79\": \"DZIAŁALNOŚĆ ORGANIZATORÓW TURYSTYKI; POŚREDNIKÓW I AGENTÓW TURYSTYCZNYCH ORAZ POZOSTAŁA DZIAŁALNOŚĆ USŁUGOWA W ZAKRESIE REZERWACJI I DZIAŁALNOŚCI Z NIĄ ZWIĄZANE\",\n" +
                "            \"sekcja\": \"SEKCJA N\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"80\": \"DZIAŁALNOŚĆ DETEKTYWISTYCZNA I OCHRONIARSKA\",\n" +
                "            \"sekcja\": \"SEKCJA N\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"81\": \"DZIAŁALNOŚĆ USŁUGOWA ZWIĄZANA Z UTRZYMANIEM PORZĄDKU W BUDYNKACH I ZAGOSPODAROWANIEM TERENÓW ZIELENI\",\n" +
                "            \"sekcja\": \"SEKCJA N\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"82\": \"DZIAŁALNOŚĆ ZWIĄZANA Z ADMINISTRACYJNĄ OBSŁUGĄ BIURA I POZOSTAŁA DZIAŁALNOŚĆ WSPOMAGAJĄCA PROWADZENIE DZIAŁALNOŚCI GOSPODARCZEJ\",\n" +
                "            \"sekcja\": \"SEKCJA N\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"84\": \"ADMINISTRACJA PUBLICZNA I OBRONA NARODOWA; OBOWIĄZKOWE ZABEZPIECZENIA SPOŁECZNE\",\n" +
                "            \"sekcja\": \"SEKCJA O\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"85\": \"EDUKACJA\",\n" +
                "            \"sekcja\": \"SEKCJA P\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"86\": \"OPIEKA ZDROWOTNA\",\n" +
                "            \"sekcja\": \"SEKCJA Q\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"87\": \"POMOC SPOŁECZNA Z ZAKWATEROWANIEM\",\n" +
                "            \"sekcja\": \"SEKCJA Q\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"88\": \"POMOC SPOŁECZNA BEZ ZAKWATEROWANIA\",\n" +
                "            \"sekcja\": \"SEKCJA Q\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"90\": \"DZIAŁALNOŚĆ TWÓRCZA ZWIĄZANA Z KULTURĄ I ROZRYWKĄ\",\n" +
                "            \"sekcja\": \"SEKCJA R\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"91\": \"DZIAŁALNOŚĆ BIBLIOTEK; ARCHIWÓW; MUZEÓW ORAZ POZOSTAŁA DZIAŁALNOŚĆ ZWIĄZANA Z KULTURĄ\",\n" +
                "            \"sekcja\": \"SEKCJA R\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"93\": \"DZIAŁALNOŚĆ SPORTOWA; ROZRYWKOWA I REKREACYJNA\",\n" +
                "            \"sekcja\": \"SEKCJA R\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"94\": \"DZIAŁALNOŚĆ ORGANIZACJI CZŁONKOWSKICH\",\n" +
                "            \"sekcja\": \"SEKCJA S\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"95\": \"NAPRAWA I KONSERWACJA KOMPUTERÓW I ARTYKUŁÓW UŻYTKU OSOBISTEGO I DOMOWEGO\",\n" +
                "            \"sekcja\": \"SEKCJA S\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"96\": \"POZOSTAŁA INDYWIDUALNA DZIAŁALNOŚĆ USŁUGOWA\",\n" +
                "            \"sekcja\": \"SEKCJA S\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"98\": \"GOSPODARSTWA DOMOWE PRODUKUJĄCE WYROBY I ŚWIADCZĄCE USŁUGI NA WŁASNE POTRZEBY\",\n" +
                "            \"sekcja\": \"SEKCJA T\"\n" +
                "        }\n" +
                "    ]";
        final JsonArray jsonArray = JsonParser.parseString(input).getAsJsonArray();
        jsonArray.forEach(elem -> {
            PkdCode pkdCode = new PkdCode();
            Map.Entry<String, JsonElement> entry = elem.getAsJsonObject().entrySet().stream().findFirst().get();
            pkdCode.setCodeClass(entry.getKey());
            pkdCode.setLore(entry.getValue().getAsString());
            pkdCodeService.savePkdCode(pkdCode);
        });
        return ResponseEntity.ok("PkdCodes added successfully").toString();
    }

}
