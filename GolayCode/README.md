# Projekto ataskaita.

### Užduotis:
- A13.
- Golėjaus kodas C23.

### Darbo vadovas:
- Dr., Asist., G. Skersys.

### Atliko:
- Karolis Zabulis, Informacinės Technologijos, 2 k.

<div style="page-break-after: always;"></div>

# Programos trūkumai.

- Programa neturi išpildyto 3 - čio scenarijaus.

<div style="page-break-after: always;"></div>

# Programos veikimo principas.

## Vartotojo sąsaja/perspektyva.

- Vartotojui paleidus programą yra paprašoma įvesti kanalo klaidos tikimybę [0 <= n <= 1].
- Įvedus tikimybę vartotojas gali pasirinkti tarp kelių opcijų t.y. koduoti nurodytą vektorių ar koduoti pateiktą tekstą.
### Vektoriaus kodavimas:
- Pasirinkus koduoti vektorių, programa paprašo įvesti vartotoją 12 - kos simbolių dvejetainį vektorių.
- Vektorius yra siunčiamas koduoti, užkodavus vektorių jis keliauja į kanalą, kuriame pagal nurodytą klaidos tikimybę yra įvedamos klaidos.
Vektorius išėjęs iš kanalo yra paduodamas vartotojui, kur vartotojas gali pataisyti vektorių arba įvesti daugiau klaidų, arba palikti nekeistą. 
Vartotojui baigus vektoriaus modifikacijas, vektorius yra siunčiamas į dekodavimo algoritmą, kur pataisomos klaidos, 
jeigu jų yra nedaugiau 3, ir vektorius grąžinamas atgal vartotojui.
### Teksto kodavimas:
- Pasirinkus koduoti tekstą, programa paprašo vartotoją įvesti tekstą kodavimui. Šis teksto įvedimas leidžia įrašyti kelias eilutes,
todėl yra užbaigiamas parašius "END", programa tai nurodo.
- Įvedus tekstą, programa, nemodifikuotą tekstą siunčia į kanalą, su atitinkama klaidų tikimybe, ir jį išveda vartotojui.
- Tą patį tekstą siunčia į kodavimo algoritmą, kanalą, su ta pačia klaidos tikimybe, ir dekodavimo algoritmą, kur pataisomos klaidos.
- Dekoduotas vektorius išvedamas vartotojui į ekraną, tada vartotojas gali palyginti šio kodo galimybes taisyti klaidas,
nes ekrane matosi 2 tekstai, vienas siųstas, kanalu, nekoduotas, kitas, siųstas kanalu, koduotas.

## Programos perspektyva.

### Vektorių kodavimas ir dekodavimas:
- Programa gautus vektorius koduoja pagal Golėjaus kodo C23 taisykles.
- Kanalas modifikuoja kiekvieną vektoriaus bituką, neatsižvelgiant į jau padarytų klaidų skaičų.
- Dekodavimo algoritmas pataiso esamas klaidas, dekoduoja vektorių ir grąžina 12 bitukų ilgio dvejetainį vektoriu.
### Teksto kodavimas ir dekodavimas:
- Įvestas tekstas yra skaldomas raidėmis ir paverčiamas dvejetaine reprezentacija, pagal [ASCII](https://www.geeksforgeeks.org/ascii-table/) lentelę.
Jeigu raidės dvejetainis vektorius nesudaro 8 bitukų, trūkstantys bitukai yra papildomi, nuliais vektoriaus pradžioje.
- Ilgas teksto vektorius yra skaldomas į 12 bitukų ilgio vektorių masyvą, jeigu neužtenka bitukų jie yra papildomi vektoriaus gale.
- Gautas masyvas yra siunčiamas į kodavimo ir dekodavimo algoritmus (žiūrėti punktą aukščiau).
- Grįžęs dekoduotas ir pataisytas masyvas yra sujungiamas į vieną eilutę, ir grąžinamos orginalio vertės pagal [ASCII](https://www.geeksforgeeks.org/ascii-table/) lentelę.

<div style="page-break-after: always;"></div>

# Šaltiniai
- [ASCII](https://www.geeksforgeeks.org/ascii-table/)
- [Golay Code](https://klevas.mif.vu.lt/~skersys/doc/ktkt/literatura12.pdf) literatūra.
- [Klaidas taisančių kodų teorija. Gintaras Skersys VU MIF](https://emokymai.vu.lt/pluginfile.php/59194/mod_resource/content/2/KTKT.pdf).
- [Picture to bit string conversion](https://stackoverflow.com/questions/65151790/convert-file-bmp-to-binary-and-binary-back-to-file-bmp-in-java).