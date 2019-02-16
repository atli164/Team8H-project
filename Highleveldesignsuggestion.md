Gróft skipulag:

- Eitt interface module sem er hluturinn sem ytri hlutir tala við. Gerir lítið sjálfur en að deila verkefnum niður á raunverulegu module-in.
- Nokkur vinnumodules sem interface module-ið talar við, sirka eitt module fyrir hverja stóra independant töflu í gagnagrunninum
  - Eitt module fyrir hótel, s.s. leitir að hótelum sjálfum og breytingar á þeim + tafla
  - Eitt module fyrir reviews, sækja, leita, skrifa + tafla
  - Eitt module fyrir notendur, sækja og skoða (til að hægt sé að skrá reviews á notendur etc.) + tafla
  - Eitt module fyrir herbergi, sækja, leita, skoða + tafla
  - Eitt module fyrir bókanir, sækja, skoða, bæta við, etc. + tafla
- Þessi module myndi þá hafa samsvarandi töflur sem geyma a.m.k. eftirfarandi upplýsingar fyrir hvert entry
  - Hóteltafla: id, nafn, staðsetning, stjörnufjölda (primary key id)
  - Reviewtafla: id, hótelid, skrifandi, innihald, einkunn, tímasetning (primary key id, index á hotelid)
  - Notendatafla: id, nafn, fleira? (primary key id)
  - Herbergjatafla: id, hotelid, öll ameneties, rúmfjölda, etc. (primary key id, index á hotelid)
  - Bókanataflan: id, roomid, userid?, frá hvaða degi, til hvaða dags, séróskir (texti) (primary key id, index á roomid)
- Með þessum hætti myndi ég vona að þessi module væru sem óháðust svo við getum unnið í þeim í parallel án þess að rugla hvorn annan.
- Fynnst ykkur vanta virknismodule eða töflur í þetta?
