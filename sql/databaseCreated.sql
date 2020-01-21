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
    opis varchar(500) )
    
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) values('Mracni vitez', ' Christopher Nolan',
 'Christian Bale, Heath Ledger, Aaron Eckhart', 'Akcija, drama', 140,
'Paramount Pictures', 'USA', 2008, 'Harvi Dent se pridruzuje Betmenu i policiji sa ciljem da se obracuna sa nadolazecom opasnoscu koji sebe nazivam Dzokerom')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) 
values('Sindlerova lista', 'Steven Spielberg', 'Liam Neeson, Ralph Fiennes, Ben Kingsley', 'Drama', 212, 'Warner Bros', 'USA',
2003, 'Film o Oskaru Sindleru, nemackom biznismenu koji je spasao vise od hiljadu poljskih Jevreja tokom holokausta.')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis)
 values('Gospodar prstenova:dve kule', 'Peter Jackson', 'Elijah Wood, Ian McKellen, Viggo Mortensen', 'Naucna fantastika, drama',
212, ' Paramount Pictures', 'USA', 1972, 'Drugi nastavak fantasticne filmske trilogije reditelja Pitera Dzeksona iz 2002. bazirane na romanu Dz.R.R.Tolkina.')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) 
values('Dzoker', 'Todd Phillips', 'Joaquin Phoenix, Robert De Niro, Zazie Beetz', 'Naucna fantastika, triler', 122,
'Warner Bros', 'USA', 1972, 'Americki psihološki triler film iz 2019. godine, baziran prema istoimenom liku iz Di-Si komiksa.')
insert into Film (naziv, reziser, glumci, zanr, trajanje, distributer, zemljaPorekla, godinaProizvodnje, opis) 
values('Vuk sa Vol Strita', 'Martin Scorsese', ' Leonardo DiCaprio, Jonah Hill, Margot Robbie ', 'Krimi, biografija', 
210, ' Paramount Pictures', 'USA', 2013, 'Americka je crna komedija u reziji Martina Skorsezea snimljena po istoimenoj knjizi memoara Dzordana Belforta.')





CREATE TABLE Sala(
    id integer primary key,
    naziv varchar(30) not null,
    tipProjekcije varchar(20) not null,
      tipoviP varchar(12),
    FOREIGN KEY(tipProjekcije) REFERENCES TipProjekcije(id) ON DELETE RESTRICT
   )

CREATE TABLE Sediste(
    redniBroj int not null primary key,
    salaId int not null, 
    FOREIGN KEY(salaId) REFERENCES Sala(id) ON DELETE RESTRICT )

CREATE TABLE TipProjekcije(
    id int primary key,
    tip varchar(8) NOT NULL DEFAULT 'dvaD' )
    
CREATE TABLE Projekcija(
    id int  primary key,
    filmId int not null,
    tipProjekcije varchar(15) not null,
    salaId int not null,
    datumVreme smalldatetime not null,
    cenaKarte DECIMAL(5, 2) NOT NULL DEFAULT 500.00, 
    admin varchar(30) not null ,
        FOREIGN KEY(filmId) REFERENCES Film(id) ON DELETE RESTRICT,
	    FOREIGN KEY(tipProjekcije) REFERENCES TipProjekcije(id) ON DELETE RESTRICT,
	    FOREIGN KEY(salaId) REFERENCES Sala(id) ON DELETE RESTRICT,
	    FOREIGN KEY(admin) REFERENCES Korisnik(korisnickoIme) ON DELETE RESTRICT  )
    
CREATE TABLE Karta(
    id int  primary key,
    projekcijaId int not null,
    idSediste int not null,
    datumVremeProdaje smalldatetime not null,
    imeKorisnika varchar(30) not null, 
   	FOREIGN KEY(projekcijaId) REFERENCES Projekcija(id) ON DELETE RESTRICT,
	FOREIGN KEY(idSediste) REFERENCES Sediste(redniBroj) ON DELETE RESTRICT, 
	FOREIGN KEY(imeKorisnika) REFERENCES Korisnik(korisnickoIme) ON DELETE RESTRICT  )
	    
CREATE TABLE Korisnik(
    korisnickoIme varchar(30) primary key,
    lozinka varchar(50) not null,
    datumRegistracije date not null,
    uloga varchar(8) NOT NULL DEFAULT 'KORISNIK')
    
insert into Korisnik values('aleksandra', 'aleksandra', '2020-01-01', 'KORISNIK')
insert into Korisnik values('marijana', 'markovic', '2020-01-08', 'KORISNIK')
insert into Korisnik values('nikola', 'andric', '2020-01-03', 'KORISNIK')
insert into Korisnik values('nikolina', 'petrovic', '2020-01-15', 'KORISNIK')
insert into Korisnik values('stefan', 'lukic', '2020-01-20', 'KORISNIK')


