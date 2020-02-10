create database bioskopPrizma ? 

use bioskopPrizma

CREATE TABLE Film(
    id integer primary key,
    naziv varchar(50) not null,
    reziser varchar(50),
    glumci varchar(250),
    zanr varchar(50),
    trajanje int not null,
    distributer varchar(50) not null,
    zemljaPorekla varchar(40) not null,
    godinaProizvodnje int not null,
    opis varchar(500)
    deleted varchar(3) default 'no')
    
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) values('Mracni vitez', ' Christopher Nolan',
 'Christian Bale, Heath Ledger, Aaron Eckhart', 'Akcija, drama', 140,
'Paramount Pictures', 'USA', 2008, 'Harvi Dent se pridruzuje Betmenu i policiji sa ciljem da se obracuna sa nadolazecom opasnoscu koji sebe nazivam Dzokerom', 'no')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Sindlerova lista', 'Steven Spielberg', 'Liam Neeson, Ralph Fiennes, Ben Kingsley', 'Drama', 212, 'Warner Bros', 'USA',
2003, 'Film o Oskaru Sindleru, nemackom biznismenu koji je spasao vise od hiljadu poljskih Jevreja tokom holokausta.', 'no')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted)
 values('Gospodar prstenova:dve kule', 'Peter Jackson', 'Elijah Wood, Ian McKellen, Viggo Mortensen', 'Naucna fantastika, drama',
212, ' Paramount Pictures', 'USA', 1972, 'Drugi nastavak fantasticne filmske trilogije reditelja Pitera Dzeksona iz 2002. bazirane na romanu Dz.R.R.Tolkina.', 'no')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Dzoker', 'Todd Phillips', 'Joaquin Phoenix, Robert De Niro, Zazie Beetz', 'Naucna fantastika, triler', 122,
'Warner Bros', 'USA', 1972, 'Americki psihološki triler film iz 2019. godine, baziran prema istoimenom liku iz Di-Si komiksa.', 'no')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Vuk sa Vol Strita', 'Martin Scorsese', ' Leonardo DiCaprio, Jonah Hill, Margot Robbie ', 'Krimi, biografija', 
210, ' Paramount Pictures', 'USA', 2013, 'Americka crna komedija u reziji Martina Skorsezea snimljena po istoimenoj knjizi memoara Dzordana Belforta.', 'no')

insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Kad jaganjci utihnu', 'Ted Tally', 'Jodie Foster, Anthony Hopkins, Scott Glenn, Ted Levine', 'Horor', 
118, ' Orion Pictures', 'USA', 1991, ' FBI je u potrazi za serijskim ubicom koga su prozvali Bafalo Bil, koji otima zene i skida im kozu.
 FBI salje Kleris Sterling, da ispita Hanibala Lektora, psihijatra koji je postao kanibalisticki serijski ubica.', 'no')

 insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Forrest Gump', ' Robert Zemeckis', ' Tom Hanks, Robin Wright, Gary Sinise', 'Drama', 
168, ' Orion Pictures', 'USA', 1994, ' Film prati zivot Forest Gampa i njegovo upoznavanje s Dzeni, jedinom zenom koju je ikada voleo.','no')

insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Matriks', 'Lana Wachowski', ' Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss', 'Naucna fantastika, akcija', 
142, ' Orion Pictures', 'USA', 1999, 'Tomas je programer, nocu vodi tajni zivot kao haker Neo.
Zeli da sazna odgovor na pitanje sta je to Matriks? Morfeus je najtrazeniji svetski haker koji stupa u kontakt sa Neom
 i upoznaje ga sa pravom prirodom sveta u kome se nalazi.', 'no')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Ubiti Bila:1', 'Quentin Tarantino', 'Uma Thurman, David Carradine, Daryl Hannah', 'Naucna fantastika, akcija', 
