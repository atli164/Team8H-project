#Listi klasa og aðferðir þeirra (allar aðgerðir eru static):
- Booking: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einni bókun á herbergi
- BookingRegistry: Hlutur sem sér um uppflettingu, viðbótum o.s.frv. á Bookings. Aðferðir eru:
  * getBookings sem skilar ArrayList af Bookings og tekur inn dagsetningabil og herbergi, þetta skilar þá öllum Bookings sem snerta það dagsetningabil og eru í því herbergi.
  * getBooking sem skilar Booking og tekur int, þetta skilar Bookingið með inntakið sem id
  * isCurrentlyBooked skilar Boolean og tekur inn Room, segir hvort herbergið sé bókað þegar kallað er á fallið
  * addBooking/removeBooking ætti að segja sig sjálft (skilar boolean sem segir hvort aðgerðin tókst)
- BookingTest: Fyrir skil 4, ætti ekki að skipta máli í lokaskilum hvað sameiningu varðar
- Hotel: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einu hóteli
- HotelQuery: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einni leit á hótelum
- HotelRegistry: Hlutur sem sér um uppflettingu, viðbótum o.s.frv. á Hotels. Aðferðir eru:
  * searchHotels sem skilar ArrayList af Bookings og tekur inn hotelQuery og heiltölu mx, þetta þá skilar öllum hótelum sem passa við queryið en klippir af afganginn eftir mx niðurstöður.
  * getHotel sem skilar Hotel og tekur int, þetta skilar Hotelinu með inntakið sem id
  * addHotel/removeHotel ætti að segja sig sjálft (skilar boolean sem segir hvort aðgerðin tókst)
- Review: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einu review á hóteli
- ReviewRegistry: Hlutur sem sér um uppflettingu, viðbótum o.s.frv. á Reviews. Aðferðir eru:
  * search sem skilar ArrayList af Reviews og tekur inn böns af dóti (sjá fall bara) og leitar að Reviews sem hafa samsvarandi gildi fyrir inntökin sem eru ekki null.
  * getReview sem skilar Review og tekur int, þetta skilar Reviewinu með inntakið sem id
  * addReview/removeReview ætti að segja sig sjálft (skilar boolean sem segir hvort aðgerðin tókst)
- Room: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einu herbergi
- RoomQuery: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einni leit á herbergjum
- RoomRegistry: Hlutur sem sér um uppflettingu, viðbótum o.s.frv. á Rooms. Aðferðir eru:
  * searchRooms sem skilar ArrayList af Bookings og tekur inn roomQuery og heiltölu mx, þetta þá skilar öllum herbergjum sem passa við queryið en klippir af afganginn eftir mx niðurstöður.
  * getRoom sem skilar Room og tekur int, þetta skilar Roominu með inntakið sem id
  * addRoom/removeRoom ætti að segja sig sjálft (skilar boolean sem segir hvort aðgerðin tókst)
- User: Hlutur með engum aðferðum sem geymir upplýsingarnar sem við koma einu user
- UserRegistry: Hlutur sem sér um uppflettingu, viðbótum o.s.frv. á User. Aðferðir eru:
  * search sem skilar ArrayList af Users og tekur inn streng og skilar lista af öllum Users sem hafa username sem passa við þann streng.
  * getUser sem skilar User og tekur int, þetta skilar Userinu með inntakið sem id
  * addUser/removeUser ætti að segja sig sjálft (skilar boolean sem segir hvort aðgerðin tókst)
