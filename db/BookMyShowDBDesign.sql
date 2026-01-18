
--user table
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15),
    Password VARCHAR(255) NOT NULL,
    RegistrationDate DATE NOT NULL
);

--Theatre table
CREATE TABLE Theatre (
    TheatreID INT PRIMARY KEY AUTO_INCREMENT,
    TheatreName VARCHAR(100) NOT NULL,
    Location VARCHAR(255) NOT NULL
);

--screen table
CREATE TABLE Screen (
    ScreenID INT PRIMARY KEY AUTO_INCREMENT,
    TheatreID INT NOT NULL,
    ScreenNumber INT NOT NULL,
    Capacity INT NOT NULL,
    FOREIGN KEY (TheatreID) REFERENCES Theatre(TheatreID)
);

--movie table
CREATE TABLE Movie (
    MovieID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Genre VARCHAR(100),
    Duration INT NOT NULL,
    ReleaseDate DATE,
    Director VARCHAR(100)
);

--show table

CREATE TABLE Show (
    ShowID INT PRIMARY KEY AUTO_INCREMENT,
    ScreenID INT NOT NULL,
    MovieID INT NOT NULL,
    ShowDate DATE NOT NULL,
    ShowTime TIME NOT NULL,
    FOREIGN KEY (ScreenID) REFERENCES Screen(ScreenID),
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID)
);

--booking table
CREATE TABLE Booking (
    BookingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    ShowID INT NOT NULL,
    NumberOfTickets INT NOT NULL,
    BookingDate DATE NOT NULL,
    PaymentStatus VARCHAR(20),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ShowID) REFERENCES Show(ShowID)
);

--ticket table
CREATE TABLE Ticket (
    TicketID INT PRIMARY KEY AUTO_INCREMENT,
    BookingID INT NOT NULL,
    SeatNumber VARCHAR(10),
    FOREIGN KEY (BookingID) REFERENCES Booking(BookingID)
);

INSERT INTO Users VALUES (1, 'Alice', 'alice@gmail.com', '9999999991', 'pwd123', DATE '2026-01-01');
INSERT INTO Users VALUES (2, 'Bob', 'bob@gmail.com', '9999999992', 'pwd456', DATE '2026-01-02');

INSERT INTO Theatre VALUES (1, 'PVR Cinemas', 'Bangalore');
INSERT INTO Theatre VALUES (2, 'INOX', 'Chennai');


INSERT INTO Screen VALUES (1, 1, 1, 200);  -- PVR Screen 1
INSERT INTO Screen VALUES (2, 1, 2, 150);  -- PVR Screen 2
INSERT INTO Screen VALUES (3, 2, 1, 180);  -- INOX Screen 1


INSERT INTO Movie VALUES (1, 'Inception', 'Sci-Fi', 148, DATE '2010-07-16', 'Christopher Nolan');
INSERT INTO Movie VALUES (2, 'Interstellar', 'Sci-Fi', 169, DATE '2014-11-07', 'Christopher Nolan');
INSERT INTO Movie VALUES (3, 'Avatar', 'Fantasy', 162, DATE '2009-12-18', 'James Cameron');


INSERT INTO Show VALUES (1, 1, 1, DATE '2026-01-20', TIME '10:00:00');
INSERT INTO Show VALUES (2, 1, 1, DATE '2026-01-20', TIME '14:00:00');
INSERT INTO Show VALUES (3, 2, 2, DATE '2026-01-20', TIME '18:00:00');
INSERT INTO Show VALUES (4, 3, 3, DATE '2026-01-21', TIME '16:00:00');


INSERT INTO Booking VALUES (1, 1, 1, 2, DATE '2026-01-10', 'PAID');
INSERT INTO Booking VALUES (2, 1, 2, 1, DATE '2026-01-10', 'PAID');
INSERT INTO Booking VALUES (3, 2, 3, 3, DATE '2026-01-11', 'PENDING');


INSERT INTO Ticket VALUES (1, 1, 'A1');
INSERT INTO Ticket VALUES (2, 1, 'A2');
INSERT INTO Ticket VALUES (3, 2, 'B1');
INSERT INTO Ticket VALUES (4, 3, 'C1');
INSERT INTO Ticket VALUES (5, 3, 'C2');
INSERT INTO Ticket VALUES (6, 3, 'C3');


--query to fetch the show details for a given date 
SELECT
    t.TheatreName,
    m.Title AS Movie,
    s.ShowDate,
    s.ShowTime
FROM Show s
JOIN Screen sc ON s.ScreenID = sc.ScreenID
JOIN Theatre t ON sc.TheatreID = t.TheatreID
JOIN Movie m ON s.MovieID = m.MovieID
WHERE t.TheatreName = 'PVR Cinemas'
  AND s.ShowDate = DATE '2026-01-20'
ORDER BY s.ShowTime;
