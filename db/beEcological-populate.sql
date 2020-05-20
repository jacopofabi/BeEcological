INSERT INTO beecological.user VALUE 
    ("user","user","user","user","user@gmail.com","4445556667",0),
	("tester","tester","test","test","test@gmail.com","1112223334",0);
    
INSERT INTO beecological.center VALUE
	("The Trasher","0775000000","Roma","Via dei fiori","00127","23"),
	("La vecchia mola","0775111111","Ravenna","Viale abolfo","15663","11"),
    ("Verona pulita","0245512368","Verona","Via nonna luisa","54642","2"),
	("Impianto comunale Viterbo","0210069584","Viterbo","Via danimarca","03058","65"),
    ("Centro smaltimento Acacia","0599665854","Roma","Via dei rovi","00127","21"),
	("Oasi Ecologica Tiburtina","0432995867","Roma","Via Tiburtina","00127","27"),
    ("Centro smaltimento","0977451986","Roma","Via Lameziana","00127","54"),
    ("Scarico rifiuti Milano2","0775120122","Milano","Viale Germania","05123","8"),
    ("Smaltimento rifiuti Damiani","0712129348","Milano","Via dei galli","05123","73");	


INSERT INTO beecological.owner VALUE
	("theBoss","bbbbbbbb","Mario","Bettini","marybetty@gmail.com","3331111111","The Trasher"),
	("theManager","aaaaaaaa","Umberto","Fantini","umbyfanty@gmail.com","3330000000","La vecchia mola"),
    ("theRicilatore","cccccccc","Davide","Rossi","rosdav@yahoo.it","3153669785","Verona pulita"),
	("theOwner","eeeeeeee","Marco","Milito","milito22@libero.it","3311144567","Impianto comunale Viterbo"),
	("admin","admin","Antonio","Gialli","gialli@yahoo.it","3212289756","Oasi Ecologica Tiburtina"),
	("theMaster","ffffffff","Gennaro","Verdi","ringhio@yahoo.it","3412697855","Centro smaltimento Acacia"),
	("gestore","gestore","Matteo","Marroni","marroni@gmail.com","3335675123","Centro smaltimento"),
	("centerAdmin","centerAdmin","Marcus","Little","marclittle@gmail.com","3412697855","Scarico rifiuti Milano2"),
	("milanoscarichi","milanoscarichi","Simone","Bianchini","bianchini@yahoo.it","3412697855","Smaltimento rifiuti Damiani");

INSERT INTO beecological.unload(user,center,date,time) VALUE
	("tester","La vecchia mola","2019-09-02","10:00"),
    ("tester","The Trasher","2020-01-03","18:30"),
    ("user","Verona Pulita","2020-01-21","09:30"),
    ("user","Impianto comunale Viterbo","2020-01-02","15:30"),
	("tester","Centro smaltimento Acacia","2020-01-03","18:30"),
    ("tester","Oasi ecologica Tiburtina","2019-09-02","10:00"),
    ("user","Centro smaltimento","2020-01-21","09:00"),
    ("user","Scarico rifiuti Milano2","2020-01-02","16:00"),
	("tester","Smaltimento rifiuti Damiani","2020-01-21","17:00");
    
INSERT INTO beecological.bookingRequest(user,center,date,time,status) VALUE 
	("user","The Trasher","2020-01-03","18:30","R"),
    ("tester","La vecchia mola","2019-09-02","10:00","R"),
    ("user","Verona Pulita","2020-01-21","09:30","R"),
	("tester","Impianto comunale Viterbo","2020-01-02","15:30","R"),
    ("user","Centro smaltimento Acacia","2020-01-03","18:30","R"),
    ("tester","Oasi ecologica Tiburtina","2019-09-02","10:00","R"),
    ("user","Centro smaltimento","2020-01-21","09:00","R"),
	("tester","Scarico rifiuti Milano2","2020-01-02","16:00","R"),
	("user","Smaltimento rifiuti Damiani","2020-01-21","17:00","R"),
	("tester","La vecchia mola","2020-02-10","16:00","A"),
    ("user","The Trasher","2020-02-11","18:00","A"),
    ("tester","Verona Pulita","2019-02-13","10:00","A"),
    ("user","Impianto comunale Viterbo","2020-02-15","09:30","A"),
	("tester","Centro smaltimento Acacia","2020-02-19","16:00","A"),
    ("user","Oasi Ecologica Tiburtina","2020-02-20","18:30","A"),
    ("tester","Centro smaltimento","2020-02-12","10:00","A"),
    ("user","Scarico rifiuti Milano2","2020-02-11","09:30","A"),
	("tester","Smaltimento rifiuti Damiani","2020-02-19","15:30","A"),
    ("user","The Trasher","2020-03-11","18:00","W"),
    ("tester","La vecchia mola","2020-03-12","10:00","W"),
    ("user","Verona Pulita","2020-03-13","09:30","W"),
	("tester","Impianto comunale Viterbo","2020-03-14","17:00","W"),
    ("user","Centro smaltimento Acacia","2020-03-15","11:00","W"),
    ("tester","Oasi ecologica Tiburtina","2020-03-15","12:30","W"),
    ("user","Centro smaltimento","2020-03-21","09:30","W"),
	("tester","Scarico rifiuti Milano2","2020-03-22","16:00","W"),
	("user","Smaltimento rifiuti Damiani","2020-03-26","09:30","W");
	
INSERT INTO beecological.waste VALUE
	("Bulky waste",1),
	("Glass",1),
	("Mixed material packaging",3),
	("Plastic packaging",3),
	("Metal packaging",2),
	("Toner and cartridges",4),
	("Battery",4),
	("Organic waste",1),
	("Electrical and electronic equipment",2),
	("Cold and climate",4),
	("Wood waste",1),
	("Mercury",5),
	("Textile",1),
	("Tires",3);
    
INSERT INTO beecological.wasteunloaded(user,center,date,time,waste,wastequantity) VALUE
	("tester","La vecchia mola","2019-09-02","10:00", "Bulky waste", 10),
    ("tester","The Trasher","2020-01-03","18:30", "Mixed material packaging", 50),
    ("user","Verona Pulita","2020-01-21","09:30", "Wood waste", 100),
    ("user","Impianto comunale Viterbo","2020-01-02","15:30", "Metal packaging", 15),
	("tester","Centro smaltimento Acacia","2020-01-03","18:30", "Organic waste", 19),
    ("tester","Oasi Ecologica Tiburtina","2019-09-02","10:00", "Mercury", 3),
    ("user","Centro smaltimento","2020-01-21","09:00", "Textile", 11),
    ("user","Scarico rifiuti Milano2","2020-01-02","16:00", "Tires", 10),
	("tester","Smaltimento rifiuti Damiani","2020-01-21","17:00", "Glass", 30);