Funkcjonalności:
- Rejestracja i logowanie użytkowników (dane zapisywane w pliku tekstowym)
- Dodawanie transakcji – wydatki i dochody z kategorią
- Podsumowanie finansów z wykresem kołowym (dochody vs wydatki)
- Persystencja danych między sesjami (zapis/odczyt z pliku)

Technologie:
- Java (Swing – GUI)
- Programowanie obiektowe (dziedziczenie, interfejsy, enum)

Struktura projektu (Klasa -- Opis)
- Main -- Punkt startowy aplikacji
- BaseWindow -- Abstrakcyjna klasa bazowa dla okien
- LoginWindow / RegistrationWindow -- Okna logowania i rejestracji  
- TransactionEntryWindow -- Dodawanie transakcji
- SummaryWindow -- Podsumowanie z wykresem
- UserDatabase -- Zarządzanie użytkownikami (plik txt)
- TransactionRepository -- Zapis/odczyt transakcji (plik txt)

Uruchomienie (bash):
- javac *.java
- java Main