138, ' Orion Pictures', 'USA', 2003, 'Cetiri godine je proslo otkad je na svom vencanju dobila metak u glavu. Mlada se budi iz kome i 
odluci da je vreme za osvetu… Ovu borbu „ubij ili budi ubijen“ ona nije zapocela, ali odlucna je da je zavrsi!', 'no')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis, deleted) 
values('Hari Poter i relikvije Smrti:2', 'David Yates', 'Daniel Radcliffe, Emma Watson, Rupert Grint', 'Naucna fantastika, drama', 
127, ' Orion Pictures', 'USA', 2003, 'Nakon deset godina i sedam filmova, dosao je kraj druzenja sa Harry Potterom na filmskom platnu. 
Osmi film je i poslednji deo sage o decaku carobnjaku.', 'no')



CREATE TABLE Sala(
    id integer primary key,
    naziv varchar(30) not null,
    tipProjekcije varchar(20) not null,
      tipoviP varchar(12),
    FOREIGN KEY(tipProjekcije) REFERENCES TipProjekcije(id) ON DELETE RESTRICT)
insert into Sala (naziv, tipProjekcije) values ('Prva sala',1)
insert into Sala (naziv, tipProjekcije) values ('Druga sala',2)
insert into Sala (naziv, tipProjekcije) values ('Treca sala',3)
insert into Sala (naziv, tipProjekcije, tipoviP) values ('Cetvrta sala',1,'triD' )
insert into Sala (naziv, tipProjekcije, tipoviP) values ('Peta sala',2, 'cetiriD')


CREATE TABLE Sediste(
	id integer primary key,
    redniBroj int not null,
    salaId int not null, 
    FOREIGN KEY(salaId) REFERENCES Sala(id) ON DELETE RESTRICT )
    --prva sala
insert into Sediste(rednibroj, salaId) values(1,1) 
insert into Sediste(rednibroj, salaId) values(2,1)
insert into Sediste(rednibroj, salaId) values(3,1)
insert into Sediste(rednibroj, salaId) values(4,1) 
--druga sala
insert into Sediste(rednibroj, salaId) values( 1,2)
insert into Sediste(rednibroj, salaId) values( 2,2)
insert into Sediste(rednibroj, salaId) values( 3,2)
insert into Sediste(rednibroj, salaId) values( 4,2)
--treca sala
insert into Sediste(rednibroj, salaId) values( 1,3)
insert into Sediste(rednibroj, salaId) values( 2,3)
insert into Sediste(rednibroj, salaId) values( 3,3)
insert into Sediste(rednibroj, salaId) values( 4,3)
insert into Sediste(rednibroj, salaId) values( 5,3)
--cetvrta sala
insert into Sediste(rednibroj, salaId) values( 1,4)
insert into Sediste(rednibroj, salaId) values( 2,4)
insert into Sediste(rednibroj, salaId) values( 3,4)
insert into Sediste(rednibroj, salaId) values( 4,4)
insert into Sediste(rednibroj, salaId) values( 5,4)
insert into Sediste(rednibroj, salaId) values( 6,4)
--peta sala
insert into Sediste(rednibroj, salaId) values( 1,5)
insert into Sediste(rednibroj, salaId) values( 2,5)
insert into Sediste(rednibroj, salaId) values( 3,5)
insert into Sediste(rednibroj, salaId) values( 4,5)
insert into Sediste(rednibroj, salaId) values( 5,5)
insert into Sediste(rednibroj, salaId) values( 6,5)
insert into Sediste(rednibroj, salaId) values( 7,5)


CREATE TABLE TipProjekcije(
    id integer primary key,
    tip varchar(8) NOT NULL DEFAULT 'dvaD' )
    
 insert into TipProjekcije (tip) values('dvaD')
 insert into TipProjekcije (tip) values('triD')
 insert into TipProjekcije (tip) values('cetiriD')

    
CREATE TABLE Projekcija(
    id integer  primary key,
    filmId int not null,
    tipProjekcije varchar(15) not null,
    salaId int not null,
    datumVreme smalldatetime not null,
    cenaKarte DECIMAL(5, 2) NOT NULL DEFAULT 500.00, 
    admin varchar(30) not null,
    deleted varchar(3) default 'no',
        FOREIGN KEY(filmId) REFERENCES Film(id) ON DELETE RESTRICT,
	    FOREIGN KEY(tipProjekcije) REFERENCES TipProjekcije(id) ON DELETE RESTRICT,
	    FOREIGN KEY(salaId) REFERENCES Sala(id) ON DELETE RESTRICT,
	    FOREIGN KEY(admin) REFERENCES Korisnik(korisnickoIme) ON DELETE RESTRICT
)

CREATE TABLE Karta(
    id integer  primary key,
    projekcijaId int not null,
    idSediste int not null,
    datumVremeProdaje smalldatetime not null,
    imeKorisnika varchar(30) not null, 
     deleted varchar(3) default 'no',
   	FOREIGN KEY(projekcijaId) REFERENCES Projekcija(id) ON DELETE RESTRICT,
	FOREIGN KEY(idSediste) REFERENCES Sediste(id) ON DELETE RESTRICT, 
	FOREIGN KEY(imeKorisnika) REFERENCES Korisnik(korisnickoIme) ON DELETE RESTRICT
)
	    
CREATE TABLE Korisnik(
    korisnickoIme varchar(30) primary key,
    lozinka varchar(50) not null,
    datumRegistracije date not null,
    uloga varchar(8) NOT NULL DEFAULT 'KORISNIK',
        deleted varchar(3) default 'no'
)
    
insert into Korisnik values('admin', 'admin', '1580938194872', 'ADMIN', 'no')
insert into Korisnik values('user', 'user', '1580938194872', 'KORISNIK', 'no')
insert into Korisnik values('user1', 'user1', '1580938194872', 'KORISNIK', 'no')


