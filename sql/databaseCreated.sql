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
    
CREATE TABLE Sala(
    id integer primary key,
    naziv varchar(30) not null,
    tipProjekcije varchar(20) not null,
      tipoviP varchar(12),
    FOREIGN KEY(tipProjekcije) REFERENCES TipProjekcije(id) ON DELETE RESTRICT
   )

CREATE TABLE Sediste(
    redniBroj int not null primary key,
    salaId int not null,  --foreign key
    FOREIGN KEY(salaId) REFERENCES Sala(id) ON DELETE RESTRICT )

CREATE TABLE TipProjekcije(
    id int primary key,
    tip varchar(8) NOT NULL DEFAULT 'dvaD' )
    
CREATE TABLE Projekcija(
    id int  primary key,
    filmId int not null, --id filma
    tipProjekcije varchar(15) not null,--tip 
    salaId int not null, --id sale
    datumVreme smalldatetime not null,
    cenaKarte DECIMAL(5, 2) NOT NULL DEFAULT 500.00, 
    admin varchar(30) not null ,-- admin
        FOREIGN KEY(filmId) REFERENCES Film(id) ON DELETE RESTRICT,
	    FOREIGN KEY(tipProjekcije) REFERENCES TipProjekcije(id) ON DELETE RESTRICT,
	    FOREIGN KEY(salaId) REFERENCES Sala(id) ON DELETE RESTRICT,
	    FOREIGN KEY(admin) REFERENCES Korisnik(korisnickoIme) ON DELETE RESTRICT  )
    
CREATE TABLE Karta(
    id int  primary key,
    projekcijaId int not null, --id projekcije
    idSediste int not null, --id sedista
    datumVremeProdaje smalldatetime not null,
    imeKorisnika varchar(30) not null, --id korisnika
   	FOREIGN KEY(projekcijaId) REFERENCES Projekcija(id) ON DELETE RESTRICT,
	FOREIGN KEY(idSediste) REFERENCES Sediste(redniBroj) ON DELETE RESTRICT, 
	FOREIGN KEY(imeKorisnika) REFERENCES Korisnik(korisnickoIme) ON DELETE RESTRICT  )
	    
	    
CREATE TABLE Korisnik(
    korisnickoIme varchar(30) primary key,
    lozinka varchar(50) not null,
    datumRegistracije date not null,
    uloga varchar(8) NOT NULL DEFAULT 'KORISNIK')
    
insert into Korisnik values('aleksandra', 'a123', '2020-01-01', 'KORISNIK')
