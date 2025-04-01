# 💳 Bankomat – aplikacja w Javie (CLI)

Aplikacja symuluje działanie bankomatu w trybie tekstowym. Pozwala użytkownikowi na:
- logowanie kartą i PIN-em,
- sprawdzanie salda konta,
- dokonywanie wpłat i wypłat,
- przeglądanie historii transakcji (wszystkich lub tylko wpłat i wypłat),
- zmianę PIN-u,
- wylogowanie się.

---

## 🚀 Jak uruchomić aplikację

### 🔧 Wymagania
- Java 17+
- Maven 3+

### 📦 Kompilacja i uruchomienie
```bash
mvn clean compile
mvn exec:java 
```
### 🧪 Uruchamianie testów
```bash
mvn test
```

Testowane scenariusze:

- poprawna i błędna autoryzacja PIN,

- wpłaty i wypłaty środków,

- historia transakcji i ich typy,

- zabezpieczenia przed operacjami bez zalogowania,

- nieprawidłowe dane wejściowe (np. zły PIN, brak konta).

## 🧱 Struktura projektu

<pre> <code> src/ ├── main/ │ └── java/ │ └── bankomat/ │ ├── ATM.java │ ├── Account.java │ ├── Bank.java │ ├── Card.java │ ├── Person.java │ ├── Transaction.java │ └── generator/ │ ├── AccountNumberGenerator.java │ └── CardNumberGenerator.java ├── test/ │ └── java/ │ └── bankomat/ │ ├── ATMTest.java │ ├── AccountTest.java │ ├── BankTest.java │ └── CardTest.java </code> </pre>



